package com.fact;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class socClient {

	public static void main(String[] args) {
		
		try {
			Socket soc = new Socket("localhost", 9908);
			System.out.println("ENTER THE NUMBER: ");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int num = Integer.parseInt(br.readLine());
			
			PrintWriter out = new PrintWriter(soc.getOutputStream(), true);
			out.println(num);
			
			BufferedReader brIn = new BufferedReader(new InputStreamReader(soc.getInputStream()));
			System.out.println(brIn.readLine());
		} 
		catch (UnknownHostException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}

}
