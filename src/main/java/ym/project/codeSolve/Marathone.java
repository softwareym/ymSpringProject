package ym.project.codeSolve;

import java.util.HashMap;
//참가자중 완주하지 못한 자 stirng 뽑기/ 동명이인 가능 / 한명빼고 다 완주함
public class Marathone {


    public static void main(String[] args) {

        String[] participant = {"leo", "kiki", "eden"};
        String[] completion = {"eden", "kiki" };
        //leo

     /*   String[] participant = {"kiki", "kiki","eden", "leo"};
        String[] completion = {"eden", "kiki", "kiki"};*/
        //leo

       /* String[] participant = {"kiki", "kiki","eden"};
        String[] completion = {"eden", "kiki",};*/
        //kiki

        solution(participant, completion);

    }
    public static String solution(String[] participant, String[] completion) {
        String answer = "";

             int pLen = participant.length;
        int cLen = completion.length;

        HashMap<String, Object> map = new HashMap<>();

        //참가자에 대한 맵 완성
        for(String p : participant){
            if(map.get(p) == null){
                map.put(p, 1);
            }else{
                map.put(p, (int)map.get(p)+1);
            }
        }

        int mapCnt = 0;

        for(String c : completion){
            mapCnt = (int) map.get(c);  //참가자수
            if(mapCnt > 0){             //참가자가 존재하면
                map.put(c, mapCnt-1);   //-1
            }
        }

        for (String key : map.keySet()){
            if((int) map.get(key) > 0){
                answer = key;
            }
        }

        System.out.println(answer);
        return answer;
    }
}
