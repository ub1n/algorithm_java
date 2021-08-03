package java_algorithm.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class bulb {
//방금했던 행동이 이후 행동에 영향을 미침 -> dp -> 당시의 최선값 = 이전에 뭘했든 상관없다.
	//->분할정복 -> 잘게 쪼갬, 한개까지
	
	static int[][] dp;
	static int[] bulbs;
	static int n;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		int k=Integer.parseInt(st.nextToken());
		
		bulbs=new int[n];
		dp=new int[n][n];
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			
			int num=Integer.parseInt(st.nextToken());
			bulbs[i]=num;
		}
		divideConquer(0,n-1);
		System.out.println(dp[0][n-1]);
		
		
	}
	
	static int divideConquer(int start,int end) {
		//1. 자기자신이면 return
		if(start==end) {
			return 0;
		}
		//2. 이미 값이 있으면 return
		int ret=dp[start][end];
		if(ret!=0) {
			return ret;
		}
		
		//3.가장 바닥까지 쪼개서 값 확인
		ret=Integer.MAX_VALUE;
		int left,right;
		for(int i=start;i<end;i++) {
			left=divideConquer(start,i);
			right=divideConquer(i+1,end);
			
			if(bulbs[start]==bulbs[i+1]) { //값이 같으면
				ret=Math.min(ret, left+right);
			}else { //다르면
				ret=Math.min(ret,left+right+1);
			}
		}
		return dp[start][end]=ret;
	}
}
