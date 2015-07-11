<%@page import="com.nklmthr.mycode.game29.controller.ActionConstants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String messages=request.getParameter("message");
if(messages==null) {
	messages="";
}
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nikhil Mathur - Welcome to Game 29</title>
</head>
<body>
<div id="message"><%=messages %></div>
<form name="myform" method ="get" action="loginAction">
	<input type="hidden" name="<%=ActionConstants.ACTION_COMMAND%>" value ="<%=ActionConstants.LOGIN_ACTION%>"/>
	Select Player 
	<select name="<%=ActionConstants.PARAM_PLAYER_ID_STR%>">
		<option value="1">One</option>
		<option value="2">Two</option>
		<option value="3">Three</option>
		<option value="4">Four</option>
	</select><br><br>
	Your Name<br>
	<input type="text" name="<%=ActionConstants.PARAM_PLAYER_NAME_STR%>" />
	<br><br>
	Password<br>
	<input type="text" name="<%=ActionConstants.PARAM_PLAYER_PASS_STR%>" />
	<br><br>
	<input type="submit" value="Submit" />
</form>
</body>
</html>