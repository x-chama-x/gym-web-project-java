<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <title>Perfil de Usuario - GYMWEB</title>
  <meta content="" name="description">
  <meta content="" name="keywords">

  <!-- Favicons -->
  <link href="assets/img/favicon.png" rel="icon">
  <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Vendor CSS Files -->
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
  <link href="assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="assets/css/style.css" rel="stylesheet">

  <!-- Importar tipografía Montserrat desde Google Fonts -->
  <link href="https://fonts.googleapis.com/css2?family=Kode+Mono&display=swap" rel="stylesheet">
</head>

<body>

  <!-- ======= Header ======= -->
  <header id="header" class="fixed-top">
    <div class="container d-flex align-items-center justify-content-between">

      <h1 class="logo"><a href="principal.jsp">GYMWEB</a></h1>
      <!-- Uncomment below if you prefer to use an image logo -->
      <!-- <a href="index.html" class="logo"><img src="assets/img/logo.png" alt="" class="img-fluid"></a>-->

      <nav id="navbar" class="navbar">
        <ul>
          <li><a class="nav-link scrollto" href="principal.jsp">Home</a></li>
          <li><a class="nav-link scrollto" href="principal.jsp">Acerca de</a></li>
        </ul>
        <i class="bi bi-list mobile-nav-toggle"></i>
      </nav><!-- .navbar -->
    </div>
  </header><!-- End Header -->

  <main id="main">
    <div id="hero" class="hero route bg-image" style="background-image: url(assets/img/hero-bg.jpg)">
        <div class="overlay-itro"></div>
        <div class="hero-content display-table">
            <div class="table-cell">
                <div class="container">
                    <div class="cajafuera" align="center">
                        <div class="formulariocaja">
                            <div class="formtitulo">Perfil de Usuario</div>
                            <div class="profile-usermenu">
                                <ul>
                                    <li>
                                        <a href="wikiEjercicios"><i class="bi bi-card-list"></i> wiki de Ejercicios</a>
                                        <p>Encuentra una variedad de ejercicios para mejorar tu entrenamiento.</p>
                                    </li>
                                    <li>
                                        <a href="Entrenamientos.html"><i class="bi bi-calendar"></i> Entrenamientos</a>
                                        <p>Accede a tu calendario de entrenamientos personalizados.</p>
                                    </li>
                                    <li>
                                        <a href="#"><i class="bi bi-graph-up"></i> Estadísticas</a>
                                        <p>Revisa tus estadísticas y progreso a lo largo del tiempo.</p>
                                    </li>
                                    <c:if test="${sessionScope.rolUsuario == 'ADMINISTRADOR'}">
                                        <li>
                                          <a href="#"><i class="bi bi-people"></i> Usuarios</a>
                                          <p>Ver y editar los usuarios registrados en la aplicación web.</p>
                                        </li>
                                    </c:if>
                                    <li>
                                        <a href="logout"><i class="bi bi-box-arrow-right"></i> Cerrar Sesión</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main><!-- End #main -->

  <main id="main">
    <!-- Antes del footer -->
    <div class="divider"></div>
  </main><!-- End #main -->

  <!-- ======= Footer ======= -->
  <footer id="footer">
    <div class="container">
      <div class="copyright">
        &copy; GYMWEB <strong><span>Aplicacion de entrenamiento</span></strong>. All Rights Reserved
      </div>
      <div class="credits">
        Designed by <a href="https://x-chama-x.github.io/franciscochiminelli.com/">x_chama_x</a>
      </div>
    </div>
  </footer><!-- End Footer -->

  <!-- Template Main JS File -->
  <script src="assets/js/main.js"></script>
</body>
</html>