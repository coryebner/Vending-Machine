package hardware.test.stub;
import hardware.funds.*;

public class BanknoteSlotListenerStub extends AbstractStub implements BanknoteSlotListener{
	public void BanknoteListenerStub(){
		
	}

	@Override
	public void validBanknoteInserted(BanknoteSlot banknoteSlot,
			Banknote banknote) {
		recordCallTo("validBanknoteInserted");
		
	}

	@Override
	public void banknoteRejected(BanknoteSlot banknoteSlot, Banknote banknote) {
		recordCallTo("banknoteRejected");
		
	}

}
