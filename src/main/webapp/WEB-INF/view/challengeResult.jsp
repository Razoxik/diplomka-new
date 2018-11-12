<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--@elvariable id="challengeResultModel" type="cz.upce.diplomovaprace.model.ChallengeResultModel"--%>
<%--@elvariable id="challenge" type="cz.upce.diplomovaprace.entity.Challenge"--%>

<%@ include file="common/header.jsp" %>

<div class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <spring:url value="submitResult" var="submitResultUrl" htmlEscape="true">
                    <spring:param name="challengeId" value="${challenge.id}"/>
                </spring:url>
                <form:form method="POST" action="${submitResultUrl}" modelAttribute="challengeResultModel">
                    <div class="form-group">
                        <label class="label-control">How do you end?</label>
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


                    <div class="form-group">
                        <label class="label-control">your score: Skóre Hráč/Team1[Pepa, Fanda, Lama,
                            Klepmr]</label>
                        <form:input path="scoreTeam1" type="text" class="form-control datetimepicker"
                                    value="12"/>
                        <form:errors path="scoreTeam1" cssClass="error"/>
                    </div>
                    <div class="form-group">
                        <label class="label-control">oponnent sore: Skóre Hráč/Team2[Pepa, Fanda, Lama,
                            Klepmr]</label>
                        <form:input path="scoreTeam2" type="text" class="form-control datetimepicker"
                                    value="2"/>
                        <form:errors path="scoreTeam2" cssClass="error"/>
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
<%@ include file="common/footer.jsp" %>


