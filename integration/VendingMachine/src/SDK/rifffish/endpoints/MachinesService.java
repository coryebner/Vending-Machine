package SDK.rifffish.endpoints;


import retrofit.http.*;
import SDK.rifffish.Machine;
import SDK.rifffish.Error;


public interface MachinesService {	
	
	
	// REST (is history)
	
	// Get 
	@GET("/machines/{machine_id}")
	Machine getMachine(@Path("machine_id") int machine_id);
	
	// Create
	@POST("/machines")
	Machine createMachine(@Body Machine machine);
	
	// Update
	@PUT("/machines/{machine_id}")
	Machine updateMachine(@Path("machine_id") int machine_id, @Body Machine machine);
	
	// Delete
	@DELETE("/machines/{machine_id}")
	Error deleteMachine(@Path("machine_id") int machine_id);
}
