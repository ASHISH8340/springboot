// import { Router } from "react-router-dom";
import React from "react";
import MyRouter from "./MyRoutes";
import {HashRouter as Router, Routes,Route} from 'react-router-dom';
import {ConfigContextProvider, HomeContextProvider } from "../Context/ConfigContext";
function RouterApp(){
return(
    <ConfigContextProvider>
        <HomeContextProvider>
     <Router>
    <Routes>
{
    MyRouter.map((item,index)=>{
        return <Route path={item.path} element={item.component} key={index}/>
    }
    )
}
    </Routes>
</Router>
</HomeContextProvider>
</ConfigContextProvider>
)}
export default RouterApp;