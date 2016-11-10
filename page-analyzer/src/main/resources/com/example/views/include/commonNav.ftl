<#ftl encoding='UTF-8'>
<#setting number_format="0.########">
<#macro layout>
<!DOCTYPE html>
<html lang="en">
<head>
    <#assign userEmail = gigUserAuth.gigUser.userEmail/>
    <#assign accessTokenId = gigUserAuth.accessTokenUuid/>


    <link rel="stylesheet" href="/css/browser.css" />
    <meta charset="utf-8">
    <title>${title}</title>


</head>

<body>
<div><a href="/service/entry>Go Back And Try Another Page...</a></div>
<#nested/>


</body>
</html>
</#macro>