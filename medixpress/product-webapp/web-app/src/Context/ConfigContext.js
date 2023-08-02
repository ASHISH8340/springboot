import React, { createContext, useState } from 'react'
export const ConfigContext = createContext()
export const ConfigContextProvider = (props) => {
    // const [variableOne, setVariableOne] = useState('somethingRandom')
    const [userPincode, setUserPincode] = useState('');

    // const url = "http://localhost:8080"
    const url = "https://medixpress.stackroute.io"
    const endPoint = '/api/v1'
    const apiRoutes = {
        "login":{
            "controller":"/user",
            "method":"/sign-in",
            "port":"/authentication-service"
        },
        "signup":{
            "controller":"/user",
            "method":"/addUser",
            "port":"/user-service"
        },
        "profile":{
            "controller":'/user',
            "method":"/getByEmail",
            "port":"/user-service"
        },
        "fetchShop":{
            "controller":'',
            "method":"/getBycontactEmail",
            "port":"/shop-service"
        },
        "updateProfile":{
            "controller":'/user',
            "method":"/updateUserByEmail",
            "port":"/user-service"
        },
        "updateAddress":{
            "controller":'/user',
            "method":"/updateAddressByEmail",
            "port":"/user-service"
        },
        "updateImage":{
            "controller":'/user',
            "method":"/addUserImageByMail",
            "port":"/user-service"
        },
        "detail":{
            "controller":'',
            "method":"/getMedicineById",
            "port":"/medicine-service"
        },
        "glogin":{
            "controller":'/user',
            "method":"/authByGoogle",
            "port":"/user-service"
        },
       
        "productByCategory":{
            "controller":'',
            "method":"/getMedicineBymedicineCatagory",
            "port":"/medicine-service"
        },
        "limitedDeal":{
            "controller":'',
            "method":"/getwithMaxDiscount",
            "port":"/medicine-service"
        },
        "nearByShop":{
            "controller":'',
            "method":"/getbypincode",
            "port":"/shop-service"
        },
        "allByShop":{
            "controller":'',
            "method":"/getallshop",
            "port":"/shop-service"
        },
        "medicines":{
            "controller":'',
            "method":"/getallMedicine",
            "port":"/medicine-service"
        },
        "shopmedicines":{
            "controller":'',
            "method":"/getMedicineByshopEmail",
            "port":"/medicine-service"
        },
        "deletemedicine":{
            "controller":'',
            "method":"/deleteMedicine",
            "port":"/medicine-service"
        },
        "addmedicine":{
            "controller":'',
            "method":"/addMedicines",
            "port":"/medicine-service"
        },
        "addimage":{
            "controller":'',
            "method":"/addimages",
            "port":"/medicine-service"
        },
       
         // krishna UI services here
         "orderList":{
            "controller":'',
            "method":"/order",
            "port":"/order-service"

        },
        "newArrivals":{
            "controller":'',
            "method":"/getwithLatestArrival",
            "port":"/medicine-service"
        },
        "addToCart":{
            "controller":'',
            "method":"/cart/Email",
            "port":"/order-service"
        },
        "addedToCart":{
            "controller":'',
            "method":"/cart/Email",
            "port":"/order-service"
        },
        "deleteFromCart":{
            "controller":'',
            "method":"/cart/MedId",
            "port":"/order-service"
        }
    };
    return (
         <ConfigContext.Provider 
            value={{
                url,
                endPoint,
                apiRoutes,
             }        
            }>
               {props.children}
         </ConfigContext.Provider>
    )
}
export const HomeContext = createContext();
export const HomeContextProvider = (props) => {
    const [userPincode, setUserPincode]=useState(null);
    return (
         <HomeContext.Provider 
            value={
                [userPincode, setUserPincode]
            }>
               {props.children}
         </HomeContext.Provider>
    )
}