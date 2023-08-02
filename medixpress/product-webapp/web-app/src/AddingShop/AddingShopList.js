import React, { useEffect, useState, useContext } from 'react';
import {Container, Button} from 'react-bootstrap';
import { BiLogIn, BiLogOut } from "react-icons/bi";
import './AddingShop.css'
import ShopInformation from './ShopInformation'
import ShopOwnerInformation from './ShopOwnerInformation'
import ShopLocationInformation from './ShopLocationInformation'
import ConfirmationShopInfo from './ConfirmationShopInfo';
import { Link, useNavigate } from "react-router-dom";
import axios, * as others from 'axios'; 
import Header from '../components/Header/Header';
import StepperHeader from '../StepperHeader/StepperHeader';
import { ConfigContext } from "../Context/ConfigContext";
import {toast } from 'react-toastify';

function AddingShopList() {
  const navigate = useNavigate();
  const { url} = useContext(ConfigContext);
	const [shop, setShop] = useState({
    shopName: "",
    shopVerification: false,
    ShopOpeningTime:"",
    ShopCloseTime:"",
    shopOwnerName: "",
    contactNumber: "",
    contactEmail: localStorage.getItem("email"),
    shopEmail: "",
    streetName : "",
    shopNumber : "",
    landmark : "",
    city: "",
    state: "",
    pincode:"",

  });

  const [shopImage, setShopImage] = useState({});

  // const [shopVerification, setShopVerification] = useState(false);


	const [step, setStep] = useState(1);

	const nextStep = () => {
    if (step < 4) {
      setStep(step + 1);
    } else if(step === 4) {
        axios.post(`${url}/shop-service/api/v1/add`,
        shop,
      ).then(() => {
        toast.success("Shop added successfully!");
        navigate('/shops');
      }).catch((err) => {
        console.log(err);
      })
    }
  };

  console.log(step);

	const prevStep = () => {
    if (step > 1) {
      setStep(step - 1);
    }
  };

	// const handleChange = (name) => (e) => {
  //   setShop({ ...shop, [name]: e.target.value });
  // }

  const handleChange = (name) => (e) => {
    const target = e.target;
    const value = target.type === 'checkbox' ? target.checked : target.value;
    console.log(value);
    const name = target.name;
    setShop({ ...shop, [name]: value });
  }

  // const handleChange = (name) => (e) => {
  //   setShop({...shop.shopImage, [name == "shopImage"]: e.target.files[0] });
  // };

  // const handleChange = (name) => (e) => {
  //   setShopName(e.target.value)
  // };

  const imageSelectorHandler = (name) => (e) => {
    setShopImage(e.target.files[0])
  }

  // const handleChecked = (name) => (e) => {
  //   setShop({...shop, [name === shopVerification]:e.target.checked})
  // }

  // console.log(shopverified);
  console.log(shop);
  console.log(shopImage);


    return (
			<>
      <Header></Header>
      <StepperHeader step={step}></StepperHeader>
      <div className='addShop-sec'>
        {
          {
            1: <ShopInformation 
                  shop={shop}
                  handleChange={handleChange} 
                  // imageSelectorHandler={imageSelectorHandler} 
                  // handleChecked={handleChecked}
                />,
            2: <ShopOwnerInformation handleChange={handleChange} />,
            3: <ShopLocationInformation handleChange={handleChange} />,
            4: <ConfirmationShopInfo 
                  setShop={setShop}
                  shop={shop} 
                  // shopVerification = {shopVerification}
                  // setShopVerification ={setShopVerification}
                  shopImage={shopImage}
                  setShopImage={setShopImage}
                />
          }[step]
        }
      </div>  
        
      <div className="d-flex justify-content-around px-5  addingShop-wrap">
        {step > 1 ? (
          // <button className="btn btn-warning" onClick={prevStep}>
          //   Back
          // </button>
          <Button variant="primary" type="submit" className="backbtn" onClick={prevStep}>
            <BiLogOut className=""></BiLogOut> Back
          </Button>
        ) : null}
        <Button variant="primary" className="btn submitbtn" onClick={nextStep}>
          <BiLogIn className=""></BiLogIn>  {step === 4 ? " Confirm " : "Next"}
        </Button>
      </div>
				
			</>
    );
  }
  
  export default AddingShopList;