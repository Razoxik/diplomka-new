<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--@elvariable id="userModel" type="cz.upce.diplomovaprace.model.UserModel"--%>
<%--@elvariable id="userRatingModels" type="java.util.List<cz.upce.diplomovaprace.model.UserRatingModel>"--%>
<%--@elvariable id="userRatingModel" type="cz.upce.diplomovaprace.model.UserRatingModel"--%>
<%--@elvariable id="isOwnerOfProfile" type="java.lang.Boolean"--%>
<%--@elvariable id="canBeAddedToFriends" type="java.lang.Boolean"--%>

<%@ include file="../common/header.jsp" %>
<div class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header card-header-primary">
                        <h4 class="card-title"><spring:message code="profile.form.header"/></h4>
                        <p class="card-category"><spring:message code="profile.form.subheader"/></p>
                    </div>
                    <div class="card-body">
                        <spring:url value="updateProfile" var="updateProfileUrl">
                            <spring:param name="userId" value="${userModel.userId}"/>
                        </spring:url>
                        <form:form method="POST" action="${updateProfileUrl}" modelAttribute="userModel"><%----%>
                            <div class="row">
                                <div class="col-md-2">
                                    <div class="form-group">
                                        <label class="bmd-label-floating">
                                            <spring:message code="profile.form.userId"/>
                                        </label>
                                        <form:input path="userId" type="text" cssClass="form-control"
                                                    value="${userModel.userId}" disabled="true"/>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label class="bmd-label-floating">
                                            <spring:message code="profile.form.username"/>
                                        </label>
                                        <form:input path="userName" type="text" cssClass="form-control" required="true"
                                                    value="${userModel.userName}" disabled="${!isOwnerOfProfile}"/>
                                    </div>
                                </div>

                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label class="bmd-label-floating">
                                            <spring:message code="profile.form.firstName"/>
                                        </label>
                                        <form:input path="firstName" type="text" cssClass="form-control"
                                                    value="${userModel.firstName}" disabled="${!isOwnerOfProfile}"/>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label class="bmd-label-floating">
                                            <spring:message code="profile.form.lastName"/>
                                        </label>
                                        <form:input path="lastName" type="text" cssClass="form-control"
                                                    value="${userModel.lastName}" disabled="${!isOwnerOfProfile}"/>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label class="bmd-label-floating">
                                            <spring:message code="profile.form.email"/>
                                        </label>
                                        <form:input path="email" type="email" cssClass="form-control" required="true"
                                                    value="${userModel.email}" disabled="${!isOwnerOfProfile}" />
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label class="bmd-label-floating">
                                            <spring:message code="profile.form.created"/>
                                        </label>
                                        <form:input path="created" type="text" cssClass="form-control"
                                                    value="${userModel.created}" disabled="true"/>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label class="bmd-label-floating">
                                            <spring:message code="profile.form.lastLogin"/>
                                        </label>
                                        <form:input path="lastLogin" type="text" cssClass="form-control"
                                                    value="${userModel.lastLogin}" disabled="true"/>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label><spring:message code="profile.form.aboutMe"/></label>
                                        <div class="form-group">
                                            <label class="bmd-label-floating">
                                                <spring:message code="profile.form.subAboutMe"/>
                                            </label>
                                            <form:textarea path="aboutMe" type="textarea" rows="5"
                                                           cssClass="form-control"
                                                           value="${userModel.aboutMe}"
                                                           disabled="${!isOwnerOfProfile}"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <c:if test="${isOwnerOfProfile}">
                                <button type="submit" class="btn btn-primary pull-right">
                                    <spring:message code="global.confirm"/>
                                </button>
                            </c:if>
                        </form:form>
                        <c:if test="${!isOwnerOfProfile}">
                            <spring:url value="/message/create" var="createMessageUrl">
                                <spring:param name="userName" value="${userModel.userName}"/>
                            </spring:url>
                            <a href="${createMessageUrl}" class="btn btn-primary btn-sm" role="button"
                               aria-disabled="true"><spring:message code="global.sendMessage"/></a>
                        </c:if>
                        <c:if test="${canBeAddedToFriends}">
                            <spring:url value="/friend/addToFriends" var="addToFriendsUrl">
                                <spring:param name="userId" value="${userModel.userId}"/>
                            </spring:url>
                            <a href="${addToFriendsUrl}" class="btn btn-primary btn-sm" role="button"
                               aria-disabled="true"><spring:message code="global.addToFriends"/></a>
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card card-profile">
                    <div class="card-avatar">
                        <a href="#">
                            <img class="img" src="/img/avatars/default.png"/>
                        </a>
                    </div>
                    <div class="card-body">
                        <h6 class="card-category">${userModel.firstName} ${userModel.lastName}</h6>
                        <h4 class="card-title">${userModel.userName}</h4>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table">
                                    <thead class="text-primary">
                                    <tr>
                                        <th>
                                            <spring:message code="global.game"/>
                                        </th>
                                        <th>
                                            <spring:message code="global.rating"/>
                                        </th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${userRatingModels}" var="userRatingModel" varStatus="status">
                                        <tr>
                                            <td>
                                                <spring:message code="global.game.${userRatingModel.game}"/>
                                            </td>
                                            <td>
                                                    ${userRatingModel.rating}
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <spring:url value="/history/list" var="historyUrl">
                            <spring:param name="userId" value="${userRatingModels.get(0).userId}"/>
                        </spring:url>
                        <a href="${historyUrl}" class="btn btn-primary btn-round">
                            <spring:message code="profile.showPlayerHistory"/>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../common/footer.jsp" %>
