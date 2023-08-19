import mysql from 'mysql';

const conn = mysql.createConnection({
    host:'127.0.0.1',
    user:'root',
    password:'12345678',
    database:'lhf'
})

export default conn;