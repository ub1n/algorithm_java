package java_algorithm.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class cut_cake {

	static int n;
	static int[] cakes;
	static long[][][] dp;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		cakes=new int[2*n];
		
		for(int i=0;i<n;i++) {
			cakes[i]=Integer.parseInt(br.readLine());
			cakes[n+i]=cakes[i];
		}
		if(n==1) {
			System.out.println(cakes[0]);
			return;
		}
		
		long max=0;
		dp=new long[2][2*n][2*n];
		for(int i=0;i<n;i++) {
			//dp=new long[2][2*n][2*n];
			int temp=cakes[i];
			max=Math.max(max, getCake(1,i+1,n-1+i)+temp);
		}
		
		System.out.println(max);
		
	}
	
	static long getCake(int flag,int start,int end) {
		if(start==end) {
			if(flag==0) {
				return dp[flag][start][end]=cakes[start];
			}else {
				return dp[flag][start][end]=0;
			}
		}
		if(dp[flag][start][end]!=0) {
			return dp[flag][start][end];
		}
		if(flag==0) {
			return dp[flag][start][end]=Math.max(getCake(1,start+1,end)+cakes[start], getCake(1,start,end-1)+cakes[end]);
		}else {
			if(cakes[start]>cakes[end]) {
				return dp[flag][start][end]=getCake(0,start+1,end);
			}else {
				return dp[flag][start][end]=getCake(0,start,end-1);
			}
			//return dp[flag][start][end]=Math.min(getCake(0,start+1,end), getCake(0,start,end-1));
		}
	}
}
