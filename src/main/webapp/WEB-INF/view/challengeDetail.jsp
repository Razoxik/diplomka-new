<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="challenge" type="cz.upce.diplomovaprace.entity.Challenge"--%>
<%--@elvariable id="challenges" type="java.util.List<ccz.upce.diplomovaprace.entity.Challenge>"--%>
<%--@elvariable id="player" type="cz.upce.diplomovaprace.entity.User"--%>
<%--@elvariable id="players" type="java.util.List<cz.upce.diplomovaprace.entity.User>"--%>
<%--@elvariable id="challengeDetailDto" type="cz.upce.diplomovaprace.dto.ChallengeDetailDto"--%>


<jsp:include page="fragments/header.jsp"/>


<body class="dark-edition">
<div class="wrapper ">
    <jsp:include page="fragments/sidebar.jsp"/>

    <div class="main-panel">
        <jsp:include page="fragments/navbar.jsp"/>

        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <%-- START TEAM 1--%>

                    <div class="col-md-12">
                        <div class="card  ">
                            <div class="card-header card-header-primary">
                                <h4 class="card-title ">
                                    <c:choose>
                                        <c:when test="${challengeDetailDto.maxPlayers gt 2}">
                                            Team 1
                                        </c:when>
                                        <c:otherwise>
                                            Hráč 1
                                        </c:otherwise>
                                    </c:choose>
                                </h4>
                                <%--   <p class="card-category"> Here is a subtitle for this table</p>--%>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table">
                                        <thead class=" text-primary">
                                        <tr>
                                            <th>
                                                User name
                                            </th>
                                            <th>
                                                Rating
                                            </th>
                                            <th>
                                                Games
                                            </th>
                                            <th>
                                                Wins
                                            </th>
                                            <th>
                                                Losses
                                            </th>
                                            <th>
                                                Ties
                                            </th>
                                            <th>
                                               Result
                                            </th>
                                            <th>
                                                <%--TH PRO TLACITKA --%>
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${challengeDetailDto.firstTeam}" var="userDto"
                                                   varStatus="status">

                                            <tr>
                                                <td>
                                                        ${userDto.userName}
                                                </td>
                                                <td>
                                                        ${userDto.rating}
                                                </td>
                                                <td>
                                                        ${userDto.numberOfGames}
                                                </td>
                                                <td>
                                                        ${userDto.numberOfWins}
                                                </td>
                                                <td>
                                                        ${userDto.numberOfLosses}
                                                </td>
                                                <td>
                                                        ${userDto.numberOfTies}
                                                </td>
                                                <td>
                                                        ${userDto.result}

                                                </td>
                                                <td style="text-align: right">

                                                    <a href="reply?messageId=1" class="btn  btn-primary btn-sm "
                                                       role="button"
                                                       aria-disabled="true">Přidat do přátel</a>
                                                    <a href="reply?messageId=1" class="btn btn-primary btn-sm"
                                                       role="button"
                                                       aria-disabled="true">Report</a>
                                                    <a href="reply?messageId=1" class="btn  btn-primary btn-sm"
                                                       role="button"
                                                       aria-disabled="true">Zpráva</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%-- END TEAM 1--%>
                    <div class="col-md-12">
                        <div class="card  ">
                            <div class="card-header card-header-primary">
                                <h4 class="card-title ">
                                    <c:choose>
                                        <c:when test="${challengeDetailDto.maxPlayers gt 2}">
                                            Team 2
                                        </c:when>
                                        <c:otherwise>
                                            Hráč 2
                                        </c:otherwise>
                                    </c:choose>
                                </h4>
                                <%--   <p class="card-category"> Here is a subtitle for this table</p>--%>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table">
                                        <thead class=" text-primary">
                                        <tr>
                                            <th>
                                                User name
                                            </th>
                                            <th>
                                                Rating
                                            </th>
                                            <th>
                                                Games
                                            </th>
                                            <th>
                                                Wins
                                            </th>
                                            <th>
                                                Losses
                                            </th>
                                            <th>
                                                Ties
                                            </th>
                                            <th>
                                                Result
                                            </th>
                                            <th>
                                                <%--TH PRO TLACITKA --%>
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${challengeDetailDto.secondTeam}" var="userDto"
                                                   varStatus="status">

                                            <tr>
                                                <td>
                                                        ${userDto.userName}
                                                </td>
                                                <td>
                                                        ${userDto.rating}
                                                </td>
                                                <td>
                                                        ${userDto.numberOfGames}
                                                </td>
                                                <td>
                                                        ${userDto.numberOfWins}
                                                </td>
                                                <td>
                                                        ${userDto.numberOfLosses}
                                                </td>
                                                <td>
                                                        ${userDto.numberOfTies}
                                                </td>
                                                <td>
                                                        ${userDto.result}
                                                </td>
                                                <td style="text-align: right">

                                                    <a href="reply?messageId=1" class="btn  btn-primary btn-sm "
                                                       role="button"
                                                       aria-disabled="true">Přidat do přátel</a>
                                                    <a href="reply?messageId=1" class="btn btn-primary btn-sm"
                                                       role="button"
                                                       aria-disabled="true">Report</a>
                                                    <a href="reply?messageId=1" class="btn  btn-primary btn-sm"
                                                       role="button"
                                                       aria-disabled="true">Zpráva</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <a href="/challenge/enterResult?challengeId=${challenge.id}" class="btn btn-primary " role="button"
                           aria-disabled="true">Zadat výsledek</a>
                        <%-- IF MUYES SE PRIPOJIT? JSI UZ PRIPOJENEJ?</p>--%>
                        <a href="/challenge/join?challengeId=${challenge.id}" class="btn btn-primary " role="button"
                           aria-disabled="true">Připojit se k výzvě</a>
                        <%-- ELSE tlacitko odhlasit?</p>--%>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="fragments/footer.jsp"/>
    </div>
</div>
<jsp:include page="fragments/filterOnDaRightSide.jsp"/>
</body>
<jsp:include page="fragments/jsCommon.jsp"/>

</html>