import React, { useEffect, useState, useContext } from 'react';
import {Container, Row, Col, Form, InputGroup, Button} from 'react-bootstrap';
import { BiSearch } from "react-icons/bi";
// import { ConfigContext } from "../Context/ConfigContext";
import { BsFillXCircleFill, BsCardImage, BsFillEyeFill } from "react-icons/bs";
import { ImCheckboxChecked } from "react-icons/im";
import { MdDelete, MdEdit } from "react-icons/md";
import { Link, useNavigate } from "react-router-dom";
import './ViewShopList.css';
import axios, * as others from 'axios'; 
import Header from '../components/Header/Header';
import { ConfigContext } from "../Context/ConfigContext";
import { ToastContainer,toast } from 'react-toastify';

function ViewShopList() {
	const navigate = useNavigate();
	const {url} = useContext(ConfigContext);

	const [ShopList, setShopList] = useState([]);
	const [searchList, setSearchList] = useState([]);
	// const [shopemail,setShopemail]=useState("");
	// const [sellerEmail, setSellerEmail] = useState();

	const [updateImage, setUpdateImage] = useState();

	const [role,setRole]=useState();

	const currTime = new Date().toLocaleTimeString;

	console.log(role);



  const loadData = async () => {
		// if(role === "SELLER"){
			try{
				if((localStorage.getItem('role')) === "SELLER"){
					const resposnse = await axios.get(`${url}/shop-service/api/v1/getBycontactEmail/${(localStorage.getItem("email"))}`);
					// http://localhost:3001/medicalShopList
					// http://localhost:8084/api/v1/getallshop'
					console.log(resposnse.data);
					setShopList(resposnse.data);
				}
				else{
					const resposnse = await axios.get(`${url}/shop-service/api/v1/getallshop`);
					// http://localhost:3001/medicalShopList
					// http://localhost:8084/api/v1/getallshop'
					console.log(resposnse.data);
					setShopList(resposnse.data);
				}
			}catch(err){
				console.log(err);
			}
		// }
	}


	useEffect(() => {
		// setSellerEmail(localStorage.getItem("email"));
		setRole(localStorage.getItem('role'));
		loadData();
	},[]);

	const handleChange = (name) => (e) => {
		const target = e.target;
		const value = target.type === 'file' ? target.files[0] : target.value;
		console.log(value);
		// const name = target.name;
		setUpdateImage(value);
	}

	const uploadImage = (shopId) => {
		const formData = new FormData();
		formData.append('multipartFile', updateImage);
		formData.append('shopid', shopId);
		console.log(formData);
		axios.post(`${url}/shop-service/api/v1/addimage/${shopId}`,formData).then((response) => {
			console.log(response);
      toast.success("Shop Image Uploaded Successfully");
     
		}).catch((err) => {console.log(err)})
    
    setTimeout(() => {
			loadData()
		},500)
	}

	console.log(updateImage);



	const deleteUser = (shopId) => {

		axios.delete(`${url}/shop-service/api/v1/deleteShop/${shopId}`).then((response) => {
      if(response.status == 200){
        toast.success(" Shop Deleted Successfully");
      }
    })

		setTimeout(() => {
			loadData()
		},500)
	}

	

	// functions
	function handlesearch(event){
		setSearchList(event.target.value)
	} 

	console.log(ShopList);

	function openAddShop(){
		navigate('/addshop');
	}

  return (
		<>
    <Header></Header>
		 <div className='Shoplist-section'>
			<Container>
				<div className='header d-flex justify-content-between align-items-center'>
					<h3>Search for shop</h3>
					{
						(role == "SELLER") &&
						<button className='btn add-shop' onClick={openAddShop}>Add Shops </button> 
					}
					
				</div>
				
				<div className='search-wrap'>
				<InputGroup className="mb-3">
					<Form.Control
						placeholder="Enter Your pin code"
						aria-label="Enter Your pin code"
						aria-describedby="basic-addon2"
						onChange={handlesearch}
						name="searchInput"
					/>
					<InputGroup.Text id="basic-addon2">
						<BiSearch></BiSearch>
					</InputGroup.Text>
				</InputGroup>
				</div>	
				
			
				{ 
					ShopList && ShopList.filter((list) => {
						if (searchList == ''){
							return list;
						}
						else if (list.pincode.toLowerCase().includes(searchList.toLowerCase())){
							return list;
						}
					}).map((list) => {
						
							
						
					return(
						
						<div className="Shoplist-wrap" key={list.shopId}>
							<Row>
								<Col md={3} className="pe-0">
								
									<div className='Shoplist-img'>
										
									{
										(list.shopImage)? 
										(<img src={"data:image/png;base64," +list.shopImage.data}  alt="shopimg" className='img-fluid'/> ) :
										(
											<div>
												{
													(role == "SELLER") && 
													(
														<>
															<Form.Group className=" uploadImg-wrap" controlId="formBasicEmail">
																<div className='icon'>
																	<BsCardImage></BsCardImage>
																</div>
																<Form.Control
																			type="file"
																			name="updateImage"
																			onChange={handleChange("updateImage")}
																/>
																
															</Form.Group>
															<Button className='text-center btn imgbtn' onClick={() => {uploadImage(list.shopId)}}>
																upload image
															</Button>
														</>
													)
												}
												
											</div>
										
									
									)
									}
										{/* <img src={list.shopImage.data}  alt="shopimg" className='img-fluid'/> */}

									</div>
								</Col>
								<Col md={6} className="ps-0">
									<div className='Shoplist-details'>
										<ul className='list-unstyled mb-0'>
											<li>
												<p><span>Shop Name </span> : {list.shopName}</p>
											</li>
											<li>
												<p><span>Shop Owner Name </span> : {list.shopOwnerName}</p>
											</li>
											<li>
												<p>
													<span>Shop verified </span> : {(list.shopVerification) ? <ImCheckboxChecked className='green'></ImCheckboxChecked>   : <BsFillXCircleFill className='red'></BsFillXCircleFill> }
												</p>
											</li>
											<li>
												<p><span>Shop address line</span> : {list.streetName}</p>
											</li>
											<li>
												<p><span>Shop Landmark </span> : {list.landmark}</p>
											</li>
											<li>
												<p><span>City Name with pincode </span> : {list.pincode} </p>
											</li>
										</ul>
									</div>
								</Col>
								<Col md={3} >
								{
									(role == "SELLER") ? 
									<div className="owner-edit-wrap">
										<ul className="list-unstyled d-flex justify-content-end">
											
											<li>
												<button 
													className='delete' 
													onClick={() => {
														// const id = list.id;
														deleteUser(list.shopId)
													}}
												>
													<MdDelete title="delete"></MdDelete>
												</button>
											</li>
										</ul>
										<ul className="list-unstyled d-flex justify-content-end">
											<li>
											<div>
												{/* <Link to="/medicines" > */}
													{/* <Button className='viewMedi btn btn-success btn-outline-success medicine-outline-btn'> */}
                          <Button className='viewMedi btn btn-success btn-outline-success medicine-outline-btn'
                            onClick={() => {
                              localStorage.setItem("shopEmail",list.shopEmail);
                              navigate('/medicines');
                            }}
                            >
														View Our medicines
													</Button>
												{/* </Link> */}
											</div>
                      <div>
												{/* <Link to="/medicines/addmedicine"> */}
													<Button className='viewMedi btn btn-success btn-outline-success medicine-outline-btn' onClick={()=>{
                                localStorage.setItem("shopEmail",list.shopEmail);
                                navigate('/medicines/addmedicine');
                                }}
                          >
														Add New Medicines
													</Button>
												{/* </Link> */}
											</div>
											</li>
										</ul>
									</div> :
									<div>
                    <div className="owner-edit-wrap">
                      <ul className="list-unstyled d-flex justify-content-end">
                        <li>
                        <div>
                          {/* <Link to="/medicines" > */}
                            {/* <Button className='viewMedi btn btn-success btn-outline-success medicine-outline-btn'> */}
                            <Button className='viewMedi btn btn-success btn-outline-success medicine-outline-btn'
                              onClick={() => {
                                localStorage.setItem("shopEmail",list.shopEmail);
                                navigate('/medicines');
                              }}
                              >
                              View Our medicines
                            </Button>
                          {/* </Link> */}
                        </div>
                        
                        </li>
                      </ul>
                    </div>
                   
                  </div>
									
								}
									
								</Col>
							</Row>

						</div>
					);
				})

				}
				

			</Container>
		 </div>
		
		</>
		
  );
}

export default ViewShopList;