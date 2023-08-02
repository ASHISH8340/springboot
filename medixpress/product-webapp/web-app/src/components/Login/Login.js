import React,{ useState, useEffect, useContext } from "react";
import { FcGoogle } from 'react-icons/fc'
import { Link, useNavigate } from "react-router-dom";
import './style.css';
import '../common.css';
import axios from "axios";
import { ConfigContext } from "../../Context/ConfigContext";
import {toast } from 'react-toastify';
import GoogleLogin from 'react-google-login';
import { gapi } from "gapi-script";
function Login() {
  const navigate = useNavigate();
  const { url,endPoint, apiRoutes} = useContext(ConfigContext);
  const absoluteUrl=url+apiRoutes.login.port+endPoint+apiRoutes.login.controller+apiRoutes.login.method;
  const gloginUrl = url+apiRoutes.signup.port+endPoint+apiRoutes.glogin.controller+apiRoutes.glogin.method;
  const absoluteShopUrl=url+apiRoutes.fetchShop.port+endPoint+apiRoutes.fetchShop.controller+apiRoutes.fetchShop.method;
  const initialValues = {username: "", password: "" };
  const field={};
  const [formValues, setFormValues] = useState(initialValues);
  const [formErrors, setFormErrors] = useState({});
  const [isSubmit, setIsSubmit] = useState(false);
  const handleChange = (e) => {
    const { name, value } = e.target;
    field[name] = value;
    setFormValues({ ...formValues, [name]: value });
    setFormErrors(validate(field));
  };
  const handleSubmit = (e) => {
    e.preventDefault();
    let errors = validate(formValues);
    setFormErrors(errors);
    setIsSubmit(true);
    if(Object.keys(errors).length === 0
    && Object.getPrototypeOf(errors) === Object.prototype){     
         axios.post(`${absoluteUrl}`,formValues,{
          headers:{
            'Access-Control-Allow-Origin': '*',
            // 'Content-Type': 'application/json',
          }
         }).then((response)=>{
          if(response.status == 200){
              if(response.data.accessToken){
                let userEmail = response.data.username;
                localStorage.setItem('email',userEmail);
                localStorage.setItem('role',response.data.role);
                if(response.data.role == 'BUYER'){
                toast.success("Login successfully!");
                localStorage.setItem('shopEmail', "undefined");
                navigate('/medicines');
                }else{
                  axios.get(`${absoluteShopUrl}/${userEmail}`,).then((response)=>{
                    console.log(response);
                    if(response.status == 200){
                      toast.success("Login successfully!");
                      navigate('/profile');

                    }
                  }).catch((error)=>{
                        if(error.response.status == 404){
                          toast.success("Login successfully!");
                          navigate('/addshop');
                        }
                        if(error.response.status == 500){
                          toast.success("Login successfully!");
                          navigate('/addshop');
                        }
                  },[]);

                }
              }
          }
          
      }).catch((error)=>{
        let errors={};
        errors.password=error.response.data.errormsg;
        setFormErrors(errors);
      },[]);
    }

  };

  useEffect(() => {
    function start() {
      gapi.client.init({
        clientId: clientId,
        scope: 'email',
      });
    }
    gapi.load('client:auth2', start);

    }, [formErrors]);
  const validate = (values) => {
    const errors = {};
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/i;
    if (values.username=='') {
      errors.username = "Email is required!";
    } else if ( values.username != undefined && !regex.test(values.username)) {
      errors.username = "This is not a valid email format!";
    }
    if (values.password=='') {
      errors.password = "Password is required";
    }
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
    <div className="main">
      <div className="sk-content">
    <div className="login-form">
      <div className="login-section">
        <div className="row">
            <div className="col-md-6 col-lg-6 login-banner">
                <div className="image-block">
                  <Link to="/"><img src={process.env.PUBLIC_URL+"/assets/images/logo.png"} class="img-fluid logo-img ${3|rounded-top,rounded-right,rounded-bottom,rounded-left,rounded-circle,|}" alt=""/></Link>
                    <img src={process.env.PUBLIC_URL+"/assets/images/loginbanner.jpg"} class="img-fluid img-banner ${3|rounded-top,rounded-right,rounded-bottom,rounded-left,rounded-circle,|}" alt=""/>
                </div>
            </div>
            <div className="col-md-6 col-lg-6  login-container">

        <div class="card text-left">
          <div className="login-card-header">
            <h2>Login</h2>
            <p>Wish you good health!</p>
          </div>
          <div class="card-body">
          <div className="login-third-party">
             <span className="google-login">
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
            <form className="text-start login-form-card" onSubmit={handleSubmit}>
              <div className="form-group">
                <label
                  htmlFor="inputEmail3"
                  className="col-sm-12 col-form-label"
                >
                  Email
                </label>
                <div className="col-sm-12">
                  <input
                    type="text"
                    name="username"
                    
                    className="form-control"
                    id="inputEmail3"
                    placeholder="Email"
                    value={formValues.username}
                    onChange={handleChange}
                  />
                  <p className="error">{formErrors.username}</p>
                </div>
              </div>
              <div className="form-group">
                <label
                  htmlFor="inputPassword3"
                  className="col-sm-12 col-form-label"
                >
                  Password
                </label>
                <div className="col-sm-12">
                  <input
                    type="password"
                    name="password"
                    value={formValues.password}
                    className="form-control"
                    id="inputPassword3"
                    placeholder="Password"
                    onChange={handleChange}
                  />
                  <p className="error">{formErrors.password}</p>
                </div>
              </div>
              <div className="form-group row">
                <div className="col-sm-6 col-md-6 remember">
                  <div className="form-check">
                    <input
                      className="form-check-input"
                      type="checkbox"
                      id="gridCheck1"
                    />
                    <label className="form-check-label" htmlFor="gridCheck1">
                      Remember me
                    </label>
                  </div>
                </div>
                <div className="col-sm-6 col-md-6 forgot-pass">
                  <div className="form-check">
                    <a href="#" target="_blank">
                      Forget password ?
                    </a>
                  </div>
                </div>
              </div>

              <div className="form-group row">
                <div className="col-sm-12 ml-1 form-footer text-right flex-column">
                  <button type="submit" className="btn signup-btn-style-body text-center">
                    LogIn
                  </button>
                  <p>Not registered yet? <Link to='/signup'>Create an Account</Link></p>
                </div>
                
              </div>
            </form>

          </div>
        </div>
            </div>
        </div>
        </div>
    </div>
    </div>
    </div>
  );
}
export default Login;
