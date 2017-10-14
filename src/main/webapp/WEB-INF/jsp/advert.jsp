<%--
  Created by IntelliJ IDEA.
  User: Abay
  Date: 8/9/2017
  Time: 2:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="links" uri="/WEB-INF/linktaglib.tld" %>

<fmt:bundle basename="locale">

    <!DOCTYPE html>
    <html class="mystyle-html">
    <head>
        <meta charset="UTF-8">
        <title><fmt:message key="site.logo"/></title>
        <!-- Favicon-->
        <link rel="icon" href="../../img/favicon.ico" type="image/x-icon">

        <links:commonLink/>

        <!-- Light Gallery Plugin Css -->
        <link href="../../css/lightgallery.css" rel="stylesheet">
    </head>
    <body class="mystyle-body" style="background: #d4d9da;">
    <div class="wraper">
        <jsp:include page="/WEB-INF/jsp/fragment/header.jsp"/>

        <div class="content container">

            <div class="row" style="margin:0 5% 0 5%">
                <h2>
                    <small><fmt:message key="advert"/> № ${advert.id}
                    </small>
                </h2>
                <hr>

                <!--content advert      -->
                <div class="col-lg-8 col-md-8 col-sm-8 col-xs-8"
                     style="background: floralwhite; padding: 10px; border-radius: 5px;">
                    <!--Gallery images  aniimated-thumbnials-->
                    <div id="aniimated-thumbnials">
                        <c:forEach begin="0" items="${advert_images}" var="advertImage" end="0">
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                <a href="data:image/jpeg;base64,${advertImage.image}" data-sub-html="">

                                    <img style="width:100%; max-height: 450px; padding: 4px;margin-bottom: 20px;background-color: #fff;border: 1px solid #ddd;border-radius: 4px;"
                                         src="data:image/jpeg;base64,${advertImage.image}">
                                </a>
                            </div>
                        </c:forEach>
                        <div class="col-lg-12 col-md-12 col-sm-12" style="word-wrap: break-word;">
                            <h3>${advert.title}</h3>
                            <h5><b>${region_advert.regionName} , ${region_advert.districts[0].districtName} &brvbar;
                                <fmt:message key="date.add"/> : ${advert.date}</b>
                                <p></h5>
                            <hr>
                            <h2>
                                <small>
                                        ${advert.description}
                                </small>
                            </h2>
                        </div>
                        <c:forEach begin="1" items="${advert_images}" var="advertImage">
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                <a href="data:image/jpeg;base64,${advertImage.image}" data-sub-html="">
                                    <img style="width:100%; max-height: 450px; padding: 4px;margin-bottom: 20px;background-color: #fff;border: 1px solid #ddd;border-radius: 4px;"
                                         src="data:image/jpeg;base64,${advertImage.image}">
                                </a>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4  text-center">
                    <!--cost-->
                    <div class="col-md-12" style="background: floralwhite; padding: 10px;  border-radius: 5px;">

                        <c:if test="${role=='admin'}">
                            <div class="col-md-12 col-sm-12 text-center">

                                <b><fmt:message key="admin"/></b><br><br>
                                <form form action="/controller" method=POST>
                                    <input type="hidden" name="command" value="handler_admin"/>
                                    <input type="hidden" name="action" value="remove_advert"/>
                                    <input type="hidden" name="admin_cabinet_action" value="adverts_is_unchecked"/>
                                    <input type="hidden" name="block" value="3"/>
                                    <input type="hidden" name="id_advert" value="${advert.id}">
                                    <button type="submit" style="width: 45%;" class="btn btn-danger">
                                        <i class="glyphicon glyphicon-trash"></i>
                                        <fmt:message key="remove"/></button>
                                </form>
                                <br>

                                <c:choose>
                                    <c:when test="${advert.checkAdmin==false}">
                                        <form form action="/controller" method=POST>
                                            <input type="hidden" name="command" value="handler_admin"/>
                                            <input type="hidden" name="action" value="checked_advert"/>
                                            <input type="hidden" name="admin_cabinet_action"
                                                   value="adverts_is_unchecked"/>
                                            <input type="hidden" name="block" value="3"/>
                                            <input type="hidden" name="id_advert" value="${advert.id}">
                                            <button type="submit" style="width: 45%;" class="btn btn-success">
                                                <i class="glyphicon glyphicon-ok"></i>
                                                <fmt:message key="checking"/></button>
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        <form form action="/controller" method=POST>
                                            <input type="hidden" name="command" value="handler_admin"/>
                                            <input type="hidden" name="action" value="unchecked_advert"/>
                                            <input type="hidden" name="admin_cabinet_action"
                                                   value="adverts_is_unchecked"/>
                                            <input type="hidden" name="block" value="3"/>
                                            <input type="hidden" name="id_advert" value="${advert.id}">
                                            <button type="submit" style="width: 45%;" class="btn btn-danger">
                                                <i class="glyphicon glyphicon-ban-circle"></i>
                                                <fmt:message key="blocking"/></button>
                                        </form>
                                    </c:otherwise>
                                </c:choose>
                                <hr>
                            </div>

                        </c:if>

                        <h2><span class="label label-default">${advert.priceMoney}<fmt:message key="tg"/></span></h2>
                        <c:if test="${swap!=null}"><h4><span class="label label-warning"><fmt:message
                                key="swap"/></span></h4></c:if>
                        <c:if test="${priceContract!=null}"><h4><span class="label label-warning"><fmt:message
                                key="price.contract"/></span></h4></c:if>

                        <hr>
                        <!-- phone -->
                        <div class="btn-group">
                            <button type="button" class="btn btn-info btn-md dropdown-toggle " data-toggle="dropdown"
                                    aria-haspopup="true" aria-expanded="false">
                                <fmt:message key="phone"/> <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">

                                <c:forEach items="${advertPhones}" var="phone">
                                    <li><a>${phone.phoneNumber}</a></li>
                                </c:forEach>

                            </ul>
                        </div>

                        <hr>
                        <!-- owner user-->
                        <div class="col-md-12 col-sm-12 text-center">
                            <fmt:message key="seller"/><br>
                            <img src="data:image/jpeg;base64,${userOwner.photoUser}"
                                 style="border-radius: 50%;" width="60" height="60" alt=""><br>
                            <h4><span class="label label-default">${userOwner.userName}</span></h4>

                        </div>

                    </div>
                </div>

            </div>
        </div>


        <jsp:include page="/WEB-INF/jsp/fragment/footer.jsp"/>
    </div>
    <!-- Light Gallery Plugin Js -->
    <script src="../../js/lightgallery-all.js"></script>

    <!-- Custom Js -->
    <script src="../../js/image-gallery.js"></script>
    </body>
    </html>
</fmt:bundle>