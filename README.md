# case_182_07

CÀI ĐẶT DOCKER (Chạy Web server, DB server) + ỨNG DỤNG LÊN THIẾT BỊ DI ĐỘNG (Android)
//====================================================

TRONG THƯ MỤC DATABASE:


docker build -t faceshop/mysql:v0 .

docker run  -d \
--publish 3306:3306 \
--volume=/home/[cnttvn.net]NodeJS/Git/docker/case_182_07/back-end/database/data:/var/lib/mysql \
--name=test-faceshop-mysql faceshop/mysql:v0

docker exec -it test-faceshop-mysql /bin/sh

mysql -u root -p -h 192.168.99.101 -P 3306 -D faceshop

//====================================================



//====================================================

TRONG THƯ MỤC NODEJS-API:

docker inspect test-faceshop-mysql | grep IPAddress

docker build -t faceshop/node:v0 .

docker run -it -d \
--publish 3000:3000 \
-e MYSQL_USER='root' \
-e MYSQL_PASSWORD='123' \
-e MYSQL_DATABASE='faceshop' \
-e MYSQL_HOST='172.17.0.2' \
--link test-faceshop-mysql:db \
--name=test-faceshop-nodejs faceshop/node:v0

//====================================================
