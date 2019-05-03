import java.io.*;
import java.util.*;
import java.net.*;
import java.lang.*;
public class Hamming
{
	public static void main(String args[]) throws IOException
	{
		try{
		ServerSocket ss=new ServerSocket(6666);  
		System.out.println("Waiting......");
		Socket s=ss.accept();//establishes connection   
		System.out.println("Connected! to client ........");
		DataOutputStream dout=new DataOutputStream(s.getOutputStream());
		String sender_data;
		int[][] data=new int[40][13];
		int[] temp_binary=new int[8];
		int k=0,one_count=0,j,p=0;
		System.out.println("Enter the data to be sent.......");
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		sender_data=br.readLine();
		int num,i,len=sender_data.length(),r=1;
		while(true)
		{
			if(Math.pow(2,r)>=8+r+1)
				break;
			else
				r++;
		}
		System.out.println(r);
	// for every charecter we convert and store into 2d array
		for(i=0;i<sender_data.length();i++)
		{
			num=(int)sender_data.charAt(i);
		//finding binary number and storing in temp array
			while(num>1)
			{
				temp_binary[k++]=num%2;
				num=num/2;
			}
			temp_binary[k]=num;
			//for(j=0;j<k;j++)
				//System.out.print(temp_binary[k]);
			System.out.println(k);
			k=1;
			
		//copying binary number into the data 2d array
			for(j=1;j<=8+r;j++)
			{
				if(k!=j)
					data[i][j]=temp_binary[p++];
				else
					k=k*2;
			}
			k=0;
			p=0;
		}
		one_count=0;
		for(p=0;p<len;p++)
		{
			for(i=1;i<Math.pow(2,r);i=i*2)
			{
				for(j=i+1;j<=8+r;j++)
				{
					if((i & j)!=0)
					{
						if(data[p][j]==1)
							one_count++;
					}
					
				}
				if(one_count%2 == 0)
					data[p][i]=0;
				else
					data[p][i]=1;
				one_count=0;
			}
		}
		for(i=0;i<len;i++)
		{
			for(j=1;j<=8+r;j++)
			{
				System.out.print(data[i][j]+"  ");
			}
			System.out.println();
		}
		dout.writeUTF(Integer.toString(sender_data.length()));
		dout.writeUTF(Integer.toString(r));
		for(i=0;i<sender_data.length();i++)
		{
			for(j=1;j<=8+r;j++)
			{
				String str=Integer.toString(data[i][j]);  
				dout.writeUTF(str);
			}
			
		}  
		dout.flush();  
		dout.close();  
		s.close();  
		ss.close();  
		}
		catch(Exception e){System.out.println(e);}  
	}
}
