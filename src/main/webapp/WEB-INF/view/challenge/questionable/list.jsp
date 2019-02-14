<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--@elvariable id="questionableChallenge" type="com.bartosektom.letsplayfolks.model.QuestionableChallengeModel"--%>
<%--@elvariable id="questionableChallenges" type="java.util.List<cz.upce.diplomovaprace.model.QuestionableChallengeModel>"--%>

<%@ include file="../../common/header.jsp" %>
<div class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header card-header-primary">
                        <h4 class="card-title"><spring:message code="challenge.questionable.list.card.title"/></h4>
                        <p class="card-category"><spring:message code="challenge.questionable.list.card.category"/></p>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table">
                                <thead class="text-primary">
                                <tr>
                                    <th><spring:message code="challenge.questionable.list.table.id"/></th>
                                    <th><spring:message code="challenge.questionable.list.table.gameName"/></th>
                                    <th><spring:message code="challenge.questionable.list.table.created"/></th>
                                    <th><spring:message code="challenge.questionable.list.table.end"/></th>
                                    <th><spring:message code="challenge.questionable.list.table.reason"/></th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${questionableChallenges}" var="questionableChallenge">
                                    <tr>
                                        <td> ${questionableChallenge.id} </td>
                                        <td> ${questionableChallenge.gameName} </td>
                                        <td>
                                            <fmt:parseDate value="${questionableChallenge.created}"
                                                           pattern="yyyy-M-d'T'HH:mm"
                                                           var="parsedDateTime" type="both"/>
                                            <fmt:formatDate pattern="d.M.yyyy HH:mm" value="${parsedDateTime}"/>
                                        </td>
                                        <td>
                                            <fmt:parseDate value="${questionableChallenge.end}"
                                                           pattern="yyyy-M-d'T'HH:mm"
                                                           var="parsedDateTime" type="both"/>
                                            <fmt:formatDate pattern="d.M.yyyy HH:mm" value="${parsedDateTime}"/>
                                        </td>
                                        <td>
                                            <c:set var="reason" value="${questionableChallenge.reason}"/>
                                            <spring:message code="challenge.questionable.list.table.reason.${reason}"/>
                                        </td>
                                        <td>
                                            <spring:url value="/challenge/detail" var="challengeDetailUrl">
                                                <spring:param name="challengeId" value="${questionableChallenge.id}"/>
                                            </spring:url>
                                            <a href="${challengeDetailUrl}">
                                                <spring:message code="global.detailLink"/>
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
<%@ include file="../../common/footer.jsp" %>
