<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--@elvariable id="challengeResultModel" type="cz.upce.diplomovaprace.model.ChallengeResultModel"--%>
<%--@elvariable id="challenge" type="cz.upce.diplomovaprace.entity.Challenge"--%>
<%--@elvariable id="challengeUserId" type="java.lang.Integer"--%>

<%@ include file="../common/header.jsp" %>

<div class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <spring:url value="submitResult" var="submitResultUrl" htmlEscape="true">
                    <spring:param name="challengeId" value="${challenge.id}"/>

                </spring:url>
                <form:form method="POST" action="${submitResultUrl}" modelAttribute="challengeResultModel">
                    <c:if test="${not empty challengeUserId}">
                      <form:input path="challengeUserId" type="hidden" value="${  challengeUserId}"/>
                    </c:if>
                    <div class="form-group">
                        <label class="label-control">Jak si dopadl?</label>
                        <form:select path="resultState" cssClass="form-control selectpicker"
                                     id="exampleFormControlSelect1">
                            <%--<c:forEach items="${games}" var="game" varStatus="status">--%>
                            <form:option value="WINNER" cssStyle="color:black">Good, Im win!</form:option>
                            <form:option value="DEFEATED" cssStyle="color:black">Bad, Im lose</form:option>
                            <form:option value="TIE" cssStyle="color:black">Alright, It was tie.</form:option>
                            <%--    </c:forEach>--%>
                        </form:select>
                        <form:errors path="resultState" cssClass="error"/>
                    </div>

                    <script>
                        var winnerScore = document.getElementById("winnerScore");
                        var loserScore = document.getElementById("loserScore");
                        function setMin() {
                            winnerScore.min = loserScore.value;
                        }
                        function setMax() {
                            loserScore.max = winnerScore.value;
                        }
                        var trigger = document.getElementById("winnerScore");
                        trigger.addEventListener("change", setMin, false);
                        var loserScoreTrigger = document.getElementById("loserScore");
                        loserScoreTrigger.addEventListener("change", setMax, false);
                    </script>
                    <div class="form-group">
                        <label class="label-control">Vítězné scoŕe
                            Klepmr]</label>
                        <form:input path="winnerScore" id="winnerScore" type="number" class="form-control"
                                    value="12"/>
                        <form:errors path="winnerScore" cssClass="error"/>
                    </div>
                    <div class="form-group">
                        <label class="label-control">skore porazeneho
                            Klepmr]</label>
                        <form:input path="loserScore" id="loserScore" type="number" class="form-control"
                                    value="2" onchange="document.getElementById('winnerScore').min=this.value;"/>
                        <form:errors path="loserScore" cssClass="error"/>
                    </div>

                    <div class="form-group">
                        <label for="exampleFormControlTextarea1">Description</label>
                        <textarea class="form-control" id="exampleFormControlTextarea1" rows="5"></textarea>
                    </div>

                    <button type="submit" class="btn btn-primary">Submit</button>
                </form:form>
            </div>
        </div>

    </div>
</div>
<%@ include file="../common/footer.jsp" %>


