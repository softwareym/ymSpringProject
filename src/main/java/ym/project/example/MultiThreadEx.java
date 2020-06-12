package ym.project.example;

/**
 * (멀티쓰레드 동기화)
 *
 * 프로세스는 프로그램을 수행하는데 필요한 데이터와 메모리 등의 자원 그리고 쓰레드로 구성되어 있다
 * 호출 스택에 있는 내용들이 모두 수행하고 나면 쓰레드는 호출스택 공간과 함께 메모리 상에서 소멸된다
 *
 * 쓰레드를 구현하는 방법은 두가지가 있다.
 * 첫번째는 Thread클래스를 상속받아서 사용하는 것.
 * 두번째는 Runnable 인터페이스를 구현하는 것이다. (Runnable이 일반적인 방법)
 *
 * 동기화 보호구역에서는 한번에 한스레드만 접근 가능하게 된다
 * 동기화된 스레드(보호구역에서 실행하고있는 스레드) 혼자서는 동기화 블록(보호구역 안)에서 다른 스레드에게 제어권을 넘기지 못합니다.
 * 동기화된 블록에서 스레드간의 통신(제어권을 넘김)하기 위해서는 wait(), notify(), notifyAll() 메소드를 사용해야 합니다.
 * wait() 메소드는 어떤 객체에 대해 스레드를 대기하게 만듭니다.
 * notify() 메소드는 객체에 대해 대기중인 스레드가 있을 경우 우선순위가 높은 스레드 하나만을 깨웁니다.
 * notifyAll() 메소드는 대기중인 스레드 전부를 깨웁니다.
 */
public class MultiThreadEx extends Thread {

    static int share;

    public static void main(String[] args){

        //notsyncmehtod();
        syncmehtod();

    }

    //Thread의 run()을 override해서 사용
    //run()메소드는 단순히 MultiThreadEx 클래스에 오버라이딩 된 메소드를 호출해서 사용하는 것
    @Override
    public void run(){
        for(int count = 0 ; count < 10; count++){
            System.out.println(share++);

            try{
                sleep(1000);                //지정된 시간(밀리초) 동안 현재 스레드를 일시 중단
            }catch (InterruptedException e){

            }

        }
    }

    public static void notsyncmehtod(){
        MultiThreadEx e1 = new MultiThreadEx();
        MultiThreadEx e2 = new MultiThreadEx();

        System.out.println("쓰레드 세이프하지 않은 실행");
        e1.start();     //쓰레드 실행
        e2.start();
    }

    public static synchronized void syncmehtod(){           // 메소드의 synchronized 동기화 방법

        System.out.println("쓰레드 세이프한 실행");
        MultiThreadEx s1 = new MultiThreadEx();
        MultiThreadEx s2 = new MultiThreadEx();

        s1.start();     //쓰레드 실행
        s2.start();
    }

}
