import React, { useEffect, useRef, useState } from "react";
import ReactDOM from "react-dom"
// import { loadScript } from "@paypal/paypal-js";
import Paypal from "./Paypal";

function CardDetails(props) {
    const {finalPayment,gotoThanks} = props
    return (
        <div className="payment-option">
            <Paypal finalPayment={finalPayment} gotoThanks={gotoThanks}/>
        </div>
    )

}

export default CardDetails

