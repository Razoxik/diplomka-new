<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="game" type="cz.upce.diplomovaprace.entity.Game"--%>
<%--@elvariable id="games" type="java.util.List<ccz.upce.diplomovaprace.entity.Game>"--%>

<%@ include file="../common/header.jsp" %>

<div class="content">
    <div class="container-fluid">
        <div class="row">

            <div class="col-md-12">
                <div class="card  ">
                    <div class="card-header card-header-primary">
                        <h4 class="card-title ">Simple Table</h4>
                        <p class="card-category"> Here is a subtitle for this table</p>
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
                                <c:forEach items="${games}" var="game" varStatus="status">
                                    <tr>
                                        <td>
                                                ${game.name}
                                        </td>
                                        <td>
                                                ${game.id} </td>
                                        <td>
                                                ${game.created} </td>
                                        <td>
                                                ${game.description} </td>
                                        <td class="text-primary">
                                            $36,738
                                        </td>
                                        <td class=>
                                            <spring:url value="/createGame" var="createGame">
                                                <spring:param name="gameId" value="${game.id}"/>
                                            </spring:url>
                                            <a href="${createGame}">Zobrazit detail</a>
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
