package another_server;

import java.net.*;
import java.io.*;

public class Server {
	public static void main(String[] ar) {
		int port = 6666;
		try {
			ServerSocket ss = new ServerSocket(port);
			System.out.println("Waiting for a client...");

			Socket socket = ss.accept();
			System.out.println("Got a client");

			InputStream sin = socket.getInputStream();
			OutputStream sout = socket.getOutputStream();

			DataInputStream in = new DataInputStream(sin);
			DataOutputStream out = new DataOutputStream(sout);

			String line = null;

			while (true) {
				line = in.readUTF();
				System.out.println("Client just sent me this line : " + line);
				System.out.println("I'm sending it back...");
				out.writeUTF(line);
				out.flush();
				System.out.println("Waiting for the next line...");
			}

		} catch (Exception x) {
			x.printStackTrace();
		}
	}
}