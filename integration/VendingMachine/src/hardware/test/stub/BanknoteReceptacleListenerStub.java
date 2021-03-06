package hardware.test.stub;

import hardware.funds.Banknote;
import hardware.funds.BanknoteReceptacle;
import hardware.funds.BanknoteReceptacleListener;

public class BanknoteReceptacleListenerStub extends AbstractStub implements
        BanknoteReceptacleListener {
    @Override
    public void enabled(BanknoteReceptacle receptacle) {
	recordCallTo("enabled");
    }

    @Override
    public void disabled(BanknoteReceptacle receptacle) {
	recordCallTo("disabled");
    }

	@Override
	public void banknoteAdded(BanknoteReceptacle receptacle, Banknote banknote) {
		recordCallTo("banknoteAdded");		
	}

	@Override
	public void banknoteRemoved(BanknoteReceptacle receptacle) {
		recordCallTo("banknoteRemoved");		
	}

	@Override
	public void banknoteFull(BanknoteReceptacle receptacle) {
		recordCallTo("banknoteFull");		
	}

}
