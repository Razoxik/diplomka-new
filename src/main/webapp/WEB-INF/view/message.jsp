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
                                         <c:forEach items="${messages}" var="message" varStatus="status">
                                        <tr>
                                            <td>
                                                    ${message.userByFromUserId.username}
                                            </td>
                                            <td>
                                                    ${message.subject}

                                            <td >
                                            <c:choose>
                                                <c:when test = "${message.text.length() gt 100}">
                                                    ${message.text.substring(0,100)}.....
                                                </c:when>
                                                <c:otherwise>
                                                    ${message.text}
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                            <td class="text-primary">
                                                10. 4. 2018
                                            </td>
                                            <spring:url value="detail" var="detailUrl" htmlEscape="true">
                                                <spring:param name="messageId" value="${message.messageId}"/>
                                            </spring:url>
                                            <td class=>
                                                <a href="${detailUrl}">Zobrazit detail</a>
                                            </td>
                                        </tr>
                                        </c:forEach>

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