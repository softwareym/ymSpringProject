package project.ym;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

//2번
public class Solution {

    public static void main(String[] args) throws IOException {
       BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
       BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        SinglyLinkedList listHead = new SinglyLinkedList();
        int listHeadCount = Integer.parseInt(bufferedReader.readLine().trim());
        IntStream.range(0, listHeadCount).forEach(i -> {
            try {
                int listHeadItem = Integer.parseInt(bufferedReader.readLine().trim());

                listHead.insertNode(listHeadItem);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        SinglyLinkedListNode result = Result.deleteOdd(listHead.head);
//        SinglyLinkedListPrintHelper.printList(result, "\n", bufferedWriter);
        bufferedWriter.newLine();
        bufferedReader.close();
        bufferedWriter.close();
    }
}

class SinglyLinkedListNode {
    public int data;
    public SinglyLinkedListNode next;

    public SinglyLinkedListNode(int nodeData) {
        this.data = nodeData;
        this.next = null;
    }
}

class SinglyLinkedList {
    public SinglyLinkedListNode head;
    public SinglyLinkedListNode tail;

    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public void insertNode(int nodeData) {
        SinglyLinkedListNode node = new SinglyLinkedListNode(nodeData);

        if (this.head == null) {
            this.head = node;
        } else {
            this.tail.next = node;
        }

        this.tail = node;
    }
}

/*
class SinglyLinkedListPrintHelper {
    public static void printList(SinglyLinkedListNode node, String sep, BufferedWriter bufferedWriter) throws IOException {
        while (node != null) {
            bufferedWriter.write(String.valueOf(node.data));

            node = node.next;

            if (node != null) {
                bufferedWriter.write(sep);
            }
        }
    }
}
*/


class Result {

     /* Complete the 'deleteOdd' function below.
            *
            * The function is expected to return an INTEGER_SINGLY_LINKED_LIST_NODE.
            * The function accepts INTEGER_SINGLY_LINKED_LIST_NODE listHead as parameter.

     * For your reference:
            * SinglyLinkedListNode {
     *     int data;
     *     SinglyLinkedListNode next;
     * }
     */

    public static SinglyLinkedListNode deleteOdd(SinglyLinkedListNode listHead) {
        // Write your code here

        SinglyLinkedList evenHead = new SinglyLinkedList();
        while (listHead != null) {
            if(listHead.data%2==0){
                evenHead.insertNode(listHead.data);
            }
            listHead = listHead.next;
        }
        return evenHead.head;

    }

}
