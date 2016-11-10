<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	    
	    <!--#assign accessTokenId = gigUserAuth.accessTokenUuid/-->
	
	    <link rel="stylesheet" href="/css/browser.css"/>
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	    <title>MAIN MENU GigFilter Admin Berlin</title>
	
	</head>
	<body class="div_body_text">
		<div align="center">
		    <img src="/img/analyze-analyze-icon.png"/>
			<h3>Analysis Results for: ${analyzerManager.url}</h3>
		<table>
		    <tr>
		        <td>
		 		HTML version: ${analyzerManager.htmlVersion}
		        </td>
		    </tr>
		    <tr>
		        <td>
		        Page Title: ${analyzerManager.pageTitle}
		        </td>
		    </tr>
		    <tr>
		        <td>
				Number of Headings per Level: ${analyzerManager.headerCount}
		        </td>
		    </tr>
		    <tr>
		        <td>
		    	Number of Internal Links: ${analyzerManager.numberOfInternalLinks}
		        </td>
		    </tr>
		    <tr>
		        <td>
		        Number of External Links: ${analyzerManager.numberOfExternalLinks}
		        </td>
		    </tr>
		    <tr>
		        <td>
		        Number of Inaccessible Links (if any): ${analyzerManager.numberOfInaccessibleLinks}
		        </td>
		    </tr>
		
		    <tr>
		        <td>
		        Does the Page Contain a Login-form?: ${analyzerManager.containLoginForm?c}
		        </td>
		    </tr>
		    <tr>
		        <td>
		        <!--spacer-->
		        </td>
		    </tr>
		    <tr>
		        <td>
		        <a href="/service/entry">Please Return and try again!</a>
		        </td>
		    </tr>
		</table>
		
		
		
		</div>
	
	</body>
</html>