package java_algorithm.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class connect_network {
	//크루스칼 알고리즘 기초
	static int parent[];
	
	static int find(int a) {
		if(parent[a]==a) {
			return a;
		}
		return parent[a]=find(parent[a]);
	}
	
	static void union(int a,int b) {
		int pa=parent[a];
		int pb=parent[b];
		parent[pb]=pa;
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		int m=Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		PriorityQueue<Edge> pq=new PriorityQueue<>();
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			int start=Integer.parseInt(st.nextToken());
			int end=Integer.parseInt(st.nextToken());
			int cost=Integer.parseInt(st.nextToken());
			
			Edge e=new Edge(start,end,cost);
			pq.add(e); //우선순위 큐에 넣음   add는 오류가 날경우 오류를 던지고 offer는 false를 반환함
			
		}
		parent=new int[n+1]; //union, find를 하기 위해 parent
		for(int i=0;i<n;i++) {
			parent[i]=i;
		}
		int count=0;
		int answer=0;
		while(!pq.isEmpty()) {
			if(count==n-1) {
				break;
			}
			Edge e=pq.poll(); //코스트가 낮은애부터 꺼냄
			if(find(e.start)==find(e.end)) { //이미 연결된 곳이 있으면 넘김
				continue;
			}else { //연결된 곳이 없으면 연결
				union(e.start,e.end);
				count++;
				answer+=e.cost;
			}
			
			
		}
		
		System.out.println(answer);
	}
	
}

class Edge implements Comparable<Edge>{
	int start,end,cost;
	
	public Edge(int start,int end,int cost) {
		this.start=start;
		this.end=end;
		this.cost=cost;
		
		
	}
	@Override
	public int compareTo(Edge j) { //최소합 비교연산자
		return this.cost-j.cost;
	}
}
