<%@ page import="java.io.PrintWriter" %>
<%--
  Created by IntelliJ IDEA.
  User: lenovopv
  Date: 2018/12/12
  Time: 21:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="viewport"
          content="width=device-width, initial-scale=1">
    <title>我的主页</title>

    <!-- Set render engine for 360 browser -->
    <meta name="renderer" content="webkit">

    <!-- No Baidu Siteapp-->
    <meta http-equiv="Cache-Control" content="no-siteapp"/>

    <link rel="icon" type="image/png" href="../assets/i/favicon.png">

    <!-- Add to homescreen for Chrome on Android -->
    <meta name="mobile-web-app-capable" content="yes">
    <link rel="icon" sizes="192x192" href="../assets/i/app-icon72x72@2x.png">

    <!-- Add to homescreen for Safari on iOS -->
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-title" content="Amaze UI"/>
    <link rel="apple-touch-icon-precomposed" href="../assets/i/app-icon72x72@2x.png">

    <!-- Tile icon for Win8 (144x144 + tile color) -->
    <meta name="msapplication-TileImage" content="assets/i/app-icon72x72@2x.png">
    <meta name="msapplication-TileColor" content="#0e90d2">

    <link rel="stylesheet" href="../assets/css/amazeui.min.css">
    <link rel="stylesheet" href="../assets/css/app.css">
    <script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.js"></script>
    <%--<script src="../assets/js/jquery.min.js"></script>--%>
    <script src="../assets/js/amazeui.min.js"></script>
</head>
<body>
<div>
    <header class="am-topbar am-topbar-inverse">
        <h1 class="am-topbar-brand">
            <a>购物商城</a>
        </h1>

        <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only"
                data-am-collapse="{target: '#doc-topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span
                class="am-icon-bars"></span></button>

        <div class="am-collapse am-topbar-collapse" id="doc-topbar-collapse">
            <ul class="am-nav am-nav-pills am-topbar-nav">
                <li class="am-active"><a>首页</a></li>

            </ul>

            <form class="am-topbar-form am-topbar-left am-form-inline" role="search">
                <div class="am-form-group">
                    <input type="text" class="am-form-field am-input-sm" placeholder="搜索">
                </div>
            </form>

            <div class="am-topbar-left">
                <span style="position: relative;top:10px;">总人数:<%=session.getAttribute("totalNum")%></span>
                <span style="position: relative;top:10px;">已登录人数:<%=session.getAttribute("userNum")%></span>
                <span style="position: relative;top:10px;">游客:<%=session.getAttribute("visitorNum")%></span>
            </div>

            <div class="am-topbar-right">
                <div class="am-dropdown" data-am-dropdown="{boundary: '.am-topbar'}">
                    <button class="am-btn am-btn-secondary am-topbar-btn am-btn-sm am-dropdown-toggle"
                            data-am-dropdown-toggle>其他 <span class="am-icon-caret-down"></span></button>
                    <ul class="am-dropdown-content">
                        <li><a>注册</a></li>
                        <li><a>随便看看</a></li>
                    </ul>
                </div>
            </div>

            <div class="am-topbar-right">
                <span style="position: relative;top:6px">欢迎，<%=session.getAttribute("username")%></span>
                <button class="am-btn am-btn-primary am-topbar-btn am-btn-sm" onclick="logout()">登出</button>
            </div>
        </div>
    </header>


    <button onclick="account()" class="am-btn am-btn-warning am-round" style="position: relative;float: right;right: 1%"
            disabled="disabled" id="submitOrder">提交订单
    </button>


    <ul data-am-widget="gallery" class="am-gallery am-avg-sm-2
  am-avg-md-3 am-avg-lg-4 am-gallery-bordered" data-am-gallery="{  }" id="ulList">
        <%--<li>--%>
        <%--<div class="am-gallery-item">--%>
        <%--<a href="http://s.amazeui.org/media/i/demos/bing-1.jpg" class="">--%>
        <%--<img src="http://s.amazeui.org/media/i/demos/bing-1.jpg" alt="商品不见了!"/>--%>
        <%--<h3>商品名称：</h3>--%>
        <%--<h3>价格：</h3>--%>
        <%--<center> <button class="am-btn am-btn-danger am-round" >加入购物车</button></center>--%>
        <%--</a>--%>
        <%--</div>--%>
        <%--</li>--%>

    </ul>

    <div class="am-modal am-modal-alert" tabindex="-1" id="my-alert">
        <div class="am-modal-dialog">
            <%--<div class="am-modal-hd">Amaze UI</div>--%>
            <div class="am-modal-bd" id="myContent">
            </div>
            <div class="am-modal-footer">
                <span class="am-modal-btn">确定</span>
            </div>
        </div>
    </div>

    <%--<div class="am-alert am-alert-success" data-am-alert id="newAlert">--%>
        <%--<button type="button" class="am-close">&times;</button>--%>
        <%--<p id="pValue"></p>--%>
    <%--</div>--%>

</div>
<script>
    var shoppingCar = [];
    var commodityList = {};

    function logout() {
        window.location.href = '/index.jsp?logout=true';
    }

    function addCommodity(commodityId) {
        var isContained = false;
        var currentCommodity = {
            'commodityId': commodityId,
            'price': commodityList[commodityId - 1]['price'],
            'quantity': 1
        };
        for (var i = 0; i < shoppingCar.length; i++) {
            if (shoppingCar[i]['commodityId'] === currentCommodity['commodityId']) {
                shoppingCar[i]['quantity'] += currentCommodity['quantity'];
                isContained = true;
                break;
            }
        }
        if (!isContained) {
            shoppingCar.push(currentCommodity);
        }

        //按钮取消禁用状态
        var submitOrder = document.getElementById('submitOrder');
        submitOrder.disabled = false;

        // alert('添加成功');
        document.getElementById('myContent').innerText = '添加成功';
        $('#my-alert').modal();

        // document.getElementById('pValue').innerText='添加成功';
        // $('#newAlert').alert();
    }

    function account() {
        $.ajax({
            url: '/shoppingCar',
            type: 'POST',
            // traditional:true,
            // dataType: 'JSON',
            // contentType: "application/json; charset=utf-8",
            data: {
                shoppingCar: JSON.stringify(shoppingCar)
            },
            success: function (res) {
                // console.log(res);
                shoppingCar = [];
                if(res === "1"){
                    document.getElementById('myContent').innerText = '下单成功，满100享受8折优惠';
                }else{
                    document.getElementById('myContent').innerText = '下单成功';
                }
                $('#my-alert').modal();
                var submitOrder = document.getElementById('submitOrder');
                submitOrder.disabled = true;

            },
            error: function (err) {
                console.log(err);
                document.getElementById('myContent').innerText = '下单失败';
                $('#my-alert').modal();
            }

        });
    }

    window.onload = function () {
        var data = {};
        $.ajax({
            url: '/getCommodity',
            type: 'POST',
            // data: JSON.stringify(data),
            success: function (res) {
                commodityList = res;
                var ul = document.getElementById('ulList');
                for (var i = 0; i < res.length; i++) {
                    var li = document.createElement('li');
                    li.innerHTML +=
                        '            <div class="am-gallery-item">\n' +
                        '                <a href="http://s.amazeui.org/media/i/demos/bing-1.jpg" class="">\n' +
                        '                        <img src="http://s.amazeui.org/media/i/demos/bing-1.jpg" alt="商品不见了!"/>\n' +
                        '                        <h3>商品名称：' + res[i]['commodityName'] + '</h3>\n' +
                        '                        <h3>价格：' + res[i]['price'] + '元</h3>\n' +
                        '                </a>\n' +
                        '                   <center><button class="am-btn am-btn-danger am-round" onclick="addCommodity(' + res[i]['commodityId'] + ')">加入购物车</button></center>\n' +
                        '            </div>\n';

                    ul.appendChild(li);
                }

            },
            error: function (err) {
                console.log(err);
            }
        });
    }
</script>

</body>

</html>
