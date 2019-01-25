package client;

import java.io.IOException;
import java.util.ArrayList;

import common.OBLclientIF;
import controllers.DatabaseController;

public class ClientConnection implements OBLclientIF {

	// The default port to connect on.
	final public static int DEFAULT_PORT = 5555;

	// The instance of the client that created this ConsoleChat.
	OBLclient client;

	/*
	 * @param host The host to connect to.
	 * 
	 * @param port The port to connect on.
	 */

	public ClientConnection() {
		this("localhost", DEFAULT_PORT);
		/*
		 * try { client = new ChatClient("localhost", DEFAULT_PORT, this);
		 * System.out.println("connected"); } catch (IOException Exception) {
		 * System.out.println("Error: Can't setup connection!" +
		 * " Terminating client."); System.exit(1); }
		 */
	}

	public ClientConnection(String host, int port) {
		try {
			client = new OBLclient(host, port, this);
		} catch (IOException exception) {
			System.out.println("Error: Can't setup connection!" + " Terminating client.");
			System.exit(1);
		}
	}

	public void executeQuery(ArrayList<String> arr) {
		client.handleMessageFromClientUI(arr);
	}

	public void executeQuery(String str) {
		client.handleMessageFromClientUI(str);
	}

	// **************************************//
	private Object obj;

	public void serverObj(Object obj) {
		System.out.println("> Object received from server.");
		System.out.println((ArrayList<String>) obj);
		this.obj = obj;
	}

	public Object getObject() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.obj;
	}

	public ArrayList<String> getList() {
		return (ArrayList<String>) this.obj;
	}

	/**
	 * This method overrides the method in the ChatIF interface. It displays a
	 * message onto the screen.
	 *
	 * @param message The string to be displayed.
	 */
	public void display(String message) {
		System.out.println("> " + message);
	}

	public void terminate() {
		client.quit();
	}

	public void init() {
		DatabaseController.InitiateClient(this);
	}

}
//End of ConsoleChat class
