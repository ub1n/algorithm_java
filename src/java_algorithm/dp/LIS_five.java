package java_algorithm.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class LIS_five {

	//NlogN으로 해야함 -> 이진탐색
	//lis라는 배열에 첫번쨰 수를 넣음
	//마지막 값보다 크면 넣음
	//새로운 값보다 마지막값보다 작으면 이진탐색 -> 새로운 값의 위치 갱신 (lowerbound 사용가능)
	// ex) 10 20 30에서 15 만남 -> 이진탐색을통해 15가 두번째임을 확인하고 두번째에 15를 넣음 , index에 2를 넣음
	
	//input  10 20 10 30 20 50 40
	//index  ->input[i]가 LIS에 들어가는 위치
	//index  0  1  0  2  1  3  2   
	
	//출력 : 맨뒤 부터 확인  if(index[i] == LISSize) 50 답으로 출력 , LISSize--
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
		//1 5 10 4  같은 경우에 위치가 바뀐 4가 LIS 안에 들어가게됨
		bw.write(sb.toString());
		bw.flush();
		
		//lis에 마지막보다 arr이 크면 lis에 넣고 dp에 들어간 lis index 저장
		//작으면 이진탐색으로 들어가야할 위치 찾음
		//lis 위치에 따라 교체 dp에 교체된 index 저장
		
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
// 삽질 포인트 : 바이너리 서치가 잘못됐다.. 리턴 start 해야되는데 return mid를 할 생각을했다..