package java_algorithm.dp;

import java.util.ArrayList;
import java.util.Scanner;

public class triangle {

	static int n;
	static int[][] dp;
	static ArrayList[] nums;
	static int[][] tri;
	
	public static void main(String[] args) throws Exception{
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		dp=new int[n][n];
		nums=new ArrayList[n];
		tri=new int[n][n];
		for(int i=0;i<n;i++) {
			ArrayList arr=new ArrayList<Integer>();
			for(int j=0;j<=i;j++) {
				tri[i][j]=sc.nextInt();
				//arr.add(sc.nextInt());
			}
			//nums[i]=arr;
		}
		dp[0][0]=tri[0][0];
		for(int i=0;i<n-1;i++) {
			for(int j=0;j<=i;j++) {
				dp[i+1][j]=Math.max(dp[i+1][j], dp[i][j]+tri[i+1][j]);
				dp[i+1][j+1]=Math.max(dp[i+1][j+1], dp[i][j]+tri[i+1][j+1]);
			}
		}
		int answer=0;
		for(int i=0;i<n;i++) {
			if(answer<dp[n-1][i]) {
				answer=dp[n-1][i];
			}
		}
		System.out.println(answer);
	}
}
