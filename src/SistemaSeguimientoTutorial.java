import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaSeguimientoTutorial {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ExpedienteTutorial expediente = new ExpedienteTutorial();

    public static void main(String[] args) {
        ejecutarMenu();
        scanner.close();
    }

    private static void ejecutarMenu() {
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Selecciona una opción: ", 0, 9);
            System.out.println();

            switch (opcion) {
                case 1:
                    registrarFichaIdentificacion();
                    break;
                case 2:
                    registrarEntrevistaTutorial();
                    break;
                case 3:
                    registrarLineaDeVida();
                    break;
                case 4:
                    registrarAnalisisFoda();
                    break;
                case 5:
                    aplicarEncuestaHabilidadesEstudio();
                    break;
                case 6:
                    aplicarTestAutoestima();
                    break;
                case 7:
                    registrarTrayectoriaAcademica();
                    break;
                case 8:
                    registrarDesempenoAcademico();
                    break;
                case 9:
                    mostrarResumen();
                    exportarResumen();
                    break;
                case 0:
                    System.out.println("Sistema finalizado.");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }

            if (opcion != 0) {
                pausar();
            }
        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        limpiarPantallaVisual();
        System.out.println("=================================================");
        System.out.println("       SISTEMA DE SEGUIMIENTO TUTORIAL");
        System.out.println("=================================================");
        System.out.println("1. Registrar ficha de identificación");
        System.out.println("2. Registrar entrevista tutorial");
        System.out.println("3. Registrar línea de vida");
        System.out.println("4. Registrar análisis FODA");
        System.out.println("5. Aplicar encuesta de habilidades de estudio");
        System.out.println("6. Aplicar test de autoestima");
        System.out.println("7. Registrar trayectoria académica");
        System.out.println("8. Registrar desempeño académico");
        System.out.println("9. Ver y exportar resumen del expediente");
        System.out.println("0. Salir");
        System.out.println("=================================================");
    }

    private static void registrarFichaIdentificacion() {
        mostrarTitulo("Ficha de identificación");

        expediente.estudiante.nombre = leerTexto("Nombre del estudiante: ");
        expediente.estudiante.numeroControl = leerTexto("Número de control: ");
        expediente.estudiante.carrera = leerTexto("Carrera: ");
        expediente.estudiante.semestre = leerTexto("Semestre: ");
        expediente.estudiante.correo = leerTexto("Correo electrónico: ");
        expediente.estudiante.telefono = leerTexto("Teléfono de contacto: ");
        expediente.estudiante.contactoEmergencia = leerTexto("Contacto de emergencia: ");

        System.out.println("\nFicha registrada correctamente.");
    }

    private static void registrarEntrevistaTutorial() {
        mostrarTitulo("Entrevista tutorial");

        expediente.entrevista.areaFamiliar = leerTextoLargo("Área familiar. Describe la relación familiar: ");
        expediente.entrevista.areaSocial = leerTextoLargo("Área social. Describe la relación con compañeros, amigos y docentes: ");
        expediente.entrevista.areaPsicopedagogica = leerTextoLargo("Área psicopedagógica. Describe situación académica, hábitos y necesidades: ");
        expediente.entrevista.planVida = leerTextoLargo("Plan de vida y carrera. Describe metas inmediatas y metas a futuro: ");
        expediente.entrevista.observaciones = leerTextoLargo("Observaciones del tutor: ");

        System.out.println("\nEntrevista registrada correctamente.");
    }

    private static void registrarLineaDeVida() {
        mostrarTitulo("Línea de vida");

        expediente.lineaVida.eventos.clear();

        int cantidad = leerEntero("¿Cuántos eventos deseas registrar? ", 1, 10);

        for (int i = 1; i <= cantidad; i++) {
            System.out.println("\nEvento " + i);
            String edad = leerTexto("Edad o etapa: ");
            String descripcion = leerTextoLargo("Descripción del evento: ");
            String impacto = leerTexto("Impacto percibido (alto, medio o bajo): ");

            expediente.lineaVida.eventos.add(new EventoVida(edad, descripcion, impacto));
        }

        expediente.lineaVida.patrones = leerTextoLargo("\nPatrones identificados en la línea de vida: ");

        System.out.println("\nLínea de vida registrada correctamente.");
    }

    private static void registrarAnalisisFoda() {
        mostrarTitulo("Análisis FODA");

        expediente.foda.fortalezas = capturarLista("Fortaleza", 4);
        expediente.foda.debilidades = capturarLista("Debilidad", 4);
        expediente.foda.oportunidades = capturarLista("Oportunidad", 4);
        expediente.foda.amenazas = capturarLista("Amenaza", 4);

        System.out.println("\nAnálisis FODA registrado correctamente.");
    }

    private static void aplicarEncuestaHabilidadesEstudio() {
        mostrarTitulo("Encuesta de habilidades de estudio");

        System.out.println("Responde SI o NO según corresponda.");
        System.out.println("La calificación se obtiene contando las respuestas NO.");
        System.out.println();

        expediente.encuestaTecnicas = aplicarEncuesta(
                "Técnicas de estudio",
                obtenerPreguntasTecnicas()
        );

        expediente.encuestaMotivacion = aplicarEncuesta(
                "Motivación para el estudio",
                obtenerPreguntasMotivacion()
        );

        System.out.println("\nEncuesta registrada correctamente.");
    }

    private static ResultadoEncuesta aplicarEncuesta(String titulo, String[] preguntas) {
        int respuestasSi = 0;
        int respuestasNo = 0;
        List<String> focosRevision = new ArrayList<>();

        System.out.println("-------------------------------------------------");
        System.out.println(titulo.toUpperCase());
        System.out.println("-------------------------------------------------");

        for (int i = 0; i < preguntas.length; i++) {
            String respuesta = leerSiNo((i + 1) + ". " + preguntas[i] + " ");

            if (respuesta.equals("SI")) {
                respuestasSi++;
                focosRevision.add(preguntas[i]);
            } else {
                respuestasNo++;
            }
        }

        ResultadoEncuesta resultado = new ResultadoEncuesta();
        resultado.nombre = titulo;
        resultado.totalPreguntas = preguntas.length;
        resultado.respuestasSi = respuestasSi;
        resultado.respuestasNo = respuestasNo;
        resultado.focosRevision = focosRevision;

        System.out.println("\nResultado de " + titulo);
        System.out.println("Respuestas SI: " + respuestasSi);
        System.out.println("Respuestas NO: " + respuestasNo);
        System.out.println("Calificación: " + respuestasNo + " / " + preguntas.length);

        return resultado;
    }

    private static void aplicarTestAutoestima() {
        mostrarTitulo("Test de autoestima");

        expediente.testAutoestima.respuestas.clear();

        PreguntaOpcion[] preguntas = obtenerPreguntasAutoestima();

        for (int i = 0; i < preguntas.length; i++) {
            PreguntaOpcion pregunta = preguntas[i];

            System.out.println("\nPregunta " + (i + 1));
            System.out.println(pregunta.texto);

            for (int j = 0; j < pregunta.opciones.length; j++) {
                char letra = (char) ('A' + j);
                System.out.println(letra + ") " + pregunta.opciones[j]);
            }

            String respuesta = leerRespuestaOpcion("Respuesta: ", pregunta.opciones.length);
            expediente.testAutoestima.respuestas.add("Pregunta " + (i + 1) + ": " + respuesta);
        }

        expediente.testAutoestima.observaciones = leerTextoLargo("\nObservaciones generales del tutor: ");

        System.out.println("\nTest de autoestima registrado correctamente.");
    }

    private static void registrarTrayectoriaAcademica() {
        mostrarTitulo("Trayectoria académica");

        expediente.trayectoria.registros.clear();

        expediente.trayectoria.mes = leerTexto("Mes de seguimiento: ");
        expediente.trayectoria.tutor = leerTexto("Nombre del tutor: ");
        expediente.trayectoria.grupo = leerTexto("Grupo: ");
        expediente.trayectoria.aula = leerTexto("Aula: ");

        int cantidad = leerEntero("¿Cuántas incidencias académicas deseas registrar? ", 1, 20);

        for (int i = 1; i <= cantidad; i++) {
            System.out.println("\nIncidencia " + i);
            String asignatura = leerTexto("Asignatura: ");
            String tipo = leerTexto("Tipo de incidencia: ");
            String estrategia = leerTextoLargo("Estrategia aplicada: ");
            String observacion = leerTextoLargo("Observación: ");

            expediente.trayectoria.registros.add(
                    new RegistroTrayectoria(asignatura, tipo, estrategia, observacion)
            );
        }

        System.out.println("\nTrayectoria académica registrada correctamente.");
    }

    private static void registrarDesempenoAcademico() {
        mostrarTitulo("Desempeño académico");

        expediente.desempeno.asignaturas.clear();

        expediente.desempeno.semestre = leerTexto("Semestre: ");

        int cantidad = leerEntero("¿Cuántas asignaturas deseas registrar? ", 1, 7);

        for (int i = 1; i <= cantidad; i++) {
            System.out.println("\nAsignatura " + i);
            String nombre = leerTexto("Nombre de la asignatura: ");
            List<Double> calificaciones = new ArrayList<>();

            int unidades = leerEntero("Número de unidades evaluadas: ", 1, 8);

            for (int unidad = 1; unidad <= unidades; unidad++) {
                double calificacion = leerDouble("Calificación de la unidad " + unidad + ": ", 0, 100);
                calificaciones.add(calificacion);
            }

            String observaciones = leerTextoLargo("Observaciones: ");
            expediente.desempeno.asignaturas.add(
                    new RegistroDesempeno(nombre, calificaciones, observaciones)
            );
        }

        System.out.println("\nDesempeño académico registrado correctamente.");
    }

    private static void mostrarResumen() {
        mostrarTitulo("Resumen del expediente tutorial");

        System.out.println(generarResumen());
    }

    private static void exportarResumen() {
        String nombreArchivo = "resumen_tutorial.txt";

        try (
                PrintWriter writer = new PrintWriter(
                        new OutputStreamWriter(
                                new FileOutputStream(nombreArchivo),
                                StandardCharsets.UTF_8
                        )
                )
        ) {
            writer.print(generarResumen());
            System.out.println("\nResumen exportado en: " + nombreArchivo);
        } catch (IOException error) {
            System.out.println("No fue posible exportar el resumen.");
        }
    }

    private static String generarResumen() {
        StringBuilder resumen = new StringBuilder();

        resumen.append("SISTEMA DE SEGUIMIENTO TUTORIAL\n");
        resumen.append("========================================\n\n");

        resumen.append("FICHA DE IDENTIFICACIÓN\n");
        resumen.append("----------------------------------------\n");
        resumen.append("Nombre: ").append(valor(expediente.estudiante.nombre)).append("\n");
        resumen.append("No. de control: ").append(valor(expediente.estudiante.numeroControl)).append("\n");
        resumen.append("Carrera: ").append(valor(expediente.estudiante.carrera)).append("\n");
        resumen.append("Semestre: ").append(valor(expediente.estudiante.semestre)).append("\n");
        resumen.append("Correo: ").append(valor(expediente.estudiante.correo)).append("\n");
        resumen.append("Teléfono: ").append(valor(expediente.estudiante.telefono)).append("\n");
        resumen.append("Contacto de emergencia: ").append(valor(expediente.estudiante.contactoEmergencia)).append("\n\n");

        resumen.append("ENTREVISTA TUTORIAL\n");
        resumen.append("----------------------------------------\n");
        resumen.append("Área familiar: ").append(valor(expediente.entrevista.areaFamiliar)).append("\n");
        resumen.append("Área social: ").append(valor(expediente.entrevista.areaSocial)).append("\n");
        resumen.append("Área psicopedagógica: ").append(valor(expediente.entrevista.areaPsicopedagogica)).append("\n");
        resumen.append("Plan de vida: ").append(valor(expediente.entrevista.planVida)).append("\n");
        resumen.append("Observaciones: ").append(valor(expediente.entrevista.observaciones)).append("\n\n");

        resumen.append("LÍNEA DE VIDA\n");
        resumen.append("----------------------------------------\n");
        if (expediente.lineaVida.eventos.isEmpty()) {
            resumen.append("Sin eventos registrados.\n");
        } else {
            for (EventoVida evento : expediente.lineaVida.eventos) {
                resumen.append("- Edad/etapa: ").append(evento.edad).append("\n");
                resumen.append("  Evento: ").append(evento.descripcion).append("\n");
                resumen.append("  Impacto: ").append(evento.impacto).append("\n");
            }
        }
        resumen.append("Patrones: ").append(valor(expediente.lineaVida.patrones)).append("\n\n");

        resumen.append("ANÁLISIS FODA\n");
        resumen.append("----------------------------------------\n");
        agregarLista(resumen, "Fortalezas", expediente.foda.fortalezas);
        agregarLista(resumen, "Debilidades", expediente.foda.debilidades);
        agregarLista(resumen, "Oportunidades", expediente.foda.oportunidades);
        agregarLista(resumen, "Amenazas", expediente.foda.amenazas);
        resumen.append("\n");

        resumen.append("ENCUESTAS DE ESTUDIO\n");
        resumen.append("----------------------------------------\n");
        agregarResultadoEncuesta(resumen, expediente.encuestaTecnicas);
        agregarResultadoEncuesta(resumen, expediente.encuestaMotivacion);
        resumen.append("\n");

        resumen.append("TEST DE AUTOESTIMA\n");
        resumen.append("----------------------------------------\n");
        if (expediente.testAutoestima.respuestas.isEmpty()) {
            resumen.append("Sin respuestas registradas.\n");
        } else {
            for (String respuesta : expediente.testAutoestima.respuestas) {
                resumen.append("- ").append(respuesta).append("\n");
            }
        }
        resumen.append("Observaciones: ").append(valor(expediente.testAutoestima.observaciones)).append("\n\n");

        resumen.append("TRAYECTORIA ACADÉMICA\n");
        resumen.append("----------------------------------------\n");
        resumen.append("Mes: ").append(valor(expediente.trayectoria.mes)).append("\n");
        resumen.append("Tutor: ").append(valor(expediente.trayectoria.tutor)).append("\n");
        resumen.append("Grupo: ").append(valor(expediente.trayectoria.grupo)).append("\n");
        resumen.append("Aula: ").append(valor(expediente.trayectoria.aula)).append("\n");
        if (expediente.trayectoria.registros.isEmpty()) {
            resumen.append("Sin incidencias registradas.\n");
        } else {
            for (RegistroTrayectoria registro : expediente.trayectoria.registros) {
                resumen.append("- Asignatura: ").append(registro.asignatura).append("\n");
                resumen.append("  Incidencia: ").append(registro.tipoIncidencia).append("\n");
                resumen.append("  Estrategia: ").append(registro.estrategia).append("\n");
                resumen.append("  Observación: ").append(registro.observacion).append("\n");
            }
        }
        resumen.append("\n");

        resumen.append("DESEMPEÑO ACADÉMICO\n");
        resumen.append("----------------------------------------\n");
        resumen.append("Semestre: ").append(valor(expediente.desempeno.semestre)).append("\n");
        if (expediente.desempeno.asignaturas.isEmpty()) {
            resumen.append("Sin asignaturas registradas.\n");
        } else {
            for (RegistroDesempeno registro : expediente.desempeno.asignaturas) {
                resumen.append("- Asignatura: ").append(registro.nombreAsignatura).append("\n");
                resumen.append("  Calificaciones: ").append(registro.calificaciones).append("\n");
                resumen.append("  Promedio: ").append(String.format("%.2f", registro.calcularPromedio())).append("\n");
                resumen.append("  Observaciones: ").append(registro.observaciones).append("\n");
            }
        }

        return resumen.toString();
    }

    private static void agregarLista(StringBuilder resumen, String titulo, List<String> elementos) {
        resumen.append(titulo).append(":\n");

        if (elementos.isEmpty()) {
            resumen.append("- Sin registro\n");
            return;
        }

        for (String elemento : elementos) {
            resumen.append("- ").append(elemento).append("\n");
        }
    }

    private static void agregarResultadoEncuesta(StringBuilder resumen, ResultadoEncuesta resultado) {
        if (resultado == null) {
            resumen.append("Sin encuesta registrada.\n");
            return;
        }

        resumen.append(resultado.nombre).append(":\n");
        resumen.append("- Total: ").append(resultado.totalPreguntas).append("\n");
        resumen.append("- Respuestas SI: ").append(resultado.respuestasSi).append("\n");
        resumen.append("- Respuestas NO: ").append(resultado.respuestasNo).append("\n");
        resumen.append("- Calificación: ").append(resultado.respuestasNo).append(" / ").append(resultado.totalPreguntas).append("\n");
    }

    private static List<String> capturarLista(String etiqueta, int cantidad) {
        List<String> elementos = new ArrayList<>();

        System.out.println("\n" + etiqueta + "s");

        for (int i = 1; i <= cantidad; i++) {
            elementos.add(leerTexto(etiqueta + " " + i + ": "));
        }

        return elementos;
    }

    private static String[] obtenerPreguntasTecnicas() {
        return new String[]{
                "¿Comienzas la lectura sin revisar previamente subtítulos e ilustraciones?",
                "¿Sueles saltarte figuras, gráficas y tablas al estudiar?",
                "¿Te cuesta seleccionar los puntos principales de un tema?",
                "¿Te distraes pensando en cosas ajenas al estudio?",
                "¿Te resulta difícil entender tus apuntes después de cierto tiempo?",
                "¿Te quedas atrás al tomar notas por no escribir con suficiente rapidez?",
                "¿Tus apuntes suelen quedar desorganizados poco después de iniciar un curso?",
                "¿Intentas escribir las palabras exactas del docente al tomar notas?",
                "¿Copias palabra por palabra cuando tomas notas de un libro?",
                "¿Te resulta difícil preparar un temario para una evaluación?",
                "¿Tienes problemas para organizar el contenido de una evaluación?",
                "¿Repasas el temario formulando un resumen?",
                "¿Memorizas fórmulas o definiciones que no entiendes con claridad?",
                "¿Te cuesta decidir qué estudiar y cómo estudiarlo?",
                "¿Tienes dificultad para ordenar lógicamente los temas que debes estudiar?",
                "¿Sueles estudiar toda la asignatura en el último momento?",
                "¿Entregas exámenes sin revisarlos detenidamente?",
                "¿Terminas con frecuencia las evaluaciones en el tiempo indicado?",
                "¿Pierdes puntos por no leer cuidadosamente preguntas de verdadero/falso?",
                "¿Te apresuras en la segunda mitad de una prueba por dedicar demasiado tiempo a la primera?"
        };
    }

    private static String[] obtenerPreguntasMotivacion() {
        return new String[]{
                "¿Pierdes interés por el estudio después de los primeros días del curso?",
                "¿Crees que basta estudiar sólo lo necesario para aprobar?",
                "¿Te sientes indeciso sobre tus metas formativas y profesionales?",
                "¿Piensas que no vale la pena el esfuerzo de una educación universitaria?",
                "¿Consideras más importante divertirte que estudiar?",
                "¿Sueles distraerte en clase en lugar de atender al docente?",
                "¿Te cuesta concentrarte por inquietud, aburrimiento o mal humor?",
                "¿Piensas que algunas asignaturas tienen poco valor práctico para ti?",
                "¿Has sentido deseos de abandonar la escuela para trabajar?",
                "¿Sientes que la escuela no te prepara para la vida adulta?",
                "¿Estudias de forma casual según tu estado de ánimo?",
                "¿Te resulta aburrido estudiar libros de texto?",
                "¿Esperas a que fijen fecha de evaluación para comenzar a estudiar?",
                "¿Ves los exámenes como pruebas penosas que sólo hay que sobrevivir?",
                "¿Sientes que tus docentes no comprenden las necesidades de los estudiantes?",
                "¿Consideras que tus docentes exigen demasiadas horas de estudio fuera de clase?",
                "¿Dudas en pedir ayuda a tus docentes cuando una tarea es difícil?",
                "¿Piensas que tus docentes no tienen contacto con temas actuales?",
                "¿Evitas hablar con tus docentes sobre tus proyectos futuros?",
                "¿Criticas con frecuencia a tus docentes con tus compañeros?"
        };
    }

    private static PreguntaOpcion[] obtenerPreguntasAutoestima() {
        return new PreguntaOpcion[]{
                new PreguntaOpcion(
                        "Al tomar decisiones importantes, ¿cómo sueles actuar?",
                        new String[]{
                                "Confío únicamente en mi opinión.",
                                "Pido apoyo sólo en decisiones importantes.",
                                "Consulto siempre para evitar equivocarme.",
                                "Analizo opciones y decido con claridad."
                        }
                ),
                new PreguntaOpcion(
                        "Si notas que no vas vestido para una reunión importante, ¿qué haces?",
                        new String[]{
                                "Le resto importancia y actúo con naturalidad.",
                                "Me da vergüenza y paso desapercibido.",
                                "Me incomoda, pero intento integrarme.",
                                "No me afecta porque confío en mi estilo."
                        }
                ),
                new PreguntaOpcion(
                        "Si compras ropa con alguien a quien todo le queda mejor, ¿cómo reaccionas?",
                        new String[]{
                                "Compro prendas simples y necesarias.",
                                "Compro algo llamativo para destacar.",
                                "Elijo lo que me favorece y disfruto la experiencia.",
                                "Pierdo el ánimo y prefiero irme."
                        }
                ),
                new PreguntaOpcion(
                        "Al hablar de ti con alguien nuevo, ¿qué cuentas?",
                        new String[]{
                                "Prefiero hablar poco de mí.",
                                "Comparto mi trabajo, aficiones y proyectos.",
                                "Hablo más de mis amistades que de mí.",
                                "Me gusta hablar ampliamente de mis logros y planes."
                        }
                ),
                new PreguntaOpcion(
                        "Si no entiendes una explicación nueva, ¿qué haces?",
                        new String[]{
                                "Pido que se explique desde el inicio.",
                                "Pregunto si otros también tienen dudas.",
                                "Me da pena preguntar y lo reviso después.",
                                "Tomo nota y pregunto al finalizar."
                        }
                ),
                new PreguntaOpcion(
                        "¿Cómo definirías tu situación laboral o académica actual?",
                        new String[]{
                                "Satisfactoria, con objetivos por cumplir.",
                                "Difícil, pero la acepto.",
                                "No me preocupa; tengo otros proyectos.",
                                "La separo de mi vida personal."
                        }
                ),
                new PreguntaOpcion(
                        "Después de un día muy productivo, ¿qué haces?",
                        new String[]{
                                "Sólo deseo descansar.",
                                "Busco reconocimiento por lo hecho.",
                                "Me siento satisfecho y me doy un descanso.",
                                "Repaso si olvidé algo."
                        }
                ),
                new PreguntaOpcion(
                        "Si piden voluntarios para representar a tu grupo, ¿qué haces?",
                        new String[]{
                                "No me ofrezco porque otros pueden hacerlo mejor.",
                                "Me ofrezco por la experiencia.",
                                "No me ofrezco aunque podría hacerlo bien.",
                                "Me ofrezco con seguridad."
                        }
                ),
                new PreguntaOpcion(
                        "¿Con qué idea sobre la buena fortuna te identificas más?",
                        new String[]{
                                "Me considero una persona afortunada.",
                                "La suerte requiere trabajo.",
                                "La suerte les pasa a otras personas.",
                                "Depende de la probabilidad y de cómo se perciban las situaciones."
                        }
                ),
                new PreguntaOpcion(
                        "En una fiesta donde conoces pocas personas, ¿qué actitud tomas?",
                        new String[]{
                                "Me interesa conocer a las personas.",
                                "Busco causar buena impresión.",
                                "Llevo la conversación a mis temas.",
                                "Escucho primero y hablo cuando conozco el tema."
                        }
                )
        };
    }

    private static void mostrarTitulo(String titulo) {
        System.out.println("-------------------------------------------------");
        System.out.println(titulo.toUpperCase());
        System.out.println("-------------------------------------------------");
    }

    private static String leerTexto(String mensaje) {
        String entrada;

        do {
            System.out.print(mensaje);
            entrada = scanner.nextLine().trim();

            if (entrada.isEmpty()) {
                System.out.println("Este campo no puede quedar vacío.");
            }
        } while (entrada.isEmpty());

        return entrada;
    }

    private static String leerTextoLargo(String mensaje) {
        return leerTexto(mensaje);
    }

    private static String leerSiNo(String mensaje) {
        while (true) {
            String entrada = leerTexto(mensaje + "(SI/NO): ").toUpperCase();

            if (entrada.equals("SI") || entrada.equals("NO")) {
                return entrada;
            }

            System.out.println("Respuesta no válida. Escribe SI o NO.");
        }
    }

    private static String leerRespuestaOpcion(String mensaje, int totalOpciones) {
        while (true) {
            String respuesta = leerTexto(mensaje).toUpperCase();

            if (respuesta.length() == 1) {
                char letra = respuesta.charAt(0);
                char limite = (char) ('A' + totalOpciones - 1);

                if (letra >= 'A' && letra <= limite) {
                    return respuesta;
                }
            }

            System.out.println("Respuesta no válida. Ingresa una letra entre A y " + (char) ('A' + totalOpciones - 1) + ".");
        }
    }

    private static int leerEntero(String mensaje, int minimo, int maximo) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();

            try {
                int numero = Integer.parseInt(entrada);

                if (numero >= minimo && numero <= maximo) {
                    return numero;
                }

                System.out.printf("Ingresa un número entre %d y %d.%n", minimo, maximo);
            } catch (NumberFormatException error) {
                System.out.println("Entrada no válida. Ingresa un número entero.");
            }
        }
    }

    private static double leerDouble(String mensaje, double minimo, double maximo) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();

            try {
                double numero = Double.parseDouble(entrada);

                if (numero >= minimo && numero <= maximo) {
                    return numero;
                }

                System.out.printf("Ingresa un número entre %.2f y %.2f.%n", minimo, maximo);
            } catch (NumberFormatException error) {
                System.out.println("Entrada no válida. Ingresa un número.");
            }
        }
    }

    private static String valor(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return "Sin registro";
        }

        return texto;
    }

    private static void pausar() {
        System.out.println("\nPresiona Enter para volver al menú principal...");
        scanner.nextLine();
    }

    private static void limpiarPantallaVisual() {
        System.out.println("\n\n");
    }

    private static class ExpedienteTutorial {
        Estudiante estudiante = new Estudiante();
        EntrevistaTutorial entrevista = new EntrevistaTutorial();
        LineaVida lineaVida = new LineaVida();
        AnalisisFoda foda = new AnalisisFoda();
        ResultadoEncuesta encuestaTecnicas;
        ResultadoEncuesta encuestaMotivacion;
        TestAutoestima testAutoestima = new TestAutoestima();
        TrayectoriaAcademica trayectoria = new TrayectoriaAcademica();
        DesempenoAcademico desempeno = new DesempenoAcademico();
    }

    private static class Estudiante {
        String nombre;
        String numeroControl;
        String carrera;
        String semestre;
        String correo;
        String telefono;
        String contactoEmergencia;
    }

    private static class EntrevistaTutorial {
        String areaFamiliar;
        String areaSocial;
        String areaPsicopedagogica;
        String planVida;
        String observaciones;
    }

    private static class LineaVida {
        List<EventoVida> eventos = new ArrayList<>();
        String patrones;
    }

    private static class EventoVida {
        String edad;
        String descripcion;
        String impacto;

        EventoVida(String edad, String descripcion, String impacto) {
            this.edad = edad;
            this.descripcion = descripcion;
            this.impacto = impacto;
        }
    }

    private static class AnalisisFoda {
        List<String> fortalezas = new ArrayList<>();
        List<String> debilidades = new ArrayList<>();
        List<String> oportunidades = new ArrayList<>();
        List<String> amenazas = new ArrayList<>();
    }

    private static class ResultadoEncuesta {
        String nombre;
        int totalPreguntas;
        int respuestasSi;
        int respuestasNo;
        List<String> focosRevision = new ArrayList<>();
    }

    private static class TestAutoestima {
        List<String> respuestas = new ArrayList<>();
        String observaciones;
    }

    private static class TrayectoriaAcademica {
        String mes;
        String tutor;
        String grupo;
        String aula;
        List<RegistroTrayectoria> registros = new ArrayList<>();
    }

    private static class RegistroTrayectoria {
        String asignatura;
        String tipoIncidencia;
        String estrategia;
        String observacion;

        RegistroTrayectoria(String asignatura, String tipoIncidencia, String estrategia, String observacion) {
            this.asignatura = asignatura;
            this.tipoIncidencia = tipoIncidencia;
            this.estrategia = estrategia;
            this.observacion = observacion;
        }
    }

    private static class DesempenoAcademico {
        String semestre;
        List<RegistroDesempeno> asignaturas = new ArrayList<>();
    }

    private static class RegistroDesempeno {
        String nombreAsignatura;
        List<Double> calificaciones;
        String observaciones;

        RegistroDesempeno(String nombreAsignatura, List<Double> calificaciones, String observaciones) {
            this.nombreAsignatura = nombreAsignatura;
            this.calificaciones = calificaciones;
            this.observaciones = observaciones;
        }

        double calcularPromedio() {
            if (calificaciones.isEmpty()) {
                return 0;
            }

            double suma = 0;

            for (double calificacion : calificaciones) {
                suma += calificacion;
            }

            return suma / calificaciones.size();
        }
    }

    private static class PreguntaOpcion {
        String texto;
        String[] opciones;

        PreguntaOpcion(String texto, String[] opciones) {
            this.texto = texto;
            this.opciones = opciones;
        }
    }
}
