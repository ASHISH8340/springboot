
import React from 'react';
import './style.css';
import axios from 'axios';
import { useEffect, useState,useContext } from 'react';
import { MdDelete } from "react-icons/md";
import { Link, useNavigate, useParams } from "react-router-dom";
import { useLocation } from 'react-router-dom'
import Header from '../Header/Header';
import { ConfigContext, HomeContext } from "../../Context/ConfigContext";

function CategoryProducts( {userRole},props) {
    const { url,endPoint, apiRoutes} = useContext(ConfigContext);
    const absoluteUrl=url+apiRoutes.productByCategory.port+endPoint+apiRoutes.productByCategory.controller+apiRoutes.productByCategory.method;
    const {category} = useParams();
    const navigate = useNavigate();
    const [meds, setMeds] = useState([]);
    const [searchTerm, setSearchTerm] = useState([]);
    const [currentPage, setCurrentPage] = useState(0);
    const [pageLimit] = useState(8);
    const [operation, setOperation] = useState("");
    const [updatecurrentImage, setUpdatecurrentImage] = useState();
    let start = 0, end = 8;
    var offer;
    var off_deci;
    const location = useLocation()
    
    useEffect(() => {
        loadData(start, end, 0);
    }, [category])
    // useEffect(() => {
    //     loadImage();
    // }, [])
    
   
    const loadData = async (start, end, increase, optType = null) => {
        switch (optType) {
            case "search":
                setOperation(optType);
                await axios.get(`${absoluteUrl}/`+category)

                    .then((response) => {
                        let a = response.data.filter(item => {
                            return item.medicineName.toLowerCase().includes(searchTerm.toLowerCase())
                        })
                        setMeds(a);
                        //  setCurrentPage(currentPage + increase);
                        console.log(response.data, "hi", a);
                    })
                    .catch((err) => console.log(err))
                break;
            default:
                return await axios.get(`${absoluteUrl}/`+category)
                    .then((response) => {
                        setMeds(response.data.slice(start, end, increase))
                        // if (response.data.length > end) {
                        setCurrentPage(currentPage + increase);
                        console.log(response.data);
                        console.log(props.ShopList,"shopList")
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
    // const handleChange = (name) => (e) => {
    //     const target = e.target;
    //     const value = target.type === 'file' ? target.files[0] : target.value;
    //     console.log(value);
    //     // const name = target.name;
    //     setUpdateImage(value);
    //   }
    
    //     const uploadImage = (email) => {
    
    //         const formData = new FormData();
    //         formData.append('multipartFile', updateImage);
    //         formData.append('shopEmail', email);
    //         console.log(formData);
    //         axios.post(`http://localhost:9192/api/v1/addimage/${email}`,formData).then((resposnse) => {
    //             console.log(resposnse);
    //             console.log("sucess")
    //         }).catch((err) => {console.log(err)})
    //     }
    //     console.log(updateImage)
    
    // const loadImage= async()=>{
    //     return await axios
    //     .get(`http://localhost:9192/api/v1/addimage/shopEmail`)
    //     .then((response)=>{
    //     setUpdatecurrentImage(response)
    //     }
    //     )

    // }

    const handlesearch = (e) => {
        e.preventDefault();
        loadData(0, 8, 0, "search", searchTerm)
    }
   
    function openAddMedicine() {
        navigate('addmedicine');
    }
    function deleteCard(medicineId) {
        if (window.confirm('Are you sure want to delete?')) {
            let a = meds.filter(item => item.medicineId != medicineId)
            setMeds(a)
            fetch('http://localhost:8088/medicine-service/api/v1/getMedicineCategory/' + medicineId, {
                method: "DELETE",
                header: {
                    'Accept': 'application/json',
                    'content-Type': 'application/json'
                }
            }
            )
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
    return (
        <>

            <div className="whole">
           <Header />
                <div className="Search-section-category">
                    <div className="search-div mx-auto">
                            <div className='form-group d-flex'>       <input type="text" placeholder='Search for Medicines and Wellness products...' className='search-bar form-control' onChange={(e) => setSearchTerm(e.target.value)}></input>
                        <button className='btn btn-style my-auto' onClick={handlesearch}>Search</button></div>
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
                    <div className='outer-div'>
                        {
                            meds.map(item => {
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
                                                    <span className="mrp-price">₹ {item.medicinePrice}</span></span>
                                                <span className="offer-price">(GET  {offer}  % OFF)</span>
                                                {/* <div className="product-name">Mkt:{item.shopName}</div> */}
                                            </div>
                                            {(userRole == "BUYER") &&
                                                <div className="cart">
                                                    <button className="cart-btn"><b>Add to Cart</b></button>
                                                </div>
                                            }
                                           {/* <p>{ShopList.contactEmail}</p> */}

                                        </div>
                                    </div>
                                )
                            })
                        }
                    </div>
                </div>
            </div>
        </>
    );
}
export default CategoryProducts;

