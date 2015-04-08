package SDK.rifffish;

public class Product {
	private int id = -1;
	private int machine_id = -1;
	private String name = null;
	private int price = -1;
	private int current_stock_level = -1;
	private int max_stock_level = -1;
	
	public Product(int machine_id, String name, int price, int current_stock_level, int max_stock_level) {
		this.machine_id = machine_id;
		this.name = name;
		this.price = price;
		this.current_stock_level = current_stock_level;
		this.max_stock_level = max_stock_level;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getMachineId() {
		return machine_id;
	}
	
	public void setMachineId(int machine_id) {
		this.machine_id = machine_id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getCurrentStockLevel() {
		return current_stock_level;
	}
	
	public void setCurrentStockLevel(int current_stock_level) {
		this.current_stock_level = current_stock_level;
	}
	
	public int getMaxStockLevel() {
		return max_stock_level;
	}
	
	public void setMaxStockLevel(int max_stock_level) {
		this.max_stock_level = max_stock_level;
	}
	
}