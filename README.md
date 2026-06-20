# Sistema de Seguimiento Tutorial Académico

Aplicación de consola desarrollada en Java para registrar información tutorial, aplicar instrumentos académicos y generar un resumen básico del seguimiento de un estudiante.

El proyecto digitaliza de forma simplificada varios formatos académicos utilizados en tutorías: ficha de identificación, entrevista tutorial, línea de vida, análisis FODA, encuesta de habilidades de estudio, test de autoestima, trayectoria académica y desempeño académico.

---

## Objetivo del proyecto

Crear un sistema de consola que permita concentrar información tutorial en un expediente académico básico.

El programa permite capturar datos del estudiante, registrar observaciones del tutor, aplicar cuestionarios y generar un archivo de resumen con la información ingresada durante la sesión.

---

## Tecnologías utilizadas

- Java
- JDK
- Programación orientada a objetos
- Consola / Terminal
- Visual Studio Code

---

## Estructura del repositorio

```text
sistema-seguimiento-tutorial-java/
├── src/
│   └── SistemaSeguimientoTutorial.java
├── README.md
└── .gitignore
```

---

## Archivo principal

```text
src/SistemaSeguimientoTutorial.java
```

Este archivo contiene la aplicación completa.  
El sistema está organizado mediante clases internas para representar el expediente tutorial, el estudiante, la entrevista, el FODA, las encuestas, la trayectoria y el desempeño académico.

---

## Módulos del sistema

### 1. Ficha de identificación

Registra los datos básicos del estudiante:

- Nombre
- Número de control
- Carrera
- Semestre
- Correo
- Teléfono
- Contacto de emergencia

### 2. Entrevista tutorial

Permite registrar información general de la entrevista:

- Área familiar
- Área social
- Área psicopedagógica
- Plan de vida y carrera
- Observaciones del tutor

### 3. Línea de vida

Permite capturar eventos importantes del estudiante por etapa o edad.

Cada evento contiene:

- Edad o etapa
- Descripción del evento
- Impacto percibido

### 4. Análisis FODA

Registra cuatro elementos por cada categoría:

- Fortalezas
- Debilidades
- Oportunidades
- Amenazas

### 5. Encuesta de habilidades de estudio

Aplica preguntas de respuesta **SI/NO** sobre:

- Técnicas de estudio
- Motivación para el estudio

Al finalizar, el sistema muestra el total de respuestas **SI**, respuestas **NO** y una calificación basada en las respuestas **NO**.

### 6. Test de autoestima

Presenta diez preguntas de opción múltiple y registra las respuestas seleccionadas por el estudiante.

También permite guardar observaciones generales del tutor.

### 7. Trayectoria académica

Permite registrar incidencias académicas por asignatura.

Cada incidencia contiene:

- Asignatura
- Tipo de incidencia
- Estrategia aplicada
- Observación

### 8. Desempeño académico

Registra asignaturas, calificaciones por unidad, observaciones y promedio por materia.

---

## Menú principal

Al ejecutar el programa se muestra el siguiente menú:

```text
=================================================
       SISTEMA DE SEGUIMIENTO TUTORIAL
=================================================
1. Registrar ficha de identificación
2. Registrar entrevista tutorial
3. Registrar línea de vida
4. Registrar análisis FODA
5. Aplicar encuesta de habilidades de estudio
6. Aplicar test de autoestima
7. Registrar trayectoria académica
8. Registrar desempeño académico
9. Ver y exportar resumen del expediente
0. Salir
=================================================
```

---

## Ejecución en Visual Studio Code

Abre la carpeta del proyecto en VSCode.

Desde la terminal integrada, entra a la carpeta `src`:

```bash
cd src
```

Compila el archivo principal:

```bash
javac SistemaSeguimientoTutorial.java
```

Ejecuta el programa:

```bash
java SistemaSeguimientoTutorial
```

---

## Ejecución desde terminal

Desde la raíz del proyecto:

```bash
cd src
javac SistemaSeguimientoTutorial.java
java SistemaSeguimientoTutorial
```

---

## Exportación de resumen

La opción 9 genera un archivo llamado:

```text
resumen_tutorial.txt
```

El archivo contiene el resumen del expediente tutorial capturado durante la ejecución del programa.

---

## Enfoque del proyecto

Este proyecto presenta una digitalización académica básica de instrumentos de tutoría.

Su propósito es mostrar el uso de Java para organizar información, validar entradas, aplicar cuestionarios y generar un resumen estructurado desde una aplicación de consola.

---

## Aviso sobre datos

Este repositorio está diseñado para trabajar con datos ficticios o de prueba.

No debe utilizarse para publicar información personal, médica, familiar o académica real de estudiantes.

---

## Autora

**Sofía Pacheco**  
GitHub: [SofiPv](https://github.com/SofiPv)

---

## Licencia

Este proyecto se distribuye bajo licencia MIT.
