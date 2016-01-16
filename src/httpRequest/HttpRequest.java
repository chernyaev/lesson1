package httpRequest;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.CharBuffer;

public class HttpRequest {

	private Socket socket;
	private InputStream is;
	private OutputStream os;

	private String AVAILABLE_METHODS = "OPTIONS,GET,HEAD,POST,PUT,DELETE,TRACE";

	private int DEFAULT_BUFFER_SIZE = 1;

	public HttpRequest() {

	}

	public String readRequest(Socket socket) {

		try {
			this.socket = socket;
			this.is = socket.getInputStream();
			this.os = socket.getOutputStream();
			return readInput();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	private String readInput() throws IOException {
		BufferedInputStream bis = new BufferedInputStream(is);
		InputStreamReader isr = new InputStreamReader(bis);
		StringBuffer process = new StringBuffer();
		CharBuffer c = CharBuffer.allocate(DEFAULT_BUFFER_SIZE);
		while (true) {
			isr.read(c);
			char ch = c.array()[0];
			process.append(ch);
			c.clear();
			if (ch == '\n') {
				break;
			}
		}

		String[] startLine = process.toString().split(" ");
		if (startLine.length != 3) {
			return "400";
		} else {
			String method = startLine[0];
			String requestURI = startLine[1];
			String httpVersion = startLine[2];
			if (method.equals("POST")) {

			} else if (method.equals("GET")) {

			} else {
				if (AVAILABLE_METHODS.contains(method)) {
					return "405";
				} else {
					return "501";
				}
			}
			if (requestURI.length() > 128) {
				return "414";
			}
		}

		return "200";
	}
}
