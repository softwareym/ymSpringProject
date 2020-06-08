package ym.project.example;

/**
 * 추상클래스 : 상속될 것을 전제로 하여 만들어진 클래스 (class 키워드 앞에 abstract)
 * 추상클래스를 상속받아 기능을 이용하고, 확장시키기 위함 / 다중 상속 불가능 / 일반메서드+추상메서드 가능 / 일반변수(가능)+일반메서드(가능)+추상메서드 형태 / 생성자와 일반변수 모두 가질 수 있음 / extends 추상클래스명
 * 추상클래스와 인터페이스는 인스턴스 생성이 불가함
 *
 * 혼자로는 Class의 역할을 다 못하지만, 새로운 Class를 작성하는 데 있어 그 바탕(Basic Class)가 되는 부모클래스(Super Class)로서 중요한 의미를 갖는다
 * 추상 클래스에서는 구현하지 않은 추상 메서드를 정의할 수 있는데 상속한 클래스는 반드시 이 메서드들을 정의해야 한다. 공통적인 기능이나 API를 설계할 때 자주 사용된다.
 * 추상 클래스는 여러 기존의 클래스에서 공통된 부분을 추상화 한 것으로 간단하게 말해 공통된 부분을 따로 뽑아낸 것이라고 할 수 있다.
 *
 * 추상 클래스의 목적 => 상속을 받아서 기능을 확장시키는 것
 * AbstractMap 의 서브클래스인 HashMap, TreeMap, ConcurrentHashMap 에서는 AbstractMap 에 정의되어 있는 get, put, isEmpty, containsKey, containsValue 등의 메소드를 공유
 *
 */
public abstract class AbstractCalculator {

    int a, b;

    //일반 메소드
    public void setOprands(int a, int b){
        this.a = a;
        this.b = b;
    }

    //추상 메소드 => 인터페이스처럼 정의만 해주고 추상클래스 내에서는 구현부는 만들지 않는다. AbstractCalculator를 상속한 클래스에서 반드시 이 메서드들을 override하여 (재)정의해야 한다.
    public abstract void sum();
}


class CalculatorDemo extends AbstractCalculator {

    //Override : 슈퍼 클래스에 존재하는 필드나 메소드를 서브 클래스에서 재정의하여 사용
    //@Override 어노테이션을 사용하면 오타 방지
    @Override
    public void sum() {
        System.out.println("[CalculatorDemo extends AbstractCalculator] sum : " + + (this.a+this.b));
    }
}


class CalculatorDemo2 extends AbstractCalculator {

    @Override
    public void sum() {
        System.out.println("[CalculatorDemo2 extends AbstractCalculator] sum : " + + ((this.a-1)+(this.b-1)));
    }
}

class Main {

    public static void main(String[] args) {
        CalculatorDemo c1 = new CalculatorDemo();
        c1.setOprands(1,2);
        c1.sum();

        CalculatorDemo2 c2 = new CalculatorDemo2();
        c2.setOprands(11,21);
        c2.sum();
    }


}
