build:
	mvn -DskipTests=true package
migrate:
	sqlite3 universe.db < src/main/resources/migrations/2023_02_20_000000_create_tables.sql
run:
	java -jar target/universe.jar application.properties
