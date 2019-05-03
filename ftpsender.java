package ftp;

import java.io.*;
import java.net.*;

public class ftpserver {
	
	
	public static void main(String []args) throws Exception {
		
		ServerSocket servsock = new ServerSocket(4000);
		Socket s = servsock.accept();
		DataInputStream cin=new DataInputStream(s.getInputStream());
		DataOutputStream cout=new DataOutputStream(s.getOutputStream());
		ftpserver ftp = new ftpserver();
		while(true)
		{
			
			String option=cin.readUTF();
			if(option.equals("send")){
				System.out.println("SEND Command Received..");
				ftp.sendfile(s);
			}
			else if(option.equals("recieve")){
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
		String filename=cin.readUTF();
		System.out.println("Reading File "+filename);
		File f=new File(filename);
		FileInputStream fin=new FileInputStream(f);
		int ch;
		do
		{
			ch=fin.read();
			cout.writeUTF(Integer.toString(ch));
		}while(ch!=-1);
		fin.close();
		System.out.println("File Sent");
		
	}

	public void receivefile(Socket s) throws Exception
	{
		Socket ssock=s;
		DataInputStream cin=new DataInputStream(ssock.getInputStream());
		DataOutputStream cout=new DataOutputStream(ssock.getOutputStream());

		String filename=cin.readUTF();
		System.out.println("Receiving File "+filename);
		File f=new File(filename);
		FileOutputStream fout=new FileOutputStream(f);
		int ch;
		while((ch=Integer.parseInt(cin.readUTF()))!=-1)
		{
			fout.write(ch);
			System.out.println(ch);
		}
		System.out.println("Received File...");
		fout.close();
		}
	}
