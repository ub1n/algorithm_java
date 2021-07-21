package java_algorithm;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

public class recommand {
//���[�ĺ���] �� 1�̸� ���ְ� 0�̸� �ȵ��ְ� -> Ž�� �ð� ����
// ��õ���� ��� Ŭ������ ����� ���� ->Ŭ������ ��õ��, ��ȣ, Ʋ ������ �־ ����
//Ŭ������ Ʋ�� �ĺ��� �������� �־��� 
//���ʿ� �־��ִ���� �ĺ����� ���ϳ��� �ٲ��ָ� Ʋ�� ���ִ� Ŭ������ ���� �ٲ� -> ���۷���Ÿ��
	
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

//�ı� : ��ó������ picture�� �� ���� ������츦 ��������ʾҰ�, �ʹ� �ܰ迡�� �ߺ��Ǿ ���� ��츦 ���������ʾҴ�.
// index outof bounds�� �����淡 ���� �;��µ� �����غ��� student num�� ���� ���� -1�� �߾���ߴ�.. (�л��̸��� 0���� ������ �ƴϴ�...)