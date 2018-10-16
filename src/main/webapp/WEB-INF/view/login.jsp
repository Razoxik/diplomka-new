<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--@elvariable id="message" type="cz.upce.diplomovaprace.entity.Message"--%>
<%--@elvariable id="messages" type="java.util.List<ccz.upce.diplomovaprace.entity.Message>"--%>

<jsp:include page="fragments/header.jsp"/>


<body class="dark-edition">
<div class="wrapper ">
    <jsp:include page="fragments/sidebar.jsp"/>

    <div class="main-panel">
        <jsp:include page="fragments/navbar.jsp"/>

        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header card-header-primary">
                                <h4 class="card-title ">Login Form</h4>
                                <p class="card-category">Prosím přihlačte se pro lepší požitek</p>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <form name='loginForm'
                                          action="<c:url value='/login' />" method='POST'>

                                        <div class="form-group">
                                            <label class="label-control">
                                                User name                                            </label>

                                            <input type='text' class="form-control" name='username' value="razox"/>
                                        </div>
                                        <div class="form-group">
                                            <label class="label-control">
                                                Password                                            </label>

                                            <input type='password' class="form-control" name='password'
                                                       value="razox"/>
                                        </div>


                                        <button type="submit" class="btn btn-primary">Submit</button>
                                        <input type="hidden" name="${_csrf.parameterName}"
                                               value="${_csrf.token}"/>
NEBO SE REGISTRUJTE ZDEEe XXXX
                                        <p><spring:message code="test"/></p>

                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <jsp:include page="fragments/footer.jsp"/>
    </div>
</div>
<jsp:include page="fragments/filterOnDaRightSide.jsp"/>
</body>
<jsp:include page="fragments/jsCommon.jsp"/>

</html>