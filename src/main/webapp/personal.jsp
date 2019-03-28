<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户中心_投资理财管理_立即有钱</title>
    <meta content="立即有钱用户中心,立即有钱理财管理" name="Keywords">
    <meta content="“立即有钱”用户中心包含：平台账户充值提现，银行卡信息管理，查看投资记录、资金明细、奖励优惠等信息。" name="description">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0" />
    <meta name="format-detection" content="telephone=no, email=no" />
    <link rel="stylesheet" href="css/common.css" />
    <link rel="stylesheet" type="text/css" href="css/index.css?v=1.2.1"/>
    <link rel="stylesheet" type="text/css" href="lib/css/layer.css"/>
    <script src="lib/js/jquery-2.1.4.js" type="text/javascript" charset="utf-8"></script>
    <script src="lib/js/jquery.base64.js" type="text/javascript" charset="utf-8"></script>
    <script src="lib/js/layer.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/wapframwork.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
<!--页面头部-->
<div class="my_header">
    <div class="my_headertop">
        <a href="notification.html#personal" class="my_iconmessage"><i ></i></a>
        <span id="user_info"></span>
        <a href="setting.jsp" class="my_iconsetting"><i ></i></a>
    </div>
    <div class="my_headercenter">
        <p class="account_type"></p>
        <p>可用余额&nbsp;&nbsp;(元)</p>
        <p class="num"><span id="balance_num"></span><span id="balance_dec" class="num_deci"></span></p>
    </div>
    <div class="my_headerbottom">
        <div class="left">
            <p>待收总额&nbsp;&nbsp;(元)</p>
            <p class="num"><span id="waitNum_num"></span><span id="waitNum_dec" class="num_deci"></span></p>
        </div>
        <div class="right">
            <p>累计收益&nbsp;&nbsp;(元)</p>
            <p class="num"><span id="returnIn_num"></span><span id="returnIn_dec" class="num_deci"></span></p>
        </div>
    </div>
</div>
<!--页面主体内容部分-->
<div class="my_content">
    <div class="my_content_top">
        <img class="ct_bg" src="img/ct_bg.png" alt="" />
        <div class="left" id="recharge_btn">
            <i class="icon_charge"></i>
            <span class="bold">充值</span>
        </div>
        <div class="right withdraw_btn">
            <i class="icon_withdraw"></i>
            <span class="bold">提现</span>
        </div>
    </div>
    <div class="my_content_bottom">
        <ul>
            <li class="item">
                <a href="investmentRecord.html">
                    <div>
								<span class="icon_wrap">
									<i class="icon icon_tzjl"></i>
								</span>
                        <span class="item_detail">理财</span>
                        <i class="icon icon_zhankai"></i>
                    </div>
                </a>
            </li>
            <li class="item">
                <a href="fund_details.html">
                    <div>
								<span class="icon_wrap">
									<i class="icon_tzmx"></i>
								</span>
                        <span class="item_detail">贷款</span>
                        <i class="icon icon_zhankai"></i>
                    </div>
                </a>
            </li>
            <li class="item">
                <a href="coupon.html">
                    <div>
								<span class="icon_wrap">
									<i class="icon icon_jlyh"></i>
								</span>
                        <span class="item_detail">信用评分 </span>
                        <span class="couponNum"></span>
                        <i class="icon icon_zhankai"></i>
                    </div>
                </a>
            </li>
            <li class="item" id="addBank">
                <a href="javascript:;" >
                    <div>
								<span class="icon_wrap">
									<i class="icon icon_card"></i>
								</span>
                        <span class="item_detail">我的额度</span>
                        <i class="icon icon_zhankai"></i>
                    </div>
                </a>
            </li>
            <li class="item">
                <a href="inviteF.html">
                    <div>
								<span class="icon_wrap">
									<i class="icon icon_ewm"></i>
								</span>
                        <span class="item_detail">足迹</span>
                        <i class="icon icon_zhankai"></i>
                    </div>
                </a>
            </li>
             <li class="item" id="addBank">
                <a href="javascript:;" >
                    <div>
								<span class="icon_wrap">
									<i class="icon icon_card"></i>
								</span>
                        <span class="item_detail">积分</span>
                        <i class="icon icon_zhankai"></i>
                    </div>
                </a>
            </li>
        </ul>
    </div>
    <div style="height: 1rem;">

    </div>
</div>
<!--	页面底部部分-->
<ul class="footer" style="background: #fff;">
    <li class="footer-item">
        <a href="index.jsp" class="">
            <i class="icon icon_index"></i>
            <p class="icon_title">首页</p>
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
        <a href="#" class="">
            <i class="icon icon_my active"></i>
            <p class="icon_title" style="color: #f23029">我的</p>
        </a>
    </li>
</ul>
</body>

<script src="js/personal.js?v=1.2.1" type="text/javascript" charset="utf-8"></script>
</html>
