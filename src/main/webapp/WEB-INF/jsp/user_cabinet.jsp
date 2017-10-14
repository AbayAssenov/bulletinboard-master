 <%--
  Created by IntelliJ IDEA.
  User: Abay
  Date: 8/3/2017
  Time: 2:21 AM
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

        <div id="content-profile" class="content container" style="padding-top: 5%">
            <div class="row">
                <div class="col-md-2">
                    <!--Sidebar content-->

                    <div class="panel panel-default">
                        <!-- Main panel content -->

                        <div class="panel-body" style="background: darkgray">

                            <span class="name" style="color: white"><fmt:message key="seller"/></span>
                        </div>
                        <!-- Group list -->
                        <div class="list-group">
                            <a href="/controller?command=user_cabinet&block=1&user_cabinet_action=user_adverts"
                               class="list-group-item" onclick="showHide(1);"><fmt:message
                                    key="my.adverts"/> </a>
                            <a href="/controller?command=user_cabinet&block=2&user_cabinet_action=profile"
                               class="list-group-item" onclick="showHide(2);"><fmt:message
                                    key="my.profile"/> </a>
                            <a href="#" class="list-group-item" onclick="showHide(3);"><fmt:message
                                    key="settings"/> </a>
                            <a href="/controller?command=logout" class="list-group-item"><span
                                    class="text-muted"><fmt:message key="log.out"/></span></a>
                        </div>
                    </div>
                </div>


                <!-- this block for advert -->
                <div id="block1" class="
              <c:choose>
                    <c:when test="${block==1}">
                   show
                    </c:when>
                    <c:otherwise>
                   hide
                    </c:otherwise>
                    </c:choose>">

                    <div class="col-md-10  my-adverts" style=" margin-bottom: 3%;  border-left: 1px solid #eee;">
                        <!--Body content-->

                        <div class="row">
                            <div class="col-md-12">
                                <h3><fmt:message key="my.adverts"/></h3>
                                <hr>
                            </div>
                        </div>
                        <!-- 1 -->
                        <div class="row ">
                            <c:forEach items="${listAdverts}" var="advert" varStatus="loop">
                                <div class="col-md-12" style="margin: 3px;  border: 2px solid #eee;">
                                    <!-- Картинка -->
                                    <div class="col-md-2">
                                        <a class="b-title" href="/controller?command=advert&id_advert=${advert.id}"
                                           title="${advert.title}">
                                            <img class="img-responsive" onerror="this.src = '../img/no_img.jpeg'"
                                                 src="data:image/jpeg;base64,${first_advert_images[advert.id].image}"
                                                 alt="${advert.title}"
                                                 style="margin: 3px; border: 2px solid #eee;max-width: 150px; max-height: 115px;">
                                        </a>
                                    </div>
                                    <div class="col-md-10" style="word-wrap: break-word;">
                                        <!-- Заголовок -->
                                        <a class="b-title" href="/controller?command=advert&id_advert=${advert.id}"
                                           title="${advert.title}"><span
                                                class="label label-default">ID: ${advert.id}</span>
                                                ${advert.title}
                                        </a>
                                        <br>

                                        <span class="text-muted">Date add: ${advert.date}</span>
                                    </div>
                                    <div class="col-md-3 pull-right " style="margin:3px;">
                                        <c:choose>
                                            <c:when test="${advert.archive==true}">
                                                <%--restore from archive--%>
                                                <form form action="/controller" method=POST>
                                                    <input type="hidden" name="command" value="handler_user"/>
                                                    <input type="hidden" name="action" value="set_advert_is_archive"/>
                                                    <input type="hidden" name="user_cabinet_action"
                                                           value="user_adverts"/>
                                                    <input type="hidden" name="block" value="1"/>
                                                    <input type="hidden" name="id_advert" value="${advert.id}">
                                                    <button type="submit" class="btn btn-info btn-md btn-block ">
                                                        <i class="glyphicon glyphicon-chevron-up"></i> &nbsp
                                                        <fmt:message key="from.archive"/>
                                                    </button>
                                                </form>

                                            </c:when>
                                            <c:otherwise>
                                                <%--set to archie--%>
                                                <form form action="/controller" method=POST>
                                                    <input type="hidden" name="command" value="handler_user"/>
                                                    <input type="hidden" name="action" value="set_advert_is_archive"/>
                                                    <input type="hidden" name="is_archive" value="true"/>
                                                    <input type="hidden" name="user_cabinet_action"
                                                           value="user_adverts"/>
                                                    <input type="hidden" name="block" value="1"/>
                                                    <input type="hidden" name="id_advert" value="${advert.id}">
                                                    <button type="submit" class="btn btn-warning btn-md btn-block ">
                                                        <i class="glyphicon glyphicon-chevron-down"></i> &nbsp
                                                        <fmt:message key="archive"/>
                                                    </button>
                                                </form>

                                            </c:otherwise>
                                        </c:choose>
                                            <%--remove advert--%>
                                        <br>
                                        <form form action="/controller" method=POST>
                                            <input type="hidden" name="command" value="handler_user"/>
                                            <input type="hidden" name="action" value="remove_advert"/>
                                            <input type="hidden" name="user_cabinet_action" value="user_adverts"/>
                                            <input type="hidden" name="block" value="1"/>
                                            <input type="hidden" name="id_advert" value="${advert.id}">
                                            <button type="submit" class="btn btn-danger btn-md btn-block ">
                                                <i class="glyphicon glyphicon-trash"></i> &nbsp <fmt:message
                                                    key="delete"/>
                                            </button>
                                        </form>
                                    </div>

                                </div>

                            </c:forEach>


                            <hr>
                            <!-- end body content -->
                            <div class="col-md-12">

                                <div class="col-sm-10 text-center">
                                    <nav aria-label="Page navigation">
                                        <ul class="pagination" id="pagination" name="selectPage">
                                            </li>
                                            <c:forEach begin="1" end="${countPage}" varStatus="loop">
                                                <li>
                                                    <a href="/controller?command=user_cabinet&selectedPage=${loop.index}&block=1&user_cabinet_action=user_adverts">${loop.index}</a>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </nav>
                                </div>
                            </div>
                            <!-- row -->
                        </div>
                        <!-- end row main -->
                    </div>
                </div>
                <!-- block1 -->


                <script type="text/javascript">

                    (function ($) {
                        $(function () {
                            $('.btn-file').each(function () {
                                var self = this;
                                $('input[type=file]', this).change(function () {
                                    // remove existing file info
                                    $(self).next().remove();
                                    // get value
                                    var value = $(this).val();
                                    // get file name
                                    var fileName = value.substring(value.lastIndexOf('/') + 1);
                                    // get file extension
                                    var fileExt = fileName.split('.').pop().toLowerCase();
                                    // append file info
                                    $('<span><i class="glyphicon glyphicon-file"></i>' + fileExt + fileName +
                                        '</span>').insertAfter(self);
                                });
                            });
                        });
                    })(jQuery);

                </script>


                <!-- this block for profile -->
                <div id="block2" class="
              <c:choose>
                    <c:when test="${block==2}">
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
                                <h3><fmt:message key="my.profile"/></h3>
                                <hr>
                            </div>


                            <div class="col-md-12 ">

                                <div class="col-md-2">
                                    <img src="data:image/jpeg;base64,${user_photo}"
                                         style="border-radius: 25%" width="120" height="120"
                                         alt="${user_name}">
                                </div>

                                <div class="col-md-10">

                                    <h3><span class="label label-default"><fmt:message
                                            key="seller"/>&nbsp&nbspID:${userId}</span></h3>
                                    <br>
                                    <h4><b><fmt:message key="your.name"/>:</b> ${user_name} </h4>
                                    <h4><b><fmt:message key="your.email"/>:</b> ${user_email} </h4>
                                </div>

                                <div class="col-md-12">


                                    <div class="col-md-4">
                                        <strong><fmt:message key="choose.user.photo"/></strong>
                                        <br>
                                        <form method="POST" action="/controller" enctype="multipart/form-data">
                                            <input type="hidden" name="command" value="handler_user"/>
                                            <input type="hidden" name="action" value="set_photo_user"/>
                                            <input type="hidden" name="user_cabinet_action" value="profile"/>
                                            <input type="hidden" name="block" value="2"/>
                                            <span class="btn btn-warning btn-sm btn-file">
                                                    <i class="glyphicon glyphicon-plus-sign"></i>
                                                    <input type="file" name="user_photo" id="image" required/>
                                                </span>
                                            <br><br>
                                            <button type="submit" style="width: 30%;"
                                                    class="btn btn-success btn-sm btn-block">
                                                <fmt:message key="safe"/>
                                            </button>

                                        </form>
                                    </div>
                                </div>
                            </div>

                            <!-- row -->
                        </div>
                        <!--end Body content-->
                    </div>
                    <!-- block2 -->
                </div>

                <!-- this block for settings -->
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
                            <div class="col-md-12">
                                <h3><fmt:message key="settings"/></h3>
                                <hr>
                            </div>

                            <div class="col-md-12">
                                <h4><fmt:message key="change.pass"/></h4>
                                <br>
                            </div>

                            <p><strong> &nbsp&nbsp <fmt:message key="your.email"/>: ${user_email} </strong></p>


                            <form action="/controller" method="POST" data-toggle="validator" role="form">
                                <input type="hidden" name="command" value="handler_user"/>
                                <input type="hidden" name="action" value="change_password"/>
                                <input type="hidden" name="block" value="3"/>

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
                <!-- block3 -->
            </div>

            <!-- end first row of main-form  -->
        </div>
        <!-- container main-form -->

        <jsp:include page="/WEB-INF/jsp/fragment/footer.jsp"/>
    </div>
    </body>
    </html>
</fmt:bundle>