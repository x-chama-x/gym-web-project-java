<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Wiki de Ejercicios - GYMWEB</title>
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
            <h1 class="logo"><a href="index.html">GYMWEB</a></h1>
            <nav id="navbar" class="navbar">
                <ul>
                    <li><a class="nav-link scrollto" href="index.html">Home</a></li>
                    <li><a class="nav-link scrollto" href="#about">Mi Perfil</a></li>
                </ul>
            </nav>
        </div>
    </header>

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
                                <button id="agregarEjercicioBtn">Agregar Ejercicio</button>

                                <div class="ejercicios-contenedor">
                                    <c:forEach var="categoria" items="${categorias}">
                                        <div class="ejercicio">
                                            <img src="assets/img/press-plano.jpg" alt="${categoria.nombre}" class="ejercicio-thumbnail">
                                            <a href="#" class="ejercicio-nombre">${categoria.nombre}</a>
                                        </div>
                                    </c:forEach>
                                </div>

                                <a href="principal.html">volver al menú de perfil</a>
                                <br>
                                <a href="index.html">Cerrar Sesión</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </main>

    <div class="divider"></div>

    <footer>
        <div class="container">
            <div class="row">
                <div class="col-sm-12">
                    <div class="copyright-box">
                        <p class="copyright">&copy; GYMWEB <strong>Aplicacion de entrenamiento</strong>. All Rights Reserved</p>
                        <div class="credits">
                            Designed by <a href="https://x-chama-x.github.io/franciscochiminelli.com/">x_chama_x</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </footer>

    <!-- Modal para agregar ejercicio -->
    <div id="agregarEjercicioModal" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <h2>Agregar Ejercicio</h2>
            <form id="agregarEjercicioForm">
                <div class="form-group">
                    <label for="nombreEjercicio">Nombre del Ejercicio:</label>
                    <input type="text" id="nombreEjercicio" name="nombreEjercicio" maxlength="50" required>
                    <span class="char-count">0/50</span>
                </div>

                <div class="form-group">
                    <label for="zonaPrincipal">Zona Principal:</label>
                    <select id="zonaPrincipal" name="zonaPrincipal" required>
                        <option value="">Seleccione una zona</option>
                        <option value="BRAZO">BRAZO</option>
                        <option value="ESPALDA">ESPALDA</option>
                        <option value="GLÚTEOS">GLÚTEOS</option>
                        <option value="HOMBRO">HOMBRO</option>
                        <option value="PECHO">PECHO</option>
                        <option value="PIERNAS">PIERNAS</option>
                        <option value="TODO EL CUERPO">TODO EL CUERPO</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="musculosPrincipales">Músculos Principales:</label>
                    <input type="text" id="musculosPrincipales" name="musculosPrincipales" maxlength="100" required>
                    <span class="char-count">0/100</span>
                </div>

                <div class="form-group">
                    <label for="equipo">Equipo:</label>
                    <select id="equipo" name="equipo" required>
                        <option value="">Seleccione el equipo</option>
                        <option value="Barra de pesas">Barra de pesas</option>
                        <option value="Mancuernas">Mancuernas</option>
                        <option value="Máquina de cable">Máquina de cable</option>
                        <option value="Banco de pesas">Banco de pesas</option>
                        <option value="Banda elástica">Banda elástica</option>
                        <option value="Pelota de estabilidad">Pelota de estabilidad</option>
                        <option value="TRX">TRX</option>
                        <option value="Kettlebell">Kettlebell</option>
                        <option value="Plyo box">Plyo box</option>
                        <option value="Peso corporal">Peso corporal</option>
                        <option value="Otro">Otro (especificar en la descripción)</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="descripcion">Descripción:</label>
                    <textarea id="descripcion" name="descripcion" maxlength="300" required></textarea>
                    <span class="char-count">0/300</span>
                </div>

                <div class="form-group">
                    <label for="preparacion">Preparación:</label>
                    <textarea id="preparacion" name="preparacion" maxlength="300" required></textarea>
                    <span class="char-count">0/300</span>
                </div>

                <div class="form-group">
                    <label for="ejecucion">Ejecución:</label>
                    <textarea id="ejecucion" name="ejecucion" maxlength="300" required></textarea>
                    <span class="char-count">0/300</span>
                </div>

                <div class="form-group">
                    <label for="consejosClave">Consejos Clave:</label>
                    <textarea id="consejosClave" name="consejosClave" maxlength="300" required></textarea>
                    <span class="char-count">0/300</span>
                </div>

                <div class="form-group">
                    <label for="imagenEjercicio">Imagen del Ejercicio:</label>
                    <input type="file" id="imagenEjercicio" name="imagenEjercicio" accept="image/*" required>
                    <img id="imagenPreview" src="#" alt="Vista previa de la imagen" style="display:none;">
                </div>

                <button type="submit">Guardar Ejercicio</button>
            </form>
        </div>
    </div>

    <!-- Add your JavaScript files here -->
    <script>
        // Get the modal
        var modal = document.getElementById("agregarEjercicioModal");

        // Get the button that opens the modal
        var btn = document.getElementById("agregarEjercicioBtn");

        // Get the <span> element that closes the modal
        var span = document.getElementsByClassName("close")[0];

        // When the user clicks the button, open the modal
        btn.onclick = function() {
            modal.style.display = "block";
        }

        // When the user clicks on <span> (x), close the modal
        span.onclick = function() {
            modal.style.display = "none";
        }

        // When the user clicks anywhere outside of the modal, close it
        window.onclick = function(event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }

        // Character count for textareas and inputs
        document.querySelectorAll('textarea, input[type="text"]').forEach(function(element) {
            element.addEventListener('input', function() {
                var charCount = this.value.length;
                var maxLength = this.getAttribute('maxlength');
                this.nextElementSibling.textContent = charCount + '/' + maxLength;
            });
        });

        // Image preview
        document.getElementById('imagenEjercicio').addEventListener('change', function(e) {
            var reader = new FileReader();
            reader.onload = function(e) {
                document.getElementById('imagenPreview').src = e.target.result;
                document.getElementById('imagenPreview').style.display = 'block';
            }
            reader.readAsDataURL(e.target.files[0]);
        });

        // Form submission
        document.getElementById('agregarEjercicioForm').addEventListener('submit', function(e) {
            e.preventDefault();
            // Here you would typically send the form data to the server
            // For now, we'll just close the modal
            modal.style.display = "none";
            alert('Ejercicio agregado con éxito!');
        });
    </script>
</body>
</html>