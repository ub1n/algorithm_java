package java_algorithm.graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class ranking {

	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		int[][] dist=new int[n+1][n+1];
		int INF=9999999;
		
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				if(i==j) {
					dist[i][j]=0;
				}else {
					dist[i][j]=INF;
				}
			}
		}
		
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			dist[a][b]=1; //�Ÿ��� �����Ƿ� 1�� ����
		}
		
		//�÷��̵� ���� ����
		for(int k=1;k<=n;k++) {
			for(int i=1;i<=n;i++) {
				for(int j=1;j<=n;j++) {
					dist[i][j]=Math.min(dist[i][j], dist[i][k]+dist[k][j]);
				}
			}
		}
		int answer=0;
		
		for(int i=1;i<=n;i++) {
			int cnt=0;
			for(int j=1;j<=n;j++) {
				//���� �ٸ����� ������ �� �ְų� �ٸ����� ������ �����Ҽ� �ִٸ� cnt++
				if(dist[i][j]!=INF ||dist[j][i]!=INF) {
					cnt++;
				}
			}
			if(cnt==n) { //������ ������ �� �ִٸ� ��++
				answer++;
			}
		}
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb=new StringBuilder();
		sb.append(answer);
		bw.write(sb.toString());
		bw.flush();
		
		
	}
}
