var net = require('net');

var HOST = '127.0.0.1'; 
var PORT = 6969; 

net.createServer((sock: any) => {

    console.log('CONNECTED: ' + sock.remoteAddress +':'+ sock.remotePort);

    sock.on('data', (data: string) => {
        console.log(data);
        sock.write(data);
    });

    sock.on('close', (data: any) => {
        console.log('CLOSED: ' + sock.remoteAddress +' '+ sock.remotePort);
    });

}).listen(PORT, HOST);

console.log('Server listening on ' + HOST +':'+ PORT);