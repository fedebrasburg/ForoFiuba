<html>

<head>
<meta name="layout" content="${gspLayout ?: 'main'}"/>
<title><g:message code='springSecurity.denied.title' /></title>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<style>
	table, th, td {
		border: 1px solid black;
		border-collapse: collapse;
	}
	</style>
</head>
<body>
<g:render template="partials/Nav"/>
<div class="body">
	<div class="errors">No entres ahi</div>
</div>
</body>

</html>
