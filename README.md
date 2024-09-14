# Proyecto GYMWEB

GYMWEB es una aplicación de entrenamiento diseñada para gestionar y facilitar el acceso a rutinas de ejercicio y administración de usuarios.

## Estructura del Proyecto

El proyecto está organizado en las siguientes carpetas:

### BD (Base de Datos)

Esta carpeta contiene los scripts SQL necesarios para la creación y gestión de la base de datos del proyecto. Aquí encontrarás:

- **BD_GYM_WEB.mwb**: Modelo de base de datos de MySQL Workbench.

### DOCUMENTACION

En esta carpeta se encuentra toda la documentación relacionada con el proyecto, incluyendo:

- **WIREFRAME GYMWEB.vsdx**: Diagrama de flujo de la aplicación.

### src/main

Esta es la carpeta principal del código fuente del proyecto. Está organizada de la siguiente manera:

- **java**: Contiene el código fuente Java del proyecto, organizado en paquetes según la funcionalidad.
- **resources**: Contiene los archivos de configuración y recursos estáticos como archivos de propiedades, plantillas, etc.
- **webapp**: Contiene los archivos web estáticos y dinámicos, incluyendo HTML, CSS, JavaScript y JSP.

### Otros Archivos y Carpetas

- **pom.xml**: Archivo de configuración de Maven, que gestiona las dependencias y el ciclo de vida del proyecto.
- **README.md**: Este archivo, que proporciona una visión general del proyecto y su estructura.

## Flujo de Trabajo

El proyecto sigue un flujo de trabajo basado en una combinación de fork y una versión simplificada de Git-Flow. Este enfoque se adapta bien a equipos pequeños y permite mantener una rama principal estable.

### Estructura de Ramas

- **main**: La rama principal intocable que refleja el código en producción.
- **develop**: Una copia de main donde se integrarán las nuevas características.
- **Ramas de características (feature branches)**: Por ejemplo, feature/login, feature/user-profile, etc.

### Convención de nombres de commits:
Los mensajes de commit deben comenzar con una categoría de cambio, que puede ser una de las siguientes: feat, fix, refactor o chore.
- **feat**: Nueva funcionalidad.
- **fix**: Corrección de errores.
- **refactor**: Cambios en el código que no añaden nuevas funcionalidades ni corrigen errores.
- **chore**: se utiliza para cualquier otro tipo de cambio, como escribir documentación, formatear código, agregar pruebas, etc.
