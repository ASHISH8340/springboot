import React from "react";
import { Card,Col,Button } from "react-bootstrap";
function Shops(props){
    let count=0;
    return(            
        props.shops.map((shops, key)=>{
            console.log(key);
            if( !isNaN(props.count) && props.count<(key+1)){
                return;
            }else{
           return (
               <React.Fragment>
                <div className="col-md-3">
                  <div class="card">
                    <div className="image-holder">
                        <i className="fa fa-building"></i>
                        {
                            shops.shopImage &&
                                                <img
                                                class="card-img-top"
                                                src={`data:image/png;base64,${shops.shopImage.data}`}
                                                alt="Card image cap"
                                              /> ||  <img
                                              class="card-img-brand"
                                              src={process.env.PUBLIC_URL+"/assets/images/logo.png"}
                                              alt="Card image cap"
                                            />
                        }
                    </div>
              
                    <div class="card-body">
                      <h5 class="card-title">{shops.shopName}</h5>
                    </div>
                  </div>
                </div>
             </React.Fragment>
           )
                    }
       })
    
    )

}
export default Shops;