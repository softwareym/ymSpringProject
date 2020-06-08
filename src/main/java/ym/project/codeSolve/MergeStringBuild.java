package ym.project.codeSolve;

//1번
public class MergeStringBuild {

    //submit
    public static void main(String[] args) {
        mergeStrings("abc","def");
    }

    public static String mergeStrings(String a, String b) {
        // Write your code here
        StringBuilder result = new StringBuilder();
        int min = a.length() > b.length() ? b.length(): a.length();

        for (int i = 0; i < min; i++) {
            result.append(a.charAt(i)).append(b.charAt(i));
        }

        String longString = a.length() >= b.length() ? a : b;
        result.append(longString.substring(min));

        return result.toString();
    }

     /*
		string, stringbuilder, stringbuffer 차이점
		=>	스트링은 메모리 불변이고 스트링빌더와 스트링버퍼는 메모리 가변입니다.
				cf) String은 같은 문자열값이면 같은 메모리를 사용한다. 즉, 같은 문자열값을 갖는다면 새로 메모리를 할당하지 않고, 기존의 메모리를 참조만 시키는 구조이다.
					반면, StringBuffer와 StringBuilder는 String과 다르게 같은 문자열을 갖더라도 변수가 다르면 서로 다른 메모리에 저장한다.
		스트링빌더는 동기화(쓰레드 세이프) 보장이 안되고, 스트링버퍼는 동기화(쓰레드 세이프) 보장이됩니다.
			cf) 동기화(쓰레드 세이프) : 멀티 쓰레드환경에서 정확한 결과를 보장해주는 것
								 (멀티쓰레드는 여러 쓰레드가 같은 프로세스 내의 자원을 공유해서 작업하기 때문에 서로의 작업에 영향을 줌)
		스트링버퍼는 동기화하는 정교한 작업을 하기 때문에 스트링버퍼가 스트링빌더보다 느리다.
		멀티쓰레드로 운영되는 기능에 대해 구현할때는 동기화가 보장되는 스트링버퍼를 사용할는것이 좋다.
		속도 : 스트링빌더 > 스트링버퍼 > 스트링
	*/


   /* public static void main(String[] args){
        String abc =  "tab4";
        System.out.println(mergeStrings("abcd", "efg"));        //결과 aebfcgd
//        System.out.println(mergeString2("abcd", "efg"));        //결과 aebfcgd
//        System.out.println(mergeString2("abc", "defg"));        //결과 adbecfg
        System.out.println(abc.substring(3));                           //결과 4      //3번째 위치부터 자름
    }

    //유추 : 두개의 문자열이 있는데, 순서대로 번갈아가면서 새로운 문자열을 만든다(단, 더 이상 번갈아갈 문자열이 없을 경우에는 남은 문자열을 모두 리턴)
    static String mergeStrings(String a, String b) {
        int max = Math.max(a.length(), b.length());			//a랑 b중 가장 큰수 리턴,		Math.max : 가장 큰 수를 리턴하는 함수
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < max; i++) {
            if (a.length() > i) {
                builder.append(a.charAt(i));               //한글자씩 해당인덱스에 대한 문자열 자름
            }

            if (b.length() > i ) {
                builder.append(b.charAt(i));
            }
        }
        return builder.toString();
    }

    static String mergeString2(String a, String b) {
        StringBuilder result = new StringBuilder();
        int limit = Math.min(a.length(), b.length());       	//a랑 b중 가장 작은수 리턴

        //작은수 기준으로 문자열 번갈아 가면서 생성[큰 수 일경우 예외에러]
        for (int index = 0; index < limit; index++) {
            result.append(a.charAt(index)).append(b.charAt(index));
        }

        //더 큰값을 longString에 대입[for문 못 돈 문자열이 있기 때문에] > 해당 인덱스를 잘라서 result에 append
        String longString = a.length() >= b.length() ? a : b;
        result.append(longString.substring(limit));

        return result.toString();
    }*/

}
