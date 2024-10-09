-- Inserción de datos en la tabla usuario
INSERT INTO `mydb`.`usuario` (`usuarioID`, `nombre`, `correo`, `contraseña`, `rol`) VALUES
(1, 'Juan Pérez', 'juan.perez@email.com', 'contraseña123', 'final'),
(2, 'María García', 'maria.garcia@email.com', 'clave456', 'final'),
(3, 'Carlos Rodríguez', 'carlos.rodriguez@email.com', 'segura789', 'administrador'),
(4, 'Ana Martínez', 'ana.martinez@email.com', 'gym2024', 'final'),
(5, 'Luis González', 'luis.gonzalez@email.com', 'fuerte101', 'final');

-- Inserción de datos en la tabla entrenamiento
INSERT INTO `mydb`.`entrenamiento` (`entrenamientoID`, `usuarioID`, `nombre`, `cantidadEjercicios`) VALUES
(1, 1, 'Rutina de pecho', '4'),
(2, 2, 'Día de piernas', '5'),
(3, 4, 'Full body', '7'),
(4, 1, 'Espalda y bíceps', '6'),
(5, 5, 'Cardio intenso', '3');

-- Inserción de datos en la tabla equipo
INSERT INTO `mydb`.`equipo` (`equipoID`, `nombre`, `imagen`) VALUES
(1, 'Mancuernas', 'https://ejemplo.com/imagenes/mancuernas.jpg'),
(2, 'Barra olímpica', 'https://ejemplo.com/imagenes/barra-olimpica.jpg'),
(3, 'Máquina de poleas', 'https://ejemplo.com/imagenes/maquina-poleas.jpg'),
(4, 'Banco plano', 'https://ejemplo.com/imagenes/banco-plano.jpg'),
(5, 'Cinta de correr', 'https://ejemplo.com/imagenes/cinta-correr.jpg');

-- Inserción de datos en la tabla parteDelCuerpo
INSERT INTO `mydb`.`parteDelCuerpo` (`parteDelCuerpoID`, `nombre`, `imagen`) VALUES
(1, 'Pecho', 'https://ejemplo.com/imagenes/musculos-pecho.jpg'),
(2, 'Espalda', 'https://ejemplo.com/imagenes/musculos-espalda.jpg'),
(3, 'Piernas', 'https://ejemplo.com/imagenes/musculos-piernas.jpg'),
(4, 'Hombros', 'https://ejemplo.com/imagenes/musculos-hombros.jpg'),
(5, 'Bíceps', 'https://ejemplo.com/imagenes/musculos-biceps.jpg');

-- Inserción de datos en la tabla ejercicio
INSERT INTO `mydb`.`ejercicio` (`ejercicioID`, `nombre`, `imagen`, `parteDelCuerpoID`, `musculosQueTrabaja`, `equipoID`, `descripcion`, `preparacion`, `ejecucion`, `consejosClave`, `cargadoPor`, `usuario_usuarioID`) VALUES
(1, 'Press de banca', 'https://ejemplo.com/imagenes/press-banca.jpg', 1, 'Pecho, tríceps, hombros', 2, 'Ejercicio clásico para pecho', 'Acuéstate en el banco con los pies en el suelo', 'Baja la barra al pecho y empuja hacia arriba', 'Mantén los codos a 45 grados del cuerpo', 'sistema', 3),
(2, 'Sentadillas', 'https://ejemplo.com/imagenes/sentadillas.jpg', 3, 'Cuádriceps, glúteos, isquiotibiales', 2, 'Ejercicio fundamental para piernas', 'Coloca la barra en los trapecios', 'Flexiona las rodillas y caderas, baja y sube', 'Mantén la espalda recta y el pecho hacia fuera', 'sistema', 3),
(3, 'Remo con barra', 'https://ejemplo.com/imagenes/remo-barra.jpg', 2, 'Espalda, bíceps, antebrazos', 2, 'Excelente para la espalda', 'Inclínate hacia adelante con la espalda recta', 'Tira de la barra hacia el abdomen', 'Aprieta los omóplatos al final del movimiento', 'sistema', 3),
(4, 'Curl de bíceps', 'https://ejemplo.com/imagenes/curl-biceps.jpg', 5, 'Bíceps, antebrazos', 1, 'Aislamiento de bíceps', 'De pie, mancuernas a los lados', 'Flexiona los codos y levanta las mancuernas', 'No balancees el cuerpo', 'usuario', 1),
(5, 'Press militar', 'https://ejemplo.com/imagenes/press-militar.jpg', 4, 'Hombros, tríceps', 2, 'Desarrollo de hombros', 'De pie, barra a la altura de los hombros', 'Empuja la barra sobre la cabeza', 'Mantén el core apretado', 'sistema', 3);

-- Inserción de datos en la tabla entrenamiento_has_ejercicio
INSERT INTO `mydb`.`entrenamiento_has_ejercicio` (`EjercicioEntrenamientoID`, `EntrenamientoID`, `usuarioID`, `ejercicioID`, `numeroSerie`, `repeticiones`, `peso`) VALUES
(1, 1, 1, 1, 1, 10, 60.5),
(2, 1, 1, 1, 2, 8, 65.0),
(3, 1, 1, 4, 1, 12, 15.0),
(4, 2, 2, 2, 1, 12, 80.0),
(5, 2, 2, 2, 2, 10, 90.0),
(6, 3, 4, 1, 1, 10, 50.0),
(7, 3, 4, 2, 1, 12, 70.0),
(8, 3, 4, 3, 1, 10, 60.0),
(9, 4, 1, 3, 1, 12, 70.0),
(10, 4, 1, 4, 1, 10, 20.0),
(11, 5, 5, 2, 1, 15, 60.0),
(12, 5, 5, 5, 1, 12, 40.0);