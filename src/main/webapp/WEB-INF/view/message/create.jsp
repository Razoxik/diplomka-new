<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--@elvariable id="messageModel" type="com.bartosektom.letsplayfolks.model.MessageModel"--%>
<%--@elvariable id="userNotFound" type="java.lang.Boolean"--%>

<spring:message code="global.mandatory.form.attribute" var="mandatoryFieldError"/>

<%@ include file="../common/header.jsp" %>
<div class="content">
    <div class="container-fluid">
        <c:if test="${userNotFound}">
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                <spring:message code="message.create.error.userNameNotFound"/>
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </c:if>
        <div class="row">
            <div class="col-md-12">
                <form:form method="POST" action="send" modelAttribute="messageModel">
                    <div class="form-group">
                        <label for="author"><spring:message code="message.create.playerUsername"/></label>
                        <form:input path="author" type="text" id="author" cssClass="form-control"
                                    value="${messageModel.author}"
                                    aria-describedby="author" required="true"
                                    oninvalid="this.setCustomValidity('${mandatoryFieldError}')"
                                    onchange="this.setCustomValidity('')"/>
                    </div>
                    <div class="form-group">
                        <label for="subject"><spring:message code="message.create.subject"/></label>
                        <form:input path="subject" type="text" id="subject" cssClass="form-control"
                                    aria-describedby="subject" required="true"
                                    oninvalid="this.setCustomValidity('${mandatoryFieldError}')"
                                    onchange="this.setCustomValidity('')"/>
                    </div>
                    <div class="form-group">
                        <label for="messageText"><spring:message code="message.create.text"/></label>
                        <form:textarea path="text" type="text" id="messageText" cssClass="form-control"
                                       aria-describedby="messageText" rows="5" maxlength="250"/>
                    </div>
                    <button type="submit" class="btn btn-primary">
                        <spring:message code="message.create.submit"/>
                    </button>
                </form:form>
            </div>
        </div>
    </div>
</div>
<%@ include file="../common/footer.jsp" %>
