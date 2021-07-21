package java_algorithm;

import java.util.Scanner;

public class sumOfNumber {
	//2�����͸� �̿� (�����̵� ������ ���)
	//  i 0 1 2 3 4 5 6 7 8 9
	//A[i]1 2 3 4 2 5 3 1 1 2
	//L 0 R 0 = 1 -> M���� ������
	//Right�� ��ĭ ������ �ű� ������ Ŀ����
	//Left(i)�� �ø�
	// ���� ������ answer ++
	// ������ �ű�� �۾����Ƿ� �����ϳ� �ű�� ������ �ű�
	// �׷������� �Űܰ��� ���� ��
	//���� Ȥ�� �������� �Ѿ�� ����
	
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

//�ı� : ��ģ�κ� -> ó���� �Ҷ��� lpoint�� rpoint�� �����鼭 sum�� M���� ���� ��츦 �����ߴ�.
// �׸��� lpoint�� ���� ���� ++�� �ؾ��ϴ� �͵� �����Ͽ���.