package java_algorithm.data_structure;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class subtotal_tree {
//long���� �ؾ���
	//�ε��� Ʈ�� ����
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
		while(S<N) { //S ���ϱ�
			S*=2;
		}
		tree = new long[S*2]; //������ �����ϰ� ������ ��� �� = S-1 , �׷��� ������� 0�� �����Ƿ� S*2
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
	
	//���Ҿ� �ʱ�ȭ -> S���ͽ����ظ����� ���� ä��
	//������ ä������ �ٷ� �� ���� �ǳ����� ���� ä��(S-1���� 1�� �������鼭 �����ڽ� *2 �������ڽ� *2+1�� ��ġ �˾Ƴ� ���� ���� ����)
	static void initBU() {  
		//Leaf�� �� �ݿ�
		for(int i=0;i<N;i++) {
			tree[S+i] = nums[i];
		}
		for(int i=S-1;i>0;i--) {
			tree[i]= tree[i*2]+tree[i*2+1];
		}
		//���γ�� ä��
	}
	
	//
	static long query(int left,int right,int node,int queryLeft,long queryRight) {
		//������ ���� -> ����� ������ ���� �� return
		if(queryRight < left|| right< queryLeft) {
			return 0;
		}
		// �Ǵ� ���� -> ���� ��� �� return
		else if(queryLeft <=left && right <= queryRight) {
			return tree[node];
		}
		// �ǴܺҰ�,�ڽĿ��� ����, �ڽĿ��� �ö�� ���� return - �ڽĿ��� �����Ҷ� ��ͷ�
		else {
			int mid = (left+right)/2;
			long leftResult = query(left,mid,node*2,queryLeft,queryRight);
			long rightResult = query(mid+1,right,node*2+1,queryLeft,queryRight);
			
			return leftResult+rightResult;
		}
		
	}
	
	static void update(int left,int right,int node,int target,long diff) { //target-> �ٲܰ� diff-> �ٲ� ������ ����
		//��������
		if(target < left || right < target) {
			return;
		}
		//���� ���� -> ���� update
		else {
			tree[node] +=diff;
			if(left!=right) {//���γ���� �ǹ�
				int mid = (left+right)/2;
				update(left,mid,node*2,target,diff);
				update(mid+1,right,node*2+1,target,diff);
			}
		}
	}
}
