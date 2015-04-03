package gui;

import java.awt.Component;
import java.awt.Container;

public abstract class GUIHelper {

	/**
	 * Will enable or disable all of the components in the passed container
	 * 
	 * @param container
	 *            is having its components enabled or disabled
	 * @param enable
	 *            , when true all components are enabled, when false, they are
	 *            disabled
	 */
	public static void enableComponents(Container container, boolean enable) {
		Component[] components = container.getComponents();
		for (Component component : components) {
			component.setEnabled(enable);
			if (component instanceof Container) {
				enableComponents((Container) component, enable);
			}
		}
	}
}
