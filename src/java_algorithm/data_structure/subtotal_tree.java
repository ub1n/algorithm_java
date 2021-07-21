package java_algorithm.data_structure;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class subtotal_tree {
//long으로 해야함
	//인덱스 트리 문제
	static int S;
	static int N;
	static int M;
	static int K;
	static long nums[];
	static long tree[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		
		nums=new long[N];
		
		for(int i=0;i<N;i++) {
			nums[i] = Long.parseLong(br.readLine());
		}
		
		S=1;
		while(S<N) { //S 구하기
			S*=2;
		}
		tree = new long[S*2]; //리프를 제외하고 나머지 노드 수 = S-1 , 그러나 빈공간인 0이 있으므로 S*2
		initBU();
		
		for(int i=0;i<M+K;i++) {
			long a,c;
			int b;
			st = new StringTokenizer(br.readLine());
			a=Long.parseLong(st.nextToken());
			b=Integer.parseInt(st.nextToken());
			c=Long.parseLong(st.nextToken());
			
			if(a==1) {
				long diff=c-tree[S+b-1];
				update(1,S,1,b,diff);
			}else {
				System.out.println(query(1,S,1,b,c));
				
			}
		}
	}
	
	//바텀업 초기화 -> S부터시작해리프에 값을 채움
	//리프를 채웠으면 바로 위 노드는 맨끝에서 부터 채움(S-1부터 1씩 내려가면서 왼쪽자식 *2 오른쪽자식 *2+1로 위치 알아낸 이후 값을 더함)
	static void initBU() {  
		//Leaf에 값 반영
		for(int i=0;i<N;i++) {
			tree[S+i] = nums[i];
		}
		for(int i=S-1;i>0;i--) {
			tree[i]= tree[i*2]+tree[i*2+1];
		}
		//내부노드 채움
	}
	
	//
	static long query(int left,int right,int node,int queryLeft,long queryRight) {
		//연관이 없음 -> 결과에 영향이 없는 값 return
		if(queryRight < left|| right< queryLeft) {
			return 0;
		}
		// 판단 가능 -> 현재 노드 값 return
		else if(queryLeft <=left && right <= queryRight) {
			return tree[node];
		}
		// 판단불가,자식에게 위임, 자식에서 올라온 합을 return - 자식에게 위임할땐 재귀로
		else {
			int mid = (left+right)/2;
			long leftResult = query(left,mid,node*2,queryLeft,queryRight);
			long rightResult = query(mid+1,right,node*2+1,queryLeft,queryRight);
			
			return leftResult+rightResult;
		}
		
	}
	
	static void update(int left,int right,int node,int target,long diff) { //target-> 바꿀곳 diff-> 바꿀 값과의 차이
		//연관없음
		if(target < left || right < target) {
			return;
		}
		//연관 있음 -> 값을 update
		else {
			tree[node] +=diff;
			if(left!=right) {//내부노드라는 의미
				int mid = (left+right)/2;
				update(left,mid,node*2,target,diff);
				update(mid+1,right,node*2+1,target,diff);
			}
		}
	}
}
