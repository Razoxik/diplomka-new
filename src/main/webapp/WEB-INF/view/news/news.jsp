<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../common/header.jsp" %>
<div class="content">
    <div class="container-fluid">
        <jsp:include page="../common/infoMessage.jsp"/>
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header card-header-primary">
                        <h4 class="card-title">
                            <spring:message code="news.example.one"/>
                        </h4>
                        <p class="card-category">
                            <spring:message code="news.example.one.subheader"/>
                        </p>
                    </div>
                    <div class="card-body">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
                        incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
                        exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure
                        dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.
                        Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt
                        mollit anim id est laborum.
                    </div>
                    <div class="card-footer text-muted">
                        12.4.2019
                    </div>
                </div>
                <div class="card">
                    <div class="card-header card-header-primary">
                        <h4 class="card-title">
                            <spring:message code="news.example.two"/>
                        </h4>
                        <p class="card-category">
                            <spring:message code="news.example.two.subheader"/>
                        </p>
                    </div>
                    <div class="card-body">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
                        incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
                        exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure
                        dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.
                        Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt
                        mollit anim id est laborum.
                    </div>
                    <div class="card-footer text-muted">
                        6.3.2019
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../common/footer.jsp" %>
