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
                                <div class="formtitulo">Agregar Ejercicio</div>
                                <form action="agregarEjercicio" method="post" enctype="multipart/form-data">
                                    <label for="nombre">Nombre:</label>
                                    <input type="text" id="nombre" name="nombre" maxlength="35" required><br>
                                    <label for="imagen">Imagen:</label>
                                    <input type="file" id="imagen" name="imagen" accept="image/*" required><br>
                                    <label for="musculosQueTrabaja">Músculos que Trabaja:</label>
                                    <input type="text" id="musculosQueTrabaja" name="musculosQueTrabaja" maxlength="45" required><br>
                                    <label for="preparacion">Preparación:</label>
                                    <input type="text" id="preparacion" name="preparacion" maxlength="150" required><br>
                                    <label for="consejosClave">Consejos Clave:</label>
                                    <input type="text" id="consejosClave" name="consejosClave" maxlength="150" required><br>
                                    <label for="descripcion">Descripción:</label>
                                    <input type="text" id="descripcion" name="descripcion" maxlength="150" required><br>
                                    <label for="ejecucion">Ejecución:</label>
                                    <input type="text" id="ejecucion" name="ejecucion" maxlength="150" required><br>
                                    <label for="musculoPrincipal">Músculo Principal:</label>
                                    <select id="musculoPrincipal" name="musculoPrincipal" required>
                                        <c:forEach var="parte" items="${partesDelCuerpo}">
                                            <option value="${parte.nombre}">${parte.nombre}</option>
                                        </c:forEach>
                                    </select><br>

                                    <label for="equipo">Equipo:</label>
                                    <select id="equipo" name="equipo" required>
                                        <c:forEach var="equipo" items="${equipos}">
                                            <option value="${equipo.nombre}">${equipo.nombre}</option>
                                        </c:forEach>
                                    </select><br>
                                    <button type="submit" class="boton">Agregar</button>
                                </form>
                                <a href="wikiEjercicios" class="boton">Cancelar</a>
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