package ym.project.codeSolve;

/*public class TableRound {

    public static void main(String[] args){
        int[][] a = {{0,0,0,0}, {0,1,1,0}, {0,0,1,0}, {0,0,0,0}};       //8
        int[][] a2 = {{0,0,0}, {0,1,0}, {0,0,0}};       //4
        int[][] a3 = {{0,0,0,0,0}, {0,1,1,1,0}, {0,1,0,1,0},{0,1,1,1,0},{0,0,0,0,0}};       //16

        solution(a2);

    }

    public static int solution(int[][] a){
        int answer = 0;
        //값이 0일 경우에만 십자(상하좌우)로 체크하면서 1이 있을 경우 각 +1씩 카운트
        for(int i=0; i<a.length; i++){
            for(int j=0; j<a[i].length; j++){
                System.out.println("i : " + i + ", j : " + j + " / 값 : " + a[i][j]);
                if(a[i][j] == 0){
                    int lastindex = a[i].length-1;
                    //맨첫번째줄  => a[i-1][j] 불가
                    if(i == 0){
                        if(j == 0){     //첫번째열
                            if(a[i+1][j] == 1)  answer++;
                            if(a[i][j+1] == 1 ) answer++;
                        }else if(j == lastindex){       //끝열
                            if(a[i+1][j] == 1)  answer++;
                            if(a[i][j-1] == 1)  answer++;
                        }else{
                            if(a[i+1][j] == 1)  answer++;
                            if(a[i][j+1] == 1)  answer++;
                            if(a[i][j-1] == 1)  answer++;
                        }
                    }else if(i == a.length-1){                   //맨끝줄    => a[i+1][j] 불가
                        if(j == 0){                      //첫번째열
                            if(a[i-1][j] == 1)  answer++;
                            if(a[i][j+1] == 1)  answer++;
                        }else if(j == lastindex){        //끝열
                            if(a[i-1][j] == 1)  answer++;
                            if(a[i][j-1] == 1)  answer++;
                        }else{
                            if(a[i-1][j] == 1)  answer++;
                            if(a[i][j+1] == 1)  answer++;
                            if(a[i][j-1] == 1)  answer++;
                        }
                    }else{                                      //중간줄
                        if(j == 0){                             //첫번째열
                            if(a[i-1][j] == 1)  answer++;
                            if(a[i+1][j] == 1)  answer++;
                            if(a[i][j+1] == 1)  answer++;

                        }else if(j == lastindex){               //끝열
                            if(a[i-1][j] == 1)  answer++;
                            if(a[i+1][j] == 1)  answer++;
                            if(a[i][j-1] == 1)  answer++;
                        }else{                                  //중간열
                            if(a[i-1][j] == 1)  answer++;
                            if(a[i+1][j] == 1)  answer++;
                            if(a[i][j+1] == 1)  answer++;
                            if(a[i][j-1] == 1)  answer++;
                        }
                    }
                }
            }
        }
        System.out.println(answer);
        return  answer;
    }
}*/


public class TableRound {
    public static void main(String[] args) {
        //String S = "0000;0110;0010;0000";

        String S = "00000;01110;01010;01110;00000";
        System.out.println(solution(S));
    }

    public static int solution(String S){
        String[] temp = S.split(";");
        int size = temp.length;


        int[][] arr = new int[size][size];

        // arr 세팅
        for(int i=0;i<size;i++){
            char[] ch = temp[i].toCharArray();
            int j = 0;
            for(char c : ch){
                arr[i][j++] = Integer.parseInt(String.valueOf(c));
            }
        }

        int round = 0;

        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(arr[i][j] == 0){
                    int up = (i-1);
                    // up
                    if(up >= 0 && up<size && arr[up][j] == 1){
                        round++;
                    }
                    int down = (i+1);
                    // down
                    if(down >= 0 && down<size && arr[down][j] == 1){
                        round++;
                    }
                    int left = (j-1);
                    // left
                    if(left >= 0  && left<size && arr[i][left] == 1){
                        round++;
                    }
                    int right = (j+1);
                    // right
                    if(right >= 0  && right<size && arr[i][right] == 1){
                        round++;
                    }
                }
            }
        }


        return round;
    }

}
