<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>登录页面</title>
    <style>
        .all{
            width: 800px;
            height: 450px;
            position:absolute;
            top:50%;
            left:50%;
            margin:-225px 0 0 -400px;
        }
    </style>
</head>
<body background="/static/img/山水.png">
<center>

    <div class="all">
        <form style="padding-top: 150px;">
            <span style="color: red" id="msg"></span><br/>
            账号:<input type="test" value="" name="username"  id="username"/>&nbsp;<span id="userTest" style="color: red"></span><br/>
            密码:<input type="password" value="" name="password"  id="password"/>&nbsp;<span id="pwdTest" style="color: red"></span><br/>
            <input type="button" value="登录" id="login"/>
            <input type="button" value="注册" id="register"/>
            <input type="reset" id="reset" value="重置" />
        </form>
    </div>

</center>
</body>
</html>
<script src="/static/js/jquery-3.6.0.js"></script>
<script>
    $(function(){

        $("#login").click(function () {
            var username=$("#username").val();
            var password=$("#password").val();
            $.ajax({
                type:"POST",
                url:"/toLogin",
                dataType:"json",
                data:{"username":username,"password":password},
                success:function (data) {
                    if (data.code!="false"){
                       window.location.href="/success";
                    }else {
                        alert(data.msg);
                    }
                }
            })
        })
        $("#register").click(function(){
            window.location.replace("/register");
        })
    })
</script>