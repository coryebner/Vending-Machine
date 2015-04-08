package SDK.localLog.io;

import SDK.rifffish.Rifffish;

public class LocalLogReader extends Thread{
	private LocalLog log;
	private Rifffish r;

	public LocalLogReader(Rifffish r, LocalLog log) {
		this.log = log;
		this.r = r;
	}
	
	public void run(){
		log.pushLocalLog(r);
	}
}