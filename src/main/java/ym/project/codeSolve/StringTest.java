package ym.project.codeSolve;

public class StringTest {

    public static void main(String[] args){

        String s = "11";         //true
        String s2 = "121";       //false
        String s3 = "122";      //true
        String s4 = "12211";      //false

        solution(s4);
    }

    public static boolean solution(String s){

        boolean answer = false;

        char[] c = s.toCharArray();

        /*
        * 문자열은 1과 2로만 구성
        * 1의 뒤는 항상 2가 존재해야만 함
        * 2의 뒤는 1 혹은 2가 존재할 수 있음
        * */
        for(int i=0; i<c.length; i++){
            if(Character.toString(c[i]).equals("1")){
                if(Character.toString(c[i]).equals("2")){
                    answer = true;
                }else{
                    answer = false;     //1
                }
            }else{
                answer = true;
            }
        }

        return answer;
    }
}
