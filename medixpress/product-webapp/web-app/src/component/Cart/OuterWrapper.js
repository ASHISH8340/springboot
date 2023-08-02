import React, { useState } from "react";
import Cart from "./Cart";
import '../../assets/stylesheets/outercart.scss'
import Corousel from "./Corousel";
import Payment from "./Payment";
import Promo from "./Promo";
import Prescription from "./Prescription";
import { ToastContainer, toast } from 'react-toastify';
import CardDetails from "./CardDetails";
import Thankyou from "./Thankyou";
import { logDOM } from "@testing-library/react";
import Header from "../../components/Header/Header";
function OuterCart(props) {

    const [itemsInCart, updateItemsInCart] = useState(null)
    const [appliedPromo, updateAppliedPromo] = useState({})
    const [state, setState] = useState({
        currentView: 0,
        uploadedPrescription: null,
        finalPayment: 0
    })
    const [finalPayment,updateFinalPayment] = useState(0)

    const notify = () => toast("Please upload prescription to proceed with payment");


    const updateItemsInCartFromcart = (itemsInCart) => {
        updateItemsInCart(itemsInCart)
    }

    const updatePromoForItemsInCart = (promo) => {
        updateAppliedPromo(promo)
    }

    const updateFileFromPrescription = (file) => {
        setState({ ...state, uploadedPrescription: file })
    }

    function gotoPrescriptionPage() {
        if (state.currentView === 0) {
            setState({ ...state, currentView: 1 })
        }
        if (state.currentView && !state.uploadedPrescription) {
            notify()
        }
        if (state.currentView && state.uploadedPrescription) {
            setState({ ...state, currentView: 2 })
        }
    }

    function goBack(step) {
        setState({ ...state, currentView: step })
    }

    return (
        <>        <Header/>
     
        <div className="cl-ol" >
            {state.currentView !== 3 &&
                <section className="outer-cart-wrapper ">
                    <div className="left-content" >
                  
                        {state.currentView === 0 && <div className="courousel-wrapper w-80">
                            <div class="textBox">
                    <h4>My Cart</h4>
                    </div>
                        </div>}
                        {state.currentView === 0 && <section className="cart-wrapper w-80">
                    
                            <Cart updateItemsInCartFromcart={updateItemsInCartFromcart} />
                            
                        </section>}
                        {state.currentView === 1 && <section className="p-wrapper w-80">
                            <Prescription updateFileFromPrescription={updateFileFromPrescription} />
                            <img alt="" src={require(`../../assets/Images/a.png`)} className="img1"/>
                            <img alt="" src={require(`../../assets/Images/b.png`)} className="img2"/>
                            <img alt="" src={require(`../../assets/Images/c.png`)} className="img3"/>
                            <img alt="" src={require(`../../assets/Images/d.png`)} className="img5"/>
                            <div className="">
                            <p>A licensed pharmacy would be delivering your order basis availability of product & fastest delivery. </p>
                           </div>
                           
                          

                        </section>}
                        {state.currentView === 2 && <section className="p-wrapper w-80">
                            <CardDetails updateFileFromPrescription={updateFileFromPrescription} finalPayment={finalPayment} gotoThanks={() => setState({ ...state, currentView: 3 })} />
                        </section>}
                    </div>
                    <div className="right-content">
                        {state.currentView === 0 && <section className="promocode-wrapper">
                            <Promo applyPromo={updatePromoForItemsInCart} />
                        </section>}
                        <section className="payment-wrapper">
                        <div id="container">
                        <img src="../assets/Images/razorpay-who.png" alt="" />
                        </div>
                            <Payment itemsInCart={itemsInCart} appliedPromo={appliedPromo} gotoPrescriptionPage={gotoPrescriptionPage} updateFinalPayment={(payment) => updateFinalPayment(payment)} goBack={goBack} />
                        </section>
                    </div>
                </section>}
            <ToastContainer />
            {state.currentView === 3 && <div className="thanks">
                <Thankyou goHome={() => setState({ ...state, currentView: 0 })} />
            </div>}
        </div>
        </>
    )
}

export default OuterCart

