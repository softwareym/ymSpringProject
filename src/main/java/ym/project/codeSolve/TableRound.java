package ym.project.codeSolve;

public class TableRound {

    public static void main(String[] args){
        int[][] a = {{0,0,0,0}, {0,1,1,0}, {0,0,1,0}, {0,0,0,0}};       //8
        int[][] a2 = {{0,0,0}, {0,1,0}, {0,0,0}};       //4
        int[][] a3 = {{0,0,0,0,0}, {0,1,1,1,0}, {0,1,0,1,0},{0,1,1,1,0},{0,0,0,0,0}};       //16

        solution(a);

    }

    public static int solution(int[][] a){
        int answer = 0;
        //값이 0일 경우에만 십자(상하좌우)로 체크하면서 1이 있을 경우 각 +1씩 카운트

        //2차원 배열 반복
        //(0,0) (0.1) (0.2)
        //(1.0) (1.1) (1.2)
        //(2.0) (2.1) (2.2)
        for(int i=0; i<a.length; i++){
            for(int j=0; j<a[i].length; j++){
                System.out.println("i : " + i + ", j : " + j + " / 값 : " + a[i][j]);
                if(a[i][j] == 0){
                    int up = (i-1);
                    if(up >= 0 && up < a.length && a[up][j] == 1){      //기준범위를 벗어나지 않고 위가 1일 경우 +1
                        answer++;
                    }

                    int down = (i+1);
                    if(down >= 0 && down < a.length && a[down][j] == 1){   //기준범위를 벗어나지 않고 아래가 1일 경우 +1
                        answer++;
                    }

                    int left = (j-1);
                    if(left >= 0 && left < a[i].length && a[i][left] == 1){     //기준범위를 벗어나지 않고 왼쪽이 1일 경우 +1
                        answer++;
                    }

                    int right = (j+1);
                    if(right >=0 && right < a[i].length && a[i][right] == 1){      //기준범위를 벗어나지 않고 오른쪽이 1일 경우 +1
                        answer++;
                    }
                }
            }
        }
        System.out.println(answer);
        return  answer;
    }
}
/*
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
*/

/*

public class TableRound {
    public static void main(String[] args) {
        int[][] table = {
                {0, 0, 0, 0},
                {0, 1, 1, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 0}
        };

        int[][] table2 = {
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 1, 0, 1, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0}
        };
        int result = 0;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if (table[i][j] == 1) {
                    if (table[i - 1][j] == 0) {
                        result++;
                    }
                    if (table[i + 1][j] == 0) {
                        result++;
                    }
                    if (table[i][j - 1] == 0) {
                        result++;
                    }
                    if (table[i][j + 1] == 0) {
                        result++;
                    }
                }
            }
        }

        System.out.println(result);
    }
}
 */