package ym.project.example.innerClass;

/**
 * 지역내부클래스 : 메소드를 호출할때 생성
 */
class Outer{

    int outNum=100;
    static int sNum = 200;

    /*
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            System.out.println("runnable");
        }
    };
    */

    public Runnable getRunnable(final int i){

        int localNum = 100;
        class MyRunnable implements Runnable{

            @Override
            public void run() {

                //메소드의 매개변수와 지역변수는 stack에 위치. 메소드 호출이 끝나면 없어짐 [변하도록 사용불가]
                //localNum += 100;
                //i += 200;
                outNum +=10;

                System.out.println(outNum);
                System.out.println(sNum);
                System.out.println(localNum);
                System.out.println(outNum);
                System.out.println(i);
            }
        }

        return new MyRunnable();
    }
}

public class LocalInnerTest {

    public static void main(String[] args){
        Outer outer = new Outer();
//        outer.getRunnable().run();
        Runnable runnable = outer.getRunnable(20);  //메소드 호출


        runnable.run();                                //메소드 호출 끝난 후 run하게 되면 stack에 변수들이 사라질 수 있음 =>localNum, i는 변할 수 없고 고정형인 상수(final)와 다름 없음
    }
}
