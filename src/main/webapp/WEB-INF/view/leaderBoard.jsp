<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--@elvariable id="challenge" type="cz.upce.diplomovaprace.entity.Challenge"--%>
<%--@elvariable id="challenges" type="java.util.List<ccz.upce.diplomovaprace.entity.Challenge>"--%>
<%--@elvariable id="challengeModel" type="cz.upce.diplomovaprace.model.ChallengeModel"--%>

<%@ include file="common/header.jsp" %>

        <div class="content">
            <div class="container-fluid">

                <div class="row">
                    <div class="col-md-12">
                        <div class="form-group">
                            <label for="exampleFormControlSelect1">Game</label>
                        <form:form method="POST" action="create" modelAttribute="challengeModel"><%----%>
                            <form:select path="gameId" cssClass="form-control selectpicker" id="exampleFormControlSelect1">
                                <c:forEach items="${games}" var="game" varStatus="status">
                                    <form:option value="${status.current}" cssStyle="color:black">${game.gameName}</form:option>
                                </c:forEach>
                            </form:select>
                        </form:form>
                        </div>
                        <div class="card">
                            <div class="card-header card-header-primary">
                                <h4 class="card-title ">Leader board</h4>
                                <p class="card-category">...for football game!</p>

                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table">
                                        <thead class=" text-primary">
                                        <tr>
                                            <th>
                                                User
                                            </th>
                                            <th>
                                                Games
                                            </th>
                                            <th>
                                                Wins
                                            </th>
                                            <th>
                                                Loses
                                            </th>
                                            <th>
                                                W/R ratio
                                            </th>
                                            <th>
                                                Rating
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
                                                158
                                            <td> 75
                                            </td>
                                            <td> 42
                                            </td>
                                            <td>
                                                62%
                                            </td>
                                            <td class="text-primary">
                                                1547
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
<%@ include file="common/footer.jsp" %>
