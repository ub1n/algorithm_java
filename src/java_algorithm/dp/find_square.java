package java_algorithm.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class find_square {

	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		
		int[][] map=new int[n+1][m+1];
		int[][] dp=new int[n+1][m+1];
		int max=0;
		for(int i=1;i<=n;i++) {
			String s=br.readLine();
			for(int j=1;j<=m;j++) {
				map[i][j]=s.charAt(j-1)-'0';   //charÀ» int·Î
				if(map[i][j]==1) {
					dp[i][j]=Math.min(dp[i-1][j-1], dp[i-1][j]);
					dp[i][j]=Math.min(dp[i][j], dp[i][j-1])+1;
					max=Math.max(dp[i][j], max);
				}
			}
		}
		
		StringBuilder sb=new StringBuilder();
		sb.append(max*max);
		bw.write(sb.toString());
		bw.flush();
		
	}
}
