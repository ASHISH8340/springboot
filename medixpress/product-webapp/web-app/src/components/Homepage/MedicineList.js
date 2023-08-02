import React from "react";
import { Card,Col,Button } from "react-bootstrap";
function MedicineList(props){
    return(            
        props.medicines.map((medicines, key)=>{
           return (
               <React.Fragment>
                <div className="col-md-3">
                  <div class="card">
                    <div className="image-holder">
                    <img
                      class="card-img-top"
                      src="https://source.unsplash.com/random/900×700?pill"
                      alt="Card image cap"
                    />
                    </div>
              
                    <div class="card-body">
                      <h5 class="card-title">Card title</h5>
                      <p class="card-text">
                        Some quick example text to build on the card title and
                        make up the bulk of the card's content.
                      </p>
                    </div>
                    <div class="card-body">
                      <ul className="product-detail">
                        <li className="current-price">
                          ₹300{" "}
                          <span className="mrp-price">
                            <del>₹500</del>
                          </span>
                        </li>
                        <li className="discount">Upto 30%</li>
                      </ul>
                      <div className="purchase-btn-section">
 
                        <a href="#" class="btn btn-purchase">
                          Add to cart
                        </a>
                      </div>
                    </div>
                  </div>
                </div>
             </React.Fragment>
   
           )
       })
    )
}
export default MedicineList;