<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--@elvariable id="challenge" type="cz.upce.diplomovaprace.entity.Challenge"--%>
<%--@elvariable id="activities" type="java.util.List<ccz.upce.diplomovaprace.entity.Challenge>"--%>

<jsp:include page="fragments/header.jsp"/>

<body class="dark-edition">
<div class="wrapper ">
    <jsp:include page="fragments/sidebar.jsp"/>


    <div class="main-panel">
        <jsp:include page="fragments/navbar.jsp"/>
        <div id="map"></div>
    </div>

</div>
<%--<jsp:include page="fragments/filterOnDaRightSide.jsp"/> --%>
<jsp:include page="fragments/jsCommon.jsp"/>
<script>
    var challenges = [
        <c:forEach items="${activities}" var="challenge" varStatus="status">
        [
            '${challenge.challengeStart}', // Name - [i][0]
            '${challenge.challengeEnd}', // Name - [i][0]
            '${challenge.coordsLat}', // Name - [i][0]
            '${challenge.coordsLng}', // Name - [i][0]
            'asd' // Name - [i][0]

        ]
        <c:if test="${!status.last}">
        ,
        </c:if>
        </c:forEach>
    ];
    $(document).ready(function () {
        // Javascript method's body can be found in assets/js/demos.js
        demo.initGoogleMaps();
    });
</script>
</body>

</html>