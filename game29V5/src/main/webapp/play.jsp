<%@page import="org.apache.commons.lang3.StringUtils"%>

<html>
<%
	String playerId = (String) request.getParameter("playerId");
	String playerName = (String) request.getParameter("playerName");
	String playerPassword = (String) request.getParameter("playerPassword");
	if (StringUtils.isBlank(playerId)) {
		response.sendRedirect("login.jsp");
	}
%>

<head>
<style type="text/css">
.tg {
	border-collapse: collapse;
	border-spacing: 0;
}

.tg td {
	font-family: Arial, sans-serif;
	font-size: 14px;
	padding: 10px 5px;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	word-break: normal;
}

.tg th {
	font-family: Arial, sans-serif;
	font-size: 14px;
	font-weight: normal;
	padding: 10px 5px;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	word-break: normal;
}

.tg .tg-b44r {
	background-color: #F1B9AC;
	vertical-align: top
}

.tg .tg-72gw {
	background-color: #AFF9EB;
	vertical-align: top
}

.tg .tg-vv23 {
	background-color: #A4AFAD;
	vertical-align: top
}

.tg .tg-3smg {
	background-color: #DFCAEC;
	vertical-align: top
}

.tg .tg-mtwr {
	background-color: #F3CCD0;
	vertical-align: top
}

.tg .tg-6c3r {
	background-color: #CCF3CF;
	vertical-align: top
}

.tg .tg-3b15 {
	background-color: #E5F3CC;
	vertical-align: top
}

.tg .tg-6c2r {
	background-color: #FBDAF5;
	vertical-align: top
}

.tg .tg-3c15 {
	background-color: #D8D8DE;
	vertical-align: top
}

.buttonBlack {
	text-decoration: none;
	text-align: center;
	padding: 8px 8px;
	border: solid 1px #004F72;
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	border-radius: 4px;
	font: 18px Arial, Helvetica, sans-serif;
	font-weight: bold;
	color: #E5FFFF;
	background-color: #454457;
	background-image: -moz-linear-gradient(top, #454457 0%, #616966 100%);
	background-image: -webkit-linear-gradient(top, #454457 0%, #616966 100%);
	background-image: -o-linear-gradient(top, #454457 0%, #616966 100%);
	background-image: -ms-linear-gradient(top, #454457 0%, #616966 100%);
	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#616966',
		endColorstr='#616966', GradientType=0);
	background-image: linear-gradient(top, #454457 0%, #616966 100%);
	-webkit-box-shadow: 0px 0px 2px #bababa, inset 0px 0px 1px #ffffff;
	-moz-box-shadow: 0px 0px 2px #bababa, inset 0px 0px 1px #ffffff;
	box-shadow: 0px 0px 2px #bababa, inset 0px 0px 1px #ffffff;
}

.buttonRed {
	text-decoration: none;
	text-align: center;
	padding: 8px 8px;
	border: solid 1px #004F72;
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	border-radius: 4px;
	font: 18px Arial, Helvetica, sans-serif;
	font-weight: bold;
	color: #E5FFFF;
	background-color: #f7f4cf;
	background-image: -moz-linear-gradient(top, #f7f4cf 0%, #b50e30 100%);
	background-image: -webkit-linear-gradient(top, #f7f4cf 0%, #b50e30 100%);
	background-image: -o-linear-gradient(top, #f7f4cf 0%, #b50e30 100%);
	background-image: -ms-linear-gradient(top, #f7f4cf 0%, #b50e30 100%);
	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#b50e30',
		endColorstr='#b50e30', GradientType=0);
	background-image: linear-gradient(top, #f7f4cf 0%, #b50e30 100%);
	-webkit-box-shadow: 0px 0px 2px #bababa, inset 0px 0px 1px #ffffff;
	-moz-box-shadow: 0px 0px 2px #bababa, inset 0px 0px 1px #ffffff;
	box-shadow: 0px 0px 2px #bababa, inset 0px 0px 1px #ffffff;
}

.buttonBlue {
	text-decoration: none;
	text-align: center;
	padding: 8px 8px;
	border: solid 1px #004F72;
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	border-radius: 4px;
	font: 18px Arial, Helvetica, sans-serif;
	font-weight: bold;
	color: #E5FFFF;
	background-color: #3BA4C7;
	background-image: -moz-linear-gradient(top, #3BA4C7 0%, #1982A5 100%);
	background-image: -webkit-linear-gradient(top, #3BA4C7 0%, #1982A5 100%);
	background-image: -o-linear-gradient(top, #3BA4C7 0%, #1982A5 100%);
	background-image: -ms-linear-gradient(top, #3BA4C7 0%, #1982A5 100%);
	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#1982A5',
		endColorstr='#1982A5', GradientType=0);
	background-image: linear-gradient(top, #3BA4C7 0%, #1982A5 100%);
	-webkit-box-shadow: 0px 0px 2px #bababa, inset 0px 0px 1px #ffffff;
	-moz-box-shadow: 0px 0px 2px #bababa, inset 0px 0px 1px #ffffff;
	box-shadow: 0px 0px 2px #bababa, inset 0px 0px 1px #ffffff;
}

.buttonGreen {
	text-decoration: none;
	text-align: center;
	padding: 8px 8px;
	border: solid 1px #004F72;
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	border-radius: 4px;
	font: 18px Arial, Helvetica, sans-serif;
	font-weight: bold;
	color: #E5FFFF;
	background-color: #a8e6cd;
	background-image: -moz-linear-gradient(top, #a8e6cd 0%, #0bd91c 100%);
	background-image: -webkit-linear-gradient(top, #a8e6cd 0%, #0bd91c 100%);
	background-image: -o-linear-gradient(top, #a8e6cd 0%, #0bd91c 100%);
	background-image: -ms-linear-gradient(top, #a8e6cd 0%, #0bd91c 100%);
	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#0bd91c',
		endColorstr='#0bd91c', GradientType=0);
	background-image: linear-gradient(top, #a8e6cd 0%, #0bd91c 100%);
	-webkit-box-shadow: 0px 0px 2px #bababa, inset 0px 0px 1px #ffffff;
	-moz-box-shadow: 0px 0px 2px #bababa, inset 0px 0px 1px #ffffff;
	box-shadow: 0px 0px 2px #bababa, inset 0px 0px 1px #ffffff;
}

.button {
	text-decoration: none;
	text-align: center;
	padding: 11px 32px;
	border: solid 1px #004F72;
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	border-radius: 4px;
	font: 18px Arial, Helvetica, sans-serif;
	font-weight: bold;
	color: #E5FFFF;
	background-color: #3BA4C7;
	background-image: -moz-linear-gradient(top, #3BA4C7 0%, #1982A5 100%);
	background-image: -webkit-linear-gradient(top, #3BA4C7 0%, #1982A5 100%);
	background-image: -o-linear-gradient(top, #3BA4C7 0%, #1982A5 100%);
	background-image: -ms-linear-gradient(top, #3BA4C7 0%, #1982A5 100%);
	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#1982A5',
		endColorstr='#1982A5', GradientType=0);
	background-image: linear-gradient(top, #3BA4C7 0%, #1982A5 100%);
	-webkit-box-shadow: 0px 0px 2px #bababa, inset 0px 0px 1px #ffffff;
	-moz-box-shadow: 0px 0px 2px #bababa, inset 0px 0px 1px #ffffff;
	box-shadow: 0px 0px 2px #bababa, inset 0px 0px 1px #ffffff;
}
</style>

<link rel="stylesheet" href="webix/codebase/webix.css" type="text/css"
	media="screen">
<script src="webix/codebase/webix.js" type="text/javascript"
	charset="utf-8"></script>
<title>My Page</title>
<script type="text/javascript">

	function makeMove(suite, rank){
		var queryParams ={
				"suite": suite,
				"rank":rank
			};
			
			result = doGet("rest/game/makeMove", queryParams);
			webix.message(result);
	}

	function openTrump(){
		var queryParams ={};
		result = doGet("rest/game/openTrump", queryParams);
		webix.message(result);
	}
	function setTrump(trump){
		var queryParams ={
			"trump": trump
		};
		result = doGet("rest/game/setTrump", queryParams);
		webix.message(result);
	}
	function throwReDoubleChallenge(isPass, isReDouble){
		var queryParams = {
				"isPass" : isPass,
				"isReDouble" : isReDouble
			} ;
		result = doGet("rest/game/reDoublechallenge", queryParams);
		webix.message(result);
	}

	function throwChallenge(points, isPass, isDouble) {
		//alert("Setting challenge to " + points + " points");
		var queryParams = {
			"points" : points,
			"isPass" : isPass,
			"isDouble" : isDouble
		} ;
		result = doGet("rest/game/challenge", queryParams);
		webix.message(result);
	}

	function doGet(path, queryParams) {
		
		var t = "playerId";
		queryParams[t] ="<%=playerId%>";

		var count = 0;
		for ( var key in queryParams) {
			if (count == 0) {
				path = path + "?";
			} else {
				path = path + "&";
			}
			count++;
			path = path + key + "=" + queryParams[key];
		}
		var xhr = webix.ajax().sync().header({
			'Content-Type' : 'application/json'
		}).get(path);
		if (xhr.status == 200) {
			//alert(xhr.responseText);
			return xhr.responseText;
		}
	}

	function doPut(path, payLoad) {
		var count = 0;
		var xhr = webix.ajax().sync().header({
			'Content-Type' : 'text/plain'
		}).put(path, JSON.stringify(payLoad));
		if (xhr.status == 200) {
			//alert(xhr.responseText);
			return xhr.responseText;
		} else {
			webix
					.alert("You are not allowed with the credentials provided. Either chose a different player or enter correct credentials...")
		}
	}
	function getData(section) {
		return doGet("rest/game/sections/" + section, {

		});
	};

	function timeout() {
		var payLoad = {
			"playerId" : "<%=playerId%>",
			"playerName" :"<%=playerName%>",
			"playerPassword" : "<%=playerPassword%>",
			"claimUser" : 0
		};
		//alert(JSON.stringify(payLoad));
		String
		response = doPut("rest/game/users", payLoad);
		if (response !== "Registered") {
			window.location = "login.jsp";
		}
		;

		setInterval(function() {
			$$("get11TemplateId").setHTML(doGet("rest/game/sections/11", {}));
		}, 3000);
		setInterval(function() {
			$$("get12TemplateId").setHTML(doGet("rest/game/sections/12", {}));
		}, 3000);
		setInterval(function() {
			$$("get13TemplateId").setHTML(doGet("rest/game/sections/13", {}));
		}, 2000);
		setInterval(function() {
			$$("get21TemplateId").setHTML(doGet("rest/game/sections/21", {}));
		}, 3000);
		setInterval(function() {
			$$("get22TemplateId").setHTML(doGet("rest/game/sections/22", {}));
		}, 1300);
		setInterval(function() {
			$$("get23TemplateId").setHTML(doGet("rest/game/sections/23", {}));
		}, 3000);

		setInterval(function() {
			$$("get31TemplateId").setHTML(doGet("rest/game/sections/31", {}));
		}, 1700);
		setInterval(function() {
			$$("get32TemplateId").setHTML(doGet("rest/game/sections/32", {}));
		}, 1900);
		//setInterval(function() {
		//$$("get33TemplateId").setHTML(doGet("rest/game/sections/33", {}));
		//}, 11000);

		//setInterval(getTemplateF, time);
	};
</script>
</head>

<body onLoad="timeout();">

	<script type="text/javascript">
		webix.ui({
			rows : [ {
				view : "accordion",
				header : "headertop",
				multi : "mixed",
				cols : [ {
					header : "Game Score",
					body : {
						id : "get11TemplateId",
						template : getData("11")
					}

				}, {
					header : "Team Player",
					body : {
						id : "get12TemplateId",
						template : getData("12")
					}

				}, {
					header : "Match Score",
					body : {
						id : "get13TemplateId",
						template : getData("13")
					}

				} ]
			}, {
				view : "resizer"
			}, {
				view : "accordion",
				header : "headermiddle",
				multi : "mixed",
				cols : [ {
					header : "Opposition 1",
					body : {
						id : "get21TemplateId",
						template : getData("21")
					}
				}, {
					header : "Play",
					body : {
						id : "get22TemplateId",
						template : getData("22")
					}
				}, {
					header : "Opposition 2",
					body : {
						id : "get23TemplateId",
						template : getData("23")
					}

				} ]
			}, {
				view : "resizer"
			}, {
				view : "accordion",
				header : "headerbottom",
				multi : "mixed",
				cols : [ {
					header : "Challenge",
					body : {
						id : "get31TemplateId",
						template : getData("31")
					}

				}, {
					header : "My Cards",
					body : {
						id : "get32TemplateId",
						template : getData("32")
					}

				}, {
					header : "Game/Information",
					body : {
						id : "get33TemplateId",
						view : "layout",
						rows : [ {
							view : "button",
							label : "New Deal",
							click : function() {
								webix.message(doGet("rest/game/deal", {}));
							}
						}, {
							id : "get44TemplateId",
							template : "Goof View"
						} ]
					}

				} ]
			}

			]
		}).show();
	</script>


</body>

</html>