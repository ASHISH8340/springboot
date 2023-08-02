import React, { useState } from "react";
import { Container, Form, InputGroup, Button, Col, Row } from "react-bootstrap";
import axios, * as others from 'axios'; 


function ConfirmationMedicineInfo({medicine,shopVerification}) {
	const addShopList = () => {
		// axios.post('http://localhost:3001/medicalList',{
		axios.post('http://localhost:8080/medicine-service/api/v1/addMedicines',
			medicine
		).then(() => {
			console.log("sucess");
		}).catch((err) => {
			console.log(err);
		})
	}
	console.log(medicine);
	console.log(shopVerification);

	return (
		<>
		<Container>
      <div className="form-wrap" >
				<h4 >Confirm your details</h4>
				<div style={{textAlign:"justify",marginLeft:"380px"}}>
				<b>
				
				{/* <p><span>Shop Email</span> : {medicine.shopEmail}</p> */}
				<p><span>Product Name</span> : {medicine.medicineName}</p>
				<p><span>Product Category</span> : {medicine.medicineCatagory}</p>
				<p><span>Product Units</span> : {medicine.medicineUnits}</p>
				<p><span>Best Price</span> : {medicine.medicineDiscountprice}</p>
				<p><span>MRP Price</span> : {medicine.medicinePrice}</p>
				<p><span>Product Description</span> : {medicine.medicineDescription}</p>
				<p><span>Manufacturing Date</span> : {medicine.manafacturedate}</p>
				<p><span>Expiry Date </span> : {medicine.expireydate}</p>
				</b>
				</div>
			
    

			</div>
			
		</Container>
		</>
	);
}

export default ConfirmationMedicineInfo;