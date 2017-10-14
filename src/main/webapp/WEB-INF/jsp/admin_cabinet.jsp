<%--
  Created by IntelliJ IDEA.
  User: Abay
  Date: 8/4/2017
  Time: 10:25 PM
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

        <script src="js/validator.min.js"></script>

            <%--select by block--%>
        <script type="text/javascript">
            var selected_item = ${block};
            function showHide(select_item) {
                if (selected_item != select_item) {
                    document.getElementById("block" + selected_item).className = "hide";
                    document.getElementById("block" + select_item).className = "show";

                    selected_item = select_item;
                }
            }
            //--></script>

        <style type="text/css">

            #content-profile div.hide {
                display: none;
            }

            #content-profile div.show {
                display: block;
            }
        </style>


    </head>

    <body class="mystyle-body">
    <div class="wraper">

        <jsp:include page="/WEB-INF/jsp/fragment/header.jsp"/>


        <div id="content-profile" class="content container" style="padding-top: 5%; padding-bottom: 120px;">
            <div class="row">
                <div class="col-md-2">
                    <!--Sidebar content-->

                    <div class="panel panel-default">
                        <!-- Main panel content -->

                        <div class="panel-body" style="background: darkgray">

                            <span class="name" style="color: white"><fmt:message key="admin"/></span>
                        </div>

                        <!-- Group list -->
                        <div class="list-group">

                            <a href="/controller?command=admin_cabinet&block=1&admin_cabinet_action=profile"
                               class="list-group-item" onclick="showHide(1);"> <fmt:message key="my.profile"/></a>
                            <a href="/controller?command=admin_cabinet&block=2&admin_cabinet_action=users_on_one_page"
                               class="list-group-item" onclick="showHide(2);"> <fmt:message key="users"/></a>
                            <a href="/controller?command=admin_cabinet&block=3&admin_cabinet_action=adverts_is_unchecked"
                               class="list-group-item" onclick="showHide(3);"> <fmt:message key="moderation"/> </a>
                            <a href="#" class="list-group-item" onclick="showHide(4);"> <fmt:message
                                    key="settings"/> </a>
                            <a href="/controller?command=logout" class="list-group-item"><span
                                    class="text-muted"><fmt:message key="log.out"/></span></a>

                        </div>
                    </div>
                </div>


                <!-- this block for profile -->
                <!-- block1 -->
                <div id="block1" class="
              <c:choose>
                    <c:when test="${block==1}">
                   show
                    </c:when>
                    <c:otherwise>
                   hide
                    </c:otherwise>
                    </c:choose>">
                    <div class="col-md-10 " style=" margin-bottom: 3%;  border-left: 1px solid #eee;">
                        <!--Body content-->

                        <div class="row">
                            <div class="col-md-12">
                                <h4><fmt:message key="my.profile"/></h4>
                                <hr>
                            </div>


                            <div class="col-md-12 ">

                                <div class="col-md-2">
                                    <img
                                            src="data:image/jpeg;base64,${user_photo}"
                                            style="border-radius: 25%" width="120" height="120"
                                            alt="${user_name}">
                                </div>

                                <div class="col-md-10">

                                    <h3><span class="label label-default"><fmt:message
                                            key="admin"/>&nbsp&nbspID:${userId}</span></h3>
                                    <br>
                                    <h4><b><fmt:message key="your.name"/>:</b> ${user_name} </h4>
                                    <h4><b><fmt:message key="your.email"/>:</b> ${user_email} </h4>
                                </div>
                            </div>
                            <!-- row -->
                        </div>
                        <!--end Body content-->
                    </div>
                    <!-- block1 -->
                </div>


                <!-- this block for existing users -->
                    <%--block2--%>
                <div id="block2" class="
              <c:choose>
                    <c:when test="${block==2}">
                   show
                    </c:when>
                    <c:otherwise>
                   hide
                    </c:otherwise>
                    </c:choose>">

                    <div class="col-md-10">

                        <div class="row">
                            <div class="col-md-12">
                                <h4><fmt:message key="users"/></h4>
                                <hr>
                            </div>
                        </div>
                        <!--Body content-->
                        <div class="row">


                            <c:forEach items="${listUsers}" var="user" varStatus="loop">

                                <div class="col-md-12
                    <c:choose>
                    <c:when test="${loop.index%2==0}">
                    bg-danger
                    </c:when>
                    <c:otherwise>
                    bg-info
                    </c:otherwise>
                    </c:choose>" style="border-radius: 10px;">

                                    <!-- photo user -->
                                    <div class="col-md-1">
                                        <img src="data:image/jpeg;base64,${user.photoUser}"
                                             style=" margin-bottom: 3%;  border: 3px solid #eee; border-radius: 30%;"
                                             width="40" height="40"
                                             alt="photo user">
                                    </div>
                                    <!-- short info about user -->
                                    <div class="col-md-8">
                                        <small class="text-muted">id :</small>
                                        <strong>${user.userId} &nbsp&nbsp</strong>
                                        <small class="text-muted">user name :</small>
                                        <strong>${user.userName} &nbsp&nbsp</strong>
                                        <small class="text-muted">email :</small>
                                        <strong>${user.userEmail} &nbsp&nbsp</strong>
                                        <small class="text-muted">count adverts :</small>
                                        <span class="badge">${countAdvert[loop.index]}</span>
                                    </div>
                                </div>

                            </c:forEach>

                            <div class="col-md-12">

                                <div class="col-sm-10 text-center">
                                    <nav aria-label="Page navigation">
                                        <ul class="pagination" id="pagination" name="selectPage">
                                            </li>
                                            <c:forEach begin="1" end="${countPageUsers}" varStatus="loop">
                                                <li>
                                                    <a href="/controller?command=admin_cabinet&selectedPageUsers=${loop.index}&block=2&admin_cabinet_action=users_on_one_page">
                                                            ${loop.index}</a>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </nav>
                                </div>

                            </div>
                        </div>
                        <!-- block2 -->
                    </div>
                </div>


                <!-- this block for moderation -->
                <!-- block3 -->
                <div id="block3" class="
              <c:choose>
                    <c:when test="${block==3}">
                   show
                    </c:when>
                    <c:otherwise>
                   hide
                    </c:otherwise>
                    </c:choose>">
                    <div class="col-md-10 " style=" margin-bottom: 3%;  border-left: 1px solid #eee;">
                        <!--Body content-->

                        <div class="row">
                            <div class="col-md-12 ">
                                <h4><fmt:message key="moderation"/></h4>
                                <hr>
                            </div>
                            <c:forEach items="${advertsIsUnchecked}" var="advertIsUnchecked" varStatus="loop">

                                <div class="col-md-12
                    <c:choose>
                    <c:when test="${loop.index%2==0}">
                    bg-warning
                    </c:when>
                    <c:otherwise>
                    bg-success
                    </c:otherwise>
                    </c:choose>" style="border-radius: 10px; margin: 1px; border: 1px solid #eee;">
                                    <a href="/controller?command=advert&id_advert=${advertIsUnchecked.id}">
                                        <small class="text-muted">title :</small>
                                        <strong>${advertIsUnchecked.title} </strong>
                                        <small class="text-muted">userID:</small>
                                        <strong>${advertIsUnchecked.userId} &nbsp&nbsp</strong>
                                        <small class="text-muted">data add :</small>
                                        <strong>${advertIsUnchecked.date} &nbsp&nbsp</strong>
                                    </a>
                                </div>
                            </c:forEach>

                            <div class="col-md-12">

                                <div class="col-sm-10 text-center">
                                    <nav aria-label="Page navigation">
                                        <ul class="pagination" id="" name="selectPage">
                                            </li>
                                            <c:forEach begin="1" end="${countPageAdUnchecked}" varStatus="loop">
                                                <li>
                                                    <a href="/controller?command=admin_cabinet&selectedPageUncheckedAd=${loop.index}&block=3&admin_cabinet_action=adverts_is_unchecked">
                                                            ${loop.index}</a>
                                                        <%--<a href="javascript: selectPage(${loop.index})">${loop.index}</a>--%>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </nav>
                                </div>
                            </div>
                            <!-- row -->
                        </div>
                        <!--end Body content-->
                    </div>
                    <!-- block3 -->
                </div>

                <!-- this block for settings -->
                <!-- block4 -->
                <div id="block4" class="
              <c:choose>
                    <c:when test="${block==4}">
                   show
                    </c:when>
                    <c:otherwise>
                   hide
                    </c:otherwise>
                    </c:choose>">
                    <div class="col-md-10 " style=" margin-bottom: 3%;  border-left: 1px solid #eee;">
                        <!--Body content-->

                        <div class="row">
                            <div class="col-md-12">
                                <h3><fmt:message key="settings"/></h3>
                                <hr>
                            </div>
                            <p><strong> &nbsp&nbsp <fmt:message key="your.email"/>: ${user_email} </strong></p>

                            <form action="/controller" method="POST" data-toggle="validator" role="form">
                                <input type="hidden" name="command" value="handler_admin"/>
                                <input type="hidden" name="action" value="change_password"/>
                                <input type="hidden" name="block" value="4"/>
                                <div class="form-group">
                                    <div class=" row">
                                        <!-- old password -->
                                        <div class="form-group col-md-12">
                                            <div class="form-group col-md-2">
 <span>
   <fmt:message key="current.pass.cabinet"/>
 </span></div>
                                            <div class="form-group col-md-5">
                                                <input type="password" name="current_password" data-minlength="6"
                                                       class="form-control"
                                                       placeholder="123456" required></div>
                                            <b> ${changePassword}</b>
                                        </div>
                                        <!-- new password -->
                                        <div class="form-group col-md-12">
                                            <div class="form-group col-md-2">
 <span>
     <fmt:message key="new.pass"/>
 </span></div>
                                            <div class="form-group col-md-5">
                                                <input type="password" name="new_password" data-toggle="validator"
                                                       data-minlength="6" class="form-control" id="inputPassword"
                                                       placeholder="123456" required>
                                                <span class="help-block"> <fmt:message key="min.enter.values"/></span>
                                            </div>
                                        </div>
                                        <!-- repeat new password -->
                                        <div class="form-group col-md-12">
                                            <div class="form-group col-md-2">
 <span>
   <fmt:message key="confirm.password"/>
 </span></div>
                                            <div class="form-group col-md-5">
                                                <input type="password" class="form-control" id="inputPasswordConfirm"
                                                       data-match="#inputPassword"
                                                       data-match-error="<fmt:message key="error.password.match"/>"
                                                       placeholder="<fmt:message key="confirm.password"/>" required>
                                                <div class="help-block with-errors"></div>
                                            </div>
                                        </div>
                                        <!-- for validator js -->


                                        <div class="col-sm-12 text-center">
                                            <button type="submit" class="btn btn-info"><fmt:message
                                                    key="safe"/></button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>


                        <!-- row -->
                    </div>
                    <!--end Body content-->
                </div>
                <!-- block4 -->
            </div>

            <!-- end first row of main-form  -->
        </div>
        <!-- container main-form -->

        <jsp:include page="/WEB-INF/jsp/fragment/footer.jsp"/>

    </div>
        <%--end wraper--%>
    </body>
    </html>
</fmt:bundle>