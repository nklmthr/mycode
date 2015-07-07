<%@page import="org.personal.controller.ActionConstants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String PARAM_PLAYER_ID_STR = request
			.getParameter(ActionConstants.PARAM_PLAYER_ID_STR);
    String PARAM_PLAYER_PASS_STR=request.getParameter(ActionConstants.PARAM_PLAYER_PASS_STR);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nikhil Mathur - Game 29</title>
<script type="text/javascript">
var t;
var timer_is_on=0;

function timedCount()
{
	t=setTimeout("timedCount()",1500);
	var url="refresh?<%=ActionConstants.ACTION_COMMAND%>=<%=ActionConstants.REFRESH_ACTION%>"
		+"&<%=ActionConstants.PARAM_PLAYER_ID_STR%>=<%=PARAM_PLAYER_ID_STR%>"
		+"&<%=ActionConstants.PARAM_PLAYER_NAME_STR%>=<%=ActionConstants.PARAM_PLAYER_NAME_STR%>"
		+"&<%=ActionConstants.PARAM_PLAYER_PASS_STR%>=<%=PARAM_PLAYER_PASS_STR%>";
	makeCall("content",url);
}

function makeCall(div,url){
	var req = initRequest();
	req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                document.getElementById(div).innerHTML = parseMessages(req.responseXML);
            } 
        }
     };
    req.open("GET", url, true);	    
    req.send(null);
}

function initRequest() {
    if (window.XMLHttpRequest) {
        return new XMLHttpRequest();
    }else if (window.ActiveXObject) {
        isIE = true;
        return new ActiveXObject("Microsoft.XMLHTTP");
    }
    
}

function onLoad(){
	var url = "refresh?<%=ActionConstants.ACTION_COMMAND%>=<%=ActionConstants.REFRESH_ACTION%>"
		+"&<%=ActionConstants.PARAM_PLAYER_ID_STR%>=<%=PARAM_PLAYER_ID_STR%>"
		+"&<%=ActionConstants.PARAM_PLAYER_NAME_STR%>=<%=ActionConstants.PARAM_PLAYER_NAME_STR%>"
		+"&<%=ActionConstants.PARAM_PLAYER_PASS_STR%>=<%=PARAM_PLAYER_PASS_STR%>";
	makeCall("content",url);
	if (!timer_is_on){
		timer_is_on=1;
		timedCount();
	}
}

function doFirstDeal(){
	
	var url="playerAction?<%=ActionConstants.ACTION_COMMAND%>=<%=ActionConstants.DO_FIRST_DEAL_ACTION%>"
		+"&<%=ActionConstants.PARAM_PLAYER_ID_STR%>=<%=PARAM_PLAYER_ID_STR%>"
		+"&<%=ActionConstants.PARAM_PLAYER_NAME_STR%>=<%=ActionConstants.PARAM_PLAYER_NAME_STR%>"
		+"&<%=ActionConstants.PARAM_PLAYER_PASS_STR%>=<%=PARAM_PLAYER_PASS_STR%>";
	makeCall("messages",url);
	}

function doSetNewChallengePoints(points){
	
	var url="playerAction?"
	url=url+"<%=ActionConstants.ACTION_COMMAND%>=<%=ActionConstants.SET_NEW_CHALL_POINT_ACTION%>"
		+"&<%=ActionConstants.PARAM_PLAYER_ID_STR%>=<%=PARAM_PLAYER_ID_STR%>"
		+"&<%=ActionConstants.PARAM_PLAYER_NAME_STR%>=<%=ActionConstants.PARAM_PLAYER_NAME_STR%>"
	 	+"&<%=ActionConstants.PARAM_PLAYER_PASS_STR%>=<%=PARAM_PLAYER_PASS_STR%>"
		+"&<%=ActionConstants.PARAM_CHALLENGE_POINT%>="+points;
		makeCall("messages",url);
}

function setChallenge(isDouble){
	
	var url="playerAction?"
			+"<%=ActionConstants.ACTION_COMMAND%>=<%=ActionConstants.SET_CHALL_ACTION%>"
			+"&<%=ActionConstants.PARAM_PLAYER_ID_STR%>=<%=PARAM_PLAYER_ID_STR%>"
			+"&<%=ActionConstants.PARAM_PLAYER_NAME_STR%>=<%=ActionConstants.PARAM_PLAYER_NAME_STR%>"
			+"&<%=ActionConstants.PARAM_PLAYER_PASS_STR%>=<%=PARAM_PLAYER_PASS_STR%>"
			+"&<%=ActionConstants.PARAM_CHALL_IS_DOUBLE%>="+isDouble;
			makeCall("messages",url);
}

function setReDouble(isRedouble){
	var url="playerAction?"
			+"<%=ActionConstants.ACTION_COMMAND%>=<%=ActionConstants.SET_REDOUBLE_ACTION%>"
			+"&<%=ActionConstants.PARAM_PLAYER_ID_STR%>=<%=PARAM_PLAYER_ID_STR%>"
			+"&<%=ActionConstants.PARAM_PLAYER_PASS_STR%>=<%=PARAM_PLAYER_PASS_STR%>"
			+"&<%=ActionConstants.PARAM_CHALL_IS_REDOUBLE%>="+isRedouble;
	makeCall("messages",url);
}


function doSetTrump(trumpId){

	var url="playerAction?"
			+"<%=ActionConstants.ACTION_COMMAND%>=<%=ActionConstants.SET_TRUMP_ACTION%>"
			+"&<%=ActionConstants.PARAM_PLAYER_ID_STR%>=<%=PARAM_PLAYER_ID_STR%>"
			+"&<%=ActionConstants.PARAM_PLAYER_NAME_STR%>=<%=ActionConstants.PARAM_PLAYER_NAME_STR%>"
			+"&<%=ActionConstants.PARAM_PLAYER_PASS_STR%>=<%=PARAM_PLAYER_PASS_STR%>"
			+"&<%=ActionConstants.PARAM_TRUMP_ID_STR%>="+trumpId;
	makeCall("messages",url);
}

function makeMove(suitNum,rankNum){
	//alert("Make move...");
	var url="playerAction?"
	+"<%=ActionConstants.ACTION_COMMAND%>=<%=ActionConstants.MAKE_MOVE_ACTION%>"
	+"&<%=ActionConstants.PARAM_PLAYER_ID_STR%>=<%=PARAM_PLAYER_ID_STR%>"
	+"&<%=ActionConstants.PARAM_PLAYER_NAME_STR%>=<%=ActionConstants.PARAM_PLAYER_NAME_STR%>"
	+"&<%=ActionConstants.PARAM_PLAYER_PASS_STR%>=<%=PARAM_PLAYER_PASS_STR%>"
	+"&<%=ActionConstants.PARAM_CARD_SUIT_NUM%>="+suitNum
	+"&<%=ActionConstants.PARAM_CARD_RANK_NUM%>="+rankNum;
	makeCall("messages",url);
}

function makeTrumpShown(){
	var url="playerAction?"
		+"<%=ActionConstants.ACTION_COMMAND%>=<%=ActionConstants.MAKE_TRUMP_SHOWN_ACTION%>"
		+"&<%=ActionConstants.PARAM_PLAYER_ID_STR%>=<%=PARAM_PLAYER_ID_STR%>"
		+"&<%=ActionConstants.PARAM_PLAYER_NAME_STR%>=<%=ActionConstants.PARAM_PLAYER_NAME_STR%>"
		+"&<%=ActionConstants.PARAM_PLAYER_PASS_STR%>=<%=PARAM_PLAYER_PASS_STR%>";
	makeCall("messages",url);
}

function newGame(){
	var url="playerAction?"
		+"<%=ActionConstants.ACTION_COMMAND%>=<%=ActionConstants.START_NEW_GAME_ACTION%>"
		+"&<%=ActionConstants.PARAM_PLAYER_ID_STR%>=<%=PARAM_PLAYER_ID_STR%>"
		+"&<%=ActionConstants.PARAM_PLAYER_NAME_STR%>=<%=ActionConstants.PARAM_PLAYER_NAME_STR%>"
		+"&<%=ActionConstants.PARAM_PLAYER_PASS_STR%>=<%=PARAM_PLAYER_PASS_STR%>";
	makeCall("messages",url);
	
}

function parseMessages(responseXML) {
	var msgs = responseXML.getElementsByTagName("xml")[0];
	var firstName;
	for ( var loop = 0; loop < msgs.childNodes.length; loop++) {
		var msg = msgs.childNodes[loop];
		firstName = msgs.getElementsByTagName("xmlMsg")[0];
	}
	return firstName.childNodes[0].nodeValue;
}

function doOuch(){
	alert("Ouucchhh!!! That hurts! Please don't press me again...");
	return false;
}

</script>
</head>
<body onload="javascript:onLoad();">
<div id="messages">&nbsp;</div>
<br>
<div id="content">&nbsp;
</div>
</body>
</html>