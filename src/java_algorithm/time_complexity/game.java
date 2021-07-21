package java_algorithm.time_complexity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class game {
	//최악의 경우 98퍼일때 X번 만큼 게임을 해야 99퍼가됨
	//end를 x값 start 를 1
	// 승률이 안변하면 오른쪽
	// 승률이 너무 많이 변하면 왼쪽
	// 승률이 1만 차이나더라도 최소값을 구해야함
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
//float이 아닌 double로 해야 이상한 값이 안나옴 -> long쓰면 굳이 형변환 안해도 됨.. 100*(Y/X) 하면 안되는데 100*Y/X 하면된다...
