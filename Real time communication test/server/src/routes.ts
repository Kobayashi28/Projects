import express from 'express';

const routes = express.Router();

routes.get("/", (req, res)=>{
    res.send('nothing here');
})

routes.get("/users", (req,res)=>{
    res.json({
        "a":{
            "name":'user-a',
            "age":14,
            "isGraduated":false
        },
        "b":{
            "name":'user-b',
            "age":14,
            "isGraduated":false
        },
        "c":{
            "name":'user-c',
            "age":14,
            "isGraduated":false
        },
        "d":{
            "name":'user-d',
            "age":14,
            "isGraduated":false
        }
    });
});

routes.get("/products", (req,res)=>{

    res.json({
        1:{
            "nome":"teclado",
            "preço":44.90,
            "estoque":10,
            "disponível":true,
        },
        2:{
            "nome":"monitor",
            "preço":789.90,
            "estoque":23,
            "disponível":true,
        },
        3:{
            "nome":"placa-mãe",
            "preço":356.90,
            "estoque":12,
            "disponível":false,
        }
    });

});

routes.post("/users", (req,res)=>{
    const {username, password}  = req.body    
    console.log(username);

    if(username != undefined && password != undefined){
        res.json({"success":true});
    }else{
        res.json({"success":false});
    }
});

export default routes;