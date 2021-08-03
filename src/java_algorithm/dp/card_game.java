package java_algorithm.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class card_game {
	//분할정복
	static int[] cards;
	static int[][][] dp;

	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		int t=Integer.parseInt(br.readLine());
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb=new StringBuilder();
		for(int k=0;k<t;k++) {
			int n=Integer.parseInt(br.readLine());
			st=new StringTokenizer(br.readLine());
			
			cards=new int[n];
			dp=new int[2][n][n];
			for(int i=0;i<n;i++) {
				cards[i]=Integer.parseInt(st.nextToken());
				
			}
			sb.append(game(0,0,n-1)+"\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
	}
	
	static int game(int flag,int start,int end) {
		if(start==end) {
			if(flag==0) {
				return dp[flag][start][end]=cards[start];
			}else {
				return dp[flag][start][end]=0;
			}
		}
		
		if(dp[flag][start][end]!=0) {
			return dp[flag][start][end];
		}
		
		if(flag==0) {
			return dp[flag][start][end]=Math.max(game(1,start+1,end)+cards[start], game(1,start,end-1)+cards[end]);
		}else {
			return dp[flag][start][end]=Math.min(game(0,start+1,end), game(0,start,end-1));
		}
		
	}
}
