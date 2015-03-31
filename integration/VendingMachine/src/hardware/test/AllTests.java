package hardware.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(
		{ HardwareTest.class, 
			hardware.test.funds.CardSlotTest.class,
			hardware.test.funds.CoinReceptacleTest.class,
			hardware.test.funds.CoinSlotTest.class,
			hardware.test.racks.CoinRackTest.class,
			hardware.test.racks.PopCanRackTest.class,
			hardware.test.ui.DeliveryChuteTest.class,
			hardware.test.ui.DisplayTest.class,
			hardware.test.ui.LockTest.class,
			hardware.test.ui.SelectionButtonTest.class
		})

public class AllTests {}
