<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
  <!-- head -->
    <c:import url="includes/head.jsp" />
   <!-- end head -->
<body>
    <!-- ======= Header ======= -->
    <c:import url="includes/header.jsp" />
    <!-- End Header -->

  <main id="main">
    <div id="hero" class="hero route bg-image" style="background-image: url(assets/img/hero-bg.jpg)">
        <div class="overlay-itro"></div>
        <div class="hero-content display-table">
            <div class="table-cell">
                <div class="container">
                    <div class="cajafuera" align="center">
                        <div class="formulariocaja">
                            <div class="formtitulo">Perfil de ${sessionScope.userLogueado.nombre}</div>
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

<!-- ======= Footer ======= -->
<c:import url="includes/footer.jsp" />
<!-- End Footer -->

  <!-- Template Main JS File -->
  <script src="assets/js/main.js"></script>
</body>
</html>