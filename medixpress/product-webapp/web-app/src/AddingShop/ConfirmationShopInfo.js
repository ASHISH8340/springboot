import React, { useState } from "react";
import { Container, Form, InputGroup, Button, Col, Row } from "react-bootstrap";
import axios, * as others from 'axios'; 


function ConfirmationShopInfo({shop,shopVerification}) {

	
	// console.log(shop);

	const addShopList = () => {
		// axios.post('http://localhost:3001/medicalShopList',{
		axios.post('http://localhost:8084/api/v1/add',
			shop,shopVerification,
		).then(() => {
			console.log("sucess");
		}).catch((err) => {
			console.log(err);
		})
	}
	console.log(shop);
	console.log(shopVerification);

	return (
		<>
		<Container>
      <div className="form-wrap ">
		<div className="Confirm-details"> 
		<h4>Confirm your details</h4>
				<p><span>Shop Name</span> : {shop.shopName}</p>
				<p><span>Shop Verification</span> : {(shop.shopVerification === true) ? "yes" : "No"}</p>
				<p><span>Shop Opening Timing</span> : {shop.ShopOpeningTime}</p>
				<p><span>Shop Closeing Timing</span> : {shop.ShopCloseTime}</p>
				<p><span>Shop Number</span> : {shop.shopNumber}</p>
				<p><span>Street Name</span> : {shop.streetName}</p>
				<p><span>Landmark </span> : {shop.landmark}</p>
				<p><span>City </span> : {shop.city}</p>
				<p><span>state </span> : {shop.state}</p>
				<p><span>pinCode </span> : {shop.pincode}</p>
		</div>
				
				

			</div>
			
		</Container>
		</>
	);
}

export default ConfirmationShopInfo;