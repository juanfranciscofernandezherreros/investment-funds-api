# Investment Funds API

Microservicio Spring Boot para gestionar fondos de inversión. El contrato HTTP es API-first: [openapi.yaml](src/main/resources/static/openapi.yaml) es la fuente de verdad y OpenAPI Generator genera la interfaz de servidor que implementa el controlador.

## Requisitos proporcionados

- Java 21, Spring Boot 4, Maven Wrapper, SQL Server, JPA y Flyway.
- API bajo `/api/v1/funds`.
- Operaciones de creación, consulta, búsqueda paginada, actualización parcial y borrado físico.
- Filtros de búsqueda: `isin`, `name`, `managementCompany`, `category`, `currency`, `riskLevel` y `active`.
- `isin` único y `riskLevel` entre 1 y 7 cuando esté informado.

## Convenciones heredadas

- Flyway es dueño del esquema y Hibernate lo valida.
- La base local usa SQL Server mediante Docker Compose y volumen persistente.
- La configuración de conexión se externaliza con `DB_URL`, `DB_USERNAME` y `DB_PASSWORD`.
- Spotless y JaCoCo forman parte de los controles de calidad; JaCoCo exige al menos 80 % de líneas.

## Decisiones de implementación

- La migración inicial crea `investment_funds` con `BIGINT IDENTITY`, `DATETIMEOFFSET`, restricción única de ISIN y `CHECK` de riesgo.
- OpenAPI Generator crea `FundsApi` y sus modelos durante `generate-sources`; `FundController` implementa esa interfaz generada.
- No se calculan rentabilidades, comisiones ni se emiten recomendaciones de inversión.

## Compilar y ejecutar

```powershell
.\mvnw.cmd clean package
.\mvnw.cmd spring-boot:run
```

Variables de entorno:

```text
DB_URL=jdbc:sqlserver://localhost:1433;databaseName=investment_funds;encrypt=true;trustServerCertificate=true
DB_USERNAME=sa
DB_PASSWORD=<secret>
```

## Docker Compose

```powershell
docker compose up -d --build
docker compose ps
```

El stack inicia SQL Server, crea la base local `investment_funds`, ejecuta Flyway desde la aplicación y conserva los datos en el volumen `investment-funds-sqlserver-data`.

## Endpoints

| Método | Ruta | Resultado |
| --- | --- | --- |
| POST | `/api/v1/funds` | Crea un fondo (`201`) |
| GET | `/api/v1/funds/{id}` | Obtiene un fondo (`200` / `404`) |
| GET | `/api/v1/funds` | Busca fondos paginados (`200`) |
| PATCH | `/api/v1/funds/{id}` | Actualiza campos presentes (`200` / `404` / `409`) |
| DELETE | `/api/v1/funds/{id}` | Borra físicamente (`204` / `404`) |

Ejemplo de creación:

```bash
curl -X POST http://localhost:8080/api/v1/funds \
  -H "Content-Type: application/json" \
  -d '{"isin":"ES0000000001","name":"Fondo ejemplo","currency":"EUR","active":true}'
```

Ejemplo de búsqueda:

```bash
curl "http://localhost:8080/api/v1/funds?currency=EUR&page=0&size=20"
```

## Pruebas e informes

```powershell
.\mvnw.cmd verify
```

Las pruebas incluyen unitarias de servicio, MVC y escenarios Cucumber de los casos válidos e inválidos de todos los endpoints. Cucumber muestra cada request y response en consola, y deja estos informes tras las pruebas:

- `target/cucumber/cucumber.html`
- `target/cucumber/cucumber.json`
- `target/site/jacoco/index.html`

La configuración JaCoCo exige al menos 80 % de líneas; la última ejecución de `verify` superó el umbral.
