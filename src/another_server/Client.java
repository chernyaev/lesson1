package another_server;

import java.net.*;
import java.io.*;

public class Client {
	public static void main(String[] ar) {
		int serverPort = 6666;
		String address = "127.0.0.1";

		try {
			InetAddress ipAddress = InetAddress.getByName(address);
			System.out.println("Connect to  " + address + ":" + serverPort);
			Socket socket = new Socket(ipAddress, serverPort);

			InputStream sin = socket.getInputStream();
			OutputStream sout = socket.getOutputStream();

			DataInputStream in = new DataInputStream(sin);
			DataOutputStream out = new DataOutputStream(sout);

			BufferedReader keyboard = new BufferedReader(new InputStreamReader(
					System.in));
			String line = null;
			System.out.println("Type in something");

			while (true) {
				line = keyboard.readLine();
				System.out.println("Sending this line to the server...");
				out.writeUTF(line);
				out.flush();
				line = in.readUTF();
				System.out.println("Server send this : " + line);
				System.out.println("Enter next line^");
			}
		} catch (Exception x) {
			x.printStackTrace();
		}
	}
}