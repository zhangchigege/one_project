<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户中心_投资理财管理_立即有钱</title>
    <meta content="立即有钱用户中心,立即有钱理财管理" name="Keywords">
    <meta content="“立即有钱”用户中心包含：平台账户充值提现，银行卡信息管理，查看投资记录、资金明细、奖励优惠等信息。" name="description">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0" />
    <meta name="format-detection" content="telephone=no, email=no" />
    <link rel="stylesheet" href="../css/common.css" />
    <link rel="stylesheet" type="text/css" href="../css/index.css?v=1.2.1"/>
    <link rel="stylesheet" type="text/css" href="../lib/css/layer.css"/>
    <script src="../lib/js/jquery-2.1.4.js" type="text/javascript" charset="utf-8"></script>
    <script src="../lib/js/jquery.base64.js" type="text/javascript" charset="utf-8"></script>
    <script src="../lib/js/layer.js" type="text/javascript" charset="utf-8"></script>
    <script src="../js/wapframwork.js" type="text/javascript" charset="utf-8"></script>
    
    <link rel="stylesheet" href="../css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="../css/css-table.css" />
	<script type="text/javascript" src="../js/style-table.js"></script>
	<script src="../js/jquery-1.8.3.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript" src="../js/jquery-ui.js"></script> 
	<script type="text/javascript">
	$(function(){
		/*************************淘宝start*********************/
		//淘宝用户信息
		var taobaoinfoformlist = '${taobaoinfoformlist}';
		var tbusername = "";//姓名
		var tbage = "";//年龄
		var tbgender = "";//性别
		var tbbirthdate = "";//出生年月
		var tbidtype = "";//证件类型
		var tbidnumber = "";//证件号码
		
		var tbcreditscore = "";//信用积分
		var tbfeedbackrate = "";//好评率
		var tbcreditlevel = "";//信用等级
		var tbcreditstanding = "";//信用度
		var tbtotalamount = "";//最近一年购买总金额
		var tbaverageamount = "";//平均每单金额
		var tbdisputes = "";//交易纠纷数
		var tbdisputesnumber = "";//拍下未付款笔数
		
		var tbnearlyonemonth = "";//近第一个月份
		var tbnearlyoneconsumetotal = "";//近第一个消费笔数
		var tbnearlyoneconsumemoney = "";//近第一个消费总金额
		var tbnearlytwomonth = "";//近第二个月份
		var tbnearlytwoconsumetotal = "";//近第二个消费笔数
		var tbnearlytwoconsumemoney = "";//近第二个消费总金额
		var tbnearlythreemonth = "";//近第三个月份
		var tbnearlythreeconsumetotal = "";//近第三个消费笔数
		var tbnearlythreeconsumemoney = "";//近第三个消费总金额
		if(taobaoinfoformlist != ""){
			<c:forEach var="taobaoinfoformlist" items="${taobaoinfoformlist}"> 
				tbusername = "${taobaoinfoformlist.tbusername}"; 
				tbage = "${taobaoinfoformlist.tbage}";
				tbgender = "${taobaoinfoformlist.tbgender}";
				tbbirthdate = "${taobaoinfoformlist.tbbirthdate}";
				tbidtype = "${taobaoinfoformlist.tbidtype}";
				tbidnumber = "${taobaoinfoformlist.tbidnumber}";
				
				tbcreditscore = "${taobaoinfoformlist.tbcreditscore}";
				tbfeedbackrate = "${taobaoinfoformlist.tbfeedbackrate}";
				tbcreditlevel = "${taobaoinfoformlist.tbcreditlevel}";
				tbcreditstanding = "${taobaoinfoformlist.tbcreditstanding}";
				tbtotalamount = "${taobaoinfoformlist.tbtotalamount}";
				tbaverageamount = "${taobaoinfoformlist.tbaverageamount}";
				tbdisputes = "${taobaoinfoformlist.tbdisputes}";
				tbdisputesnumber = "${taobaoinfoformlist.tbdisputesnumber}";
				
				tbnearlyonemonth = "${taobaoinfoformlist.tbnearlyonemonth}";
				tbnearlyoneconsumetotal = "${taobaoinfoformlist.tbnearlyoneconsumetotal}";
				tbnearlyoneconsumemoney = "${taobaoinfoformlist.tbnearlyoneconsumemoney}";
				tbnearlytwomonth = "${taobaoinfoformlist.tbnearlytwomonth}";
				tbnearlytwoconsumetotal = "${taobaoinfoformlist.tbnearlytwoconsumetotal}";
				tbnearlytwoconsumemoney = "${taobaoinfoformlist.tbnearlytwoconsumemoney}";
				tbnearlythreemonth = "${taobaoinfoformlist.tbnearlythreemonth}";
				tbnearlythreeconsumetotal = "${taobaoinfoformlist.tbnearlythreeconsumetotal}";
				tbnearlythreeconsumemoney = "${taobaoinfoformlist.tbnearlythreeconsumemoney}";
			</c:forEach>
		}
		$("#tbusername").text(tbusername);
		$("#tbage").text(tbage);
		$("#tbgender").text(tbgender);
		$("#tbbirthdate").text(tbbirthdate);
		$("#tbidtype").text(tbidtype);
		$("#tbidnumber").text(tbidnumber);
		
		$("#tbcreditscore").text(tbcreditscore);
		$("#tbfeedbackrate").text(tbfeedbackrate);
		$("#tbcreditlevel").text(tbcreditlevel);
		$("#tbcreditstanding").text(tbcreditstanding);
		$("#tbtotalamount").text(tbtotalamount);
		$("#tbaverageamount").text(tbaverageamount);
		$("#tbdisputes").text(tbdisputes);
		$("#tbdisputesnumber").text(tbdisputesnumber);
		
		$("#tbnearlyonemonth").text(tbnearlyonemonth);
		$("#tbnearlyoneconsumetotal").text(tbnearlyoneconsumetotal);
		$("#tbnearlyoneconsumemoney").text(tbnearlyoneconsumemoney);
		$("#tbnearlytwomonth").text(tbnearlytwomonth);
		$("#tbnearlytwoconsumetotal").text(tbnearlytwoconsumetotal);
		$("#tbnearlytwoconsumemoney").text(tbnearlytwoconsumemoney);
		$("#tbnearlythreemonth").text(tbnearlythreemonth);
		$("#tbnearlythreeconsumetotal").text(tbnearlythreeconsumetotal);
		$("#tbnearlythreeconsumemoney").text(tbnearlythreeconsumemoney);
		/*************************淘宝end*********************/
		
		/*************************京东start*********************/
		//京东用户信息
		var jingdonginfoformlist = '${jingdonginfoformlist}';
		var jdusername = "";//姓名
		var jdage = "";//年龄
		var jdgender = "";//性别
		var jdbirthDate = "";//出生年月
		var jdidtype = "";//证件类型
		var jdidnumber = "";//证件号码
		var jdname = "";//会员名
		var jdulevel = "";//会员等级
		
		var jdxylevel = "";//信用等级
		var jdsxzed = "";//授信总额度
		var jdfqje = "";//当前可分期金额
		var jdyyzed ="";//已用总额度
		
		var jdnearlyonemonth = "";//近第一个月份
		var jdnearlyoneconsumetotal = "";//近第一个消费笔数
		var jdnearlyoneconsumemoney = "";//近第一个消费总金额
		var jdnearlytwomonth = "";//近第二个月份
		var jdnearlytwoconsumetotal = "";//近第二个消费笔数
		var jdnearlytwoconsumemoney = "";//近第二个消费总金额
		var jdnearlythreemonth = "";//近第三个月份
		var jdnearlythreeconsumetotal = "";//近第三个消费笔数
		var jdnearlythreeconsumemoney = "";//近第三个消费总金额
		if(jingdonginfoformlist != ""){
			<c:forEach var="jingdonginfoformlist" items="${jingdonginfoformlist}"> 
				jdusername = "${jingdonginfoformlist.jdusername}"; 
				jdage = "${jingdonginfoformlist.jdage}";
				jdgender = "${jingdonginfoformlist.jdgender}";
				jdbirthDate = "${jingdonginfoformlist.jdbirthdate}";
				jdidtype = "${jingdonginfoformlist.jdidtype}";
				jdidnumber = "${jingdonginfoformlist.jdidnumber}";
				jdname = "${jingdonginfoformlist.jdname}";
				jdulevel = "${jingdonginfoformlist.jdulevel}";
				
				jdxylevel = "${jingdonginfoformlist.jdxylevel}"; 
				jdsxzed = "${jingdonginfoformlist.jdsxzed}";
				jdfqje = "${jingdonginfoformlist.jdfqje}"; 
				jdyyzed = "${jingdonginfoformlist.jdyyzed}";
				
				jdnearlyonemonth = "${jingdonginfoformlist.jdnearlyonemonth}"; 
				jdnearlyoneconsumetotal = "${jingdonginfoformlist.jdnearlyoneconsumetotal}"; 
				jdnearlyoneconsumemoney = "${jingdonginfoformlist.jdnearlyoneconsumemoney}"; 
				jdnearlytwomonth = "${jingdonginfoformlist.jdnearlytwomonth}"; 
				jdnearlytwoconsumetotal = "${jingdonginfoformlist.jdnearlytwoconsumetotal}"; 
				jdnearlytwoconsumemoney = "${jingdonginfoformlist.jdnearlytwoconsumemoney}"; 
				jdnearlythreemonth = "${jingdonginfoformlist.jdnearlythreemonth}"; 
				jdnearlythreeconsumetotal = "${jingdonginfoformlist.jdnearlythreeconsumetotal}"; 
				jdnearlythreeconsumemoney = "${jingdonginfoformlist.jdnearlythreeconsumemoney}"; 
			</c:forEach> 
		}
		$("#jdusername").text(jdusername);
		$("#jdage").text(jdage);
		$("#jdgender").text(jdgender);
		$("#jdbirthDate").text(jdbirthDate);
		$("#jdidtype").text(jdidtype);
		$("#jdidnumber").text(jdidnumber);
		$("#jdname").text(jdname);
		$("#jdulevel").text(jdulevel);
		
		$("#jdxylevel").text(jdxylevel);
		$("#jdsxzed").text(jdsxzed);
		$("#jdfqje").text(jdfqje);
		$("#jdyyzed").text(jdyyzed);
		
		$("#jdnearlyonemonth").text(jdnearlyonemonth);
		$("#jdnearlyoneconsumetotal").text(jdnearlyoneconsumetotal);
		$("#jdnearlyoneconsumemoney").text(jdnearlyoneconsumemoney);
		$("#jdnearlytwomonth").text(jdnearlytwomonth);
		$("#jdnearlytwoconsumetotal").text(jdnearlytwoconsumetotal);
		$("#jdnearlytwoconsumemoney").text(jdnearlytwoconsumemoney);
		$("#jdnearlythreemonth").text(jdnearlythreemonth);
		$("#jdnearlythreeconsumetotal").text(jdnearlythreeconsumetotal);
		$("#jdnearlythreeconsumemoney").text(jdnearlythreeconsumemoney);
		/*************************京东end*********************/
		
		/*************************社保start*********************/
		//社保用户信息
		var sbUsers = '${sbUsers}';
		var comPany_Name = "";//单位名称
		var unified_Social_Credit_Code = "";//统一社会信用代码
		var social_Security_Registration_Number = "";//社会保险登记号
		var county = "";//所属区县
		var userName = "";//用户名称
		var id_Number = "";//身份证号
		var date = "";//出生日期
		var sex = "";//性别
		var nation = "";	//民族
		var state = "";//国家
		var pin = "";//个人身份
		var date_Of_Employment = "";//参加工作日期
		var account_Properties = "";//户口性质
		var registered_Permanent_Residence = "";//户口所在地
		var place_Of_Abode = "";//居住地
		var ydnearlytwosetmealmoney = "";//最近2月账单套餐金额
		var phone = "";//参保人电话
		var salary = "";//参保人工资
		var bank_Account = "";//银行账号
		if(sbUsers != ""){
			<c:forEach var="sbUsers" items="${sbUsers}"> 
			uid = "${sbUsers.uid}"; 
			comPany_Name = "${sbUsers.comPany_Name}";
			unified_Social_Credit_Code = "${sbUsers.unified_Social_Credit_Code}";
			social_Security_Registration_Number = "91110108746123250C";
			county = "${sbUsers.county}";
			userName = "${sbUsers.userName}";
			id_Number = "${sbUsers.id_Number}";
			date = "${sbUsers.date}";
			sex = "${sbUsers.sex}";
			nation = "${sbUsers.nation}";
			state = "${sbUsers.state}"; 
			pin = "${sbUsers.pin}";
			date_Of_Employment = "${sbUsers.date_Of_Employment}";
			account_Properties = "${sbUsers.account_Properties}";
			registered_Permanent_Residence = "${sbUsers.registered_Permanent_Residence}";
			place_Of_Abode = "${sbUsers.place_Of_Abode}";
			phone = "${sbUsers.phone}";
			salary = "${sbUsers.salary}";
			bank_Account = "${sbUsers.bank_Account}";
			</c:forEach> 
		}
		$("#uid").text(uid);
		$("#comPany_Name").text(comPany_Name);
		$("#unified_Social_Credit_Code").text(unified_Social_Credit_Code);
		$("#social_Security_Registration_Number").text(social_Security_Registration_Number);
		$("#county").text(county);
		$("#userName").text(userName);
		$("#id_Number").text(id_Number);
		$("#date").text(date);
		$("#sex").text(sex);
		$("#nation").text(nation);
		$("#state").text(state);
		$("#pin").text(pin);
		$("#date_Of_Employment").text(date_Of_Employment);
		$("#account_Properties").text(account_Properties);
		$("#registered_Permanent_Residence").text(registered_Permanent_Residence);
		$("#place_Of_Abode").text(place_Of_Abode);
		$("#phone").text(phone);
		$("#salary").text(salary);
		$("#bank_Account").text(bank_Account);
		/*************************社保end*********************/
		/*************************联通start*********************/
		//中国联通用户信息
		var chinaUnicomInfoFormlist = '${chinaUnicomInfoFormlist}';
		var chinaupersonname = "";//姓名
		var chinaupersonsex = "";//性别
		var chinaupapernum = "";//身份证
		var chinaupaperaddress = "";//地址
		
		if(chinaUnicomInfoFormlist != ""){
			<c:forEach var="chinaUnicomInfoFormlist" items="${chinaUnicomInfoFormlist}"> 
				chinaupersonname = "${chinaUnicomInfoFormlist.chinaupersonname}"; 
				chinaupersonsex = "${chinaUnicomInfoFormlist.chinaupersonsex}";
				chinaupapernum = "${chinaUnicomInfoFormlist.chinaupapernum}";
				chinaupaperaddress = "${chinaUnicomInfoFormlist.chinaupaperaddress}";
			</c:forEach> 
		}
		$("#chinaupersonname").text(chinaupersonname);
		$("#chinaupersonsex").text(chinaupersonsex);
		$("#chinaupapernum").text(chinaupapernum);
		$("#chinaupaperaddress").text(chinaupaperaddress);
		/*************************联通end*********************/
	});

</script>
</head>
<body text="">
<h1>淘宝</h1>
<!------------------------------------ 淘宝start --------------------------------------->
<!--用户信息-->
<table id="tbtravel1">
	<caption><h3>用户信息</h3></caption>       
    <tbody>
    	<tr>
    		<th scope="row">姓名</th>
            <td id="tbusername"></td>
            <th scope="row">年龄</th>
            <td id="tbage"></td>
        </tr>
        <tr>
        	<th scope="row">性别</th>
            <td id="tbgender"></td>
            <th scope="row">出生年月</th>
            <td id="tbbirthdate"></td>
        </tr>
        <tr>
        	<th scope="row">证件类型</th>
            <td id="tbidtype"></td>
            <th scope="row">证件号码</th>
            <td id="tbidnumber"></td>
        </tr> 
    </tbody>
<!--用户信息-->
</table>
<!--淘宝信息-->
<table id="tbtravel2">
	<caption><h3>淘宝信息</h3></caption>       
    <tbody>
    	<tr>
    		<th scope="row">信用积分</th>
            <td id="tbcreditscore"></td>
            <th scope="row">好评率</th>
            <td id="tbfeedbackrate"></td>
        </tr>
        
        <tr>
        	<th scope="row">信用等级</th>
            <td id="tbcreditlevel"></td>
            <th scope="row">信用度</th>
            <td id="tbcreditstanding"></td>
        </tr>
        
        <tr>
        	<th scope="row">最近一年购买总金额</th>
            <td id="tbtotalamount"></td>
            <th scope="row">平均每单金额</th>
            <td id="tbaverageamount"></td>
        </tr>  
        
         <tr>
        	<th scope="row">交易纠纷数</th>
            <td id="tbdisputes"></td>
            <th scope="row">拍下未付款笔数</th>
            <td id="tbdisputesnumber"></td>
        </tr>
    </tbody>
</table>    
<!--淘宝信息-->

<!--收货地址-->
<table id="tbtravel">
	<caption><h3>收货地址</h3></caption>
    <thead>    
        <tr>
            <th scope="col">收货人</th>
            <th scope="col">所在地区</th>
            <th scope="col">详细地址</th>
            <th scope="col">邮编</th>
            <th scope="col">电话手机</th>
        </tr>        
    </thead>   
    <tbody>
    <c:forEach items="${taobaoaddressFormlist}" var="taobaoaddressFormlist" >
		<tr>
			<td>${taobaoaddressFormlist.tbconsignee}</td>
			<td>${taobaoaddressFormlist.tbareaname}</td>
			<td>${taobaoaddressFormlist.tbaddress}</td>
			<td>${taobaoaddressFormlist.tbzip}</td>
			<td>${taobaoaddressFormlist.tbphonenumber}</td>
		</tr>
    </c:forEach> 
    </tbody>
</table>  
<!--收货地址-->

<!--近3个月月消费统计-->
<table id="tbtrave4">
	<caption><h3>近3个月月消费统计</h3></caption>
    <thead>    
        <tr>
            <th scope="col">月份</th>
            <th scope="col">消费笔数</th>
            <th scope="col">消费总金额</th>
        </tr>        
    </thead>   
    <tbody>
		<tr>
			<td id = "tbnearlyonemonth"></td>
			<td id = "tbnearlyoneconsumetotal"></td>
			<td id = "tbnearlyoneconsumemoney"></td>
		</tr>
		<tr>
			<td id = "tbnearlytwomonth"></td>
			<td id = "tbnearlytwoconsumetotal"></td>
			<td id = "tbnearlytwoconsumemoney"></td>
		</tr>
		<tr>
			<td id = "tbnearlythreemonth"></td>
			<td id = "tbnearlythreeconsumetotal"></td>
			<td id = "tbnearlythreeconsumemoney"></td>
		</tr>
    </tbody>
    <!--近3个月月消费统计-->
</table>
<!------------------------------------ 淘宝end --------------------------------------->
<!------------------------------------ 京东start --------------------------------------->
<!--用户信息-->
<h1>京东</h1>
<table id="jdtravel1">
	<caption><h3>用户信息</h3></caption>       
    <tbody>
    	<tr>
    		<th scope="row">姓名</th>
            <td id="jdusername"></td>
            <th scope="row">年龄</th>
            <td id="jdage"></td>
        </tr>
        <tr>
        	<th scope="row">性别</th>
            <td id="jdgender"></td>
            <th scope="row">出生年月</th>
            <td id="jdbirthDate"></td>
        </tr>
        <tr>
        	<th scope="row">证件类型</th>
            <td id="jdidtype"></td>
            <th scope="row">证件号码</th>
            <td id="jdidnumber"></td>
        </tr>
        <tr>
        	<th scope="row">会员名</th>
            <td id="jdname"></td>
            <th scope="row">会员等级</th>
            <td id="jdulevel"></td>
        </tr> 
    </tbody>
<!--用户信息-->
</table>
<!--京东白条信息-->
<table id="jdtravel2">
	<caption><h3>京东白条信息</h3></caption>       
    <tbody>
        <tr>
        	<th scope="row">信用等级</th>
            <td id="jdxylevel"></td>
            <th scope="row">授信总额度</th>
            <td id="jdsxzed"></td>
        </tr>  
        
         <tr>
        	<th scope="row">当前可分期金额</th>
            <td id="jdfqje"></td>
            <th scope="row">已用总额度</th>
            <td id="jdyyzed"></td>
        </tr>
    </tbody>
</table>    
<!--京东白条信息-->

<!--收货地址-->
<table id="jdtravel">
	<caption><h3>收货地址</h3></caption>
    <thead>    
        <tr>
            <th scope="col">收货人</th>
            <th scope="col">所在地区</th>
            <th scope="col">详细地址</th>
            <th scope="col">手机</th>
            <th scope="col">固定电话</th>
            <th scope="col">电子邮箱</th>
        </tr>        
    </thead>   
    <tbody>
    <c:forEach items="${jingdongAddressFormlist}" var="jingdongAddressFormlist" >
		<tr>
			<td>${jingdongAddressFormlist.jdconsignee}</td>
			<td>${jingdongAddressFormlist.jdareaname}</td>
			<td>${jingdongAddressFormlist.jdaddress}</td>
			<td>${jingdongAddressFormlist.jdphone}</td>
			<td>${jingdongAddressFormlist.jdphonenumber}</td>
			<td>${jingdongAddressFormlist.jdemail}</td>
		</tr>
    </c:forEach>
    </tbody>
</table>  
<!--收货地址-->

<!--近3个月月消费统计-->
<table id="jdtrave4">
	<caption><h3>近3个月月消费统计</h3></caption>
    <thead>    
        <tr>
            <th scope="col">月份</th>
            <th scope="col">消费笔数</th>
            <th scope="col">消费总金额</th>
        </tr>        
    </thead>   
    <tbody>
		<tr>
			<td id = "jdnearlyonemonth"></td>
			<td id = "jdnearlyoneconsumetotal"></td>
			<td id = "jdnearlyoneconsumemoney"></td>
		</tr>
		<tr>
			<td id = "jdnearlytwomonth"></td>
			<td id = "jdnearlytwoconsumetotal"></td>
			<td id = "jdnearlytwoconsumemoney"></td>
		</tr>
		<tr>
			<td id = "jdnearlythreemonth"></td>
			<td id = "jdnearlythreeconsumetotal"></td>
			<td id = "jdnearlythreeconsumemoney"></td>
		</tr>
    </tbody>
</table>
<!--近3个月月消费统计-->

<!------------------------------------ 京东end --------------------------------------->
<!------------------------------------ 社保start --------------------------------------->
<!--用户信息-->
<h1>社保</h1>
<table id="sbtravel1">
	<caption><h3>用户信息</h3></caption>       
    <tbody>
    	<tr>
    		<th scope="row">单位名称</th>
            <td id="comPany_Name"></td>
            <th scope="row">统一社会信用代码</th>
            <td id="unified_Social_Credit_Code"></td>
        </tr>
        <tr>
        	<th scope="row">社会保险登记号</th>
            <td id="social_Security_Registration_Number"></td>
            <th scope="row">所属区县</th>
            <td id="county"></td>
        </tr>
        <tr>
        	<th scope="row">用户名称</th>
            <td id="userName"></td>
            <th scope="row">身份证号</th>
            <td id="id_Number"></td>
        </tr>
        <tr>
        	<th scope="row">出生日期</th>
            <td id="date"></td>
            <th scope="row">性别</th>
            <td id="sex"></td>
        </tr> 
        <tr>
        	<th scope="row">民族</th>
            <td id="nation"></td>
            <th scope="row">国家</th>
            <td id="state"></td>
        </tr> 
        <tr>
        	<th scope="row">个人身份</th>
            <td id="pin"></td>
            <th scope="row">参加工作日期</th>
            <td id="date_Of_Employment"></td>
        </tr> 
        <tr>
        	<th scope="row">户口性质</th>
            <td id="account_Properties"></td>
            <th scope="row">户口所在地</th>
            <td id="registered_Permanent_Residence"></td>
        </tr> 
        <tr>
        	<th scope="row">居住地</th>
            <td id="place_Of_Abode"></td>
            <th scope="row">参保人电话</th>
            <td id="phone"></td>
        </tr> 
        <tr>
        	<th scope="row">参保人工资</th>
            <td id="salary"></td>
            <th scope="row">银行账号</th>
            <td id="bank_Account"></td>
        </tr> 
    </tbody>
<!--用户信息-->
</table>
<!------------------------------------ 社保end --------------------------------------->
<!------------------------------------ 联通start --------------------------------------->
<!--用户信息-->
<h1>联通</h1>
<table id="lttravel1">
	<caption><h3>用户信息</h3></caption>       
    <tbody>
    	<tr>
    		<th scope="row">姓名</th>
            <td id="chinaupersonname"></td>
            <th scope="row">身份证</th>
            <td id="chinaupapernum"></td>
        </tr>
        <tr>
        	<th scope="row">性别</th>
            <td id="chinaupersonsex"></td>
            <th scope="row">地址</th>
            <td id="chinaupaperaddress"></td>
        </tr> 
    </tbody>
</table>
<!--用户信息-->
<!------------------------------------ 联通end --------------------------------------->
<!------------------------------------ 个人失信信息start --------------------------------------->
<!--用户信息-->
<h1>失信</h1>
<table id="sxtravel">
	<caption><h3>失信信息</h3></caption>
    <thead>    
        <tr>
            <th scope="col">被执行人姓名</th>
            <th scope="col">性别</th>
            <th scope="col">年龄</th>
            <th scope="col">身份证号码/组织机构代码</th>
            <th scope="col">执行法院</th>
            <th scope="col">省份</th>
            <th scope="col">执行依据文号</th>
            <th scope="col">立案时间</th>
            <th scope="col">案号</th>
            <th scope="col">做出执行依据单位</th>
            <th scope="col">生效法律文书确定的义务</th>
            <th scope="col">失信被执行人行为具体情形</th>
            <th scope="col">发布时间</th>
        </tr>        
    </thead>   
    <tbody>
    <c:forEach items="${perdishonestyFormlist}" var="perdishonestyFormlist" >
		<tr>
			<td>${perdishonestyFormlist.sxname}</td>
			<td>${perdishonestyFormlist.sxgender}</td>
			<td>${perdishonestyFormlist.sxage}</td>
			<td>${perdishonestyFormlist.sxcertno}</td>
			<td>${perdishonestyFormlist.sxenforceorgname}</td>
			<td>${perdishonestyFormlist.sxprovincename}</td>
			<td>${perdishonestyFormlist.sxenforcefileno}</td>
			<td>${perdishonestyFormlist.sxregisterdate}</td>
			<td>${perdishonestyFormlist.sxcaseno}</td>
			<td>${perdishonestyFormlist.sxcourtname}</td>
			<td>${perdishonestyFormlist.sxcasereason}</td>
			<td>${perdishonestyFormlist.sxalreadyenforcecase}</td>
			<td>${perdishonestyFormlist.sxpublishdate}</td>
		</tr>
    </c:forEach>
    </tbody>
</table>
<!--用户信息-->
<!------------------------------------ 个人失信信息end --------------------------------------->
<!--页面主体内容部分-->
<div class="my_content">
</div>
<div>
	<span>  
		<a href="importPDF"><button>导出pdf</button></a>
	</span>
</div>
<!--	页面底部部分-->
<ul class="footer" style="background: #fff; margin-top: 10px " >
    <li class="footer-item">
        <a href="../index.jsp" class="">
            <i class="icon icon_index"></i>
            <p class="icon_title">首页</p>
        </a>
    </li>
    <li class="footer-item">
        <a href="../invest.jsp" class="">
            <i class="icon icon_invest"></i>
            <p class="icon_title">贷款</p>
        </a>
    </li>
    <li class="footer-item">
        <a href="../activity.jsp" class="">
            <i class="icon icon_activity"></i>
            <p class="icon_title">商城</p>
        </a>
    </li>
    <li class="footer-item">
        <a href="../personal.jsp" class="">
            <i class="icon icon_my"></i>
            <p class="icon_title">我的</p>
        </a>
    </li>
</ul>
</body>

<!-- <script src="../js/personal.js?v=1.2.1" type="text/javascript" charset="utf-8"></script> -->
</html>
