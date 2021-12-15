<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>无忧手写</title>
    <style>
        tr td,th{
            border:1px solid black;
        }
        .mt{
            border-collapse:collapse;
        }
    </style>
</head>
<body >
<center>
    <div style="display: none" id="div">
        <button id="user">个人中心</button>&nbsp;
        <button id="channel">渠道管理</button>&nbsp;
        <button id="toLogin">退出登录</button>
    </div>

    <div style="display: none;margin-top: 10px" id="div2">
        快速搜索订单号:<input type="text" id="search" name="search" />&nbsp; <button id="searchbut" type="button">搜索</button>&nbsp;<button id="save" type="button">添加信息</button>
        <table class="mt" width="95%" border="1" cellpadding="2" cellspacing="1" style="margin-top: 5px;" id="table">
            <tr>
                <th>编号</th>
                <th>状态</th>
                <th>订单号</th>
                <th>产品</th>
                <th>来源</th>
                <th>下单时间</th>
                <th>金额(元)</th>
                <th>折扣(%)</th>
                <th>返现金额(元)</th>
                <th>实际折扣(%)</th>
                <th>收益(元)</th>
                <th>操作</th>
            </tr>
        </table>
        <span id="msg" style="font-size:20px"></span>
    </div>
</center>
</body>
</html>
<script src="/static/js/jquery-3.6.0.js"></script>
<script>
    $(function () {
        var user_id =${id!"0"};
        var str="";
        var str2="";
       if (user_id=="0"){
           alert("请先登录")
           window.location.href="/login";
       }else {
           $("#div").css("display","block");
           $("#div2").css("display","block");

           $.ajax({
               type:"post",
               dataType:"json",
               url:"/userList",
               data:{"user_id":user_id},
               success:function (data) {
                   if (data==""){
                       $("#msg").html("暂无数据");
                   }else {
                       $.each(data,function (i,item) {
                            str+="<tr align='center'>";
                            str+="<td>"+(i+1)+"</td>";
                            str+="<td>"+item.status+"</td>";
                            str+="<td>"+item.t_order+"</td>";
                            str+="<td>"+item.product+"</td>";
                            str+="<td>"+item.source+"</td>";
                            str+="<td>"+item.order_time+"</td>";
                            str+="<td>"+item.amount+"</td>";
                            str+="<td>"+item.discount+"</td>";
                            str+="<td>"+item.cash_amount+"</td>";
                            str+="<td>"+item.actual_discount+"</td>";
                            str+="<td>"+item.profit+"</td>";
                            str+="<td>"+"<button id='update' value='"+item.id+"'>"+ "修改" +"</button>"+"&nbsp;"+"<button id='det' value='"+item.id+"'>"+"删除"+"</button>"+"</td>";
                            str+="</tr>";
                       })
                       $("#table").append(str);
                   }
               }

           });
           $("#searchbut").click(function () {
               var search=$("#search").val();
               if (search==""){
                   window.location.reload();
               }else {
                   $.ajax({
                       type:"post",
                       datatype:"json",
                       data:{"search":search,"user_id":user_id},
                       url:"/quickSearch",
                       success:function (data) {
                           if (data==""){
                               $("#msg").html("暂无数据");
                           }else {
                               $.each(data,function (i,item) {
                                   str2+="<tr align='center'>";
                                   str2+="<td>"+(i+1)+"</td>";
                                   str2+="<td>"+item.status+"</td>";
                                   str2+="<td>"+item.t_order+"</td>";
                                   str2+="<td>"+item.product+"</td>";
                                   str2+="<td>"+item.source+"</td>";
                                   str2+="<td>"+item.order_time+"</td>";
                                   str2+="<td>"+item.amount+"</td>";
                                   str2+="<td>"+item.discount+"</td>";
                                   str2+="<td>"+item.cash_amount+"</td>";
                                   str2+="<td>"+item.actual_discount+"</td>";
                                   str2+="<td>"+item.profit+"</td>";
                                   str2+="<td>"+"<button id='update' value='"+item.id+"'>"+ "修改" +"</button>"+"&nbsp;"+"<button id='det' value='"+item.id+"'>"+"删除"+"</button>"+"</td>";
                                   str2+="</tr>";
                               })
                               $("#table tr:not(:first)").remove();
                               $("#table").append(str2);
                           }
                       }

                   })

               }

           });

           //删除
           $(document).on('click','#det',function(){
               var id=$(this).val();
               $.ajax({
                   type: "post",
                   datatype: "json",
                   data: {"id":id},
                   url: "/detUserList",
                   success:function (data) {
                       if (data.code=="false"){
                           alert(data.msg);
                           window.location.reload();
                       }else {
                           alert(data.msg);
                           window.location.reload();
                       }
                   }
               })

           })

           //登出
           $("#toLogin").click(function () {
                window.location.href="/outLogin";
           })
           //渠道管理
           $("#channel").click(function () {
               window.location.href="/toChannel";
           })
           //个人中心
           $("#user").click(function () {
               window.location.href="/toUser";
           })
           //添加信息
           $("#save").click(function () {
               window.location.href="/toSaveUser";
           })
           //修改信息
           $(document).on('click','#update',function(){
               var id=$(this).val();
               $.ajax({
                   type:"post",
                   datatype: "json",
                   data:{"id":id},
                   url:"/isIdNull",
                   success:function (data) {
                        if (data.code=="false"){
                            alert(data.msg);
                        }else {
                            window.location.href="/toUpdateList";
                        }
                   }
               })
           })



       }
    });
</script>