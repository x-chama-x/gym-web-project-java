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
    <section id="hero" class="hero route bg-image" style="background-image: url(assets/img/hero-bg.jpg)">
      <div class="overlay-itro"></div>
      <div class="hero-content display-table">
        <div class="table-cell">
          <div class="container">
            <div class="cajafuera" align="center">
              <div class="formulariocaja">
                <div class="formtitulo"> ${entrenamiento.nombre}</div>
                <div class="profile-usermenu">
                  <ul>
                    <c:forEach var="ejercicioConSeries" items="${ejerciciosConSeries}">
                      <li>
                        <div class="ejercicio">
                          <img src="assets/img/${ejercicioConSeries.ejercicio.imagen}" alt="${ejercicioConSeries.ejercicio.nombre}" class="ejercicio-thumbnail">
                          <a href="#"><span class="ejercicio-nombre">${ejercicioConSeries.ejercicio.nombre}</span></a>
                          <span class="ejercicio-detalles">${ejercicioConSeries.maxSeries} series</span>
                        </div>
                      </li>
                    </c:forEach>
                  </ul>
                  <ul>
                    <li style="list-style-type: none;"><a href=""><i class="bi bi-tools"></i> Editar rutina</a></li>
                    <li style="list-style-type: none;"><a href="#" ><i class="bi bi-trash-fill"></i> eliminar rutina</a></li>
                    <li style="list-style-type: none;"><a href="verEntrenamientos"><i class="bi bi-box-arrow-right"></i> volver al menú de entrenamientos</a></li>
                    <a href="redirigirPerfil">Volver al menú de perfil</a>
                    <br>
                    <c:import url="includes/cerrarSesion.jsp" />
                  </ul>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </main><!-- End #main -->

  <!-- ======= Footer ======= -->
  <c:import url="includes/footer.jsp" />
  <!-- End Footer -->

  <!-- Template Main JS File -->
  <script src="assets/js/main.js"></script>
</body>
</html>