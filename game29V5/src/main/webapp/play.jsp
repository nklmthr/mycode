
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="org.personal.controller.ActionConstants"%>
<html>
<%
	String playerId = (String) request.getParameter(ActionConstants.PARAM_PLAYER_ID_STR);
	System.out.println("playerId" + playerId);
	if (StringUtils.isBlank(playerId)) {
		response.sendRedirect("login.jsp");
	}
%>

<head>
<style type="text/css">
.buttonDB {
	text-decoration: none;
	text-align: center;
	padding: 5px 5px;
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

.buttonNA {
	text-decoration: none;
	text-align: center;
	padding: 5px 5px;
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

.buttonA {
	text-decoration: none;
	text-align: center;
	padding: 5px 5px;
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

.buttonNO {
	text-decoration: none;
	text-align: center;
	padding: 5px 5px;
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
			alert(result);
	}

	function openTrump(){
		var queryParams ={};
		result = doGet("rest/game/openTrump", queryParams);
		alert(result);
	}
	function setTrump(trump){
		var queryParams ={
			"trump": trump
		};
		result = doGet("rest/game/setTrump", queryParams);
		alert(result);
	}
	function throwReDoubleChallenge(isPass, isReDouble){
		var queryParams = {
				"isPass" : isPass,
				"isReDouble" : isReDouble
			} ;
		result = doGet("rest/game/reDoublechallenge", queryParams);
		alert(result);
	}

	function throwChallenge(points, isPass, isDouble) {
		//alert("Setting challenge to " + points + " points");
		var queryParams = {
			"points" : points,
			"isPass" : isPass,
			"isDouble" : isDouble
		} ;
		result = doGet("rest/game/challenge", queryParams);
		alert(result);
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

	function getData(section) {
		return doGet("rest/game/sections/" + section, {

		});
	};

	function getTemplateF() {
		//console.log("Start get11Template()");
		$$("get11TemplateId").setHTML(doGet("rest/game/sections/11", {}));
		$$("get12TemplateId").setHTML(doGet("rest/game/sections/12", {}));
		$$("get13TemplateId").setHTML(doGet("rest/game/sections/13", {}));
		$$("get21TemplateId").setHTML(doGet("rest/game/sections/21", {}));
		$$("get22TemplateId").setHTML(doGet("rest/game/sections/22", {}));
		$$("get23TemplateId").setHTML(doGet("rest/game/sections/23", {}));
		$$("get31TemplateId").setHTML(doGet("rest/game/sections/31", {}));
		$$("get32TemplateId").setHTML(doGet("rest/game/sections/32", {}));

		//console.log("end get11Template()");
	};

	function timeout(time) {
		//console.log("Start timeout()");
		setInterval(getTemplateF, time);
	};
</script>
</head>

<body onLoad="timeout(1500);">

	<script type="text/javascript">
		webix.ui({
			rows : [ {
				view : "toolbar",
				elements : [ {
					view : "label",
					label : "nklmthr World"
				} ]
			}, {
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
								alert(doGet("rest/game/deal", {

								}));
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