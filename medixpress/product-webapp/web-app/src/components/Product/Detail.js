import React,{ Component, useEffect, useState, useContext } from "react";
import 'bootstrap/dist/css/bootstrap.min.css'
import './style.css';
import '../common.css';
import Header from "../Header/Header";
import { useParams,useNavigate } from "react-router-dom";
import axios from "axios";
import { toast } from "react-toastify";
import { ConfigContext } from "../../Context/ConfigContext";
    // constructor(props){

    // }
  function  Detail(){
    const {id} = useParams();
    console.log(id);
    const [detail,setDetail]=useState({});
    const { url,endPoint, apiRoutes} = useContext(ConfigContext);
    const absoluteUrl=url+apiRoutes.detail.port+endPoint+apiRoutes.detail.controller+apiRoutes.detail.method;
    const navigate = useNavigate();

    useEffect(()=>{
        axios.get(`${absoluteUrl}/${id}`).then((response)=>{
   
            if(response.status == 200){
              setDetail(response.data);
              // setFormValues(response.data);
      
            }
      },[]).catch((error)=>{
        if(error.response.status == 404){
            toast.success("Medicine not found");
            navigate('/error');
          }
      });
    },[])
    if( Object.keys(detail).length >0 ){
return(
    <div className="main">
        <Header/>
        {/* <div className="content"> */}
    <div className="detail-wrapper">

    <div className="container-fluid detail-box">
    <div className="card detail-box-card">
        <div className="container-fluid">
            <div className="wrapper row">
                <div className="preview col-md-6">
                    
                    <div className="preview-pic tab-content">
                      <div className="tab-pane product-detail-img active" id="pic-1"><img src={ detail.image && "data:image/png;base64," + detail.image.data ||process.env.PUBLIC_URL+'/assets/images/medicine.jpg'} /></div>
                    </div>
                </div>
                <div className="details col-md-6">
                    <h3 className="product-title">{detail.medicineName}</h3>
                    <div className="favorite"><i className="fa fa-heart text-danger" aria-hidden="true"></i></div>
                    <div className="rating">
                        <div className="stars">
                            <span className="fa fa-star checked"></span>
                            <span className="fa fa-star checked"></span>
                            <span className="fa fa-star checked"></span>
                            <span className="fa fa-star"></span>
                            <span className="fa fa-star"></span>
                        </div>
                        <span className="review-no">41 reviews</span>
                    </div>
                    <p className="product-description">{detail.medicineDescription}</p>
                    <h4 className="price">current price: <span>₹ {detail.medicineDiscountprice}/-</span></h4>
                    <h5 className="price">MRP: <span>₹  <del> {detail.medicinePrice}/-</del></span></h5>
                    <p className="vote"><strong>91%</strong> of buyers reccommended! <strong>(87 votes)</strong></p>
                    <div className="card-footer mt-auto">
                    <div className="action">
                     <button className="btn btn-styles add-to-cart" type="button">add to cart</button>
        </div>

        </div>
                </div>
            </div>
        </div>
</div>
</div>
    {/* </div> */}
    </div></div>
)
    }
}
export default Detail;