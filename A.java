package udp;

import java.util.*;
import java.io.*;
import java.net.*;

class ListeningAtA implements Runnable
{
    

    int rand50()
    {

        return Math.abs(new Random().nextInt()) & 1;
    }

    public static void printData(byte buf[])
    {
        System.out.println("Data packet is: "+buf[0]+" "+buf[1]+" "+buf[2]+" "+buf[3]+" "+buf[4]);
    }

    public byte[] generateDataGram(byte color,byte ttl,byte seqno, byte srcid,byte cycle)
    {
        byte buf[]=new byte[5];
        buf[0]=color;
        buf[1]=ttl;
        buf[2]=seqno;
        buf[3]=srcid;
        buf[4]=cycle;
        return buf;
    }

    public void run() {
        try {
            DatagramSocket ds_rec = new DatagramSocket(1234);
            DatagramSocket ds_sen = new DatagramSocket();
            byte[] receive = new byte[65535];

            DatagramPacket DpReceive = null,DpSend=null;

            while (true) {

                DpReceive = new DatagramPacket(receive, receive.length);
                try {

                    ds_rec.receive(DpReceive);
                    byte dt[]=receive;
                    System.out.println("Packet Recieved");


                    if(dt[0]==0)//Red 
                    {
                        DatagramPacket g1=null,g2=null;
                        dt[1]--;
                        byte s1[] = generateDataGram((byte)2,dt[1],dt[2],dt[3],(byte)1);
                        byte s2[] = generateDataGram((byte)2,dt[1],dt[2],dt[3],(byte)0);

                        InetAddress ip = InetAddress.getLocalHost();
                        DpSend = new DatagramPacket(s1, s1.length,ip,1235);
                        ds_sen.send(DpSend);
                        System.out.println("Green DataGram Packet was forwarded ClockWise");
                        printData(s1);

                        DpSend = new DatagramPacket(s2, s2.length,ip,1236);
                        ds_sen.send(DpSend);
                        System.out.println("Green DataGram Packet was forwarded Anti ClockWise");
                        printData(s2);

                    }

                    else if(dt[0]==1)//Blue
                    {
                        System.out.println("Blue Packet recieved with srcid="+dt[3]+" Ip address is"+ds_rec.getRemoteSocketAddress());
                        printData(dt);
                    }

                    else if(dt[0]==2)//Green 
                    {
                        if(rand50()==1)
                        {
                            dt[1]--;
                        }
                        if(dt[1]>0)
                        {
                            InetAddress ip = InetAddress.getLocalHost();
                            byte send[]=generateDataGram((byte)2,dt[1],dt[2],dt[3],(byte)1);
                            DpSend = new DatagramPacket(send, send.length,ip,1235);
                            ds_sen.send(DpSend);
                            System.out.println("Green DataGram Packet was forwarded");
                            printData(send);
                        }
                        else if(dt[1]==0)
                        {
                            InetAddress ip = InetAddress.getLocalHost();
                            byte send[]=generateDataGram((byte)1,(byte)8 ,dt[2],dt[3],(byte)1);
                            DpSend = new DatagramPacket(send, send.length,ip,1235);
                            ds_sen.send(DpSend);
                            System.out.println("Blue DataGram Packet send");
                            printData(send);
                        }
                    }



                } catch (Exception e) {
                    System.out.println("Inside Run "+e);
                }
            }

        }
        catch (Exception e)
        {
            System.out.println("In Listening Class "+e);
        }
    }


}


public class A {
    
    public static void printData(byte buf[])
    {
        System.out.println("Data packet is: "+buf[0]+" "+buf[1]+" "+buf[2]+" "+buf[3]+" "+buf[4]);
    }

    public static void main(String args[]) throws InterruptedException {

        Thread.sleep(15000);
        ListeningAtA la=new ListeningAtA();
        Thread t=new Thread(la);
        t.start();
        try {
            DatagramSocket ds = new DatagramSocket();

            InetAddress ip = InetAddress.getLocalHost();
            byte buf[] = new byte[5];
            byte seq = 0;
            while (true) {

                Thread.sleep(5000);
                buf[0] = 0;
                buf[1] = 8;
                buf[2] = (seq++);
                buf[3] = 10;
                buf[4]=1;

                DatagramPacket DpSend =
                        new DatagramPacket(buf, buf.length, ip, 1235); //B's Port number:



                ds.send(DpSend);
                printData(buf);
            }

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
