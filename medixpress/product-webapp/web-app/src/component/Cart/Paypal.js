import React, { useEffect, useState, useContext } from 'react';
import ReactDOM from "react-dom";
import { useNavigate } from "react-router-dom";
import { ConfigContext } from "../../Context/ConfigContext";

import axios from "axios";
const Paypal = (props) => {
  const { url,endPoint, apiRoutes} = useContext(ConfigContext);
  const absoluteUrl=url+apiRoutes.profile.port+endPoint+apiRoutes.profile.controller+apiRoutes.profile.method;
  const navigate = useNavigate();
    const { finalPayment,gotoThanks } = props
    const [role,setRole]=useState(null);
    const initialValues = {name:"",contactNo:"",gender:''};
    // const field={};
    const [formValues, setFormValues] = useState(initialValues);
    const initialAddressValues = {streetName:"",doorNumber:"",landmark:'',state:'',city:"",pinCode:''};
    const [adressFormValues, setAdressFormValues] = useState(initialAddressValues);

    useEffect(() => {
      const loggedInUser = localStorage.getItem('email');
      let role = localStorage.getItem('role');
      setRole(role);
      if (!loggedInUser || loggedInUser == null) {
        navigate('../login',{ replace: true });
       }
       axios.get(`${absoluteUrl}/${loggedInUser}`).then((response)=>{
        // console.log(response);
        if(response.status == 200){
          // setDetail(response.data);
          // setFormValues(response.data);
          formValues.name=response.data.name;
          formValues.contactNo=response.data.contactNo;
          formValues.gender=response.data.gender;
          formValues.emailId=response.data.emailId;
          console.log(response.data);
          setAdressFormValues(response.data.address[0]);
  
        }
  },[]);
    },[]);
        function openCheckout() {
          let options = {
            "key": "rzp_test_exrYYk5YyMUBpS",
            "amount": (finalPayment*100), // 2000 paise = INR 20, amount in paisa
            "name": "MediXpress",
            "description": "Purchase Descriptio",
            "image": '',
            "handler": function (response){
                callPaymentSuccessApI(response)
                gotoThanks()
            },
            "prefill": {
              "name": formValues.name,
              "email": formValues.emailId
            },
            "notes": {
              "address": `H.No. ${adressFormValues.doorNumber} ${adressFormValues.streetName}, ${adressFormValues.landmark}, ${adressFormValues.city}, ${adressFormValues.state}, ${adressFormValues.pinCode}
              `
            },
            "theme": {
              "color": "#7095d4"
            }
          };
      
          let rzp = new window.Razorpay(options);
          rzp.open();
        }



        function callPaymentSuccessApI(response) {
          console.log("Inside payment success",response)
          //to hit payment success api with details
        }
      
          return (
            <div>
                <div className="rzrp" onClick={() =>openCheckout()}>{}Pay with Razorpay</div>
             
                <img alt="" src={require(`../../assets/Images/razorpay-who.png`)} className="img"/>
            </div>
          )




    };

export default Paypal;
