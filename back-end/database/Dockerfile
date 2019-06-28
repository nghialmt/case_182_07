FROM mysql:5.7

ENV MYSQL_DATABASE=faceshop \
    MYSQL_ROOT_PASSWORD=123

EXPOSE 3306

COPY ./faceshop.sql /docker-entrypoint-initdb.d/