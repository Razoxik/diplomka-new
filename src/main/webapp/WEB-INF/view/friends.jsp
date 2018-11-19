<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--@elvariable id="friend" type="cz.upce.diplomovaprace.entity.Friend"--%>
<%--@elvariable id="friends" type="java.util.List<ccz.upce.diplomovaprace.entity.Friend>"--%>

<%@ include file="common/header.jsp" %>

<div class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header card-header-primary">
                        <h4 class="card-title ">Přátelé</h4>
                        <p class="card-category">Seznam vašich přátel</p>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table">
                                <thead class=" text-primary">
                                <tr>
                                    <th>
                                        Id
                                    </th>
                                    <th>
                                        Username
                                    </th>
                                    <th>
                                        Last login
                                    </th>
                                    <th>
                                        Přátelé od
                                    </th>
                                    <th>
                                    </th>
                                    <th>
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${friends}" var="friend" varStatus="status">
                                    <tr>
                                        <td>
                                                ${friend.id}
                                        </td>
                                        <td>
                                                ${friend.userByToUserId.userName}
                                        <td>
                                                ${friend.userByToUserId.lastLogin}
                                        </td>
                                        <td class="text-primary">
                                                ${friend.created}
                                        </td>
                                        <spring:url value="detail" var="detailUrl" htmlEscape="true">
                                            <spring:param name="messageId" value="${message.id}"/>
                                        </spring:url>
                                        <td class=>
                                            <a href="${detailUrl}">Zobrazit detail</a>
                                        </td>
                                        <spring:url value="message/create" var="messageUrl" htmlEscape="true">
                                        </spring:url>
                                        <td class=>
                                            <a href="reply?messageId=1" class="btn  btn-primary btn-sm "
                                               role="button"
                                               aria-disabled="true">Poslat zprávu</a>
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
<%@ include file="common/footer.jsp" %>
