<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--@elvariable id="challenge" type="cz.upce.diplomovaprace.entity.Challenge"--%>
<%--@elvariable id="challenges" type="java.util.List<ccz.upce.diplomovaprace.entity.Challenge>"--%>

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

                    <form>
                   
                        <div class="form-group">
                            <label for="exampleInputEmail1">Players nickname</label>
                            <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" >
                        </div>

                        <div class="form-group">
                            <label for="exampleFormControlTextarea1">Message..</label>
                            <textarea class="form-control" id="exampleFormControlTextarea1" rows="5"></textarea>
                        </div>

                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form>
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