<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--@elvariable id="challenge" type="cz.upce.diplomovaprace.entity.Challenge"--%>
<%--@elvariable id="challenges" type="java.util.List<ccz.upce.diplomovaprace.entity.Challenge>"--%>
<%--@elvariable id="player" type="cz.upce.diplomovaprace.entity.User"--%>
<%--@elvariable id="challengeDetailModel" type="cz.upce.diplomovaprace.model.ChallengeDetailModel"--%>
<%--@elvariable id="isUserAlreadyInChallenge" type="java.lang.Boolean"--%>
<%--@elvariable id="isChallengeFinished" type="java.lang.Boolean"--%>
<%@ include file="../common/header.jsp" %>

<div class="content">
    <div class="container-fluid">
        <div class="row">
            <%-- START TEAM 1--%>

            <div class="col-md-12">
                <div class="card  ">
                    <div class="card-header card-header-primary">
                        <h4 class="card-title ">
                            <c:choose>
                                <c:when test="${challengeDetailModel.maxPlayers gt 2}">
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
                                <c:forEach items="${challengeDetailModel.firstTeam}" var="challengeDetailUserModel"
                                           varStatus="status">

                                    <tr>
                                        <td>
                                                ${challengeDetailUserModel.userName}
                                        </td>
                                        <td>
                                                ${challengeDetailUserModel.rating}
                                        </td>
                                        <td>
                                                ${challengeDetailUserModel.numberOfGames}
                                        </td>
                                        <td>
                                                ${challengeDetailUserModel.numberOfWins}
                                        </td>
                                        <td>
                                                ${challengeDetailUserModel.numberOfLosses}
                                        </td>
                                        <td>
                                                ${challengeDetailUserModel.numberOfTies}
                                        </td>
                                        <c:set var="scoreColor" value=""/>
                                        <c:if test="${challengeDetailUserModel.winningUserScore gt  challengeDetailUserModel.lossingUserScore}">
                                            <c:set var="scoreColor" value="color:green"/>
                                        </c:if>
                                        <c:if test="${challengeDetailUserModel.winningUserScore lt  challengeDetailUserModel.lossingUserScore}">
                                            <c:set var="scoreColor" value="color:red"/>
                                        </c:if>
                                        <c:if test="${challengeDetailUserModel.winningUserScore eq  challengeDetailUserModel.lossingUserScore and !challengeDetailUserModel.challengeResultState.equals('IN_PROGRESS')}">
                                            <c:set var="scoreColor" value="color:yellow"/>
                                        </c:if>

                                        <c:set var="scoreColor" value=""/>
                                        <c:set var="score"
                                               value="${challengeDetailUserModel.winningUserScore}:${challengeDetailUserModel.lossingUserScore}"/>
                                        <c:if test="${challengeDetailUserModel.challengeResultState eq 'WINNER'}">
                                            <c:set var="scoreColor" value="color:green"/>
                                        </c:if>
                                            ${challengeDetailUserModel.challengeResultState}
                                        <c:if test="${challengeDetailUserModel.challengeResultState eq 'DEFEATED'}">Ł
                                            <c:set var="scoreColor" value="color:red"/>
                                            <c:set var="score"
                                                   value="${challengeDetailUserModel.lossingUserScore}:${challengeDetailUserModel.winningUserScore}"/>
                                        </c:if>
                                        <c:if test="${challengeDetailUserModel.challengeResultState eq 'TIE'}">
                                            <c:set var="scoreColor" value="color:yellow"/>
                                        </c:if>
                                        <td style="${scoreColor}">
                                            <c:choose>
                                                <c:when test="${challengeDetailUserModel.challengeResultState.equals('IN_PROGRESS')}">
                                                    N/A
                                                </c:when>
                                                <c:otherwise>
                                                    ${score}
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td style="text-align: right">
                                            <c:if test="${ true }">
                                                <%-- ROLE ADMIN -- PRISTUP DO H2-CONSOLE A MENIT DATA NAPRIMO V DB --%>
                                                <%-- ROLE OPERATOR -- Uprava tech spornych challenge --%>
                                                <%--AND IF ROLE OPERATOR CO TOHLE MUZE TVORIT AZUROVA BARVA, PRO ROLI OPERATORA --%>
                                                <spring:url value="/challenge/enterResult" var="enterResultUrl">
                                                    <spring:param name="challengeId" value="${challenge.id}"/>
                                                    <spring:param name="challengeUserId"
                                                                  value="${challengeDetailUserModel.id}"/>
                                                </spring:url>
                                                <a href="${enterResultUrl}"
                                                   class="btn  btn-success btn-sm "
                                                   role="button"
                                                   aria-disabled="true">Opravit výsledek</a>
                                            </c:if>
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
                                <c:when test="${challengeDetailModel.maxPlayers gt 2}">
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
                                <c:forEach items="${challengeDetailModel.secondTeam}" var="challengeDetailUserModel"
                                           varStatus="status">

                                    <tr>
                                        <td>
                                                ${challengeDetailUserModel.userName}
                                        </td>
                                        <td>
                                                ${challengeDetailUserModel.rating}
                                        </td>
                                        <td>
                                                ${challengeDetailUserModel.numberOfGames}
                                        </td>
                                        <td>
                                                ${challengeDetailUserModel.numberOfWins}
                                        </td>
                                        <td>
                                                ${challengeDetailUserModel.numberOfLosses}
                                        </td>
                                        <td>
                                                ${challengeDetailUserModel.numberOfTies}
                                        </td>
                                        <c:set var="scoreColor" value=""/>
                                        <c:if test="${challengeDetailUserModel.winningUserScore gt  challengeDetailUserModel.lossingUserScore}">
                                            <c:set var="scoreColor" value="color:green"/>
                                        </c:if>
                                        <c:if test="${challengeDetailUserModel.winningUserScore lt  challengeDetailUserModel.lossingUserScore}">
                                            <c:set var="scoreColor" value="color:red"/>
                                        </c:if>
                                        <c:if test="${challengeDetailUserModel.winningUserScore eq  challengeDetailUserModel.lossingUserScore and !challengeDetailUserModel.challengeResultState.equals('IN_PROGRESS')}">
                                            <c:set var="scoreColor" value="color:yellow"/>
                                        </c:if>
                                        <td style="${scoreColor}">
                                            <c:choose>
                                                <c:when test="${challengeDetailUserModel.challengeResultState.equals('IN_PROGRESS')}">
                                                    N/A
                                                </c:when>
                                                <c:otherwise>
                                                    ${challengeDetailUserModel.winningUserScore}:${challengeDetailUserModel.lossingUserScore}
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td style="text-align: right">
                                            <c:if test="${ true }"> <%--AND IF ROLE OPERATOR CO TOHLE MUZE TVORIT --%>
                                                <spring:url value="/challenge/enterResult" var="enterResultUrl">
                                                    <spring:param name="challengeId" value="${challenge.id}"/>
                                                    <spring:param name="challengeUserId"
                                                                  value="${challengeDetailUserModel.id}"/>
                                                </spring:url>
                                                <a href="${enterResultUrl}"
                                                   class="btn  btn-success btn-sm "
                                                   role="button"
                                                   aria-disabled="true">Opravit výsledek</a>
                                            </c:if>
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
                <c:if test="${not isChallengeFinished and isUserAlreadyInChallenge}">
                    <a href="/challenge/enterResult?challengeId=${challenge.id}" class="btn btn-primary "
                       role="button"
                       aria-disabled="true">Zadat výsledek</a>
                </c:if>

                <c:if test="${not isChallengeFinished and isUserAlreadyInChallenge}">
                    <spring:url value="logout" var="logoutUrl" htmlEscape="true">
                        <spring:param name="challengeId" value="${challenge.id}"/>
                    </spring:url>
                    <a href="${logoutUrl}" class="btn btn-primary " role="button" aria-disabled="true">
                        Odhlásit se
                    </a>
                </c:if>
                <c:if test="${not isChallengeFinished and not isUserAlreadyInChallenge}">
                    <spring:url value="join" var="joinUrl" htmlEscape="true">
                        <spring:param name="challengeId" value="${challenge.id}"/>
                    </spring:url>
                    <a href="${joinUrl}" class="btn btn-primary" role="button" aria-disabled="true">
                        Připojit se k výzvě
                    </a>
                </c:if>
                <spring:url value="join" var="joinUrl" htmlEscape="true">
                    <spring:param name="challengeId" value="${challenge.id}"/>
                </spring:url>
                <a href="${joinUrl}" class="btn btn-success" role="button" aria-disabled="true">
                    Odstranit výzvu - pro operátora
                </a>
            </div>
        </div>
    </div>
</div>
<%@ include file="../common/footer.jsp" %>
