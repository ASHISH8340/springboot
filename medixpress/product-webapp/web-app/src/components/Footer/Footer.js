import React from "react";
import './style.css'
function Footer(){
    return(
<footer class="page-footer font-small footer-section-lower pt-4">

  <div class="container-fluid text-center text-md-left">

    <div class="row">

      <div class="col-md-6 mt-md-0 mt-3">


        <h5 class="text-uppercase">MediXpress</h5>
        <p>We are here to deliver your medicine at your doorstep. <strong>Shop Now !</strong></p>

      </div>

      <hr class="clearfix w-100 d-md-none pb-3"/>


      <div class="col-md-3 mb-md-0 mb-3">

        <h5 class="text-uppercase">Categories</h5>

        <ul class="list-unstyled">
          <li>
            <a href="#!">Categories</a>
          </li>
          <li>
            <a href="#!">Best Selling</a>
          </li>
          <li>
            <a href="#!">Diabetese</a>
          </li>
        </ul>

      </div>

      <div class="col-md-3 mb-md-0 mb-3">

        <h5 class="text-uppercase">Help</h5>

        <ul class="list-unstyled">
          <li>
            <p>Call Us at: 9876543478</p>
          </li>
          <li>
            <p>Mail Us at: help@MediXpress.com</p>
          </li>
          <li>
            <p>Find Us at: The middle of earth</p>
          </li>
        </ul>

      </div>

    </div>

  </div>

  <div class="footer-copyright text-center py-3">© 2022 Copyright:
    <a href="/"> MediXpress.com</a>
  </div>

</footer>
    )
}
export default Footer;