<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>新增列表信息</title>
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
            <input type="hidden" value="" name="id"  id="id"/><br/>
            状态:<select id="status">
                    <option id="no">未返现</option>
                    <option id="yes">已返现</option>
                </select><br/>
            订单号:<input type="test" value="" name="t_order"  id="t_order"/><br/>
            产品:<input type="test" value="" name="product"  id="product"/><br/>
            来源:<input type="test" value="" name="source"  id="source"/><br/>
            下单时间:<input type="date" value="" name="order_time"  id="order_time"/><br/>
            金额(元):<input type="test" value="" name="amount"  id="amount"/><br/>
            折扣(%):<input type="test" value="" name="discount"  id="discount"/><br/>
            返现金额(元):<input type="test" value="" name="cash_amount"  id="cash_amount" disabled="disabled"/><br/>
            实际折扣(%):<input type="test" value="" name="actual_discount"  id="actual_discount"/><br/>
            收益(元):<input type="test" value="" name="profit"  id="profit" disabled="disabled"/><br/>
            <input type="button" value="确定" id="login"/>
            <input type="button" value="返回" id="register"/>
            <input type="reset" id="reset" value="重置" />
        </form>
    </div>
</center>
</body>
</html>
<script src="/static/js/jquery-3.6.0.js"></script>
<script>
    $(function () {
        $.ajax({
            type:"post",
            url:"/validateLogon",
            datatype:"json",
            success:function (data) {
                if (data.code=="true"){
                    $("#div").css("display","block");
                    $("#id").val(data.user_id);
                }else {
                    alert(data.msg);
                    window.location.href="/login";
                }
            }
        })
        var bdiscount=false;
        var bamount=false;
        var bactual_discount=false;

        $("#discount").blur(function () {
            var discount=$("#discount").val();
            if (discount.trim()!="" && discount!=""){
                bamount=true;
                if (bdiscount&&bamount&&bactual_discount==true){
                    var discount=$("#discount").val();
                    var amount=$("#amount").val();
                    var actual_discount=$("#actual_discount").val();
                    $.ajax({
                        type: "post",
                        url: "/amountAssignment",
                        datatype: "json",
                        data:{"discount":discount,"amount":amount,"actual_discount":actual_discount},
                        success:function (data) {
                            $("#profit").val(data.profit);
                            $("#cash_amount").val(data.cash_amount);
                        }
                    })
                }

            }
        })
        $("#amount").blur(function () {
            var amount=$("#amount").val();
            if (amount.trim()!="" && amount!=""){
                bdiscount=true;
                if (bdiscount&&bamount&&bactual_discount==true){
                    var discount=$("#discount").val();
                    var amount=$("#amount").val();
                    var actual_discount=$("#actual_discount").val();
                    $.ajax({
                        type: "post",
                        url: "/amountAssignment",
                        datatype: "json",
                        data:{"discount":discount,"amount":amount,"actual_discount":actual_discount},
                        success:function (data) {
                            $("#profit").val(data.profit);
                            $("#cash_amount").val(data.cash_amount);
                        }
                    })
                }

            }
        })
        $("#actual_discount").blur(function () {
            var actual_discount=$("#actual_discount").val();
            if (actual_discount.trim()!="" && actual_discount!=""){
                bactual_discount=true;
                if (bdiscount&&bamount&&bactual_discount==true){
                    var discount=$("#discount").val();
                    var amount=$("#amount").val();
                    var actual_discount=$("#actual_discount").val();
                    $.ajax({
                        type: "post",
                        url: "/amountAssignment",
                        datatype: "json",
                        data:{"discount":discount,"amount":amount,"actual_discount":actual_discount},
                        success:function (data) {
                            $("#profit").val(data.profit);
                            $("#cash_amount").val(data.cash_amount);
                        }
                    })
                }

            }
        })
        $("#login").click(function () {
            var user_id=$("#id").val();
            var status=$("#status option:selected").text();
            var t_order=$("#t_order").val();
            var product=$("#product").val();
            var source=$("#source").val();
            var discount=$("#discount").val();
            var order_time=$("#order_time").val();
            var amount=$("#amount").val();
            var cash_amount=$("#cash_amount").val();
            var actual_discount=$("#actual_discount").val();
            var profit=$("#profit").val();
            $.ajax({
                type:"post",
                datatype:"json",
                url:"/save",
                data:{
                        "user_id":user_id,
                        "status":status,
                        "t_order":t_order,
                        "product":product,
                        "source":source,
                        "discount":discount,
                        "order_time":new Date(order_time),
                        "amount":amount,
                        "cash_amount":cash_amount,
                        "actual_discount":actual_discount,
                        "profit":profit
                    },
                success:function (data) {
                    if (data.code=="fales"){
                        alert(data.msg);
                    }else {
                        alert(data.msg);
                        window.location.href="/success";
                    }

                }
            })
        })

        $("#register").click(function () {
            window.history.go(-1);
        })



    });

</script>
