package httpServer;

import httpRequest.HttpRequest;

import java.io.InputStream;
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
		private Socket socket;
		private OutputStream os;

		String inputMethod = null;
		String request = null;

		private SocketProcessor(Socket s) throws Throwable {
			this.socket = s;
			this.os = s.getOutputStream();
		}

		public void run() {
			try {
				HttpRequest httpRequest = new HttpRequest();

				String code = httpRequest.readRequest(socket);
				writeResponse("server return code " + code + "\r\n", code);
			} catch (Throwable t) {
				/* do nothing */
			} finally {
				try {
					socket.close();
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

	}
}
