<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--@elvariable id="_csrf" type="org.springframework.security.web.csrf.CsrfToken"--%>

<%@ include file="../common/header.jsp" %>
<div class="content">
    <div class="container-fluid">
        <jsp:include page="../common/infoMessage.jsp"/>
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header card-header-primary">
                        <h4 class="card-title"><spring:message code="login.form.title"/></h4>
                        <p class="card-category"><spring:message code="login.form.subtitle"/></p>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <form name='loginForm' action="<c:url value='/login' />" method='POST'>
                                <div class="form-group">
                                    <label for="login" class="label-control">
                                        <spring:message code="login.form.userNameInput"/>
                                    </label>
                                    <input id="login" type='text' class="form-control" name='username' value="admin"/>
                                </div>
                                <div for="pw" class="form-group">
                                    <label class="label-control">
                                        <spring:message code="login.form.passwordInput"/>
                                    </label>
                                    <input id="pw" type='password' class="form-control" name='password' value="admin"/>
                                </div>
                                <button type="submit" class="btn btn-primary">
                                    <spring:message code="login.form.submitButton"/>
                                </button>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <spring:url value="/user/registration" var="registrationUrl"/>
                                <p><spring:message code="login.noAccount.text"/>
                                    <a href="${registrationUrl}">
                                        <strong><spring:message code="login.noAccount.link"/></strong>
                                    </a>
                                    <span>&#33;</span>
                                </p>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../common/footer.jsp" %>