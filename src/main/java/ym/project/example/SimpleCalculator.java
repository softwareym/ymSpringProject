package ym.project.example;

public class SimpleCalculator {

    int a, b;

    public void setOprands(int a, int b){
        this.a = a;
        this.b = b;
    }

    public void sum(){
        System.out.println("[SimpleCalculator] sum : "+ (this.a+this.b));
    }
}

class SimpleCalculatorDemo{         //자바에서 public 클래스는 해당 파일 이름과 동일해야 함

    public static void main(String[] args) {
        SimpleCalculator sc = new SimpleCalculator();
        sc.setOprands(3,6);
        sc.sum();
   }

}