# 📁 Proyecto GYMWEB - Seguimiento de actividad física

## Descripción:
GYMWEB es una aplicación web que permite a los usuarios realizar un seguimiento de sus entrenamientos y progreso en el gimnasio. Ofrece funciones para registrar entrenamientos, gestionar ejercicios y analizar estadísticas.

## 🚀 Características Principales

### Dos roles de usuario:

#### Usuario final:
- Puede registrarse e iniciar sesión en la plataforma
- Puede acceder a su perfil, que incluye secciones para entrenamientos personalizados, una wiki de ejercicios y un informe de progreso
- Puede crear nuevos entrenamientos y agregarle múltiples ejercicios, especificando el nombre del ejercicio, la cantidad de repeticiones y series
- Puede visualizar, editar y eliminar entrenamientos cargados previamente
- Puede ver los ejercicios en la wiki de ejercicios, tanto los cargados por el sistema (solo verlos) como los creados por él mismo (editar y eliminar)
- Puede ver un resumen de sus entrenamientos anteriores, así como detalles sobre qué músculo se trabaja más en promedio

#### Administrador:
- Puede gestionar usuarios: agregar, modificar y eliminar cuentas
- Puede asignar roles de "Usuario Final" o "Administrador" a los usuarios
- Puede gestionar ejercicios: agregar, eliminar y editar ejercicios del sistema
- también puede hacer todo lo que un usuario final puede hacer

### Características generales:
- Entrenamientos personalizados: Los usuarios finales pueden crear, editar y visualizar sus propios entrenamientos
- Wiki de ejercicios: Una base de datos completa de ejercicios con descripciones detalladas, imágenes y categorización por parte del cuerpo
- Informes de progreso: Ofrece a los usuarios información sobre su rendimiento, incluyendo qué músculos se trabajan con más frecuencia

## 🧱 Estructura del Proyecto

El proyecto sigue una arquitectura Modelo-Vista-Controlador (MVC) y se basa en las siguientes tecnologías:

- Frontend: HTML, CSS, JavaScript, JSP (Jakarta Server Pages)
- Backend: Java, Jakarta EE (anteriormente Java EE), Servlets
- Base de datos: MySQL
- Gestión de dependencias: Maven
- Librería de etiquetas: JSTL (Jakarta Standard Tag Library)
- Conexión a la base de datos: Pool de conexiones

### Carpetas principales:

- **BD**: Contiene los scripts SQL y el modelo de base de datos (BD_GYM_WEB.mwb) para la creación y gestión de la base de datos.

- **DOCUMENTACION**: Contiene la documentación del proyecto, incluyendo el diagrama de flujo (WIREFRAME GYMWEB.vsdx), el diagrama entidad-relación, casos de uso y notas sobre características específicas como la wiki de ejercicios.

- **src/main/java**: Contiene el código fuente Java del proyecto, organizado en paquetes:
  - controlador: Servlets que manejan las peticiones HTTP, la lógica de control y el enrutamiento
  - modelo: Clases JavaBeans que representan las entidades de la base de datos
  - modelo/db: Clases responsables de la interacción con la base de datos
  - fitros: Filtros que se aplican a las peticiones HTTP para la autenticación y autorización de usuarios a ciertas rutas

- **src/main/resources**: Contiene archivos de configuración, incluyendo el archivo de propiedades para la conexión a la base de datos.

- **src/main/webapp**: Contiene los archivos web estáticos (HTML, CSS, JavaScript) y dinámicos (JSP).

## 💻 Flujo de trabajo

El proyecto utiliza una combinación de fork y una versión simplificada de Git-Flow para gestionar el desarrollo colaborativo.

### Estructura de ramas:
- main: Rama principal que contiene el código de producción
- develop: Rama de desarrollo donde se integran las nuevas características
- Ramas de características (feature branches): Ramas dedicadas al desarrollo de nuevas funcionalidades

## 🔧 Instalación y ejecución

1. Clonar el repositorio: `git clone [URL del repositorio]`
2. Importar el proyecto en un IDE compatible con Java EE, como NetBeans o Eclipse
3. Configurar la conexión a la base de datos: Modificar el archivo `src/main/resources/META-INF/DBConnection.properties`
4. Compilar y desplegar el archivo WAR generado en un servidor Apache Tomcat

## 👤 Contacto

- Desarrollador: Francisco Chiminelli

## 📚 Recursos adicionales

- [Documentación de Jakarta EE](https://jakarta.ee/)
- [Documentación de Apache Tomcat](https://tomcat.apache.org/)
- [Documentación de MySQL](https://dev.mysql.com/doc/)
- [Documentación de JSTL](https://docs.oracle.com/javaee/5/tutorial/doc/bnalj.html)