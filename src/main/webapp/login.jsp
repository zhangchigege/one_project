<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <meta content="马上有钱登录,马上有钱注册" name="Keywords">
    <meta content="“马上有钱”用户登录入口，新用户注册可领理财红包。马上有钱平台交易安全有保障，是您投资理财的不二选择！" name="description">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no, email=no"/>
    <meta property="wb:webmaster" content="xxxxxxx"/>
    <link rel="stylesheet" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/index.css"/>
    <link rel="stylesheet" type="text/css" href="lib/css/layer.css"/>
    <script src="lib/js/jquery-2.1.4.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/wapframwork.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
<div class="login_bg">
    <img src="img/login_bg.png">
    <div>
        <div class="login_title" style="display: none;">
            <a href="index.jsp">
                <i class="icon"></i>
            </a>
            <a href="reg.jsp">
                <span>注册</span>
            </a>
        </div>
        <!--登录内容部分-->
        <div class="login_content">
            <div class="username">
                <!--<i class="icon icon_user"></i>-->
                <input type="text" name="mainuser" id="mainuser" placeholder="用户名/手机号"/>
            </div>
            <div class="password">
                <!--<i class="icon icon_pwd"></i>-->
                <input type="password" name="mainpwd" id="mainpwd" placeholder="密码"/>
                <img class="icon_eye" src="img/bkj.png"/>
            </div>
            <span class="btn login_btn">登录</span>
            <p class="forget_btn">
                <a href="forgetPwd.jsp">忘记密码?</a>
            </p>
            <!-- <div class="thirdPartyLoginWrap">
                <ul class="clearfix">
                    <li id="wechatBtn"><a href="javascript:;" class="wechat"><img src="img/loginwxa.png"></a></li>
                </ul>
            </div> -->
        </div>
    </div>
</div>
</body>

<script src="lib/js/layer.js" type="text/javascript" charset="utf-8"></script>
<!--头部-->
<script src="lib/js/RainbowBridge.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    setTitle(".login_title", {'title': '登录'});
</script>
<script src="js/loading.js"></script>
<script src="js/weChatConfig.js"></script>
<!--微信第三方登录openid-->
<script src="js/indexWechat.js"></script>
<script src="js/login.js?v=1.2.2" type="text/javascript" charset="utf-8"></script>
<script>
    $(".thirdPartyLoginWrap").on("click", "li", function () {
        weChatLoginFn(wechatLoginObj);
    });
</script>
</html>
