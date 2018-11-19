<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%--@elvariable id="activeTab" type="java.lang.String"--%>

<div class="sidebar" data-color="purple" data-background-color="black" data-image="/img/sidebar-2.jpg">
    <!--
    Tip 1: You can change the color of the sidebar using: data-color="purple | azure | green | orange | danger"

    Tip 2: you can also add an image using data-image tag
    -->
    <div class="logo">
        <spring:url value="https://letsplayfolks.herokuapp.com/" var="appUrl"/>
        <a href="${appUrl}" class="simple-text logo-normal">
            <spring:message code="global.app.name"/>
        </a>
    </div>
    <div class="sidebar-wrapper">
        <ul class="nav">
            <li class="nav-item <c:if test="${activeTab eq 'NEWS'}">active</c:if>">
                <spring:url value="/news" var="newsUrl"/>
                <a class="nav-link" href="${newsUrl}">
                    <i class="material-icons">home</i>
                    <p><spring:message code="sidebar.user.news"/></p>
                </a>
            </li>
            <sec:authorize access="hasAnyAuthority('USER','OPERATOR','ADMIN')">
                <li class="nav-item <c:if test="${activeTab eq 'USER PROFILE'}">active</c:if>">
                    <spring:url value="/user" var="userUrl"/>
                    <a class="nav-link" href="${userUrl}">
                        <i class="material-icons">person</i>
                        <p><spring:message code="sidebar.user.userProfile"/></p>
                    </a>
                </li>
                <li class="nav-item <c:if test="${activeTab eq 'FRIENDS'}">active</c:if>">
                    <spring:url value="/friends" var="friendsUrl"/>
                    <a class="nav-link" href="${friendsUrl}">
                        <i class="material-icons">people</i>
                        <p><spring:message code="sidebar.user.friends"/></p>
                    </a>
                </li>
                <li class="nav-item  <c:if test="${activeTab eq 'MESSAGES'}">active</c:if>">
                    <spring:url value="/message/list" var="messageUrl"/>
                    <a class="nav-link" href="${messageUrl}">
                        <i class="material-icons">message</i>
                        <p><spring:message code="sidebar.user.messages"/></p>
                    </a>
                </li>
            </sec:authorize>
            <li class="nav-item <c:if test="${activeTab eq 'MAP' }">active</c:if>">
                <spring:url value="/map" var="mapUrl"/>
                <a class="nav-link" href="${mapUrl}">
                    <i class="material-icons">location_ons</i>
                    <p><spring:message code="sidebar.user.map"/></p>
                </a>
            </li>
            <sec:authorize access="hasAnyAuthority('USER','OPERATOR','ADMIN')">
                <li class="nav-item <c:if test="${activeTab eq 'HISTORY'}">active</c:if>">
                    <spring:url value="/history/list" var="historyUrl"/>
                    <a class="nav-link" href="${historyUrl}">
                        <i class="material-icons">content_paste</i>
                        <p><spring:message code="sidebar.user.history"/></p>
                    </a>
                </li>
            </sec:authorize>
            <li class="nav-item <c:if test="${activeTab eq 'LEADERBOARD' }">active</c:if>">
                <spring:url value="/leaderboard" var="leaderboardUrl"/>
                <a class="nav-link" href="${leaderboardUrl}">
                    <i class="material-icons">dashboard</i>
                    <p><spring:message code="sidebar.user.leaderboard"/></p>
                </a>
            </li>
            <sec:authorize access="hasAnyAuthority('OPERATOR','ADMIN')">
                <li class="nav-item">
                    <spring:url value="/operator" var="operatorUrl"/>
                    <a class="nav-link success-link" href="${operatorUrl}">
                        <i class="material-icons">settings</i>
                        <p><spring:message code="sidebar.operator"/></p>
                    </a>
                </li>
            </sec:authorize>
            <sec:authorize access="hasAnyAuthority('ADMIN')">
                <li class="nav-item">
                    <spring:url value="/admin/gameApproval" var="gameApprovalUrl"/>
                    <a class="nav-link danger-link" href="${gameApprovalUrl}">
                        <i class="material-icons">videogame_asset</i>
                        <p><spring:message code="sidebar.admin.gameApproval"/></p>
                    </a>
                </li>
                <li class="nav-item">
                    <spring:url value="/admin/console" var="consoleUrl"/>
                    <a class="nav-link danger-link" href="${consoleUrl}">
                        <i class="material-icons">verified_user</i>
                        <p><spring:message code="sidebar.admin.console"/></p>
                    </a>
                </li>
            </sec:authorize>
        </ul>
    </div>
</div>
<%--   <li class="nav-item">
               <a class="nav-link" href="../../theme/examples/icons.html">
                   <i class="material-icons">bubble_chart</i>
                   <p>Statistics</p>
               </a>
           </li>--%>
<%--  <li class="nav-item">
      <a class="nav-link" href="../../theme/examples/notifications.html">
          <i class="material-icons">notifications</i>
          <p>Notifications</p>
      </a>
  </li>--%>
