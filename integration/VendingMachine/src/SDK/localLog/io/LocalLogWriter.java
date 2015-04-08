package SDK.localLog.io;

import SDK.rifffish.Problem;
import SDK.rifffish.Stockout;
import SDK.rifffish.Transaction;

public class LocalLogWriter extends Thread{
	private Object toLog;
	private LocalLog log;

	public LocalLogWriter(LocalLog log, Transaction t) {
		this.toLog = t;
		this.log = log;
	}
	
	public LocalLogWriter(LocalLog log, Problem p){
		this.toLog = p;
		this.log = log;
	}
	
	public LocalLogWriter(LocalLog log, Stockout p){
		this.toLog = p;
		this.log = log;
	}
	
	public void run(){
		if(toLog instanceof Transaction)
			log.printToLocalLog((Transaction)toLog);
		else if(toLog instanceof Problem)
			log.printToLocalLog((Problem)toLog);
		else if(toLog instanceof Stockout)
			log.printToLocalLog((Stockout)toLog);
	}
}