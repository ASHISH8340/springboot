import React, { useEffect, useState } from "react";

function Payment(props) {
    const { itemsInCart, appliedPromo,gotoPrescriptionPage,updateFinalPayment } = props

    useEffect(() => {
        handlePaymentDetails()
    }, [itemsInCart])

    const [totalPrice, updateTotalPrice] = useState(0)
    const [priceDetails, updatePriceDetails] = useState({
        discountPrice: 0,
        totalPriceAfterDiscount: 0
    })

    useEffect(() => {
        handleDiscount(appliedPromo)
    }, [appliedPromo])

    function handlePaymentDetails() {
        let totalCalculatedPrice = 0
        itemsInCart && itemsInCart.forEach(item => {
            totalCalculatedPrice += (item.quantity * item.medPrice)
        });
        updateTotalPrice(totalCalculatedPrice)
        handleDiscount(appliedPromo)
        updateFinalPayment(totalCalculatedPrice || totalPrice)
    }

    function handleDiscount(promo) {
        let totalPriceAfterDiscount = 0
        const discountPrice = (totalPrice * promo.value) / 100
        totalPriceAfterDiscount = totalPrice - discountPrice
        updatePriceDetails({ ...priceDetails, discountPrice, totalPriceAfterDiscount })
        updateFinalPayment(totalPriceAfterDiscount || totalPrice)
    }

    return (
        <div className="payment-container">
            <p className='payment-denote'>Payment</p>
            <div className="vertical-center justify-left">
                <p className='med-desc'>Item Total(MRP)</p>
                <p className='med-desc ml-auto'>₹ {totalPrice}</p>
            </div>
            <div className="vertical-center justify-left">
                <p className='med-desc'>DISCOUNT: </p>
                <p className='med-desc ml-auto'>₹ {priceDetails.discountPrice || 0}</p>
            </div>
            <div className="vertical-center justify-left">
                <p className='med-desc'>Total Price*</p>
                <p className='med-desc ml-auto'>₹ {priceDetails.totalPriceAfterDiscount || totalPrice}</p>
            </div>
            <div className="input-button proceed-button" onClick={() => gotoPrescriptionPage()}>Proceed</div>
        </div>

    )

}

export default Payment