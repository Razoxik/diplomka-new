<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--@elvariable id="infoMessage" type="java.lang.String"--%>
<%--@elvariable id="reportReasons" type="java.util.List<com.bartosektom.letsplayfolks.entity.ReportReason>"--%>
<%--@elvariable id="reportModel" type="com.bartosektom.letsplayfolks.model.ReportModel"--%>

<%@ include file="../common/header.jsp" %>
<div class="content">
    <div class="container-fluid">
        <c:if test="${not empty infoMessage}">
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                    ${reportModel.userName}&nbsp<spring:message code="${infoMessage}"/>
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
                     Nahlásit uživatele
                    </h4>
                </div>
                <div class="card-body">
                <form:form method="POST" action="report" modelAttribute="reportModel">
                    <form:input path="userId" type="hidden" value="${reportModel.userId}"/>
                    <div class="form-group">
                        <label for="author"><spring:message code="message.create.playerUsername"/></label>
                        <form:input path="userName" type="text" id="author" cssClass="form-control"
                                    value="${reportModel.userName}" disabled="true"
                                    aria-describedby="author" required="true"/>
                    </div>
                    <div class="form-group">
                        <label class="label-control">Důvod nahlášení</label>
                        <form:select path="reportReasonId" cssClass="form-control selectpicker" required="true">
                            <c:forEach items="${reportReasons}" var="reportReason">
                                <form:option value="${reportReason.id}" cssStyle="color:grey">
                                    <spring:message code="report.reportReason.${reportReason.reason}"/>
                                </form:option>
                            </c:forEach>
                        </form:select>
                    </div>
                    <div class="form-group">
                        <label for="messageText">Popis</label>
                        <form:textarea path="description" type="text" id="messageText" cssClass="form-control"
                                       aria-describedby="messageText" rows="5" maxlength="250"/>
                    </div>
                    <button type="submit" class="btn btn-primary" <c:if test="${not empty infoMessage}">disabled</c:if>>
                        <spring:message code="message.create.submit"/>
                    </button>
                </form:form>
                </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../common/footer.jsp" %>
