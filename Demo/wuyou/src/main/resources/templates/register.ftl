<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>注册页面</title>
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
            账号:<input type="test" value="" id="username"/>&nbsp;<span style="color: red" id="susername"></span> <br/>
            密码:<input type="test" value="" id="password"/>&nbsp;<span style="color: red" id="spassword"></span> <br/>
            昵称:<input type="test" value="" id="name"/>&nbsp;<span style="color: red" id="sname"></span> <br/>
            年龄:<input type="test" value="" id="age"/>&nbsp;<span style="color: red" id="sage"></span> <br/>
            性别:<select id="sex">
                    <option id="man">男</option>
                    <option id="woman">女</option>
                </select>&nbsp;<span style="color: red" id="ssex"></span> <br/>
            手机:<input type="test" value="" id="iphone"/>&nbsp;<span style="color: red" id="siphone"></span> <br/>
            <input type="button" value="点击注册" id="register"/>
            <input type="button" value="返回登录" id="toLogin"/>
            <input type="reset"  id="reset" value="重置" />
        </form>
    </div>
</center>
</body>
</html>
<script src="/static/js/jquery-3.6.0.js"></script>
<script>
    $(function(){
        $("#toLogin").click(function(){
            window.location.replace("/login");
        });

        var busername=false;
        var bpassword=false;
        var bname=false;
        var bage=false;
        var biphone=false;

        //账号效验
        $("#username").blur(function () {
            var username=$("#username").val();
            $.ajax({
                type:"post",
                url:"/FindByName",
                dataType:"json",
                data:{"username":username},
                success:function (data) {
                    if(data.code=="false"){
                        $("#susername").html(data.msg);
                        busername=false;
                    }else {
                        $("#susername").html("");
                        busername=true;
                    }
                }
            })

        });

        //密码效验
        $("#password").blur(function () {
            var password=$("#password").val();
            if (password=="" || password.trim()==""){
                $("#spassword").html("密码不能为空");
                bpassword=false;
            }else {
                $("#spassword").html("");
                bpassword=true;
            }
        });

        //昵称效验
        $("#name").blur(function () {
            var name=$("#name").val();
            if (name=="" || name.trim()==""){
                $("#sname").html("昵称不能为空");
                bname=false;
            }else{
                $("#sname").html("");
                bname=true;
            }
        });

        //年龄效验
        $("#age").blur(function () {
            var age=$("#age").val();
            if (age=="" || age.trim()==""){
                $("#sage").html("年龄不能为空");
                bage=false;
            }else{
                $("#sage").html("");
                bage=true;
            }
        });

        //电话效验
        $("#iphone").blur(function () {
            var iphone=$("#iphone").val();
            $.ajax({
                type:"post",
                dataType: "json",
                url:"/FindByIphone",
                data:{"iphone":iphone},
                success:function (data) {
                    if (data.code=="false"){
                        $("#siphone").html(data.msg);
                        biphone=false;
                    }else {
                        $("#siphone").html("");
                        biphone=true;
                    }
                }

            })
        });

        //注册按钮
        $("#register").click(function () {
            if (busername&&bpassword&&bname&&bage&&biphone==true){
                var username=$("#username").val();
                var password=$("#password").val();
                var name=$("#name").val();
                var age=$("#age").val();
                var sex=$("#sex option:selected").text();
                var iphone=$("#iphone").val();
                $.ajax({
                   type:"post",
                   dataType:"json",
                   url:"/InsertUser",
                    data:{"username":username,"password":password,"name":name,"age":age,"sex":sex,"iphone":iphone},
                    success:function (data) {
                        if (data.code=="true"){
                            window.location.href="/registersuccess";
                        }else {
                            $("#msg").html(data.msg);
                        }
                    }
                });
            }else {
                alert("请正确填写信息");
            }
        });


    })
</script>