package ym.project.example;

/**
 * <인터페이스>*
 * 구현 객체의 같은 동작을 보장하기 위함 / 다중 상속 가능 / 추상메서드만 가능 / 상수+추상메서드 형태 / 생성자와 일반변수를 가질 수 없음 / implments 인터페이스명
 * 인터페이스를 구현한 클래스(Cat, Monkey)에서는 반드시 그 인터페이스 안에 정의된 메소드들을 구현해야한다.
 *  => (예외)Java 8부터는 default 라는 access modifier(접근 제한자) 를 붙이면, 인터페이스 안에서도 메소드 바디를 구현할 수 있다 [return]
 * 인터페이스는 다형성에 초점을 맞추어 사용
 * 추상클래스와 인터페이스는 인스턴스 생성이 불가함
 *
 * 인터페이스에서는 각 동물들이 무엇을 먹고, 어떻게 걷고, 자는지 구현하도록 되어있습니다.
 * 그렇기때문에 각각의 동물(클래스)들이 animals라는 인터페이스를 상속받을 시 eat, work, move 메서드를 오버라이드 하여 구현하여야 합니다.
 * 그리고 고양이의 자는 방법이 달라지더라도 원숭이나 닭에게는 아무런 영향이 없습니다. *
 * 동물들은 모두 먹고, 걷고, 잠을 자지만 동물들마다 그 방식은 다릅니다.
 * 구현체에서는 동물들이 각각 먹고, 걷고, 자는 방식을 구현하는데 같은 "먹는다"라는 동사에서 동물마다 여러가지 형태로 구현할 수 있기때문에 다형성이라고 합니다.
 *
 */
interface animals{
    public void eat(String bab);
    public void work(String move);
    default int sleep(int zzz){
        return zzz;
    }
}

class Cat implements animals{

    @Override
    public void eat(String bab) {
        System.out.println("고양이는 " + bab + "을 먹는다");
    }

    @Override
    public void work(String move) {
        System.out.println("고양이는 " + move + "걷는다" );
    }

    @Override
    public int sleep(int zzz) {
        System.out.println("고양이는 하루에 " + zzz + "시간을 잔다.");
        return zzz-1;
    }
}


class Monkey implements animals{

    @Override
    public void eat(String bab) {
        System.out.println("원숭이는 " + bab + "을 먹는다");
    }

    @Override
    public void work(String move) {
        System.out.println("원숭이는 " + move + "걷는다" );
    }

    @Override
    public int sleep(int zzz) {
        System.out.println("원숭이는 하루에 " + zzz + "시간을 잔다.");
        return zzz-1;
    }
}



public class TestAnimalInterface {

    public static void main(String[] args){

        Cat cat = new Cat();
        cat.eat("생선");
        cat.work("네발로");
        int hour = cat.sleep(10);
        System.out.println("return : " + hour);

        Monkey monkey = new Monkey();
        monkey.eat("바나나");
        monkey.work("두발로");
        monkey.sleep(12);

    }
}
