package ym.project.example;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class CompareString implements BinaryOperator<String>{
    @Override
    public String apply(String s, String s2) {
        if(s.getBytes().length <= s2.getBytes().length)
            return s;
        else return s2;
    }
}

/**
 * <스트림(stream)> *
 * -자료의 대상과 관계없이 동일한 연산을 수행 -배열,컬렉션을 대상으로 한 동일한 연산을 수행함
 * -한번 생성하고 사용한 스트림은 재사용 할 수 없음(자료에 대한 스트림을 생성하여 연산을 수행하면 스트림은 소모됨)
 * -스트림 연산은 기존 자료를 변경 x - 자료에 대한 스트림을 생성하면 별도의 메모리 공간을 사용하므로 기존 자료를 변경하지 않음
 *
 * -스트림(Streams)은 람다를 활용할 수 있는 기술 중 하나로
 *  기존에 자바 컬렉션이나 배열의 원소를 가공할떄, for문, foreach 등으로 원소 하나씩 골라내여 가공을 하였다면,
 *  Stream 을 이용하여 람다함수형식으로 간결하고 깔끔하게 요소들의 처리가 가능
 *
 * <스트림 사용법>
 * -배열의 원소를 가공하는데 있어 map, filter, sorted 등이 있습니다.
 * -map은 요소들을 특정조건에 해당하는 값으로 변환해 줍니다. 요소들을 대,소문자 변형 등 의 작업을 하고 싶을떄 사용 가능 합니다.
 *  filter는 요소들을 조건에 따라 걸러내는 작업을 해줍니다. 길이의 제한, 특정문자포함 등 의 작업을 하고 싶을때 사용 가능합니다.
 *  sorted는 요소들을 정렬해주는 작업을 해줍니다. 요소들의 가공이 끝났다면 리턴해줄 결과를 collect 를 통해 만들어줍니다.
 *
 *  reduce()연산
 *  -정의된 연산이 아닌 프로그래머가 직접 지정하는 연산을 적용
 */
public class StreamEx {

    public static void main(String args[]){
        streamTest1();
        streamTest2();
        streamReduce();
    }

    public static void streamTest1(){
        ArrayList<String> list = new ArrayList<>(Arrays.asList("Apple","Banana","Melon","Grape","Strawberry"));

        System.out.println(list);  //[Apple, Banana, Melon, Grape, Strawberry]

        System.out.println(list.stream().map(s->s.toUpperCase()).collect(Collectors.joining(" ")));
        System.out.println(list.stream().map(s->s.toUpperCase()).collect(Collectors.toList()));
        list.stream().map(String::toUpperCase).forEach(s-> System.out.println(s));

        System.out.println(list.stream().filter(t->t.length()>5).collect(Collectors.toList()));

        System.out.println(list.stream().sorted().collect(Collectors.toList()));
    }


    public static void streamTest2(){
        List<String> sList = new ArrayList<String>();
        sList.add("a");
        sList.add("b");
        sList.add("c");

        Stream<String> stream = sList.stream();
        stream.forEach(s-> System.out.println("streamTest2_1 :" +s));

        for(String s : sList){
            System.out.println("streamTest2_2 :"+s);
        }

        sList.stream().sorted().forEach(s-> System.out.println("streamTest2_3 :"+s));
    }

    public static void streamReduce(){

        String[] sArray ={"일이삼사","일","일이","일이삼"};

        System.out.println("streamReduce : 람다");
        System.out.println(
                Arrays.stream(sArray).reduce("",(s1,s2)->{
                    if(s1.getBytes().length >= s2.getBytes().length)
                        return s1;
                    else return s2;
                })
        );

        System.out.println("streamReduce : CompareString");
        String str =  Arrays.stream(sArray).reduce( new CompareString()).get();
        System.out.println(str);

    }






}
