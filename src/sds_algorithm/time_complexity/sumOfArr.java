package sds_algorithm.time_complexity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class sumOfArr {
//기존대로 하게되면 네개의 포인터가 필요 어느쪽을 움직여야할까?
	
	//A의 부분배열을 전부다구하고, B의 부분배열을 전부다구함
	//subA와 subB에 정렬 후(subB는 역순정렬) 각각 포인터를 두고 합을 계산
	//원하는 값보다 작으면 A 포인터 이동
	// 원하는 값보다 크면 B포인터 이동
	//답을 만났다고 포인터를 옮겨버리면 겹치는 답이 있을 때 놓칠수있음
	//답을 만나면 바로옆이 겹치는지 확인 후 움직임
	
	static int T;
	static int n;
	static int A[];
	static int m;
	static int B[];
	static ArrayList<Long> subA;
	static ArrayList<Long> subB;
	static int aPoint;
	static int bPoint;
	static long answer;
	
	public static ArrayList<Long> sumOfNum(int[] arr,int n){
		ArrayList<Long> subArr=new ArrayList<Long>();
		
		for(int i=0;i<n;i++) {
			long sum=0;
			for(int j=i;j<n;j++) {
				sum+=arr[j];
				subArr.add(sum);
			}
		}
		
		return subArr;
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(br.readLine());
		A=new int[n+1];
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			A[i]=Integer.parseInt(st.nextToken());
		}
		m = Integer.parseInt(br.readLine());
		B=new int[m+1];
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<m;i++) {
			B[i]=Integer.parseInt(st.nextToken());
		}
		subA=sumOfNum(A,n);
		subB=sumOfNum(B,m);
		
		Collections.sort(subA);
		Collections.sort(subB,Collections.reverseOrder());
		
		aPoint=0;
		bPoint=0;
		answer=0;
		
		while(true) {
			if(aPoint==subA.size() || bPoint==subB.size()) {
				break;
			}
			long current=subA.get(aPoint)+subB.get(bPoint);
			if(current<T) {
				aPoint++;
			}
			else if(current>T) {
				bPoint++;
			}
			else {
				long acount=1;
				long bcount=1;
				long currentA=subA.get(aPoint);
				long currentB=subB.get(bPoint);
				while(true) {
					aPoint++;
					if(aPoint==subA.size()) {
						break;
					}
					if(currentA==subA.get(aPoint)) {
						acount++;
					}else {
						break;
					}
				}
				while(true) {
					bPoint++;
					if(bPoint==subB.size()) {
						break;
					}
					if(currentB==subB.get(bPoint)) {
						bcount++;
					}else {
						break;
					}
				}
				
				answer+=acount*bcount;
			}
			
		}
		System.out.println(answer);
	}
}
//삽질 포인트 : n제곱이라서 long을 쓰면 안될줄.. 근데 long을 심지어 acount와 bcount 에도 써야했다...