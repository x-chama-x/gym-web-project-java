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

  <main id="main" role="main">
    <!-- ======= Hero Section ======= -->
    <section id="hero" class="hero route bg-image" style="background-image: url(assets/img/hero-bg.jpg)">
      <div class="overlay-itro"></div>
      <div class="hero-content display-table">
        <div class="table-cell">
          <div class="container">
            <div class="cajafuera" align="center">
              <div class="formulariocaja">
                <div class="formtitulo">Mis Entrenamientos</div>
                <p class="color-text-a">Aquí puedes ver todos tus entrenamientos cargados y acceder a los detalles de cada uno.</p>

                <div class="profile-usermenu">
                  <ul>
                    <c:forEach var="entrenamiento" items="${entrenamientos}">
                      <li><a href="mostrarEntrenamiento?entrenamientoId=${entrenamiento.entrenamientoID}"><i class="bi bi-caret-right"></i> ${entrenamiento.nombre}</a></li>
                    </c:forEach>
                  </ul>
                  <ul>
                    <li style="list-style-type: none;"><a href="redirigirPerfil"><i class="bi bi-box-arrow-right"></i> volver al menú de perfil</a></li>
                    <c:import url="includes/cerrarSesion.jsp" />
                  </ul>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </main>

  <main id="main">
    <!-- Antes del footer -->
    <div class="divider"></div>
  </main>

  <!-- ======= Footer ======= -->
  <c:import url="includes/footer.jsp" />
  <!-- End Footer -->

  <!-- Template Main JS File -->
  <script src="assets/js/main.js"></script>
</body>
</html>