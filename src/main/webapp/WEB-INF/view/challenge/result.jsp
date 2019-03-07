<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--@elvariable id="infoMessage" type="java.lang.String"--%>

<%--@elvariable id="challengeResultModel" type="com.bartosektom.letsplayfolks.model.ChallengeResultModel"--%>
<%--@elvariable id="challenge" type="com.bartosektom.letsplayfolks.entity.Challenge"--%>
<%--@elvariable id="challengeUserId" type="java.lang.Integer"--%>

<%@ include file="../common/header.jsp" %>
<div class="content">
    <div class="container-fluid">
        <c:if test="${not empty infoMessage}">
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                <spring:message code="${infoMessage}"/>
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </c:if>
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                <div class="card-header card-header-primary">
                    <h4 class="card-title">
                       Zadejte výsledek
                    </h4>
                </div>
                <div class="card-body">
                <spring:url value="submitResult" var="submitResultUrl" htmlEscape="true">
                    <spring:param name="challengeId" value="${challenge.id}"/>
                </spring:url>
                <form:form method="POST" action="${submitResultUrl}" modelAttribute="challengeResultModel">
                    <c:if test="${not empty challengeUserId}">
                        <form:input path="challengeUserId" type="hidden" value="${challengeUserId}"/>
                    </c:if>
                    <div class="form-group">
                        <label class="label-control">
                            <spring:message code="challenge.result.howItGoes"/>
                        </label>
                        <form:select path="resultState" cssClass="form-control selectpicker">
                            <form:option value="WINNER" cssStyle="color:grey">
                                <spring:message code="challenge.result.howItGoes.win"/>
                            </form:option>
                            <form:option value="DEFEATED" cssStyle="color:grey">
                                <spring:message code="challenge.result.howItGoes.loss"/>
                            </form:option>
                            <form:option value="TIE" id="draw" cssStyle="color:grey">
                                <spring:message code="challenge.result.howItGoes.tie"/>
                            </form:option>
                        </form:select>
                        <form:errors path="resultState" cssClass="error"/>
                    </div>
                    <script>
                        /*
                        const winnerScore = document.getElementById("winnerScore");
                        const loserScore = document.getElementById("loserScore");

                        function setMin() {
                            winnerScore.min = loserScore.value;
                        }

                        function setMax() {
                            loserScore.max = winnerScore.value;
                        }

                        const trigger = document.getElementById("winnerScore");
                        trigger.addEventListener("change", setMin, false);
                        const loserScoreTrigger = document.getElementById("loserScore");
                        loserScoreTrigger.addEventListener("change", setMax, false);
                        */
                    </script>
                    <div class="form-group">
                        <label class="label-control">
                            <spring:message code="challenge.result.winnerScore"/>
                        </label>
                        <form:input path="winnerScore" id="winnerScore" type="number" class="form-control"/>
                        <form:errors path="winnerScore" cssClass="error"/>
                    </div>
                    <div class="form-group">
                        <label class="label-control">
                            <spring:message code="challenge.result.losserScore"/>
                        </label>
                        <form:input path="loserScore" id="loserScore" type="number" class="form-control"/>
                            <%--  onchange="document.getElementById('winnerScore').min = this.value;" --%>
                        <form:errors path="loserScore" cssClass="error"/>
                    </div>
                    <div class="form-group">
                        <label class="label-control">
                            <spring:message code="challenge.result.description"/>
                        </label>
                        <form:textarea path="description" cssClass="form-control" type="textarea" rows="5"/>
                    </div>
                    <button type="submit" class="btn btn-primary">
                        <spring:message code="global.submit"/>
                    </button>
                </form:form>
            </div>
            </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../common/footer.jsp" %>


