# local 개발
docker run -p 5432:5432 --name study-db -e POSTGRES_USER=study -e POSTGRES_PASSWORD=study -e POSTGRES_DB=study -d postgres
# test db
# docker run -p 5433:5432 --name study-testdb -e POSTGRES_USER=studytest -e POSTGRES_PASSWORD=studytest -e POSTGRES_DB=studytest -d postgres
