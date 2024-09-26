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
                            <div class="formtitulo">Wiki de Ejercicios</div>
                            <p>Aquí puedes ver todos los ejercicios disponibles y sus detalles.</p>
                            <div class="ejercicios-contenedor">
                                <c:forEach var="categoria" items="${categorias}">
                                    <div class="ejercicio">
                                        <img src="assets/img/musculoPrincipalCategoria/${categoria.imagen}" alt="${categoria.nombre} " class = "ejercicio-thumbnail">
                                        <a href="mostrarEjercicios?categoriaId=${categoria.parteDelCuerpoID}" class="ejercicio-nombre">${categoria.nombre}</a>
                                    </div>
                                </c:forEach>
                            </div>
                            <a href="agregarEjercicio.html" class="boton">Agregar Ejercicio</a>
                            <br>
                            <a href="principal.html">Volver al menú de perfil</a>
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