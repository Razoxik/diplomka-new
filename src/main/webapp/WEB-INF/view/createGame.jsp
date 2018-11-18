<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--@elvariable id="gameModel" type="cz.upce.diplomovaprace.model.GameModel"--%>
<%--@elvariable id="games" type="List<cz.upce.diplomovaprace.entity.Game>"--%>
<%--@elvariable id="game" type="cz.upce.diplomovaprace.entity.Game"--%>

// přidat do GAME nějakou FLAG, jestli je hra potvrzena nebo ne, a pak jen vyhledat tyhle hry na schvaleni adminem
<%@ include file="common/header.jsp" %>
<div class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">

                <form:form method="POST" action="create" modelAttribute="gameModel"><%----%>
                    <!-- input with datetimepicker -->
                    <div class="form-group">
                        <label class="label-control">Název hry</label>
                        <form:input path="name" cssClass="form-control" value="${gameModel.name}"/>
                    </div>
                    <div class="form-group">
                        <label class="label-control">Počet hráčů</label>
                        <form:input path="numberOfPlayers" cssClass="form-control"
                                    value="${gameModel.numberOfPlayers}"/>
                    </div>

                    <div class="form-group">
                        <label for="exampleFormControlTextarea1">Description</label>
                        <textarea class="form-control" id="exampleFormControlTextarea1" rows="5"></textarea>
                    </div>

                    <button type="submit" class="btn btn-primary">Odeslat ke schválení</button>
                </form:form>
                <form method="POST" action="/upload" enctype="multipart/form-data">
                    <input type="file" name="file"/><br/><br/>
                    <input type="hidden" name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>
                    <input type="submit" value="Vytvořit hru"/>
                </form>
            </div>
        </div>

    </div>
</div>
<%@ include file="common/footer.jsp" %>
