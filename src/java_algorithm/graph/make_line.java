package java_algorithm.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class make_line {
//�񱳸� �ϴµ� cost�� ���� �𸥴�?
	//"������� Ű����" = "����" �� �ƴ� ��Ȳ
	//1~N�л� 2�� ���� Ű�� ���� ��ü�� ���ض�
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		
		ArrayList[] graph= new ArrayList[n+1];
		int[] degree=new int[n+1];
		for(int i=1;i<=n;i++) {
			graph[i]=new ArrayList<Integer>();
			degree[i]=0;
		}
		
		
		for(int i=0;i<m;i++) {
			st= new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b= Integer.parseInt(st.nextToken());
			graph[a].add(b);
			degree[b]+=1;
			
		}
		Queue<Integer> q=new LinkedList<>();
		for(int i=1;i<=n;i++) {
			if(degree[i]==0) {
				q.add(i);
			}
		}
		ArrayList<Integer> answer=new ArrayList<>();
		while(!q.isEmpty()) {
			int temp=q.poll();
			answer.add(temp);
			for(int i=0;i<graph[temp].size();i++) {
				degree[(int) graph[temp].get(i)]--;
				if(degree[(int) graph[temp].get(i)]==0) {
					q.add((Integer) graph[temp].get(i));
				}
			}
		}
		for(int i=0;i<answer.size();i++) {
			System.out.print(answer.get(i)+" ");
		}
	}
}
