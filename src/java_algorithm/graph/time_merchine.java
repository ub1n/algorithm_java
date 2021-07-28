package java_algorithm.graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class time_merchine {

	static int INF=Integer.MAX_VALUE;
	static int n,m;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		Edge edges[]=new Edge[m];
		
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			edges[i]=new Edge(a,b,c);
		}
		
		long[] dist=new long[n+1];
		for(int i=2;i<=n;i++) {
			dist[i]=INF;
		}
		for(int i=1;i<=n;i++) {
			for(int j=0;j<m;j++) {
				Edge now= edges[j];
				
				if(dist[now.start]==INF) {
					continue;
				}
				dist[now.end]=Math.min(dist[now.end], dist[now.start]+now.cost);
			}
		}
		StringBuilder sb= new StringBuilder();
		
		int flag=0;
		for(int i=0;i<m;i++) {
			Edge now=edges[i];
			
			if(dist[now.start]==INF) {
				continue;
			}
			if(dist[now.end]>dist[now.start]+now.cost) {
				flag=1;
				break;
			}
		}
		
		if(flag==0) {
			for(int i=2;i<=n;i++) {
				if(dist[i]!=INF) {
					sb.append(dist[i]+"\n");
				}else {
					sb.append("-1\n");
				}
			}
			bw.write(sb.toString());
		}else {
			bw.write("-1");
		}
		bw.write(sb.toString());
		bw.flush();
		
		
	}
}

class Edge{
	int start,end,cost;
	
	public Edge(int start,int end,int cost) {
		this.cost=cost;
		this.end=end;
		this.start=start;
	}
}
