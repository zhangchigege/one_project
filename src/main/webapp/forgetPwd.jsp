<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>找回密码</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0" />
    <meta name="format-detection" content="telephone=no, email=no" />
    <link rel="stylesheet" href="lib/css/layer.css" type="text/css">
    <link rel="stylesheet" href="css/common.css" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="css/personalCenter.css"/>
    <script src="lib/js/jquery-2.1.4.js"></script>
    <script src="lib/js/jquery.base64.js"></script>
    <script src="lib/js/layer.js"></script>
    <script src="js/wapframwork.js"></script>
</head>
<body>
<div class="find_loginPwd_title header">
    <a href="javascript:history.go(-1)">
        <i class="icon"></i>
    </a>
    <span>找回密码</span>
</div>
<section class="find_loginPwd_wrap">
    <ul class="list">
        <li class="list_item">
            <label>
                <span class="item_text">手机号</span><input type="text" name="phone" placeholder="请输入手机号" id="phone" maxlength="11" minlength="11" >
            </label>
        </li>
        <li class="list_item">
            <label>
                <span class="item_text">验证码</span><input type="text" name="code" placeholder="请输入验证码"  maxlength="6" id="txtMsgCode" style="vertical-align: middle;"><input id="sendCode" type="button" value="点击获取"  class="getCodeBtn">
            </label>
        </li>
    </ul>
    <a href="javascript:;" class="next_step" id="nextstep">下一步</a>
    <p class="text_desc">请输入您在马上有钱绑定的手机号，我们会将您的密码发送至您的手机，请注意查收。</p>
</section>

</body>

<script src="js/forgetPwd.js"></script>
<!--头部-->
<script src="lib/js/RainbowBridge.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    setTitle(".find_loginPwd_title", {'title':'找回密码'});
</script>
</html>
