<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--@elvariable id="challenge" type="cz.upce.diplomovaprace.entity.Challenge"--%>
<%--@elvariable id="challenges" type="java.util.List<ccz.upce.diplomovaprace.entity.Challenge>"--%>

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
                                <h4 class="card-title ">Mailbox</h4>
                                <p class="card-category">Here are your messages</p>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table">
                                        <thead class=" text-primary">
                                        <tr>
                                            <th>
                                                Author
                                            </th>
                                            <th>
                                                Subject
                                            </th>
                                            <th>
                                                Text
                                            </th>
                                            <th>
                                                Date
                                            </th>
                                            <th>
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <%--<c:forEach items="${challenges}" var="challenge" varStatus="status"> --%>
                                        <tr>
                                            <td>
                                                Razox
                                            </td>
                                            <td>
                                                Pozdrav
                                            <td>
                                                Text zprávy kterej v dtočku musíš omezit max na 30 znaků třeba..
                                            </td>
                                            <td class="text-primary">
                                                10. 4. 2018
                                            </td>
                                            <spring:url value="detail" var="detailUrl" htmlEscape="true">
                                                <spring:param name="messageId" value="1"/>
                                            </spring:url>
                                            <td class=>
                                                <a href="${detailUrl}">Zobrazit detail</a>
                                            </td>
                                        </tr>
                                        <%--</c:forEach>--%>

                                        </tbody>
                                    </table>
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