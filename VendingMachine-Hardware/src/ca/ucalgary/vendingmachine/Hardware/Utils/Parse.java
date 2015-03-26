package ca.ucalgary.vendingmachine.Hardware.Utils;

/**
 * Parse
 * 
 * @synopsis
 * 		Library utilities related to parsing varying inputs for varying purposes
 * 
 * @author 	wwright@ucalgary.ca
 * 
 */

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parse {
	
	private Parse() {
	}

	static class URL {

		private String protocol = "";
		private String hostname = "";
		private String fullpathname = "";
		private String pathname = "";
		private String filename = "";

		private int port = 80;

		/**
		 * URL
		 * 
		 * @synopsis
		 * 		Constructor with single argument <String> parameter, parses String, populating 
		 * 		static URL fields with:
		 * 
		 * 				private String protocol 	(ie: http, ssh, ftp)
		 * 				private String hostname 	(ie: ucalgary.ca)
		 * 				private String fullpathname (ie: http://ucalgary.ca/home/students/welcome.html)
		 * 				private String pathname 	(ie: home/students/welcome.html)
		 * 				private String filename 	(ie: welcome.html)
		 * 				private int port			(ie: 8888, default: 80)
		 * 
		 * @param		aUrl <String>
		 * @throws		IllegalStateException
		 * @throws		NumberFormatException
		 * 
		 * @author		wwright@ucalgary.ca
		 */
		public URL(String aUrl) throws IllegalStateException, NumberFormatException {

			// TODO: improve the following pattern
			Pattern tPattern = Pattern.compile("(https?://)([^:^/]*):?(\\d*)?(.*)?");
			try {
				Matcher tMatch = tPattern.matcher(aUrl);
				tMatch.find();

				this.protocol = tMatch.group(1);
				this.hostname = tMatch.group(2);
				this.fullpathname = tMatch.group(4);
				this.pathname = this.fullpathname.substring(0, this.fullpathname.lastIndexOf('/') + 1);
				this.filename = new File(this.fullpathname).getName();	
				this.port = Integer.parseInt(tMatch.group(3));

			} catch (IllegalStateException e) {
				System.err.println("Invalid URL Specified.");
			} catch (NumberFormatException e) {
				this.port = 80;
			}
		} /* Constructor: public URL(String aURL) */

		public String getProtocol() {
			return this.protocol;
		}

		public String getHostname() {
			return this.hostname;
		}

		public String getFullPathname() {
			return this.fullpathname;
		}

		public String getPathname() {
			return this.pathname;
		}

		public String getFilename() {
			return this.filename;
		}

		public int getPort() {
			return this.port;
		}
	} /* static class URL */
	
	
	
	// ---------------------------------------------------------------------------------------
	// 								HTTP Parsing Utilities 
	// ---------------------------------------------------------------------------------------
	static class HTTP {
		
		public static Map<String, String> Header(String aString) throws NumberFormatException {
			Map<String, String> headers = new HashMap<String, String>();
			String[] pairs = aString.split("\n");
			if(pairs.length > 0)
				headers.put("HTTPStatus",  pairs[0]);
			for (int i=0; i < pairs.length; i++)  {
				String[] keyValue = pairs[i].toString().split(":", 2);
				// Assume the first response from a valid web server is a status code
				if(keyValue.length == 2)
					headers.put(keyValue[0], keyValue[1]);
			}
			return headers;
		}
	}
} /* public class Parse */