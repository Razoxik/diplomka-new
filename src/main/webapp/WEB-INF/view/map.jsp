<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--@elvariable id="challenge" type="cz.upce.diplomovaprace.entity.Challenge"--%>
<%--@elvariable id="challengeDtos" type="java.util.List<cz.upce.diplomovaprace.dto.ChallengeDto>"--%>
<%--@elvariable id="challengeDto" type="cz.upce.diplomovaprace.dto.ChallengeDto"--%>

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
<%--${challenge.challengeResultsById.stream().filter(challengeResult -> challengeResult.getChallengeByChallengeId().getId()==challenge.id).findFirst().orElse(null).userByUserId.ratingsById.stream().filter(rating -> rating.getGameByGameId().getId()==challenge.gameByGameId.id).findFirst().orElse(null).rating}
--%>
<jsp:include page="fragments/jsCommon.jsp"/>
<script>
    var challenges = [
        <c:forEach items="${challengeDtos}" var="challengeDto" varStatus="status">
        [
            '${challengeDto.challenge.coordsLat}', // Name - [i][0]
            '${challengeDto.challenge.coordsLng}', // Name - [i][0]
            '<b><spring:message code="map.challenge.game"/></b> <spring:message code="global.game.${challengeDto.game.name}"/>', // Name - [i][0]
            '<b><spring:message code="map.challenge.challengerName"/></b>  ${challengeDto.host.userName}', // Name - [i][0]
            '<b><spring:message code="map.challenge.rating"/></b> ${challengeDto.rating}', // Name - [i][0]
            '<b><spring:message code="map.challenge.start"/></b> ${challengeDto.start}', // Name - [i][0]
            '<b><spring:message code="map.challenge.end"/></b> ${challengeDto.end}',
            '<b><spring:message code="map.challenge.players"/></b> ${challengeDto.listOfPlayers.size()} / ${challengeDto.maxPlayers}',
            '<b><spring:message code="map.challenge.challengeDetail"/></b>',
            '${challengeDto.challenge.id}',
            '${challengeDto.game.name}',
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