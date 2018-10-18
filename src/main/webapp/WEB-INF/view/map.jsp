<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--@elvariable id="challenge" type="cz.upce.diplomovaprace.entity.Challenge"--%>
<%--@elvariable id="challenges" type="java.util.List<ccz.upce.diplomovaprace.entity.Challenge>"--%>
<%--@elvariable id="r" type="cz.upce.diplomovaprace.entity.Rating"--%>

<jsp:include page="fragments/header.jsp"/>

<body class="dark-edition">
<div class="wrapper ">
    <jsp:include page="fragments/sidebar.jsp"/>


    <div class="main-panel">
        <jsp:include page="fragments/navbar.jsp"/>
        <div id="map"></div>
    </div>

</div>
<%--<jsp:include page="fragments/filterOnDaRightSide.jsp"/> UDELAT Z CHALLENGES MAPU<<--%>
<jsp:include page="fragments/jsCommon.jsp"/>
<script>
    var challenges = [
        <c:forEach items="${challenges}" var="challenge" varStatus="status">
        [
            '${challenge.coordsLat}', // Name - [i][0]
            '${challenge.coordsLng}', // Name - [i][0]
            '<b><spring:message code="map.challenge.game"/></b> ${challenge.gameByGameGameId.gameName}', // Name - [i][0]
            '<b><spring:message code="map.challenge.challengerName"/></b> ${challenge.userByChallengerUserId.username}', // Name - [i][0]
            '<b><spring:message code="map.challenge.rating"/></b> ${challenge.userByChallengerUserId.ratingsByUserId.stream().filter(r -> r.gameByGameGameId.gameId==challenge.gameByGameGameId.gameId).findFirst().orElse(null).rating}', // Name - [i][0]
            '<b><spring:message code="map.challenge.start"/></b> ${challenge.challengeStart}', // Name - [i][0]
            '<b><spring:message code="map.challenge.end"/></b> ${challenge.challengeEnd}',
            '<b><spring:message code="map.challenge.challengeDetail"/></b>',
            '${challenge.challengeId}',
            '${challenge.gameByGameGameId.gameName}',
        ]
        <c:if test="${!status.last}">
        ,
        </c:if>
        </c:forEach>
    ];
    $(document).ready(function () {
        // Javascript method's body can be found in assets/js/demos.js
        demo.initGoogleMaps();
    });
</script>
</body>

</html>