import React, { useEffect, useState , useContext } from 'react';
import {Container, Row, Col, Form, InputGroup, Button} from 'react-bootstrap';
import './OrderList.css';
import axios, * as others from 'axios'; 
import Header from '../components/Header/Header';
import { ConfigContext } from "../Context/ConfigContext";

function OrderList() {

  const [orderList, setOrderList] = useState([]);
  const { url,endPoint, apiRoutes} = useContext(ConfigContext);
  const absoluteShopUrl=url+apiRoutes.orderList.port+endPoint+apiRoutes.orderList.controller+apiRoutes.orderList.method;

  const loadData = async () => {
		try{
      const resposnse = await axios.get(`${absoluteShopUrl}`);
      // const resposnse = await axios.get('http://localhost:8087/v1/api/order');
			// http://localhost:3001/medicalShopList
			// http://localhost:8084/api/v1/getallshop'
			setOrderList(resposnse.data)
		} catch(err){
			console.log(err);
		}
	}

  console.log(orderList)


  useEffect(() => {
		loadData();
	},[]);

    return (
      <>
        <Header></Header>
        <div className='orderList-section'>
          <Container>
            <div className='header'>
              <h3>Order List</h3>
            </div>
           {

             orderList.length>0 ? orderList.map((list) => {

              return(
                <>
                  <div className='orderList-wrap'>
                    <Row>
                      <Col>
                        <div className='orderList-img'>
                          <h5>Address</h5>
                          <p className="mb-0">{list.address[0].doorNumber}</p>
                          <p className="mb-0">{list.address[0].streetName}</p>
                          <p className="mb-0">{list.address[0].landmark}</p>
                          <p className="mb-0">{list.address[0].city}</p>
                          <p className="mb-0">{list.address[0].state}</p>
                          <p className="mb-0">{list.address[0].pinCode}</p>
                        </div>
                      </Col>
                      <Col>
                        <div className='orderList-header'>
                          <h5>medicines ({list.cart[0].cartQuantity})</h5>
                        </div>
                      </Col>
                      <Col>
                        <div className='orderList-price'>
                          <h5>&#x20B9; {list.price} </h5>
                        </div>
                      </Col>
                      <Col>
                        <div className='orderList-delivery'>
                          <h5>Delivered on : {new Date(list.deliveryDate).toDateString()} </h5>
                          
                        </div>
                      </Col>
                    </Row>
                  </div>
                </>
              );

             })
             :
             
              <div className="order-list-section">
                <div className='order-list-section-message'>
                  <p>No order is placed yet</p>
                </div>
              </div>
             
           }
            
          </Container>
        </div>
      </>
  
      
    );
  }
  
  export default OrderList;