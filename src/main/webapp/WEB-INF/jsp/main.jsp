<%--
  Created by IntelliJ IDEA.
  User: Abay
  Date: 6/17/2017
  Time: 1:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="links" uri="/WEB-INF/linktaglib.tld" %>

<fmt:bundle basename="locale">

    <!DOCTYPE html>
    <html class="mystyle-html">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title><fmt:message key="site.logo"/></title>
        <link rel="icon" href="../../img/favicon.ico" type="image/x-icon">

        <links:commonLink/>

    </head>

    <body class="mystyle-body">
    <div class="wraper">
        <jsp:include page="/WEB-INF/jsp/fragment/header.jsp"/>

        <div class="content container">
            <div class="row">
                <c:forEach items="${headings}" var="heading">
                    <div class="col-xs-12 col-sm-6 col-md-4">
                        <div class="panel-heading">
                            <div class="media">
                                <div class="media-left">
                                    <div class="b-category-img">
                                        <img onerror="this.src = '../img/heading.png'"
                                             src="data:image/jpeg;base64,${heading.headingImage}"
                                             height="85" width="85" alt="Heading"/>
                                    </div>
                                </div>
                                <div class="media-body">
                                    <div class="h4 title">
                                        <a href="/controller?command=search_advert&id_heading=${heading.headingId}">
                                                ${heading.headingName}
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <jsp:include page="/WEB-INF/jsp/fragment/footer.jsp"/>
    </div>
    </body>
    </html>
</fmt:bundle>