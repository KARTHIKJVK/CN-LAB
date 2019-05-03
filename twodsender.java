import java.io.*;  
import java.net.*;  
import java.util.*;
public class Twod_sender{  
public static void main(String[] args){  
try{  
	ServerSocket ss=new ServerSocket(6666);  
	System.out.println("Waiting......");
	Socket s=ss.accept();//establishes connection   
	System.out.println("Connected! to client ........");
	DataOutputStream dout=new DataOutputStream(s.getOutputStream());
	int[][] data=new int[40][9];





	String sender_data;
	int[] temp_binary=new int[8];
	int k=0,one_count=0,j;
	System.out.println("Enter the data to be sent.......");
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	sender_data=br.readLine();
	int num,i;
	// for every charecter we convert and store into 2d array
	for(i=0;i<sender_data.length();i++)
	{
		num=(int)sender_data.charAt(i);
		//finding binary number and storing in temp array
		while(num>1)
		{
			temp_binary[k++]=num%2;
			if(temp_binary[k-1]==1)
				one_count++;
			num=num/2;
		}
		temp_binary[k]=num;
		if(num==1)
			one_count++;
		//copying binary number into the data 2d array
		for(j=0;j<8;j++)
		{
			data[i][7-j]=temp_binary[j];
		}
		data[i][8]=one_count%2;
	
	
		
		one_count=0;
		k=0;
	}
	//column parity insertion
	for(i=0;i<9;i++)
	{
		for(j=0;j<sender_data.length();j++)
		{
			if(data[j][i]==1)
				one_count++;
		}
		data[j][i]=one_count%2;
		one_count=0;
	}
	//printing the sending data
	for(i=0;i<=sender_data.length();i++)
	{
		for(j=0;j<9;j++)
			System.out.print(data[i][j]);
		System.out.println();
		
	}
	
	System.out.println("Enter 1 to make changes in the bits and 0 to not make any changes........");
	int input=Integer.parseInt(br.readLine());
	int min=0,max=3;
	Random r = new Random();
	int loop=r.nextInt((max - min) + 1) + min;
	for(i=0;i<=loop;i++)
	{
		min=0;max=7;
		if(input == 1)
		{
			int random_bit=r.nextInt((max - min) + 1) + min;
			min=0;max=sender_data.length()-1;
			int random_frame=r.nextInt((max - min) + 1) + min;
			if(data[random_frame][random_bit]==0)
				data[random_frame][random_bit]=1;
			else
				data[random_frame][random_bit]=0;
			System.out.println("curroption at "+random_frame+" , "+random_bit);
		}
	}



	dout.writeUTF(Integer.toString(sender_data.length()));
	for(i=0;i<=sender_data.length();i++)
	{
		for(j=0;j<9;j++)
		{
			String str=Integer.toString(data[i][j]);  
			dout.writeUTF(str);
		}
			
	}  
	
//int b=(int)dis.readUTF();

//System.out.println("message= "+b);  
dout.flush();  
dout.close();  
s.close();  
ss.close();  
}catch(Exception e){System.out.println(e);}  
}  
}  
