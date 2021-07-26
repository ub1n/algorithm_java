package java_algorithm.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class connect_network {
	//ũ�罺Į �˰��� ����
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
			pq.add(e); //�켱���� ť�� ����   add�� ������ ����� ������ ������ offer�� false�� ��ȯ��
			
		}
		parent=new int[n+1]; //union, find�� �ϱ� ���� parent
		for(int i=0;i<n;i++) {
			parent[i]=i;
		}
		int count=0;
		int answer=0;
		while(!pq.isEmpty()) {
			if(count==n-1) {
				break;
			}
			Edge e=pq.poll(); //�ڽ�Ʈ�� �����ֺ��� ����
			if(find(e.start)==find(e.end)) { //�̹� ����� ���� ������ �ѱ�
				continue;
			}else { //����� ���� ������ ����
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
	public int compareTo(Edge j) { //�ּ��� �񱳿�����
		return this.cost-j.cost;
	}
}
