var config = {
    database: {
        host: process.env.MYSQL_HOST ||'172.17.0.2',
        user: process.env.MYSQL_USER ||'root',
        password: process.env.MYSQL_PASSWORD ||'123',
        port: '3306',
        db: process.env.MYSQL_DATABASE ||'faceshop'
    }
}

module.exports = config