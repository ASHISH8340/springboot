import React,{ useEffect } from "react";
import { useNavigate } from "react-router-dom";
function Logout(){
    const navigate = useNavigate();
    useEffect(()=>{
        localStorage.removeItem('email');
        localStorage.removeItem('role');
        localStorage.removeItem('pincode');
        navigate('/login');
    })
    return(
        <></>
    );
}
export default Logout;