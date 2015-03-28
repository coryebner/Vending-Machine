package hardware.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import hardware.channels.CoinChannel;
import hardware.exceptions.CapacityExceededException;
import hardware.exceptions.DisabledException;
import hardware.exceptions.SimulationException;
import hardware.funds.Coin;
import hardware.funds.CoinReceptacle;
import hardware.test.stub.AbstractCoinAcceptorStub;
import hardware.test.stub.CoinReceptacleListenerStub;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CoinReceptacleTest {
    private Coin coin, invalidCoin;
    private CoinReceptacle receptacle;
    private AbstractCoinAcceptorStub coinRackStub, fullCoinRackStub;
    private Map<Integer, CoinChannel> rackChannels;
    private AbstractCoinAcceptorStub deliveryChuteStub;
    private AbstractCoinAcceptorStub storageBinStub;
    private CoinReceptacleListenerStub coinReceptacleListener;

    @Before
    public void setup() {
	coin = new Coin(1);
	invalidCoin = new Coin(2);

	receptacle = new CoinReceptacle(2);

	coinRackStub = new AbstractCoinAcceptorStub(true);
	fullCoinRackStub = new AbstractCoinAcceptorStub(false);

	rackChannels = new HashMap<Integer, CoinChannel>();
	rackChannels.put(new Integer(1), new CoinChannel(coinRackStub));
	deliveryChuteStub = new AbstractCoinAcceptorStub(true);
	storageBinStub = new AbstractCoinAcceptorStub(true);
	coinReceptacleListener = new CoinReceptacleListenerStub();

	receptacle.connect(rackChannels, new CoinChannel(deliveryChuteStub), new CoinChannel(storageBinStub));
	receptacle.register(coinReceptacleListener);

	coinReceptacleListener.init();
	deliveryChuteStub.init();
	storageBinStub.init();
	coinRackStub.init();
	fullCoinRackStub.init();
    }

    @After
    public void teardown() {
	coin = null;
	invalidCoin = null;

	receptacle.connect(null, null, null);
	receptacle.deregisterAll();
	receptacle = null;

	coinRackStub = null;
	fullCoinRackStub = null;

	rackChannels.clear();
	rackChannels = null;

	deliveryChuteStub = null;
	storageBinStub = null;
	coinReceptacleListener = null;
    }

    @Test
    public void testCoinReceptacleStore() throws CapacityExceededException, DisabledException {
	receptacle.loadWithoutEvents(coin, coin);
	coinRackStub.expect("hasSpace", "acceptCoin", "hasSpace", "acceptCoin");
	coinReceptacleListener.expect("coinsRemoved");

	receptacle.storeCoins();
	coinRackStub.assertProtocol();
	coinReceptacleListener.assertProtocol();
    }

    @Test
    public void testCoinReceptacleStoreDisabled() throws CapacityExceededException, DisabledException {
	receptacle.loadWithoutEvents(coin, coin);
	coinReceptacleListener.expect("disabled");

	try {
	    receptacle.disable();
	    receptacle.storeCoins();
	    fail();
	}
	catch(DisabledException e) {
	}

	coinRackStub.assertProtocol();
	coinReceptacleListener.assertProtocol();
    }

    @Test
    public void testCoinReceptacleStoreFull() throws DisabledException {
	receptacle.loadWithoutEvents(coin, coin);

	try {
	    receptacle.acceptCoin(coin);
	    fail();
	}
	catch(CapacityExceededException e) {
	}
	coinReceptacleListener.assertProtocol();
    }

    @Test
    public void testCoinReceptacleStoreBecomingFull() throws CapacityExceededException, DisabledException {
	receptacle.loadWithoutEvents(coin);
	coinReceptacleListener.expect("coinAdded", "coinsFull");

	assertTrue(receptacle.hasSpace());
	receptacle.acceptCoin(coin);
	assertFalse(receptacle.hasSpace());

	coinReceptacleListener.assertProtocol();
    }

    @Test
    public void testCoinReceptacleStoreEmpty() throws CapacityExceededException, DisabledException {
	receptacle.storeCoins();
    }

    @Test
    public void testCoinReceptacleStoreFullWithOverflow() throws CapacityExceededException, DisabledException {
	rackChannels.clear();
	rackChannels.put(new Integer(1), new CoinChannel(fullCoinRackStub));
	receptacle.connect(rackChannels, new CoinChannel(deliveryChuteStub), new CoinChannel(storageBinStub));

	fullCoinRackStub.expect("hasSpace");
	storageBinStub.expect("hasSpace", "acceptCoin");
	coinReceptacleListener.expect("coinsRemoved");

	receptacle.loadWithoutEvents(coin);

	receptacle.storeCoins();
	fullCoinRackStub.assertProtocol();
	storageBinStub.assertProtocol();
	coinReceptacleListener.assertProtocol();
    }

    @Test
    public void testCoinReceptacleInvalid() throws CapacityExceededException, DisabledException {
	receptacle.loadWithoutEvents(invalidCoin);
	coinReceptacleListener.expect("coinsRemoved");
	storageBinStub.expect("hasSpace");
	storageBinStub.expect("acceptCoin");

	receptacle.storeCoins();

	coinReceptacleListener.assertProtocol();
	storageBinStub.assertProtocol();
    }

    @Test
    public void testCoinReceptacleReturn() throws CapacityExceededException, DisabledException {
	receptacle.loadWithoutEvents(coin);
	deliveryChuteStub.expect("acceptCoin");
	coinReceptacleListener.expect("coinsRemoved");

	receptacle.returnCoins();
	deliveryChuteStub.assertProtocol();
	coinReceptacleListener.assertProtocol();
    }

    @Test
    public void testCoinReceptacleReturnDisabled() throws CapacityExceededException {
	receptacle.loadWithoutEvents(coin);
	coinReceptacleListener.expect("disabled");

	try {
	    receptacle.disable();
	    receptacle.returnCoins();
	    fail();
	}
	catch(DisabledException e) {
	}

	deliveryChuteStub.assertProtocol();
	coinReceptacleListener.assertProtocol();
    }

    @Test
    public void testCoinReceptacleReturnEmpty() throws CapacityExceededException, DisabledException {
	receptacle.returnCoins();
	coinReceptacleListener.assertProtocol();
	deliveryChuteStub.assertProtocol();
    }

    @Test
    public void testCoinReceptacleDisabled() {
	coinReceptacleListener.expect("disabled", "enabled");

	receptacle.disable();
	assertTrue(receptacle.isDisabled());
	receptacle.enable();

	coinReceptacleListener.assertProtocol();
    }

    @Test
    public void testCoinReceptacleAddCoinToDisabled() throws CapacityExceededException {
	coinReceptacleListener.expect("disabled");

	receptacle.disable();
	assertTrue(receptacle.isDisabled());
	try {
	    receptacle.acceptCoin(coin);
	    fail();
	}
	catch(DisabledException e) {
	}

	coinReceptacleListener.assertProtocol();
    }

    @Test(expected = SimulationException.class)
    public void testCoinReceptacleOverload() {
	receptacle.loadWithoutEvents(coin, coin, coin);
    }

    @Test(expected = SimulationException.class)
    public void testCoinReceptacleBadSetup1() {
	receptacle = new CoinReceptacle(0);
    }

    @Test(expected = SimulationException.class)
    public void testCoinReceptacleBadSetup2() {
	receptacle = new CoinReceptacle(-1);
    }

    @Test(expected = SimulationException.class)
    public void testCoinReceptacleStoreNoOther() throws CapacityExceededException, DisabledException {
	rackChannels.clear();
	rackChannels.put(new Integer(1), new CoinChannel(fullCoinRackStub));
	receptacle.connect(rackChannels, null, null);
	receptacle.loadWithoutEvents(coin);

	fullCoinRackStub.expect("hasSpace");

	try {
	    receptacle.storeCoins();
	}
	finally {
	    fullCoinRackStub.assertProtocol();
	}
    }
}