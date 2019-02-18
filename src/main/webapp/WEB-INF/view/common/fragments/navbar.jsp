<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--@elvariable id="_csrf" type="org.springframework.security.web.csrf.CsrfToken"--%>

<nav class="navbar navbar-expand-lg navbar-transparent navbar-absolute fixed-top " id="navigation-example">
    <div class="container-fluid">
        <div class="navbar-wrapper">
            <a class="navbar-brand" href="#">${activeTab}</a>
        </div>

        <button class="navbar-toggler" type="button" data-toggle="collapse" aria-controls="navigation-index"
                aria-expanded="false" aria-label="Toggle navigation" data-target="#navigation-example">
            <span class="sr-only">Toggle navigation</span>
            <span class="navbar-toggler-icon icon-bar"></span>
            <span class="navbar-toggler-icon icon-bar"></span>
            <span class="navbar-toggler-icon icon-bar"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-end">
            <%--
            <form class="navbar-form">
                <div class="input-group no-border">
                    <input type="text" value="" class="form-control" placeholder="Search...">
                    <button type="submit" class="btn btn-default btn-round btn-just-icon">
                        <i class="material-icons">search</i>
                        <div class="ripple-container"></div>
                    </button>
                </div>
            </form>
            --%>

            <ul class="navbar-nav">
                <%--
                    <li class="nav-item">
                        <a class="nav-link" href="javascript:void(0)">
                            <i class="material-icons">dashboard</i>
                            <p class="d-lg-none d-md-block">
                                Stats
                            </p>
                        </a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link" href="javscript:void(0)" id="navbarDropdownMenuLink"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="material-icons">notifications</i>
                            <span class="notification">5</span>
                            <p class="d-lg-none d-md-block">
                                Some Actions
                            </p>
                        </a>
                        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink">
                            <a class="dropdown-item" href="javascript:void(0)">Mike John responded to your email</a>
                            <a class="dropdown-item" href="javascript:void(0)">You have 5 new tasks</a>
                            <a class="dropdown-item" href="javascript:void(0)">You're now friend with Andrew</a>
                            <a class="dropdown-item" href="javascript:void(0)">Another Notification</a>
                            <a class="dropdown-item" href="javascript:void(0)">Another One</a>
                        </div>
                    </li>
                --%>

                <li class="nav-item dropdown">
                    <a class="nav-link" href="#" id="navbarDropdownMenuLinkLanguage"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="material-icons">flag</i>
                        <p class="d-lg-none d-md-block">
                            Language
                        </p>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink">
                        <a id="langEn" style="cursor: pointer" class="dropdown-item">English</a>
                        <a id="langCz" style="cursor: pointer" class="dropdown-item">Czech</a>
                    </div>
                    <script type="text/javascript">
                        /**
                         * @return {string}
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
                            <a class="dropdown-item" href="<c:url value="/login"/>">Login</a>
                        </sec:authorize>
                        <sec:authorize access="isAuthenticated()">
                            <form id="logoutForm" name='logoutForm' action="<c:url value='/logout'/>" method='POST'>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            </form>
                            <a class="dropdown-item" href="javascript:{}"
                               onclick="document.getElementById('logoutForm').submit();">Logout
                            </a>
                        </sec:authorize>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</nav>
