package ym.project.codeSolve;

import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveDuplicateString {

    public static void main(String[] args) {

      //문제 : 주어진 문자열 s에서 중복되는 k개의 개수만큼 연속되는 동일한 문자를 더이상 제거 할수 없을때까지 제거하고 남은 최종 문자열을 반환
      //  String s = "abbcdd";         int k = 2;        //ac
      //  String s = "abcd";         int k = 2;        //abcd
        String s = "deeedbbcccbdaa";         int k = 3;        //aa
      //  String s = "pbbcggttciiippooaais";         int k = 2;        //ps

        solution(s,k);

    }

    //큐 : 선입선출 - 먼저들어온것 먼저 처리
    //스택(=Deque) : 후입선출(LIFO) - 가장 나중에 저장된(push) 데이터가 가장 먼저 인출(pop)되는 구조
    // => 빠른 스택을 구현하고 싶다면 Deque 인터페이스를 구현한 ArrayDeque 클래스를 사용 => pop 반환및제거 : peek 반환 : push 저장
    // => Deque : 양쪽 끝에서 삽입과 삭제가 모두 발생할 수 있는 큐 /어떻게 사용하느냐에 따라 큐와 스택이 모두 될 수 있음
    //http://tcpschool.com/java/java_collectionFramework_stackQueue
    //https://opensourcedev.tistory.com/3
    public static String solution(String s, int k) {
        String result = "";

        Deque<Node> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {

            if (!stack.isEmpty() && stack.peek().ch == c) {         //stack.peek() 입력된 stack 중 가장 방금 넣은 것을 반환
                ++stack.peek().count;
                if (stack.peek().count == k) {                    //연속된 문자열일 경우 해당하는 stack ch의 count를 +1 / 해당 카운트가 k와 같으면  pop()으로 stack에서 제거
                    stack.pop();
                }
            } else {
                stack.push(new Node(c,1));
            }
        }

        StringBuilder ans = new StringBuilder();
        while (!stack.isEmpty()) {
            Node curr = stack.pollLast();                           //Deque 인터페이스의 pollLast(); : 뒤에서 값을 꺼내어 제거한 후 curr에 넣음
            for (int i = 0; i < curr.count; i++) {
                ans.append(curr.ch);                                //stack에 들어있었던 count만큼 문자열 더함
            }
        }

        System.out.println(ans.toString());
        result = ans.toString();

        return result;

    }

    public static class Node{
        char ch;
        int count;

        Node(char ch, int count){
            this.ch = ch;
            this.count = count;
        }
    }
}


/*
Given a string s, a k duplicate removal consists of choosing k adjacent and equal letters from s
and removing them causing the left and the right side of the deleted substring to concatenate together.
We repeatedly make k duplicate removals on s until we no longer can.
Return the final string after all such duplicate removals have been made.
It is guaranteed that the answer is unique.
Example 1:
Input: s = "abcd", k = 2
Output: "abcd"
Explanation: There's nothing to delete.
Example 2:
Input: s = "deeedbbcccbdaa", k = 3
Output: "aa"
Explanation:
First delete "eee" and "ccc", get "ddbbbdaa"
Then delete "bbb", get "dddaa"
Finally delete "ddd", get "aa"
Example 3:
Input: s = "pbbcggttciiippooaais", k = 2
Output: "ps"
Constraints:
    1. 1 <= s.length <= 10^5
    2. 2 <= k <= 10^4
    3. s only contains lower case English letters.
*/