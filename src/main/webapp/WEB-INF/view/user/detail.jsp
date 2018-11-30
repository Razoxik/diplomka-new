<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--@elvariable id="userModel" type="cz.upce.diplomovaprace.model.UserModel"--%>
<%--@elvariable id="userRatingModels" type="java.util.List<cz.upce.diplomovaprace.model.UserRatingModel>"--%>
<%--@elvariable id="userRatingModel" type="cz.upce.diplomovaprace.model.UserRatingModel"--%>

<%@ include file="../common/header.jsp" %>
<div class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header card-header-primary">
                        <h4 class="card-title">Profil hráče</h4>
                        <p class="card-category">Detailní informace</p>
                    </div>
                    <div class="card-body">
                        <form>
                            <div class="row">
                                <div class="col-md-2">
                                    <div class="form-group">
                                        <label class="bmd-label-floating">user id</label>
                                        <input type="text" class="form-control" value="${userModel.userId}" >
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label class="bmd-label-floating">Username</label>
                                        <input type="text" class="form-control" value="${userModel.userName}" >
                                    </div>
                                </div>

                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label class="bmd-label-floating">Fist Name</label>
                                        <input type="text" class="form-control" value="${userModel.firstName}">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label class="bmd-label-floating">Last Name</label>
                                        <input type="text" class="form-control" value="${userModel.lastName}">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label class="bmd-label-floating">Email address</label>
                                        <input type="email" class="form-control" value="${userModel.email}">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label class="bmd-label-floating">created</label>
                                        <input type="text" class="form-control" disabled  value="${userModel.created}">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label class="bmd-label-floating">last login</label>
                                        <input type="text" class="form-control" disabled  value="${userModel.lastLogin}">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label>About Me</label>
                                        <div class="form-group">
                                            <label class="bmd-label-floating"> Lamborghini Mercy, Your chick she
                                                so thirsty, I'm in that two seat Lambo.</label>
                                            <textarea class="form-control" rows="5"></textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            Pokud ty jsi majitelem tohoto profilu tak povolit tento button - update profil
                            <button type="submit" class="btn btn-primary pull-right">Update Profile</button>
                            <div class="clearfix"></div>
                        </form>
                        <spring:url value="/message/create" var="createMessageUrl">
                            <spring:param name="userName" value="${friendModel.userName}"/>
                        </spring:url>
                        <a href="${createMessageUrl}" class="btn btn-primary btn-sm" role="button"
                           aria-disabled="true"><spring:message code="global.sendMessage"/></a>
                        <a href="${createMessageUrl}" class="btn btn-primary btn-sm" role="button"
                           aria-disabled="true">Přidat do přátel</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card card-profile">
                    <div class="card-avatar">
                        <a href="#pablo">
                            <img class="img" src="../img/faces/marc.jpg"/>
                        </a>
                    </div>
                    <div class="card-body">
                        <h6 class="card-category">Tomáš Bartošek</h6>
                        <h4 class="card-title">Username</h4>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table">
                                    <thead class="text-primary">
                                    <tr>
                                        <th>
                                            Hra
                                        </th>
                                        <th>
                                            Rating
                                        </th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${userRatingModels}" var="userRatingModel" varStatus="status">
                                        <tr>
                                            <td>
                                                    ${userRatingModel.game}
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
                        <a href="${historyUrl}" class="btn btn-primary btn-round">Zobrazit historii hráče</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../common/footer.jsp" %>
