import React from "react";
import OuterWrapper from "../component/Cart/OuterWrapper";
import Homepage from "../components/Homepage/Homepage";
import Login from "../components/Login/Login";
import Logout from "../components/Login/Logout";
import Detail from "../components/Product/Detail";
import Profile from "../components/Profile/Profile";
import Signup from "../components/Signup/Signup";
 import Card  from "../Product-List-Page/Card";
  import AddingMedicineList from "../Add-Medicine-Page/AddingMedicineList";
import ViewShopList from '../viewShopList/ViewShopList';
import AddingShopList from '../AddingShop/AddingShopList';
import Error from "../components/Errors/Error";
import NotAllowed from "../components/Errors/NotAllowed";
import OuterCart from "../component/Cart/OuterWrapper";
import CategoryProducts from "../components/Product/CategoryProducts";

import OrderList from "../OrderList/OrderList";


const MyRoutes=[
        {
            path:'/login',
            component:<><Login/></>
        },
        {
            path:'/signup',
            component:<><Signup/></>
        },   
        {
            path:'/logout',
            component:<><Logout/></>
        },
         {
            path:'/profile',
            component:<><Profile/></>
         },
         {
            path:'/medicines',
         component:<><Card userRole={localStorage.getItem("role")} /></>
        //  component:<><Card userRole="SELLER" /></>
        // component:<><Card userRole="SELLER" /></>
        },
         {
            path:'/medicines/addmedicine',
            component:<><AddingMedicineList /></>
        },
        {
            path:'/',
            component:<><Homepage/></>
        },
        {
            path:'/shops',
            component:<><ViewShopList role={localStorage.getItem('role')}/></>
        },
        {
            path:'/addshop',
            component:<><AddingShopList /></>
        },
        {
            path:'/orders',
            component:<><OrderList /></>
        },
        {
            path:'/cart',
            component:<><OuterCart/></>
        },
        {
            path:'/error',
            component:<><NotAllowed/></>
        },
        {
            path:'*',
            component:<><Error/></>
         
        },
        {
            path:'/Cart',
            component:<><OuterWrapper/></>
        },
        {
            path:'/detail/:id',
            component:<><Detail/></>
        },
        {
            path:'/medicines/:category',
            component:<><CategoryProducts userRole={localStorage.getItem('role')}/></>
        }
    ];
export default MyRoutes;