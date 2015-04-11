<%--
  Created by IntelliJ IDEA.
  User: andrewgyq
  Date: 2015/4/8
  Time: 11:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Rating</title>
  <link rel="stylesheet" media="screen" href="./css/main.css">
  <link rel="stylesheet" media="screen" href="./css/bootstrap.min.css">
  <link rel="stylesheet" media="screen" href="./css/bootstrap-theme.min.css">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="shortcut icon" type="image/png" href="./img/favicon.png">
  <script src="./js/jquery-1.9.0.min.js" type="text/javascript"></script>
  <script src="./js/bootstrap.min.js" type="text/javascript"></script>
</head>
<body data-spy="scroll" data-twttr-rendered="true">
<!-- Fixed navbar -->
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
  <div class="container">
    <div class="navbar-header">
      <a class="navbar-brand" href="/foodrecommendation">Food recommender</a>
    </div>
    <div class="navbar-collapse collapse">
      <ul class="nav navbar-nav">
        <li><a href="/foodrecommendation/rate">Ratings</a></li>
        <li><a href="/foodrecommendation/recommendation">Recommendations</a></li>
      </ul>
    </div> <!--/.nav-collapse -->
  </div>
</div> <!-- /container -->
<div class="jumbotron">
  <h2 class="text-center">
    <span>Your rating:</span>
    <button type="button" class="btn btn-lg btn-danger" onclick="location.href='/foodrecommendation/rate?item=${itemId}&rating=1'">Very Bad</button>
    <button type="button" class="btn btn-lg btn-warning" onclick="location.href='/foodrecommendation/rate?item=${itemId}&rating=2'">Bad</button>
    <button type="button" class="btn btn-lg btn-info" onclick="location.href='/foodrecommendation/rate?item=${itemId}&rating=3'">Average</button>
    <button type="button" class="btn btn-lg btn-primary" onclick="location.href='/foodrecommendation/rate?item=${itemId}&rating=4'">Good</button>
    <button type="button" class="btn btn-lg btn-success" onclick="location.href='/foodrecommendation/rate?item=${itemId}&rating=5'">Excellent</button>
    or <a href="/foodrecommendation/rate">Skip</a>
  </h2>
</div>
<h3>${title}</h3>
<div class="row">
  <div class="col-md-4">
    <img src='${img}' width="300px" height="300px"/>
    <br/>
    <a href='${url}' target="_blank">See item on Amazon</a>
  </div>
  <div class="col-md-6">
    ${desc}
  </div>
</div>
</body>
</html>
