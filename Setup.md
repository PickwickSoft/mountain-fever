# Getting Started
This is the setup manual
### PostgreSQL DB Setup
To setup the DB run
```shell
docker create --name mountain-fever -e POSTGRES_PASSWORD=admin -p 5432:5432 postgres:11.5-alpine
```
Start container:
```shell
docker start mountain-fever
```
Stop container:
```shell
docker start mountain-fever
```
Connection Info:
```shell
JDBC URL: `jdbc:postgresql://localhost:5432/mountain_fever`

Username: `postgres`

Password: `admin`
```
Connect to console from Terminal:
```shell
docker exec -it mountain-fever psql -U postgres
```
Create the Database:
```postgresql
create database mountain_fever;
```
Exit with <kbd>Ctrl</kbd> + <kbd>D</kbd>

Setup the Tables:
```shell
docker cp db/create_tables.sql mountain-fever:/create_tables.sql
docker exec -it mountain-fever psql -d conference_app -f create_tables.sql -U postgres
```
_OPTIONAL_: Install the Data
```shell
docker cp db/insert_data.sql mountain-fever:/insert_data.sql
docker exec -it mountain-fever psql -d conference_app -f insert_data.sql -U postgres
```