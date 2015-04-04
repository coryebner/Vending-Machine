package business.config;

import business.selection_delivery.AbstractControllerListener;

public interface ConfigurationListener extends AbstractControllerListener {
	public void nameChanged(int index, String newName);
	public void priceChanged(int index, int newPrice);
}
