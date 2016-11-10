<#-- @ftlvariable name="errorView" type="com.gigfilter.rest.views.ErrorView" -->
<#import "Layout.ftl" as layout>
<@layout.layout>
<h3> 400 Bad Request</h3>
<h3> Something is askew...</h3>

<h3>
Sorry, Please hit the back button and try again.

<br/>
<br/>
<#if exceptionMessage??> ${exceptionMessage}  <#else></#if>


</h3>
</@layout.layout>