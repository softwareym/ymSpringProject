package ym.project.codeSolve;

public class LinkedList {

    private Node head, tail;  // head, tail을 가르키는 Node
    int size;

    class Node{ // 리스트에 들어갈 Node 클래스
        int data;
        Node next;

        Node(int data){
            this.data = data;
        }
    }

    public void addfirst(int a){  // 리스트 맨 앞에 삽입
        Node temp = new Node(a);
        if(head == null){
            head = temp;
            tail = temp;
        }
        else{
            temp.next = head;
            head = temp;
        }
        size++;
    }

    public void add(int a, int index){ // 리스트 중간에 삽입
        if(index > size){
            return;
        }
        Node temp1 = head;
        Node temp2 = new Node(a);

        for(int i = 0; i < index-1; i++){
            temp1 = temp1.next;
        }
        temp2.next = temp1.next;
        temp1.next = temp2;

        size++;
    }

    public void addlast(int a){ // 리스트 맨 마지막에 삽입0
        Node temp = new Node(a);
        if(tail == null){
            addfirst(a);
        }
        else{
            tail.next = temp;
            tail = temp;
            size++;
        }
    }

    public String toString(){
        Node temp = head;
        String s = "";

        while(temp.next != null){
            s += temp.data + " ";
            temp = temp.next;
        }
        return s;
    }

    public static void main(String[] args) {
        LinkedList ll = new LinkedList();

        ll.addfirst(10);
        ll.addfirst(15);
        ll.add(3, 1);
        ll.add(4, 2);
        ll.addlast(30);
    }
}
