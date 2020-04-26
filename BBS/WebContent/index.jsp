<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>MyShoppingMall</title>
<link href="css/bootstrap.css" rel="stylesheet">
<script src="https://kit.fontawesome.com/79413e27e1.js"></script>
</head>
<body>
<header class="header">
	<div class="navbar navbar-dark bg-dark">
		<div class="container-fluid">
			<div class="navbar-header">
				<a href="#" class="navbar-brand navbar-center">The Mall<i class="far fa-heart"></i></a>
				
			</div>
			<div>
				<form class="form-inline my-2 my-lg-0">
      			<input class="form-control mr-sm-2" type="search" placeholder="검색어를 입력하세요" aria-label="Search" style="width:300px;">
      			<button class="btn btn-outline-light my-2 my-sm-0" type="submit">Search</button>
    			</form>
			</div>
			<div class="navbar-text navbar-right">
					<button type="button" class="btn btn-outline-light">로그인</button>
					<button type="button" class="btn btn-outline-light">회원가입</button>				
					<button type="button" class="btn btn-outline-light"><i class="fas fa-shopping-cart"></i></button>
				
			</div>
		</div>
	</div>
</header>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<a href="#" class="navbar-brand">MyShoppingMall</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" 
		aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	 <span class="navbar-toggler-icon"></span>
	 </button>
	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active">
				<a href="#" class="nav-link">HOME <span class="sr-only">(current)</span></a>
			</li>
			<li class="nav-item dropdown">
				<a href="#" class="nav-link dropdown-toggle" id="navbarDropdown" role="button"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				카테고리	
				</a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown">
					<a href="#" class="dropdown-item">기초케어</a>
					<a href="#" class="dropdown-item">메이크업</a>
					<a href="#" class="dropdown-item">화장소품</a>
					<a href="#" class="dropdown-item">남성케어</a>
				</div>
			</li>
			<li class="nav-item">
				<a href="#" class="nav-link">신상</a>
			</li>
			<li class="nav-item">
				<a href="#" class="nav-link">베스트</a>
			</li>
			<li class="nav-item">
				<a href="#" class="nav-link">기획전</a>
			</li>
			<li class="nav-item">
				<a href="#" class="nav-link">이벤트</a>
			</li>
		</ul>
	</div>
</nav>
<div>
<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
  <ol class="carousel-indicators">
    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
  </ol>
  <div class="carousel-inner" style="height:400px;">
    <div class="carousel-item active">
      <img src="image/main_image_1.jpg" class="d-block w-100" alt="Respoinsive image">
    </div>
    <div class="carousel-item">
      <img src="image/main_image_2.jpg" class="d-block w-100" alt="Respoinsive image">
    </div>
    <div class="carousel-item">
      <img src="image/main_image_3.jpg" class="d-block w-100" alt="Respoinsive image">
    </div>
  </div>
  <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>
</div>
<div class="container border" style="margin-top:2rem;">
	<div class="row">
	
		<h3><a href="#" class="nav-link">신상</a></h3>
		<div class="col-xs"></div>
	
	
	</div>
</div>
	

<footer class="footer">
</footer>





<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
<script src="js/bootstrap.js"></script>
</body>
</html>