package java_algorithm.number_theory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class minusgcd {
	//하나를 뺴고 다 구함
	
	//ex ) 8 12 24 36 48
	//LtoR 8 4  4  4   4    왼쪽끝부터 오른쪽까지 gcd의 누적
	//RtoL 4 12 12 12 48    
	
	//LR[1] 과 RL[3] 의 gcd를 구하면 2가 빠진 gcd를 구할 수 있음
	
	//gcd(a,b)== gcd(b,a%b) ,stop a%b==0
	static int gcd(int a,int b) {
		if(b>a) {
			int temp=a;
			a=b;
			b=temp;
		}
		while(b!=0) {
			int r= a%b;
			a=b;
			b=r;
		}
		return a;
	}
	
	static int N;
	static int[] nums;
	static int[] gcdLtoR;
	static int[] gcdRtoL;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		StringTokenizer st=new StringTokenizer(br.readLine());
		nums=new int[N];
		gcdLtoR=new int[N];
		gcdRtoL=new int[N];
		for(int i=0;i<N;i++) {
			nums[i]=Integer.parseInt(st.nextToken());
		}
		int temp=0;
		for(int i=0;i<N;i++) {
			gcdLtoR[i]=gcd(nums[i],temp);
			temp=gcdLtoR[i];
		}
		temp=0;
		for(int i=N-1;i>=0;i--) {
			gcdRtoL[i]=gcd(nums[i],temp);
			temp=gcdRtoL[i];
		}
		int max_gcd=-1;
		int minus_num=0;
		for(int i=0;i<N;i++) {
			int current_gcd;
			if(i==0) {
				current_gcd=gcdRtoL[i+1];
			}else if(i==N-1) {
				current_gcd=gcdLtoR[i-1];
			}else {
				current_gcd=gcd(gcdLtoR[i-1],gcdRtoL[i+1]);
			}
			if(nums[i]<current_gcd || gcd(nums[i],current_gcd)!=current_gcd) {
				if(max_gcd<current_gcd) {
					max_gcd=current_gcd;
					minus_num=nums[i];
				}
			}
		}
		
		if(max_gcd==-1) {
			System.out.println(-1);
		}else {
			System.out.print(max_gcd);
			System.out.print(" ");
			System.out.println(minus_num);
		}
		
		
	}
}
