<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no, email=no"/>
    <meta name="Keywords" content="立即有钱,老板,互联网金融平台,北京p2p公司,北京投资理财,个人投资理财,小额投资理财,北京网贷公司">
    <meta name="description" content="“立即有钱”手机官网，本平台拥有5亿注册资本，账户险、银行存管共同保障用户资金。理财热线：888-888-8888"/>
    <title>立即有钱-老板旗下贷款理财平台-理财就立即有钱</title>
    <link rel="stylesheet" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/index.css"/>
    <link rel="stylesheet" href="lib/css/layer.css" type="text/css">
    <script src="lib/js/jquery-2.1.4.js" type="text/javascript" charset="utf-8"></script>
    <script src="lib/js/jquery.base64.js"></script>
    <script src="lib/js/layer.js"></script>
    <script src="js/wapframwork.js" type="text/javascript" charset="utf-8"></script>
</head>
<body style="position: relative;overflow: hidden;background: #f2f2f0;">
<div class="wrap">
    <section class="container index">
        <!--0807 v1.2 star-->
        <header class="header">
            <a href="notification.html"><i class="icon" id="homeNews"></i></a>
            <span>立即有钱</span>
            <a href="javascript:;" onclick="clickMe()"><i class="icon"></i></a>
        </header>
        <!--0807 v1.2 end-->
        <section style="height: 100%;overflow: auto;">
            <div id="slide" class="slide index-slide">
                <!-- 轮播图片数量可自行增减 -->
                <!--  <div class="img"><img src="img/banner1.png"/></div>
                  <div class="img"><img src="img/banner2.png"/></div>
                  <div class="img"><img src="img/banner3.png"/></div>-->
            </div>
            <!--0807 v1.2 star-->
            <ul class="sub_nav clearfix">
                <li><a href="platformData.html"><img src="img/ptsja.png"><span>平台数据</span></a></li>
                <li><a href="javascript:;" id="inviteBtn"><img src="img/ewma.png"><span>专属二维码</span></a></li>
                <li id="newer"><a href="noviceRaiders.html"><img src="img/xsgla.png"><span>新手攻略</span></a></li>
                <li style="display: none"><a href="invest.html?tab=3"><img src="img/rmtza.png"><span>热门投资</span></a>
                </li>
                <li id="shoppingMall"><a href="javascript:;"><img src="img/jfsca.png"><span>积分商城</span></a></li>
            </ul>
            <!--0807 v1.2 end-->
            <!--新手部分-->
            <section class="newer_list" style="padding-bottom: 0.5rem;background: #fff" id="newer_list">
                <div class="newer">
                    <div class="newer_title">安心贷款 方便大家</div>
                    <div class="row-bot">
                        <div class="center-shadow">
                            <div class="carousel-container">
                                <div id="carousel" class="carousel">
                                </div>
                            </div>
                        </div>
                    </div>
                    <ul class="text_wrap clearfix" id="textDesc">
<!--                         <li><span class="Period"></span><span>锁定期</span></li> -->
<!--                         <li><span class="Amount red"></span><span>剩余金额</span></li> -->
<!--                         <li><span class="EachAmount"></span><span>起投金额</span></li> -->
                    </ul>
                    <div class="newer_detail">
                        <a class="invest_btn" href="invest.jsp?tab=1">立即有钱</a>
                    </div>
<!--                     <a href="javascript:;" class="download_icon" id="goAppBtn"><img src="img/app1.png"></a> -->
                </div>
                <p class="slogan"><i class="icon"></i>账户安全由XXX保险和XXX银行共同保障</p>
            </section>
        </section>
        <!--	页面底部部分-->
        <ul class="footer">
            <li class="footer-item">
                <a href="index.jsp" class="">
                    <i class="icon icon_index active"></i>
                    <p class="icon_title" style="color: #f23029">首页</p>
                </a>
            </li>
            <li class="footer-item">
                <a href="invest.jsp" class="">
                    <i class="icon icon_invest"></i>
                    <p class="icon_title">贷款</p>
                </a>
            </li>
            <li class="footer-item">
                <a href="activity.jsp" class="">
                    <i class="icon icon_activity"></i>
                    <p class="icon_title">商城</p>
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
<!--占屏广告-->
<div class="modal advertisementModal" id="advertisementModal" style="display:none;">
    <div class="advertisement">
        <a href="javascript:;" class="closeAdvertisementModal" onclick="$('#advertisementModal').hide();"></a>
        <a href="javascript:;" id="advertisementUrl"><img src="" style="display: block;width: 100%;" onerror="$('#advertisementModal').hide();"></a>
    </div>
</div>
</body>
<!--小能 start-->
<script src="js/indexHeader.js"></script>
<script type="text/javascript" src="http://dl.ntalker.com/js/xn6/ntkfstat.js?siteid=kf_9092" charset="utf-8"></script>
<!--小能 end-->
<!--插件-->
<script src="js/jquery.featureCarousel.js" type="text/javascript" charset="utf-8"></script>
<!--index-->
<script src="js/index.js?v=1.2.2"></script>
</html>