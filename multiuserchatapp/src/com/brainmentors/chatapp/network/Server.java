package com.brainmentors.chatapp.network;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.brainmentors.chatapp.utils.ConfigReader;

public class Server {
	ServerSocket serverSocket ;
	ArrayList<ServerWorker> workers = new ArrayList<>(); //Contains all the client Sockets
	public Server() throws IOException {
		int PORT = Integer.parseInt(ConfigReader.getValue("PORTNO"));
		serverSocket = new ServerSocket(PORT);
		System.out.println("Server Start and Waiting for the clients to Join...");
		handleClientRequest();
	}
	//Multiple Client HandShaking
	public void handleClientRequest() throws IOException {
		while(true) {
			Socket clientSocket = serverSocket.accept(); //HnadShaking
			
			//Per Client Per Thread
			ServerWorker serverWorker = new ServerWorker(clientSocket, this); //Creating a new Worker/Thread
			workers.add(serverWorker);
			serverWorker.start();
			}
	}
	
	/* Single Client */
	/*
	public Server() throws IOException {
		int PORT = Integer.parseInt(ConfigReader.getValue("PORTNO"));
		serverSocket = new ServerSocket(PORT);
		System.out.println("Server Started and waiting for the Client Connection...");
		Socket socket = serverSocket.accept(); //handShaking
		System.out.println("Client Joins the Server");
		InputStream in = socket.getInputStream(); //read bytes from Network
		byte arr[] = in.readAllBytes();
		String str = new String(arr); //Bytes convert into String
		System.out.println("Message Rec From the Client"+str);
		in.close();
		socket.close();
	}*/
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Server server = new Server();

	}

}
