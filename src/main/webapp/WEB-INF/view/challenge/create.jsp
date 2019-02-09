<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--@elvariable id="challengeModel" type="cz.upce.diplomovaprace.model.ChallengeModel"--%>
<%--@elvariable id="games" type="List<cz.upce.diplomovaprace.entity.Game>"--%>
<%--@elvariable id="game" type="cz.upce.diplomovaprace.entity.Game"--%>

<%@ include file="../common/header.jsp" %>
<div class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <form:form method="POST" action="create" modelAttribute="challengeModel">
                    <div class="form-group">
                        <label class="label-control">
                            <spring:message code="challenge.create.start"/>
                        </label>
                        <form:input type="datetime-local" path="start" cssClass="form-control datetimepicker"
                                    value="${challengeModel.start}"/>
                        <form:errors path="start" cssClass="error"/>
                    </div>
                    <div class="form-group">
                        <label class="label-control">
                            <spring:message code="challenge.create.end"/>
                        </label>
                        <form:input type="datetime-local" path="end" cssClass="form-control datetimepicker"
                                    value="${challengeModel.end}"/>
                        <form:errors path="end" cssClass="error"/>
                    </div>
                    <div class="form-group">
                        <label class="label-control">
                            <spring:message code="challenge.create.coords"/>
                        </label>
                        <div class="row">
                            <div class="col">
                                <form:input path="latCoords" cssClass="form-control" placeholder="Lat"
                                            value="${challengeModel.latCoords}" disabled="true"/>
                                <form:input path="latCoords" type="hidden" value="${challengeModel.latCoords}"/>
                            </div>
                            <div class="col">
                                <form:input path="lngCoords" cssClass="form-control" placeholder="Lng"
                                            value="${challengeModel.lngCoords}" disabled="true"/>
                                <form:input path="lngCoords" type="hidden" value="${challengeModel.lngCoords}"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="label-control">
                            <spring:message code="challenge.create.game"/>
                        </label>
                        <form:select path="gameId" cssClass="form-control selectpicker">
                            <c:forEach items="${games}" var="game">
                                <form:option value="${game.id}" cssStyle="color:black">
                                    <spring:message code="global.game.${game.name}" text="${game.name}"/>
                                </form:option>
                            </c:forEach>
                        </form:select>
                    </div>
                    <div class="form-group">
                        <label class="label-control">
                            <spring:message code="challenge.create.desc"/>
                        </label>
                        <form:textarea path="description" cssClass="form-control"
                                       value="${challengeModel.description}" type="textarea" rows="5"/>
                    </div>
                    <button type="submit" class="btn btn-primary">
                        <spring:message code="global.submit"/>
                    </button>
                    <spring:url value="/game/create" var="enterResultUrl"/>
                    <a href="${enterResultUrl}" class="btn btn-primary" role="button" aria-disabled="true">
                        <spring:message code="challenge.create.newGame"/>
                    </a>
                </form:form>
            </div>
        </div>
    </div>
</div>
<%@ include file="../common/footer.jsp" %>
