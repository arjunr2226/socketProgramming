package com.soc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class socServer {

	public static void main(String[] args) {
		try 
		{
			System.out.println("Server is Waiting!");
			ServerSocket ss = new ServerSocket(9908);
			
			Socket soc = ss.accept();
			System.out.println("Connection Established!");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(soc.getInputStream()));
			String str = br.readLine();
			PrintWriter out = new PrintWriter(soc.getOutputStream(), true);
			out.println("Server: " + str);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

	}

}
