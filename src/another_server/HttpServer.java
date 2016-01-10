package another_server;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.MessageFormat;

import lesson1.HashTable;

public class HttpServer {

	private static HashTable<String, String> hashTable = new HashTable<String, String>();

	private static final String HEAD_METHOD_RESPONSE = "HTTP/1.1 {0} \r\n";
	private static final String HEAD_CONTENT_LENGTH = "Content-Type: text/html\r\n";
	private static final String HEAD_CONTENT_TYPE = "Content-Length: {0} \r\n";
	private static final String HEAD_CONNECTAION_CLOSE = "Connection: close\r\n\r\n";

	public static void main(String[] args) throws Throwable {
		ServerSocket ss = new ServerSocket(8080);
		while (true) {
			Socket s = ss.accept();
			new Thread(new SocketProcessor(s)).start();
		}
	}

	private static class SocketProcessor implements Runnable {

		public static final String RESP_403 = "403 Forbidden";
		public static final String RESP_404 = "404 Not Found";
		public static final String RESP_503 = "503 Bad Request";
		public static final String RESP_200 = "200 OK";
		public static final String POST = "POST";
		public static final String GET = "GET";
		private Socket s;
		private InputStream is;
		private OutputStream os;

		String inputMethod = null;
		String request = null;

		private SocketProcessor(Socket s) throws Throwable {
			this.s = s;
			this.is = s.getInputStream();
			this.os = s.getOutputStream();
		}

		public void run() {
			try {
				String header[] = readInput().split("\n");
				inputMethod = header[0].split(" ")[0];
				request = header[0].split(" ")[1];
				if (!inputMethod.equals(GET) && !inputMethod.equals(POST)) {
					writeResponse("YOBA, eto ti?", RESP_403);
				} else if (inputMethod.equals(POST)) {
					String body = "";
					if (header.length < 7) {
						writeResponse("No body in request", RESP_503);
					} else {
						for (int i = 7; i < header.length; i++) {
							body += header[i];
						}
						System.out.println(body);
						hashTable.addEntry(request, body);
					}
				} else if (inputMethod.equals(GET)) {
					HashTable.Entry e = hashTable.getEntry(request);
					if (null != e) {
						writeResponse(e.getValue().toString(), RESP_200);
					} else {
						writeResponse(RESP_404, RESP_404);
					}
				}
			} catch (Throwable t) {
				/* do nothing */
			} finally {
				try {
					s.close();
				} catch (Throwable t) {
					/* do nothing */
				}
			}
		}

		private void writeResponse(String content, String code)
				throws Throwable {
			StringBuffer response = new StringBuffer();
			response.append(MessageFormat.format(HEAD_METHOD_RESPONSE, code));
			response.append(HEAD_CONTENT_LENGTH);
			response.append(MessageFormat.format(HEAD_CONTENT_TYPE,
					content.length()));
			response.append(HEAD_CONNECTAION_CLOSE);
			String result = response + content;
			os.write(result.getBytes());
			os.flush();
			os.close();
		}

		private String readInput() throws Throwable {
			BufferedInputStream bis = new BufferedInputStream(is);
			InputStreamReader isr = new InputStreamReader(bis);
			StringBuffer process = new StringBuffer();
			while (true) {
				char[] cbuf = new char[1024];
				isr.read(cbuf);
				for (char c : cbuf) {
					process.append(c);
				}
				System.out.println(process);

				break;
			}
			return process.toString();
		}
	}
}
