import React,{ useState, useEffect,useContext } from "react";
import Sidebar from "./Sidebar";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { ConfigContext } from "../../Context/ConfigContext";
import "./style.css";
import '../common.css';
import $ from 'jquery';
import Header from "../Header/Header";
import {toast } from 'react-toastify';

function Profile() {
  const navigate = useNavigate();
  const [authenticated, setAuthenticated] = useState(null);
  const [role,setRole]=useState(null);
  const { url,endPoint, apiRoutes} = useContext(ConfigContext);
  const absoluteUrl=url+apiRoutes.profile.port+endPoint+apiRoutes.profile.controller+apiRoutes.profile.method;
  const absoluteUpdateProfileUrl=url+apiRoutes.updateProfile.port+endPoint+apiRoutes.updateProfile.controller+apiRoutes.updateProfile.method;
  const absoluteUpdateAddressUrl=url+apiRoutes.updateAddress.port+endPoint+apiRoutes.updateAddress.controller+apiRoutes.updateAddress.method;
  const absoluteUpdateImageUrl=url+apiRoutes.updateImage.port+endPoint+apiRoutes.updateImage.controller+apiRoutes.updateImage.method;

  const initialValues = {name:"",contactNo:"",gender:''};
  const field={};
  const [formValues, setFormValues] = useState(initialValues);
  const initialAddressValues = {streetName:"",doorNumber:"",landmark:'',state:'',city:"",pinCode:''};
  const [adressFormValues, setAdressFormValues] = useState(initialAddressValues);
  const [file,setFile]=useState();
  const [formErrors, setFormErrors] = useState({});
  const [isSubmit, setIsSubmit] = useState(false);

  const [detail,setDetail] = useState(null);
  useEffect(() => {
    const loggedInUser = localStorage.getItem('email');
    let role = localStorage.getItem('role');
    setRole(role);
    if (loggedInUser) {
      setAuthenticated(loggedInUser);
    }
    if (!loggedInUser || loggedInUser == null) {
      navigate('../login',{ replace: true });
     }
     axios.get(`${absoluteUrl}/${loggedInUser}`).then((response)=>{
      // console.log(response);
      if(response.status == 200){
        setDetail(response.data);
        // setFormValues(response.data);
        formValues.name=response.data.name;
        formValues.contactNo=response.data.contactNo;
        formValues.gender=response.data.gender;
        formValues.emailId=response.data.emailId;
        console.log(response.data);
        setAdressFormValues(response.data.address[0]);

      }
},[]);
  },[]);
  const handleChange = (e) => {
    const { name, value } = e.target;
    field[name] = value;
    console.log(field);
    setFormValues({ ...formValues, [name]: value });
    // console.log(formValues);
    setFormErrors(validate(field));
    // setIsSubmit(true);
  };
  const handleChangeProfile  = (e) => {
    // setFile();
    // console.log(file);
    const formData = new FormData();
		formData.append('multipartFile', e.target.files[0]);
    formData.append('email', detail.emailId);
    axios.post(`${absoluteUpdateImageUrl}`,formData).then((response)=>{
      if(response.status==200){
        setDetail(response.data);
        localStorage.setItem('image',response.data.shopImage.data);
        toast.success("Updated successfully!");
      }
  }).catch(error=>console.log(`Error:${error}`),[]);

  };
  const handleAdressChange=(e)=>{
    const { name, value } = e.target;
    field[name] = value;
    console.log(field);
    setAdressFormValues({...adressFormValues, [name]: value })
  };
  
  var hideModal = () => {
    $("#exampleModal").modal("hide");
  };
  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(formValues);

    let errors = validate(formValues);
    setFormErrors(errors);
    setIsSubmit(true);
    if(Object.keys(errors).length === 0
    && Object.getPrototypeOf(errors) === Object.prototype){    
    axios.put(`${absoluteUpdateProfileUrl}/${formValues.emailId}`,formValues,).then((response)=>{
      if(response.status==200){
        formValues.name=response.data.name;
        formValues.contactNo=response.data.contactNo;
        formValues.gender=response.data.gender;
        formValues.emailId=response.data.emailId;
        setDetail(response.data)
        toast.success("Updated successfully!");
      }
      
  }).catch(error=>console.log(`Error:${error}`),[]);
}
  };
  const handleAddressSubmit = (e) => {
    e.preventDefault();
    console.log("hello");

    let errors = validateAdress(adressFormValues);
    setFormErrors(errors);
    setIsSubmit(true);
    if(Object.keys(errors).length === 0
    && Object.getPrototypeOf(errors) === Object.prototype){    
    axios.put(`${absoluteUpdateAddressUrl}/${detail.emailId}`,{
      address:[
        adressFormValues
      ]
    },).then((response)=>{
      if(response.status==200){
        setFormValues(response.data);
        hideModal();
        toast.success("Updated successfully!");
      }
      
  }).catch(error=>console.log(`Error:${error}`),[]);
}
  };
  const validate = (values) => {
    // console.log(values);
    const errors = {};
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/i;
    const nameRegex=/^[a-zA-Z ]+$/;
    if (values.name=='') {
      errors.name = "Name is required!";
    } else if ( values.name != undefined && !nameRegex.test(values.name)) {
      errors.name = "This is not a valid name format!";
    }
    if (values.gender=='') {
      errors.gender = "Gender is required!";
    }
    if (values.contactNo=='') {
      errors.contactNo = "Name is required!";
    }else if(values.contactNo.length > 10 || values.contactNo.length < 10){
      errors.contactNo = "contact no. should be length 10";
    }
    // if (values.terms=='') {
    //   errors.terms = "please accepts the terms and conditions to proceed.";
    // }
    return errors;
  };
  const validateAdress = (values) => {
    // console.log(values);
    const errors = {};
    // const regex = /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/i;
    // const nameRegex=/^[a-zA-Z ]+$/;
    if (values.streetName=='') {
      errors.streetName = "Street Name is required!";
    }
    if (values.doorNumber=='') {
      errors.doorNumber = "Door Number is required!";
    }
    if(values.doorNumber!='undefined' && isNaN(values.doorNumber)){
      errors.doorNumber = "It should be number!";
    }
    if (values.landmark=='') {
      errors.landmark = "Landmark is required!";
    }
    if (values.state=='') {
      errors.state = "state is required!";
    }
    if (values.city=='') {
      errors.city = "city is required!";
    }
    if (values.pinCode=='') {
      errors.pinCode = "pincode is required!";
    }
    // if (values.terms=='') {
    //   errors.terms = "please accepts the terms and conditions to proceed.";
    // }
    return errors;
  };
if(detail){
  return (
    <>
    <Header image={detail.shopImage && detail.shopImage.data}/>
    <div className="Profile-content">
    <div className="dashboard-wrapper pt-5">
      <Sidebar name={detail.name} image={detail.shopImage && detail.shopImage.data}/>
      <section class="dashboard-contentwrap">
        <section class="asset split about profile-section" id="about-section">
          <p class="title pl-5 pt-5 pb-1 mt-3  ">
          <span>Profile</span>

          </p>
          <div class="row">
            <div className="section-item">
            <form className="center" onSubmit={handleSubmit}>
              <div class="form-row d-flex justify-content-around g-2 p-2">
                <div class="form-group col-md-12">
                  <label for="inputAddress">Fullname</label>
                  <input
                    type="text"
                    class="form-control"
                    id="inputAddress"
                    placeholder="Name"
                    value={formValues.name}
                    onChange={handleChange} 
                    name="name"
  />
  <p className="error">{formErrors.name}</p>

                </div>
                <div class="form-group col-md-12">
                  <label for="inputEmail4">Email</label>
                  <input
                    type="emailId"
                    class="form-control"
                    value={detail.emailId}
                    id="inputEmail4"
                    email="emailId"
                    placeholder="Email"
                    disabled={'true'}
                  />
                </div>
              </div>

              <div className="row justify-content-center align-items-center g-2 gender ml-2">
              <div class="form-group col-md-12 mr-3">
                  <label for="inputEmail4">Contact</label>
                  <input
                    type="number"
                    class="form-control"
                    value={formValues.contactNo}
                    name="contactNo"
                    id="inputEmail4"
                    placeholder="Contact"
                    onChange={handleChange} 
          />
  <p className="error">{formErrors.contactNo}</p>
                </div>
                <div className="col">
                <label className="form-check-label mr-5 mb-2 " htmlFor="gender">
                      Gender
                </label>
                  <div className="form-check">
                    <input
                      className="form-check-input"
                      type="radio"
                      name="gender"
                      id="gender5"
                      value="male"
                      onChange={handleChange} 
                      checked={formValues.gender=='male'}
                    />
                    <label className="form-check-label mr-5" htmlFor="gender5">
                      Male
                    </label>
                    <input
                      className="form-check-input"
                      type="radio"
                      name="gender"
                      id="gender6"
                      value="female"
                      onChange={handleChange}
                      checked={formValues.gender=='female'}
                    //   {detail.gender == 'male' && "checked"}
                    />
                    <label className="form-check-label" htmlFor="gender6">
                      Female
                    </label>
                  </div>
                </div>
                <div class="form-group col-md-12 mr-3">
                  <label for="inputEmail4">Contact</label>
                  <input
                    type="file"
                    class="form-control"
                    name="profile-file"
                    id="inputEmail4"
                    placeholder="Contact"
                    onChange={handleChangeProfile} 
          />
  <p className="error">{formErrors.contactNo}</p>
                </div>
              </div>
              <div className="form-group row">
                <div className="col-sm-12 form-footer text-right pr-4">
                  <button type="submit" className="btn btn-style text-center">
                    Update
                  </button>
                </div>
              </div>
            </form>
          </div>
          </div>
        </section>
      {
        role=='BUYER' &&         
        <section class="asset split about" id="about-section">
        <p class="title section-item">
          <span>Addresses <button type="button" class="" data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo"><i class="fa fa-pencil ms-5"></i></button>
</span>
        </p>
        <div class="row">
          {/* <div class="section-item"> */}
              
            <div class="card col-md-4 m-3">
              <div class="card-body">
                <h5 class="card-title">
                  <i class="fa fa-home" aria-hidden="true"></i> Home
                </h5>
                <p class="card-text">
                  H.No. {adressFormValues.doorNumber} {adressFormValues.streetName}, {adressFormValues.landmark}, {adressFormValues.city}, {adressFormValues.state}, {adressFormValues.pinCode}
                </p>
                <a href="#" class="btn btn-style">
                  Default
                </a>
              </div>
            </div>
          {/* </div> */}
        </div>
        <div class="modal fade addressupdate" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
<div class="modal-dialog" role="document">
  <div class="modal-content">
    <div class="modal-header">
      <h5 class="modal-title" id="exampleModalLabel">Update Adress</h5>
      <button type="button" class="close" data-dismiss="modal" aria-label="Close">
        <span aria-hidden="true">&times;</span>
      </button>
    </div>
    <div class="modal-body adress-form-section">
      <form onSubmit={handleAddressSubmit}>
        <div class="form-group">
          <label for="recipient-name" class="col-form-label">Street:</label>
          <input type="text" name="streetName"  value={adressFormValues.streetName}
                    onChange={handleAdressChange}  class="form-control" id="recipient-name"/>
          <p className="error">{formErrors.streetName}</p>

        </div>
        <div class="form-group">
          <label for="recipient-name" class="col-form-label">Door Number:</label>
          <input type="text" name="doorNumber" value={adressFormValues.doorNumber}
                    onChange={handleAdressChange} class="form-control" id="recipient-name"/>
                  <p className="error">{formErrors.doorNumber}</p>

        </div>
        <div class="form-group">
          <label for="recipient-name" class="col-form-label">Landmark:</label>
          <input type="text" name="landmark" value={adressFormValues.landmark}
                    onChange={handleAdressChange} class="form-control" id="recipient-name"/>
                    <p className="error">{formErrors.landmark}</p>

        </div>
        <div class="form-group">
          <label for="recipient-name" class="col-form-label">State:</label>
          <input type="text" name="state" value={adressFormValues.state}
                    onChange={handleAdressChange} class="form-control" id="recipient-name"/>
                <p className="error">{formErrors.state}</p>

        </div>
        <div class="form-group">
          <label for="recipient-name" class="col-form-label">City:</label>
          <input type="text" name="city" value={adressFormValues.city}
                    onChange={handleAdressChange} class="form-control" id="recipient-name"/>
                                    <p className="error">{formErrors.city}</p>

        </div>
        <div class="form-group">
          <label for="recipient-name" class="col-form-label">Pincode:</label>
          <input type="text" name="pinCode" value={adressFormValues.pinCode}
                    onChange={handleAdressChange}  class="form-control" id="recipient-name"/>
                    <p className="error">{formErrors.pinCode}</p>
        </div>
        <div className="form-group text-end">
        <button type="submit" class="btn btn-style">Update</button>

        </div>
      </form>
    </div>
  </div>
</div>
</div>
      </section>
      }
      </section>
    </div>
    </div>
    </>
  );
  }
}
export default Profile;
