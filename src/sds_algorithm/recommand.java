package sds_algorithm;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

public class recommand {
//기록[후보수] 로 1이면 들어가있고 0이면 안들어가있고 -> 탐색 시간 줄임
// 추천수랑 엮어서 클래스를 만들면 좋다 ->클래스에 추천수, 번호, 틀 유무를 넣어서 만듬
//클래스를 틀과 후보에 양쪽으로 넣어줌 
//양쪽에 넣어주더라고 후보에서 값하나를 바꿔주면 틀에 들어가있는 클래스도 값이 바뀜 -> 레퍼런스타입
	
	static Student[] picture;
	static Student[] students;
	static class comp implements Comparator<Student>{
		@Override
		public int compare(Student o1,Student o2) {
			if(o1.recommand_num-o2.recommand_num!=0) {
				return o1.recommand_num-o2.recommand_num;
			}
			return o1.count-o2.count;
		}
	}
	
	static class lastcomp implements Comparator<Student>{
		@Override
		public int compare(Student o1,Student o2) {
			if(o1==null || o2==null) {
				return 0;
			}
			return o1.student_num-o2.student_num;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N=sc.nextInt();
		int M= sc.nextInt();
		
		picture = new Student[N];
		students = new Student[M];
		for(int i=0;i<M;i++) {
			students[i] = new Student(0,i,false,M+10);
		}
		int count=0;
		int pcnt=0;
		for(int i=0;i<M;i++) {
			if(picture[picture.length-1]!=null) {
				M=M-i;
				break;
			}
			int rec=sc.nextInt();
			if(students[rec-1].is_picture) {
				students[rec-1].recommand_num+=1;
			}else {
				picture[pcnt]=students[rec-1];
				picture[pcnt].is_picture=true;
				picture[pcnt].recommand_num+=1;
				picture[pcnt].count=count;
				pcnt+=1;
			}
			if(i==M-1) {
				M=0;
			}
			count+=1;
			
		}
		while(M>0) {
			int rec=sc.nextInt();
			if(students[rec-1].is_picture) {
				students[rec-1].recommand_num+=1;
			}else {
				Arrays.sort(picture,new comp());
				picture[0].is_picture=false;
				picture[0].recommand_num=0;
				picture[0]=students[rec-1];
				students[rec-1].is_picture=true;
				students[rec-1].recommand_num+=1;
				students[rec-1].count=count;
			}
			count+=1;
			M=M-1;
		}
		Arrays.sort(picture,new lastcomp());
		for(int i=0;i<picture.length;i++) {
			if(picture[i]==null) {
				break;
			}
			System.out.print((picture[i].student_num+1)+" ");
		}
	}
}

class Student{
	int recommand_num;
	int student_num;
	boolean is_picture;
	int count;
	
	public Student(int renum,int stnum,boolean ispic,int count) {
		super();
		this.recommand_num=renum;
		this.student_num=stnum;
		this.is_picture=ispic;
		this.count=count;
	}

	@Override
	public String toString() {
		return "Student [recommand_num=" + recommand_num + ", student_num=" + student_num + ", is_picture=" + is_picture
				+ ", count=" + count + "]";
	}

	
	
}

//후기 : 맨처음에는 picture이 꽉 차지 않을경우를 고려하지않았고, 초반 단계에서 중복되어서 들어올 경우를 생각하지않았다.
// index outof bounds가 나오길래 뭐지 싶었는데 생각해보니 student num은 받은 값에 -1을 했어야했다.. (학생이름이 0부터 시작이 아니니...)