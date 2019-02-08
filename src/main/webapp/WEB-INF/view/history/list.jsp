<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--@elvariable id="historyModel" type="cz.upce.diplomovaprace.model.HistoryModel"--%>
<%--@elvariable id="historyModels" type="java.util.List<cz.upce.diplomovaprace.model.HistoryModel>"--%>

<%@ include file="../common/header.jsp" %>
<div class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header card-header-primary">
                        <h4 class="card-title "><spring:message code="history.table.header"/></h4>
                        <p class="card-category"><spring:message code="history.table.subheader"/></p>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table">
                                <thead class=" text-primary">
                                <tr>
                                    <th>
                                        <spring:message code="history.table.game"/>
                                    </th>
                                    <th>
                                        <spring:message code="history.table.date"/>
                                    </th>
                                    <th>
                                        <spring:message code="history.table.result"/>
                                    </th>
                                    <th>
                                        <spring:message code="history.table.score"/>
                                    </th>
                                    <th>
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${historyModels}" var="historyModel" varStatus="status">
                                    <tr>
                                        <td>
                                            <spring:message code="global.game.${historyModel.gameName}" text="${historyModel.gameName}"/>
                                        </td>
                                        <td>
                                                ${historyModel.start}
                                        </td>
                                        <c:set var="resultColor" value=""/>
                                        <c:set var="score" value=""/>
                                        <c:if test="${historyModel.resultState eq 'WINNER' }">
                                            <c:set var="resultColor" value="color:green"/>
                                            <c:set var="score"
                                                   value="${historyModel.scoreWinner}:${historyModel.scoreLooser}"/>
                                        </c:if>
                                        <c:if test="${historyModel.resultState eq 'DEFEATED' }">
                                            <c:set var="resultColor" value="color:red"/>
                                            <c:set var="score"
                                                   value="${historyModel.scoreLooser}:${historyModel.scoreWinner}"/>
                                        </c:if>
                                        <c:if test="${historyModel.resultState eq 'TIE' }">
                                            <c:set var="resultColor" value="color:yellow"/>
                                            <c:set var="score"
                                                   value="${historyModel.scoreWinner}:${historyModel.scoreLooser}"/>
                                        </c:if>
                                        <c:if test="${historyModel.resultState eq 'IN_PROGRESS' }">
                                            <c:set var="score" value="N/A"/>
                                        </c:if>
                                        <td style="${resultColor}">
                                            <spring:message code="global.gameResult.${historyModel.resultState}"/>
                                        </td>
                                        <td>
                                                ${score}
                                        </td>
                                        <spring:url value="/challenge/detail" var="challengeDetailUrl">
                                            <spring:param name="challengeId" value="${historyModel.challengeId}"/>
                                        </spring:url>
                                        <td>
                                            <a href="${challengeDetailUrl}">
                                                <spring:message code="history.linkToChallenge"/>
                                            </a>
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
