package ym.project.codeSolve;

import java.util.ArrayList;
import java.util.List;

/*public class ValidDataCopy {

    public static final int ARR_SIZE = 8;

    public static void main(String[] args){
*//*
        4; 1 30 0 6 0 2 1 3
        0; 0 2 0 4 1 3 0 0
*//*
        int pointer = 4;
        int[] arr = {1,30,0,6,0,2,1,3};

        solution(pointer, arr);
    }

    public static String solution(int p, int[] arr){
        String answer = null;
        //0 + 포인터 / 1 + 유효값
        //유효한 값이 나올때까지 반복[1이 나오면 반복문 종료]

       ArrayList<Integer> arrList = new ArrayList<>(ARR_SIZE);
       ArrayList<Integer> answerList = new ArrayList<>(ARR_SIZE);

       for(Integer a : arr){
           arrList.add(a);
       }

       int nextIndex = 0;

       for(int i=0; i < arrList.size(); i++){
           if(i == 0){
               if(arrList.get(p) == 0){
                   answerList.add(arrList.get(p));
                   answerList.add(arrList.get(p+1));
                   nextIndex = arrList.get(p+1);                 //다음 주소
               }else if(arrList.get(p) == 1){
                   answerList.add(arrList.get(p));
                   answerList.add(arrList.get(p+1));
               }else{
                   for(int j=0; j<ARR_SIZE; j++)   answerList.add(0);
               }
           }else{
               if( nextIndex != 9 && arrList.get(nextIndex) == 0){
                   answerList.add(arrList.get(nextIndex));
                   answerList.add(answerList.size()+1);          //answerList에 다음 주소 입력
                   nextIndex = arrList.get(nextIndex+1);

               }else if(nextIndex != 9 && arrList.get(nextIndex) == 1){
                   answerList.add(arrList.get(nextIndex));
                   answerList.add(arrList.get(nextIndex+1));
                   nextIndex = 9;           //종료
                   for(int j=0; j< ARR_SIZE - answerList.size(); j++)  answerList.add(0);

               }else{
                   for(int j=0; j< ARR_SIZE - answerList.size(); j++)  answerList.add(0);
               }
           }
       }
        return answer;
    }
}*/

public class ValidDataCopy {
    public static final int POINT_SIZE = 8 ;

    public static void main(String[] args) {
        String S = "4;1 30 0 6 0 2 1 3";
        solution(S);
    }

    public static void solution(String S){
        String[] temp = S.split(";");
        int start = Integer.parseInt(temp[0]);
        int size = POINT_SIZE;

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");

        String[] standardString = temp[1].split(" ");
        int[] standardInt =  new int[size];
        for(int i=0;i<size;i++){
            standardInt[i] = Integer.parseInt(standardString[i]);
        }

        int[] copy = new int[size];
        int index = 0;
        do{
            int firstNum = standardInt[start];
            if(firstNum == 0){
                copy[index++]=standardInt[start];
                copy[index++]=standardInt[start+1];
                start = standardInt[start+1];
            }else{
                copy[index-1] = index;
                copy[index++]=standardInt[start];
                copy[index++]=standardInt[start+1];
                start = -1;
            }
        }while(start != -1);

        System.out.println(copy);
        StringBuilder sb = new StringBuilder("0;");
        for(int i : copy){
            sb.append(String.valueOf(i));
        }

        System.out.println(sb.toString());


    }
}