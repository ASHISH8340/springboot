import axios from 'axios';
import React, { useEffect, useState, useContext } from 'react';
import Header from '../../components/Header/Header';
import { ConfigContext } from "../../Context/ConfigContext";

function Cart(props) {
    const { url, endPoint, apiRoutes } = useContext(ConfigContext);
    const absoluteUrl = url + apiRoutes.detail.port + endPoint + apiRoutes.detail.controller + apiRoutes.detail.method;
    const { updateItemsInCartFromcart } = props
    const [medicineListFromAPI, updateMedicineList] = useState([])
    const [medicineQuantityMap, updateMedicineQuantityMap] = useState({})
    const addedToCartUrl=url+apiRoutes.addedToCart.port+endPoint+apiRoutes.addedToCart.controller+apiRoutes.addedToCart.method;
    const deleteFromCartUrl=url+apiRoutes.deleteFromCart.port+endPoint+apiRoutes.deleteFromCart.controller+apiRoutes.deleteFromCart.method;

    const medicines = {};
    useEffect(() => {
        updateItemsInCartFromcart(medicineListFromAPI)
    }, [medicineListFromAPI])

    useEffect(() => {
        tryFetchingCart()
    }, [])

    async function tryFetchingCart () {
        const email = localStorage.getItem('email')
        const url = `${addedToCartUrl}/` + email
        let arrayToParse = []
        await axios.get(url).then(async (response) => {
            let map = {}
            response.data.medicines.forEach((element) => {
                map[element.medId] = element
            })
            updateMedicineQuantityMap(map)
            await response.data.medicines.map(async function (item) {
                await axios.get(`${absoluteUrl}/${item.medId}`).then((res) => {
                    arrayToParse.push(res.data)
                    map[res.data.medicineId]['medName'] = res.data.medicineName
                    map[res.data.medicineId]['image'] = res.data.image.data
                    map[res.data.medicineId]['pharma'] = res.data.shopName

                })
                if(response?.data?.medicines?.length === arrayToParse.length) {
                    const keys = Object.keys(map)
                    let arr = []
                    keys.forEach((element) => {
                        arr.push(map[element])
                    })
                    await updateMedicineList(arr)
                }

            })
            // }
        })
    }



    const increaseQuantity = (medicine, index) => {
        const listToParse = [...medicineListFromAPI]
        if (listToParse[index]) {
            listToParse[index].quantity += 1
            updateMedicineList(listToParse)
        }
    }

    const decreaseQuantity = (medicine, index) => {
        const listToParse = [...medicineListFromAPI]
        if (listToParse[index] && listToParse[index].quantity > 0) {
            listToParse[index].quantity -= 1
            updateMedicineList(listToParse)
        }
    }

      function deleteItem(medicineListFromAPI, index) {
        const listToParse = [...medicineListFromAPI]
        if (listToParse[index] && listToParse[index].quantity > 0) {
            listToParse[index].quantity = 0
            updateMedicineList(listToParse)
        }
    }

    function deleteItem(medicine,index) {
        let email = localStorage.getItem('email');
        axios.delete(`${deleteFromCartUrl}/${medicine.medId}/${email}`).then((res)=>{
            deleteItem(medicineListFromAPI,index)
        })
        console.log("inside Delete "+medicine)

        tryFetchingCart();
    }

    return (
        <>
        <div className='cart-container'>
            <p className='cart-denote'>Cart Items({medicineListFromAPI.length})</p>
            <div className='content-wrapper'>
                {medicineListFromAPI.map((medicine, index) => {
                    return (
                        <React.Fragment key={index}>
                            {medicine.quantity > 0 &&
                                <div className='medicine-box vertical-center'>
                                    <div className='medicine-image'>
                                        <img className='med-image' src={`data:image/png;base64, ${medicine.image}`} height='80px' width='80px' />
                                    </div>
                                    <div className='medicine-name'>
                                        <p className='med-name'>{medicine.medName}</p>
                                        <p className='med-desc'>{medicine.pharma}</p>
                                        <p className='med-desc'>Delivery By{medicine.deliveryDate}</p>
                                    </div>
                                    <div className='medicine-price margin-auto red-color'>Rs. {medicine.medPrice}</div>
                                    <div className='quantity-wrapper vertical-center'>
                                        {medicine.quantity > 1 && <span onClick={() => decreaseQuantity(medicine, index)} className='fa fa-minus denoted-icon'> </span>}
                                        <span className='cart-quantity'>{medicine.quantity}</span>
                                        <span onClick={() => increaseQuantity(medicine, index)} className='fa fa-plus denoted-icon'></span>
                                        <span onClick={() => deleteItem(medicine, index)} className='fa fa-trash delbtn'   ></span>
                                    </div>
                                </div>}
                        </React.Fragment>
                    )
                })}
            </div>
        </div></>
    )
}


export default Cart