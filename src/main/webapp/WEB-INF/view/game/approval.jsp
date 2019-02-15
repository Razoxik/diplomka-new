<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--TODO: Přidat do tabulky Game, který uživatel hru vytvořil a do této tabulky potom dát Návrhováno tímto uživatelem--%>
<%--@elvariable id="gameModel" type="com.bartosektom.letsplayfolks.model.GameModel"--%>
<%--@elvariable id="gameModels" type="java.util.List<com.bartosektom.letsplayfolks.model.GameModel>"--%>

<%@ include file="../common/header.jsp" %>
<div class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header card-header-primary">
                        <h4 class="card-title"><spring:message code="game.approval.card.title"/></h4>
                        <p class="card-category"><spring:message code="game.approval.card.category"/></p>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table">
                                <thead class="text-primary">
                                <tr>
                                    <th>
                                        <spring:message code="game.approval.table.id"/>
                                    </th>
                                    <th>
                                        <spring:message code="game.approval.table.name"/>
                                    </th>
                                    <th>
                                        <spring:message code="game.approval.table.created"/>
                                    </th>
                                    <th>
                                        <spring:message code="game.approval.table.desc"/>
                                    </th>
                                    <th>
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${gameModels}" var="gameModel" varStatus="status">
                                    <tr>
                                        <td>
                                                ${gameModel.id}
                                        </td>
                                        <td>
                                                ${gameModel.name}
                                        </td>
                                        <td>
                                            <fmt:parseDate value="${gameModel.created}" pattern="yyyy-M-d'T'HH:mm"
                                                           var="parsedDateTime" type="both"/>
                                            <fmt:formatDate pattern="d.M.yyyy HH:mm" value="${parsedDateTime}"/>
                                        </td>
                                        <td>
                                                ${gameModel.description}
                                        </td>
                                        <td class=>
                                            <spring:url value="/game/create" var="gameCreate">
                                                <spring:param name="gameId" value="${gameModel.id}"/>
                                            </spring:url>
                                            <a href="${gameCreate}">
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
<%@ include file="../common/footer.jsp" %>
