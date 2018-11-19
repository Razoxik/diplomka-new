<%@ page contentType="text/html; charset=UTF-8" %>

<footer class="footer">
    <div class="container-fluid">
        <nav class="float-left">
            <ul>
                <li>
                    <a href="https://letsplayfolks.herokuapp.com/">
                        Let's play folks
                    </a>
                </li>
                <li>
                    <a href="https://www.tomasbartosek.cz">
                        About Us
                    </a>
                </li>
                <li>
                    <a href="https://www.tomasbartosek.cz">
                        Blog
                    </a>
                </li>
                <li>
                    <a href="https://github.com/TomasBartosek">
                        GitHub
                    </a>
                </li>
            </ul>
        </nav>
        <div class="copyright float-right" id="date">
            , made with <i class="material-icons">favorite</i> by
            <a href="https://www.tomasbartosek.cz" target="_blank">Tomáš Bartošek</a>
        </div>
    </div>
</footer>
<script>
    const x = new Date().getFullYear();
    let date = document.getElementById('date');
    date.innerHTML = '&copy; ' + x + date.innerHTML;
</script>