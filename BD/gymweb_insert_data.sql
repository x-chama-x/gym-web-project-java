-- Inserción de datos en la tabla usuario
INSERT INTO `mydb`.`usuario` (`usuarioID`, `nombre`, `correo`, `contraseña`, `rol`) VALUES
(1, 'francisco', 'franciscofchiminelli@hotmail.com', '123456', 'administrador'),
(2, 'santi', 'santi.garcia@email.com', '1234', 'final'),

-- Inserción de datos en la tabla entrenamiento
INSERT INTO `mydb`.`entrenamiento` (`entrenamientoID`, `usuarioID`, `nombre`, `cantidadEjercicios`) VALUES
(1, 1, 'Rutina de pecho', '4'),
(2, 2, 'Día de piernas', '5'),
(3, 4, 'Full body', '7'),
(4, 1, 'Espalda y bíceps', '6'),
(5, 5, 'Cardio intenso', '3');

-- Inserción de datos en la tabla equipo
INSERT INTO `mydb`.`equipo` (`equipoID`, `nombre`, `imagen`) VALUES
(1, 'Banco plano', 'banco_plano.jpg'),
(2, 'Mancuernas', 'mancuernas.jpg'),
(3, 'Peso corporal', 'peso_corporal.jpg'),
(4, 'Barra de pesas', 'barra.jpg'),
(5, 'Máquina de poleas', 'poleas.jpg'),
(6, 'Máquina de pectorales', 'maquinapec.jpg');

-- Inserción de datos en la tabla parteDelCuerpo
INSERT INTO `mydb`.`parteDelCuerpo` (`parteDelCuerpoID`, `nombre`, `imagen`) VALUES
(1, 'Pecho', 'pecho.jpg'),
(2, 'Espalda', 'espalda.jpg'),
(3, 'Piernas', 'piernas.jpg'),
(4, 'Hombros', 'hombros.jpg'),
(5, 'Brazos', 'brazos.jpg'),
(6, 'Abdominales', 'abdominales.jpg');

-- Inserción de datos en la tabla ejercicio
INSERT INTO `mydb`.`ejercicio` (`ejercicioID`, `nombre`, `imagen`, `parteDelCuerpoID`, `musculosQueTrabaja`, `equipoID`, `descripcion`, `preparacion`, `ejecucion`, `consejosClave`, `cargadoPor`, `usuario_usuarioID`) VALUES
(1, 'Press de banca', 'press_banca.jpg', 1, 'Pectorales, tríceps', 1, 'Ejercicio básico para pecho', 'Acostado en banco', 'Baja la barra al pecho', 'Empuja la barra hacia arriba', 'sistema', NULL),
(2, 'Aperturas con mancuernas', 'aperturas.jpg', 1, 'Pectorales', 1, 'Aísla los pectorales', 'Acostado en banco', 'Abre y cierra los brazos', 'Mantén los codos ligeramente flexionados', 'sistema', NULL),
(3, 'Dominadas', 'dominadas.jpg', 2, 'Espalda, bíceps', 1, 'Ejercicio compuesto para espalda', 'Colgado de barra', 'Eleva el cuerpo', 'Agarre firme', 'sistema', NULL),
(4, 'Remo con barra', 'remo_barra.jpg', 2, 'Espalda media, bíceps', 1, 'Fortalece la espalda media', 'De pie, inclinado', 'Tira de la barra hacia el abdomen', 'Mantén la espalda recta', 'sistema', NULL),
(5, 'Sentadillas', 'sentadillas.jpg', 3, 'Cuádriceps, glúteos', 1, 'Ejercicio completo para piernas', 'De pie, pies separados', 'Flexiona rodillas, baja y sube', 'Mantén la espalda recta', 'sistema', NULL),
(6, 'Peso muerto', 'peso_muerto.jpg', 3, 'Isquiotibiales, glúteos, espalda baja', 1, 'Ejercicio compuesto para piernas y espalda', 'De pie, barra al frente', 'Flexiona caderas y rodillas, baja y sube', 'Mantén la espalda recta', 'sistema', NULL),
(7, 'Press militar', 'press_militar.jpg', 4, 'Deltoides, tríceps', 1, 'Ejercicio básico para hombros', 'De pie o sentado', 'Empuja la barra sobre la cabeza', 'Mantén el core estable', 'sistema', NULL),
(8, 'Elevaciones laterales', 'elevaciones_laterales.jpg', 4, 'Deltoides laterales', 1, 'Aísla los deltoides laterales', 'De pie, mancuernas a los lados', 'Eleva las mancuernas hasta la altura de los hombros', 'Codos ligeramente flexionados', 'sistema', NULL),
(9, 'Curl de bíceps Mancuerna', 'curl_biceps.jpg', 5, 'Bíceps', 1, 'Ejercicio de aislamiento para bíceps', 'De pie, mancuernas', 'Flexiona y extiende los codos', 'Codos pegados al cuerpo', 'sistema', NULL),
(10, 'Extensiones de tríceps', 'extensiones_triceps.jpg', 5, 'Tríceps', 1, 'Aísla los tríceps', 'De pie o sentado', 'Extiende los brazos sobre la cabeza', 'Mantén los codos cerca de la cabeza', 'sistema', NULL),
(11, 'Crunches', 'crunches.jpg', 6, 'Abdominales superiores', 1, 'Ejercicio básico para abdominales', 'Acostado, rodillas flexionadas', 'Eleva los hombros del suelo', 'Mantén la zona lumbar pegada al suelo', 'sistema', NULL),
(12, 'Plancha', 'plancha.jpg', 6, 'Core, abdominales', 1, 'Fortalece el core y la estabilidad', 'Posición de flexiones', 'Mantén la posición', 'Mantén el cuerpo en línea recta', 'sistema', NULL);

-- El campo usuario_usuarioID es NULL porque en los datos hardcodeados
-- de los ejercicios en EjercicioDAOHardCodeado, no se especifica un valor
-- para este campo. En el archivo SQL, el valor NULL se utiliza
-- para indicar que no hay un usuario específico asociado con la inserción de esos ejercicios.



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