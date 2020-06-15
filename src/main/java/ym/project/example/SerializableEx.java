package ym.project.example;


import java.io.*;
import java.util.Base64;

/**
 * 직렬화(Serialization) : 객체를 데이터스트림(스트림에 쓰기(write)위한 연속적인(serial) 데이터)으로 만드는 것.
 * 데이터를 파일에 쓰거나, 네트워크를 타고 다른 곳에 전송할 때는 데이터를 바이트 단위로 분해하여 순차적으로 보내야 한다. 이것을 직렬화(Serialization)라고 한다.
 *
 * 역직렬화(Deserialization) : 스트림으로부터 데이터를 읽어서 객체를 만드는 것.
 * 직렬화할 클래스에 Serializable 인터페이스를 사용
 */
class Member implements Serializable {

    //직렬화된 객체를 역직렬화할 때 서로 같은 클래스를 사용해야 하는데, 클래스의 이름이 같더라도 클래스의 내용이 변경된 경우 역직렬화가 실패하며 에러가 발생한다.
    private static final long serialVersionUID = 4220461820168818967L;

    private String name;
    private transient String email;
    private transient int age;          //transient : 직렬화에서 제외 (보안 변수 : null 전송)

    public Member(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("Member{name='%s', email='%s', age='%s'}", name, email, age);
    }
}


public class SerializableEx {

    public static void main(String args[]){

        String serializedString = "";
        serializedString = serializable();
        deserializable(serializedString);
    }


    /**
     * ObjectOutputStream : 직렬화 보조스트림, 혼자 사용 불가
     */
    public static String serializable(){

        Member member = new Member("김가나","a@b.com", 20);
        byte[] serializedMember;
        String  ret = "";

        try(ByteArrayOutputStream baos = new ByteArrayOutputStream()){
            try(ObjectOutputStream oos = new ObjectOutputStream(baos)){
                oos.writeObject(member);
                //serializedMember : 직렬화된 member 객체
                serializedMember = baos.toByteArray();

                // 바이트 배열로 생성된 직렬화 데이터를 base64로 변환
                ret = Base64.getEncoder().encodeToString(serializedMember);
                System.out.println(Base64.getEncoder().encodeToString(serializedMember));
            }catch (Exception e){
                System.out.println("ObjectOutputStream Exception");
            }


        }catch (Exception e){
            System.out.println("ByteArrayOutputStream Exception");
        }

        return ret;

    }

    public static void deserializable(String serializedString){
        byte[] serializedMember = Base64.getDecoder().decode(serializedString);

        try(ByteArrayInputStream bais = new ByteArrayInputStream(serializedMember)){
            try(ObjectInputStream ois = new ObjectInputStream(bais)){

                // 역직렬화된 Member 객체를 읽어온다.
                Object objectMember = ois.readObject();
                Member member = (Member) objectMember;
                System.out.println(member);

            }catch (Exception e){
                System.out.println("ObjectInputStream Exception");
            }
        }catch (Exception e){
            System.out.println("ObjectInputStream Exception");
        }
    }
}
