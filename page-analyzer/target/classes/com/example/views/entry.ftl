<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" href="/css/browser.css" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<title>Web Page Analyzer</title>
	</head>
	
	<body class="div_body_text">
		<div align="center">
		<img src="/img/analyze-analyze-icon.png" width="50%"/>
			<fieldset>
			<div class="login_header">Web Page Analyzer</div>
				<form method="post" action="/service/results" class="div_body_text">
					<p>
						<h3>Enter a valid URL below and hit the below submit button in order to know the following about your link:</h3>
						<table>
							<tr><td>HTML Version</td></tr>
							<tr><td>Page Title</td></tr>
							<tr><td>Number of Headings per Level</td></tr>
							<tr><td>Number of Internal Links</td></tr>
							<tr><td>Number of External Links</td></tr>
							<tr><td>Number of Inaccessible Links (if any)</td></tr>
							<tr><td>Does the Page Contain a Login-Form?</td></tr>
						</table>
					</p>
				<input type="text" id="url" name="url"/><br/>
				<button type="submit">Analyze Your Page!</button>
				</form>
			</div>
			</fieldset>
	</body>
	
</html>