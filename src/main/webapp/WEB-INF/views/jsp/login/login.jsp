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
    <title>LOGIN</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style type="text/css">
        #loginer
        {
            padding: 20px;
            margin-bottom: 20px;
            border: 1px solid #bcbcbc;
            text-align: center;
        }
        #form
        {
            font-size: 1.3em;
            width: 50%;
            display: inline-block;
        }
        ::placeholder
        {
            font-size: 1.3em;
            font-family: 'Nanum Brush Script', cursive;
        }
        button
        {
            border: 0px;
            background-color: lightsteelblue;
        }
        /* 링크 색상 없애기 */
        a:link { color: black; text-decoration: none;}
        a:visited { color: black; text-decoration: none;}
        a:hover { color: black; text-decoration: underline;}
    </style>

    <!-- 구글 웹 폰트 -->
    <link href="https://fonts.googleapis.com/css?family=Nanum+Brush+Script" rel="stylesheet">
    <style>
        body
        {
            font-family: 'Nanum Brush Script', cursive;
        }
    </style>
    <!-- 구글 웹 폰트 끝 -->

    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script type="text/javascript">

        $(document).ready(function(){

            document.loginForm.id.focus();

            //jquery serializeObject 사용
            $.fn.serializeObject = function() {
                "use strict"
                var result = {}
                var extend = function(i, element) {
                    var node = result[element.name]
                    if ("undefined" !== typeof node && node !== null) {
                        if ($.isArray(node)) {
                            node.push(element.value)
                        } else {
                            result[element.name] = [node, element.value]
                        }
                    } else {
                        result[element.name] = element.value
                    }
                }
                $.each(this.serializeArray(), extend)
                return result
            }

        });

        function fn_login(){
            if($('#id').val() == ''){
                alert('아이디를 입력하세요.');
                document.loginForm.id.focus();
                return;
            }else if($('#pwd').val() == ''){
                alert('패스워드를 입력하세요.');
                document.loginForm.pwd.focus();
                return;
            }

            var data = $("#loginForm").serializeObject();               //{"userid":"a","pwd":"a"}

           // console.log(JSON.stringify(data));

            $.ajax({
                type: "POST" ,
                url: "/login/loginOk.do",
                dataType : "json",
                data: JSON.stringify(data),
                async : true,                          //비동기
                contentType: "application/json",
                error: function (request, status, error) {
                    //console.log(error);
                }, success: function (response, status, request) {

                    alert(response.msg + ' [userid = ' + response.userid +']');
                    if(response.status == 'MNG_OK'){
                        location.href = "/mng/main.do"
                    }else if(response.status == "USER_OK"){
                        location.href = "/common/main.do"
                    }
                }
            });

        }
    </script>

</head>

<body>
<div id="container">
    <!-- 로그인 폼 CSS -->
    <div id="loginer">
        <div id="form">
            <form name="loginForm" id="loginForm" method="post" action="/login/loginOk.do">
                <fieldset>
                    <legend>LOGIN !</legend>
                    <input type="text" name="userid" id="id" value="" placeholder="Enter Your Id">
                    <br><br>
                    <input type="password" name="passpwd" id="passpwd" value="" placeholder="Enter Your Password">
                    <br><br>
                    <button onclick="fn_login(); return false;">로그인</button>
                    <button href="/login/join.do">회원가입</button>
                </fieldset>
            </form>
        </div>
    </div>

    <!-- 하단 -->
    <div id="footer">
        <p align="center">by kym</p>
    </div>
</div>
</body>
</html>

