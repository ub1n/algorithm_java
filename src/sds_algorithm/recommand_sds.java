package sds_algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class recommand_sds {
	static int N,K;
	static int[] inputs;
	static Person[] people;
	
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		
		N=sc.nextInt();
		K=sc.nextInt();
		
		inputs = new int[K];
		people = new Person[101];
		
		List<Person> list = new ArrayList<>();
		for (int i=0;i<K;i++) {
			int num=sc.nextInt();
			if(people[num] == null) {
				people[num] = new Person(num,0,0,false);
			}
			if(people[num].isIn == true) {
				people[num].count++;
			}else {
				if(list.size() == N) { //사진틀이 꽉차있으면
					Collections.sort(list); // 아래 comparable을 기준으로 정렬
					Person p = list.remove(0); //0번쨰 리스트 체거하며 지워질 사람을 받음
					p.isIn=false;
				}
				people[num].count=1;
				people[num].isIn = true;
				people[num].timeStamp = i;
				list.add(people[num]);
			}
		}
		
		Collections.sort(list, new Comparator<Person>() {
			@Override
			public int compare(Person p1,Person p2) {
				return p1.num - p2.num;
			}
		}); // 새로운  comparator을 기준으로 정렬
		
		for(Person l:list) {
			System.out.print(l.num+" ");
		}
	}
}

class Person implements Comparable<Person>{ // 정렬할 수 있는 객체임을 명시
	int num;
	int count;
	int timeStamp;
	boolean isIn;
	
	public Person(int num,int count,int timeStamp, boolean isIn) {
		this.num=num;
		this.count=count;
		this.timeStamp=timeStamp; // 언제 들어갔는지
		this.isIn=isIn;
	}
	
	@Override
	public int compareTo(Person person) {
		// -1 바꾸지않음
		int result = count - person.count;
		if(result == 0) {
			return timeStamp-person.timeStamp;
		}else {
			return result;
		}
		// 0 바꾸지 않음
		// 1 바꿈
	}

	@Override
	public String toString() {
		return "Person [num=" + num + ", count=" + count + ", timeStamp=" + timeStamp + ", isIn=" + isIn + "]";
	}
	
}
