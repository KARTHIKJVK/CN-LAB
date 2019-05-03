#include<iostream>
#include<sys/ipc.h>
#include<sys/shm.h>
#include<fstream>
using namespace std;
int main() { 
	int n;
	cout<<"Enter number of routers in network topology :\t";
	cin>>n;
	int shmid0,shmid1,shmid2,shmid3; 
	shmid0 = shmget(IPC_PRIVATE,n*sizeof(int),0777|IPC_CREAT);
	shmid1 = shmget(IPC_PRIVATE,n*sizeof(int),0777|IPC_CREAT);
	shmid2 = shmget(IPC_PRIVATE,n*sizeof(int),0777|IPC_CREAT);
	shmid3 = shmget(IPC_PRIVATE,n*sizeof(int),0777|IPC_CREAT); 
	ofstream fout;
	fout.open("sh_memory.txt",ios::out);
	fout<<shmid0<<endl;
	fout<<shmid1<<endl;
	fout<<shmid2<<endl;
	fout<<shmid3;
	fout.close(); 
	return 0; 
}
