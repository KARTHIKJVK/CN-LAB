import java.io.*;  
import java.net.*;  
public class Hamming_receiver{  
public static void main(String[] args) {  
try{      
//Socket s=new Socket("192.168.43.251",6666);  
	Socket s=new Socket("localhost",6666); 
	System.out.println("Connected........");
	DataInputStream dis=new DataInputStream(s.getInputStream());  


	int[][] data=new int[40][13];
	int len=Integer.parseInt((String)dis.readUTF());
	int r=Integer.parseInt((String)dis.readUTF());
	int[] res=new int[8];
	int p=0,i,j,one_count=0,k,q=0,temp;
	
	data[0][7]=0;
	
	for(i=0;i<len;i++)
	{
		for(j=1;j<=8+r;j++)
		{
			String  str=(String)dis.readUTF();  
			data[i][j]=Integer.parseInt(str);
			System.out.print(data[i][j]);
		}
		System.out.println();
	}
	k=r;
	for(p=0;p<len;p++)
	{
		for(i=1;i<Math.pow(2,r);i=i*2)
		{
			for(j=i;j<=8+r;j++)
			{
				if((i & j)!=0)
				{
					if(data[p][j]==1)
						one_count++;
				}			
			}
			if(one_count%2 == 0)
				res[k--]=0;
			else
				res[k--]=1;
			one_count=0;
		}
		temp=0;
		for(i=0;i<r;i++)
		{
			temp=temp+((int)Math.pow(2,r-i)*res[i]);
			System.out.println(res[3-i]);
		}
		if(temp==0)
		{
		}
			//System.out.println("Successfully transmited");
		else
		{
			System.out.println("error occurs at position : "+temp);
			q=1;
		}
	}
	if(q==0)
		System.out.println("Successfully transmited");
	s.close();
	}
	catch(Exception e){System.out.println(e);}  
	}  
}  
