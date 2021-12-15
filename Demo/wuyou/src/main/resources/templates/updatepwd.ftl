<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改密码</title>
    <style>
        .all{
            background:#000;
            width:800px;
            height:350px;
            padding:5px;
            position:absolute;
            top:50%;
            left:50%;
            margin:-225px 0 0 -400px;
            color:#f0f0f0;
            filter:alpha(Opacity=60);-moz-opacity:0.6;opacity: 0.6
        }
        .from1{
            width: 300px;
            height: 30px;
            margin: 20px auto;
            padding: 50px 0;
        }
    </style>
</head>
<body background="/static/img/山水.png">
    <center>
        <form style="padding-top: 300px;">
            <span style="color: red" id="msg"></span><br/>
            账号:<input type="text" value="" id="username"/><span style="color: red" id="susername"></span><br/>
            <div style="display: none" id="div1">
                密码:<input type="password" value="" id="pwd"/><span style="color: red" id="spwd"></span><br/>
                再一次输入密码:<input type="password" value="" id="apwd"/><span style="color: red" id="sapwd"></span><br/>
            </div>
            <input type="button" value="点击修改" id="bupdate"/>
            <input type="button" value="返回" id="return"/>
        </form>
    </center>
</body>
</html>
<script src="/static/js/jquery-3.6.0.js"></script>
<script>
    $(function () {
        var busername=false;
        var bpassword=false;
        var bapassword=false;
        $("#username").blur(function () {
            var username =$("#username").val();
            $.ajax({
                type:"post",
                url:"/FindByName",
                dataType:"json",
                data:{"username":username},
                success:function (data) {
                    if(data.code=="false"){
                        $("#susername").html("");
                        $("#div1").attr("style","display: block;")
                        busername=true;
                    }else {
                        $("#susername").html("账号不存在!");
                        $("#div1").attr("style","display: none;")
                        busername=false;
                    }
                }
            });
        });

        //密码效验
        $("#pwd").blur(function () {
            var username =$("#username").val();
            var pwd=$("#pwd").val();
            if (pwd=="" || pwd.trim()==""){
                $("#spwd").html("密码不能为空11");
                bpassword=false;
            }else {
                $("#spwd").html("");
                bpassword=true;
            }
            $.ajax({
               type:"post",
               url:"/FindPwd",
               dataType:"json",
               data:{"username":username,"password":pwd},
                success:function (data) {
                    if(data.code=="false"){
                        $("#spwd").html(data.msg);
                        bpassword=false;
                    }else {
                        $("#spwd").html("");
                        bpassword=true;
                    }
                }
            });
        });

        //密码二次效验
        $("#apwd").blur(function () {
            var apwd=$("#apwd").val();
            var pwd=$("#pwd").val();
            if (apwd=="" || apwd.trim()==""){
                $("#sapwd").html("密码不能为空");
                bapassword=false;
            }else {
                $("#sapwd").html("");
                bapassword=true;
            }
            if (pwd == apwd){
                $("#sapwd").html("");
                bapassword=true;
            }else{
                $("#sapwd").html("密码不相同");
                bapassword=false;
            }
        });

        //点击修改
        $("#bupdate").click(function () {
            if (bapassword&&bpassword&&busername==true){
                var username =$("#username").val();
                var pwd=$("#pwd").val();
                $.ajax({
                    type:"post",
                    url:"/UpdatePwd",
                    dataType:"json",
                    data:{"username":username,"password":pwd},
                    success:function (data) {
                        if (data.code=="true"){
                            alert("修改成功!")
                            window.location.href="/login";
                        }else {
                            alert("修改失败!")
                        }
                    }
                });
            }
        });

        //返回
        $("#return").click(function () {
            window.history.go(-1);
        })


    })
</script>