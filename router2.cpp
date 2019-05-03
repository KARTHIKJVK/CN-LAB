#include<iostream>
#include<fstream>
#include<sys/ipc.h>
#include<sys/shm.h>
#include<unistd.h>
#define INF 99999999
using namespace std;
int main(){
        ifstream fin;
        fin.open("sh_memory.txt",ios::in);
        char ch;
        int ans=0,cnt=0;
        int shmid0,shmid1,shmid2,shmid3;
        while(fin.get(ch)){
                if(ch!='\0'&&ch!='\t'&&ch!='\n')
                ans=ans*10+ch-48;
                else{
                        if(cnt==0)
                        shmid0=ans,ans=0,cnt++;
                        else if(cnt==1)
                        shmid1=ans,ans=0,cnt++;
                        else if(cnt==2)
                        shmid2=ans,ans=0,cnt++;
                }
        }
        shmid3=ans;
        //cout<<shmid0<<endl<<shmid1<<endl<<shmid2<<endl<<shmid3<<endl;
        fin.close();
        int* previous[4];
        int routing_table[4][3];
        int adj[]={INF,3,0,11};
        for(int i=0;i<4;i++){
                routing_table[i][0]=i;
                routing_table[i][2]=i;
        }
		routing_table[0][1]=INF;
        routing_table[1][1]=3;
        routing_table[2][1]=0;
        routing_table[3][1]=11;
        previous[0]=(int*) shmat(shmid0,0,0);
        previous[1]=(int*) shmat(shmid1,0,0);
        previous[2]=(int*) shmat(shmid2,0,0);
        previous[3]=(int*) shmat(shmid3,0,0);
        for(int i=0;i<4;i++){
                previous[2][i]=routing_table[i][1];
        }
        sleep(15);
  //       for(int i=0;i<4;i++){
		// 	for(int j=0;j<4;j++){
		// 		if(adj[i]!=INF&&i!=2){
		// 			cout<<previous[i][j]<<"\t";
		// 		}
		// 	}
		// 	cout<<endl;
		// }
		for(int a0=0;a0<2;a0++){
		for(int i=0;i<4;i++){
			if(adj[i]!=INF&&i!=2){
				for(int j=0;j<4;j++){
					if(adj[i]+previous[i][j]<routing_table[j][1]){
						routing_table[j][1]=adj[i]+previous[i][j];
						routing_table[j][2]=i;
					}
				}
			}
		}
		for(int i=0;i<4;i++){
			previous[2][i]=routing_table[i][1];
		}
		sleep(15);
	}
	cout<<"Final Routing Table at router C\n";
	cout<<"Destination\tDistance\tNext Hop\n";
	for(int i=0;i<4;i++){
		cout<<"\t"<<(char)(routing_table[i][0]+65)<<"\t";
		cout<<routing_table[i][1]<<"\t";
		cout<<(char)(routing_table[i][2]+65)<<"\n";
	}
        return 0;
}


