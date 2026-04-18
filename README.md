[![CI/CD Pipeline](https://github.com/xiomara-udea/banco2026v/actions/workflows/build.yml/badge.svg)](https://github.com/xiomara-udea/banco2026v/actions/workflows/build.yml)
[![SonarQube Cloud](https://sonarcloud.io/images/project_badges/sonarcloud-light.svg)](https://sonarcloud.io/summary/new_code?id=xiomara-udea_banco2026v)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=xiomara-udea_banco2026v&metric=bugs)](https://sonarcloud.io/summary/new_code?id=xiomara-udea_banco2026v)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=xiomara-udea_banco2026v&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=xiomara-udea_banco2026v)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=xiomara-udea_banco2026v&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=xiomara-udea_banco2026v)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=xiomara-udea_banco2026v&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=xiomara-udea_banco2026v)

BIENVENIDO A MI APLICATION

API REST desarrollada con Spring Boot para la gestión de operaciones bancarias.


## Tecnologías utilizadas

* Java 17
* Spring Boot
* Spring Data JPA
* MySQL (entorno de desarrollo)
* H2 Database (entorno de pruebas)
* Maven
* GitHub Actions

---


##  Configuración de entornos

El proyecto usa perfiles de Spring Boot:

* `dev` → MySQL (desarrollo)
* `test` → H2 (tests automáticos)

---

## Cómo ejecutar la aplicación

Desde la raíz del proyecto:

```bash
mvn spring-boot:run
```

La aplicación estará disponible en:

```
http://localhost:8080
```

---

## Cómo ejecutar los tests

```bash
mvn clean test
```

✔ Usa base de datos H2 en memoria
✔ No requiere MySQL

---

## Cómo construir el proyecto

```bash
mvn clean package
```

El `.jar` se genera en:

```
target/
```

---


