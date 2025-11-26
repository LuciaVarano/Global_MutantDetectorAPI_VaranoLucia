# 🧬 Mutant Detector API - Global Project

API REST desarrollada en Spring Boot para detectar si un humano es mutante basándose en su secuencia de ADN. Este proyecto forma parte del examen Global de Desarrollo de Software.

## 🛜 Links Necesarios
- Render: https://global-mutantdetectorapi-varanolucia.onrender.com/swagger-ui/index.html

## 📑 Índice

1. [Descripción del Proyecto](#-descripción-del-proyecto)
   - [¿Cómo se detecta un mutante?](#cómo-se-detecta-un-mutante)
   - [Ejemplo de ADN Mutante](#ejemplo-de-adn-mutante)
2. [Arquitectura y Estructura del Proyecto](#️-arquitectura-y-estructura-del-proyecto)
   - [Estructura de Directorios](#estructura-de-directorios)
   - [Tecnologías Utilizadas](#tecnologías-utilizadas)
   - [Arquitectura por Capas](#arquitectura-por-capas)
3. [Instalación y Configuración](#-instalación-y-configuración)
   - [Prerrequisitos](#prerrequisitos)
   - [Clonar el Repositorio](#clonar-el-repositorio)
   - [Configuración Local](#configuración-local)
   - [Ejecución con Docker](#ejecución-con-docker)
4. [Endpoints de la API](#-endpoints-de-la-api)
   - [Detectar Mutante](#1-detectar-mutante)
   - [Obtener Estadísticas](#2-obtener-estadísticas)
5. [Testing](#-testing)
   - [Ejecutar Tests](#ejecutar-tests)
   - [Cobertura de Tests](#cobertura-de-tests)
6. [Ejemplos de Uso](#-ejemplos-de-uso)
   - [Usando cURL](#usando-curl)
   - [Usando Postman](#usando-postman)
   - [Usando JavaScript](#usando-javascript-fetch-api)
7. [Deployment en Render](#-deployment-en-render)
8. [Validaciones](#-validaciones)
9. [Complejidad del Algoritmo](#-complejidad-del-algoritmo)
10. [Mejoras Futuras](#️-mejoras-futuras)
11. [Contribución](#-contribución)
12. [Licencia](#-licencia)
13. [Autor](#-autor)
14. [Contacto](#-contacto)
15. [Agradecimientos](#-agradecimientos)

## 📋 Descripción del Proyecto

Magneto quiere reclutar la mayor cantidad de mutantes para poder luchar contra los X-Men. Para ello, te ha contratado para que desarrolles un proyecto que detecte si un humano es mutante basándose en su secuencia de ADN.

### ¿Cómo se detecta un mutante?

Un humano es considerado **mutante** si se encuentra **más de una secuencia de cuatro letras iguales** de forma:
- **Horizontal**
- **Vertical** 
- **Diagonal** (tanto / como \)

Las letras válidas del ADN son: `A`, `T`, `C`, `G`

### Ejemplo de ADN Mutante

```
A T G C G A
C A G T G C
T T A T G T
A G A A G G
C C C C T A
T C A C T G
```

En este caso se detectan **2 secuencias** de 4 letras iguales:
- Horizontal: `CCCC`
- Vertical: `AAAA`

---

## 🏗️ Arquitectura y Estructura del Proyecto

### Estructura de Directorios

```
mutantesGlobal/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com.example.mutantesGlobal/
│   │   │       ├── config/          # Configuraciones de la aplicación
│   │   │       ├── controllers/     # Controladores REST
│   │   │       ├── dto/             # Data Transfer Objects
│   │   │       ├── Entidades/       # Entidades JPA
│   │   │       ├── Exception/       # Manejo de excepciones
│   │   │       ├── Repositorio/     # Repositorios JPA
│   │   │       ├── Servicios/       # Lógica de negocio
│   │   │       ├── Validadores/     # Validaciones personalizadas
│   │   │       └── MutantesGlobal   # Clase principal
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── com.example.mutantesGlobal/
│               ├── testController/
│               └── testServicio/
├── build.gradle                     # Configuración de dependencias
├── Dockerfile                       # Configuración de Docker
└── README.md
```

### Tecnologías Utilizadas

- **Java 21**
- **Spring Boot 3.x**
  - Spring Web
  - Spring Data JPA
  - Spring Validation
- **H2 Database** (desarrollo)
- **PostgreSQL** (producción)
- **Gradle** (gestor de dependencias)
- **Docker** (containerización)
- **JUnit 5** (testing)
- **Render** (deployment)

### Arquitectura por Capas

1. **Controllers**: Exponen los endpoints REST
2. **Services**: Contienen la lógica de negocio
3. **Repositories**: Acceso a datos con JPA
4. **Entities**: Modelo de datos
5. **DTOs**: Transferencia de datos
6. **Validators**: Validaciones personalizadas

---

## 🚀 Instalación y Configuración

### Prerrequisitos

- Java 21 o superior
- Gradle 8.x
- Docker (opcional)
- PostgreSQL (para producción)

### Clonar el Repositorio

```bash
git clone https://github.com/LuciaVarano/Global_MutantDetectorAPI_VaranoLucia.git
cd Global_MutantDetectorAPI_VaranoLucia
```

### Configuración Local

1. **Configurar Base de Datos** (opcional - usa H2 por defecto)

   Edita `src/main/resources/application.properties`:

   ```properties
   # H2 Database (desarrollo)
   spring.h2.console.enabled=true
   spring.datasource.url=jdbc:h2:mem:testdb
   
   # PostgreSQL (producción)
   # spring.datasource.url=jdbc:postgresql://localhost:5432/mutantdb
   # spring.datasource.username=tu_usuario
   # spring.datasource.password=tu_contraseña
   ```

2. **Compilar el Proyecto**

   ```bash
   ./gradlew clean build
   ```

3. **Ejecutar la Aplicación**

   ```bash
   ./gradlew bootRun
   ```

   La API estará disponible en: `http://localhost:8080`

### Ejecución con Docker

1. **Construir la imagen**

   ```bash
   docker build -t mutant-detector .
   ```

2. **Ejecutar el contenedor**

   ```bash
   docker run -p 8080:8080 mutant-detector
   ```

---

## 📡 Endpoints de la API

### Base URL
- **Local**: `http://localhost:8080`
- **Producción**: `https://tu-app.render.com` (reemplazar con tu URL de Render)

### 1. Detectar Mutante

**POST** `/mutant`

Verifica si una secuencia de ADN corresponde a un mutante.

#### Request Body

```json
{
  "dna": [
    "ATGCGA",
    "CAGTGC",
    "TTATGT",
    "AGAAGG",
    "CCCCTA",
    "TCACTG"
  ]
}
```

#### Responses

**200 OK** - Es mutante
```json
{
  "message": "Mutante detectado"
}
```

**403 Forbidden** - No es mutante
```json
{
  "message": "No es mutante"
}
```

**400 Bad Request** - ADN inválido
```json
{
  "error": "Secuencia de ADN inválida"
}
```

### 2. Obtener Estadísticas

**GET** `/stats`

Retorna estadísticas de las verificaciones de ADN.

#### Response

```json
{
  "count_mutant_dna": 40,
  "count_human_dna": 100,
  "ratio": 0.4
}
```

**Descripción de campos:**
- `count_mutant_dna`: Cantidad de mutantes detectados
- `count_human_dna`: Cantidad de humanos (no mutantes)
- `ratio`: Proporción de mutantes sobre el total

---

## 🧪 Testing

### Ejecutar Tests

```bash
# Ejecutar todos los tests
./gradlew test

# Ejecutar con reporte de cobertura
./gradlew test jacocoTestReport

# Ver reporte de cobertura
open build/reports/jacoco/test/html/index.html
```

### Cobertura de Tests

El proyecto incluye:
- ✅ Tests unitarios de servicios
- ✅ Tests de controladores
- ✅ Tests de validación de ADN
- ✅ 19 casos de prueba diferentes
- ✅ Cobertura de casos edge y límites

### Ejemplos de Tests

```java
// Test de mutante con secuencia horizontal
@Test
public void testMutantWithHorizontalSequence() {
    String[] dna = {
        "AAAAGA",
        "CAGTGC",
        "TTATGT",
        "AGAAGG",
        "CCCCTA",
        "TCACTG"
    };
    assertTrue(MutantDetector.isMutant(dna));
}
```

---

## 📊 Ejemplos de Uso

### Usando cURL

#### Verificar ADN Mutante

```bash
curl -X POST http://localhost:8080/mutant \
  -H "Content-Type: application/json" \
  -d '{
    "dna": [
      "ATGCGA",
      "CAGTGC",
      "TTATGT",
      "AGAAGG",
      "CCCCTA",
      "TCACTG"
    ]
  }'
```

#### Obtener Estadísticas

```bash
curl -X GET http://localhost:8080/stats
```

### Usando Postman

1. Importa la colección desde `postman_collection.json` (si existe)
2. Configura el environment con la URL base
3. Ejecuta las requests de ejemplo

### Usando JavaScript (Fetch API)

```javascript
// Detectar mutante
async function detectMutant(dna) {
  const response = await fetch('http://localhost:8080/mutant', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({ dna })
  });
  
  return response.status === 200;
}

// Obtener estadísticas
async function getStats() {
  const response = await fetch('http://localhost:8080/stats');
  return await response.json();
}

// Uso
const isMutant = await detectMutant([
  "ATGCGA",
  "CAGTGC",
  "TTATGT",
  "AGAAGG",
  "CCCCTA",
  "TCACTG"
]);

console.log('Es mutante:', isMutant);
```

---

## 🐳 Deployment en Render

### Configuración de Render

1. **Crear cuenta en Render**: https://render.com

2. **Crear nuevo Web Service**:
   - Connect your GitHub repository
   - Selecciona el repositorio `Global_MutantDetectorAPI_VaranoLucia`
   - Name: `mutant-detector-api`
   - Environment: `Docker`
   - Branch: `main`

3. **Variables de Entorno** (si usas PostgreSQL):
   ```
   DATABASE_URL=your_postgresql_url
   SPRING_PROFILES_ACTIVE=prod
   ```

4. **Deploy**: Render detectará automáticamente el Dockerfile y construirá la aplicación

### URL de Producción

Una vez deployado, tu API estará disponible en:
```
https://mutant-detector-api-xxxx.onrender.com
```

---

## 🔍 Validaciones

### Reglas de Validación del ADN

1. ✅ La matriz debe ser NxN (cuadrada)
2. ✅ N debe ser mayor o igual a 4
3. ✅ Solo se permiten las bases: A, T, C, G
4. ✅ Todas las filas deben tener la misma longitud
5. ✅ No se permiten valores nulos o vacíos

### Ejemplos de ADN Inválido

```json
// Matriz no cuadrada (INVÁLIDO)
{
  "dna": [
    "ATGC",
    "CAGTGC",
    "TTAT"
  ]
}

// Caracteres inválidos (INVÁLIDO)
{
  "dna": [
    "ATXC",
    "CAGT",
    "TTAT",
    "AGAA"
  ]
}

// Tamaño menor a 4x4 (INVÁLIDO)
{
  "dna": [
    "ATG",
    "CAG",
    "TTA"
  ]
}
```

---

## 📈 Complejidad del Algoritmo

### Análisis de Rendimiento

- **Complejidad Temporal**: O(N²)
  - Se recorre la matriz una vez para detectar secuencias
  
- **Complejidad Espacial**: O(1)
  - No se requieren estructuras de datos adicionales significativas

### Optimizaciones Implementadas

- ✅ Early exit: Se detiene cuando encuentra 2 secuencias
- ✅ Validación previa de entrada
- ✅ Búsqueda eficiente en todas las direcciones

---

## 👩‍💻 Autora

**Lucía Varano**
- GitHub: [@LuciaVarano](https://github.com/LuciaVarano)
- Proyecto: Examen Global - Desarrollo de Software




