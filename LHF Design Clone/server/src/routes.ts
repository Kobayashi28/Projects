import express from 'express';

import conn from './database/conn';

const routes = express.Router();

routes.post('/user/create', (req,res)=>{
    var {username, address, cep, city, state, country, email, complement, frontDoc, backDoc} = req.body;

    frontDoc = frontDoc.split('\\')[frontDoc.split('\\').length-1];
    backDoc = backDoc.split('\\')[backDoc.split('\\').length-1];
    
    conn.connect();

    var sqlQuery = "insert into userInfo(username, address, cep, city, state, country, email, complement, frontDocument, backDocument) values ('"+username+"','"+address+"',"+cep+",'"+city+"','"+state+"','"+country+"','"+email+"','"+complement+"','"+frontDoc+"','"+backDoc+"')";
    
    var success = false;

    conn.query(sqlQuery,(error, results)=>{
        if(error)throw error
        else success = true;
    });
    
    conn.end();

   res.json({success}) ;
})

routes.post('/user/auth',(req,res)=>{
    

});

export default routes;