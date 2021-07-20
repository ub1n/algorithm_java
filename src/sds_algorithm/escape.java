package sds_algorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class escape {
	static int[] mx= {-1,1,0,0};
	static int[] my = {0,0,-1,1};
	
	static int R,C;
	static char[][] map;
	static int[][] dp;
	static Queue<Point> queue;
	static boolean foundAnswer;
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		R=sc.nextInt();
		C= sc.nextInt();
		
		map=new char[R][C];
		dp = new int[R][C];
		queue = new LinkedList<>();
		
		Point st = null;
		for(int r=0; r<R;r++) {
			String line=sc.next();
			for (int c=0; c<C; c++) {
				map[r][c] = line.charAt(c);
				if (map[r][c] =='S') {
					st= new Point(r,c,'S');
				} else if (map[r][c] =='*') {
					queue.add(new Point(r,c,'*'));  //���� ���� �־���
				}
			}
		}
		
		//������ ���� -> ť�� ���������� ����
		queue.add(st);
		
		while(!queue.isEmpty()) {
			Point p=queue.poll();
			
			//1. ť���� ������
			//2. �������ΰ� (ifp==0)
			if(map[p.y][p.x] =='D') {
				System.out.println(dp[p.y][p.x]);
				foundAnswer = true;
				break;
			}
			//3. �� �� �ִ°��� ��ȸ for( ��,��,��,�Ʒ�)
			
			for (int i=0;i<4;i++) {
				int ty=p.y+my[i];
				int tx=p.x+mx[i];
				//4. ���� �ִ°�? if(���� ������ʰ� , .�̰ų� D�̰ų�)
				if(0<= ty && ty < R && 0<=tx && tx< C) { //map ���� ��
					if(p.type=='*') {
						if(map[ty][tx] == '.' || map[ty][tx]== 'S') {
							//üũ��
							map[ty][tx]='*';
							//ť�� �ֱ�
							queue.add(new Point(ty,tx,'*'));
						}
					}else { //����ġ
						if(map[ty][tx]=='.' || map[ty][tx]=='D') {
							if(dp[ty][tx] == 0) { //�湮���� ������
								//üũ��
								dp[ty][tx] = dp[p.y][p.x]+1;
								//ť���ֱ�
								queue.add(new Point(ty,tx,'S'));
							}
						}
					}
				}
				
			}
			
		}
		if (foundAnswer == false) {
			System.out.println("KAKTUS");
		}
	}
	
}

class Point {
	int y;
	int x;
	char type;
	
	public Point(int y,int x, char type) {
		super();
		this.y=y;
		this.x=x;
		this.type=type;
	}

	@Override
	public String toString() {
		return "Point [y=" + y + ", x=" + x + ", type=" + type + "]";
	}
	
	
}