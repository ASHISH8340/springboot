import React, { useEffect, useState } from "react";
import { Link,useNavigate } from "react-router-dom";
import "./style.css";

function Sidebar(props){
    const [role,setRole] = useState(null);
    const navigate = useNavigate();
    useEffect(()=>{
        let role = localStorage.getItem('role');
        if(role!=undefined && role){
            setRole(role);
        }
    });
    const handleLogout=(event)=>{
        localStorage.removeItem('email');
        localStorage.removeItem('role');
        localStorage.removeItem('pincode');
        navigate('/login');
    }
    return (
        <div className="sidebar mt-5">


    <section class="profile">
        <div class="img-container">
            <img src={localStorage.getItem('image') && `data:image/png;base64,${localStorage.getItem('image')}` || process.env.PUBLIC_URL+"/assets/images/profile.jpg"}alt=""/>
        </div>
        <div class="profile-body">
            <p class="name">{props.name}</p>
                
       <section class="menu">
        <Link to='/profile' className="menu-item"><i class="fas fa-user mr-3"></i>Profile</Link>
        {
            role == 'SELLER' &&
            <>
                    <Link to='/medicines' className="menu-item"><i class="fas fa-list-alt mr-3"></i>Products</Link>
                    <Link to='/shops' className="menu-item"><i class="fas fa-list-alt mr-3"></i>Shops</Link>

            </>
        }
        {
            role=='BUYER' &&
            <>
                        <Link to='/orders' className="menu-item"><i class="fas fa-list-alt mr-3"></i>Orders</Link>

            </>
        }
        <a href="javascript:void(0)" onClick={handleLogout} className="menu-item"><i class="fas fa-sign-out-alt mr-3"></i>Logout</a>
    </section>
            </div>
    
    </section>



        </div>
    )
}
export default Sidebar;