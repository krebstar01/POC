<#-- @ftlvariable name="errorView" type="com.gigfilter.rest.views.ErrorView" -->
<#import "Layout.ftl" as layout>
<@layout.layout>
<h3>500 SERVER ERROR</h3>
<h4>AHHH crap. look at this big ugly stacktrace:</h4>
<p>${stackTrace}</p>
</@layout.layout>