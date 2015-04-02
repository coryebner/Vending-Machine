package hardware.Utils;

/**
 * SocketIO
 * 
 * @synopsis
 * 		Library utilities dealing with input/output processing as related to Socket programming
 * 
 * @author		wwright@ucalgary.ca
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.DatagramPacket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import hardware.Utils.Exceptions.HTTPException;

@SuppressWarnings("unused")
public class SocketIO {
	
	private SocketIO() {
	}
	
	// -------------------------------------------------------------
	// 					Datagram packets
	// -------------------------------------------------------------
	private static DatagramPacket createDatagramPacket(byte buf[]) {
		return new DatagramPacket(buf, buf.length);
	}
	
	public static DatagramPacket createDatagramPacket(int bufSize) {
		return createDatagramPacket(new byte[bufSize]);
	}

	private static class UDP {
		
	private interface IUDPSocket<T> {
		    public void setSoTimeout(int msTimeout) throws SocketException;
		    public T receive() throws IOException;
		    public void send(T info) throws IOException;
		    
			class UDPSocket<T> implements IUDPSocket<T> {
				@Override
				public void setSoTimeout(int msTimeout) throws SocketException {			
				}

				@Override
				public T receive() throws IOException {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public void send(T data) throws IOException {
					// TODO Auto-generated method stub
					
				}
				
			}
		} /* IUDPSocket */
	} /* UDP class */


	/**
	 * HTTP
	 * 
	 * @synopsis
	 * 		Library utilities specific to HTTP socket handling (transmission requests, object fetching, etc)
	 * 
	 * NOTE: Anticipated HTTP Header delimiter is <Carriage return + Newline + Carriage return + Newline>, as
	 * 		 outlined by HEADER_DELIM.
	 * 
	 * 
	 * @author wwright@ucalgary.ca
	 *
	 */

	private static class HTTP {
		protected static Parse.URL url;		
		// Specifies anticipated HTTP Protocol - Header delimiter
		private static final String HEADER_DELIM = "\r\n\r\n";

		/**
		 * fetchObject
		 * 
		 * NOTE: Expected encoding of header specific data is US-ASCII,
		 * TODO: Enable easy, on the fly customization of encoding
		 * 
		 * @synopsis
		 * 		Fetch an "object" <generic> via an HTTP Request as specified by REQString <String>
		 * 
		 * @param	REQString				<String> used in transmission request (HTTP)
		 * @throws	UnknownHostException
		 * @throws	IOException
		 */
		public static void fetchObject(String REQString) throws UnknownHostException, IOException {
			Map<String, String> headers = new HashMap<String, String>();
			byte[]  bHeaderDelim = HEADER_DELIM.getBytes();
			boolean headerfound = false;

			try(Socket socket = new Socket(url.getHostname(), url.getPort())) {
				socket.setSoTimeout(60000);

				InputStream rIn = socket.getInputStream();
				rIn = new BufferedInputStream(rIn);

				OutputStream rOut = socket.getOutputStream();
				rOut = new BufferedOutputStream(rOut);

				Writer cOut = new OutputStreamWriter(rOut, "US-ASCII");
				cOut.write(REQString);
				cOut.flush();

				ByteArrayOutputStream buf = new ByteArrayOutputStream();
				int ch = 0;

				while((ch = rIn.read()) != -1) {
					if (ch == bHeaderDelim[0] && headerfound == false) {
						rIn.mark(HEADER_DELIM.length());
						byte []b = new byte[HEADER_DELIM.length()-1];
						if (rIn.read(b) == (HEADER_DELIM.length()-1) && b[0] == '\n' && b[1] == '\r' && b[2] == '\n') {
							buf.write(ch);
							buf.write(b);
							byte rawHeader[] = buf.toByteArray();
							int len = rawHeader.length;	
							if(len > 0) {
								headers = Parse.HTTP.Header(new String(rawHeader, 0, len-4, "US-ASCII"));
								if(headers.get("HTTPStatus").contains("404"))
									throw new HTTPException();
								headerfound = true;
							}
							buf.reset();
							continue;
						}
						else {
							rIn.reset();
						}
					}
					buf.write(ch);
				}
				byte rawPayload[] = buf.toByteArray();
				
				// TODO: Transition socket/io resource safe closing to finally clause or manip try-with-resources
				rIn.close();
				rOut.close();
				cOut.close();
				if(rawPayload.length > 0) {
					FileIO.writeByteArrayToFile(rawPayload, url.getPathname(), socket);
				}
				socket.close();
			} catch( HTTPException | IOException e ) {
				e.printStackTrace();
			}
		}
	}
}
