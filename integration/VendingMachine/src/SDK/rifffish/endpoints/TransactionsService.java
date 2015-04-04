package SDK.rifffish.endpoints;


import retrofit.http.Body;
import retrofit.http.POST;
import SDK.rifffish.Transaction;

public interface TransactionsService {	
	@POST("/log/transactions")
	Transaction createTransaction(@Body Transaction transaction);
}
