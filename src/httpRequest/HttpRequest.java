package httpRequest;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class HttpRequest {

	private Socket socket;
	private InputStream is;
	private OutputStream os;

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
	
	private String readInput() throws IOException{
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
