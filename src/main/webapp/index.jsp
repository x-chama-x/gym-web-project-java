<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>GYMWEB - Login</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
    <link href="assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">
    <link href="assets/css/style.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Kode+Mono&display=swap" rel="stylesheet">
</head>
<body>
    <header id="header" class="fixed-top">
        <div class="container d-flex align-items-center justify-content-between">
            <h1 class="logo"><a href="index.jsp">GYMWEB</a></h1>
            <nav id="navbar" class="navbar">
                <ul>
                    <li><a class="nav-link scrollto" href="#hero">Home</a></li>
                    <li><a class="nav-link scrollto" href="#about">Acerca de</a></li>
                </ul>
                <i class="bi bi-list mobile-nav-toggle"></i>
            </nav>
        </div>
    </header>

    <div id="hero" class="hero route bg-image" style="background-image: url(assets/img/hero-bg.jpg)">
        <div class="overlay-itro"></div>
        <div class="hero-content display-table">
            <div class="table-cell">
                <div class="container">
                    <div class="cajafuera" align="center">
                        <div class="formulariocaja">
                            <form action="login" method="post" name="vaidrollteam">
                                <div class="formtitulo">Iniciar sesión</div>
                                <c:if test="${not empty errorMessage and errorMessage != null}">
                                    <div class="alert" role="alert">
                                        ${errorMessage}
                                    </div>
                                </c:if>
                                <img src="assets/img/apple-touch-icon.png"/>
                                <input type="text" name="username" placeholder="&#128273; Ingresar usuario" class="cajaentradatexto" required>
                                <input type="password" name="password" placeholder="&#128274; Ingresar password" class="cajaentradatexto" required>
                                <div align="right" class="af"><a href="#">Recuperar contraseña</a></div>
                                <input type="submit" value="Iniciar sesión" class="botonenviar">
                                <div>¿Necesitas una cuenta? <a href="registro.html">Registrar</a></div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <main id="main">
        <div class="divider"></div>
    </main>

    <footer id="footer">
        <div class="container">
            <div class="copyright">
                &copy; GYMWEB <strong><span>Aplicacion de entrenamiento</span></strong>. All Rights Reserved
            </div>
            <div class="credits">
                Designed by <a href="https://x-chama-x.github.io/franciscochiminelli.com/">x_chama_x</a>
            </div>
        </div>
    </footer>

    <script src="assets/js/main.js"></script>
    <script>
        document.getElementById('rolSelect').addEventListener('change', function() {
            this.style.background = 'transparent';
        });

        document.getElementById('rolSelect').addEventListener('focus', function() {
            this.style.background = '#333';
        });

        document.getElementById('rolSelect').addEventListener('blur', function() {
            if (this.value) {
                this.style.background = 'transparent';
            } else {
                this.style.background = 'transparent';
            }
        });
    </script>
</body>
</html>