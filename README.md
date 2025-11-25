# 🧬 Mutant Detector API - GLOBAL
Este repositorio contiene la implementación de un proyecto para detectar mutantes basado en secuencias de ADN, desarrollado como parte del examen técnico de MercadoLibre.

## 📌 Descripción del Proyecto
El proyecto está estructurado en tres niveles:<br>

__Nivel 1__: Implementación de la función isMutant en Java, que analiza una matriz de ADN para determinar si una persona es mutante (si contiene más de una secuencia de cuatro letras iguales en horizontal, vertical u oblicuo).

__Nivel 2__: Creación de una API REST con Spring Boot, que expone un endpoint /mutant/ para verificar si un ADN es mutante mediante una petición POST. La API devuelve:
- HTTP 200 OK si es mutante.
- HTTP 403 Forbidden si no es mutante.

__Nivel 3__: Integración con una base de datos H2 para almacenar los ADN verificados y un endpoint /stats que devuelve estadísticas en formato JSON

### Diagrama de secuencia
__MUTANT__
  <img width="606" height="814" alt="image" src="https://github.com/user-attachments/assets/de951614-f63a-4151-8006-b5881ccf0dbf" />

__STATS__
  <img width="806" height="922" alt="image" src="https://github.com/user-attachments/assets/83b5e76e-3aa0-4368-baa6-63cefb027a79" />

