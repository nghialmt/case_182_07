# case_182_07

CÀI ĐẶT DOCKER (Chạy Web server, DB server) + ỨNG DỤNG LÊN THIẾT BỊ DI ĐỘNG (Android)
//====================================================

TRONG THƯ MỤC DATABASE:

------------- Build một image từ container
docker build -t faceshop/mysql:v0 .

------------- Tạo mới một container, đồng thời khởi động với tùy chọn cổng và volume
------------- Docker PUBLISH (3306)trong docker container dùng để mở cổng và tất cả các ứng dụng khác từ host có thể truy cập khi đó bạn sử dụng.
docker run  -d \
--publish 3306:3306 \
--volume=/home/[cnttvn.net]NodeJS/Git/docker/case_182_07/back-end/database/data:/var/lib/mysql \
--name=test-faceshop-mysql faceshop/mysql:v0

------------- Khởi động một container 
docker exec -it test-faceshop-mysql /bin/sh

mysql -u root -p -h 192.168.99.101 -P 3306 -D faceshop

//====================================================



//====================================================

TRONG THƯ MỤC NODEJS-API:
---------------
Làm thế nào để biết MYSQL_HOST đang sử dụng địa chỉ IP 172.17.0.2 làm MYSQL_HOST. 
Sử dụng docker kiểm tra test-mysql-microservice | grep IPAddress
---------------
thực thi lệnh : docker inspect test-faceshop-mysql | grep IPAddress
lúc đó sẽ có địa chỉ (172.17.0.2) xuất hiện


docker build -t faceshop/node:v0 .
----------------
d chạy trong detach mode
--publish ánh xạ cổng máy chủ 3000 sang cổng container 3000
-e chuyển các biến môi trường cho ứng dụng nodejs cần thiết để tạo kết nối mysql (kiểm tra tệp app.js)
-----------------
docker run -it -d \
--publish 3000:3000 \
-e MYSQL_USER='root' \
-e MYSQL_PASSWORD='123' \
-e MYSQL_DATABASE='faceshop' \
-e MYSQL_HOST='172.17.0.2' \
--link test-faceshop-mysql:db \
--name=test-faceshop-nodejs faceshop/node:v0

//====================================================

Sau đó kiểm tra docker ps xem liệu có 2 container là test-faceshop-mysql và test-faceshop-nodejs có đang chạy hay không.
Nếu cả 2 đã chạy thành công thì lúc đó ứng dụng android có thể vận hành được.


