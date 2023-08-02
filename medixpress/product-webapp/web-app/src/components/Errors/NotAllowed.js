import React from "react";
import { useNavigate } from "react-router-dom";
import './style.css';
function NotAllowed(props){
    let history = useNavigate();
    return(
        <section class="page_404">
	<div class="container">
		<div class="row">	
		<div class="col-sm-12 ">
		<div class="col-sm-10 col-sm-offset-1  text-center">
		<div class="four_zero_four_bg">
			<h1 class="text-center ">{props.error && props.error || "403"}</h1>
		</div>
		
		<div class="contant_box_404">
		<h3 class="h2">
		{props.description && props.description || "Look like you're lost"}
		</h3>
		
		<p>You are not allowed to access this page!</p>
		
        <button className="link_404" onClick={() => history(-2)}>Go Back</button>	</div>
		</div>
		</div>
		</div>
	</div>
</section>
    )
}
export default NotAllowed;