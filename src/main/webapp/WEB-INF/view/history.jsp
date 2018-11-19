<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--@elvariable id="challengeResult" type="cz.upce.diplomovaprace.entity.ChallengeResult"--%>
<%--@elvariable id="challengeResults" type="java.util.List<ccz.upce.diplomovaprace.entity.ChallengeResult>"--%>

<%@ include file="common/header.jsp" %>

<div class="content">
    <div class="container-fluid">
        <div class="row">

            <div class="col-md-12">
                <div class="card  ">
                    <div class="card-header card-header-primary">
                        <h4 class="card-title ">Historie</h4>
                        <p class="card-category">Zde vidíte historii jednotlivých zápasů.</p>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table">
                                <thead class=" text-primary">
                                <tr>
                                    <th>
                                        ID
                                    </th>
                                    <th>
                                        Name
                                    </th>
                                    <th>
                                        Skore
                                    </th>
                                    <th>
                                        Datum
                                    </th>
                                    <th>
                                        Něco
                                    </th>
                                    <th>
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${challengeResults}" var="challengeResult" varStatus="status">
                                    <tr>
                                        <td>
                                                ${challengeResult.id}
                                        </td>
                                        <td>
                                                ${challengeResult.challengeByChallengeId.gameByGameId.name}
                                        </td>
                                        <td>
                                                ${challengeResult.scoreWinner} :${challengeResult.scoreDefeated} </td>
                                        <td>
                                                ${challengeResult.created     }    </td>
                                        <td>
                                            blabla    </td>

                                        <spring:url value="/challenge/detail" var="challengeDetailUrl">
                                            <spring:param name="challengeId" value="${challengeResult.challengeByChallengeId.id}"/>
                                        </spring:url>
                                        <td>
                                            <a href="${challengeDetailUrl}">Zobrazit detail</a>
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
<%@ include file="common/footer.jsp" %>
