<%--
  Created by IntelliJ IDEA.
  User: Abay
  Date: 6/17/2017
  Time: 2:30 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="links" uri="/WEB-INF/linktaglib.tld" %>

<fmt:bundle basename="locale">

    <!DOCTYPE html>
    <html class="mystyle-html">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title><fmt:message key="registration.on.site"/></title>
        <link rel="icon" href="../../img/favicon.ico" type="image/x-icon">

        <links:commonLink/>

        <script src="../../js/validator.min.js"></script>

    </head>

    <body class="mystyle-body">
    <div class="wraper">
        <jsp:include page="/WEB-INF/jsp/fragment/header.jsp"/>

        <div class="content container">

            <div class="col-md-8 col-sm-10 col-md-push-2 col-sm-push-1">

                <div class="row">
                    <div class="col-lg-8 col-md-8 col-lg-push-2">
                        <form data-toggle="validator" role="form" method="POST" action="/controller">
                            <legend><h1><fmt:message key="registration"/></h1></legend>
                            <input type="hidden" name="command" value="registration"/>
                            <p class="gray">
                                <span> <fmt:message key="it.take.few.time"/> </span>
                            </p>

                            <div class="form-group">
                                <label for="inputName" class="control-label"><fmt:message key="your.name"/> </label>
                                <input type="text" name="name" pattern="^[_A-z0-9А-я ]{1,}$" maxlength="15"
                                       class="form-control" id="inputName" placeholder="<fmt:message key="enter.name"/>"
                                       data-error="<fmt:message key="only.letters.numbers"/>" required>
                                <div class="help-block with-errors"></div>
                            </div>

                            <div class="form-group">
                                <label for="inputEmail" class="control-label"><fmt:message key="your.email"/> </label>
                                <input type="email" name="email" class="form-control" id="inputEmail"
                                       placeholder="<fmt:message key="enter.email"/>"
                                       data-error="<fmt:message key="error.enter.email"/>" required>
                                    ${errorEmail}
                                <div class="help-block with-errors"></div>
                            </div>
                            <div class="form-group">
                                <label for="inputPassword" class="control-label"><fmt:message
                                        key="enter.password"/></label>
                                <div class="form-inline row">
                                    <div class="form-group col-sm-6">
                                        <input type="password" name="password" data-toggle="validator"
                                               data-minlength="6" class="form-control" id="inputPassword"
                                               placeholder="123456" required>
                                        <span class="help-block"><fmt:message key="min.enter.values"/></span>
                                    </div>
                                    <div class="form-group col-sm-6">
                                        <input type="password" class="form-control" id="inputPasswordConfirm"
                                               data-match="#inputPassword"
                                               data-match-error="<fmt:message key="error.password.match"/>"
                                               placeholder="<fmt:message key="confirm.password"/>" required>
                                        <div class="help-block with-errors"></div>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" id="terms" required>
                                        <label><a href="#"><fmt:message key="site.agree"/></a></label>
                                    </label>

                                </div>
                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-primary"><fmt:message key="send"/></button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="/WEB-INF/jsp/fragment/footer.jsp"/>
    </div>
    </body>
    </html>
</fmt:bundle>