package java_algorithm.time_complexity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class game {
	//�־��� ��� 98���϶� X�� ��ŭ ������ �ؾ� 99�۰���
	//end�� x�� start �� 1
	// �·��� �Ⱥ��ϸ� ������
	// �·��� �ʹ� ���� ���ϸ� ����
	// �·��� 1�� ���̳����� �ּҰ��� ���ؾ���
	static long X,Y;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		X=Long.parseLong(st.nextToken());
		Y=Long.parseLong(st.nextToken());
		
		long win=100*Y/X;
		if(win==99 || win==100) {
			System.out.println(-1);
		}else {
			
			long start=1;
			long end=X;
			long mid=(start+end)/2;
			long result=X;
			
			while(start<=end) {
				long current_win=100*(Y+mid)/(X+mid);
				if(current_win>win) {
					result=Math.min(mid, result);
					end=mid-1;
					mid=(start+end)/2;
				}else {
					start=mid+1;
					mid=(start+end)/2;
				}
			}
			System.out.println(result);
		}
		
	}
}
//float�� �ƴ� double�� �ؾ� �̻��� ���� �ȳ��� -> long���� ���� ����ȯ ���ص� ��.. 100*(Y/X) �ϸ� �ȵǴµ� 100*Y/X �ϸ�ȴ�...
