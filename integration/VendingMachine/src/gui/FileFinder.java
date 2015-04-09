package gui;

import java.io.File;
import java.util.ArrayList;

public class FileFinder {

	private File folder = null;
	private File[] listOfFiles = null;
	private final String filetype = ".txt";
	private ArrayList<String> fileArray = new ArrayList<String> ();
	
	public static void main(String []args){
		FileFinder f = new FileFinder();
	}
	
	
	public FileFinder(){
		folder = new File("configfiles");
		listOfFiles = folder.listFiles();
	    for (int i = 0; i < listOfFiles.length; i++) {
	      if (listOfFiles[i].isFile()) {
	    	  if(listOfFiles[i].getName().contains(filetype)){
	    		  fileArray.add(listOfFiles[i].getName());
//	    		  System.out.println("File " + listOfFiles[i].getName());
	    	  }
	      }
	  }
	}
	
	
	public String getElementInFileArray(int index){
		return fileArray.get(index);
	}
	
	public int getFileArraySize(){
		return fileArray.size();
	}
	
}
