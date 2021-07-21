package java_algorithm.data_structure;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class minheap {
//ArrayList로 트리 직접 구현, 힙을 직접 구현해보자
	//push, pop 을 구현해보자
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		Heap heap=new Heap();
		for(int i=0;i<N;i++) {
			int n=Integer.parseInt(br.readLine());
			if(n==0) {
				System.out.println(heap.pop());
			}else {
				heap.push(n);
			}
		}
		
	}
	
}
class Heap{
	ArrayList<Integer> heap;
	
	public Heap() {
		heap = new ArrayList<Integer>();
		heap.add(0);
	}
	
	public void push(int n) {
		heap.add(n);
		int current_index=heap.size()-1;
		while(true) {
			int last_index=current_index;
			current_index=current_index/2;
			if(current_index==0) {
				break;
			}
			if(heap.get(current_index)>n) {
				heap.set(last_index,heap.get(current_index));
				heap.set(current_index, n);
			}else {
				break;
			}
			
		}
	}
	
	public int pop() {
		if(heap.size() == 1) {
			return 0;
		}
		int m=heap.get(1);
		int last_index=heap.size()-1;
		int n=heap.get(last_index);
		heap.set(1, heap.get(last_index));
		heap.remove(last_index);
		int current_index=1;
		while(true) {
			int left_index=current_index*2;
			int right_index=current_index*2+1;
			
			if(left_index>=heap.size()) {
				break;
			}
			
			int change_index=left_index;
			if(right_index<heap.size()) {
				if(heap.get(left_index)>heap.get(right_index)) {
					change_index=right_index;
				}
			}
			if(heap.get(change_index)<n) {
				int temp=heap.get(change_index);
				heap.set(change_index,n);
				heap.set(current_index, temp);
				current_index=change_index;
			}else {
				break;
			}
			
			
		}
		return m;
	}
}
//논리가 꼬여버린.. 왼쪽만 남아있는 경우를 처음에 고려하지 않아 꼬였다.. 어찌어찌 했지만 93퍼에서 틀렸습니다가 나와서... 논리에 문제가 있는거같아 다시 짰다.
//오른쪽이 있을 경우 확인하는 방법으로.. 
//스캐너를 썼을때는 어떤거는 시간초과뜨고 어떤거는 통과했다... 버퍼리더를 사용하니 정답 메모리가 1/3으로 줄고 시간은 절반으로 주네..
/*
 * 
 * class Heap{
	ArrayList<Integer> heap;
	
	public Heap() {
		this.heap = new ArrayList<Integer>();
		this.heap.add(0);
	}
	public void push(int n) {
		this.heap.add(n);
		int current_index=this.heap.size()-1;
		while(true) {
			int last_index=current_index;
			current_index=current_index/2;
			if(current_index==0) {
				break;
			}
			if(heap.get(current_index)<n) {
				heap.set(last_index,heap.get(current_index));
				heap.set(current_index, n);
			}else {
				break;
			}
			
		}
	}
	
	public int pop() {
		int n=this.heap.get(1);
		int last_index=this.heap.size()-1;
		this.heap.set(1, this.heap.get(last_index));
		this.heap.remove(last_index);
		int current_index=1;
		while(true) {
			int left_index=current_index*2;
			int right_index=current_index*2+1;
			
			if(left_index>=this.heap.size()||right_index>=this.heap.size()) {
				break;
			}
			int left=this.heap.get(left_index);
			int right=this.heap.get(right_index);
			if(left>right && left>n) {
				this.heap.set(current_index, left);
				this.heap.set(left_index, n);
				current_index=left_index;
			}else if(right>left && right>n) {
				this.heap.set(current_index, right);;
				this.heap.set(right_index, n);
				current_index=right_index;
			}else {
				break;
			}
		}
		return n;
	}
}*/ //max heap
