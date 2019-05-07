<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="challenge" type="com.bartosektom.letsplayfolks.entity.Challenge"--%>
<%--@elvariable id="challenges" type="java.util.List<com.bartosektom.letsplayfolks.entity.Challenge>"--%>

<%@ include file="../common/header.jsp" %>

<div class="content">
    <div class="container-fluid">
        <div class="row">

            <div class="col-md-12">
                <div class="card">
                    <div class="card-header card-header-primary">
                        <h4 class="card-title">Games with bad score</h4>
                        <p class="card-category">Please fix it my lovely operator</p>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table">
                                <thead class=" text-primary">
                                <tr>
                                    <th>
                                        ID
                                    </th>
                                    <th>
                                        Name
                                    </th>
                                    <th>
                                        Country
                                    </th>
                                    <th>
                                        City
                                    </th>
                                    <th>
                                        Salary
                                    </th>
                                    <th>
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${challenges}" var="challenge" varStatus="status">
                                    <tr>
                                        <td>
                                                ${challenge.start}
                                        </td>
                                        <td>
                                                ${challenge.end} </td>
                                        <td>
                                                ${challenge.coordsLat} </td>
                                        <td>
                                                ${challenge.coordsLng} </td>
                                        <td class="text-primary">
                                            $36,738
                                        </td>
                                        <td class=>
                                            <spring:url value="/challenge/detail" var="challengeDetailUrl">
                                                <spring:param name="challengeId" value="${challenge.id}"/>
                                            </spring:url>
                                            <a href=" ${ challengeDetailUrl }">Zobrazit detail</a>
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
