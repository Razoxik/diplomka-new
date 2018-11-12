<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<meta charset="utf-8"/>
<link rel="apple-touch-icon" sizes="76x76" href="${pageContext.request.contextPath}img/apple-icon.png">
<link rel="icon" type="image/png" href="${pageContext.request.contextPath}img/favicon.png">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>
    Diplomová práce
</title>
<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no'
      name='viewport'/>
<!-- Fonts and icons -->
<link rel="stylesheet" type="text/css"
      href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons"/>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
<!-- CSS Files -->
<spring:url value="/css/material-dashboard.css" var="materialDashboardCss"/>
<spring:url value="/css/demo.css" var="demoCss"/>
<link rel="stylesheet" type="text/css" href="${materialDashboardCss}"/>
<!-- CSS Just for demo purpose, don't include it in your project -->
<link rel="stylesheet" type="text/css" href="${demoCss}"/>
