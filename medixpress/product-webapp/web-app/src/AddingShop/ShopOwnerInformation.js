import React from "react";
import { Container, Form, InputGroup, Button, Col, Row } from "react-bootstrap";
import { BsAt, BsKeyFill, BsFillTelephoneFill } from "react-icons/bs";
import { BiLogIn, BiUser, BiPhoneCall } from "react-icons/bi";

const ShopOwnerInformation = ({ handleChange }) => {
  return (
    <Container>
      <div className="form-wrap">
        <h4>Shop Owner Details </h4>
          <Form>
          <Row>
            <Col md={4}>
              <Form.Group className="mb-3" controlId="formShopName">
                <Form.Label>Owner Name</Form.Label>
                <InputGroup className="mb-4">
                  <InputGroup.Text id="basic-addon1"><BiUser></BiUser></InputGroup.Text>
                    <Form.Control
                      placeholder="Owner Name"
                      aria-label="Owner Name"
                      aria-describedby="basic-addon1"
                      type="text"
                      name="shopOwnerName"
                      onChange={handleChange("shopOwnerName")}
                    />
                  </InputGroup>
              </Form.Group>
            </Col>
          </Row>  
          <Row>
            <Col md={4} className="offset-md-4">
              <Form.Group className="mb-3" controlId="formBasicEmail">
                <Form.Label> Contact Number</Form.Label>
                <InputGroup className="mb-4">
                  <InputGroup.Text id="basic-addon1"><BiPhoneCall></BiPhoneCall></InputGroup.Text>
                    <Form.Control
                      placeholder="Contact Number"
                      aria-label="Contact Number"
                      aria-describedby="basic-addon1"
                      type="text"
                      name="contactNumber"
                      onChange={handleChange("contactNumber")}
                    />
                  </InputGroup>
              </Form.Group>
            </Col>
          </Row>
          <Row>
            <Col  md={4} className="offset-md-8">
            <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label> Contact Email</Form.Label>
                <InputGroup className="mb-4">
                  <InputGroup.Text id="basic-addon1"><BsAt></BsAt></InputGroup.Text>
                    <Form.Control
                     
                      type="email"
                      name="contactEmail"
                      onChange={handleChange("contactEmail")}
                      value={localStorage.getItem("email")}
                      disabled
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

export default ShopOwnerInformation;