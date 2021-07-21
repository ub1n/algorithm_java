package java_algorithm.data_structure;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class diamond_thef {
//���� ���� ������ ����, ���� ��� ������ ��
	//������ ���Ա������� ����
	//������ ����� ���� ������ ����
	//������ ���� 
	//���� �ѵ� ������ �켱���� ť�� ���� �������� ����
	
	static class comp implements Comparator<Jewelry>{
		@Override
		public int compare(Jewelry o1,Jewelry o2) {
			return o1.weight-o2.weight;
		}
	}
	static int Bags[];
	static Jewelry Jewelries[];
	static int N;
	static int K;
	static PriorityQueue<Jewelry> queue;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N= Integer.parseInt(st.nextToken());
		K= Integer.parseInt(st.nextToken());
		
		Jewelries=new Jewelry[N];
		for(int i=0;i<N;i++) {
			st= new StringTokenizer(br.readLine());
			Jewelries[i]=new Jewelry(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
		Bags=new int[K];
		for(int i=0;i<K;i++) {
			Bags[i]=Integer.parseInt(br.readLine());
		}
		Arrays.sort(Bags);
		Arrays.sort(Jewelries,new comp());
		
		queue=new PriorityQueue<Jewelry>();
		
		long answer=0;
		/*int bcount=0;
		int jcount=0;
		int bag_count=0;
		for(int i=0;i<N;i++) {
			if(count==K) {
				
				break;
			}
			if(Bags[count]>=Jewelries.get(i).weight) {
				queue.add(Jewelries.get(i));
			}else {
				if(queue.size()!=0) {
					answer+=queue.poll().price;
				}
				count++;
				bag_count++;
			}
		}
		
		while(true) {
			if(bcount==K ||jcount==N) {
				break;
			}
		}
		if(bag_count==K-1) {
			if(queue.size()!=0) {
				answer+=queue.poll().price;
			}
		}*/
		int jewelry_index=0;
		for(int i=0;i<K;i++) {
			while(true) {
				if(jewelry_index==N) {
					break;
				}
				if(Jewelries[jewelry_index].weight<=Bags[i]) {
					queue.add(Jewelries[jewelry_index++]);
				}else {
					break;
				}
			}
			if(queue.size()!=0) {
				answer+=queue.poll().price;
			}
		}
		System.out.println(answer);
		
	}
}

class Jewelry implements Comparable<Jewelry>{
	int weight;
	int price;
	
	public Jewelry(int weight,int price) {
		this.weight=weight;
		this.price=price;
	}
	
	@Override
	public int compareTo(Jewelry j) {
		return j.price-this.price;
	}
	
}
//Arraylist�� Ȱ���ؼ� remove�ϴ� ������ �ð��� ���� ��Ƹ����� ����... ���ʿ� scanner�� buffer�� ���� ���̸����� �ð��ʰ��� ���� �����̴ϱ�...