<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--@elvariable id="fromLogout" type="java.lang.Boolean"--%>
<%--@elvariable id="fromLogin" type="java.lang.Boolean"--%>
<%--@elvariable id="lang" type="java.lang.String"--%>

<%@ include file="../common/header.jsp" %>
<div class="content">
    <div class="container-fluid">
        <c:if test="${fromLogout}">
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                <strong>Odhlášení</strong> proběhlo úspěšně.
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </c:if>
        <c:if test="${fromLogin}">
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                <strong>Příhlášení</strong> proběhlo úspěšně.
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </c:if>
        <c:if test="${not empty lang}">
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                Jazyk úspěšně změnen
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </c:if>
        <div class="row">
            <div class="col-md-12">
                <div class="card  ">
                    <div class="card-header card-header-primary">
                        <h4 class="card-title ">Změna pravidel </h4>
                        <p class="card-category">Zakázáno hrát kopanou v dešti</p>
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
                        12.9.2018
                    </div>
                </div>

                <div class="card  ">
                    <div class="card-header card-header-primary">
                        <h4 class="card-title "> Přidána nová hra - Dáma</h4>
                        <p class="card-category">Hra nejen pro dámy, ale i pány</p>
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
                        6.3.2018
                    </div>
                </div>

            </div>
        </div>
    </div>
    <%@ include file="../common/footer.jsp" %>
