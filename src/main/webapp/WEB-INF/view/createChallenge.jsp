<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--@elvariable id="challengeModel" type="cz.upce.diplomovaprace.model.ChallengeModel"--%>
<%--@elvariable id="games" type="List<cz.upce.diplomovaprace.entity.Game>"--%>
<%--@elvariable id="game" type="cz.upce.diplomovaprace.entity.Game"--%>

<jsp:include page="fragments/header.jsp"/>


<script>

</script>
<body class="dark-edition">

<div class="wrapper ">
    <jsp:include page="fragments/sidebar.jsp"/>

    <div class="main-panel">
        <jsp:include page="fragments/navbar.jsp"/>

        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">

                        <form:form method="POST" action="create" modelAttribute="challengeModel"><%----%>
                            <!-- input with datetimepicker -->
                            <div class="form-group">
                                <label class="label-control">Challenge start</label>
                                <input type="text" class="form-control datetimepicker" value="10/05/2016"/>
                            </div>
                            <div class="form-group">
                                <label class="label-control">Challenge end</label>
                                <input type="text" class="form-control datetimepicker" value="10/05/2016"/>
                            </div>

                            <div class="form-group">
                                <label class="label-control">Coords[Lat;Lng]</label>
                            <div class="row">
                                <div class="col">
                                    <form:input path="latCoords" cssClass="form-control" placeholder="Lat"
                                                value="${challengeModel.latCoords}"/>
                                    <form:errors path="latCoords" cssClass="error"/>
                                </div>
                                <div class="col">
                                    <form:input path="lngCoords" cssClass="form-control" placeholder="Lng"
                                                value="${challengeModel.lngCoords}"/>
                                    <form:errors path="lngCoords" cssClass="error"/>
                                </div>
                            </div>
                            </div>

                            <div class="form-group">
                                <label for="exampleFormControlSelect1">Game</label>

                                <form:select path="gameId" cssClass="form-control selectpicker" id="exampleFormControlSelect1">
                                    <c:forEach items="${games}" var="game" varStatus="status">
                                        <form:option value="${game.id}" cssStyle="color:black">${game.name}</form:option>
                                    </c:forEach>
                                </form:select>
                            </div>
                            <div class="form-group">
                                <label for="exampleFormControlTextarea1">Description</label>
                                <textarea class="form-control" id="exampleFormControlTextarea1" rows="5"></textarea>
                            </div>

                            <button type="submit" class="btn btn-primary">Submit</button>
                        </form:form>
                    </div>
                </div>

            </div>
        </div>
        <jsp:include page="fragments/footer.jsp"/>
    </div>
</div>
<jsp:include page="fragments/filterOnDaRightSide.jsp"/>
</body>
<jsp:include page="fragments/jsCommon.jsp"/>

</html>