import React from "react";
import { useEffect, useState } from 'react';
import { Container, Form, InputGroup, Button, Col, Row } from "react-bootstrap";
import { BsCardImage } from "react-icons/bs";
// import { GiMedicinePills } from "react-icons/bs";
import { GiMedicinePills,GiMedicines } from "react-icons/gi";
import { MdMedicalServices } from "react-icons/md";
import { MdDriveFileRenameOutline,MdOutlineMarkEmailRead } from "react-icons/md";
import axios, * as others from 'axios';
import "./AddingMedicine.css";
const MedicineInformation = ({ handleChange }) => {

  // const [updateImage, setUpdateImage] = useState();
  // const [input, setInput] = useState('');
  // const ImageChange = (name) => (e) => {
  //   const target = e.target;
  //   const value = target.type === 'file' ? target.files[0] : target.value;
  //   console.log(value);
  //   // const name = target.name;
  //   setUpdateImage(value);
  // }
  // const uploadImage = (email) => {
  // 	const formData = new FormData();
  // 	formData.append('multipartFile', updateImage);
  // 	formData.append('shopEmail', email);
  // 	console.log(formData);
  // 	axios.post(`http://localhost:9192/api/v1/addimage/${email}`,formData).then((response) => {
  // 		console.log(response);
  // 		console.log("sucesss")
  // 	}).catch((err) => {console.log(err)})
  // }

  // console.log(updateImage);
  return (
    <Container>
      <div className="form-wrap">
        <h4>Medicine Details</h4>
        <Form>
          <Row>
            <Col md={6}>
              <Form.Group className="mb-3" controlId="formShopName">
                <Form.Label>Shop Email</Form.Label>
                <InputGroup className="mb-4">
                  <InputGroup.Text id="basic-addon1"><MdOutlineMarkEmailRead/> </InputGroup.Text>
                 
                  <Form.Control
                   
                    aria-label="Shop Email"
                    aria-describedby="basic-addon1"
                    type="email"
                    name="shopEmail"
                    value={localStorage.getItem("shopEmail")}
                    onChange={handleChange("shopEmail")}
                      disabled
                                  />
                </InputGroup>
              </Form.Group>
            </Col>
            {/* <Col md={6} >
              <Form.Group className="mb-3" controlId="formBasicEmail">
                <Form.Label> Shop Name</Form.Label>
                <InputGroup className="mb-4">
                  <InputGroup.Text id="basic-addon1"><MdDriveFileRenameOutline /></InputGroup.Text>
                  <Form.Control
                    placeholder="ShopName"
                    aria-label="Shop Name"
                    aria-describedby="basic-addon1"
                    type="text"
                    name="shopName"
                    onChange={handleChange("shopName")}
                  />

                </InputGroup>
              </Form.Group>
            </Col> */}
            <Col md={6} >
              <Form.Group className="mb-3" controlId="formShopName">
                <Form.Label>Product Name</Form.Label>
                <InputGroup className="mb-4">
                  <InputGroup.Text id="basic-addon1"><GiMedicines /></InputGroup.Text>
                  <Form.Control
                    placeholder="Product Name"
                    aria-label="Product Name"
                    aria-describedby="basic-addon1"
                    type="text"
                    name="medicineName"
                    onChange={handleChange("medicineName")}
                  />
                </InputGroup>
              </Form.Group>
            </Col>
            
                
                   <Col md={6} className="">
              <Form.Group className="mb-3" controlId="formBasicEmail">
                <Form.Label> Product Category</Form.Label>
                <InputGroup className="mb-4">
                  <InputGroup.Text id="basic-addon1"><GiMedicinePills /></InputGroup.Text>
                    <Form.Select 
                    aria-label="Default select example"
                    name="medicineCatagory"
                    onChange={handleChange("medicineCatagory")}
                    
                    >
                      <option>Open this select menu</option>
                      <option value="tablet">Tablet</option>
                      <option value="syrup">Syrup</option>
                      <option value="capsules">Capsules</option>
                      <option value="drops">Drops</option>
                      <option value="inhalers">Inhalers</option>
                      <option value="injections">Injections</option>
                      <option value="ayurvedic">Ayurvedic</option>
                      <option value="homeopathy">Homeopathy</option>
                      <option value="babyCare">Baby care</option>
                      <option value="hairCare">Hair care</option>
                      <option value="diabetes">Diabetes</option>
                      <option value="covid">Covid</option>
                      <option value="dailySupplements">Daily Supplements</option>
                      <option value="foodSupplements">Food supplements</option>
                      <option value="eyeCare">Eye Care </option>
                      
                   
                      
                    </Form.Select>
                   
                  </InputGroup>
              </Form.Group>
            </Col>
            

              
            <Col md={6} className="">
              <Form.Group className="mb-3" controlId="formBasicEmail">
                <Form.Label> Product Units</Form.Label>
                <InputGroup className="mb-4">
                  <InputGroup.Text id="basic-addon1"><MdMedicalServices /></InputGroup.Text>
                    <Form.Select 
                    aria-label="Default select example"
                    name="medicineUnits"
                    onChange={handleChange("medicineUnits")}
                    >  
                      <option>Open this select menu</option>
                     
                     
                      <option value="20">20</option>
                      <option value="40">40</option>
                      <option value="60">60</option>
                      <option value="80">80</option>
                      <option value="100">100</option>
                      <option value="120">120</option>
                      <option value="140">140</option>
                      <option value="160">160</option>
                      <option value="180">180</option>
                      <option value="200">200</option>
                    
                     
                     </Form.Select> 
                   
                  </InputGroup>
              </Form.Group>
            </Col>
            <Col md={6} className="offset-md-3">
              <Form.Group className="mb-3" controlId="formShopName">
                <Form.Label>Product Description</Form.Label>
                <InputGroup className="mb-6">
                  <InputGroup.Text id="basic-addon1"><MdDriveFileRenameOutline></MdDriveFileRenameOutline></InputGroup.Text>
                  <textarea class="form-control" rows="4"
                    placeholder="Product Description"
                    aria-label="Product Description"
                    aria-describedby="basic-addon1"
                    type="Textarea"
                    name="medicineDescription"
                    onChange={handleChange("medicineDescription")}
                    required
                  >
                    </textarea>
                </InputGroup>
              </Form.Group>
            </Col>

          </Row>
        </Form>
      </div>
    </Container>

  );
};

export default MedicineInformation;