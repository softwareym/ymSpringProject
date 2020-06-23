<%--
  Created by IntelliJ IDEA.
  User: PC18031
  Date: 2020-01-10
  Time: 오전 10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <title>Common Main</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>

<body>
<div id="container">
</div>
<script type = "text/javascript">

    //test1();
    //test2();

    //비동기적 콜백실행
    function test1(){
        console.log(0);

        setTimeout( function(){
            console.log('setTimeout');
        },1000)
        console.log(1);
    }
    //--비동기적 콜백실행

    //동기적 콜백실행
    function test2(){
        function fakeSetTimeout(callback, delay){
            callback();
        }

        console.log("00");
        fakeSetTimeout(function () {
            console.log("fakeSetTimeout");
        }, 0)

        console.log("11");
    }
    //--동기적 콜백실행

    //클로저
    /* 클로저는 독립적인 (자유) 변수를 가리키는 함수이다. 또는, 클로저 안에 정의된 함수는 만들어진 환경을 ‘기억한다’. [스코프체인]
       흔히 함수 내에서 함수를 정의하고 사용하면 클로저라고 한다. 하지만 대개는 정의한 함수를 리턴하고 사용은 바깥에서 하게된다.
       클로저는 생성한 시점의 스코프를 가지고 있다.
    */
    var d = 'X';    //global 변수
    function outer(){
        var a = 1;
        var b = 'B';

        function inner(){
            var  a = 2;
            //console.log(a);
            console.log("클로저 : " + b);     //'B' => 클로저를 이용하여 값 가져옴
            //console.log(d);
        }
        return inner();
        //console.log(a);
        //변수나 함수명을 가지고 그 값을 찾을때 들여다보는 곳이 스코프라 하는데
        //자바스크립트는 함수 단위로 스코프 생성
        //global 스코프, outer용 스코프, inner용 스코프 각각 존재하고 inner와 outer 안에 있기 때문에 연결 되어 있음[스코프체인]
    }
    var someFunc = outer();    //global에서 호출하기때문에 global 스코프와 outer가 연결 되어 있음[스코프체인]
    //someFunc();
    //--클로저

    //콜백지옥 탈출 promise
    /*
    function delay(sec,callback){
        setTimeout(()=>{
            callback(new Date().toISOString());
        }, sec*1000);
    }

    //<1초 후인 동일한 시간에 1,2,3 출력>
    //1 "2020-06-23T03:16:05.632Z"
    //2 "2020-06-23T03:16:05.632Z"
    //3 "2020-06-23T03:16:05.632Z"
    delay(1, (result) =>{
        console.log(1, result);
    });
    delay(1, (result) =>{
        console.log(2, result);
    });
    delay(1, (result) =>{
        console.log(3, result);
    });

    // <1초 후 1 출력, 1초후 2, 1초후 3 > => 콜백 안에 콜백이 있을 경우 가독성이 떨어지기 때문에 promise와 async/await을 사용
    //1 "2020-06-23T03:17:22.001Z"
    //2 "2020-06-23T03:17:23.003Z"
    //3 "2020-06-23T03:17:24.005Z"
    delay(1, (result) =>{
        console.log(1, result);
        delay(1, (result) =>{
            delay(1, (result) =>{
                console.log(3, result);
            });
            console.log(2, result);
        });
    });
    */

    function delayP(sec){
        return new Promise((resolve,reject)=>{       //인스턴스를 리턴해야지만 실제 then 에서 리턴하여 사용할 수 있음
            setTimeout(()=>{
                resolve(new Date().toISOString());  //resolve를 통해서 결과값을 반환
            },sec*1000)
        });
        //resolve : 할 일을 다 했을 떄 호출
        //reject : 할 일을 하다가 예외가 발생했을경우 호출
    }

    //promise 형태 실행에 필요한 파라미터 1, 결과를 받을 콜백을 넘김[then((result))]
    //delayP로 만드는게 프로미스의 인스턴스
    //프로미스에서는 콜백의 콜백을 호출하는게 아니라 콜백을 순차적으로 지정함
    delayP(1).then((result)=>{
       console.log(1, result);
       return delayP(1);
    }).then((result)=>{
       console.log(2, result);
       return delayP(1);
    }).then((result)=>{
       console.log(3,result);
       return 'wow';
    }).then((result)=>{
       console.log(result);
    });

</script>
</body>
</html>

