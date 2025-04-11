# 🚀 Tenpo Desafío - Api Rest en Spring Boot

Este proyecto es un microservicio desarrollado con Spring Boot 3 y Java 21 como parte del desafío técnico de Tenpo.

Funcionalidades principales:

- Un endpoint que reciba num1 y num2, los sume y aplique un porcentaje
adicional obtenido de un servicio externo (puede ser un mock con valor
fijo).
- Caché del porcentaje: El porcentaje obtenido debe almacenarse en memoria durante 30
minutos. Si el servicio externo falla, se usa el último valor almacenado; si no hay,
se devuelve un error.
- Historial de llamadas: Un endpoint que devuelva el historial de llamadas (fecha, endpoint,
parámetros, respuesta o error).
El registro debe ser asíncrono para no afectar el rendimiento.

---

## 🛠️ Requisitos previos

- Java 21
- Gradle 8+ (o usar `./gradlew`)
- Docker Compose

---

## ▶️ Instalación y ejecución en local

1. Clonar el repositorio:

```bash
git clone git@github.com:angelcrisos/desafiotenpo.git
cd desafiotenpo
```

---

## 🧪 Ejecutar tests

```bash
./gradlew test
```

---

## 📦 Build del JAR ejecutable

Generar el JAR completo:

```bash
./gradlew clean bootJar
```

El archivo se generará en:  
`build/libs/desafio-0.0.1-SNAPSHOT.jar`

---


## 🧩 Docker Compose

Correr docker-compose:

```bash
docker-compose up
```

Esto levantará el servicio backend y base de datos.

---

## 📑 Documentación API

- Swagger UI:  
  [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

- OpenAPI JSON:  
  [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

---

## ⚙️ Configuración

La configuración principal se encuentra en:

```
src/main/resources/application.yml
```

---

## 🧑‍💻 Autor

Desarrollado por Angel Crisóstomo.

---