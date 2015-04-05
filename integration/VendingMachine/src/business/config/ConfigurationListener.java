package business.config;

import business.selection_delivery.AbstractControllerListener;

public interface ConfigurationListener extends AbstractControllerListener {
	public void priceChanged(int index, int newPrice);
	public void nameChanged(int index, String newName);
}
