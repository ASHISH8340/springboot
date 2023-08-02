import React from "react";
import { Container, Form, InputGroup, Button, Col, Row } from "react-bootstrap";

import { FaRupeeSign } from "react-icons/fa";
import { BsFillCalendar2RangeFill} from "react-icons/bs";

const MedicinePriceInformation = ({ handleChange }) => {
  return (
    <Container>
      <div className="form-wrap">
        <h4>Medicine Price Details </h4>
          <Form>
          <Row>
            <Col md={6}>
              <Form.Group className="mb-3" >
                <Form.Label>MRP Price</Form.Label>
                <InputGroup className="mb-4">
                  <InputGroup.Text id="basic-addon1">
                  <FaRupeeSign />
                  </InputGroup.Text>
                    <Form.Control
                      placeholder="Enter the MRP Price"
                      aria-label="Enter the MRP Price"
                      aria-describedby="basic-addon1"
                      type="text"
                      name="medicinePrice"
                      onChange={handleChange("medicinePrice")}
                    />
                  </InputGroup>
              </Form.Group>
            </Col>
            <Col md={6}>
              <Form.Group className="mb-3" controlId="formShopName">
                <Form.Label>Best Price`</Form.Label>
                <InputGroup className="mb-4">
                  <InputGroup.Text id="basic-addon1"><FaRupeeSign /></InputGroup.Text>
                    <Form.Control
                      placeholder="Enter Best Price"
                      aria-label="Enter Best Price"
                      aria-describedby="basic-addon1"
                      type="text"
                      name="medicineDiscountprice"
                      onChange={handleChange("medicineDiscountprice")}
                    />
                  </InputGroup>
              </Form.Group>
            </Col>
            <Col md={6}>
              <Form.Group className="mb-3" controlId="formShopName">
                <Form.Label>Manufacturing Date</Form.Label>
                <InputGroup className="mb-4">
                  <InputGroup.Text id="basic-addon1"><BsFillCalendar2RangeFill></BsFillCalendar2RangeFill></InputGroup.Text>
                    <Form.Control
                      placeholder="Enter Manufacturing Date"
                      aria-label="Enter Manufacturing Date"
                      aria-describedby="basic-addon1"
                      type="date"
                      name="manafacturedate"
                      onChange={handleChange("manafacturedate")}
                    />
                  </InputGroup>
              </Form.Group>
            </Col>
            <Col md={6}>
              <Form.Group className="mb-3" controlId="formShopName">
                <Form.Label>Expiry Date</Form.Label>
                <InputGroup className="mb-4">
                  <InputGroup.Text id="basic-addon1"><BsFillCalendar2RangeFill></BsFillCalendar2RangeFill></InputGroup.Text>
                    <Form.Control
                      placeholder="Enter Expiry Date"
                      aria-label="Enter Expiry Date"
                      aria-describedby="basic-addon1"
                      type="date"
                      name="expireydate"
                      onChange={handleChange("expireydate")}
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

export default MedicinePriceInformation;