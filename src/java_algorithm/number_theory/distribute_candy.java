package java_algorithm.number_theory;

import java.util.Scanner;

public class distribute_candy {
	// X: 사탕수 Y: 봉지수
	// K명에게 X개씩 나눔 -> K*X+1 = C*Y
	// kx-cy=-1
	// gcd(a,b)*x= c 가 성립되지않으면 불가능
	// 일반해 공식 : x,y 를 구했을때 x가 얼마 올라가면 y가 얼마 빠지면 다른 해가 되는지
	// Y는 10의 9승 개까지 살수있음 사탕봉지는 음수가 될수 없음
	// X도 자연수
	// 일반해 공식을 통해 저 안에 들어가는 답을 찾아야함

	static int A, B, N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		// X:인당 나눠줄 사탕의 수
		// Y: 사탕 봉지의 수
		// A*x+1=B*y
		// Ax+By=C형태로 변환
		// -Ax+By=1 음수가 있으면 처치하기가 곤란
		// A(-x)+By=1 의 형태로 변환
		for (int i = 0; i < N; i++) {
			A = sc.nextInt();
			B = sc.nextInt();

			ExtendedGcdResult result = extendedGcd(A, B);

			// D=gcd(A,B)
			// Ax+By=C 일때 C%D ==0 이어야 해를 가질수 있음 : 배주항등식
			if (result.r != 1) { // C가 1이므로 1밖에 gcd 안됨
				System.out.println("IMPOSSIBLE");

			} else {
				
				// x0=s*C/D
				// y0=t*C/D
				long x0 = result.s;
				long y0 = result.t;

				// x=y0+B/D*k ->디오판토스 방정식
				// y=y0-A/D*k

				// k의 범위뽑기
				// x<0
				// ->x0+B*k<0 (D는 여기서 1이므로 없애도됨)
				// ->k< -x0/B

				// 0<y<=1e9
				// ->0<y-A*k<=1e9
				// -> -y<-A*k<=1e9-y

				// -> (y-1e9)/A<=k<y/A
				// k<-x0/B k는 오른쪽값 ceil-1

				// 우측 경계
				long kFromY = (long) Math.ceil((double) y0 / (double) A) - 1; //k<y/A
				long kFromX = (long) Math.ceil((double) -x0 / (double) B) - 1;   //k<-x0/B
				long k = Math.min(kFromY, kFromX);
				long y = y0- A*k;
				if(y<=1e9) {
					System.out.println(y);
				}else {
					System.out.println("IMPOSSIBLE");
				}
				
				/*long k=(long)Math.min(Math.ceil((double)result.t/(double)A), Math.ceil(((double)-result.s)/(double)B))-1;
				
				long res=result.t-k*A;
				while(res<0) {
					k=k-1;
					res=result.t-k*A;
				}
				System.out.println(res);*/
			}
		}
	}

	public static ExtendedGcdResult extendedGcd(long a, long b) {
		long s0 = 1, t0 = 0, r0 = a;
		long s1 = 0, t1 = 1, r1 = b;

		long temp;
		while (r1 != 0) {
			long q = r0 / r1; // 몫

			temp = r0 - q * r1; // r2
			r0 = r1;
			r1 = temp;

			temp = s0 - q * s1;
			s0 = s1;
			s1 = temp;

			temp = t0 - q * t1;
			t0 = t1;
			t1 = temp;

		}
		return new ExtendedGcdResult(s0, t0, r0);
	}
}

class ExtendedGcdResult {
	long s;
	long t;
	long r;

	public ExtendedGcdResult(long s, long t, long gcd) {
		this.s = s;
		this.t = t;
		this.r = gcd;
	}
}
// floor 함수
// ceil 함수

//k를 구하는 과정에서 double을 안써버림 