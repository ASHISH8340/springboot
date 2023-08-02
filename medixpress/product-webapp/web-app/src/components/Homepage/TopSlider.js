import React from "react";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import Slider from "react-slick";
function TopSlider(props){
    let settings = {
        dots: false,
        className: 'sample',
        speed: 500,
        slidesToShow: 1,
        slidesToScroll: 1,
        autoplay:true,
        autoplaySpeed: 2000,
        pauseOnHover: true,
        responsive: [
            {
              breakpoint: 1024,
              settings: {
                slidesToShow: 1,
                slidesToScroll: 3,
                infinite: true,
              }
            },
            {
              breakpoint: 600,
              settings: {
                slidesToShow: 1,
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
          <div className="container-fluid top-slider">

                <Slider {...settings}>
                 <div className="slick-item">
                <img src={process.env.PUBLIC_URL+"/assets/images/banner2.jpg"}/>
                </div>
                <div className="slick-item">
                <img src={process.env.PUBLIC_URL+"/assets/images/5665572.jpg"}/>
                </div>

                </Slider>
                </div>
            
    
     
        
        )
}
export default TopSlider;