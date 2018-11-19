<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--@elvariable id="messageModel" type="cz.upce.diplomovaprace.model.MessageModel"--%>

<%@ include file="../common/header.jsp" %>
<div class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header card-header-primary">
                        <h4 class="card-title"><spring:message code="message.table.detail.header"/></h4>
                        <p class="card-category"><spring:message code="message.table.detail.subheader"/></p>
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
                                <tr>
                                    <td>
                                        ${messageModel.author}
                                    </td>
                                    <td>
                                        ${messageModel.subject}
                                    <td>
                                        ${messageModel.text}
                                    </td>
                                    <td class="text-primary">
                                        ${messageModel.formattedSentDate}
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <spring:url value="create" var="createUrl">
                            <spring:param name="messageId" value="${messageModel.id}"/>
                        </spring:url>
                        <a href="${createUrl}" class="btn btn-primary" role="button" aria-disabled="true">
                            <spring:message code="message.detail.reply"/>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../common/footer.jsp" %>
