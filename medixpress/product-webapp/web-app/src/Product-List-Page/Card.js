import React from 'react';
import './Card.css';
import axios from 'axios';
import { useEffect, useState,useContext } from 'react';
import { MdDelete } from "react-icons/md";
import { BiSearch } from "react-icons/bi";
import { Link, useNavigate } from "react-router-dom";
import {Container, Row, Col, Form, InputGroup, Button} from 'react-bootstrap';
import Header from '../components/Header/Header';
import { ConfigContext } from "../Context/ConfigContext";
import { toast } from 'react-toastify';
function Card() {

  const userRole = localStorage.getItem('role');
     
    const navigate = useNavigate()
    const [meds, setMeds] = useState([]);
    const [showEmail, setShowEmail] = useState("");
    const [searchTerm, setSearchTerm] = useState([]);
    const [currentPage, setCurrentPage] = useState(0);
    const [pageLimit] = useState(8);
    const [inCartItem, updateInCartItem] = useState([])
    const [operation, setOperation] = useState("");
    const [updatecurrentImage, setUpdatecurrentImage] = useState();
    const { url,endPoint, apiRoutes} = useContext(ConfigContext);
    const absoluteUrl=url+apiRoutes.medicines.port+endPoint+apiRoutes.medicines.controller+apiRoutes.medicines.method;
    const deleteUrl=url+apiRoutes.deletemedicine.port+endPoint+apiRoutes.deletemedicine.controller+apiRoutes.deletemedicine.method;
    const shopmedicinesUrl=url+apiRoutes.shopmedicines.port+endPoint+apiRoutes.shopmedicines.controller+apiRoutes.shopmedicines.method;
    const addToCartUrl=url+apiRoutes.addToCart.port+endPoint+apiRoutes.addToCart.controller+apiRoutes.addToCart.method;
  
    
    

    let start = 0, end = 8;
    var offer;
    var off_deci;
   
    
    console.log(showEmail);
   
    const loadData = async (start, end, increase, optType ) => {
        switch (optType) {
            case "search":
              setOperation(optType);
              await axios
                  .get(`${absoluteUrl}`)
                  .then((response) => {
                      let a = response.data.filter(item => {
                          return item.medicineName.toLowerCase().includes(searchTerm.toLowerCase())
                      })
                      setMeds(a);
                        
                      console.log(response.data, "hi", a);
                  })
                  .catch((err) => console.log(err))
              break;

              case "ShowEmail":
                  await axios
                      .get(`${shopmedicinesUrl}/${localStorage.getItem("shopEmail")}`)
                      
                      .then((response) => {
                          // // let a = response.data.filter(item => {
                          //     return item.medicineName.toLowerCase().includes(searchTerm.toLowerCase())
                          // })
                          
                          //   setCurrentPage(currentPage + increase);
                            console.log(response, "showEmail",showEmail);
                            
                            setMeds(response.data)
                            console.log(meds,"meds")
                          //  localStorage.removeItem("shopEmail")
                      })
                      .catch((err) => console.log(err))
                  break;


            default:
                 await axios.get(`${absoluteUrl}`)
                    .then((response) => {
                        setMeds(response.data.slice(start, end, increase))
                        // if (response.data.length > end) {
                        setCurrentPage(currentPage + increase);
                        console.log(response.data);
                       
                        start = start + 8;
                        end = end + 8;
                        // }
                        // else{
                        //    return null;
                        // }
                    })
                    .catch((err) => console.log(err))
        }
    }
  
    useEffect(() => {
     
      switch (userRole){
        case "SELLER":
          loadData(start, end, 0, "ShowEmail");
          break;

        case "BUYER":  
          if(localStorage.getItem("shopEmail") == "undefined"){
            // alert("im working")
            loadData(start, end, 0);  
          }else{
            // alert("im testing")
            setTimeout(() => {
              loadData(start, end, 0, "ShowEmail");
            },500)
          } 
          break;

        default:
          if(localStorage.getItem("shopEmail") == "undefined"){
            // alert("im working")
            loadData(start, end, 0);  
          }else{
            // alert("im testing")
            setTimeout(() => {
              loadData(start, end, 0, "ShowEmail");
            },500)
          } 
      }
         
    }, [])

    // const handlesearch = (e) => {
    //     e.preventDefault();
    //     // loadData(0, 8, 0, "search", searchTerm)
    // }
   
    function openAddMedicine() {
        navigate('addmedicine');
    }
    function deleteCard(medicineId) {
        if (medicineId) {
            let a = meds.filter(item => item.medicineId != medicineId)
            setMeds(a)
            fetch(`${deleteUrl}/` + medicineId, {
                method: "DELETE",
                header: {
                    'Accept': 'application/json',
                    'content-Type': 'application/json'
                }
            }
            ).then(() =>{
              toast.success(" Medicines Deleted Successfully");
            })
        }

    }
    const renderPagination = () => {
        console.log(meds, "page");
        if (meds.length < 8 && currentPage === 0) return null;
        if (currentPage === 0) {
            return (
                <div>
                    <button className='pagenum-btn'>1</button>
                    <button className='pagination-btn' onClick={() => loadData(8, 16, 1, operation)}>Next</button>
                </div>
            );
        }
        else if (currentPage < (pageLimit - 1) && meds.length === pageLimit) {
            return (
                <div>
                    <button className='pagination-btn' onClick={() => loadData((currentPage - 1) * 8, currentPage * 8, -1, operation)}>Prev</button>
                    <button className='pagenum-btn'>{currentPage + 1}</button>
                    <button className='pagination-btn' onClick={() => loadData((currentPage + 1) * 8, (currentPage + 2) * 8, 1, operation)}>Next</button>
                </div>
            );
        }
        else {
            return (
                <div>
                    <button className='pagination-btn' onClick={() => loadData((currentPage - 1) * 8, currentPage * 8, -1, operation)}>Prev</button>
                    <button className='pagenum-btn'>{currentPage + 1}</button>

                </div>
            )
        }
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
    
    return (
        <>

          <div className="whole">
           <Header />
                <div className="Search-section">
                    <div className="search-div mx-auto">
                        <input type="text" placeholder='Search for Medicines and Wellness products...' className='search-bar' onChange={(e) => setSearchTerm(e.target.value)}></input>
                        <button className='Search-button' ><BiSearch></BiSearch></button>
                        {/* <InputGroup className="mb-3" className='search-bar'>
                            <Form.Control
                                placeholder="Enter Your pin code"
                                aria-label="Enter Your pin code"
                                aria-describedby="basic-addon2"
                                onChange={(e) => setSearchTerm(e.target.value)}
                                name="searchInput"
                                
                            />
                            <InputGroup.Text id="basic-addon2">
                                <BiSearch></BiSearch>
                            </InputGroup.Text>
                        </InputGroup> */}
                    </div>
                    <div className="add-medicine-div ml-auto">
                        {
                            (userRole =="SELLER") &&
                            <button className='btn-add-Medicine' onClick={openAddMedicine} >Add Medicine</button>
                        }

                    </div>
                </div>
                <div>
                    <div className="Pagination" >    {renderPagination()}
                    </div>
                    <section className='medi-sections'>
                      <div className='outer-div'>
                          {
                              meds && meds.filter((item) => {
                                  if (searchTerm == ''){
                                      return item;
                                  }
                                  else if (item.medicineName.toLowerCase().includes(searchTerm.toLowerCase())){
                                      return item;
                                  }
                              }).map((item) => {
                                  off_deci = [(item.medicinePrice - item.medicineDiscountprice) / (item.medicinePrice)] * 100;
                                  offer = Math.ceil(off_deci)
                                  return (
                                      <div key={item.medicineId}>
                                          <div className="bg-white productdiv">
                                              <div className='del-med'>
                                                  {(userRole == "SELLER") &&
                                                      <button className='delete_medicine'>
                                                          <MdDelete title="delete" onClick={() => deleteCard(item.medicineId)}></MdDelete>
                                                      </button>
                                                  }
                                              </div>
                                              <div
                      className='med-img'>
                                          {(item.image) &&
                                            <Link to={`/detail/${item.medicineId}`}><img src={"data:image/png;base64," +item.image.data}  alt="medicine_imga" width="50%" height="50%"/></Link>
                                                  }
                                                
                                                        
                                                  {/* <img src={"data:image/png;base64," +item.image.data}  alt="medicine_imga" width="100%" height="100"/> */}
                                                              
                                                                                        
      
                                          </div>
                                              <div className="product-name"><b>{item.medicineName}</b></div>
                                              <div className="pricebox">
                                                  <span><b>Best Price</b>:</span>
                                                  <span className="final-price">₹ {item.medicineDiscountprice}</span>
                                                  <br />

                                                  <span>MRP:
                                                      <span className="mrp-price">₹ {item.medicinePrice} </span></span>
                                                  <span className="offer-price"> (GET  {offer}  % OFF)</span>
                                                  {/* <div className="product-name">Mkt:{item.shopName}</div> */}
                                              </div>
                                              {(userRole == "BUYER") &&
                                                  <div className="cart">
                                                    <button className="cart-btn" data-id={`${item.id}`} onClick={()=>addToCart(item,inCartItem[item.medicineId])}><b>{inCartItem[item.medicineId] ? 'Goto cart' : 'Add to Cart'}</b></button>
                                                  </div>
                                              }
                                            {/* <p>{ShopList.contactEmail}</p> */}

                                          </div>
                                      </div>
                                  )
                              })
                          }
                      </div>
                    </section>
                </div>
            </div>
        </>
    );
}
export default Card;

