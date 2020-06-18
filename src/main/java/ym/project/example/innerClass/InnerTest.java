package ym.project.example.innerClass;

/**
 * 인스턴스 내부클래스 & 정적 내부 클래스
 * OutClass : 외부클래스
 * InClass : 내부클래스
 * InStaticClass : 정적내부클래스
 */
class OutClass{
    private int num = 10;
    private static int sNum = 20;
    private InClass inClass;

    //생성자
    public OutClass() {
        inClass = new InClass();
    }

    private class InClass{
        int inNum = 200;

        void inTest(){
            System.out.println(num);
            System.out.println(sNum);
        }
    }
    public void usingIntest(){
        inClass.inTest();
    }

    static class InStaticClass{
        int iNum = 100;
        static int sInNum = 200;

        void inTest(){
            //num += 10;  //사용불가
            sNum += 10;
            System.out.println(sNum);
            System.out.println(iNum);
            System.out.println(sInNum);
        }

        static void sTest(){
            System.out.println(sNum);
            //System.out.println(iNum);   //사용불가
            System.out.println(sInNum);
        }
    }
}

public class InnerTest {

    public static void main(String[] args){
        OutClass outClass = new OutClass();
        outClass.usingIntest();

        OutClass.InStaticClass sInClass = new OutClass.InStaticClass();
        sInClass.inTest();

        OutClass.InStaticClass.sTest();
    }

}
