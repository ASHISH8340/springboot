import React,{Fragment} from "react";
import { Link } from "react-router-dom";
import { containerFluid,Col,Card, Button,Row} from "react-bootstrap";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import Slider from "react-slick";
import './style.css'
function DisplayImages(props){
    console.log(props.images)
    if(props.images.length>1){
    return props.images.map(function(item,i){
        // return <div key={item.id}><img src={item.posterurl} /></div>
        return(
            <Fragment>
                <div className={i===0?"carousel-item":"carousel-item active"}>
            <img src={item.posterurl} className="d-block w-100" alt='...'/>
          </div>
            </Fragment>
        )
   });
}
}

function getPercentage(price,discount){
        return (((price-discount)/price)*100).toFixed(2);
}
function BestSellingSlider(props){
    let settings = {
        dots: false,
        className: 'best-selling-slide',
        speed: 500,
        slidesToShow: props.slidesToShow,
        slidesToScroll: 3,
        autoplay:props.autoPlay,
        autoplaySpeed: 2000,
        pauseOnHover: true,
        responsive: [
            {
              breakpoint: 1024,
              settings: {
                slidesToShow: 3,
                slidesToScroll: 3,
                infinite: true,
              }
            },
            {
              breakpoint: 600,
              settings: {
                slidesToShow: 2,
                slidesToScroll: 2,
                initialSlide: 2
              }
            },
            {
              breakpoint: 480,
              settings: {
                slidesToShow: 1,
                slidesToScroll: 1
              }
            }
          ]
    }
    
        return(
                <Slider {...settings}>
                    {
                      props.data.map((item,index)=>{
                        return(
                          <div class="card product-slider-card">
                          <div className="image-holder">
                            <Link to={`/detail/${item.medicineId}`}>
                          {
                            item.image &&
                                                <img
                                                class="card-img-top"
                                                src={`data:image/png;base64,${item.image.data}`}
                                                alt="Card image cap"
                                              /> ||  <img
                                              class="card-img-brand"
                                              src={process.env.PUBLIC_URL+"/assets/images/logo.png"}
                                              alt="Card image cap"
                                            />
                        }
                        </Link>
                            </div>
                            <div class="card-body product-title-body">
                              <h5 class="card-title product-title" title={`${item.medicineName}`}>{`${item.medicineName}`}</h5>
                              <p class="card-text product-desciption">
                              {`${item.medicineDescription}`}
                              </p>
                            </div>
                            <div class="card-body">
                              <ul className="product-detail">
                                <li className="current-price">
                                ₹{`${item.medicineDiscountprice}`}
                                 &nbsp;&nbsp;
                                  <span className="mrp-price">
                                    <del>₹{`${item.medicinePrice}`}</del>
                                  </span>
                                </li>
                                <li className="discount">Upto {getPercentage(item.medicinePrice,item.medicineDiscountprice)}%</li>
                              </ul>
                              <div className="purchase-btn-section">
                                {
                                  localStorage.getItem('email')?
                                <Link to="#" class="btn btn-purchase"data-id={`${item.id}`} onClick={()=>props.addToCart(item,props.inCartItem[item.medicineId])}><b>{props.inCartItem[item.medicineId] ? 'Goto cart' : 'Add to Cart'}</b>
                                </Link>
                                :  <Link to="/login" class="btn btn-purchase">
                                Add to cart
                              </Link>
                                }

                              </div>
                            </div>
                          </div>      
                        );
                      })
                    }
                </Slider>
        )
}
export default BestSellingSlider;