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
		long[] dp=new long[max+1]; //cost에 따라 가질 수 있는 최대값을 저장할예정
		
		for(int i=0;i<n;i++) {
			apps[i]=new Apps(memory[i],c[i]);
		}
		
		Arrays.sort(apps);
		
		for(int i=0;i<n;i++) { //메모리를 cost가 낮은 순으로 돌면서
			
			//앞에서부터 하게되면 갱신된 값이 계속 쌓여서 생각한 값보다 더 커지게됨
			for(int j=max;j>=apps[i].cost;j--) { //현재 dp값과 현재 메모리 cost를 뺀 dp값에 현재 메모리값을 더한 값을 비교 
				dp[j]=Math.max(dp[j], dp[j-apps[i].cost]+apps[i].memory);
				//0부터할거면 dp[i(n)][j(max)]를 잡고 이전까지의 값 배열을 비교하는 식으로 해야함
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

//삽질 포인트 -> j를 cost부터 시작해서 max까지로 했다가 dp[j-apps[i].cost] 값이 현재 app을 통해(i를통해) 갱신된 값으로 비교해서 더 큰값이 나와버렸다..
