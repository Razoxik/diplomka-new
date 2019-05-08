<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--@elvariable id="gameModel" type="com.bartosektom.letsplayfolks.model.GameModel"--%>
<%--@elvariable id="approval" type="java.lang.Boolean"--%>

<%@ include file="../common/header.jsp" %>
<div class="content">
    <div class="container-fluid">
        <jsp:include page="../common/infoMessage.jsp"/>
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header card-header-primary">
                        <h4 class="card-title">
                            Založení nové aktivity
                        </h4>
                    </div>
                    <div class="card-body">
                        <form:form method="POST" action="/game/create" modelAttribute="gameModel">
                            <form:hidden path="id"/>
                            <div class="form-group">
                                <label class="label-control">
                                    <spring:message code="game.create.name"/>
                                </label>
                                <form:input path="name" cssClass="form-control" required="true"
                                            value="${gameModel.name}" type="text"/>
                            </div>
                            <div class="form-group">
                                <label class="label-control">
                                    <spring:message code="game.create.numberOfPlayers"/>
                                </label>
                                <form:input path="numberOfPlayers" cssClass="form-control" required="true"
                                            value="${gameModel.numberOfPlayers}" type="text" pattern="^\d*[2468]|[1-9][02468]$"
                                            title="Číslo musí být sudé a menší než 100."/>
                            </div>
                            <div class="form-group">
                                <label class="label-control">
                                    <spring:message code="game.create.description"/>
                                </label>
                                <form:textarea path="description" cssClass="form-control"
                                               value="${gameModel.description}" type="textarea" rows="5"/>
                            </div>
                            <c:if test="${not empty gameModel.id}">
                                <sec:authorize access="hasAnyAuthority('ADMIN')">
                                    <button type="submit" class="btn btn-danger"
                                            onclick="form.action='/game/approval';">
                                        <spring:message code="game.create.approveGame"/>
                                    </button>
                                    <button type="submit" class="btn btn-danger"
                                            onclick="form.action='/game/decline';">
                                        <spring:message code="game.create.declineGame"/>
                                    </button>
                                </sec:authorize>
                            </c:if>
                            <c:if test="${!approval}">
                                <button type="submit" class="btn btn-primary">
                                    <spring:message code="game.create.submitForApproval"/>
                                </button>
                            </c:if>
                        </form:form>
                        <%--
                          <form method="POST" action="/game/upload" enctype="multipart/form-data">
                              <input type="file" name="file"/><br/><br/>
                              <input type="hidden" name="${_csrf.parameterName}"
                                     value="${_csrf.token}"/>
                              <input type="submit" value="Vytvořit hru"/>
                          </form>--%>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../common/footer.jsp" %>
