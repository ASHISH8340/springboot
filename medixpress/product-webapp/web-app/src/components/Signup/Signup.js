import React,{ useState, useEffect, useContext } from "react";
import { FcGoogle } from "react-icons/fc";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";
import { ConfigContext } from "../../Context/ConfigContext";
import '../common.css';
import './style.css';
import {toast } from 'react-toastify';
import GoogleLogin from 'react-google-login';
import { gapi } from "gapi-script";
function Signup() {
  const navigate = useNavigate();
  const { url,endPoint, apiRoutes} = useContext(ConfigContext);
  const absoluteUrl=url+apiRoutes.signup.port+endPoint+apiRoutes.signup.controller+apiRoutes.signup.method;
  const gloginUrl = url+apiRoutes.signup.port+endPoint+apiRoutes.glogin.controller+apiRoutes.glogin.method;
  const initialValues = {emailId: "", password: "", confirmPassword:"" , name:"", terms:true,userRole:'',gender:''};
  const field={};
  const [formValues, setFormValues] = useState(initialValues);
  const [formErrors, setFormErrors] = useState({});
  const [isSubmit, setIsSubmit] = useState(false);

  const handleChange = (e) => {
    const { name, value } = e.target;
    field[name] = value;
    console.log(field);
    setFormValues({ ...formValues, [name]: value });
    // console.log(formValues);
    setFormErrors(validate(field));
    // setIsSubmit(true);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    let errors = validate(formValues);
    setFormErrors(errors);
    setIsSubmit(true);
    if(Object.keys(errors).length === 0
    && Object.getPrototypeOf(errors) === Object.prototype){    
    axios.post(`${absoluteUrl}`,formValues,).then((response)=>{
      if(response.status==200){
        toast.success("Registered successfully!");
        navigate('/login');

      }
      
  }).catch(error=>{
      
  },[]);
}
  };

  useEffect(() => {
    // console.log(formErrors);
    if (Object.keys(formErrors).length === 0 && isSubmit) {
      console.log(formValues);
    }
    function start() {
      gapi.client.init({
        clientId: clientId,
        scope: 'email',
      });
    }

    gapi.load('client:auth2', start);

  }, [formErrors]);
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
    if (values.emailId=='') {
      errors.emailId = "Email is required!";
    } else if ( values.emailId != undefined && !regex.test(values.emailId)) {
      errors.emailId = "This is not a valid email format!";
    }
    if (values.password=='') {
      errors.password = "Password is required";
    } else if ( values.password != undefined && values.password.length < 4) {
      errors.password = "Password must be more than 4 characters";
    } else if ( values.password != undefined && values.password.length > 10) {
      errors.password = "Password cannot exceed more than 10 characters";
    }
    if (values.confirmPassword=='') {
      errors.confirmPassword  = "Confirm password is required";
    } else if ( values.confirmPassword  != undefined && values.confirmPassword .length < 4) {
      errors.confirmPassword  = "Confirm password must be more than 4 characters";
    } else if ( values.confirmPassword  != undefined && values.confirmPassword .length > 10) {
      errors.confirmpassword  = "Confirm password cannot exceed more than 10 characters";
    }
   
    else if ( values.confirmPassword  != undefined && values.confirmPassword != values.password) {
      errors.confirmPassword  = "Password and confirm password not match";
    }
    // if (values.terms=='') {
    //   errors.terms = "please accepts the terms and conditions to proceed.";
    // }
    return errors;
  };
  const clientId = '720797435107-s6h2ekiap712qnkpj0cd3ntjju084hj3.apps.googleusercontent.com';
  const clientSecretId='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';

    const onSuccess=(Res)=>{
      let userData=Res.profileObj;
      let data={};
      data.emailId=userData.email;
      data.name=userData.name;
      console.log(data);
     
      axios.post(`${gloginUrl}`,data,{
        headers:{
          'Content-Type':'application/json'
        }
      }).then((response)=>{
        localStorage.setItem('email',response.data.emailId);
        localStorage.setItem('role',response.data.userRole);
            navigate('/medicines');
            toast.success('Login Succesfully')
      });
    }
    const onFailure=(Res)=>{
      toast('Currently this service is not available.')
    }
  return (
    <>
    <div className="main"><div className="sk-content">
    {/* <div className="login-form"> */}
        <div className="row signup-section">
            <div className="col-md-6 col-lg-6 login-banner">
                <div className="image-block">
                <Link to="/"><img src={process.env.PUBLIC_URL+"/assets/images/logo.png"} class="img-fluid logo-img ${3|rounded-top,rounded-right,rounded-bottom,rounded-left,rounded-circle,|}" alt=""/></Link>
                    <img src={process.env.PUBLIC_URL+"/assets/images/signupbanner1.jpg"} class="img-fluid img-banner ${3|rounded-top,rounded-right,rounded-bottom,rounded-left,rounded-circle,|}" alt=""/>
                </div>
            </div>
            <div className="col-md-12 col-lg-6 signup-container">

                <div className="card">
                  <div className="signup-card-header">
                  <h2>Create Account</h2>
                  <p>Get medicine at your door step, fast.</p>
                  </div>
                  <div className="card-body">
                  <div className="login-third-party">
             <span className="signup-google-login"> 
             <GoogleLogin
        clientId="326538060414-5vqrgaosddu857h2siom011r796tfl4j.apps.googleusercontent.com"
        buttonText="SignIn with google"
        onSuccess={onSuccess}
        onFailure={onFailure}
        className="google-btn"
      />
</span>
            </div>
            <p className="login-form-separator">
              or Sign in with Email
            </p>
            <form className="signup-form text-start" onSubmit={handleSubmit}>
            <div className="role-section">
            <div class="form-check form-check-inline">
  <input class="form-check-input" type="radio" name="userRole" id="inlineRadio1" value="BUYER"  onChange={handleChange} />
  <label class="form-check-label" htmlFor="inlineRadio1">Buyer</label>
</div>
<div class="form-check form-check-inline">
  <input class="form-check-input" type="radio" name="userRole" id="inlineRadio2" value="SELLER"  onChange={handleChange}/>
  <label class="form-check-label" htmlFor="inlineRadio2">Seller</label>
</div>
            </div>
            <div className="row d-flex form-input">
    <div className="col-md-12 col-sm-12">
      <label htmlFor="validationCustom01">Full name</label>
      <input type="text" className="form-control" id="validationCustom01" name="name" placeholder="Full name"
                        value={formValues.name}
                        onChange={handleChange} 
      />
      <p className="error">{formErrors.name}</p>
    </div>
    <div className="col-md-12 col-sm-12">
      <label htmlFor="validationCustom02">Email</label>
      <input type="text" className="form-control" id="validationCustom02" name="emailId" placeholder="Email"
                    value={formValues.emailId}
                    onChange={handleChange} />

    </div>
    <p className="error">{formErrors.emailId}</p>

  </div>
  <div className="row d-flex">
    <div className="col-md-12 col-sm-12">
      <label htmlFor="validationCustom01">Password</label>
      <input type="text" className="form-control" id="validationCustom01" name="password" placeholder="Password"
      value={formValues.password}
      onChange={handleChange} 
      />
            <p className="error">{formErrors.password}</p>
    </div>
    <div className="col-md-12 col-sm-12">
      <label htmlFor="validationCustom02">Confirm Password</label>
      <input type="text" className="form-control" id="validationCustom02" name="confirmPassword" placeholder="Confirm Password" 
       value={formValues.confirmPassword}
       onChange={handleChange} 
      />
       <p className="error">{formErrors.confirmPassword}</p>
    </div>
  </div>
  <label htmlFor="validationCustom02">Gender</label>

  <div className="role-section">
  <div class="form-check form-check-inline">
  <input class="form-check-input" type="radio" name="gender" id="inlineRadio3" value="male" />
  <label class="form-check-label" htmlFor="inlineRadio3">Male</label>
</div>
<div class="form-check form-check-inline">
  <input class="form-check-input" type="radio" name="gender" id="inlineRadio4" value="female"/>
  <label class="form-check-label" htmlFor="inlineRadio4">Female</label>
</div>
            </div>
              <div className="row">
                <div className="col-sm-12 col-md-12 terms">
                  <div className="form-check">
                    <input
                      className="form-check-input mt-1 p-0"
                      type="checkbox"
                      id="gridCheck1"
                      name="terms"
                      
                    />

                    <label className="form-check-label m-0 p-0" htmlFor="gridCheck1">
                        I Agree to terms & Conditions.
                    </label>
                    {/* <p className="error">{formErrors.terms}</p> */}

                  </div>
                </div>

              </div>

              <div className="form-group row">
                <div className="col-sm-12 form-footer text-right pr-4 flex-column">
                  <button type="submit" className="btn signup-btn-style-body text-center">
                    Create Account
                  </button>
                  <p>Already have account? <Link to='/login'>Click here to login</Link></p>
                </div>
              </div>
            </form>
          
                  </div>
                </div>
            </div>
        </div>
    {/* </div> */}
    </div></div>

    </>
  );
}
export default Signup;
