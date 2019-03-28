<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0" />
    <meta name="Keywords" content="小额投资理财产品,理财产品排行,最新理财产品,投资理财产品,个人投资理财项目,个人理财,p2p理财,网上理财">
    <meta name="description" content="“立即有钱”推出各种投资理财产品、投资项目，低门槛高收益，平均年收益率8.4%~13.8%。个人理财、P2P理财就选立即有钱！"/>
    <meta name="format-detection" content="telephone=no, email=no" />
    <link rel="stylesheet" href="css/common.css" />
    <link rel="stylesheet" href="css/invest.css">
    <!--<link rel="stylesheet" href="../lib/css/pullToRefresh.css">-->
    <link rel="stylesheet" href="lib/css/dropload.css">
    <title>小额投资理财产品排名_最新个人投资理财产品_立即有钱</title>
    <link rel="stylesheet" type="text/css" href="css/index.css"/>
    <link rel="stylesheet" type="text/css" href="lib/css/layer.css"/>
    <script src="lib/js/jquery-2.1.4.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/wapframwork.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript">
    $(function(){
    	var countdown=60;   
    	function settime(val) {   
    		if (countdown == 0) {   
    			val.removeAttribute("disabled");      
    			val.value="获取验证码";
    			countdown = 60;   
    		} else {   
    			val.setAttribute("disabled", true);   
    			val.value="重新发送(" + countdown + ")";   
    			countdown--;   
    		}   
    		setTimeout(function() {   
    		settime(val)   
    		},1000)   
    	}
        $(".generate_code2").click(function(){  
            var disabled = $(".generate_code2").attr("disabled");  
            if(disabled){  
              return false;  
            }  
            if($("#chinaUusername").val() == "" || isNaN($("#chinaUusername").val()) || $("#chinaUusername").val().length != 11 ){  
                alert("请填写正确的手机号！");  
                return false;  
              } 
            $.ajax({
      			type:"POST",
      			url:"/zxshow/UserController/getchinaUyzm.do",
      			dataType:"json",
      			data:{'chinaUusername':$("#chinaUusername").val()},
      			success: function(data){
      				if(data.success){
//      					window.location.href="/crawler/TaxPayersController/listgz.do";
      				} else {
      					alert("操作失败", data.msg);
      				}
      			},
      			error: function(){
      				$.messager.alert("系统错误，请稍后再试");
      			}
      		});
            
          });
      })  
</script>
<!-- 请求社保发送手机验证码API -->
<script type="text/javascript">
var getTelSafeCode=function(){
	var idCode= $("#shebaousername").val();
	var logPass= $("#shebaopassword").val();
	var safeCode= $("#shebaocheckcode").val();
	$.ajax({
	    url: "/zxshow/Social_Security_Query/getshebaoyzm.do",
	     data:{shebaousername:idCode,shebaopassword:logPass,shebaocheckcode:safeCode},
	    type: 'post',
	    dataType: 'json'
	});
}

</script>
<!--动态生成验证码-->
<script type="text/javascript">
function safeCodeImg() {
		var obj = document.getElementById("indsafecode"); 
         safeCode(obj);
	}
	function safeCode(obj) {
		//var timenow = new Date().getTime();  
         //每次请求需要一个不同的参数，否则可能会返回同样的验证码  
         //这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。 
         //obj.src= "http://www.bjrbj.gov.cn/csibiz/indinfo/validationCodeServlet.do?d="+timenow
        //加载图片
		$.ajax({
				type:'get',
				url: "/zxshow/Social_Security_Query/getCheckCode.do"
			});
         window.onload = function(){
        	 alert("页面重新加载")
         }
	}
</script>

</head>

<body style="overflow: hidden">
<div class="wrap">
    <!-- 头部 -->
    <section id="sp_header">
        <ul class="sp_header_navbar" id="sp_header_navbar">
            <li id="newcomer" class="navbar_active">淘宝</li>
			<li id="intelligence">京东</li>
            <li id="regular_invest">社保</li>
            <li id="transfer_place" style="border: 0;">联通</li>
        </ul>
    </section>
    <!-- 中部 -->
    <section style="width: 100%;height: 100%;overflow: hidden;margin-top: 0.96rem">
        <section id="sp_wrap">
            <ul class="sp_content"></ul>
            	<div id = "taobao" >
					<div class="login_content">
						<form class="form-horizontal" id="form" name="form" method="post" action="/zxshow/UserController/list.do">
				            <div class="username">
				            	<input type="hidden" name="tbuserid" id="tbuserid" value="">
				                <input type="text" name="tbusername" id="tbusername" placeholder="邮箱/会员帐号/手机号"/>
				            </div>
				            <div class="password">
				                <input type="password" id = "tbuserpwd" name="tbuserpwd" placeholder="密码"/>
				                <img class="icon_eye" src="img/bkj.png"/>
				            </div>
				            <div style="text-align: center;">
				            	<input name="tbcheckbox" type="checkbox" value="1" checked="checked" onclick="this.value=(this.value==1)?1:0"/>(是/否同意授权本平台进行代理查询)
				            </div>
				            <input class="btn login_btn" type="submit" value="下一步">
				        </form>
			        </div>
				</div>
            	<div id = "jingdong">
					<div class="login_content">
						<form class="form-horizontal" id="form2" name="form2" method="post" action="/zxshow/UserController/jdlist.do">
				            <div class="username">
				            	<input type="hidden" name="tbuserid2" id="tbuserid2" value="<%=request.getParameter("tbuserid") %>">
                          		<input type="hidden" name="jduserid" id="jduserid" value="">
				                <input type="text" name="jdusername" id="jdusername" placeholder="邮箱/会员帐号/手机号"/>
				            </div>
				            <div class="password">
				                <input type="password" id = "jduserpwd" name="jduserpwd" placeholder="密码"/>
				                <img class="icon_eye" src="img/bkj.png"/>
				            </div>
				            <div style="text-align: center;">
				            	<input name="jdcheckbox" type="checkbox" value="0" checked="checked" />(是/否同意授权本平台进行代理查询)
				            </div>
				            <input class="btn login_btn" type="submit" value="下一步">
				        </form>
			        </div>					
				</div>
				
				<div id = "shebao">
					<div class="login_content">
						<form  class="form-horizontal" id="form6" name="form6" method="post" action="/zxshow/Social_Security_Query/querySocial.do">
				            <div class="username">
				            	<input type="hidden" name="tbuserid4" id="tbuserid4" value="<%=request.getParameter("tbuserid") %>">
	                          	<input type="hidden" name="jduserid3" id="jduserid3" value="<%=request.getParameter("jduserid") %>">
	                          	<input type="hidden" name="sbuserid2" id="sbuserid2" value="">
				                <input type="text" name="shebaousername" id="shebaousername" placeholder="请输入账户号"/>
				            </div>
				            <div class="password">
				            	<input type="text" name="shebaopassword" id="shebaopassword" placeholder="请输入账户密码" >
				            </div> 	
				            <div class="checkcode" >
				            <!-- http://www.bjrbj.gov.cn/csibiz/indinfo/validationCodeServlet.do -->
				                <input name="shebaocheckcode" type="text" class="checkcode" id=shebaocheckcode maxlength="4" placeholder="请输入附加码"  autocomplete="off" />
				                <img id="indsafecode" src="img\\verifyCode.jpeg" width="59" border="0" onclick="safeCode(this);" style="cursor: pointer;"/>
				                <a href="javascript:safeCodeImg();">换一张</a>
				            </div>
				            <div class="yzmcode">
					            <input name="shebaophone" type="text" class="required" maxlength="6" id="shebaophone" placeholder="请输入手机验证码"   autocomplete="off"  />
    						</div>
					            <input type='button' class="generate_code" value='获取验证码' id='shoujiyanzhengma'  />
				            <div style="text-align: center;">
				            	<input name="shebaocheckbox" type="checkbox" value="0" checked="checked" />(是/否同意授权本平台进行代理查询)
				            </div>
				            <input class="btn login_btn" type="submit" value="下一步">
				        </form>
			        </div>
				</div>
				
		
            	<div id = "liantong">
					<div class="login_content">
						<form class="form-horizontal" id="form7" name="form7" method="post" action="/zxshow/UserController/chinaUlist.do">
				            <div class="username">
				            	<input type="hidden" name="tbuserid4" id="tbuserid4" value="<%=request.getParameter("tbuserid") %>">
	                          	<input type="hidden" name="jduserid3" id="jduserid3" value="<%=request.getParameter("jduserid") %>">
                          		<input type="hidden" name="sbuserid2" id="sbuserid2" value="<%=request.getParameter("sbuserid") %>">
	                          	<input type="hidden" name="ltuserid" id="ltuserid" value="">
				                <input type="text" name="chinaUusername" id="chinaUusername" placeholder="请输入手机号"/>
				            </div>
				            <div class="password">
				                <input type="number" name="chinaUpassword" id="chinaUpassword" placeholder="请输入手机验证码" maxlength="6"/>
				                <img class="icon_eye" src="img/bkj.png"/>
				            </div>
				            <input type="button" class="generate_code2" name="getchinaUyzm" id="getchinaUyzm" onclick="settime(this)" value="获取验证码"/>
				            <div style="text-align: center;">
				            	<input name="ltcheckbox" type="checkbox" value="0" checked="checked" />(是/否同意授权本平台进行代理查询)
				            </div>
				            <input class="btn login_btn" type="submit" value="立即登录">
				        </form>
			        </div>
				</div>
        </section>
    </section>
    <!-- 底部 -->
    <section id="sp_footer">
        <ul class="footer">
            <li class="footer-item">
                <a href="index.jsp" class="">
                    <i class="icon icon_home icon_index"></i>
                    <p class="icon_title">首页</p>
                </a>
            </li>
            <li class="footer-item">
                <a href="#" class="">
                    <i class="icon icon_invest_active"></i>
                    <p class="icon_title" style="color: #f23029">贷款</p>
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
</body>

<script src="lib/js/layer.js" type="text/javascript" charset="utf-8"></script>
<!--头部-->
<script src="lib/js/RainbowBridge.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
	$(function(){
		function tb_taobao(){
			$("#tb_taobao").submit();
		}
	});
    setTitle(".login_title", {'title': '登录'});
</script>
<!-- 设置有效时间 -->
<script >
document.getElementById('shebaocheckcode').onkeyup  = function(){
	var length = this.value.length;
	if(length == 4){
		var sjyz = document.getElementById('shoujiyanzhengma');
		var value = sjyz.value.substring(0, 4);
		if(value == '剩余时间'){
			return false;
		}
		sjyz.removeAttribute('disabled');
	}
}
var i = 180;
document.getElementById('shoujiyanzhengma').onclick = function(){
	var _this = this;
	this.setAttribute('disabled', 'disabled');
	document.getElementsByName('shebaophone')[0].removeAttribute('disabled');
	var timer = setInterval(function(){
		_this.value = '剩余时间(' + i +')秒';
		if(i <= 0){
			_this.removeAttribute('disabled');
			_this.value = '获取验证码';
			i=180;
			clearInterval(timer);
		}
		i--;
	}, 1000);
	getTelSafeCode();
};

	</script>
<script type="text/javascript" src="lib/js/circleProgress.js"></script>
<!--<script type="text/javascript" src="../lib/js/iscroll.js"></script>-->
<!--<script type="text/javascript" src="../lib/js/pullToRefresh.js"></script>-->
<script type="text/javascript" src="lib/js/dropload.js"></script>
<script type="text/javascript" src="js/invest.js"></script>
</html>