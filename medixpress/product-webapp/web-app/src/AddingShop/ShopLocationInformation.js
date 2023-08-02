import React from "react";
import { Container, Form, InputGroup, Button, Col, Row } from "react-bootstrap";
import { BsAt, BsKeyFill, BsFillTelephoneFill } from "react-icons/bs";
import { BiLogIn, BiUser, BiStreetView } from "react-icons/bi";
import { AiOutlineNumber } from "react-icons/ai";
import { FaCity } from "react-icons/fa";

const ShopLocationInformation = ({ handleChange }) => {
  return (
    <Container>
      <div className="form-wrap">
        <h4>Shop Location Details </h4>
          <Form>
          <Row>
            <Col md={6}>
              <Form.Group className="mb-3" controlId="formShopName">
                <Form.Label>Shop Number</Form.Label>
                <InputGroup className="mb-4">
                  <InputGroup.Text id="basic-addon1">
                    <AiOutlineNumber></AiOutlineNumber>
                  </InputGroup.Text>
                    <Form.Control
                      placeholder="Enter your shop number"
                      aria-label="Enter your shop number"
                      aria-describedby="basic-addon1"
                      type="text"
                      name="shopNumber"
                      onChange={handleChange("shopNumber")}
                    />
                  </InputGroup>
              </Form.Group>
            </Col>
            <Col md={6}>
              <Form.Group className="mb-3" controlId="formShopName">
                <Form.Label>Street Name</Form.Label>
                <InputGroup className="mb-4">
                  <InputGroup.Text id="basic-addon1"><BiStreetView></BiStreetView></InputGroup.Text>
                    <Form.Control
                      placeholder="Enter Street Name"
                      aria-label="Enter Street Name"
                      aria-describedby="basic-addon1"
                      type="text"
                      name="streetName"
                      onChange={handleChange("streetName")}
                    />
                  </InputGroup>
              </Form.Group>
            </Col>
            <Col md={6}>
              <Form.Group className="mb-3" controlId="formShopName">
                <Form.Label>Landmark</Form.Label>
                <InputGroup className="mb-4">
                  <InputGroup.Text id="basic-addon1"><BiStreetView></BiStreetView></InputGroup.Text>
                    <Form.Control
                      placeholder="Enter Landmark"
                      aria-label="Enter Landmark"
                      aria-describedby="basic-addon1"
                      type="text"
                      name="landmark"
                      onChange={handleChange("landmark")}
                    />
                  </InputGroup>
              </Form.Group>
            </Col>
            <Col md={6}>
              <Form.Group className="mb-3" controlId="formShopName">
                <Form.Label>city</Form.Label>
                <InputGroup className="mb-4">
                  <InputGroup.Text id="basic-addon1"><FaCity></FaCity></InputGroup.Text>
                    <Form.Control
                      placeholder="Enter city"
                      aria-label="Enter city"
                      aria-describedby="basic-addon1"
                      type="text"
                      name="city"
                      onChange={handleChange("city")}
                    />
                  </InputGroup>
              </Form.Group>
            </Col>
            <Col md={6}>
              <Form.Group className="mb-3" controlId="formShopName">
                <Form.Label>state</Form.Label>
                <InputGroup className="mb-4">
                  <InputGroup.Text id="basic-addon1"><FaCity></FaCity></InputGroup.Text>
                    <Form.Control
                      placeholder="Enter state"
                      aria-label="Enter state"
                      aria-describedby="basic-addon1"
                      type="text"
                      name="state"
                      onChange={handleChange("state")}
                    />
                  </InputGroup>
              </Form.Group>
            </Col>
            <Col md={6}>
              <Form.Group className="mb-3" controlId="formShopName">
                <Form.Label>pinCode</Form.Label>
                <InputGroup className="mb-4">
                  <InputGroup.Text id="basic-addon1"><FaCity></FaCity></InputGroup.Text>
                    <Form.Control
                      placeholder="Enter pinCode"
                      aria-label="Enter pinCode"
                      aria-describedby="basic-addon1"
                      type="text"
                      name="pincode"
                      onChange={handleChange("pincode")}
                    />
                  </InputGroup>
              </Form.Group>
            </Col>
          </Row>  
          </Form>
      </div>
    </Container>
   
  );
};

export default ShopLocationInformation;