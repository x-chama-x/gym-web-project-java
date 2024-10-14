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
        <section id="hero" class="hero route bg-image" style="background-image: url(assets/img/hero-bg.jpg)">
            <div class="overlay-itro"></div>
            <div class="hero-content display-table">
                <div class="table-cell">
                    <div class="container">
                        <div class="cajafuera" align="center">
                            <div class="formulariocaja">
                                <div class="formtitulo">Ejercicios de ${categoriaNombre}</div>
                                <p>Aquí puedes ver todos los ejercicios disponibles y sus detalles.</p>
                                <div class="ejercicios-contenedor">
                                    <c:forEach var="ejercicio" items="${ejercicios}">
                                        <div class="ejercicio">
                                            <img src="assets/img/ejercicioImagen/${ejercicio.imagen}" alt="${ejercicio.nombre}" class="ejercicio-thumbnail">
                                            <a href="mostrarDetalleDeEjercicio?ejercicioId=${ejercicio.ejercicioID}&categoriaId=${ejercicio.parteDelCuerpoID}" class="ejercicio-nombre">${ejercicio.nombre}</a>
                                            <span class="ejercicio-detalles">${ejercicio.musculosQueTrabaja}</span>
                                        </div>
                                    </c:forEach>
                                </div>
                                <a href="agregarEjercicio" class="boton">Agregar Ejercicio</a>
                                <br>
                                <a href="wikiEjercicios">Volver a la wiki de ejercicios</a>
                                <br>
                                <a href="redirigirPerfil">Volver al menú de perfil</a>
                                <br>
                                <c:import url="includes/cerrarSesion.jsp" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </main>
    <!-- ======= Footer ======= -->
    <c:import url="includes/footer.jsp" />
    <!-- End Footer -->
</body>
</html>