import java.io.*;
import java.util.Scanner;
public class dvr
{
	static int mat[][];
 	static int path[][];
 	static int rt[][];
 	static int v;
 	static int e;

 	public static void main(String args[]) throws IOException
 	{
 		Scanner br = new Scanner(System.in);
  
 		System.out.println("number of Vertices?: ");
 		v = br.nextInt();
 		
 		System.out.println(" number of Edges?: ");
 		e = br.nextInt();
 		
 		mat 	= new int[v][v];
 		path = new int[v][v];
 		rt = new int[v][v];
 		for(int i = 0; i < v; i++)
 			for(int j = 0; j < v; j++)
 			{
 				if(i == j)
 					mat[i][j] = 0;
 				else
 					mat[i][j] = 9999;
 			}
 		
 		for(int i = 0; i < e; i++)
 		{
 			System.out.println("  enter data for Edge " + (i + 1) + ":");
 			System.out.print("Source: ");
 			int s = br.nextInt();
 			s--;
 			System.out.print("Destination: ");
 			int d = br.nextInt();
 			d--;
 			System.out.print("Cost: ");
 			int c = br.nextInt();
 			mat[s][d] = c;
 			mat[d][s] = c;
 		}
 		
 		dvr_calc_disp("The initial   Tables are: ");
 		
 		System.out.print("  enter the Source Node for the edge whose cost has changed: ");
 		int s = br.nextInt();
 		s--;
 			System.out.print("  enter the Destination Node for the edge whose cost has changed: ");
 			int d = br.nextInt();
 			d--;
 			System.out.print("  enter the new cost: ");
 			int c = br.nextInt();
 				mat[s][d] = c;
 				mat[d][s] = c;
 				
 				dvr_calc_disp("The new   Tables are: ");
 	}
 
 static void dvr_calc_disp(String message)
 {
  System.out.println();
  init_tables();
  update_tables();
  System.out.println(message);
  print_tables();
  System.out.println();
 }
 
 static void update_table(int source)
 {
  for(int i = 0; i < v; i++)
  {
   if(mat[source][i] != 9999)
   {
    int dist = mat[source][i];
    for(int j = 0; j < v; j++)
    {
     int inter_dist = rt[i][j];
     if(path[i][j] == source)
      inter_dist = 9999;
     if(dist + inter_dist < rt[source][j])
     {
      rt[source][j] = dist + inter_dist;
      path[source][j] = i;
     }
    }
   }
  }
 }
 
 
