import React from "react";
// import logo from "./logo.svg";
import $ from 'jquery';
// import "./App.css";
// import Header from "./components/Header";
import Login from "./Login/Login";
import Signup from "./Signup/Signup";
import 'font-awesome/css/font-awesome.min.css';
import {BrowserRouter as Router,Route,Routes} from 'react-router-dom';
import Detail from "./Product/Detail";
// import Homepage from "./components/Homepage/Homepage";
function MyComponent() {
  return (
    <Router>
    <div className="main">
      {/* <Header/> */}
        <div className="content">
          <Routes>
            {/* <Route path="/">
            <Login/>
            </Route> */}
            <Route path="/login" element= {<Login/>}/>
            <Route path="/signup" element= {<Signup/> }/>
            <Route path="/detail" element={<Detail/>}/>
            {/* <Route path="/" element={<Homepage/>}/> */}
          </Routes>
        </div>
      </div>
      {/* <ViewShopList role={"user"}></ViewShopList> */}

    </Router>

  );
}

export default MyComponent;
