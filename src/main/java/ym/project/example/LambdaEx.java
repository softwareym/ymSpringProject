package ym.project.example;

interface Compare {
    int comapareTo(int value11, int value2);
}

interface Say{
    int something(int a, int b);
}

@FunctionalInterface        //함수형 인터페이스 체크 어노테이션
interface MyNumber{
    int getMax(int num1, int num2);
}

interface PrintString{
    void showString(String str);
}

class Person{
    public void hi(Say line){
        int number = line.something(3,4);
        System.out.println("Number is " + number);      //main()에서 return한 숫자 = number
    }
}

/**
 *  람다 : 함수형 프로그래밍
 *  ((매개변수) -> {실행문}) : 딱 한 번만 쓰일때 사용(재사용 불가),  병렬프로그래밍이 용이
 */
public class LambdaEx {

    public static void execCompare(Compare compare){
        int k = 10;
        int m = 20;
        int value = compare.comapareTo(k,m);
        System.out.println("2개의 숫자 더한 값" + value);
    }

    public static void main(String[] args){

        //두개의 숫자 더하기
        execCompare((i,j) -> {
            return i+j;
        });
        /////////////////////////////////////////////////////////////////////////////////////////
        Person rinLambda = new Person();
        rinLambda.hi((a,b)->{
            System.out.println("람다식 사용 O : parameter number is " + a + "," +b);
            return 9;
        });
        /////////////////////////////////////////////////////////////////////////////////////////
        Person rin = new Person();
        rin.hi(new Say(){
            @Override
            public int something(int a, int b) {
                System.out.println("람다식 사용 X / 내부클래스 사용 O : parameter number is " + a + "," +b);
                return 6;
            }
        });

        /////////////////////////////////////////////////////////////////////////////////////////
        MyNumber max = (x,y) -> ( x >= y )? x:y;
        System.out.println("2개의 숫자 중 큰 수 : " + max.getMax(50,30));
        /////////////////////////////////////////////////////////////////////////////////////////
        Runnable runnable = () ->{
            for(int i=0; i <30; i++){
                System.out.println("Runnable 인스턴스 생성 : "+i);
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        /////////////////////////////////////////////////////////////////////////////////////////
        Thread thread2 = new Thread( () ->{
            for(int i=0; i<10; i++){
                System.out.println("쓰레드 호출 :"+i);
            }
        });
        thread2.start();
        /////////////////////////////////////////////////////////////////////////////////////////

        //변수에 대입해서 바로 implementation하는 방법 [인터페이스형 변수에 람다식 대입]
        PrintString lambdaPrint = str -> System.out.println(str);
        lambdaPrint.showString("test");

        //implementation된 변수 자체가 매개변수로 넘어가는 방법 [매개변수로 전달하는 람다식]
        showMyString(lambdaPrint);

        //implementation한 자체를 반환값으로 넘기는 방법
        PrintString reStr = returnPrint();
        reStr.showString("Hello");
    }

    public static void showMyString(PrintString lambda){     //매개변수를 인터페이스형으로 받음
        lambda.showString("test2");
    }

    public static PrintString returnPrint(){                //람다식을 반환하는 메서드
        return s -> System.out.println(s + "world");
    }
}











