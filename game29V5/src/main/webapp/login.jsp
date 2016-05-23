<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nikhil Mathur - Welcome to Game 29</title>
<link rel="stylesheet" href="webix/codebase/webix.css" type="text/css"
	media="screen">
<script src="webix/codebase/webix.js" type="text/javascript"
	charset="utf-8"></script>
<script type="text/javascript">
	function doGet(path, payLoad) {
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
</script>
</head>
<body>
	<script type="text/javascript">
		var form1 = [
				{
					view : "segmented",
					id : "playerIdPicker",
					options : [ {
						id : "1",
						value : "One"
					}, {
						id : "2",
						value : "Two"
					}, {
						id : "3",
						value : "Three"
					}, {
						id : "4",
						value : "Four"
					} ]
				},
				{
					view : "text",
					id : "playerNamePicker",
					placeholder : "player name",
					label : "Name"
				},
				{
					view : "text",
					id : "playerPasswordPicker",
					type : 'password',
					placeholder : '123pass',
					label : "Password"
				},
				{
					id : "claimUserCheckbox",
					view : "checkbox",
					label : "Claim existing user",
					labelWidth : 200
				},
				{
					cols : [ {
						view : "button",
						value : "Login",
						type : "form",
						click : function() {
							if ($$("playerNamePicker").getValue() == "") {
								webix
										.alert("Please enter Player Name to continue...");
								return;
							}
							if ($$("playerPasswordPicker").getValue() == "") {
								webix
										.alert("Please enter password to continue...");
								return;
							}
				
							var payLoad = {
								"playerId" : $$("playerIdPicker").getValue(),
								"playerName" : $$("playerNamePicker")
										.getValue(),
								"playerPassword" : $$("playerPasswordPicker")
										.getValue(),
								"claimUser" : $$("claimUserCheckbox")
										.getValue()
							};
							String
							response = doGet("rest/game/users", payLoad);
							if (response == "Registered") {
								window.location = "play.jsp?playerId="
										+ payLoad.playerId;
							}

						}
					} ]
				} ];
		webix.ui({
			cols : [ {
				rows : [ {
					view : "form",
					scroll : false,
					elements : form1
				} ]
			} ]

		});
	</script>
</body>
</html>