<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--@elvariable id="message" type="com.bartosektom.letsplayfolks.model.MessageModel"--%>
<%--@elvariable id="messages" type="java.util.List<com.bartosektom.letsplayfolks.model.MessageModel>"--%>
<%--@elvariable id="messageSent" type="java.lang.Boolean"--%>

<%@ include file="../common/header.jsp" %>
<div class="content">
    <div class="container-fluid">
        <c:if test="${messageSent}">
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                <spring:message code="message.create.success.sent"/>
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </c:if>
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header card-header-primary">
                        <h4 class="card-title"><spring:message code="message.table.title"/></h4>
                        <p class="card-category"><spring:message code="message.table.subtitle"/></p>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table">
                                <thead class="text-primary">
                                <tr>
                                    <th>
                                        <spring:message code="message.table.header.author"/>
                                    </th>
                                    <th>
                                        <spring:message code="message.table.header.subject"/>
                                    </th>
                                    <th>
                                        <spring:message code="message.table.header.text"/>
                                    </th>
                                    <th>
                                        <spring:message code="message.table.header.date"/>
                                    </th>
                                    <th>
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${messages}" var="message" varStatus="status">
                                    <tr>
                                        <td>
                                                ${message.author}
                                        </td>
                                        <td>
                                                ${message.subject}
                                        <td>
                                            <c:choose>
                                                <c:when test="${message.text.length() gt 100}">
                                                    ${message.text.substring(0,100)}.....
                                                </c:when>
                                                <c:otherwise>
                                                    ${message.text}
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td class="text-primary">
                                                ${message.formattedSentDate}
                                        </td>
                                        <spring:url value="detail" var="detailUrl">
                                            <spring:param name="messageId" value="${message.id}"/>
                                        </spring:url>
                                        <td class=>
                                            <a href="${detailUrl}">
                                                <spring:message code="message.list.detailUrl"/>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <spring:url value="create" var="createUrl"/>
                        <a href="${createUrl}" class="btn btn-primary" role="button" aria-disabled="true">
                            <spring:message code="message.list.create"/>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../common/footer.jsp" %>
