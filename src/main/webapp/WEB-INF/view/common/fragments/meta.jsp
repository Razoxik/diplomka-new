<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<meta charset="utf-8"/>
<spring:url value="/img/titleIcon.png" var="titleIconUrl"/>
<link rel="icon" type="image/png" href="${titleIconUrl}">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>
    Let's play folks
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
<link rel="stylesheet" type="text/css" href="${demoCss}"/>
<!-- JS Files -->
<spring:url value="/js/jquery.min.js" var="jqueryMinJs"/>
<spring:url value="/js/popper.min.js" var="popperMinJs"/>
<spring:url value="/js/bootstrap-material-design.min.js" var="bootstrapMatMinJs"/>
<spring:url value="/js/perfect-scrollbar.jquery.min.js" var="scrollbarMinJs"/>
<spring:url value="/js/chartist.min.js" var="chartistMinJs"/>
<spring:url value="/js/bootstrap-notify.js" var="notifyJs"/>
<spring:url value="/js/material-dashboard.js?v=2.1.0" var="matDashboardJs"/>
<script src="${jqueryMinJs}"></script>
<script src="${popperMinJs}"></script>
<script src="${bootstrapMatMinJs}"></script>
<script src="${scrollbarMinJs}"></script>
<script async defer src="https://buttons.github.io/buttons.js"></script>
<script src="${chartistMinJs}"></script>
<script src="${notifyJs}"></script>
<script src="${matDashboardJs}"></script>
<script src="https://unpkg.com/default-passive-events"></script>
<!-- Google Maps Plugin -->
<spring:url value="/map/demo.js" var="mapDemoJs"/>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDM3hLUh10lPdC4qzzQ24HMuVldsSja0yk"></script>
<script type="text/javascript" charset="UTF-8" src="${mapDemoJs}"></script>
