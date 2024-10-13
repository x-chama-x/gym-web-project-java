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
                                <div class="formtitulo">Confirmar Eliminación de Ejercicio</div>
                                <p>¿Estás seguro de que deseas eliminar el ejercicio <strong>${ejercicioNombre}</strong>?</p>
                                <img src="assets/img/ejercicioImagen/${ejercicioImagen}" alt="${ejercicioNombre}" style="max-width: 200px;">
                                <form action="eliminarEjercicio" method="post">
                                    <input type="hidden" name="ejercicioId" value="${ejercicioId}">
                                    <input type="hidden" name="categoriaId" value="${categoriaId}">
                                    <button type="submit" class="boton">Confirmar</button>
                                </form>
                                <a href="mostrarEjercicios?categoriaId=${categoriaId}" class="boton">Cancelar</a>
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