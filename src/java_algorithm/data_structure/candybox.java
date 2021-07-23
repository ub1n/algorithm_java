package java_algorithm.data_structure;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class candybox {
//indexed tree
	//1 맛 두개를 넣으면 1번리프노드에 2 추가, 위에부모도 업데이트
	//3 맛 세개를 넣으면 3번 리프노드에 3 추가, 위에부모도 업데이트
	// 즉, 루트노드는 총 사탕의수, 부모는 해당하는 사탕의 수를 가지고 있음
	// query를 탑다운으로 날려서 query(left,right,node,target)
	// 사탕의맛은 리프노드의 left,right가 가지고있음
	
	static int S;
	static int N;
	static int M;
	static int K;
	static long nums[];
	static long tree[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N=1000000;
		
		
		S=1;
		while(S<N) { //S 구하기
			S*=2;
		}
		tree = new long[S*2]; //리프를 제외하고 나머지 노드 수 = S-1 , 그러나 빈공간인 0이 있으므로 S*2
		initBU();
		
		M=Integer.parseInt(st.nextToken());
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a= Integer.parseInt(st.nextToken());
			int b= Integer.parseInt(st.nextToken());
			if(a==2) {
				long c= Long.parseLong(st.nextToken());
				if(c==-1) {
					long diff=-(tree[S+b-1]);
					update(1,S,1,b,diff);
				}else {
					update(1,S,1,b,c);
				}
			}else {
				int candy=query(1,S,1,b);
				System.out.println(candy);
				update(1,S,1,candy,-1);
			}
		}
		
		
		
	}
	static void initBU() {
		for(int i=0;i<N;i++) {
			tree[i+S]=0;
		}
		for(int i=S-1;i>0;i--) {
			tree[i]=tree[i*2]+tree[i*2+1];
		}
	}
	
	static void update(int left,int right,int node,int target,long diff) {
		if(target<left || target>right) {
			return;
		}
		else {
			tree[node]+=diff;
			if(left!=right) {
				int mid=(left+right)/2;
				update(left,mid,node*2,target,diff);
				update(mid+1,right,node*2+1,target,diff);
			}
		}
	}
	
	static int query(int left,int right,int node,long target) {
		/*if(tree[node]<target) {
			return 0;
		}else if(left==right) {
			return left;
		}else {
			int mid=(left+right)/2;
			int leftResult=query(left,mid,node*2,target);
			int rightResult=query(mid+1,right,node*2+1,target-tree[node*2]);
			if(leftResult==0) {
				return rightResult;
			}else {
				return leftResult;
			}
		}*/
		if(left==right) {
			return left;
		}
		int mid=(left+right)/2;
		if(tree[node*2]>=target) {
			return query(left,mid,node*2,target);
		}else {
			return query(mid+1,right,node*2+1,target-tree[node*2]);
		}
	}
}
// 논리식이 잘못된.. 오른쪽일 경우 target을 왼쪽 자식 숫자만큼 뺴줬어야 한다는걸 지나쳤다..
