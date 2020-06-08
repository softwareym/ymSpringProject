package ym.project.example;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 *  예외사슬 example / thorw, throws, try~ catch
 *
 * [메소드() throws Exception] => 강제로 Exception을 자신을 호출한 상위메소드에 책임 전가
 * -메소드 뒤에 throws ~~ 를 사용하고 싶지 않으면 그냥 서비스 구현하는 곳에서(ex-impl)에서 try catch 쓰면 된다
 *  (catch문에 예외 발생 시 처리 로직 구현하거나.. 단, 책임을 전가하지는 않는다) *
 * -exception이 발생할 여지가 있는곳에 try catch문을 작성함으로서 예외가 발생하더라도 구동중이던 어플리케이션이 중간에 멈추지않게끔 하는게 예외처리인데
 *  메서드에서 throws Exception을 해주게되면 해당 메서드 내에서 예외가 발생하면 자신이 처리하는게 아니고 자신을 호출한 상위메서드로 예외를 던지게됩니다.
 *
 */

class B{
    void run(){
    }
}
class C{
    void run(){
        B b = new B();
        b.run();
    }
}
public class ThrowExceptionDemo {

    public static void main(String[] args) {
        C c = new C();
        c.run();

        /*
         * <예외사슬>
         * ThrowExceptionDemo.main은 C.run의 사용자이다.
         * C.run은 B.run의 사용자이다.
         * 반대로 B.run의 다음 사용자는 C.run이고 C.run의 다음 사용자는 ThrowExceptionDem.main이 되는 셈이다.         *
         */
        method01();
    }

    public static void method01(){
        try{
            method02();
        }catch(Exception e){
            //method02의 사용자가 throws Exception으로 책임을 전가하여 method01에서 예외를 받고 처리하는데 catch문이 있기 때문에 main에서 처리하지 않고 method01의 catch문에서 처리
            System.out.println(e.getStackTrace());
        }
    }

    public static void method02() throws Exception{
        throw new Exception();
    }

}

