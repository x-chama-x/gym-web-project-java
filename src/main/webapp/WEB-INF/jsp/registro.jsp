<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
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
                <form action="registro" method="post" name="vaidrollteam">
                  <div class="formtitulo">Registrarse</div>
                  <c:if test="${not empty errorMessage and errorMessage != null}">
                    <div class="alert" role="alert">
                      ${errorMessage}
                    </div>
                  </c:if>
                  <img src="assets/img/apple-touch-icon.png"/>
                  <input type="text" name="username" placeholder="&#128273; Ingresar nombre de usuario" maxlength="12" class="cajaentradatexto" required>
                  <input type="email" name="email" placeholder="&#128231; Ingresar correo electrónico" maxlength="35" class="cajaentradatexto" required>
                  <input type="password" name="password" placeholder="&#128274; Ingresar contraseña" maxlength="12" class="cajaentradatexto" required>
                  <input type="password" name="confirmpassword" placeholder="&#128274; Confirmar contraseña" maxlength="12" class="cajaentradatexto" required>
                  <input type="submit" value="Registrarse" class="botonenviar">
                  <div>¿Ya tienes una cuenta? <a href="index.jsp">Iniciar sesión</a></div>
                </form>
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