package java_algorithm.basic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class maximum_value {

	static int result=0;
	static int[] arr;
	static boolean[] visited;
	static int n;
	static int[] tarr;
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		arr=new int[n];
		tarr=new int[n];
		visited=new boolean[n];
		StringTokenizer st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		back(0);
		System.out.println(result);
		
	}
	
	static void back(int count) {
		if(count==n) {
			int tmp=0;
			for(int i=0;i<n-1;i++) {
				tmp+=Math.abs(tarr[i]-tarr[i+1]);
			}
			result=Math.max(result, tmp);
			return;
		}
		
		for(int i=0;i<n;i++) {
			if(visited[i]==true) {
				continue;
			}
			tarr[count]=arr[i];
			visited[i]=true;
			back(count+1);
			visited[i]=false;
		}
		
		
	}
	
	
}

