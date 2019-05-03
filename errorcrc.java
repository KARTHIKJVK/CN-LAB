import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class crc_gen
{
    static int data_bits, divisor_bits, tot_length;

    public static void main(String args[]) throws IOException
    {
        ServerSocket ss=new ServerSocket(5000);
        System.out.println("Waiting");
        Socket s=ss.accept();
        System.out.println(" Connected to client having"+s.getRemoteSocketAddress());
        DataInputStream din=new DataInputStream(s.getInputStream());
        DataOutputStream dout=new DataOutputStream(s.getOutputStream());
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int[] data;
        int[] div;
        int[] divisor;
        int[] rem;
        int[] crc;

        System.out.println("Enter number of data bits : ");
        data_bits=Integer.parseInt(br.readLine());
        data=new int[data_bits];

        System.out.println("Enter data bits : ");
        for(int i=0; i<data_bits; i++) {
            data[i] = Integer.parseInt(br.readLine());
        }
        System.out.println("Enter number of bits in divisor : ");
        divisor_bits=Integer.parseInt(br.readLine());

        divisor=new int[divisor_bits];

        System.out.println("Enter Divisor bits : ");
        for(int i=0; i<divisor_bits; i++)
            divisor[i]=Integer.parseInt(br.readLine());


        tot_length=data_bits+divisor_bits-1;

        div=new int[tot_length];
        rem=new int[tot_length];
        crc=new int[tot_length];
        /*------------------ CRC GENERATION-----------------------*/
        for(int i=0;i<data.length;i++)
            div[i]=data[i];

        System.out.print("Dividend (after appending 0's) are : ");
        for(int i=0; i< div.length; i++)
            System.out.print(div[i]);
        System.out.println();

        for(int j=0; j<div.length; j++){
            rem[j] = div[j];
        }

        rem=divide(div, divisor, rem);

        for(int i=0;i<div.length;i++)           //append dividend and ramainder
        {
            crc[i]=(div[i]^rem[i]);
        }

        System.out.println();
        dout.writeInt(tot_length);
        System.out.println("CRC code : ");
        for(int i=0;i<crc.length;i++) {
            System.out.print(crc[i]);
        dout.writeInt(crc[i]);
        }
        dout.writeInt(divisor_bits);
        for (int i=0;i<divisor_bits;i++)
            dout.writeInt(divisor[i]);
        System.out.println("Data sent to receiver");

    }

    static int[] divide(int div[],int divisor[], int rem[])
    {
        int cur=0;
        while(true)
        {
            for(int i=0;i<divisor.length;i++)
                rem[cur+i]=(rem[cur+i]^divisor[i]);

            while(rem[cur]==0 && cur!=rem.length-1)
                cur++;

            if((rem.length-cur)<divisor.length)
                break;
        }
        return rem;
    }
}
