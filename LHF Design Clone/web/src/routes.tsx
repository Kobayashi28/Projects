import react from 'react';

import { BrowserRouter, Routes, Route } from 'react-router-dom';

import Home from './pages/Home';
import Login from './pages/Login';
import Dashboard from './pages/Dashboard';

const Rotas = ()=>{
    return(
        <BrowserRouter>
            <Routes>
                <Route element={<Home/>} path="/"/>
                <Route element={<Login/>} path="login" />
                <Route element={<Dashboard/>} path="dashboard"/>
            </Routes>
        </BrowserRouter>
    );
}

export default Rotas;