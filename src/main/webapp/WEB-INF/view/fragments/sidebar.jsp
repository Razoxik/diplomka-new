<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags" %>
<div class="sidebar" data-color="purple" data-background-color="black" data-image="img/sidebar-2.jpg">
    <!--
      Tip 1: You can change the color of the sidebar using: data-color="purple | azure | green | orange | danger"

      Tip 2: you can also add an image using data-image tag
  -->
    <div class="logo">
        <a href="http://www.creative-tim.com" class="simple-text logo-normal">
            Let's play
        </a>
    </div>
    <div class="sidebar-wrapper">
        <ul class="nav">
            <li class="nav-item <c:if test="${activeTab == 'News'}">active</c:if>">
                <a class="nav-link" href="/news">
                    <i class="material-icons">dashboard</i>
                    <p>News</p>
                </a>
            </li>
            <li class="nav-item <c:if test="${activeTab == 'User'}">active</c:if>">
                <a class="nav-link" href="/user">
                    <i class="material-icons">person</i>
                    <p>User Profile</p>
                </a>
            </li>
            <li class="nav-item <c:if test="${activeTab == 'History'}">active</c:if>">
                <a class="nav-link" href="/history">
                    <i class="material-icons">content_paste</i>
                    <p>History</p>
                </a>
            </li>
            <li class="nav-item  <c:if test="${activeTab == 'Message'}">active</c:if>">
                <a class="nav-link" href="/message/list">
                    <i class="material-icons">message</i>
                    <p>Messages</p>
                </a>
            </li>
            <li class="nav-item ">
                <a class="nav-link" href="../theme/examples/icons.html">
                    <i class="material-icons">bubble_chart</i>
                    <p>Statistics</p>
                </a>
            </li>
            <li class="nav-item <c:if test="${activeTab eq 'MAP' }">active</c:if>">
                <a class="nav-link" href="/map">
                    <i class="material-icons">location_ons</i>
                    <p>Map</p>
                </a>
            </li>
            <li class="nav-item ">
                <a class="nav-link" href="../theme/examples/notifications.html">
                    <i class="material-icons">notifications</i>
                    <p>Notifications</p>
                </a>
            </li>
            <li class="nav-item ">
                <a class="nav-link" href="/leaderBoard">
                    <i class="material-icons">unarchive</i>
                    <p>Leaderboard</p>
                </a>
            </li>
        </ul>
    </div>
</div>