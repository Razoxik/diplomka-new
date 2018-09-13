<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="challenge" type="cz.upce.diplomovaprace.entity.Challenge"--%>
<%--@elvariable id="activities" type="java.util.List<ccz.upce.diplomovaprace.entity.Challenge>"--%>

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
                                <h4 class="card-title ">Simple Table</h4>
                                <p class="card-category"> Here is a subtitle for this table</p>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table">
                                        <thead class=" text-primary">
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
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${challenges}" var="challenge" varStatus="status">
                                            <tr>
                                                <td>
                                                        ${challenge.challengeStart}
                                                </td>
                                                <td>
                                                        ${challenge.challengeEnd} </td>
                                                <td>
                                                        ${challenge.coordsLat} </td>
                                                <td>
                                                        ${challenge.coordsLng} </td>
                                                <td class="text-primary">
                                                    $36,738
                                                </td>
                                            </tr>
                                        </c:forEach>

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12">
                        <div class="card card-plain">
                            <div class="card-header card-header-primary">
                                <h4 class="card-title mt-0"> Table on Plain Background</h4>
                                <p class="card-category"> Here is a subtitle for this table</p>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-hover">
                                        <thead class="">
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
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td>
                                                1
                                            </td>
                                            <td>
                                                Dakota Rice
                                            </td>
                                            <td>
                                                Niger
                                            </td>
                                            <td>
                                                Oud-Turnhout
                                            </td>
                                            <td>
                                                $36,738
                                            </td>
                                        </tr>
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
<%--<jsp:include page="fragments/filterOnDaRightSide.jsp"/>--%>
</body>
<jsp:include page="fragments/jsCommon.jsp"/>

</html>