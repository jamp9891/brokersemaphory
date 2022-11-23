package com.ud.socket;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

@Setter
@Getter
public class Client implements Runnable{

	private Socket client;

	private String jsonObject;
	public Client(int port) throws IOException {
		client = new Socket("127.0.0.1", port);
	}

	@Override


	public void run() {
		try {

			InputStream inFromServer = client.getInputStream();
			DataInputStream input = new DataInputStream(inFromServer);

			OutputStream outToServer = client.getOutputStream();
			DataOutputStream output = new DataOutputStream(outToServer);
			Scanner userEntry = new Scanner(System.in);

			System.out.print("JSON : "+this.getJsonObject());
			output.writeUTF(this.getJsonObject());

			System.out.println(input.readUTF());

			int service;

			service =1;
			//System.out.print("Salida : ");
			output.writeInt(service);

			/*do {
				//System.out.print("\nSeleccione una Opción: ");


			} while (service != 0);*/


		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (NoSuchElementException ne){   //This exception may be raised when the server closes connection
			System.out.println("Conexión cerrada");
		}
		finally {
			try {
				System.out.println("\n* Cerrando conexión *");
				client.close();
			} catch (IOException ioEx) {
				System.out.println("--->Conexión no disponible<---");
				System.exit(1);
			}
		}
	}
}
