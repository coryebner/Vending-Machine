package ca.ucalgary.vendingmachine.Hardware.Utils;

/**
 * FileIO
 * 
 * @synopsis
 * 		Library utilities related to file input and output operations
 * 
 * @author 	wwright@ucalgary.ca
 * 
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class FileIO {
	
	private FileIO() {
	}

	/**
	 * writeStringToFile
	 * 
	 * @synopsis
	 * 		Writes 'netFileContentString' to 'aFile' resource.
	 * 
	 * @param newFileContentString
	 * @param aFile
	 * 
	 * @author wwright@ucalgary.ca
	 */
	public static void writeStringToFile(String newFileContentString, File aFile) {
		FileWriter fw;
		try {
			fw = new FileWriter(aFile.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(newFileContentString);
			bw.close();
		} catch (IOException e) {
			System.out.println("HTTrack.java: Error writing payload to file.");
		}
	}
	
	/**
	 * readFileToString
	 * 
	 * @synopsis
	 * 		Reads 'afile' resource contents into String as return value
	 * 
	 * @param 	aFile
	 * @return 	String
	 * 
	 * @author 	wwright@ucalgary.ca
	 */
	public static String readFileToString(File aFile) {
		String contents="";
		
	 try(BufferedReader br = new BufferedReader(new FileReader(aFile))) {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append(System.lineSeparator());
	            line = br.readLine();
	        }
	        contents = sb.toString();
	    } catch (IOException e) {
			e.printStackTrace();
		}
	return contents;
	}
	

	public static void writeByteArrayToFile(byte[] aByteArray, String fileName, Socket aSocket) throws IOException {
		
		// Process the first few bytes of the payload to determine the encoding to use
		// Magic numbers for gif -> Hex: 47 49 46 38
		// TODO: Implement more modern approach to magic number filtering (can be done a variety of ways)
		
		boolean writeBytes = false;
		FileOutputStream fos;

		String gifMagicNumbers = "0x47, 0x49, 0x46, 0x38";
		byte[] gifMagicWords = "GIF89".getBytes();

		String[] hexValues = gifMagicNumbers.split(",");
		byte hexByte;
		int i;
		
		for(i = 0; i < hexValues.length; i++) {
			hexByte = Integer.valueOf(hexValues[i].substring(3).trim(), 16).byteValue();
			if(aByteArray[i] == hexByte || aByteArray[i] == gifMagicWords[i])
				continue;
			else
				break;
		}
		if(i == hexValues.length || i == gifMagicWords.length)
			writeBytes = true;

		String dataToWrite;

		if(writeBytes == true)  {		
			File file = new File(fileName);
			// TODO: remove / modify mkdir operations 
			file.getParentFile().mkdirs();

			try {
				fos = new FileOutputStream(file);
				fos.write(aByteArray);
				fos.close();
			} catch (IOException e) {
				System.out.println("FileIO: WriteByteArrayToFile failed to append payload.");
			}
		}
		else {
			try {
				dataToWrite = new String(aByteArray, 0, aByteArray.length, "US-ASCII");

				File file = new File(fileName);
				// TODO: remove / modify mkdir operations 
				file.getParentFile().mkdirs();
				writeStringToFile(dataToWrite, file);
			} catch (UnsupportedEncodingException e) {
				System.out.println("FileIO: WriteByteArrayToFile failed to append payload.");
			}	
		}
	}
	
}
