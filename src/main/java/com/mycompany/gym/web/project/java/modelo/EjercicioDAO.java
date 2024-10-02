package com.mycompany.gym.web.project.java.modelo;

import java.util.ArrayList;
import java.util.List;

public class EjercicioDAO implements DAO<Ejercicio,Integer> {
    private List<Ejercicio> ejercicios;
    private static EjercicioDAO instance; // Singleton para que solo haya una instancia de la clase

    public EjercicioDAO() {
        this.ejercicios = new ArrayList<>();
        cargarTodosLosEjercicios();
    }

    public static synchronized EjercicioDAO getInstance() {
        if (instance == null) {
            instance = new EjercicioDAO();
        }
        return instance;
    }

    // Datos Hardcodedeados de los ejercicios

    private void cargarTodosLosEjercicios() {
        ejercicios.clear(); // Limpiar la lista antes de cargar nuevos ejercicios
        cargarEjerciciosPecho();
        cargarEjerciciosEspalda();
        cargarEjerciciosPiernas();
        cargarEjerciciosHombros();
        cargarEjerciciosBrazos();
        cargarEjerciciosAbdominales();
    }

    private void cargarEjerciciosPecho() {
        ejercicios.add(new Ejercicio(1, 1, 1, 1, "Press de banca", "press_banca.jpg",
                "Pectorales, tríceps", "Acostado en banco", "Baja la barra al pecho",
                "Ejercicio básico para pecho", "Empuja la barra hacia arriba", "Pectorales",
                CargadoPor.SISTEMA));
        ejercicios.add(new Ejercicio(2, 1, 2, 1, "Aperturas con mancuernas", "aperturas.jpg",
                "Pectorales", "Acostado en banco", "Mantén los codos ligeramente flexionados",
                "Aísla los pectorales", "Abre y cierra los brazos", "Pectorales",
                CargadoPor.SISTEMA));
    }

    private void cargarEjerciciosEspalda() {
        ejercicios.add(new Ejercicio(3, 1, 3, 2, "Dominadas", "dominadas.jpg",
                "Espalda, bíceps", "Colgado de barra", "Agarre firme",
                "Ejercicio compuesto para espalda", "Eleva el cuerpo", "Dorsal ancho",
                CargadoPor.SISTEMA));
        ejercicios.add(new Ejercicio(4, 1, 4, 2, "Remo con barra", "remo_barra.jpg",
                "Espalda media, bíceps", "De pie, inclinado", "Mantén la espalda recta",
                "Fortalece la espalda media", "Tira de la barra hacia el abdomen", "Trapecio",
                CargadoPor.SISTEMA));
    }

    private void cargarEjerciciosPiernas() {
        ejercicios.add(new Ejercicio(5, 1, 3, 3, "Sentadillas", "sentadillas.jpg",
                "Cuádriceps, glúteos", "De pie, pies separados", "Mantén la espalda recta",
                "Ejercicio completo para piernas", "Flexiona rodillas, baja y sube", "Cuádriceps",
                CargadoPor.SISTEMA));
        ejercicios.add(new Ejercicio(6, 1, 4, 3, "Peso muerto", "peso_muerto.jpg",
                "Isquiotibiales, glúteos, espalda baja", "De pie, barra al frente", "Mantén la espalda recta",
                "Ejercicio compuesto para piernas y espalda", "Flexiona caderas y rodillas, baja y sube", "Isquiotibiales",
                CargadoPor.SISTEMA));
    }

    private void cargarEjerciciosHombros() {
        ejercicios.add(new Ejercicio(7, 1, 2, 4, "Press militar", "press_militar.jpg",
                "Deltoides, tríceps", "De pie o sentado", "Mantén el core estable",
                "Ejercicio básico para hombros", "Empuja la barra sobre la cabeza", "Deltoides",
                CargadoPor.SISTEMA));
        ejercicios.add(new Ejercicio(8, 1, 2, 4, "Elevaciones laterales", "elevaciones_laterales.jpg",
                "Deltoides laterales", "De pie, mancuernas a los lados", "Codos ligeramente flexionados",
                "Aísla los deltoides laterales", "Eleva las mancuernas hasta la altura de los hombros", "Deltoides",
                CargadoPor.SISTEMA));
    }

    private void cargarEjerciciosBrazos() {
        ejercicios.add(new Ejercicio(9, 1, 2, 5, "Curl de bíceps Mancuerna", "curl_biceps.jpg",
                "Bíceps", "De pie, mancuernas", "Codos pegados al cuerpo",
                "Ejercicio de aislamiento para bíceps", "Flexiona y extiende los codos", "Bíceps",
                CargadoPor.SISTEMA));
        ejercicios.add(new Ejercicio(10, 1, 5, 5, "Extensiones de tríceps", "extensiones_triceps.jpg",
                "Tríceps", "De pie o sentado", "Mantén los codos cerca de la cabeza",
                "Aísla los tríceps", "Extiende los brazos sobre la cabeza", "Tríceps",
                CargadoPor.SISTEMA));
    }

    private void cargarEjerciciosAbdominales() {
        ejercicios.add(new Ejercicio(11, 1, 3, 6, "Crunches", "crunches.jpg",
                "Abdominales superiores", "Acostado, rodillas flexionadas", "Mantén la zona lumbar pegada al suelo",
                "Ejercicio básico para abdominales", "Eleva los hombros del suelo", "Recto abdominal",
                CargadoPor.SISTEMA));
        ejercicios.add(new Ejercicio(12, 1, 3, 6, "Plancha", "plancha.jpg",
                "Core, abdominales", "Posición de flexiones", "Mantén el cuerpo en línea recta",
                "Fortalece el core y la estabilidad", "Mantén la posición", "Core",
                CargadoPor.SISTEMA));
    }

    @Override
    public List<Ejercicio> getAll() throws Exception {
        return new ArrayList<>(ejercicios);
    }

    @Override
    public Ejercicio getById(Integer id) throws Exception {
        Ejercicio ejercicio = null;
        int i = 0;
        while (i < ejercicios.size() && ejercicio == null) {
            if (ejercicios.get(i).getEjercicioID() == id) {
                ejercicio = ejercicios.get(i);
            }
            i++;
        }
        if (ejercicio == null) {
            throw new IllegalArgumentException("Ejercicio no encontrado: " + id);
        }
        return ejercicio;
    }

    // obtener una lista de ejercicios por ID de parte del cuerpo
    public List<Ejercicio> getByParteDelCuerpoID(int parteDelCuerpoID) {
        List<Ejercicio> ejerciciosPorParteDelCuerpo = new ArrayList<>();
        for (Ejercicio ejercicio : ejercicios) {
            if (ejercicio.getParteDelCuerpoID() == parteDelCuerpoID) {
                ejerciciosPorParteDelCuerpo.add(ejercicio);
            }
        }
        return ejerciciosPorParteDelCuerpo;
    }

    // Metodo que devuelve un ejercicio por ID de una lista de ejercicios cargada
    public Ejercicio getById(List<Ejercicio> ejercicios, int ejercicioId) {
        Ejercicio ejercicio = null;
        int i = 0;
        while (i < ejercicios.size() && ejercicio == null) {
            if (ejercicios.get(i).getEjercicioID() == ejercicioId) {
                ejercicio = ejercicios.get(i);
            }
            i++;
        }
        if (ejercicio == null) {
            throw new IllegalArgumentException("Ejercicio no encontrado: " + ejercicioId);
        }
        return ejercicio;
    }

    @Override
    public void add(Ejercicio entidad) throws Exception {
        entidad.setEjercicioID(ejercicios.size() + 1); // Simular ID autoincremental
        ejercicios.add(entidad);
    }

    @Override
    public void delete(Integer id) throws Exception {
        Ejercicio ejercicio = getById(id);
        if (ejercicio != null) {
            ejercicios.remove(ejercicio);
        } else {
            throw new IllegalArgumentException("Ejercicio no encontrado: " + id);
        }
    }

    @Override
    public void update(Ejercicio entidad) throws Exception {
        Ejercicio ejercicio = getById(entidad.getEjercicioID());
        if (ejercicio != null) {
            ejercicio.setNombre(entidad.getNombre());
            ejercicio.setImagen(entidad.getImagen());
            ejercicio.setMusculosQueTrabaja(entidad.getMusculosQueTrabaja());
            ejercicio.setPreparacion(entidad.getPreparacion());
            ejercicio.setConsejosClave(entidad.getConsejosClave());
            ejercicio.setDescripcion(entidad.getDescripcion());
            ejercicio.setEjecucion(entidad.getEjecucion());
            ejercicio.setMusculoPrincipal(entidad.getMusculoPrincipal());
        } else {
            throw new IllegalArgumentException("Ejercicio no encontrado: " + entidad.getEjercicioID());
        }
    }

    public void mostrarListaDeEjerciciosSoloNombre(List<Ejercicio> ejercicios) {
        System.out.println("Lista de ejercicios:");
        for (Ejercicio ejercicio : ejercicios) {
            System.out.println(ejercicio.getNombre());
        }
    }
}
