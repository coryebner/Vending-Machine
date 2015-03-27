package hardware.test.stub;

import hardware.CardSlotListener;
import hardware.CardSlot;

public class CardSlotListenerStub extends AbstractStub implements
        CardSlotListener {
    @Override
    public void cardInserted(CardSlot slot) {
	recordCallTo("cardInserted");
    }

    @Override
    public void cardEjected(CardSlot slot) {
	recordCallTo("cardEjected");
    }
}
