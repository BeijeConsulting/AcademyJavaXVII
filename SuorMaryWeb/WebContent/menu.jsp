<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Rubrica</title>
</head>
<body>

	<h1>Benvenuto nella rubrica</h1>
	<form action="menu" method="post">
		<select name="menu">
			<option disabled selected>Choices</option>
			<%
			String[] menuItems = { "view all contacts", "insert a contact" };
			%>
			<%
			for (String item : menuItems) {
			%>
			<option value="<%=item.toLowerCase()%>"><%=item%></option>
			<%
			}
			%>
		</select>
		<input type="submit" value="Go">
	</form>

</body>
</html>
