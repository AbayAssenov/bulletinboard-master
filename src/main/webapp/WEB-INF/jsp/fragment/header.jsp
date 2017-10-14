<%--
  Created by IntelliJ IDEA.
  User: Abay
  Date: 7/9/2017
  Time: 11:39 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:bundle basename="locale">

    <header id="header-container">

        <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container">
                <div class="navbar-header">

                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>

                    <a class="navbar-brand" href="/controller">
                        <div style="margin-top: -13px; display: inline-block;"><img class="logo-main"
                                                                                    src="../../../img/brand1.png"
                                                                                    width="150"
                                                                                    height="110" alt="Логотип"
                                                                                    alt="Brand"></div>
                    </a>

                </div>
                <div class="collapse navbar-collapse">


                    <ul class="nav navbar-nav navbar-right">
                        <!-- class="active" -->


                        <c:choose>
                            <c:when test="${role=='user'}">
                                <li><a class="btn  add-new" height=50%; href="/controller?command=create_advert">
                                    <i class="glyphicon glyphicon-plus"></i> <fmt:message key="add.advert"/></a></li>

                                <li>
                                <li>
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                                       aria-expanded="false"> <i class="glyphicon glyphicon-user"></i>
                                            ${user_name} <span class="caret"></span></a>
                                    <ul class="dropdown-menu" role="menu">
                                        <li class="dropdown-header"><fmt:message key="my.cabinet"/></li>
                                        <li>
                                            <a href="/controller?command=user_cabinet&block=2&user_cabinet_action=profile"><fmt:message
                                                    key="profile"/></a>
                                        </li>
                                        <li>
                                            <a href="/controller?command=user_cabinet&block=1&user_cabinet_action=user_adverts"><fmt:message
                                                    key="adverts"/></a></li>
                                        <li><a href="/controller?command=user_cabinet&block=3"><fmt:message
                                                key="settings"/></a></li>
                                        <li class="divider"></li>
                                        <li><a href="/controller?command=logout"><fmt:message key="log.out"/></a></li>
                                    </ul>
                                </li>
                                </li>
                            </c:when>
                            <c:when test="${role=='admin'}">
                                <li>
                                <li>
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                                       aria-expanded="false"> <i class="glyphicon glyphicon-fire"></i>
                                            ${user_name} <span class="caret"></span></a>
                                    <ul class="dropdown-menu" role="menu">
                                        <li class="dropdown-header"><fmt:message key="my.cabinet"/></li>
                                        <li>
                                            <a href="/controller?command=admin_cabinet&block=1&admin_cabinet_action=profile">
                                                <fmt:message key="profile"/></a></li>
                                        <li>
                                            <a href="/controller?command=admin_cabinet&block=2&admin_cabinet_action=users_on_one_page">
                                                <fmt:message key="users"/></a></li>
                                        <li>
                                            <a href="/controller?command=admin_cabinet&block=3&admin_cabinet_action=adverts_is_unchecked">
                                                <fmt:message key="moderation"/></a></li>
                                        <li><a href="/controller?command=admin_cabinet&block=4"><fmt:message
                                                key="settings"/></a></li>
                                        <li class="divider"></li>
                                            <%--<li class="dropdown-header">Витрина</li>--%>
                                        <li><a href="/controller?command=logout"><fmt:message key="log.out"/></a></li>
                                    </ul>
                                </li>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li><a class="btn  add-new" height=50%; href="/controller?command=create_advert">
                                    <i class="glyphicon glyphicon-plus"></i><fmt:message key="add.advert"/></a></li>

                                <li><a href="/controller?command=registration_form"><fmt:message key="sign.up"/></a>
                                </li>
                                <li><a href="/controller?command=login_form"><fmt:message key="sign.in"/></a></li>
                            </c:otherwise>
                        </c:choose>

                        <li>
                            <a href="/controller?command=locale&locale=en_US&redirect=true"
                               style="display: inline-block; padding-right: 2px;">
                                <img src="../../../img/eng.png" alt="English"/></a>

                            <a href="/controller?command=locale&locale=ru_RU&redirect=true"
                               style="display: inline-block; padding-left: 2px;">
                                <img src="../../../img/rus.png" alt="Russian"/></a>
                        </li>

                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </div>

    </header>
</fmt:bundle>