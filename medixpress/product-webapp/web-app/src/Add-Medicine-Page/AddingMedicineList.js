import React, { useEffect, useState, useContext } from 'react';
import {Container, Button} from 'react-bootstrap';
import { BiLogIn, BiLogOut } from "react-icons/bi";
import './AddingMedicine.css'
import { toast } from 'react-toastify';
import MedicinePriceInformation from './MedicinePriceInformation';
import ConfirmationMedicineInfo from './ConfirmationMedicineInfo';
import { Link, useNavigate } from "react-router-dom";
import axios, * as others from 'axios'; 
import MedicineInformation from './MedicineInfo';
import AddImage from './AddImage';
import Header from '../components/Header/Header';
import MedicineStepperHeader from './MedicineStepperHeader';
import { ConfigContext } from "../Context/ConfigContext";
// import { MediValid} from './MedicineValidation';

function AddingMedicineList() {
  const navigate = useNavigate();
  const [newmedicineid,setNewmedicineid]=useState("");
  const { url,endPoint, apiRoutes} = useContext(ConfigContext);
  const addmedicineUrl=url+apiRoutes.addmedicine.port+endPoint+apiRoutes.addmedicine.controller+apiRoutes.addmedicine.method;
  
	const [medicine ,setMedicine] = useState({
    shopName:"",
    shopEmail:localStorage.getItem("shopEmail"),
    medicineName:"",
    medicineCatagory:"",
    medicineUnits:"",
    medicineDescription:"",
    medicineDiscountprice:"",
    medicinePrice:"",
    manufacturedate:"",
    expireydate:""
  
   

  });
  const intialValues = { 
  medicineName:"",
  medicineCatagory:"",
  medicineUnits:"",
  medicineDescription:""};

   const [shopImage, setShopImage] = useState({});
  const [formValues, setFormValues] = useState(intialValues);
  const [formErrors, setFormErrors] = useState({});
  const [isSubmitting, setIsSubmitting] = useState(false);

	const [step, setStep] = useState(1);
  let image_generation;

	const nextStep = () => {

    
    if(step === 3) {
    console.log("3")
        axios.post(`${addmedicineUrl}`,
        medicine
        
      ).then((response) => {
        // navigate('/medicines');
        console.log(response)
        let image_generation=response;
        console.log(image_generation,"imagegenaerate")
        var meddata=image_generation.data.medicineId
        console.log(meddata,"medicineid")
        setNewmedicineid(response.data.medicineId);

      }).catch((err) => {
        console.log(err);
      })
      setStep(step + 1);
      console.log("step4")
    }
    
    else if(step === 4) {
      console.log("4")
        
      // axios.post('http://localhost:9192/api/v1/addimage/',
      // medicine,
      
    // ).then((response) => {
    //   //  navigate('/medicines')
    //   console.log(response)
    // }).catch((err) => {
    //   console.log(err);
    // })
    
   }
  else  {
    setStep(step + 1);
    console.log("2")
  } 
  };

  console.log(step);

	const prevStep = () => {
    if (step > 1) {
      setStep(step - 1);
    }
  };

// 	const ValidateOne=async(event)=>{
//     console.log("validstart");
//     let formData={
//       shopEmail:medicine.shopEmail,
//       medicineName:medicine.medicineName,
//       medicineCatagory:medicine.medicineCatagory,
//       medicineUnits:medicine.medicineUnits,
//       medicineDescription:medicine.medicineDescription
   
//     }
//     console.log(formData,"formData")
//     //  const isValid=await MediValid.isValid(formData)
//      console.log(isValid)
//      console.log("validend");

//      if(isValid===true){
//      setStep(step + 1)
//   }
// }

  const handleChange = (name) => (e) => {
    const target = e.target;
    const value = target.type === 'checkbox' ? target.checked : target.value;
    console.log(value);
    const name = target.name;
    setMedicine({ ...medicine, [name]: value });
    // const { name, value } = e.target;
    setFormValues({ ...formValues, [name]: value });
  }

  // const handleChange = (name) => (e) => {
  //   setShop({...shop.shopImage, [name == "shopImage"]: e.target.files[0] });
  // };

  // const handleChange = (name) => (e) => {
  //   setShopName(e.target.value)
  // };

  const imageSelectorHandler = (name) => (e) => {
    setShopImage(e.target.files[0])
  }

  // const handleChecked = (name) => (e) => {
  //   setShop({...shop, [name === shopVerification]:e.target.checked})
  // }

  // console.log(shopverified);
  console.log(medicine);
  console.log(shopImage);


    return (
			<>
       <Header></Header>
      <MedicineStepperHeader step={step}></MedicineStepperHeader>
      {
            {
              1: <MedicineInformation
              medicine={medicine}
                    handleChange={handleChange} 
                    // imageSelectorHandler={imageSelectorHandler} 
                    // handleChecked={handleChecked}
                  />,
              2: <MedicinePriceInformation handleChange={handleChange} />,
              3: <ConfirmationMedicineInfo 
                     setMedicine={setMedicine}
                    medicine={medicine} 
                    // shopVerification = {shopVerification}
                    // setShopVerification ={setShopVerification}
                    shopImage={shopImage}
                    setShopImage={setShopImage}
                  />,
              4: <AddImage
              medicine={medicine}
              newmedicineid={newmedicineid}
              setNewmedicineid={setNewmedicineid}
                    // handleChange={handleChange} 
                    // imageSelectorHandler={imageSelectorHandler} 
                    // handleChecked={handleChecked}
                    
                  />

            }[step]
          }
          <div className="d-flex justify-content-around px-5 mt-5 addingShop-wrap">
            {step > 1 ? (
              // <button className="btn btn-warning" onClick={prevStep}>
              //   Back
              // </button>
              <Button variant="secondary" type="submit" className="back-btn" onClick={prevStep}>
                <BiLogOut className="pr-2 "></BiLogOut> Back
              </Button>
            ) : null}


            {step < 4 ? (
              <Button variant="primary" className="btn submitbtn" onClick={nextStep}>
              <BiLogIn className=""></BiLogIn>  {step === 3 ? " Confirm " : "Next"}
            </Button>
            ):null}
            
            
          </div>
				
			</>
    );
  }
  
  export default AddingMedicineList;