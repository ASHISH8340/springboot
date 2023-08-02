import React, { useState } from "react";

function Promo(props) {
    const {applyPromo} = props
    const [promoText,updatePromoText] = useState('')

    function tryApplyingPromo () {
        //we can call api to update price details
        if(promoText && promoText.value) {
            applyPromo(promoText)
        }
    }

    function tryUpdatingPromo(promo) {
        updatePromoText(promo)
    }

    const promoCodes = [{'text': 'MEDI25',value:25},{'text': 'MEDI50',value:50},{'text': 'MEDI60',value:60}]

    return (
        <div className='promo-wrapper'>
            <div className='Promo'>
                <p className='med-desc t-center'> Apply Promocode</p>
                <div className="input-wrapper vertical-center">
                    <input className="common-input" type='text' value={promoText.text || ''} onChange={(e)=>updatePromoText(e.target.value)} />
                    <div className="input-button" onClick={()=>tryApplyingPromo()}>Apply</div>
                </div>
                <div className="promo-code-wrapper ">
                    {promoCodes.map((promo,index) => {
                        return (
                            <section  key={index}>
                            {promoText.text !== promo.text &&<div className="promo-code" onClick={()=>tryUpdatingPromo(promo)}>{promo.text}</div>}
                            </section>
                        )
                    })}
                </div>
            </div>
        </div>
    )
}

export default Promo 