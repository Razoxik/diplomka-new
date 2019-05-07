<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--@elvariable id="game" type="com.bartosektom.letsplayfolks.entity.Game"--%>
<%--@elvariable id="games" type="java.util.List<com.bartosektom.letsplayfolks.entity.Game>"--%>
<%--@elvariable id="leaderboardModels" type="java.util.List<com.bartosektom.letsplayfolks.model.LeaderboardModel>"--%>
<%--@elvariable id="leaderboardModel" type="com.bartosektom.letsplayfolks.model.LeaderboardModel"--%>

<%@ include file="../common/header.jsp" %>
<div class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <div class="form-group">
                    <label for="leaderboardGameSelect">Vyberte hru</label>
                    <form>
                        <select class="form-control selectpicker" id="leaderboardGameSelect"
                                name='gameId' onchange='this.form.submit()'>
                            <c:forEach items="${games}" var="game" varStatus="status">
                                <option value="${game.id}" style="color:black"
                                        <c:if test="${game.id eq leaderboardModels.get(0).gameId}">selected</c:if>>
                                    <spring:message code="global.game.${game.name}" text="${game.name}"/>
                                </option>
                            </c:forEach>
                        </select>
                    </form>
                </div>
                <div class="card">
                    <div class="card-header card-header-primary">
                        <h4 class="card-title"><spring:message code="leaderboard.table.header"/></h4>
                        <p class="card-category"><spring:message code="leaderboard.table.subheader.for"/>
                            <spring:message code="global.game.${leaderboardModels.get(0).gameName}"
                                            text="${leaderboardModels.get(0).gameName}"/>&#33;
                        </p>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table">
                                <thead class=" text-primary">
                                <tr>
                                    <th>
                                        DODELAT TADY JESTE PORADI TEDA V TEJ HRE???!!
                                        <spring:message code="leaderboard.table.user"/>
                                    </th>
                                    <th>
                                        <spring:message code="leaderboard.table.numberOfGames"/>
                                    </th>
                                    <th>
                                        <spring:message code="leaderboard.table.numberOfWins"/>
                                    </th>
                                    <th>
                                        <spring:message code="leaderboard.table.numberOfLooses"/>
                                    </th>
                                    <th>
                                        <spring:message code="leaderboard.table.numberOfTies"/>
                                    </th>
                                    <th>
                                        <spring:message code="leaderboard.table.WRratio"/>
                                    </th>
                                    <th>
                                        <spring:message code="leaderboard.table.rating"/>
                                    </th>
                                    <th>
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${leaderboardModels}" var="leaderboardModel" varStatus="status">
                                    <tr>
                                        <td>
                                                ${leaderboardModel.userName}
                                        </td>
                                        <td>
                                                ${leaderboardModel.numberOfGames}
                                        <td>
                                                ${leaderboardModel.numberOfWins}
                                        </td>
                                        <td>
                                                ${leaderboardModel.numberOfLooses}
                                        </td>
                                        <td>
                                                ${leaderboardModel.numberOfTies}
                                        </td>
                                        <td>
                                                ${leaderboardModel.winRateRatio} &#37;
                                        </td>
                                        <td class="text-primary">
                                                ${leaderboardModel.rating}
                                        </td>
                                        <spring:url value="/user/detail" var="userDetailUrl">
                                            <spring:param name="userId" value="${leaderboardModel.userId}"/>
                                        </spring:url>
                                        <td class=>
                                            <a href="${userDetailUrl}"><spring:message code="global.detailLink"/></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../common/footer.jsp" %>
