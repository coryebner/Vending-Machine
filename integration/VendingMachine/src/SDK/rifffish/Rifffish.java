package SDK.rifffish;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import SDK.rifffish.endpoints.MachinesService;
import SDK.rifffish.endpoints.ProblemService;
import SDK.rifffish.endpoints.StockoutService;
import SDK.rifffish.endpoints.ProductsService;
import SDK.rifffish.endpoints.TransactionsService;

public class Rifffish {
	private RestAdapter restAdapter = null;
	private final static String RIFFFISH_API_URL = "http://rifffish.com/api";
	
	public static enum PaymentMethod {COIN, CREDIT_CARD, PAYPAL};
	public static enum ProblemTypes {OUTOFORDER, FAIL};
	public static enum StockoutTypes {OUTOFSTOCK, ALMOSTOUT};
	
	/**
	 * Rifffish is a powerful way to manage your vending machines
	 * See API/SDK documentation for more information
	 * 
	 * @param API_TOKEN, found in profile page under API keys
	 */
	public Rifffish(final String API_TOKEN) {
		this(API_TOKEN, RIFFFISH_API_URL);		
	}
	
	public Rifffish(final String API_TOKEN, String API_URL) {
		RequestInterceptor requestInterceptor = new RequestInterceptor() {
			@Override
				public void intercept(RequestFacade request) {
					request.addHeader("Content-Type", "application/json");
					request.addHeader("X-RIFFFISH-TOKEN", API_TOKEN);
				}
			};
			
		restAdapter = new RestAdapter.Builder()
	    .setEndpoint(API_URL)
	    .setRequestInterceptor(requestInterceptor)
	    .build();
	}
	
	/**
	 * Log for Transactions 
	 * Logs a Transaction to our API
	 * @param t, A transaction that is being logged
	 * @return Error, returns null when transaction was logged successfully, 
	 * 		   else returns an Error (which could be parsed or just printed)
	 */
	public Error log(Transaction t) {
		Error error = null;
		
		TransactionsService service = restAdapter.create(TransactionsService.class);		
		
		try {
			service.createTransaction(t);
		} catch(Exception e) {
			error = new Error("400 - Bad Request. Transaction Malformed.");
		}
		
		return error;
 	}
	
	/**
	 * Log for Problems
	 * Logs a Problem to our API
	 * @param p, A problem that is being logged
	 * @return Error, returns null when problem was logged successfully, 
	 * 		   else returns an Error (which could be parsed or just printed)
	 */
	public Error log(Problem p) {
		Error error = null;
		
		ProblemService service = restAdapter.create(ProblemService.class);		
		
		try {
			service.createProblem(p);
		} catch(Exception e) {
			System.out.println(e);
			error = new Error("400 - Bad Request. Problem Malformed.");
		}
		
		return error;
 	}
	
	/**
	 * Log for Stockout
	 * Logs a Stockout to our API
	 * @param p, A stockout that is being logged
	 * @return Error, returns null when stockout was logged successfully, 
	 * 		   else returns an Error (which could be parsed or just printed)
	 */
	public Error log(Stockout p) {
		Error error = null;
		
		StockoutService service = restAdapter.create(StockoutService.class);		
		
		try {
			service.createStockout(p);
		} catch(Exception e) {
			System.out.println(e);
			error = new Error("400 - Bad Request. Stockout Malformed.");
		}
		
		return error;
 	}
	
	public PaymentMethod valueOfPayment(String s){
		PaymentMethod result = null;
		switch (s) {
		case "coins":
			result = PaymentMethod.COIN;
			break;
		case "credit_cards":
			result = PaymentMethod.CREDIT_CARD;
			break;
		case "paypal":
			result = PaymentMethod.PAYPAL;
			break;
		default:
			break;
		}
		
		return result;
	}

	public ProblemTypes valueOfProblem(String s) {
		ProblemTypes result = null;
		switch (s) {
		case "fail_whale":
			result = ProblemTypes.FAIL;
			break;
		case "out_of_order":
			result = ProblemTypes.OUTOFORDER;
			break;
		default:
			break;
		}

		return result;
	}
	
	public StockoutTypes valueOfStockout(String s) {
		StockoutTypes result = null;
		switch (s) {
		case "out_of_stock":
			result = StockoutTypes.OUTOFSTOCK;
			break;
		case "almost_out":
			result = StockoutTypes.ALMOSTOUT;
			break;
		default:
			break;
		}

		return result;
	}

	
	// Rest Machine Management
	
	/**
	 * Gets a machine associated with that machine id (from Rifffish)
	 * @param machineId
	 * @return Machine
	 */
	public Machine getMachine(int machineId) {
		Machine machine = null;
		MachinesService service = restAdapter.create(MachinesService.class);		
		
		try {
			machine = service.getMachine(machineId);
		} catch(Exception e) {}
		
		return machine;
	}
	
	/**
	 * Update a the past Machine (updates the machine using it's own id)
	 * @param machine
	 * @return Machine (newly updated - ID won't change)
	 */
	public Machine updateMachine(Machine machine) {
		Machine machineResponse = null;
		MachinesService service = restAdapter.create(MachinesService.class);		
		
		try {
			machineResponse = service.updateMachine(machine.getId(), machine);
		} catch(Exception e) {}
		
		return machineResponse;
	}
	
	/**
	 * Creates a New Machine (see required params)
	 * @param machine
	 * @return Machine (the newly created machine), null if machine has error
	 */
	public Machine createMachine(Machine machine) {
		Machine machineResponse = null;
		MachinesService service = restAdapter.create(MachinesService.class);		
		
		try {
			machineResponse = service.createMachine(machine);
		} catch(Exception e) {}
		
		return machineResponse;
	}
	
	/**
	 * Deletes the machine with that ID
	 * @param machineId
	 * @return Error, null if machine is deleted successfully
	 */
	public Error deleteMachine(int machineId) {
		Error error = null;
		MachinesService service = restAdapter.create(MachinesService.class);		
		
		try {
			error = service.deleteMachine(machineId);
			
			if (!error.foundError()) // Error System Print Legacy.
				error = null;
		} catch(Exception e) {}
		
		return error;
	}
	
	// Rest Product Management 
	
	public Product getProduct(int productId) {
		Product product = null;
		ProductsService service = restAdapter.create(ProductsService.class);		
		
		try {
			product = service.getProduct(productId);
		} catch(Exception e) {}
		
		return product;
	}
	
	public Product updateProduct(Product product) {
		Product productResponse = null;
		ProductsService service = restAdapter.create(ProductsService.class);		
		
		try {
			productResponse = service.updateProduct(product.getId(), product);
		} catch(Exception e) {}	
		
		return productResponse;
	}
	
	public Product createProduct(Product product) {
		Product productResponse = null;
		ProductsService service = restAdapter.create(ProductsService.class);		
		
		try {
			productResponse = service.createProduct(product.getMachineId(), product);
		} catch(Exception e) {}
		
		return productResponse;
	}
	
	public Error deleteProduct(int productId) {
		Error error = null;
		ProductsService service = restAdapter.create(ProductsService.class);		
		
		try {
			error = service.deleteProduct(productId);
			
			if (!error.foundError()) // Error System Print Legacy.
				error = null;
		} catch(Exception e) {}
		return error;
	}
	
}
