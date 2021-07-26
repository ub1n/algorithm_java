package java_algorithm.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class make_game {

	static int[] degree;
	static ArrayList[] edge;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		
		degree= new int[n+1];
		edge=new ArrayList[n+1];
		int[] costs=new int[n+1];   //비용을 담을 배열
		int[] answer=new int[n+1];  //정답을 담을 배열
		for(int i=1;i<=n;i++) {
			edge[i]=new ArrayList<Integer>();
		}
		for(int i=1;i<=n;i++) {
			st=new StringTokenizer(br.readLine());
			int cost=Integer.parseInt(st.nextToken());
			while(true) {
				int a=Integer.parseInt(st.nextToken());
				if(a==-1) {
					break;
				}
				//앞에 필요한게 있다면 degree를 올리고, edge를 추가함
				degree[i]++;
				edge[a].add(i);
			}
			costs[i]=cost;
		}
		//큐에 차수가 0인 애들을 넣음
		Queue<Integer> q= new LinkedList<Integer>();
		for(int i=1;i<=n;i++) {
			if(degree[i]==0) {
				q.add(i);
			}
		}
		
		//꺼내면서 확인, 차수를 지우면서 답에 시간 추가
		while(!q.isEmpty()) {
			int now=q.poll();
			//answer[now]+=costs[now];
			
			int size=edge[now].size();
			for(int i=0;i<size;i++) {
				int temp=(int) edge[now].get(i);
				degree[temp]--;
				//costs[temp]+=costs[now]; //degree를 지우면서 정답에 현재 쓰인 시간 추가
				answer[temp]=Math.max(answer[temp], costs[now]);
				if(degree[temp]==0) {
					q.add(temp);
					costs[temp]+=answer[temp]; //앞에까지 걸렸던 시간 추가
				}
			}
		}
		for(int i=1;i<=n;i++) {
			System.out.println(costs[i]+" "+answer[i]);
		}
	}
	
}
