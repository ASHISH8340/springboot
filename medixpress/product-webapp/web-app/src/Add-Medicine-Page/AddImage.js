import React from "react";
import { useEffect, useState,useContext } from 'react';
import { Container, Form, InputGroup, Button, Col, Row } from "react-bootstrap";
import { BsCardImage } from "react-icons/bs";
import './AddingMedicine.css'
import { toast } from 'react-toastify';
import { MdDriveFileRenameOutline } from "react-icons/md";
import axios, * as others from 'axios'; 
import { useNavigate } from "react-router-dom";
import { ConfigContext } from "../Context/ConfigContext";

const AddImage = ({medicine,image_generation,newmedicineid,setNewmedicineid}) => {
    const navigate = useNavigate();
    console.log(newmedicineid,"newid");

    const { url,endPoint, apiRoutes} = useContext(ConfigContext);
    const imageUrl=url+apiRoutes.addimage.port+endPoint+apiRoutes.addimage.controller+apiRoutes.addimage.method;
    

  const [updateImage, setUpdateImage] = useState();

  const ImageChange = (name) => (e) => {
    const target = e.target;
    const value = target.type === 'file' ? target.files[0] : target.value;
    console.log(value);
    // const name = target.name;
    setUpdateImage(value);
  }
	const uploadImage = (Id) => {
		const formData = new FormData();
		formData.append('multipartFile', updateImage);
		formData.append('data.medicineId', Id);
		console.log(formData);
    console.log(medicine,"medicine")
        console.log(Id,"medimagepoststart")
        console.log("one")
       
		axios.post(`${imageUrl}/${Id}`,formData).then((response) => {
			console.log(response);
			console.log("sucesss")
      toast.success(" Medicine Added Successfully");
      navigate("/medicines")
		}).catch((err) => {console.log(err)})
	}

	console.log(updateImage);
  return (
    <Container>

      <div className="form-wrap">
        <h4>Medicine Image</h4>
         
            <div className="image-div">
          
          
          <Form.Group className=" uploadImg-wrap" controlId="formBasicEmail">
                                                            
																<div className='icon'>
																	<BsCardImage></BsCardImage>
																</div>
																<Form.Control
																			type="file"
																			name="updateImage"
																			onChange={ImageChange("updateImage")}
																/>
																
															</Form.Group>
															<Button className='text-center  imagebtn'onClick={() => {uploadImage(newmedicineid)}} >
                                                                
																upload image
															</Button>
                                                            </div>
														
            
                
          
            {/* <Col md={6}>
              <Form.Group className="mb-3" controlId="formShopName">
                <Form.Label>Product Name</Form.Label>
                <InputGroup className="mb-4">
                  <InputGroup.Text id="basic-addon1"><MdDriveFileRenameOutline></MdDriveFileRenameOutline></InputGroup.Text>
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
            </Col> */}
         
         
      </div>
    </Container>
   
  );
};

export default AddImage;