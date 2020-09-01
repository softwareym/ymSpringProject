package ym.project.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 중복 코드 제외
 * level 별로 처리하여 세팅하는 값을 클래스로 분류하여 값 세팅
 * 이와 같이 하면 좋은 점은 같은 값세팅을 하는 경우 하드코딩하여 중복된 코드 발생이 줄어들어 재사용 용이 / 세팅값 관리에 용이
 * 나중에 level이 추가될 경우 다른곳에서 사용할수도 있는 상황이 오면 level3 등 서브클래스만 추가해주면 되고 재사용하기도 편하기 때문 변경 [나중에 코드값이 변경되거나 추가되도 한곳에서 관리할수있음]

cf) 이펙티브자바 3판
 * 필요한 원소를 컴파일타임에 다 알 수 있는 상수 집합이라면 항상 열거 타입을 사용하자. 열거 타입은 확실히 정수 상수보다 뛰어나다. 더 읽기 쉽고 안전하고 강력하다.
 * 대다수 열거 타입이 명시적 생성자나 메서드 없이 쓰이지만, 각 상수를 특정 데이터와 연결짓거나 상수마다 다르게 동작하게 할 때는 필요하다.
 * 드물게는 하나의 메서드가 상수별로 다르게 동작해야 할 때도 있다. 이런 열거 타입에서는 switch 문 대신 상수별 메서드 구현을 사용하자.
 * 열거 타입 상수 일부가 같은 동작을 공유한다면 전략 열거 타입 매턴을 사용하자.

 * <enum을 사용하는 이유>
  - 코드가 단순해진다.
  - 인스턴스 생성과 상속을 방지한다.
  - 키워드 enum을 사용하기 때문에 구현의 의도가 열거임을 분명하게 나타낼 수 있다.

 * enum은 사실 클래스다. 그렇기 때문에 생성자를 가질 수 있다. 필드의 숫자만큼 생성자가 호출된다
 * enum의 생성자가 접근 제어자 private만을 허용하기 때문 /  변수는 private으로 선언한다 => 사용자가 값 변경을 막기 위함

cf) https://opentutorials.org/module/516/6091
    https://soheemon.tistory.com/entry/%EC%9E%90%EB%B0%94%EB%A1%9C-%EB%B0%B0%EC%9A%B0%EB%8A%94-%EB%A6%AC%ED%8C%A9%ED%86%A0%EB%A7%81-2%EC%9D%BC%EC%B0%A8

 */
public enum Level {

    //Level enum의 필드명, 매개변수명(levelValue), 생성자 생성과 추상메소드 override 하여 각각에 해당하는 return 타입 지정
    NONE(-1){

        @Override
        public String[] getArrTitle() {
            return new String[0];
        }

        @Override
        public String[] getArrTitleDetail() {
            return new String[0];
        }

    },
    Level(1){
        @Override
        public String[] getArrTitle() {
            return new String[] { "level1_a", "level1_b", "level1_c", "level1_d", "level1_e", "level1_f"};
        }

        @Override
        public String[] getArrTitleDetail() {
            return new String[] {"레벨디테일1_a", "레벨디테일1_b", "레벨디테일1_c", "레벨디테일1_d", "레벨디테일1_e","레벨디테일1_f"};
        }

    },
    Level2(2){
        @Override
        public String[] getArrTitle() {
            return new String[]{ "level2_a", "level2_b", "level2_c", "level2_d", "level2_e", "level2_f"};
        }

        @Override
        public String[] getArrTitleDetail() {
            return new String[]{"레벨디테일2_a", "레벨디테일2_b", "레벨디테일2_c", "레벨디테일2_d", "레벨디테일2_e","레벨디테일2_f"};
        }

    };

    private int levelValue;

    //생성자
    Level(int levelValue) {
        this.levelValue = levelValue;
    }

    public int getLevelValue() {
        return levelValue;
    }

    private static final Map<Integer, Level> levelMap = new HashMap<>();

    //static 블록은 클래스가 최초 로딩될 때 수행되므로 생성자 실행과 상관없이 수행됨
    static{
        for(Level level : Level.values()){          //enum Level의 값들
            levelMap.put(level.getLevelValue(), level);
        }
    }

    public static Level findBy(int levelValue){
         /* java 7 이하
        if(levelMap.get(levelValue) == null){
            return Level.NONE;
        }else{
            return levelMap.get(levelValue);
        }
        */

        return Optional.ofNullable(levelMap.get(levelValue)).orElse(Level.NONE);        //java 8 이상 지원
    }

    //추상메소드 사용한 상수별 메소드 구현[오버라이드해서 사용]
    public abstract String[] getArrTitle();
    public abstract String[] getArrTitleDetail();


}
