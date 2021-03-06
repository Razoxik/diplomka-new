<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--@elvariable id="challenge" type="com.bartosektom.letsplayfolks.entity.Challenge"--%>
<%--@elvariable id="challengeDetailModel" type="com.bartosektom.letsplayfolks.model.ChallengeDetailModel"--%>
<%--@elvariable id="isUserAlreadyInChallenge" type="java.lang.Boolean"--%>
<%--@elvariable id="isChallengeFinished" type="java.lang.Boolean"--%>
<%--@elvariable id="canBeAddedToFriends" type="java.lang.Boolean"--%>
<%--@elvariable id="canUserEnterResult" type="java.lang.Boolean"--%>
<%--@elvariable id="isChallengeFull" type="java.lang.Boolean"--%>
<%--@elvariable id="infoMessage" type="java.lang.String"--%>

<%@ include file="../common/header.jsp" %>
<div class="content">
    <div class="container-fluid">
        <jsp:include page="../common/infoMessage.jsp"/>
        <div class="row">
            <%-- START TEAM 1 --%>
            <c:set var="challengeDetailModelTeam" value="${challengeDetailModel.firstTeam}" scope="request"/>
            <c:set var="teamNumber" value="1" scope="request"/>
            <jsp:include page="team.jsp"/>
            <%-- START TEAM 2 --%>
            <c:set var="challengeDetailModelTeam" value="${challengeDetailModel.secondTeam}" scope="request"/>
            <c:set var="teamNumber" value="2" scope="request"/>
            <jsp:include page="team.jsp"/>
            <div class="col-md-12">
                <%-- TODO: !!!and canUserEnterResult missing: just for testing purpose!!! --%>
                <c:if test="${not isChallengeFinished and isUserAlreadyInChallenge}">
                    <spring:url value="enterResult" var="enterResultUrl" htmlEscape="true">
                        <spring:param name="challengeId" value="${challenge.id}"/>
                    </spring:url>
                    <a href="${enterResultUrl}" class="btn btn-primary" role="button" aria-disabled="true">
                        <spring:message code="challenge.detail.enterResult"/>
                    </a>
                </c:if>
                <c:if test="${not isChallengeFinished and isUserAlreadyInChallenge}">
                    <spring:url value="logout" var="logoutUrl" htmlEscape="true">
                        <spring:param name="challengeId" value="${challenge.id}"/>
                    </spring:url>
                    <a href="${logoutUrl}" class="btn btn-primary" role="button" aria-disabled="true">
                        <spring:message code="challenge.detail.logout"/>
                    </a>
                </c:if>
                <c:if test="${not isChallengeFinished and not isUserAlreadyInChallenge and not isChallengeFull}">
                    <spring:url value="join" var="joinUrl" htmlEscape="true">
                        <spring:param name="challengeId" value="${challenge.id}"/>
                    </spring:url>
                    <a href="${joinUrl}" class="btn btn-primary" role="button" aria-disabled="true">
                        <spring:message code="challenge.detail.login"/>
                    </a>
                </c:if>
            </div>
        </div>
    </div>
</div>
<%@ include file="../common/footer.jsp" %>
