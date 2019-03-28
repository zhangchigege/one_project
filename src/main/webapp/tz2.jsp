<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>跳转页面</title>
<link rel="stylesheet" href="../css/jquery-ui.css" />
<link rel="stylesheet" type="text/css" media="screen" href="../css/css-table.css" />
<script type="text/javascript" src="../js/style-table.js"></script>
<script src="../js/jquery-1.8.3.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="../js/jquery-ui.js"></script> 
<script type="text/javascript">
	window.addEventListener('load', function(){ 
		$("#tz2form").submit();
	}); 
</script>
</head>
<body >
	<form action="/zxshow/invest.jsp?tab=3" id="tz2form" method="post">  
		<input type="hidden" id="tbuserid" name="tbuserid" value="1"/>
		<input type="hidden" id="jduserid" name="jduserid" value="2"/>
	</form>
</body>
<script type="text/javascript">
	$(function(){
		var tbuserid = '${tbuserid}';
		var jduserid = '${jduserid}';
		$("#tbuserid").val(tbuserid);
		$("#jduserid").val(jduserid); 
	});
</script>
</html>
