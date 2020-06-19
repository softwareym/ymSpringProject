package ym.project.example.innerClass;

//1.인터페이스를 익명클래스로 구현
interface Tv{
    void on();
    void off();
}
class Machine {
    Tv tv = new Tv(){
        @Override
        public void on() {
            System.out.println("on");
        }

        @Override
        public void off() {
            System.out.println("off");
        }
    };
}
class Person{
    void whoAmI(){
        System.out.println("Person");
    }
}
/**
 * 보통의 경우, 부모클래스를 상속받아 처리하려면 부모클래스를 상속받는 클래스를 별도로 만들어 처리하는데
 * Person를 상속받아 처리해야 하는 클래스가 또 필요한 경우, 매번 Child2, Child3… 등등을 만드는 것은 낭비고 불필요한 클래스만 많아진다.
 * 상속받은 클래스가 재사용되면 모를까, 그냥 한번 쓰고 버려진다면 굳이 클래스 파일을 만들 필요가 없는데 이럴 경우에 바로 익명 클래스를 사용한다.
 */
class Child extends Person{
    @Override
    void whoAmI() {
        super.whoAmI();
    }
}
/**
 * <익명클래스>
 * 부모클래스 인스턴스 = new 부모클래스() {
 *      처리구문
 * }; *
 */
public class AnonymousTest {

    public static void main(String args[]){
        //1.인터페이스를 익명클래스로 구현
        Machine mc = new Machine();
        mc.tv.on();
        mc.tv.off();

        //2.클래스를 익명클래스로 구혐
        Person p = new Person(){
            String name="ym";

            @Override
            void whoAmI() {
                super.whoAmI();
            }
        };
        p.whoAmI();
    }
}
