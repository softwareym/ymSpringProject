package ym.project.example;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

/**
 * < Comparable >
 - 정의: 정렬 수행시 기본적으로 적용되는 정렬 기준이 되는 메서드를 정의해 놓는 인터페이스
 - 사용법: Comparable 인터페이스를 implements 한 뒤, 내부에 있는 객체를 정렬하는 데 사용되는 메소드인 compareTo()를 원하는 정렬 기준대로 구현하여 사용할 수 있다.
 *
 * 자바에서 같은 타입의 인스턴스를 서로 비교해야만 하는 클래스들은 모두 Comparable 인터페이스를 구현하고 있다.
 * 따라서 Boolean을 제외한 래퍼 클래스나 String, Time, Date와 같은 클래스의 인스턴스는 모두 정렬 가능하다.
 * 이때 기본 정렬 순서는 작은 값에서 큰 값으로 정렬되는 오름차순이 된다.
 * Arrays.sort()등 정렬 메서드들은 정렬 시 자동으로 Comparable에 구현되어 있는 compareTo() 메서드를 호출해서 사용

 * < Comparator >
 - 정의: 기본 정렬 기준과는 다른 방식으로 정렬하고 싶을 때 사용
 - 사용법: Comparator 클래스를 생성하여, 내부에 compare 메서드를 원하는 정렬 기준대로 구현하여 사용할 수 있다.
 - 주로 익명클래스(new Comparator(){ ... })로 사용되며, 기본적으로 오름차순이 정렬 기준인 것을 내림차순으로 정렬하는 등의 용도로 사용된다.

 =>Collections.sort() , Arrays.sort() 등 ~~.sort()는 배열이나 리스트를 정렬할 때 Comparator를 지정하지 않았을 경우
   Comprarable을 구현한 클래스의 객체에 구현된 내용에 따라 정렬

 */
class Student implements Comparable<Student>{
    String name;
    int id;
    double score;

    public Student(String name, int id, double score) {
        this.name = name;
        this.id = id;
        this.score = score;
    }

    public String toString() {
        return "Student{" + "name='" + name + '\'' + ", id=" + id + ", score=" + score + '}';
    }

    @Override
    public int compareTo(Student o) {

        /**
         * < compareTo 메소드 >
         * 정렬이 진행될 때 자리바꿈(=정렬) 여부를 결정하는 값을 넘겨주는 역할을 한다.
         * 만약 return값이 0이나 음수이면 자리바꿈을 하지 않고, 양수이면 자리바꿈을 수행한다.
         * 만약 오름차순이 아니라 내림차순으로 정렬하고 싶다면 매개변수의 순서를 바꿔주면 된다.
         * */
        //return Integer.compare(id, o.id);      /* 기본 정렬 기준: 학번 오름차순 */
        return Integer.compare(o.id, id);        /* 학번 내림차순 */
    }
}

public class SortEx {

    public static void main(String args[]){

        System.out.println("comparabletest");
        comparabletest();

        System.out.println("comparatortest");
        comparatortest();

        System.out.println("comparatortest2");
        comparatortest2();
    }

    //1.comparable 인터페이스 사용
    public static void comparabletest(){
        Student st[] = new Student[3];

        st[0] = new Student("aaa",3,10);
        st[1] = new Student("bbb",2,80);
        st[2] = new Student("ccc",1,50);

        Arrays.sort(st);

        for(int i=0; i<st.length; i++){
            System.out.println(st[i]);
        }
        List<Student> lst = new ArrayList<>();
        lst.add(new Student("가",1,500));
        lst.add(new Student("나",4,700));
        lst.add(new Student("다",4,900));

        Collections.sort(lst);
    }

    //2.comparator 인터페이스  사용 - Arrays.sort
    public static void comparatortest(){

        Student st[] = new Student[3];
        st[0] = new Student("aaa",3,80);
        st[1] = new Student("bbb",2,80);
        st[2] = new Student("ccc",1,50);

        Arrays.sort(st, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                double o1Score = o1.score;
                double o2Score = o2.score;
                if(o1Score == o2Score){                     //학점이 같으면
                    return Double.compare(o1.id, o2.id);    //학번 오름차순
                }
                return Integer.compare(o1.id, o2.id);
            }
        });

        for(int i=0; i<st.length; i++){
            System.out.println(st[i]);
        }
    }

    //2.comparator 인터페이스  사용 - collections.sort
    public static void comparatortest2(){

        List<Student> lst = new ArrayList<>();
        lst.add(new Student("가2",1,500));
        lst.add(new Student("나2",4,700));
        lst.add(new Student("다2",4,900));

        //Comparator 정의 후 정렬하는 방식
        Comparator<Student> comparator = new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o2.id - o1.id;
            }
        };
        System.out.println("1: " + lst.toString());
        Collections.sort(lst, comparator);
        System.out.println("2: " + lst.toString());

        /* 정렬할때 Comparator
        Collections.sort(lst, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o2.id - o1.id;
            }
        });
        */

        /* Comparator 객체는 메서드가 하나 뿐인 함수형 인터페이스를 구현하기 때문에 람다함수로 대체 가능
        Collections.sort(lst, (a,b) -> b.id - a.id);
        */

        //Stream 클래스의 sorted() 메서드도 Comparator 객체를 인자로 받아 정렬을 해준다
        //스트림을 사용하면 위의 정렬과 달리 기존 객체의 순서를 변경하지 않고, 새롭게 정렬된 객체를 생성하고자 할 때 사용
        List<Student> sortedStudent = lst.stream().sorted((a,b) -> b.id - a.id).collect(Collectors.toList());
        System.out.println(sortedStudent);
    }

}
