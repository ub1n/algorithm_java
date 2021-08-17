package java_algorithm.graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class professor_not_wait {

	//���ݱ��� �� ����� �������� ����� ���� �ְ� ���Ҽ��� �ִ� - union, find
	//parent[i](��������)���� ���̸� ������ dist[i]
	//union�� ĥ�� �� ����
	//���� �׷��̶�� ������ dist�� ���ָ� ��
	
	
	//pa+dist[a] =a
	//a+w = b
	// b= pb+dist[b]
	
	//pa + dist[a] = b-w
	//pa+dist[a] = pb+dist[b] -w
	// pa+(dist[a]-dist[b]+w)=pb
	static int[] parent;
	static long[] dist;
	static int find(int a) {
		if(parent[a]==a) {
			return a;
		}
		int pa=find(parent[a]);
		dist[a]+=dist[parent[a]];  //����κ� �߻����غ���!
		
		return parent[a]=pa;
	}
	
	static void union(int a,int b,long w) {
		int pa=find(a);
		int pb=find(b);
		if(pa==pb) {
			return;
		}
		dist[pb]=dist[a]-dist[b]+w;
		parent[pb]=pa;
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		while(true) {
			st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());
			parent=new int[n+1];
			dist=new long[n+1];
			if(n==0 && m==0) {
				break;
			}
			for(int i=1;i<=n;i++) {
				parent[i]=i;
			}
			
			for(int i=0;i<m;i++) {
				st=new StringTokenizer(br.readLine());
				String question=st.nextToken();
				if("!".equals(question)) {
					int a=Integer.parseInt(st.nextToken());
					int b=Integer.parseInt(st.nextToken());
					int c=Integer.parseInt(st.nextToken());
					union(a,b,c);
				}else {
					int a=Integer.parseInt(st.nextToken());
					int b=Integer.parseInt(st.nextToken());
					if(find(a)!=find(b)) {
						sb.append("UNKNOWN\n");
					}else {
						sb.append(dist[b]-dist[a]+"\n");
					}
				}
			}
		}
		bw.write(sb.toString());
		bw.flush();
		
	}
}

//https://subbak2.tistory.com/59
//���� ����Ʈ : parent[pb]=pa�� parent[pa]=pb�� �ϸ� �ȵȴ�. b�� ����������
//ó���� i�� parent�� �ʱ�ȭ�� ���ߴ�.
