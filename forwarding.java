import java.util.Random;
import java.util.Scanner;

 class LabTask{
    public static void main(String args[]){
        Random random = new Random();
        Scanner sc = new Scanner(System.in);
        System.out.println("=>This program assumes class-A, class-B, class-C as the only available networks\n" +
                "=>The first and last host ip addresses are excluded for every network");
        System.out.print("Enter no of physical networks :\t");
        int numberOfPhysicalNetworks = sc.nextInt();
        System.out.print("Enter no of host devices per each network :\t");
        int numberOfHostDevicesPerNetwork = sc.nextInt();
        if(numberOfPhysicalNetworks>1){
            int ip[]= new int[32];
            int totalNoOfDevices=numberOfHostDevicesPerNetwork*numberOfPhysicalNetworks;
            char classOfNetwork;
            int k=1;
            while((numberOfPhysicalNetworks/(int)Math.pow(2,k))>0) {
                k++;
            }
            System.out.println(k);
            if(totalNoOfDevices<((int)Math.pow(2,8-k)-2)*(int)Math.pow(2,k)){
                System.out.println("Network :\tClass-C");
                System.out.println("Number of Subnets :\t"+(int)Math.pow(2,k));
                int ip_oct_1=random.nextInt(32)+192,ip_oct_2=random.nextInt(255);
                int ip_oct_3=random.nextInt(255),ip_oct_4=0;
                System.out.println("Assume a Class-C "+ip_oct_1+"."+ip_oct_2+"."+ip_oct_3+"."+ip_oct_4
                        +" network Id is allocated to you\nWe are using "+
                        "/"+(24+k)+" as subnet id");
                System.out.println("-------------------Forwarding Table-------------------");
                System.out.print("NetworkId\t\t\t\tSubnetMask\t\t\tInterface\n");
                for(int i=0;i<(int)Math.pow(2,k);i++){
                    System.out.print(ip_oct_1+"."+ip_oct_2+"."+ip_oct_3+".");
                    System.out.print(i*(int)Math.pow(2,8-k)+"\t\t\t");
                    System.out.print("255.255.255.");
                    System.out.print(((int)Math.pow(2,k)-1)*(int)Math.pow(2,8-k)+"\t\t");
                    System.out.print("\tI"+(i+1)+"\n");
                }

                float thru=(float)totalNoOfDevices/(((int)Math.pow(2,8-k)-2)*(int)Math.pow(2,k));
                thru*=100;
                System.out.println("Throughput =\t"+thru+"%");
                return;
            }
            if(totalNoOfDevices<((int)Math.pow(2,16-k)-2)*(int)Math.pow(2,k)){
                System.out.println("Network :\tClass-B");
                System.out.println("Number of Subnets :\t"+(int)Math.pow(2,k));
                int ip_oct_1=random.nextInt(64)+128,ip_oct_2=random.nextInt(255);
                int ip_oct_3=0,ip_oct_4=0;
                System.out.println("Assume a Class-B "+ip_oct_1+"."+ip_oct_2+"."+ip_oct_3+"."+ip_oct_4
                        +" network Id is allocated to you\nWe are using " +
                        "/"+(16+k)+" as subnet id");
                System.out.println("-------------------Forwarding Table-------------------");
                System.out.print("NetworkId\t\t\t\tSubnetMask\t\t\tInterface\n");
                for(int i=0;i<(int)Math.pow(2,k);i++){
                    System.out.print(ip_oct_1+"."+ip_oct_2+".");
                    System.out.print((i*(int)Math.pow(2,16-k))/256+".");
                    System.out.print((i*(int)Math.pow(2,16-k))%256+"\t\t\t");
                    System.out.print("255.255.");
                    System.out.print((((int)Math.pow(2,k)-1)*(int)Math.pow(2,16-k))/256+".");
                    System.out.print((((int)Math.pow(2,k)-1)*(int)Math.pow(2,16-k))%256+"\t\t");
                    System.out.print("\tI"+(i+1)+"\n");
                }
                float thru=(float)totalNoOfDevices/(((int)Math.pow(2,16-k)-2)*(int)Math.pow(2,k));
                thru*=100;
                System.out.println("Throughput =\t"+thru+"%");
                return;
            }
            if(totalNoOfDevices<((int)Math.pow(2,24-k)-2)*(int)Math.pow(2,k)){
                System.out.println("Network :\tClass-A");
                System.out.println("Number of Subnets :\t"+(int)Math.pow(2,k));
                int ip_oct_1=random.nextInt(64)+128,ip_oct_2=random.nextInt(255);
                int ip_oct_3=0,ip_oct_4=0;
                System.out.println("Assume a Class-A "+ip_oct_1+"."+ip_oct_2+"."+ip_oct_3+"."+ip_oct_4
                        +" network Id is allocated to you");
                System.out.println("-------------------Forwarding Table-------------------");
                System.out.print("NetworkId\t\t\t\tSubnetMask\t\t\tInterface\n");
                for(int i=0;i<(int)Math.pow(2,k);i++){
                    System.out.print(ip_oct_1+".");
                    int p=i*(int)Math.pow(2,24-k);
                    int p4=p%256;
                    p/=256;
                    int p3=p%256;
                    p/=256;
                    System.out.print(p+"."+p3+"."+p4+"\t\t\t");
                    System.out.print("255.");
                    int l=((int)Math.pow(2,k)-1)*(int)Math.pow(2,24-k);
                    int l4=l%256;
                    l/=256;
                    int l3=l%256;
                    l/=256;
                    System.out.print(l+"."+l3+"."+l4+"\t\t");
                    System.out.print("\tI"+(i+1)+"\n");
                }
                float thru=(float)totalNoOfDevices/(((int)Math.pow(2,24-k)-2)*(int)Math.pow(2,k));
                thru*=100;
                System.out.println("Throughput =\t"+thru+"%");
                return;
            }
        }
    }
}
