package com.chatSystem;

import java.net.Socket;
import java.io.*;
import java.util.Scanner;

public class socketClient 
{
	private String username;
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;
	private Socket socket;
	
	public socketClient(Socket socket, String username)
	{
		try {
			this.socket = socket;
			this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			this.username = bufferedReader.readLine();
		} catch(IOException e) {
			closeEverything(socket, bufferedReader, bufferedWriter);
		}
	}
	
	public void sendMessage()
	{
		try {
			bufferedWriter.write(username);
			bufferedWriter.newLine();
			bufferedWriter.flush();
			
			Scanner scanner = new Scanner(System.in);
			while(socket.isConnected())
			{
				String messageToSend = scanner.nextLine();
				
				bufferedWriter.write(messageToSend);
				bufferedWriter.newLine();
				bufferedWriter.flush();
			}
		} catch(IOException e) {
			closeEverything(socket, bufferedReader, bufferedWriter);
		}
	}
	
	public void listenForMessage()
	{
		new Thread(new Runnable() {

			@Override
			public void run() {
				String messageFromClient;
				
				try {
					messageFromClient = bufferedReader.readLine();
					System.out.println(messageFromClient);
				}catch(IOException e) {
					closeEverything(socket, bufferedReader, bufferedWriter);
				}
				
			}
			
		}).start();
	}
	
	public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter)
	{
		try {
			if(socket != null)
					socket.close();
			if(bufferedReader != null)
					bufferedReader.close();
			if(bufferedWriter != null)
					bufferedWriter.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)throws IOException
	{
		Scanner sc = new Scanner(System.in);
		Socket socket = new Socket("localhost", 9908);
		System.out.print("ENTER YOUR USERNAME: ");
		String username = sc.nextLine();
		socketClient client = new socketClient(socket, username);
		
		client.sendMessage();
		client.listenForMessage();
		sc.close();
	}
}