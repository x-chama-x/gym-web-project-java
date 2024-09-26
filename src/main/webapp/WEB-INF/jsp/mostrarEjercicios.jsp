<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Ejercicios</title>
    <link href="assets/css/style.css" rel="stylesheet">
</head>
<body>
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
                                            <img src="assets/img/${ejercicio.imagen}" alt="${ejercicio.nombre}" class="ejercicio-thumbnail">
                                            <a href="#" class="ejercicio-nombre">${ejercicio.nombre}</a>
                                            <span class="ejercicio-detalles">${ejercicio.musculosQueTrabaja}</span>
                                        </div>
                                    </c:forEach>
                                </div>
                                <a href="agregarEjercicio.html" class="boton">Agregar Ejercicio</a>
                                <br>
                                <a href="wikiEjercicios">Volver a la wiki de ejercicios</a>
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
</body>
</html>