package java_algorithm.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class LIS_five {

	//NlogN���� �ؾ��� -> ����Ž��
	//lis��� �迭�� ù���� ���� ����
	//������ ������ ũ�� ����
	//���ο� ������ ������������ ������ ����Ž�� -> ���ο� ���� ��ġ ���� (lowerbound ��밡��)
	// ex) 10 20 30���� 15 ���� -> ����Ž�������� 15�� �ι�°���� Ȯ���ϰ� �ι�°�� 15�� ���� , index�� 2�� ����
	
	//input  10 20 10 30 20 50 40
	//index  ->input[i]�� LIS�� ���� ��ġ
	//index  0  1  0  2  1  3  2   
	
	//��� : �ǵ� ���� Ȯ��  if(index[i] == LISSize) 50 ������ ��� , LISSize--
	static int[] arr;
	static int n;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		
		n=Integer.parseInt(br.readLine());
		arr=new int[n];
		StringTokenizer st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}

		ArrayList<Integer> lis=new ArrayList<Integer>();
		int[] dp=new int[n];
		
		lis.add(arr[0]);
		dp[0]=0;
		
		for(int i=1;i<n;i++) {
			int last_num=lis.get(lis.size()-1);
			if(arr[i]>last_num) {
				lis.add(arr[i]);
				dp[i]=lis.size()-1;
			}else {
				int index=BS(lis,arr[i],0,lis.size()-1);
				dp[i]=index;
				lis.set(index, arr[i]);
			}
		}
		StringBuilder sb= new StringBuilder();
		ArrayList<Integer> answer=new ArrayList<Integer>();
		
		int current=lis.size()-1;
		for(int i=n-1;i>=0;i--) {
			if(dp[i]==current) {
				answer.add(arr[i]);
				current--;
			}
		}
		sb.append(lis.size()+"\n");
		for(int i=answer.size()-1;i>=0;i--) {
			sb.append(answer.get(i)+" ");
		}
		//1 5 10 4  ���� ��쿡 ��ġ�� �ٲ� 4�� LIS �ȿ� ���Ե�
		bw.write(sb.toString());
		bw.flush();
		
		//lis�� ���������� arr�� ũ�� lis�� �ְ� dp�� �� lis index ����
		//������ ����Ž������ ������ ��ġ ã��
		//lis ��ġ�� ���� ��ü dp�� ��ü�� index ����
		
	}
	
	static int BS(ArrayList<Integer> array,int num,int start,int end) {
		
		//int index=0;
		while(start<end) {
			int mid=(start+end)/2;
			if(array.get(mid)>=num) {
				//index=mid;
				end=mid;
			}else {
				start=mid+1;
			}
		}
		return start;
	}
}
// ���� ����Ʈ : ���̳ʸ� ��ġ�� �߸��ƴ�.. ���� start �ؾߵǴµ� return mid�� �� �������ߴ�..