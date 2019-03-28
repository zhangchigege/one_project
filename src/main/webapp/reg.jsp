<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>快速注册</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no, email=no"/>
    <link rel="stylesheet" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/index.css"/>
    <link rel="stylesheet" type="text/css" href="lib/css/layer.css"/>
    <script src="lib/js/jquery-2.1.4.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/wapframwork.js" type="text/javascript" charset="utf-8"></script>
    <script src="//c.dun.163yun.com/js/c.js"></script><!-- 验证码组件js -->
</head>
<body class="">
<div class="login_bg">
    <img src="img/login_bg.png">
    <div>
        <div class="register_title" style="display:none;">
            <a href="javascript:location.href='index.jsp'">
                <i class="icon"></i>
            </a>
            <a class="login_btn" href="login.jsp?z">登录</a>
        </div>
        <div class="register_content">
            <div class="register_part2">
                <div class="item">
                    <input type="number" id="phonenum" placeholder="手机号码" oninput="if(value.length>11)value=value.slice(0,11)"
                           onkeyup="value = value.replace(/[^\d{2}\.]/g, '').replace(/(\.\d{2}).*/g, '$1')"/>
                </div>
                <div class="item">
                    <input type="password" id="password" placeholder="密码6-20位字母数字组合" maxlength="20"/>
                    <img class="icon_eye" src="img/bkj.png"/>
                </div>
                <div class="item">
                    <input type="text" id="valicode" placeholder="短信验证码" maxlength="6"
                           onkeyup="value = value.replace(/[^\d{2}\.]/g, '').replace(/(\.\d{2}).*/g, '$1')"/>
                    <span class="get_code" id="get_valicode">获取验证码</span>
                </div>
                <div style="text-align: center;" id="captcha_div"></div>
                <!--<div class="item">
                    <span class="item_des">推荐人账号（选填）</span>
                    <input class="recommend_input" id="recommend_p" type="text" maxlength="20"/>
                </div>
                <div class="referenceWrap">
                    <div class="referenceTitle" id="referenceTitle"><img src="img/inviteperson.png">邀请人账号</div>
                    <div style="display: none">
                        <input class="recommend_input" id="recommend_p" type="text" maxlength="20" placeholder="邀请人账号（选填）"
                               oninput="recommendFn($(this).val())"/>
                        <div class="recommendMessage"><span></span><span id="recommendName"></span></div>
                    </div>

                </div>-->
                <a href="javascript:;" class="register_btn disabled" style="text-align: center"
                   id="register_btn">注册</a>
                <!--<input type="button" class="register_btn disabled" disabled id="register_btn" value="立即注册">-->

                <div class="xieyi">
                    <!--<input type="checkbox" checked class="if_agree" id="if_agree"/><label for="if_agree">我同意</label>-->
                    <span class="xieyiname">注册账户表示您同意<a href="service_protol.html">《立即有钱注册协议》</a></span>
                </div>
            </div>
            <div class="register_part3" style="display: none;">
                <p class="user"></p>
                <a href="realName_authentication.html#reg" class="wcrz">去完成认证</a>
                <a href="index.jsp" class="xqgg">先去逛逛</a>
            </div>
        </div>
    </div>
    <div class="modal" id="modal"  style="display: none;">
        <img src="img/loading.svg" class="loadingImg" >
    </div>
</div>

</body>


<script src="lib/js/layer.js" type="text/javascript" charset="utf-8"></script>
<!--头部-->
<script src="lib/js/RainbowBridge.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    setTitle(".register_title", {'title': '快速注册'});
    function recommendFn(val) {
        if (val.length) {
            $.ajax({
                type: "GET",
                url: getAPIURL() + "Register/checkPromotion",
                dataType: "json",
                contentType: "application/json",
                data:{
                    account:val
                },
                success:function (data) {
                    if(data.rtn==1){
                        if(data.Message!=""&&data.Message!=null){
                            $("#recommendName").html("您的销售经理: &nbsp;&nbsp;"+data.Message)
                        }
                    }
                    else if(data.rtn==-1){
                        $("#recommendName").html("")
                    }
                }
            })
        }
    }
</script>
<script src="js/reg.js?v=1.2.2" type="text/javascript" charset="utf-8"></script>
</html>