package java_algorithm.number_theory;

import java.util.Scanner;

public class distribute_candy {
	// X: ������ Y: ������
	// K���� X���� ���� -> K*X+1 = C*Y
	// kx-cy=-1
	// gcd(a,b)*x= c �� �������������� �Ұ���
	// �Ϲ��� ���� : x,y �� �������� x�� �� �ö󰡸� y�� �� ������ �ٸ� �ذ� �Ǵ���
	// Y�� 10�� 9�� ������ ������� ���������� ������ �ɼ� ����
	// X�� �ڿ���
	// �Ϲ��� ������ ���� �� �ȿ� ���� ���� ã�ƾ���

	static int A, B, N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		// X:�δ� ������ ������ ��
		// Y: ���� ������ ��
		// A*x+1=B*y
		// Ax+By=C���·� ��ȯ
		// -Ax+By=1 ������ ������ óġ�ϱⰡ ���
		// A(-x)+By=1 �� ���·� ��ȯ
		for (int i = 0; i < N; i++) {
			A = sc.nextInt();
			B = sc.nextInt();

			ExtendedGcdResult result = extendedGcd(A, B);

			// D=gcd(A,B)
			// Ax+By=C �϶� C%D ==0 �̾�� �ظ� ������ ���� : �����׵��
			if (result.r != 1) { // C�� 1�̹Ƿ� 1�ۿ� gcd �ȵ�
				System.out.println("IMPOSSIBLE");

			} else {
				
				// x0=s*C/D
				// y0=t*C/D
				long x0 = result.s;
				long y0 = result.t;

				// x=y0+B/D*k ->������佺 ������
				// y=y0-A/D*k

				// k�� �����̱�
				// x<0
				// ->x0+B*k<0 (D�� ���⼭ 1�̹Ƿ� ���ֵ���)
				// ->k< -x0/B

				// 0<y<=1e9
				// ->0<y-A*k<=1e9
				// -> -y<-A*k<=1e9-y

				// -> (y-1e9)/A<=k<y/A
				// k<-x0/B k�� �����ʰ� ceil-1

				// ���� ���
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
			long q = r0 / r1; // ��

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
// floor �Լ�
// ceil �Լ�

//k�� ���ϴ� �������� double�� �Ƚ���� 