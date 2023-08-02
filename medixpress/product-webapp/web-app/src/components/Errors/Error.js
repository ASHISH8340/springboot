import React from "react";
import './style.css';
function Error(props){
    return(
        <section class="page_404">
	<div class="container">
		<div class="row">	
		<div class="col-sm-12 ">
		<div class="col-sm-10 col-sm-offset-1  text-center">
		<div class="four_zero_four_bg">
			<h1 class="text-center ">{props.error && props.error || "404"}</h1>
		</div>
		
		<div class="contant_box_404">
		<h3 class="h2">
		{props.description && props.description || "Look like you're lost"}
		</h3>
		
		<p>the page you are looking for, not avaible!</p>
		
		{
			localStorage.getItem('role') == 'BUYER' &&
			<a href="/" class="link_404">Go to Home</a> || <a href="/profile" class="link_404">Go to profile</a>
		}
	</div>
		</div>
		</div>
		</div>
	</div>
</section>
    )
}
export default Error;