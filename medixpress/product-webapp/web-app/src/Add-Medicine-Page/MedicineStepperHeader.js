import React, { useEffect, useState } from 'react';
import {Container, Row, Col, Form, InputGroup, Button, NavItem} from 'react-bootstrap';


function MedicineStepperHeader({step}) {

  return (
        <>
          <Container>
            <div className='StepperHead-wrap'>
              <ul className='d-flex list-unstyled justify-content-between'>
                <li className={step === 1 && 'active'}>
                  <div className='d-flex StepperHead-content align-items-center'>
                    <div>
                      <h6>01</h6>
                    </div>
                    <div>
                      <h5>Medicine Info</h5>
                    </div>
                  </div>
                </li>
                <li className={step === 2 && 'active'}>
                  <div className='d-flex StepperHead-content align-items-center'>
                    <div>
                      <h6>02</h6>
                    </div>
                    <div>
                      <h5>Medicine Price Info </h5>
                    </div>
                  </div>
                </li>
                <li className={step === 3 && 'active'}>
                  <div className='d-flex StepperHead-content align-items-center'>
                    <div>
                      <h6>03</h6>
                    </div>
                    <div>
                      <h5>Confirm your Details</h5>
                    </div>
                  </div>
                </li>
                <li className={step === 4 && 'active'}>
                  <div className='d-flex StepperHead-content align-items-center'>
                    <div>
                      <h6>04</h6>
                    </div>
                    <div>
                      <h5>Image Upload</h5>
                    </div>
                  </div>
                </li>
              </ul>
              <div className='horizonalLine'>
                <hr></hr>
              </div>
            </div>
          </Container>
        </>
      );
  }
    
export default MedicineStepperHeader;