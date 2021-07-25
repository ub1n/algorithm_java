package java_algorithm.number_theory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class make_password {

	//�Ҽ� ����Ʈ �����
	//2~k ���� �Ҽ��� �������� bad
	//�ٵ� ��ȣ P�� ���� ��û Ŀ�� String���� , String �������� �������ؾߵ�
	public static boolean isMeasure(String p,int n) {
		String temp="";
		for(int i=0;i<p.length();i++) {
			temp+=p.charAt(i);
			int int_temp=Integer.parseInt(temp);
			if(int_temp>=n) {
				temp=Integer.toString(int_temp%n);
			}
		}
		if(Integer.parseInt(temp)==0) {
			return true;
		}else {
			return false;
		}
	}
	static int MAX=1000000;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		String P = st.nextToken();
		int K=Integer.parseInt(st.nextToken());
		ArrayList<Integer> nums=new ArrayList<Integer>();
		boolean check[]=new boolean[MAX+1];
		for(int i=2;i<=MAX;i++) {
			if(!check[i]) {
				nums.add(i);
				for(int j=i*2;j<=MAX;j+=i) {
					check[j]=true;
				}
			}
		}
		int result=-1;
		for(int i=0;i<nums.size();i++) {
			int num=nums.get(i);
			if(num>K) {
				break;
			}
			if(isMeasure(P,num)) {
				result=num;
				//System.out.println("BAD "+num);
				return;
			}
			
		}
		/*for(int i=2;i<=K;i++) {
			if(i%2!=0&&i%3!=0&&i%5!=0&&i%7!=0) {
				if(isMeasure(P,i)) {
					System.out.println("BAD "+i);
					return;
				}
			}
		}*/
		//System.out.println("GOOD");
		if(result==-1) {
			System.out.println("GOOD");
		}else {
			System.out.println("BAD "+result);
		}
	}
}
