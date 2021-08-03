package java_algorithm.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class bulb {
//����ߴ� �ൿ�� ���� �ൿ�� ������ ��ħ -> dp -> ����� �ּ��� = ������ ���ߵ� �������.
	//->�������� -> �߰� �ɰ�, �Ѱ�����
	
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
		//1. �ڱ��ڽ��̸� return
		if(start==end) {
			return 0;
		}
		//2. �̹� ���� ������ return
		int ret=dp[start][end];
		if(ret!=0) {
			return ret;
		}
		
		//3.���� �ٴڱ��� �ɰ��� �� Ȯ��
		ret=Integer.MAX_VALUE;
		int left,right;
		for(int i=start;i<end;i++) {
			left=divideConquer(start,i);
			right=divideConquer(i+1,end);
			
			if(bulbs[start]==bulbs[i+1]) { //���� ������
				ret=Math.min(ret, left+right);
			}else { //�ٸ���
				ret=Math.min(ret,left+right+1);
			}
		}
		return dp[start][end]=ret;
	}
}
