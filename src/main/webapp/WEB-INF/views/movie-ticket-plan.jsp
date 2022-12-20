<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet" href="./resources/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="./resources/assets/css/all.min.css">
<link rel="stylesheet" href="./resources/assets/css/animate.css">
<link rel="stylesheet" href="./resources/assets/css/flaticon.css">
<link rel="stylesheet" href="./resources/assets/css/magnific-popup.css">
<link rel="stylesheet" href="./resources/assets/css/odometer.css">
<link rel="stylesheet" href="./resources/assets/css/owl.carousel.min.css">
<link rel="stylesheet" href="./resources/assets/css/owl.theme.default.min.css">
<link rel="stylesheet" href="./resources/assets/css/nice-select.css">
<link rel="stylesheet" href="./resources/assets/css/main.css">
<link rel="shortcut icon" href="./resources/assets/images/favicon.png"
	type="image/x-icon">
<title>Boleto - Online Ticket Booking Website HTML Template</title>
</head>
<body>
	<!-- ==========Preloader========== -->
	<div class="preloader">
		<div class="preloader-inner">
			<div class="preloader-icon">
				<span></span><span></span>
			</div>
		</div>
	</div>
	<!-- ==========Preloader========== -->
	<!-- ==========Overlay========== -->
	<div class="overlay"></div>
	<a href="#0" class="scrollToTop"><i class="fas fa-angle-up"></i></a>
	<!-- ==========Overlay========== -->
	<!-- ==========Header-Section========== -->
	<%@ include file = "header-section.jsp" %>
	<!-- ==========Header-Section========== -->
	<!-- ==========Window-Warning-Section========== -->
	<section class="window-warning inActive">
		<div class="lay"></div>
	</section>
	<!-- ==========Window-Warning-Section========== -->
	<!-- ==========Banner-Section========== -->
	<section class="details-banner hero-area bg_img"
		data-background="${timBackPic}">
		<div class="container">
			<div class="details-banner-wrapper">
				<div class="details-banner-content">
					<h3 class="title"> ${timMovName}</h3>
					<div class="tags">
						<a href="#0">English</a><a href="#0">Hindi</a><a href="#0">Telegu</a><a
							href="#0">Tamil</a>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- ==========Banner-Section========== -->
	<!-- ==========Book-Section========== -->
	<section class="book-section bg-one">
		<div class="container">
			<form class="ticket-search-form two">
				<div class="form-group">
					<div class="thumb">
						<img src="./resources/assets/images/ticket/city.png" alt="ticket">
					</div>
					<span class="type">city</span><select class="select-bar"><option
							value="london">London</option>
						<option value="dhaka">dhaka</option>
						<option value="rosario">rosario</option>
						<option value="madrid">madrid</option>
						<option value="koltaka">kolkata</option>
						<option value="rome">rome</option>
						<option value="khoksa">khoksa</option></select>
				</div>
				<div class="form-group">
					<div class="thumb">
						<img src="./resources/assets/images/ticket/date.png" alt="ticket">
					</div>
					<span class="type">date</span><select class="select-bar"><option
							value="26-12-19">23/10/2020</option>
						<option value="26-12-19">24/10/2020</option>
						<option value="26-12-19">25/10/2020</option>
						<option value="26-12-19">26/10/2020</option></select>
				</div>
				<div class="form-group">
					<div class="thumb">
						<img src="./resources/assets/images/ticket/cinema.png" alt="ticket">
					</div>
					<span class="type">cinema</span><select class="select-bar"><option
							value="Awaken">Awaken</option>
						<option value="Venus">Venus</option>
						<option value="wanted">wanted</option>
						<option value="joker">joker</option>
						<option value="fid">fid</option>
						<option value="kidio">kidio</option>
						<option value="mottus">mottus</option></select>
				</div>
			</form>
		</div>
	</section>
	<!-- ==========Book-Section========== -->
	<!-- ==========Movie-Section========== -->
	<div class="ticket-plan-section padding-bottom padding-top">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-lg-9 mb-5 mb-lg-0">
					<ul class="seat-plan-wrapper bg-five">
					
					
						<c:forEach var="tm" items="${tm}">
						<li><div class="movie-name">
								<div class="icons">
									<i class="far fa-heart"></i><i class="fas fa-heart"></i>
								</div>
								<a href="#0" class="name">${tm.timTheRoom}</a>
								<div class="location-icon">
									<i class="fas fa-map-marker-alt"></i>
								</div>
							</div>
							<div class="movie-schedule">
								<div class="item" 
								onclick="location.href = 'movie-seat-plan?timMovName=${tm.timMovName}&timTheName=${tm.timTheName}&timTheRoom=${tm.timTheRoom} & timStartDate=${tm.timStartDate} & timCurTime=${tm.timStartTime1}'">
								${tm.timStartTime1}</div>
								<div class="item" 
								onclick="location.href = 'movie-seat-plan?timMovName=${tm.timMovName}&timTheName=${tm.timTheName}&timTheRoom=${tm.timTheRoom} & timStartDate=${tm.timStartDate} & timCurTime=${tm.timStartTime2}'">${tm.timStartTime2}</div>
								<div class="item" 
								onclick="location.href = 'movie-seat-plan?timMovName=${tm.timMovName}&timTheName=${tm.timTheName}&timTheRoom=${tm.timTheRoom} & timStartDate=${tm.timStartDate} & timCurTime=${tm.timStartTime3}'">${tm.timStartTime3}</div>
								<div class="item" 
								onclick="location.href = 'movie-seat-plan?timMovName=${tm.timMovName}&timTheName=${tm.timTheName}&timTheRoom=${tm.timTheRoom} & timStartDate=${tm.timStartDate} & timCurTime=${tm.timStartTime4}'">${tm.timStartTime4}</div>
							</div></li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<!-- ==========Movie-Section========== -->
	<!-- ==========Newslater-Section========== -->
	<footer class="footer-section">

		<div class="container">
			<div class="footer-top">
				<div class="logo">
					<a href="index-1"><img
						src="./resources/assets/images/footer/footer-logo.png" alt="footer"></a>
				</div>
				<ul class="social-icons">
					<li><a href="#0"><i class="fab fa-facebook-f"></i></a></li>
					<li><a href="#0" class="active"><i class="fab fa-twitter"></i></a></li>
					<li><a href="#0"><i class="fab fa-pinterest-p"></i></a></li>
					<li><a href="#0"><i class="fab fa-google"></i></a></li>
					<li><a href="#0"><i class="fab fa-instagram"></i></a></li>
				</ul>
			</div>
			<div class="footer-bottom">
				<div class="footer-bottom-area">
					<div class="left">
						<p>
							Copyright Â© 2020.All Rights Reserved By <a href="#0">Boleto </a>
						</p>
					</div>
					<ul class="links">
						<li><a href="#0">About</a></li>
						<li><a href="#0">Terms Of Use</a></li>
						<li><a href="#0">Privacy Policy</a></li>
						<li><a href="#0">FAQ</a></li>
						<li><a href="#0">Feedback</a></li>
					</ul>
				</div>
			</div>
		</div>
	</footer>
	<!-- ==========Newslater-Section========== -->
	<script src="./resources/assets/js/jquery-3.3.1.min.js"></script>
	<script src="./resources/assets/js/modernizr-3.6.0.min.js"></script>
	<script src="./resources/assets/js/plugins.js"></script>
	<script src="./resources/assets/js/bootstrap.min.js"></script>
	<script src="./resources/assets/js/isotope.pkgd.min.js"></script>
	<script src="./resources/assets/js/magnific-popup.min.js"></script>
	<script src="./resources/assets/js/owl.carousel.min.js"></script>
	<script src="./resources/assets/js/wow.min.js"></script>
	<script src="./resources/assets/js/countdown.min.js"></script>
	<script src="./resources/assets/js/odometer.min.js"></script>
	<script src="./resources/assets/js/viewport.jquery.js"></script>
	<script src="./resources/assets/js/nice-select.js"></script>
	<script src="./resources/assets/js/main.js"></script>
</body>
<script>
	function movOne(){
		
	}
</script>
</html>