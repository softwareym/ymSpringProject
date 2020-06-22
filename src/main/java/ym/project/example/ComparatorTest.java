package ym.project.example;

import java.math.BigDecimal;
import java.util.*;

class Player {
    private String name;
    private int age;
    private String address;

    public Player(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;

    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}

public class ComparatorTest {

    /*
     * compare() 메서드 작성법
     * 첫 번째 파라미터로 넘어온 객체 < 두 번째 파라미터로 넘어온 객체: 음수 리턴
     * 첫 번째 파라미터로 넘어온 객체 == 두 번째 파라미터로 넘어온 객체: 0 리턴
     * 첫 번째 파라미터로 넘어온 객체 > 두 번째 파라미터로 넘어온 객체: 양수 리턴
     * 음수 또는 0이면 객체의 자리가 그대로 유지되며, 양수인 경우에는 두 객체의 자리가 변경된다.
     * 즉, Integer.compare(x, y)(오름차순 정렬)와 동일한 개념이다.
     * return (x < y) ? -1 : ((x == y) ? 0 : 1);
     * 내림차순 정렬의 경우 두 파라미터의 위치를 바꿔준다. Integer.compare(y, x)(내림차순 정렬)
     */
    static void SetStudent() {
        ArrayList<Player> sList = new ArrayList<Player>();

        Player s1 = new Player("가", 10, "주소1");
        Player s2 = new Player("나", 13, "주소2");
        Player s3 = new Player("다", 11, "주소3");

        // 배열 정렬시 Arrays.sort, List 정렬시 Collections.sort
        sList.add(s1);
        sList.add(s2);
        sList.add(s3);

        // 기본값은 Comprarable 이고, 이는 기본적인 정렬기능
        // Comparator는 내가 원하는 정렬방식을 지정 가능
        Collections.sort(sList, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                int age1 = o1.getAge();
                int age2 = o2.getAge();

               return (age1 < age2) ? 1 : ((s1 == s2 ? 0 :  -1));  // 내림차순 정렬
            }
        });

        for (int i = 0; i < sList.size(); i++) {
            System.out.println(sList.get(i).getName());
        }

    }

    static void comparingTest(){

        List<Player> sList = new ArrayList<Player>();

        Player s1 = new Player("가가가", 10, "주소1");
        Player s2 = new Player("다다다", 11, "주소2");
        Player s3 = new Player("나나나", 11, "주소3");

        sList.add(s1);
        sList.add(s2);
        sList.add(s3);

        //getAge 내림차순 1순위. getName 오름차순 2순위. getAddress 오름차순 3순위정렬
        Collections.sort(sList, Comparator.comparing(Player :: getAge).reversed().thenComparing(Player::getName).thenComparing(Player::getAddress));
    }

    // string 문자를 숫자 기준으로 정렬하기 (단, 출력할때 입력받은 문자열 형식 그대로 출력)
    static void ArraySort() {
        String[] str = { "-100", "50", "0", "56.6", "90", "0.12", ".12", "02.34", "000.000" };

        Arrays.sort(str, 0, str.length, new Comparator<String>() { // 정렬 대상 배열 지정 가능
            @Override
            public int compare(String o1, String o2) {
                BigDecimal b1 = new BigDecimal(o1);
                BigDecimal b2 = new BigDecimal(o2);

//              return b1.compareTo(b2);       //오름차순
                return b2.compareTo(b1);         //내림차순
            }
        });
        for (int i = 0; i < str.length; i++) {
            System.out.println(str[i]);
        }

    }

    public static void main(String[] args) {
        SetStudent();
        ArraySort();
        comparingTest();
    }
}
