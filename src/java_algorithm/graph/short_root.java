package java_algorithm.graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class short_root {

	static int V,E;
	static int start;
	static ArrayList[] adjList;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		V=Integer.parseInt(st.nextToken());
		E=Integer.parseInt(st.nextToken());
		start=Integer.parseInt(br.readLine());
		adjList=new ArrayList[V+1];
		
		int[] dist=new int[V+1];
		for(int i=1;i<=V;i++) {
			adjList[i]=new ArrayList<Node>();
			dist[i]=Integer.MAX_VALUE;
		}
		dist[start]=0;
		
		for(int i=1;i<=E;i++) {
			st=new StringTokenizer(br.readLine());
			int u=Integer.parseInt(st.nextToken());
			int v=Integer.parseInt(st.nextToken());
			int w=Integer.parseInt(st.nextToken());
			
			adjList[u].add(new Node(v,w));
		}
		
		PriorityQueue pq=new PriorityQueue<Node>();
		pq.add(new Node(start,0));
		
		while(!pq.isEmpty()) {
			Node now=(Node)pq.poll();
			
			if(now.cost>dist[now.id]) {
				continue;
			}
			
			int size=adjList[now.id].size();
			for(int i=0;i<size;i++) {
				//인접리스트 확인하여 최소값 갱신하면 q에 넣음
				Node next=(Node)adjList[now.id].get(i);
				
				if(dist[next.id]>now.cost+next.cost) {
					dist[next.id]=now.cost+next.cost;
					pq.add(new Node(next.id,dist[next.id]));  //pq.add(next) 아님
				}
			}
			
		}
		StringBuilder sb = new StringBuilder();
        for (int i=1; i<=V; i++) {
        	if (dist[i] == Integer.MAX_VALUE) {
        		sb.append("INF\n");
        	}
        	else {
        		sb.append(dist[i]+"\n");
        	}
        }
        bw.write(sb.toString());
        
        bw.flush();
        bw.close();
        br.close();
		
		
		
	}
}

class Node implements Comparable<Node>{
	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		return this.cost-o.cost;
	}

	int id,cost;
	
	public Node(int id,int cost) {
		this.id=id;
		this.cost=cost;
	}
	
}
