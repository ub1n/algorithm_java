package java_algorithm.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class app {

	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		
		st=new StringTokenizer(br.readLine());
		int[] memory=new int[n];
		int[] c=new int[n];
		for(int i=0;i<n;i++) {
			memory[i]=Integer.parseInt(st.nextToken());
		}
		st=new StringTokenizer(br.readLine());
		int max=0;
		for(int i=0;i<n;i++) {
			c[i]=Integer.parseInt(st.nextToken());
			max+=c[i];
		}
		
		Apps[] apps=new Apps[n];
		long[] dp=new long[max+1]; //cost�� ���� ���� �� �ִ� �ִ밪�� �����ҿ���
		
		for(int i=0;i<n;i++) {
			apps[i]=new Apps(memory[i],c[i]);
		}
		
		Arrays.sort(apps);
		
		for(int i=0;i<n;i++) { //�޸𸮸� cost�� ���� ������ ���鼭
			
			//�տ������� �ϰԵǸ� ���ŵ� ���� ��� �׿��� ������ ������ �� Ŀ���Ե�
			for(int j=max;j>=apps[i].cost;j--) { //���� dp���� ���� �޸� cost�� �� dp���� ���� �޸𸮰��� ���� ���� �� 
				dp[j]=Math.max(dp[j], dp[j-apps[i].cost]+apps[i].memory);
				//0�����ҰŸ� dp[i(n)][j(max)]�� ��� ���������� �� �迭�� ���ϴ� ������ �ؾ���
				// dp[i][j]=dp[i][j],dp[i-1][j-cost]+memory
			}
		}
		
		for(int i=0;i<=max;i++) {
			if(dp[i]>=m) {
				System.out.println(i);
				break;
			}
		}

		
	}
	
}

class Apps implements Comparable<Apps>{
	int memory,cost;

	@Override
	public int compareTo(Apps arg0) {
		// TODO Auto-generated method stub
		return this.cost-arg0.cost;
	}
	
	public Apps(int memory,int cost) {
		this.cost=cost;
		this.memory=memory;
	}
	
}

//���� ����Ʈ -> j�� cost���� �����ؼ� max������ �ߴٰ� dp[j-apps[i].cost] ���� ���� app�� ����(i������) ���ŵ� ������ ���ؼ� �� ū���� ���͹��ȴ�..
