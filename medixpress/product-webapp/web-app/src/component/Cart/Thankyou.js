import React from "react";
import { Link } from "react-router-dom";
import '../../assets/stylesheets/outercart.scss'

function Thankyou(props) {
    const {goHome} = props
    return (
        <div className="thankyou-page-wrapper">
            <div className="content">
                <div className="wrapper-1">
                    <div className="wrapper-2">
                        <h1>Thank you !</h1>
                        <p>Your Payment is successfull and Thanks for shopping with us.  </p>
                        <p>You Will receive a confirmation email soon  </p>
                        <Link className="go-home mt-3" to="/">
                           Home
                        </Link>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Thankyou