<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--@elvariable id="friendModel" type="com.bartosektom.letsplayfolks.model.FriendModel"--%>
<%--@elvariable id="friendModels" type="java.util.List<cz.upce.diplomovaprace.model.FriendModel>"--%>

<%@ include file="../common/header.jsp" %>
<div class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header card-header-primary">
                        <h4 class="card-title "><spring:message code="friend.card.title"/></h4>
                        <p class="card-category"><spring:message code="friend.card.subtitle"/></p>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table">
                                <thead class="text-primary">
                                <tr>
                                    <th>
                                        <spring:message code="friend.table.header.username"/>
                                    </th>
                                    <th>
                                        <spring:message code="friend.table.header.firstname"/>
                                    </th>
                                    <th>
                                        <spring:message code="friend.table.header.lastname"/>
                                    </th>
                                    <th>
                                        <spring:message code="friend.table.header.friendsFrom"/>
                                    </th>
                                    <th>
                                        <spring:message code="friend.table.header.lastLogin"/>
                                    </th>
                                    <th>
                                    </th>
                                    <th>
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${friendModels}" var="friendModel" varStatus="status">
                                    <tr>
                                        <td>
                                                ${friendModel.userName}
                                        </td>
                                        <td>
                                                ${friendModel.firstName}
                                        </td>
                                        <td>
                                                ${friendModel.lastName}
                                        </td>
                                        <td>
                                                ${friendModel.friendsFrom}
                                        </td>
                                        <td class="text-primary">
                                                ${friendModel.lastLogin}
                                        </td>
                                        <spring:url value="/user/detail" var="userDetailUrl">
                                            <spring:param name="userId" value="${friendModel.userId}"/>
                                        </spring:url>
                                        <td>
                                            <a href="${userDetailUrl}"><spring:message code="global.detailLink"/></a>
                                        </td>
                                        <spring:url value="/message/create" var="createMessageUrl">
                                            <spring:param name="userName" value="${friendModel.userName}"/>
                                        </spring:url>
                                        <td>
                                            <a href="${createMessageUrl}" class="btn btn-primary btn-sm" role="button"
                                               aria-disabled="true"><spring:message code="global.sendMessage"/></a>
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
<%@ include file="../common/footer.jsp" %>
