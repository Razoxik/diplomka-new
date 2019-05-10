<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--@elvariable id="_csrf" type="org.springframework.security.web.csrf.CsrfToken"--%>

<nav class="navbar navbar-expand-lg navbar-transparent navbar-absolute fixed-top" id="navigation-example">
    <div class="container-fluid">
        <div class="navbar-wrapper">
            <a class="navbar-brand" href="#">
                <spring:message code="global.activeTab.${activeTab}"/>
            </a>
        </div>
        <button class="navbar-toggler" type="button" data-toggle="collapse" aria-controls="navigation-index"
                aria-expanded="false" aria-label="Toggle navigation" data-target="#navigation-example">
            <span class="sr-only">Toggle navigation</span>
            <span class="navbar-toggler-icon icon-bar"></span>
            <span class="navbar-toggler-icon icon-bar"></span>
            <span class="navbar-toggler-icon icon-bar"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-end">
            <ul class="navbar-nav">
                <li class="nav-item dropdown">
                    <a class="nav-link" href="#" id="navbarDropdownMenuLinkLanguage"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="material-icons">flag</i>
                        <p class="d-lg-none d-md-block">
                            Language
                        </p>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink">
                        <a id="langEn" style="cursor: pointer" class="dropdown-item">
                            <spring:message code="navbar.language.english"/>
                        </a>
                        <a id="langCz" style="cursor: pointer" class="dropdown-item">
                            <spring:message code="navbar.language.czech"/>
                        </a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link" href="#" id="navbarDropdownMenuLogout" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">
                        <i class="material-icons">person</i>
                        <p class="d-lg-none d-md-block">
                            Account
                        </p>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink">
                        <sec:authorize access="isAnonymous()">
                            <a class="dropdown-item" href="<c:url value="/login"/>">
                                <spring:message code="navbar.login"/>
                            </a>
                        </sec:authorize>
                        <sec:authorize access="isAuthenticated()">
                            <form id="logoutForm" name='logoutForm' action="<c:url value='/logout'/>" method='POST'>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            </form>
                            <a class="dropdown-item" href="javascript:{}"
                               onclick="document.getElementById('logoutForm').submit();">
                                <spring:message code="navbar.logout"/>
                            </a>
                        </sec:authorize>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</nav>
<script type="text/javascript">
    /**
     * @return {string} url param
     */
    function URL_add_parameter(url, param, value) {
        let hash = {};
        let parser = document.createElement('a');

        parser.href = url;

        let parameters = parser.search.split(/[?&]/);

        for (let i = 0; i < parameters.length; i++) {
            if (!parameters[i])
                continue;

            let ary = parameters[i].split('=');
            hash[ary[0]] = ary[1];
        }

        hash[param] = value;

        let list = [];
        Object.keys(hash).forEach(function (key) {
            list.push(key + '=' + hash[key]);
        });

        parser.search = '?' + list.join('&');
        return parser.href;
    }

    document.getElementById("langEn").onclick = function () {
        location.href = URL_add_parameter(location.href, 'lang', 'en');
    };
    document.getElementById("langCz").onclick = function () {
        location.href = URL_add_parameter(location.href, 'lang', 'cz');
    };
</script>
