import React from "react";
import './App.css';
import RouterApp from "./router/RouterApp";
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
// import 'bootstrap/dist/css/bootstrap.min.css'
// import 'jquery/dist/jquery.slim.js'
// import 'bootstrap/dist/js/bootstrap.min.js'

function App() {
  return (
    <>
    {/* <div className="App">
      <Header />

      <OuterCart />
      <outerOrder/>
   <Header />
     <ViewShopList role={"user"}></ViewShopList>
    <AddingShopList/>
    <Card></Card>
    </div> */}
    {/* <MyComponent /> */}
 
    <RouterApp/>
    <ToastContainer
    position="top-right"
    autoClose={5000}
    hideProgressBar={false}
    newestOnTop={false}
    closeOnClick
    rtl={false}
    pauseOnFocusLoss
    pauseOnHover
    />
    </>

    
  );
}

export default App;
