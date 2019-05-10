<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%--@elvariable id="challenge" type="com.bartosektom.letsplayfolks.entity.Challenge"--%>
<%--@elvariable id="challengeDetailModel" type="com.bartosektom.letsplayfolks.model.ChallengeDetailModel"--%>
<%--@elvariable id="challengeDetailModelTeam" type="List<com.bartosektom.letsplayfolks.model.ChallengeDetailUserModel>"--%>
<%--@elvariable id="challengeDetailUserModel" type="com.bartosektom.letsplayfolks.model.ChallengeDetailUserModel"--%>
<%--@elvariable id="userId" type="java.lang.Integer"--%>
<%--@elvariable id="teamNumber" type="java.lang.Integer"--%>

<div class="col-md-12">
    <div class="card">
        <div class="card-header card-header-primary">
            <h4 class="card-title">
                <c:choose>
                    <c:when test="${challengeDetailModel.maxPlayers gt 2}">
                        <spring:message code="challenge.detail.team.card.title.team"/> ${teamNumber}
                    </c:when>
                    <c:otherwise>
                        <spring:message code="challenge.detail.team.card.title.player"/> ${teamNumber}
                    </c:otherwise>
                </c:choose>
            </h4>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table">
                    <thead class="text-primary">
                    <tr>
                        <th>
                            <spring:message code="challenge.detail.team.table.header.userName"/>
                        </th>
                        <th>
                            <spring:message code="challenge.detail.team.table.header.rating"/>
                        </th>
                        <th>
                            <spring:message code="challenge.detail.team.table.header.games"/>
                        </th>
                        <th>
                            <spring:message code="challenge.detail.team.table.header.wins"/>
                        </th>
                        <th>
                            <spring:message code="challenge.detail.team.table.header.losses"/>
                        </th>
                        <th>
                            <spring:message code="challenge.detail.team.table.header.ties"/>
                        </th>
                        <th>
                            <spring:message code="challenge.detail.team.table.header.result"/>
                        </th>
                        <th>
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${challengeDetailModelTeam}" var="challengeDetailUserModel">
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
                            <c:set var="score"
                                   value="${challengeDetailUserModel.winningUserScore}:${challengeDetailUserModel.losingUserScore}"/>
                            <c:if test="${challengeDetailUserModel.challengeResultState eq 'WINNER'}">
                                <c:set var="scoreColor" value="color:green"/>
                            </c:if>
                            <c:if test="${challengeDetailUserModel.challengeResultState eq 'DEFEATED'}">
                                <c:set var="scoreColor" value="color:red"/>
                                <c:set var="score"
                                       value="${challengeDetailUserModel.losingUserScore}:${challengeDetailUserModel.winningUserScore}"/>
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
                                <spring:url value="/friend/addToFriends" var="addToFriendsUrl">
                                    <spring:param name="userId" value="${challengeDetailUserModel.id}"/>
                                </spring:url>
                                <a href="${addToFriendsUrl}" role="button" class="btn btn-primary btn-sm
                                <c:if test="${!challengeDetailUserModel.canBeAddToFriends}">disabled</c:if>">
                                    <spring:message code="challenge.detail.addToFriends"/>
                                </a>
                                <spring:url value="/message/create" var="createMessageUrl">
                                    <spring:param name="userName" value="${challengeDetailUserModel.userName}"/>
                                </spring:url>
                                <a href="${createMessageUrl}" role="button" class="btn btn-primary btn-sm
                                    <c:if test="${!(challengeDetailUserModel.id != userId)}">disabled</c:if>">
                                    <spring:message code="challenge.detail.sendMessage"/>
                                </a>
                                <spring:url value="/report/report" var="reportUserUrl">
                                    <spring:param name="userId" value="${challengeDetailUserModel.id}"/>
                                </spring:url>
                                <a href="${reportUserUrl}" role="button" class="btn btn-primary btn-sm
                                    <c:if test="${!(challengeDetailUserModel.id != userId)}">disabled</c:if>">
                                    <spring:message code="global.report"/>
                                </a>
                                <sec:authorize access="hasAnyAuthority('OPERATOR','ADMIN')">
                                    <spring:url value="/challenge/enterResult" var="enterResultUrl">
                                        <spring:param name="challengeId" value="${challenge.id}"/>
                                        <spring:param name="challengeUserId"
                                                      value="${challengeDetailUserModel.id}"/>
                                    </spring:url>
                                    <a href="${enterResultUrl}" class="btn btn-success btn-sm" role="button">
                                        <spring:message code="challenge.detail.team.correctResult"/>
                                    </a>
                                </sec:authorize>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
