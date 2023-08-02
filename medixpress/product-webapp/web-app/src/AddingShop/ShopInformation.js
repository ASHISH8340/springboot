import React, { useState } from "react";
import { Container, Form, InputGroup, Button, Col, Row } from "react-bootstrap";
import { BsAt, BsKeyFill, BsCardImage } from "react-icons/bs";
import { BiTime, BiUser } from "react-icons/bi";
import { MdDriveFileRenameOutline } from "react-icons/md";


const ShopInformation = ( { handleChange, shop, handleChecked } ) => {

 

  

  return (
    <Container>
      <div className="form-wrap">
        <h4>Shop Info </h4>
          <Form>
          <Row>
            <Col md={6}>
              <Form.Group className="mb-3" controlId="formShopName">
                <Form.Label>Shop Name</Form.Label>
                <InputGroup className="mb-4">
                  <InputGroup.Text id="basic-addon1"><MdDriveFileRenameOutline></MdDriveFileRenameOutline></InputGroup.Text>
                    <Form.Control
                      placeholder="Shop Name"
                      aria-label="Shop Name"
                      aria-describedby="basic-addon1"
                      type="text"
                      name="shopName"
                      onChange={handleChange("shopName")}
                      value={shop.shopName}
                    />
                  </InputGroup>
              </Form.Group>
            </Col>
            <Col md={6}>
              <Form.Group className="mb-3" controlId="formShopName">
                <Form.Label>Shop Email</Form.Label>
                <InputGroup className="mb-4">
                  <InputGroup.Text id="basic-addon1"><BsAt></BsAt></InputGroup.Text>
                    <Form.Control
                      placeholder="Shop Email"
                      aria-label="Shop Email"
                      aria-describedby="basic-addon1"
                      type="email"
                      name="shopEmail"
                      onChange={handleChange("shopEmail")}
                    />
                  </InputGroup>
              </Form.Group>
            </Col>
            
          </Row>
          <Row>
            <Col md={4} className="">
              <Form.Group className="mb-3" controlId="formBasicEmail">
                <Form.Label> Shop Opening Timing</Form.Label>
                <InputGroup className="mb-4">
                  <InputGroup.Text id="basic-addon1"><BiTime></BiTime></InputGroup.Text>
                    <Form.Select 
                    aria-label="Default select example"
                    name="ShopOpeningTime"
                    onChange={handleChange("ShopOpeningTime")}
                    >
                      <option>Open this select menu</option>
                      <option value="12AM">12-AM</option>
                      <option value="1AM">1-AM</option>
                      <option value="2AM">2-AM</option>
                      <option value="3AM">3-AM</option>
                      <option value="4AM">4-AM</option>
                      <option value="5AM">5-AM</option>
                      <option value="6AM">6-AM</option>
                      <option value="7AM">7-AM</option>
                      <option value="8AM">8-AM</option>
                      <option value="9AM">9-AM</option>
                      <option value="10AM">10-AM</option>
                      <option value="11AM">11-AM</option>
                    </Form.Select>
                   
                  </InputGroup>
              </Form.Group>
            </Col>
            <Col md={4}   className="">
              <Form.Group className="mb-3" controlId="formShopName">
                <Form.Label className="text-center d-block">Shop Verification</Form.Label>
                    <Form.Check
                      type="checkbox"
                      label="Yes"
                      name="shopVerification"
                      // value={shop.shopVerification}
                      onChange={handleChange("shopVerification")}
                    />
              </Form.Group>
            </Col>
            <Col md={4} className="">
              <Form.Group className="mb-3" controlId="formBasicEmail">
                <Form.Label> Shop closeing Timing</Form.Label>
                <InputGroup className="mb-4">
                  <InputGroup.Text id="basic-addon1"><BiTime></BiTime></InputGroup.Text>
                    <Form.Select 
                    aria-label="Default select example"
                    name="ShopCloseTime"
                    onChange={handleChange("ShopCloseTime")}
                    >
                      <option>Open this select menu</option>
                      <option value="12PM">12-PM</option>
                      <option value="1PM">1-PM</option>
                      <option value="2PM">2-PM</option>
                      <option value="3PM">3-PM</option>
                      <option value="4PM">4-PM</option>
                      <option value="5PM">5-PM</option>
                      <option value="6PM">6-PM</option>
                      <option value="7PM">7-PM</option>
                      <option value="8PM">8-PM</option>
                      <option value="9PM">9-PM</option>
                      <option value="10PM">10-PM</option>
                      <option value="11PM">11-PM</option>
                    </Form.Select>
                   
                  </InputGroup>
              </Form.Group>
            </Col>
            
            
           
          </Row>
          </Form>
      </div>
    </Container>
   
  );
}

export default ShopInformation;