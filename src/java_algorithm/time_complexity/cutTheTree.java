package java_algorithm.time_complexity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class cutTheTree {
//�Ķ��Ʈ�� ��ġ - ���̳ʸ� ��ġ�� Ư�� ���ǿ� �°� ���°�
	//�ڸ� ���� ũ�Ⱑ ũ�� start�� mid�� mid�� �� �߰�����
	
	static int N;
	static long M;
	static int trees[];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Long.parseLong(st.nextToken());
		trees=new int[N];
		int end=0;
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			trees[i]=Integer.parseInt(st.nextToken());
			end=Math.max(trees[i],end);
		}
		int start=0;
		int mid=(start+end)/2;
		ArrayList<Integer> cuts=new ArrayList<Integer>();
		
		while(true) {
			if(end<start) {
				break;
			}
			long count=0;
			for(int i=0;i<N;i++) {
				if(trees[i]-mid>0) {
					count+=trees[i]-mid;
				};
			}
			if(count>=M) {
				cuts.add(mid);
				start=mid+1;
				mid=(start+end)/2;
			}else{
				end=mid-1;
				mid=(start+end)/2;
			}
		}
		System.out.println(Collections.max(cuts));
		
	}
}
