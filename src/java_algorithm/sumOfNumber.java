package java_algorithm;

import java.util.Scanner;

public class sumOfNumber {
	//2포인터를 이용 (슬라이딩 윈도우 방법)
	//  i 0 1 2 3 4 5 6 7 8 9
	//A[i]1 2 3 4 2 5 3 1 1 2
	//L 0 R 0 = 1 -> M보다 작으면
	//Right를 한칸 옆으로 옮김 값보다 커지면
	//Left(i)를 늘림
	// 값이 나오면 answer ++
	// 왼쪽을 옮기면 작아지므로 왼쪽하나 옮기고 오른쪽 옮김
	// 그런식으로 옮겨가며 값을 비교
	//왼쪽 혹은 오른쪽이 넘어가면 종료
	
	static int N;
	static int M;
	static int A[];
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		N=sc.nextInt();
		M=sc.nextInt();
		A=new int[N];
		for(int i=0;i<N;i++) {
			A[i]=sc.nextInt();
		}
		
		int lpoint=0;
		int rpoint=0;
		int current_sum=A[0];
		int answer=0;
		
		while(true) {
			if(current_sum==M) {
				answer++;
				rpoint++;
				if(rpoint==N) {
					break;
				}
				current_sum+=A[rpoint];
			}
			else if(current_sum<M) {
				rpoint++;
				if(rpoint==N) {
					break;
				}
				current_sum+=A[rpoint];
			}
			else if(current_sum>M) {
				if(lpoint<rpoint) {
					if(lpoint==N) {
						break;
					}
					current_sum-=A[lpoint];
					lpoint++;
				}else {
					rpoint++;
					if(rpoint==N) {
						break;
					}
					current_sum+=A[rpoint];
				}
			}
			
		}
		System.out.println(answer);
	}
}

//후기 : 놓친부분 -> 처음에 할때는 lpoint와 rpoint가 같으면서 sum이 M보다 작은 경우를 간과했다.
// 그리고 lpoint는 뺴준 이후 ++를 해야하는 것도 간과하였다.