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
                                <div class="formtitulo">${ejercicio.nombre}</div>
                                <img src="assets/img/${ejercicio.imagen}" alt="${ejercicio.nombre}" class="ejercicio-imagen">
                                <div class="ejercicio-espaciado"></div>
                                <div class="ejercicio-detalles">
                                    <span><strong>Músculos Principales:</strong> ${ejercicio.musculosQueTrabaja}</span><br>
                                    <span><strong>Equipo:</strong> ${equipoNombre}</span><br><br>
                                    <h3>Descripción</h3>
                                    <p>${ejercicio.descripcion}</p>
                                    <h3>Preparación</h3>
                                    <p>${ejercicio.preparacion}</p>
                                    <h3>Ejecución</h3>
                                    <p>${ejercicio.ejecucion}</p>
                                    <h3>Consejos Clave</h3>
                                    <ul>
                                        <c:forEach var="consejo" items="${ejercicio.consejosClave.split(',')}">
                                            <li>${consejo}</li>
                                        </c:forEach>
                                    </ul>
                                </div>
                                <a href="mostrarEjercicios?categoriaId=${ejercicio.parteDelCuerpoID}" class="boton">Volver a la lista de ejercicios </a>
                                <br>
                                <c:if test="${sessionScope.rolUsuario == 'ADMINISTRADOR' || ejercicio.cargadoPor != 'SISTEMA'}">
                                <br>
                                    <a href="editarEjercicio?ejercicioId=${ejercicio.ejercicioID}" class="boton">Editar Ejercicio</a>
                                    <br>
                                    <a href="eliminarEjercicio?ejercicioId=${ejercicio.ejercicioID}&categoriaId=${ejercicio.parteDelCuerpoID}" class="boton">Eliminar Ejercicio</a>
                                    <br>
                                </c:if>
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