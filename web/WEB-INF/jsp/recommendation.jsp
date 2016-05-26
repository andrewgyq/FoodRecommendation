<%--
  Created by IntelliJ IDEA.
  User: andrewgyq
  Date: 2015/4/8
  Time: 12:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Recommendations</title>
    <link rel="stylesheet" media="screen" href="./css/main.css">
    <link rel="stylesheet" media="screen" href="./css/bootstrap.min.css">
    <link rel="stylesheet" media="screen" href="./css/bootstrap-theme.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" type="image/png" href="./img/favicon.png">
    <script src="./js/jquery-1.9.0.min.js" type="text/javascript"></script>
    <script src="./js/bootstrap.min.js" type="text/javascript"></script>
    <script type="text/javascript">
    </script>
</head>
<body>
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
      </div>
    </div>
  </div>
  <div class="jumbotron">
      <h2 class="text-center">
          <span>Your recommendations</span>
      </h2>
  </div>
  <c:forEach var="product" items="${list}">
      <div class="jumbotron">
          <h4 class="text-center">
              <span>${product.title}</span>
          </h4>
          <div class="row">
              <div class="col-md-4">
                  <img src="${product.img}"/>
                  <br/><br>
                  <a href="${product.url}">View on Amazon</a>
              </div>
              <div class="col-md-6">
                      ${product.desc}
              </div>
          </div>
      </div>
      <li></li>
  </c:forEach>



</body>
</html>
