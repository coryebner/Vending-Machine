package hardware.test.stub;

import hardware.ui.IndicatorLight;
import hardware.ui.IndicatorLightListener;

public class IndicatorLightListenerStub extends AbstractStub implements
        IndicatorLightListener {
    @Override
    public void activated(IndicatorLight light) {
	recordCallTo("activated");
    }

    @Override
    public void deactivated(IndicatorLight light) {
	recordCallTo("deactivated");
    }
}
