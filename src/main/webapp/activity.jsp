<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no, email=no"/>
    <meta name="Keywords" content="P2P红包,网贷加息,理财返利">
    <meta name="description" content="“投呗”为您提供丰富的平台活动，P2P理财红包、加息返利活动，为您的投资收益更添一筹，理财就选投呗！"/>
    <title>P2P网贷红包_理财返利_网贷加息活动_投呗</title>
    <link rel="stylesheet" href="css/common.css" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="css/index.css?v=1.2.2"/>
    <link rel="stylesheet" type="text/css" href="lib/css/layer.css"/>
    <!--    <link rel="stylesheet" href="../css/personalCenter.css" type="text/css">
        &lt;!&ndash;插件样式表&ndash;&gt;
        <link rel="stylesheet" href="../lib/css/dropload.css" type="text/css">-->
    <link rel="stylesheet" href="lib/css/swiper.min.css">
</head>
<body>
<div class="wrap activity_page">
    <section class="container">
        <section style="top:0;bottom: 0.95rem; overflow-y: scroll;-webkit-overflow-scrolling: touch;" class="active_wrap">
            <div class="activityBg">
                <ul class="isLogin clearfix" style="display: none;" id="jifenWrap">
                    <li><i class="icon"></i><span id="jf"></span></li>
                    <li><i class="icon"></i><span id="level"></span></li>
                </ul>
                <div class="swiper-container">
                    <div class="swiper-wrapper" id="smallPic">
                        <div class="swiper-slide"><a href="javascript:;"><img src="img/shoppingmallone.png"></a></div>
                        <div class="swiper-slide"><a href="javascript:;"><img src="img/shoppingmalltwo.png"></a></div>
                        <div class="swiper-slide"><a href="javascript:;"><img src="img/shoppingmallthree.png"></a></div>
                        <div class="swiper-slide"><a href="javascript:;"><img src="img/shoppingmallfour.png"></a></div>
                    </div>
                </div>
                <a href="javascript:;" class="jfdh" id="jfdh" style="display: block;"><img src="img/jfdha.png"></a>
            </div>
            <div class="modal" id="modal"  style="display: none;">
                <img src="img/loading.svg" class="loadingImg" >
            </div>
        </section>
        <ul class="footer">
            <li class="footer-item">
                <a href="index.jsp" class="">
                    <i class="icon icon_index"></i>
                    <p class="icon_title">首页</p>
                </a>
            </li>
            <li class="footer-item">
                <a href="invest.jsp" class="">
                    <i class="icon icon_invest"></i>
                    <p class="icon_title">理财</p>
                </a>
            </li>
            <li class="footer-item">
                <a href="activity.jsp" class="">
                    <i class="icon icon_activity"></i>
                    <p class="icon_title" style="color: #f23029">商城</p>
                </a>
            </li>
            <li class="footer-item">
                <a href="personal.jsp" class="">
                    <i class="icon icon_my"></i>
                    <p class="icon_title">我的</p>
                </a>
            </li>
        </ul>
    </section>
</div>
</body>
<script src="lib/js/jquery-2.1.4.js"></script>
<script src="lib/js/jquery.base64.js"></script>
<script src="js/wapframwork.js"></script>

<!--插件-->
<!--<script src="../lib/js/dropload.js"></script>-->
<!--0807 1.2-->
<script src="lib/js/layer.js" type="text/javascript" charset="utf-8"></script>
<script src="lib/js/swiper.min.js"></script>

<!--头部-->
<script src="lib/js/RainbowBridge.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    setTitle(".active_title", {'title': '活动'});
</script>
<script src="js/activity.js?v=1.2.2" type="text/javascript" charset="utf-8"></script>
</html>
