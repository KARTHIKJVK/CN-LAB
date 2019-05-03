import java.io.*;
import java.util.*;
 class lsr
{
public static void main(String args[]) throws IOException
{
int n=9999;
int j=0;
File file =  new File("/home/pawan/cn/f1.txt"); 
Scanner sc = new Scanner(file); 
String str;
str=sc.nextLine();
String[] arr = str.split(" ");

int v=Integer.parseInt(arr[0]);
int e=Integer.parseInt(arr[1]);
int graph[][]=new int [v][v];
 


for(int i=0;i<v;i++)
for(j=0;j<v;j++){
graph[i][j]=n;
}

for(int i=0;i<v;i++)
graph[i][i]=0;


   for(int i=0;i<e;i++)
    {
       str=sc.nextLine();
       arr =  str.split(" ");
       
       graph[Integer.parseInt(arr[0])][Integer.parseInt(arr[1])]=Integer.parseInt(arr[2]);
       graph[Integer.parseInt(arr[1])][Integer.parseInt(arr[0])]=Integer.parseInt(arr[2]);
         
    }

for(int i=0;i<v;i++)
for(j=0;j<v;j++)
System.out.print(graph[i][j]+" ");
  for(int i=0;i<v;i++)
    {
      int distance []=Dijkstra(graph,i);
      System.out.println("\nFor"+i);
      for(j=0;j<v;j++)
         System.out.print(distance[j]+" ");
     } 


}


public static  int[] Dijkstra(int[][] graph,int x)
{
 int v=graph.length;
 boolean visited[]=new boolean[v];
 int distance[]=new int[v];
 for(int i=0;i<v;i++)
    distance[i]=Integer.MAX_VALUE;
 distance[x]=0;
 for(int i=0;i<v-1;i++)
  {
   int min_vertex=findminVertex(distance,visited);
   visited[min_vertex]=true;
   for(int j=0;j<v;j++)
      {
      if(graph[min_vertex][j]!=0 && (!visited[j])&&(distance[min_vertex]!=Integer.MAX_VALUE))
         {
         int new_dist=distance[min_vertex]+graph[min_vertex][j];
         if(new_dist<distance[j])
         distance[j]=new_dist;
         }
      }
    }
  return distance;
 }


public static int findminVertex(int distance[],boolean visited[])
 {
 int  min=-1;
 for(int i=0;i<distance.length;i++)
    if((!visited[i]) && (min==-1 || distance[i]<distance[min]))
       min=i;
 return min;    
 }    

}
