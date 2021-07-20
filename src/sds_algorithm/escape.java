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
					queue.add(new Point(r,c,'*'));  //물을 먼저 넣어줌
				}
			}
		}
		
		//시작점 설정 -> 큐에 시작점들을 넣음
		queue.add(st);
		
		while(!queue.isEmpty()) {
			Point p=queue.poll();
			
			//1. 큐에서 꺼내옴
			//2. 목적지인가 (ifp==0)
			if(map[p.y][p.x] =='D') {
				System.out.println(dp[p.y][p.x]);
				foundAnswer = true;
				break;
			}
			//3. 갈 수 있는곳을 순회 for( 좌,우,위,아래)
			
			for (int i=0;i<4;i++) {
				int ty=p.y+my[i];
				int tx=p.x+mx[i];
				//4. 갈수 있는가? if(맵을 벗어나지않고 , .이거나 D이거나)
				if(0<= ty && ty < R && 0<=tx && tx< C) { //map 범위 안
					if(p.type=='*') {
						if(map[ty][tx] == '.' || map[ty][tx]== 'S') {
							//체크인
							map[ty][tx]='*';
							//큐에 넣기
							queue.add(new Point(ty,tx,'*'));
						}
					}else { //고슴도치
						if(map[ty][tx]=='.' || map[ty][tx]=='D') {
							if(dp[ty][tx] == 0) { //방문한적 없을때
								//체크인
								dp[ty][tx] = dp[p.y][p.x]+1;
								//큐에넣기
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