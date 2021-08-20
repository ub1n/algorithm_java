package java_algorithm.graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class LCA_two {

	static int[][] parent;
	static ArrayList<Integer>[] tree;
	static int n,m,k;
	static int[] depth;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		n=Integer.parseInt(br.readLine());
		tree= new ArrayList[n+1];
		for(int i=0;i<=n;i++) {
			tree[i]=new ArrayList<Integer>();
		}
		k=0;
		for(int i=1;i<=n;i=i*2) {
			k++;
		}
		parent=new int[k][n+1];
		depth=new int[n+1];
		for(int i=0;i<n-1;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			tree[a].add(b);
			tree[b].add(a);
		}
		dfs(1,1);
		fillparent();
		
		int m=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			sb.append(lca(a,b)+"\n");
			
		}
		bw.write(sb.toString());
		bw.flush();
	}
	public static void dfs(int id,int count) {
		depth[id]=count;
		int size=tree[id].size();
		for(int i=0;i<size;i++) {
			int next=tree[id].get(i);
			if(depth[next]==0) {
				dfs(next,count+1);
				parent[0][next]=id;
			}
		}
		return;
	}
	
	public static void fillparent() {
		for(int i=1;i<k;i++) {
			for(int j=1;j<=n;j++) {
				parent[i][j]=parent[i-1][parent[i-1][j]];
			}
		}
	}
	
	public static int lca(int a,int b) {
		if(depth[a]<depth[b]) {
			int temp=a;
			a=b;
			b=temp;
		}
		
		for(int i=k-1;i>=0;i--) {
			if(Math.pow(2,i)<=depth[a]-depth[b]) {
				a=parent[i][a];
			}
		}
		
		if(a==b) {
			return a;
		}
		for(int i=k-1;i>=0;i--) {
			if(parent[i][a]!=parent[i][b]) {
				a=parent[i][a];
				b=parent[i][b];
			}
		}
		
		return parent[0][a];
	}
}
