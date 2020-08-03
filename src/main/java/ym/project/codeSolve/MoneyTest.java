package ym.project.codeSolve;

public class MoneyTest {

    public static void main(String[] args){

        int[] k = {100, 300, 10, 0, 40, 0, 0, 70, 65};
        int[] l = {40, 300, 20, 10, 10, 20, 100, 10, 0};
        solution(k,l);
    }

    public static int[] solution(int[] k, int[] l){
        int[] answer = new int[k.length];

        //k >= l 일 경우 k-l 값 출력 (같을 경우도 포함)
        //k < l 일 경우 l-k 값을 다음 index의 B값에 더함
        for(int i=0; i<k.length; i++){
            if(k[i] >= l[i]){
                answer[i] = k[i]-l[i];
            }else{
                l[i+1] = l[i+1] + (l[i]-k[i]);
                answer[i] = 0;
            }
        }
        return answer;
    }
}
