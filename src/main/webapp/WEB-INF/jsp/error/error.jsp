<%--
  Created by IntelliJ IDEA.
  User: Abay
  Date: 9/5/2017
  Time: 8:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:bundle basename="locale">
    <html>
    <head>
        <title><fmt:message key="site.logo"/></title>
    </head>
    <body>

    <br>
    Request from ${pageContext.errorData.requestURI} is failed
    <br/>
    Servlet name or type: ${pageContext.errorData.servletName}
    <br/>
    Status code: ${pageContext.errorData.statusCode}
    <br/>
    Exception: ${pageContext.errorData.throwable}
    </body>
    </html>
</fmt:bundle>