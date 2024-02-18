# Doctor API

## Instructions

To run locally, use `mvn spring-boot:run` (I used port `6099`).

To run in Docker, use `docker run --publish 6099:6099 dermtest-docker`.

## Docs

- Docs can be seen at [http://localhost:6099/swagger-ui/index.html](http://localhost:6099/swagger-ui/index.html).

## Tasks

### Configuration

1. Add file-based database
- I used SQLite framework. This saves data to file-based database (DoctorDB).
- I have to say, this took me some time to figure out, as I have never worked with this framework before.
2. Add Lombok
- Added it to POM and used it on models.
3. Create runnable Docker image
- Copied this from some old project and was good to go.
4. Add OpenApi documentation
- Added `springdoc-openapi-ui` to POM and it was good to go.
5. Add log4j
- Added `log4j` to POM, created property file and added some logging to `DoctorController`

### Development - each task includes developing one unit or integration test.
1. Create data model (i.e doctor or nurse)
- Created a doctor data model with following variables:
  - `id`
  - `firstName`
  - `lastName`
  - `age`
  - `refCode`
2. Create one of CRUD based controller with input/output JSON (i.e use your created data model)
- Created `DoctorController` that has various endpoints to add, edit, delete and list doctor data.
- Took some time to create and test that everything works.
3. Create one JPA repository for connecting to database
- Created `DoctorRepository`