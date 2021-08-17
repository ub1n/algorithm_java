package java_algorithm.graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class professor_not_wait {

	//지금까지 잰 결과를 바탕으로 대답할 수도 있고 못할수도 있다 - union, find
	//parent[i](최종조상)와의 차이를 가지는 dist[i]
	//union을 칠때 값 갱신
	//같은 그룹이라면 각자의 dist값 빼주면 됨
	
	
	//pa+dist[a] =a
	//a+w = b
	// b= pb+dist[b]
	
	//pa + dist[a] = b-w
	//pa+dist[a] = pb+dist[b] -w
	// pa+(dist[a]-dist[b]+w)=pb
	static int[] parent;
	static long[] dist;
	static int find(int a) {
		if(parent[a]==a) {
			return a;
		}
		int pa=find(parent[a]);
		dist[a]+=dist[parent[a]];  //여기부분 잘생각해보기!
		
		return parent[a]=pa;
	}
	
	static void union(int a,int b,long w) {
		int pa=find(a);
		int pb=find(b);
		if(pa==pb) {
			return;
		}
		dist[pb]=dist[a]-dist[b]+w;
		parent[pb]=pa;
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		while(true) {
			st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());
			parent=new int[n+1];
			dist=new long[n+1];
			if(n==0 && m==0) {
				break;
			}
			for(int i=1;i<=n;i++) {
				parent[i]=i;
			}
			
			for(int i=0;i<m;i++) {
				st=new StringTokenizer(br.readLine());
				String question=st.nextToken();
				if("!".equals(question)) {
					int a=Integer.parseInt(st.nextToken());
					int b=Integer.parseInt(st.nextToken());
					int c=Integer.parseInt(st.nextToken());
					union(a,b,c);
				}else {
					int a=Integer.parseInt(st.nextToken());
					int b=Integer.parseInt(st.nextToken());
					if(find(a)!=find(b)) {
						sb.append("UNKNOWN\n");
					}else {
						sb.append(dist[b]-dist[a]+"\n");
					}
				}
			}
		}
		bw.write(sb.toString());
		bw.flush();
		
	}
}

//https://subbak2.tistory.com/59
//삽질 포인트 : parent[pb]=pa를 parent[pa]=pb로 하면 안된다. b가 리더여야함
//처음에 i로 parent를 초기화를 안했다.
