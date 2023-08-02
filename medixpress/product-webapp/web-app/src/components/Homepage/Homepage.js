import React, { Component, useEffect, useState, useContext } from "react";
import Header from "../Header/Header";
import SlickSlider from "./TopSlider";
import "./style.css";
import axios from "axios";

import TopSlider from "./TopSlider";
import BestSellingSlider from "./BestSellingSlider";
import Footer from "../Footer/Footer";
import { Link, useNavigate } from "react-router-dom";
import MedicineList from "./MedicineList";
import Shops from "./Shops";
import Sidebar from "../Sidebar/Sidebar";
import {ConfigContext, HomeContext } from "../../Context/ConfigContext";
function Homepage() {
  const { url,endPoint, apiRoutes} = useContext(ConfigContext);
  const absoluteUrl=url+apiRoutes.limitedDeal.port+endPoint+apiRoutes.limitedDeal.controller+apiRoutes.limitedDeal.method;
  const nearByShopUrl=url+apiRoutes.nearByShop.port+endPoint+apiRoutes.nearByShop.controller+apiRoutes.nearByShop.method;
  const allByShopUrl=url+apiRoutes.allByShop.port+endPoint+apiRoutes.allByShop.controller+apiRoutes.allByShop.method;
  const newArrivalsUrl=url+apiRoutes.newArrivals.port+endPoint+apiRoutes.newArrivals.controller+apiRoutes.newArrivals.method;
  const addToCartUrl=url+apiRoutes.addToCart.port+endPoint+apiRoutes.addToCart.controller+apiRoutes.addToCart.method;

  const navigate = useNavigate();
  const [inCartItem, updateInCartItem] = useState([])
  const [medicine,setMedicine]=useState([]);
  const [limitedDeal,setLimitedDeal]=useState([]);
  const [newArrivals,setNewArrivals]=useState([]);
  const [userPincode,setUserPincode]=useContext(HomeContext);
  const [shops,setShops]=useState([]);
  function loadLimitedDeal(){
      axios.get(`${absoluteUrl}`).then((res)=>{
        setLimitedDeal(res.data);
      }).catch((error)=>{
        console.log(error);
      })
  }
  function loadNewArrivals(){
    axios.get(`${newArrivalsUrl}`).then((res)=>{
      setNewArrivals(res.data);
    }).catch((error)=>{
      console.log(error);
    })
}
const addToCart = (item,gotoCart) => {
  if(gotoCart) {
      window.location.href = '/cart'

  
  }else {
      const email = localStorage.getItem('email')
      const data = {
          // "userEmail": email,
          "medicines": [
            {
              "medId": item.medicineId,
              "quantity": 1,
              "medPrice": item.medicineDiscountprice,
              "medTotalPrice": 0
            }
          ],
          "deliveryDate": "2022-09-29T07:34:56.460+00:00"
        }

      axios.put(`${addToCartUrl}/${email}`,data,{
          headers:{'Content-Type': 'application/json'}
      }).then((response)=> {
          if(response && response.data?.medicines.length) {
              const map = {}
              response.data?.medicines.forEach(element => {
                  map[element.medId] = element.quantity
              })
             updateInCartItem(map)
              //show success message item added to cart
          }
      })
  }
}
  useEffect(()=>{
    console.log(userPincode, "state has changed");
    let role= localStorage.getItem('role');
    let pincodeStorage = localStorage.getItem('pincode');
    if(pincodeStorage){
     
      setUserPincode(pincodeStorage);
    }
    if(role=='SELLER'){
      navigate('/error')
    }
    console.log(userPincode)
    if(userPincode){
      axios.get(`${nearByShopUrl}/${userPincode}`).then((res)=>{
        setShops(res.data);
      }).catch((error)=>console.error(error));
    }else{
      axios.get(`${allByShopUrl}`).then((res)=>{
        setShops(res.data);
      }).catch((error)=>console.error(error));
    }
    loadLimitedDeal();
    loadNewArrivals();
  },[])
    return (
      <>
      <div className="main">

        <Header />
    

          <div className="home-wrapper">
            
            <TopSlider />
              {
                Object.keys(shops).length != 0 &&
                            <div className="shop-section mt-3">
                            <div className="section-header">
                            <p className="header">
                              <i class="fa fa-building mr-3"></i>Near by shops
                            </p>
                            <Link to="/shops" className="btn btn-purchase mt-0 ms-auto">Sell All</Link>
                            </div>
                            <div className="row g-2 d-flex justify-content-start">
                              <Shops shops={shops} count={4}/>
                            </div>
                          </div>
                
              }
              {
                Object.keys(limitedDeal).length != 0 &&
            <div className="offers-section mt-3">
              <div className="section-header">
              <p className="header">
                <i class="fa fa-hourglass mr-3"></i>Limited Time offers
              </p>
              <Link to="/medicines" onClick={() => {localStorage.setItem('shopEmail', "undefined");}} className="btn btn-purchase mt-0 ms-auto">Sell All</Link>
              </div>
              <div className="row g-2 d-flex justify-content-start">
              <BestSellingSlider data={limitedDeal} slidesToShow={4} autoPlay="false" addToCart={addToCart} inCartItem={inCartItem}/>
              </div>
            </div>
              }
            <div className="best-selling-section mt-3">
            <div className="section-header">
            <p className="header"> <i class="fa fa-star mr-2" aria-hidden="true"></i>
               Best selling</p>
              <Link to="/medicines" onClick={() => {localStorage.setItem('shopEmail', "undefined");}} className="btn btn-purchase mt-0 ms-auto mr-5">Sell All</Link>
              </div>

              <BestSellingSlider data={limitedDeal} slidesToShow={3} autoPlay="false" addToCart={addToCart} inCartItem={inCartItem}/>
            </div>
            <div className="best-selling-section mt-3">
 
               <div className="section-header">
               <p className="header">
               New Arrivals</p>
              <Link to="/medicines" onClick={() => {localStorage.setItem('shopEmail', "undefined");}} className="btn btn-purchase mt-0 ms-auto mr-5">Sell All</Link>
              </div>
              <BestSellingSlider data={newArrivals} slidesToShow={4} autoPlay="false" addToCart={addToCart} inCartItem={inCartItem}/>
            </div>
            <div className="category-section mt-3">
            <p className="header"> <i class="fa fa-square mr-2" aria-hidden="true"></i>
               Shop By categories</p>
            <div className="row m-0 p-0">
              <div className="col-md-3 category-item"><div className="category-name"><Link to="/medicines/homeopathy">Homeopathy</Link></div></div>
              <div className="col-md-3 category-item"><Link to="/medicines/covid"><div className="category-name">Covid</div></Link></div>
              <div className="col-md-3 category-item"><Link to="/medicines/ayurvedic"><div className="category-name">Ayurvedic</div></Link></div>
              <div className="col-md-3 category-item"><Link to="/medicines/nutrition"><div className="category-name">Nutrition</div></Link></div>
              <div className="col-md-3 category-item"><Link to="/medicines/hairCare"><div className="category-name">Hair care</div></Link></div>
              <div className="col-md-3 category-item"><Link to="/medicines/faceCare"><div className="category-name">Face care</div></Link></div>
              <div className="col-md-3 category-item"><Link to="/medicines/babyCare"><div className="category-name">Baby care</div></Link></div>
              <div className="col-md-3 category-item"><Link to="/medicines/eyeCare"><div className="category-name">Eye care</div></Link></div>
              <div className="col-md-3 category-item"><Link to="/medicines/diabtese"><div className="category-name">Diabtese</div></Link></div>
              <div className="col-md-3 category-item"><Link to="/medicines/dailySupplements"><div className="category-name">Daily Supplements</div></Link></div>
              <div className="col-md-3 category-item"><Link to="/medicines/dailyCare"><div className="category-name">Daily care</div></Link></div>
              <div className="col-md-3 category-item"><Link to="/medicines/foodSupplemets"><div className="category-name">Food supplemets</div></Link></div>
            </div>
            </div>
            <div className="footer-section">
           
               <div className="footer-section-top">
               <p><b>Popular Wellness Products:</b><br/><a href="/non-prescriptions/shilajit-gold-20-s">Dabur Shilajit Gold Capsule 20's</a> | <a href="/non-prescriptions/dabur-chyawanprash-awaleha-500gm">Dabur Chyawanprash Awaleha 500 gm</a> | <a href="/non-prescriptions/pankaja-kasthuri-breathe-easy-400gm">Pankajakasthuri Breathe Easy Granules 400 gm</a> | <a href="/non-prescriptions/peptamen-peptide-based-diet-vanilla-flavour-powder-400gm">Nestle Peptamen Peptide Based Diet Powder - Vanilla Flavour 400 gm (Tin)</a> | <a href="/non-prescriptions/pentasure-2-0-powder-1kg">Pentasure 2.0 Vanilla Powder 1 kg</a> | <a href="/non-prescriptions/scalpe-shampoo-75ml">Scalpe Plus Anti Dandruff Shampoo 75 ml</a> | <a href="/non-prescriptions/nestle-nan-excella-pro-1-400gm">Nestle Nan Excella Pro 1 (Upto 6 Months) Powder 400 gm (Refill Pack)</a> | <a href="/non-prescriptions/accu-chek-active-test-strip-50-s">Accu-Chek Active Test Strips 50's</a> | <a href="/non-prescriptions/d-protin-chocolate-powder-500gm">D Protin Chocolate Powder 500 gm</a> | <a href="/non-prescriptions/climax-delay-action-spray-for-men-12gm">Climax Spray for Men 12 gm</a><br/>
                <b>Top-Selling Health Packages:</b><br/> <a href="/health-packages">AAROGYAM C</a> | <a href="/health-packages">Netmed Health Pack</a> | <a href="/health-packages">Aarogyam 1.3</a> | <a href="/health-packages">Netmeds Swasthya</a> | <a href="/health-packages">Diabetic Checkup</a> | <a href="/health-packages">Aarogyam 1.7</a> | <a href="/health-packages">Basic Allergy Package</a> | <a href="/health-packages">Aarogyam X</a> | <a href="/health-packages">Advance Heart Care</a> | <a href="/health-packages">Netmeds Swasthya Plus</a><br/><b>Top-Selling Lab Tests:</b> <br/><a href="/health-packages">Complete Blood Count (CBC)/Complete Hemogram</a> | <a href="/health-packages">LIVER FUNCTION TEST</a> | <a href="/health-packages">Blood Glucose Fasting (FBS)</a> | <a href="/health-packages">THYROID PROFILE -TOTAL(T3,T4&amp;TSH)</a> | <a href="/health-packages">Fever Profile</a> | <a href="/health-packages">Urine Routine &amp; Microscopy</a> | <a href="/health-packages">Hemoglobin A1C (HbA1c)</a> | <a href="/health-packages">25-OH Vitamin D (TOTAL)/Vitamin D Total 25 Hydroxy</a> | <a href="/health-packages">Uric Acid</a> | <a href="/health-packages">Maternal screen-1st Trimester Dual Marker test</a><br/><b>Top-Selling Radiology Tests:</b> <br/> <a href="/health-packages">Ultrasound Whole Abdomen</a> | <a href="/health-packages">MRI Scan Brain</a> | <a href="/health-packages">CT Scan Brain</a> | <a href="/health-packages">ECHO</a> | <a href="/health-packages">X Ray Chest PA View</a> | <a href="/health-packages">PET Scan</a> | <a href="/health-packages">ECG</a> | <a href="/health-packages">Ultrasound Pelvis</a> | <a href="/health-packages">3T MRI Scan Brain</a> | <a href="/health-packages">CT Scan Chest</a><br/><b>Most Viewed Health Articles:</b><br/><a href="/health-library/post/chandraprabha-vati-uses-functions-and-therapeutic-benefits">Chandraprabha Vati - Uses, Functions And Therapeutic Benefits</a> | <a href="/health-library/post/amla-benefits-uses-for-hair-and-health-conditions-supplements-and-recipes">Amla: Benefits, Uses For Hair and Health Conditions, Supplements And Recipes</a> | <a href="/health-library/post/amazing-benefits-of-chyawanprash-for-bolstering-immunity-and-vitality">Amazing Benefits Of Chyawanprash For Bolstering Immunity And Vitality</a> | <a href="/health-library/post/arogyavardhini-vati-uses-functions-and-therapeutic-benefits">Arogyavardhini Vati - Uses, Functions And Therapeutic Benefits</a> | <a href="/health-library/post/amazing-benefits-of-betel-leaves-nobody-told-you">Amazing Benefits Of Betel Leaves Nobody Told You</a></p>             
               </div>
            </div>
          </div>
        <Footer/>
      </div>
      </>
    );
}
export default Homepage;
