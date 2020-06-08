package ym.project.example.modifierPack;

import ym.project.example.modifierPack2.OtherPackageModifier;

/*
* 접근제어자 : 클래스,멤버변수,메소드,생성자에 붙어서 사용 가능
* 1. public : 모든 접근 가능
* 2. private : 같은 클래스 내에서만 접근 가능
* 3. default : 같은 클래스, 같은 패키지 내에서만 접근 가능
* 4. protected : 같은 클래스, 같은 패키지, 다른 패키지에서 상속을 받아 자손클래스에서의 사용 가능
* */
public class ModifierCheck {

    public ModifierCheck(){         //생성자
        this._public();
        this._private();
        this._default();
        this._protected();
    }

    public void _public(){
        System.out.println("public");
    }

    private void _private(){
        System.out.println("private");
    }

    void _default(){
        System.out.println("default");
    }

    protected void _protected(){
        System.out.println("protected");
    }
    //같은 클래스내의 모든 메소드나 모든 변수는 접근제어자가 어떤것이든 모두 접근 가능
}

class PackageModifierCheck extends SamePackageModifer{          //동일 패키지 상속

    public PackageModifierCheck(){
        SamePackageModifer spm = new SamePackageModifer();
        spm._public();
//        spm._private();   //같은 패키지의 경우 private은 접근 불가
        spm._default();
        spm._protected();

        this._public();
//        this._private();  //같은 패키지에서 상속관계를 가지는 경우 private 접근 불가
        this._default();
        this._protected();

    }
}

class OtherPackageModifierCheck extends OtherPackageModifier{      //다른 패키지 상속

    public OtherPackageModifierCheck(){
        OtherPackageModifier opm = new OtherPackageModifier();
        opm._public();
        //opm._private();
        //opm._default();
        //opm._protected();
        //다른 패키지에 있는 메소드나 멤버변수의 접근은 public 이외에는 불가능

        this._public();
//        this._private();
//        this._default();
        this._protected();
        //다른 패키지의 클래스를 상속받는 경우는 public, protected 이외에는 접근이 불가능하다

    }
}
