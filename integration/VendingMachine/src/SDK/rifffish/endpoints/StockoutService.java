package SDK.rifffish.endpoints;


import retrofit.http.Body;
import retrofit.http.POST;
import SDK.rifffish.Stockout;

public interface StockoutService {	
	@POST("/log/stockouts")
	Stockout createStockout(@Body Stockout stockout);
}
