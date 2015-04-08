package SDK.localLog.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

import SDK.rifffish.Problem;
import SDK.rifffish.Rifffish;
import SDK.rifffish.Stockout;
import SDK.rifffish.Transaction;

public class LocalLog{
	private boolean available = false;
	
	/**
	 * Prints a transaction to a local log file
	 * 
	 * @param t a transaction object
	 */
	public synchronized void printToLocalLog(Transaction t){
		 while (available == true) {
	         try {
	            wait();
	         }
	         catch (InterruptedException e) { 
	         } 
	      }
		
		  try(    FileWriter fw = new FileWriter("log.txt", true);
		          BufferedWriter bw = new BufferedWriter(fw);
		          PrintWriter out = new PrintWriter(bw)){

			  out.println("Transaction," + t.getId() + "," + t.getProductId() + "," + t.getQuantity() + "," + t.getPaymentMethod() + "," + t.getStatus() + "," + t.getTimestamp() + "," + t.getError());

		  }  
		  catch( IOException e ){
		      System.out.println("Failed to print to file");// File writing/opening failed at some stage.
		  }
		  
		  available = true;
		  notifyAll();
	}
	
	/**
	 * Prints a Stockout to a local log file
	 * 
	 * @param t a Stockout object
	 */
	 public synchronized void printToLocalLog(Stockout t){
		 while (available == true) {
	         try {
	            wait();
	         }
	         catch (InterruptedException e) { 
	         } 
	      }
		 
		  try(    FileWriter fw = new FileWriter("log.txt", true);
		          BufferedWriter bw = new BufferedWriter(fw);
		          PrintWriter out = new PrintWriter(bw)){

			  out.println("Stockout," + t.getId() + "," + t.getDescription() + "," + t.getTimestamp());

		  }  
		  catch( IOException e ){
		      // File writing/opening failed at some stage.
		  }
		  
		  available = true;
		  notifyAll();
	}
	 
		/**
		 * Prints a Problem to a local log file
		 * 
		 * @param t a Problem object
		 */
		 public synchronized void printToLocalLog(Problem t){
			 while (available == true) {
		         try {
		            wait();
		         }
		         catch (InterruptedException e) { 
		         } 
		      }
			 
			  try(    FileWriter fw = new FileWriter("log.txt", true);
			          BufferedWriter bw = new BufferedWriter(fw);
			          PrintWriter out = new PrintWriter(bw)){

				  out.println("Problem," + t.getId() + "," + t.getDescription() + "," + t.getTimestamp());

			  }  
			  catch( IOException e ){
			      // File writing/opening failed at some stage.
			  }
			  
			  available = true;
			  notifyAll();
		}
	 
	/**
	 *  Sends any local logs to the server. NOT SAFE.
	 *  
	 * @param r Riffish object
	 */
	public synchronized void pushLocalLog(Rifffish r){
		Error error = null;
		
		while (available == false) {
	         try {
	            wait();
	         }
	         catch (InterruptedException e) { 
	         } 
	      }
		
		try {
			File theFile = new File("log.txt");
			RandomAccessFile file =  new RandomAccessFile(theFile, "rw");
			    String line;
			    String[] split;	            
			    while ((line = file.readLine()) != null && error == null) {
			    	
			       // process the line.
			    	split = line.split(",");
			    	if(split[0].equals("Transaction")){
			    		Transaction t = new Transaction(Integer.parseInt(split[3]), r.valueOfPayment(split[4]), Boolean.valueOf(split[5]));
			    		t.id = Integer.parseInt(split[2]);
			    		t.timestamp = split[6];
			    		r.log(t);
			    	}else if(split[0].equals("Problem")){
			    		Problem p = new Problem(r.valueOfProblem(split[2]));
			    		p.timestamp = split[3];
			    		p.machine_id = Integer.parseInt(split[1]);
			    		r.log(p);
			    	}else if(split[0].equals("Stockout")){
			    		Stockout p = new Stockout(Integer.parseInt(split[1]), r.valueOfStockout(split[2]));
			    		p.timestamp = split[3];
			    		r.log(p);
			    	}			    	
			    	
			    }
			    
			    if(error == null){
			    	File temporaryFileName = new File("temporaryLog.txt");
			    	RandomAccessFile temporaryFile= new RandomAccessFile(temporaryFileName , "rw");

			    	temporaryFile.close();
			    	               
			    	theFile.delete();
			    	temporaryFileName.renameTo(theFile);
			    }
			    file.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		available = false;
		notifyAll();
	}
	
	/**
	 * Returns the number of lines in the log file
	 * 
	 * @return the number of lines in the file
	 */
	public synchronized int getNumLines(){
		int result = 0;
		
		try {
			LineNumberReader  lnr = new LineNumberReader(new FileReader(new File("log.txt")));
			lnr.skip(Long.MAX_VALUE);
			result = lnr.getLineNumber();
			lnr.close();
		} catch (FileNotFoundException e) {
			result=0;
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}