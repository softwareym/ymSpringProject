package ym.project.codeSolve;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.*;

public class PowerSet {
    private static boolean[] include;
    private static char[] charArrays;
    private static int legnth;
    private static List<String> arrayList = new ArrayList<>();

    public static void main(String[] args) {
        buildSubsequences("abc");
    }

    public static List<String> buildSubsequences(String s) {
        // Write your code here
        String[] S = s.split("");       //a,b,c를 배열 S에 넣기
        int n = S.length;
        boolean[] include = new boolean[n];     //배열 개수만큼 boolean 생성
        set(0,include,S,n);
        Collections.sort(arrayList);
        return  arrayList;
    }

    public static void set(int d ,boolean[] visited,String[] s,int n) {

        if (d == n) {
            String temp="";
            for (int i = 0; i < n; i++) {
                if (visited[i]) {
                    temp=temp+s[i];
                }
            }
            if(!"".equals(temp)){
                arrayList.add(temp);
            }
            return;
        }
        visited[d] = false;
        set(d + 1,visited,s,n);
        visited[d] = true;
        set(d + 1,visited,s,n);
    }

    //익명 클래스 : 재사용하는 용도가 아니고 일회성 혹은 상황에 따라 로직변동이 자주 바뀌는 클래스일 경우  별도의 클래스파일을 만들필요 없이 익명클래스로 처리
    //=>파라미터로 전달하는 익명클래스가 너무 길다. 그래서 이러한 것을 해결하기 위해서 드디어 람다(Lambda) 표현식이 등장

    //유추 : 입력받은 문자열 하나에 대해 앞에서부터/뒤에서부터 만들수 있는 모든 문자 조합을 배열에 넣음
   /* public static void main(String[] args) {
        String[] abcs = buildSubsequences("abc");

        for (String test : abcs) {          //배열 abcs을 하나씩 test라는 변수에 넣고 for문 반복
            System.out.print(test);
            System.out.println();
        }

        ArrayList<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));     //a,b,c 배열을 list에 넣음

        ArrayList<ArrayList<String>> result = findPowerSet(list, 0);

        for (ArrayList<String> re : result) {
            for (String integer : re) {
                System.out.print(integer);
            }
            System.out.println();
        }

    }

    static String[] buildSubsequences(String s) {
        List<String> list = new ArrayList<>();
        subsequenc(s, "", list);              //subsequenc("abc","",list);
        *//*
            // Stream : 자바8에 추가된 API
            "컬렉션", "배열"등의 저장 요소를 하나씩 순차적으로 참조하며 함수형 인터페이스(람다식)을 적용해 반복적으로 처리할 수 있도록 해주는 기능이다.
            (for문,if문등으로 처리하는 것보다 한줄 두줄로 간단하게 처리가 가능하다.)
        *//*

        String[] array = list.stream().toArray(String[]::new);                        //메소드 참조 방식 : 람다식을 작성하는 다른방법
        //String[] arrayLambda = list.stream().toArray(value -> new String[value]);   //람다 표현

        Arrays.sort(array);

        return array;

    }

    private static void subsequenc(String string1, String string2, List<String> list) {

        if (string1.isEmpty()) {
            if (!string2.isEmpty()) {
                list.add(string2);
            }
            return;
        }

        //string1.substring(1)  : 인덱스 1 위치부터 자름(0부터 시작)
        subsequenc(string1.substring(1), string2 + string1.charAt(0), list);
        //1.subsequenc("bc","a",list);    //2.subsequenc("c","ab",list);   //3.subsequenc("","abc",list);    //6.subsequenc("","ac",list);    //8.subsequenc("c","b",list);    //9.subsequenc("","bc",list);

        subsequenc(string1.substring(1), string2, list);
        //4.subsequenc("","ab",list);     //5.subsequenc("c","a",list);                                     //7. subsequenc("","a",list);      //10.subsequenc("b","b",list);
    }

    //ArrayList<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
    //     ArrayList<ArrayList<String>> result = findPowerSet(list, 0);
    static ArrayList<ArrayList<String>> findPowerSet(ArrayList<String> originalSet, int pos) {
        if (originalSet == null)
            return null;

        if (pos == originalSet.size()) {
            ArrayList<ArrayList<String>> powerSet = new ArrayList<ArrayList<String>>();
            ArrayList<String> subset = new ArrayList<String>();
            powerSet.add(subset);
            return powerSet; // return powerset with 1 element as [ [] ]
        }

        ArrayList<ArrayList<String>> subPowerSet = findPowerSet(originalSet, pos + 1);

        String currentVal = originalSet.get(pos);


        // copy each subset into a new one, and add current element to it
        ArrayList<ArrayList<String>> subPowerSet2 = new ArrayList<ArrayList<String>>();

        for (ArrayList<String> subset : subPowerSet) {
            ArrayList<String> subset2 = (ArrayList<String>) subset.clone();
            subset2.add(currentVal);
            subPowerSet2.add(subset2);
        }


        // merge the two sub-powersets into one
        subPowerSet.addAll(subPowerSet2);
        return subPowerSet;
    }*/

	/*static void powerset2(String s) {
		char[] arrays = s.toCharArray();
		for (int  i =1; i < (1 << arrays.length); i++) {
			for (int j = 1; j < arrays.length; j++) {
				if (i & (1 << j)) {
				}
			}
		}
	}*/

	/*static String[] buildSubsequences(String s) {
		include = new boolean[s.length()];
		charArrays = s.toCharArray();
		legnth = s.length();
		subsequences(0);
		return arrayList.toArray(new String[arrayList.size()]);
	}
	private static void subsequences(int k) {
		if (k == legnth) {
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < legnth ; i++) {
				if (include[i]) {
					builder.append(String.valueOf(charArrays[i]));
				}
			}
			if (!"".equals(builder.toString())) {
				arrayList.add(builder.toString());
			}
			return;
		}
		include[k] = false;
		subsequences(k + 1);
		include[k] = true;
		subsequences(k + 1);
	}*/
}
