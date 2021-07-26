package java_algorithm.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class rep_of_set {

	static int n,m;
	static int[] parent;
	
	static int find(int a) {
		if(parent[a]==a) {
			return a;
		}
		return parent[a]=find(parent[a]);
	}
	
	static void union(int a,int b) {
		int pa=find(a);
		int pb=find(b);
		
		parent[pa]=pb;
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		parent=new int[n+1];
		
		for(int i=1;i<=n;i++) {
			parent[i]=i;
		}
		
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			
			if(a==0) {
				union(b,c);
			}else {
				if(find(b)==find(c)) {
					System.out.println("YES");
				}else {
					System.out.println("NO");
				}
			}
		}
		
	}
}
