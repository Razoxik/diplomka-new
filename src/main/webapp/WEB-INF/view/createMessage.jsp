<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--@elvariable id="message" type="cz.upce.diplomovaprace.entity.Message"--%>
<%--@elvariable id="messageModel" type="cz.upce.diplomovaprace.model.MessageModel"--%>

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
<!--https://stackoverflow.com/questions/5272433/html5-form-required-attribute-set-custom-validation-message -->
                        <form:form method="POST" action="send" modelAttribute="messageModel"><%----%>
                            <div class="form-group">
                                <label for="playerNickname">Players nickname</label>
                                <form:input path="nickname" type="text" id="playerNickname" cssClass="form-control"
                                            placeholder="nickname"
                                            value="${message.userByFromUserId.userName}"
                                            aria-describedby="playerNickname" required="true"
                                            oninvalid="this.setCustomValidity('Username cannot be empty.')"
                                            onchange="this.setCustomValidity('')" />
                            </div>
                            <div class="form-group">
                                <label for="subject">Subject</label>
                                <form:input path="subject" type="text" id="subject" cssClass="form-control"
                                            placeholder="subject placeholder"
                                            aria-describedby="subject" required="true"
                                            oninvalid="this.setCustomValidity('Subject cannot be empty.')"
                                            onchange="this.setCustomValidity('')" />
                            </div>
                            <div class="form-group">
                                <label for="messageText">Message..</label>
                                <form:textarea path="text" type="text" id="messageText" cssClass="form-control"
                                            placeholder="messageText placeholder"
                                            aria-describedby="messageText" rows="5"/>
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