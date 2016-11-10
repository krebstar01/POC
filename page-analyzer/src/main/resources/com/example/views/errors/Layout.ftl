<#macro layout>
        <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="stylesheet" href="/css/browser.css" />
    <title>Dropwizard Error Page</title>




    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <![endif]-->
</head>

<body class="error">

<div class="error-page-container">
    <div class="error-page-header">
        <h1>Dropwizard Error Page</h1>
        
    </div>

    <#nested/>
    <h3><a href="/service/entry">Please Return and try again!</a></h3>
</div>
<!-- /.container -->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
</body>
</html>
</#macro>