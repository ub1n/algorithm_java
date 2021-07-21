package java_algorithm;

import java.util.Scanner;

public class subtotal {
	static int N;
	static int M;
	static int A[];
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		N=sc.nextInt();
		M=sc.nextInt();
		A=new int[N+1];  
		for(int i=0;i<N;i++) {
			A[i]=sc.nextInt();
		}
		
		int lpoint=0;
		int rpoint=0;
		long current_sum=A[0];
		int answer=M+10;
		
		while(true) {
			if(current_sum>=M) {
				answer=Math.min(answer, rpoint-lpoint+1);
				current_sum-=A[lpoint++];
			}
			else {
				current_sum+=A[++rpoint];
			}
			if(rpoint==N) {
				break;
			}
		}
		
		if(answer==M+10) {
			System.out.println(0);
		}else {
			System.out.println(answer);
		}
	}
}
