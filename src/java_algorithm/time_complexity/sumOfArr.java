package java_algorithm.time_complexity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class sumOfArr {
//������� �ϰԵǸ� �װ��� �����Ͱ� �ʿ� ������� ���������ұ�?
	
	//A�� �κй迭�� ���δٱ��ϰ�, B�� �κй迭�� ���δٱ���
	//subA�� subB�� ���� ��(subB�� ��������) ���� �����͸� �ΰ� ���� ���
	//���ϴ� ������ ������ A ������ �̵�
	// ���ϴ� ������ ũ�� B������ �̵�
	//���� �����ٰ� �����͸� �Űܹ����� ��ġ�� ���� ���� �� ��ĥ������
	//���� ������ �ٷο��� ��ġ���� Ȯ�� �� ������
	
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
				long acount=0;
				long bcount=0;
				long currentA=subA.get(aPoint);
				long currentB=subB.get(bPoint);
				/*while(true) {
					aPoint++;
					if(aPoint==subA.size()) {
						break;
					}
					if(currentA==subA.get(aPoint)) {
						acount++;
					}else {
						break;
					}
				}*/
				while(true) {
					if(aPoint==subA.size()||currentA!=subA.get(aPoint)) {
						break;
					}
					aPoint++;
					acount++;
				}
				while(true) {
					if(bPoint==subB.size()||currentB!=subB.get(bPoint)) {
						break;
					}
					bPoint++;
					bcount++;
				}
				/*while(true) {
					bPoint++;
					if(bPoint==subB.size()) {
						break;
					}
					if(currentB==subB.get(bPoint)) {
						bcount++;
					}else {
						break;
					}
				}*/
				
				answer+=acount*bcount;
			}
			
		}
		System.out.println(answer);
	}
}
//���� ����Ʈ : n�����̶� long�� ���� �ȵ���.. �ٵ� long�� ������ acount�� bcount ���� ����ߴ�...