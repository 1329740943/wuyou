<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>个人中心</title>
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
    <div class="all" style="display: none" id="div">
        <form style="padding-top: 10px;" class="from1">
            <span style="color: red" id="msg"></span><br/>
            昵称:<input type="test" value="" name="name"  id="name"/> <span style="color: red" id="sname"></span> <br/>
            性别:<select id="sex"></select><br/>
            年龄:<input type="text" value="" name="age" id="age" /> <span style="color: red" id="sage"></span> <br/>
            手机号:<input type="text" value="" name="iphone" id="iphone" disabled="disabled" /> <span style="color: red" id="siphone"></span> <br/>
            <input type="button" value="修改" id="update"/>
            <input type="button" value="修改密码" id="updatePwd"/>
            <input type="button" value="返回" id="register"/>
        </form>
    </div>
</center>
</body>
</html>
<script src="/static/js/jquery-3.6.0.js"></script>
<script>
    $(function () {
        var id=${id!"0"};
        var str="";
        $.ajax({
            type: "post",
            datatype:"json",
            data:{"id":id},
            url:"/UserHomePage",
            success:function (data) {
                if (data.code=="false"){
                    alert(data.msg);
                    window.location.href="/login";
                }else if (data.code=="error"){
                    alert(data.msg);
                    window.history.go(-1);
                }else if (data.code=="true"){
                    $("#div").css("display","block");
                    $("#name").val(data.tuser.name);
                    if ("男"==data.tuser.sex.toString()){
                        str+="<option >"+data.tuser.sex+"</option>";
                        str+="<option >女</option>";
                    }else if ("女"==data.tuser.sex.toString()){
                        str+="<option >"+data.tuser.sex+"</option>";
                        str+="<option >男</option>";
                    }
                    $("#sex").append(str);
                    $("#age").val(data.tuser.age);
                    $("#iphone").val(data.tuser.iphone);
                }
            }

        })
        var bname=true;
        var bage=true;
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


        $("#update").click(function () {
            if (bname&&bage==true){
                var name=$("#name").val();
                var age=$("#age").val();
                var sex=$("#sex option:selected").text();
                var iphone=$("#iphone").val();
                $.ajax({
                    type: "post",
                    datatype: "json",
                    data: {"id":id,"name":name,"age":age,"sex":sex,"iphone":iphone},
                    url:"/UpdateInformation",
                    success:function (data) {
                        if (data.code=="true"){
                            alert(data.msg)
                            window.location.href="/success";
                        }else {
                            alert(data.msg)
                        }
                    }
                })

            }
            alert("验证错误")

        })


        $("#updatePwd").click(function () {
            window.location.href="/toUpdatePwd";
        })

        $("#register").click(function () {
            window.history.go(-1);
        })


    })
</script>