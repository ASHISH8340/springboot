import React,{ useState, useEffect, useContext} from "react";
import * as bs from "bootstrap/dist/css/bootstrap.css";
import { Link, useParams } from "react-router-dom";
import { useNavigate, useLocation } from "react-router-dom";
import './style.css'
import Sidebar from "../Sidebar/Sidebar";
import { HomeContext } from "../../Context/ConfigContext";

function Header() {
  const {category}=useParams();
  const [authenticated, setAuthenticated] = useState(null);
  const [userPincode,setUserPincode]=useContext(HomeContext);
  const location = useLocation();
  const navigate = useNavigate();
 const handleLogout=(event)=>{
      localStorage.removeItem('email');
      localStorage.removeItem('role');
      localStorage.removeItem('pincode');
      setAuthenticated(null);
      navigate('/login');
  }
   useEffect(() => {
    const loggedInUser = localStorage.getItem('email');
    let pincode = localStorage.getItem('pincode');
    if(pincode && pincode!=undefined){
      setUserPincode(pincode);
    }
    console.log(loggedInUser);
    if (loggedInUser) {
      setAuthenticated(loggedInUser);
    }
  });
  const handlePincode = (e)=>{
    let pincode = e.target.value;
    localStorage.setItem('pincode',pincode);
    setUserPincode(pincode);
  }

  return (
    <>
    <div className="headerBar">
      <nav className="navbar navbar-expand-lg">
      <Link to='/' className="navbar-brand"><img class="brand-logo" src={process.env.PUBLIC_URL+"/assets/images/logo.png"}/></Link>
        <button
          className="navbar-toggler me-3"
          type="button"
          data-toggle="collapse"
          data-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent"
          aria-expanded="false"
          aria-label="Toggle navigation" >
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse text-center" id="navbarSupportedContent">
          <div className="navbar-nav ms-auto me-5">
            {
              (!authenticated || authenticated ==null)&&
            <><Link to='/login' className="nav-item nav-link btn bg-light mr-3">Login</Link>
            <Link to='/signup' className="nav-item nav-link btn bg-light mr-5">SignUp</Link></>
            }
            {
              authenticated &&

            
            <a className="nav-item dropdown mr-5">
            <a className="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <img src={localStorage.getItem('image')?"data:image/png;base64,"+localStorage.getItem('image'):process.env.PUBLIC_URL+"/assets/images/profile.jpg"} width="40" height="40" class="rounded-circle"/>
        </a>
        <div className="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
          {/* <a class="dropdown-item" href="#"></a> */}
          <Link to="/profile" className="dropdown-item"><i className="fa fa-user"></i>  Profile</Link>
         {

          localStorage.getItem('role') == 'BUYER' &&
           <><Link to="/orders" className="dropdown-item"><i className="fa fa-shopping-basket"></i>  Orders</Link>
           <Link to="/cart" className="dropdown-item"><i className="fa fa-shopping-cart"></i>  Cart</Link>
           </>
         }

          <a href="javascript:void(0)" onClick={handleLogout} className="dropdown-item"><i className="fa fa-sign-out"></i>Log Out</a>
        </div>
            </a>
} 
          </div>
        </div>
      </nav>
      {
        location.pathname != 'profile' &&
        <div className="top-menu">
          <div className="pincode-area ml-5">
          <input className="form-control pincode-input" type="text" value={userPincode} onChange={handlePincode} title="expolore near by shops" placeholder="Enter Pincode"></input>
          <i class="fa fa-search search-btn" aria-hidden="true"></i>
          </div>
        <ul className="menu-list mx-auto">
          <li className= {"menu-list-item"}><Link to="/medicines/covid"><i class="mr-2"></i>Covid Essential</Link></li> 
          <li className="menu-list-item"><Link to="/shops"><i className="fa fa-map-marker mr-2"></i>Near By Shops</Link></li>
          <li className="menu-list-item"><Link to="/medicines/ayurvedic"><i class="fa fa-leaf mr-2"></i>Ayurvedic</Link></li>
          <li className="menu-list-item"><Link to="/medicines/homeopathy"><i class="fa fa-medicine mr-2"></i>Homeopathy</Link></li>
          <li className="menu-list-item">
          <div className="dropdown show">
          <a className="dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fa fa-list mr-2"></i> Other Medicines
          </a>
          <div className="dropdown-menu" aria-labelledby="dropdownMenuLink">
          <Link className="dropdown-item" to="/medicines/syrup">Syrup</Link>
          <Link className="dropdown-item" to="/medicines/capsules">Capsules</Link>
          <Link className="dropdown-item" to="/medicines/babyCare">Baby Care</Link>
          <Link className="dropdown-item" to="/medicines/hairCare">Hair Care</Link>
          <Link className="dropdown-item" to="/medicines/eyeCare">Eye Care</Link>
          <Link className="dropdown-item" to="/medicines/diabetes">Diabetes</Link>
          <Link className="dropdown-item" to="/medicines/dailySupplements">Daily Supplements</Link>
          <Link className="dropdown-item" to="/medicines/foodSupplements">Food supplemets</Link>
          </div>
        </div>
          </li>
        </ul>

      </div>
      }

    </div>
    {/* <Sidebar/> */}
    </>
  );
}
export default Header;
