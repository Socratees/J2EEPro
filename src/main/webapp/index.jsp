<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="viewport"
          content="width=device-width, initial-scale=1">
    <title>登录</title>

    <!-- Set render engine for 360 browser -->
    <meta name="renderer" content="webkit">

    <!-- No Baidu Siteapp-->
    <meta http-equiv="Cache-Control" content="no-siteapp"/>

    <link rel="icon" type="image/png" href="assets/i/favicon.png">

    <!-- Add to homescreen for Chrome on Android -->
    <meta name="mobile-web-app-capable" content="yes">
    <link rel="icon" sizes="192x192" href="assets/i/app-icon72x72@2x.png">

    <!-- Add to homescreen for Safari on iOS -->
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-title" content="Amaze UI"/>
    <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">

    <!-- Tile icon for Win8 (144x144 + tile color) -->
    <meta name="msapplication-TileImage" content="assets/i/app-icon72x72@2x.png">
    <meta name="msapplication-TileColor" content="#0e90d2">

    <link rel="stylesheet" href="assets/css/amazeui.min.css">
    <link rel="stylesheet" href="assets/css/app.css">
    <script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.js"></script>
    <%--<script src="assets/js/jquery.min.js"></script>--%>
    <script src="assets/js/amazeui.min.js"></script>
</head>
<body>

<div>
    <%
        //        if(session.getAttribute("username") != null){
//            response.sendRedirect("/mainPage/user.jsp");
//        }
        if (null != request.getParameter("logout")) {
            if (session != null) {
                session.invalidate();
                session = null;
            }
//            response.sendRedirect("/index.jsp");
        }
    %>
    <%
        Cookie cookie = null;
        String uname = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];
                if (cookie.getName().equals("username")) {
                    uname = cookie.getValue();
                }
            }
        }
    %>
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
                <!--<li><a>项目</a></li>-->
                <!--<li class="am-dropdown" data-am-dropdown>-->
                <!--<a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">-->
                <!--下拉 <span class="am-icon-caret-down"></span>-->
                <!--</a>-->
                <!--<ul class="am-dropdown-content">-->
                <!--<li class="am-dropdown-header">标题</li>-->
                <!--<li><a href="#">1. 去月球</a></li>-->
                <!--<li class="am-active"><a href="#">2. 去火星</a></li>-->
                <!--<li><a href="#">3. 还是回地球</a></li>-->
                <!--<li class="am-disabled"><a href="#">4. 下地狱</a></li>-->
                <!--<li class="am-divider"></li>-->
                <!--<li><a href="#">5. 桥头一回首</a></li>-->
                <!--</ul>-->
                <!--</li>-->
            </ul>

            <form class="am-topbar-form am-topbar-left am-form-inline" role="search">
                <div class="am-form-group">
                    <input type="text" class="am-form-field am-input-sm" placeholder="搜索">
                </div>
            </form>

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

            <!--<div class="am-topbar-right">-->
            <!--<button class="am-btn am-btn-primary am-topbar-btn am-btn-sm">登录</button>-->
            <!--</div>-->
        </div>
    </header>

    <div class="am-g" style="position:relative;top:7em;max-width: 60%">
        <div class="am-u-md-8 am-u-sm-centered">
            <form class="am-form" action="/login" method="post">
                <fieldset class="am-form-set">
                    <input type="text" placeholder="我的账号" name="username" value="<%=uname%>">
                    <input type="password" placeholder="输入密码" name="password">
                </fieldset>
                <button type="submit" class="am-btn am-btn-primary am-btn-block">现在登录</button>
                <%--<button onclick="viewAsVisitor()" class="am-btn am-btn-primary am-btn-block">以游客身份访问</button>--%>

            </form>
            <form class="am-form" action="/login" method="post" style="position: fixed;top: 330px;width: 37.5%">
                <button class="am-btn am-btn-primary am-btn-block">以游客身份访问</button>
                <fieldset>
                    <input type="text" name="visitor" value="visitor" style="visibility: hidden">
                </fieldset>

            </form>

        </div>
    </div>
</div>
<script>
    function viewAsVisitor() {
        $.ajax({
            url: '/login',
            type: 'POST',
            data: {
                visitor: 'visitor',
                // password: 'visitor'
            },
            // success: function (res) {
            //
            // },
            // error: function (err) {
            //     console.log(err);
            //
            // }
        });
    }
</script>

<%--<div style="position: fixed;left: 500px;top:50px;">--%>
<%--<%--%>
<%--//        if(session.getAttribute("username") != null){--%>
<%--//            response.sendRedirect("/mainPage/user.jsp");--%>
<%--//        }--%>
<%--if(null!=request.getParameter("logout")){--%>
<%--if(session!=null){--%>
<%--session.invalidate();--%>
<%--session = null;--%>
<%--}--%>
<%--//            response.sendRedirect("/index.jsp");--%>
<%--}--%>
<%--%>--%>
<%--<%--%>
<%--Cookie cookie = null;--%>
<%--String uname = "";--%>
<%--Cookie[] cookies = request.getCookies();--%>
<%--if(cookies!=null){--%>
<%--for(int i=0;i<cookies.length;i++){--%>
<%--cookie = cookies[i];--%>
<%--if(cookie.getName().equals("username")){--%>
<%--uname = cookie.getValue();--%>
<%--}--%>
<%--}--%>
<%--}--%>
<%--%>--%>
<%--<form action="/login" method="post">--%>
<%--用户名：<input name="username" type="text" value="<%=uname%>"/><br>--%>
<%--密码：<input name="password" type="password" /><br>--%>
<%--<input type="submit" value="确定" />--%>
<%--</form>--%>
<%--</div>--%>

</body>
</html>
