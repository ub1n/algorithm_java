package java_algorithm.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class subtotal_five {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		
		int[][] map=new int[n+1][n+1];
		int[][] dp=new int[n+1][n+1];
		for(int i=1;i<=n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=1;j<=n;j++) {
				int num=Integer.parseInt(st.nextToken());
				map[i][j]=num;
				
				dp[i][j]=dp[i-1][j]+num;
			}
		}
		
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			int x1=Integer.parseInt(st.nextToken());
			int y1=Integer.parseInt(st.nextToken());
			int x2=Integer.parseInt(st.nextToken());
			int y2=Integer.parseInt(st.nextToken());
			int ans=0;
			for(int j=y1;j<=y2;j++){
				ans+=dp[x2][j]-dp[x1-1][j];
			}
			sb.append(ans+"\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}
}
