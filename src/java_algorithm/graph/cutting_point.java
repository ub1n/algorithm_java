package java_algorithm.graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class cutting_point {

	static int V,E, order, ans;
	static ArrayList[] adjList;
	static int[] visit;
	static boolean[] isDjj;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter (new OutputStreamWriter(System.out));
		
		
		// 1. 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		adjList = new ArrayList[V+1];
		for (int i=1; i<=V; i++) {
			adjList[i] = new ArrayList<Integer>();
		}
		
		// 양방향 간선 
		int a, b;
		for (int i = 1; i<=E; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			adjList[a].add(b);
			adjList[b].add(a);
		}
		
		isDjj=new boolean[V+1];
		visit=new int[V+1];
		
		order =1;
		for(int i=1;i<=V;i++) {
			if(visit[i]==0) {
				dfs(i,true,0);
			}
		}
		
		StringBuilder sb=new StringBuilder();
		int ans=0;
		for(int i=1;i<=V;i++) {
			if(isDjj[i]==true) {
				ans++;
				sb.append(i+" ");
			}
		}
		if(ans==0) {
			sb=new StringBuilder();
		}
		bw.write(ans+"\n"+sb.toString());
		bw.flush();
	}
	static int dfs(int id,boolean isRoot,int parent) {
		visit[id]=order;
		order++;
		
		int child=0;
		int size=adjList[id].size();
		int ret=visit[id];
		
		for(int i=0;i<size;i++) {
			int next=(int) adjList[id].get(i);
			if(next==parent) {
				continue;
			}
			if(visit[next]==0) {
				child++;
				int low=dfs(next,false,id);
				if(!isRoot&&low>=visit[id]) {
					isDjj[id]=true;
				}
				ret=Math.min(ret, low);
			}else {
				ret=Math.min(ret,visit[next]);
			}
		}
		
		if(isRoot&&child>=2) {
			isDjj[id]=true;
		}
		
		return ret;
	}
}
