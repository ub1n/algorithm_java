package java_algorithm.dp;

import java.util.Scanner;

public class subtotal_four {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		int[] dp=new int[n+1];
		int[] nums=new int[n+1];
		for(int i=1;i<=n;i++) {
			nums[i]=sc.nextInt();
		}
		//차근차근 구간합구하기
		dp[1]=nums[1];
		for(int i=1;i<=n;i++) {
			dp[i]=dp[i-1]+nums[i];
		}
		for(int i=0;i<m;i++) {
			int a=sc.nextInt();
			int b=sc.nextInt();
			System.out.println(dp[b]-dp[a-1]);
		}
	}
}
