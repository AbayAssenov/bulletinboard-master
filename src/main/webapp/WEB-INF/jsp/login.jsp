<%--
  Created by IntelliJ IDEA.
  User: Abay
  Date: 6/17/2017
  Time: 1:29 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:bundle basename="locale">

    <!DOCTYPE html>
    <html class="mystyle-html">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title><fmt:message key="authorization.on.site"/></title>
        <link rel="icon" href="../../img/favicon.ico" type="image/x-icon">

        <!-- Bootstrap -->
        <link href="../../css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="../../css/stylelogin.css"/>
        <link href="../../css/main-form.css" rel="stylesheet">
        <link href="../../css/main.css" rel="stylesheet">

    </head>
    <body class="mystyle-body">
    <div class="wraper">
        <jsp:include page="/WEB-INF/jsp/fragment/header.jsp"/>

        <div class="content container">
            <div class="row">
                <div class="col-md-offset-3 col-md-6">


                    <form class="form-horizontal" name="loginForm" method="POST" action="/controller">
                        <span class="heading"><fmt:message key="authorization"/></span>

                        <input type="hidden" name="command" value="login"/>
                        <div class="form-group">
                            <input type="email" class="form-control" name="login" value="" id="inputEmail"
                                   placeholder="<fmt:message key="enter.email"/>">
                            <i class="glyphicon glyphicon-user"></i>
                        </div>
                        <div class="form-group help">

                            <input type="password" class="form-control" name="password" value="" id="inputPassword"
                                   placeholder="<fmt:message key="enter.password"/>">
                            <i class="glyphicon glyphicon-sunglasses"></i>

                            <br/>
                                ${errorLoginPassMessage}

                        </div>
                        <div class="form-group">


                            <a href="/controller?command=registration_form"><fmt:message key="sign.up"/></a>

                            <button type="submit" class="btn btn-default"><fmt:message key="enter"/></button>
                        </div>

                    </form>
                </div>
            </div>
        </div>

        <jsp:include page="/WEB-INF/jsp/fragment/footer.jsp"/>
    </div>
    </body>
    </html>
</fmt:bundle>