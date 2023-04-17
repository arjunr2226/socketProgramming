package com.fact;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class socServer {

	public static void main(String[] args) {
		try 
		{
			System.out.println("Server is Waiting...");
			ServerSocket ss = new ServerSocket(9908);
			Socket soc = ss.accept();
			System.out.println("Connection Established!");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(soc.getInputStream()));
			int num = Integer.parseInt(br.readLine());
			
			PrintWriter out = new PrintWriter(soc.getOutputStream(), true);
			
			out.println("Factorial of " +num+ " is: " + getFact(num));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	static int getFact(int num) 
	{
		int f = 1;
		for(int i=1; i<num; i++)
			f *= i;
		return f;
	}

}
