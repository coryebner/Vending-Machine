package hardware.test.stub;

import hardware.DeliveryChuteListener;
import hardware.DeliveryChute;

public class DeliveryChuteListenerStub extends AbstractStub implements
        DeliveryChuteListener {
    @Override
    public void itemDelivered(DeliveryChute chute) {
	recordCallTo("itemDelivered");
    }

    @Override
    public void doorOpened(DeliveryChute chute) {
	recordCallTo("doorOpened");
    }

    @Override
    public void doorClosed(DeliveryChute chute) {
	recordCallTo("doorClosed");
    }

    @Override
    public void chuteFull(DeliveryChute chute) {
	recordCallTo("chuteFull");
    }
}
