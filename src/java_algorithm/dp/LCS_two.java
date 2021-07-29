package java_algorithm.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class LCS_two {

	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		String inputA=br.readLine();
		String inputB=br.readLine();
		
		int[][] dp=new int[inputB.length()+1][inputA.length()+1];
		
		int[] dp2=new int[inputA.length()+1];
		for(int i=1;i<=inputB.length();i++) {
			int max=0;
			for(int j=1;j<=inputA.length();j++) {
				max=Math.max(dp2[j-1], max);
				if(inputB.charAt(i-1)==inputA.charAt(j-1)) {
					dp2[j]=Math.max(dp2[j-1]+1, max+1);
					dp[i][j]=dp[i-1][j-1]+1;
				}else {
					dp[i][j]=Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		
		int max_num=0;
		String answer="";
		for(int i=0;i<=inputA.length();i++) {
			if(dp2[i]>max_num) {
				max_num=dp2[i];
				answer+=inputA.charAt(i-1);
			}
			
		}
		/*for(int i=1;i<=inputA.length();i++) {
			System.out.println(dp2[i]);
		}*/
		
		System.out.println(max_num);
		/*if(max_num!=0) {
			System.out.println(answer);
		}*/
		/*for(int i=1;i<=inputA.length();i++) {
			System.out.println(dp[inputB.length()][i]);
		}*/
		
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		int N = inputA.length();
		int M = inputB.length();
		StringBuilder sb = new StringBuilder();
		while ( N != 0 && M != 0) {
			if (inputA.charAt(N - 1) == inputB.charAt(M - 1)) {
				sb.insert(0, inputA.charAt(N - 1));
				N--;
				M--;
			} else if (dp[M][N] == dp[M - 1][N]) {
				M--;
			} else if (dp[M][N] == dp[M][N - 1]) {
				N--;
			}
		}
		// LCS 문자열 길이 출력
		System.out.println(sb.length());
		bw.write(sb.toString());

		bw.flush();
	}
}
