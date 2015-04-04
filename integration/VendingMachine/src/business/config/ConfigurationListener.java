package business.config;

public interface ConfigurationListener {
	public void nameChanged(int index, String newName);
	public void priceChanged(int index, int newPrice);
}
