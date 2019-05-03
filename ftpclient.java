package ftp;

import java.io.*;
import java.net.*;
import java.util.*;

class ftpclient
{
	static Scanner in = new Scanner(System.in);

	public static void main(String[] args) throws Exception
	{
		String option;
		Socket s=new Socket("localhost",4000);
		System.out.println("MENU");
		System.out.println("1.SEND");
		System.out.println("2.RECEIVE");
		ftpclient ftp=new ftpclient();
		while(true)
		{
			option=in.nextLine();
			if(option.equals("1")){
				System.out.println("SEND Command Received..");
				ftp.sendfile(s);
			}
			
			else if(option.equals("2")){
				System.out.println("RECEIVE Command Received..");
				ftp.receivefile(s);
			}
			
		}
	}
	
	public void sendfile(Socket s) throws Exception
	{
		Socket ssock=s;		
		DataInputStream cin=new DataInputStream(ssock.getInputStream());
		DataOutputStream cout=new DataOutputStream(ssock.getOutputStream());
		
		cout.writeUTF("recieve");
	
		String filename=in.nextLine();
		System.out.println("Reading File "+filename);
		cout.writeUTF(filename);
		File f=new File(filename);
		FileInputStream fin=new FileInputStream(f);
		int ch;
		do
		{
			ch=fin.read();
			cout.writeUTF(String.valueOf(ch));
			System.out.println(ch);
		}while(ch!=-1);
		fin.close();
		System.out.println("File Sent");
	}	
	
	public void receivefile(Socket s) throws Exception
	{
		Socket ssock=s;
		DataInputStream cin=new DataInputStream(ssock.getInputStream());
		DataOutputStream cout=new DataOutputStream(ssock.getOutputStream());
		
		cout.writeUTF("send");		

		
		String filename=in.nextLine();
		cout.writeUTF(filename);
		System.out.println("Receiving File "+filename);
		File f=new File(filename);
		FileOutputStream fout=new FileOutputStream(f);
		int ch;
		do
		{
			ch=Integer.parseInt(cin.readUTF());
			if(ch!=-1) fout.write(ch);
		}while(ch!=-1);
		System.out.println("Received File...");
		fout.close();
	}
}  
