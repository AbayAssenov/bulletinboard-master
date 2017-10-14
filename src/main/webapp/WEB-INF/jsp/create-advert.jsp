<%--
  Created by IntelliJ IDEA.
  User: Abay
  Date: 7/17/2017
  Time: 2:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="links" uri="/WEB-INF/linktaglib.tld" %>

<fmt:bundle basename="locale">

    <html class="mystyle-html">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title><fmt:message key="site.logo"/></title>
        <link rel="icon" href="../../img/favicon.ico" type="image/x-icon">

        <links:commonLink/>

        <script src="../../js/image-script.js"></script>

    </head>
    <body class="mystyle-body">
    <div class="wraper">
        <jsp:include page="/WEB-INF/jsp/fragment/header.jsp"/>

        <div class="content container">
            <label style="font-size: 16px; font-weight: 700"><fmt:message key="give.advert.free"/></label>
            <hr>
            <form data-toggle="validator" role="form" method="POST" action="/controller" enctype="multipart/form-data">
                <input type="hidden" name="command" value="add_advert"/>
                    <%--Caption heading --%>
                <div class="row">
                    <div class="col-lg-8 col-lg-offset-2">
                        <div class="form-group">
                            <label><fmt:message key="caption"/><span class="text-danger">*</span></label>
                            <input type="text" name="caption" pattern="^[_A-z0-9А-я ]{1,}$" style="width:80%"
                                   maxlength="70" minlength="6"
                                   class="form-control" id="inputName" placeholder="<fmt:message key="product.name"/>"
                                   data-error="<fmt:message key="only.letters.numbers"/>" required>
                            <div class="help-block with-errors"></div>
                        </div>
                        <div class="form-group">
                            <label><fmt:message key="heading"/><span class="text-danger">*</span></label>
                        </div>

                        <select name="heading" class="form-control" data-type="section" data-next=""
                                style="width: 35%" required>

                            <c:forEach items="${headings}" var="heading">
                                <jsp:useBean id="heading" type="kz.epam.training.bulletin_board.model.heading.Heading"/>
                                <option value="${heading.headingId}">${heading.headingName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <hr>
                    <%--Discription --%>
                <div class="row">
                    <div class="col-lg-6 col-lg-offset-2">
                        <div class="form-group">
                            <label><fmt:message key="description"/><span class="text-danger">*</span></label>
                            <textarea id="text" name="description" class="form-control parameter-text" data-info=" "
                                      data-loaded="" data-length-max="3000" size="3000" maxlength="3000"
                                      style="height: 140px ;"></textarea>
                        </div>
                    </div>
                </div>
                <hr>
                    <%--Region district address --%>
                <div class="row">
                    <div class="col-lg-6 col-lg-offset-2">
                        <div class="form-group">
                            <label><fmt:message key="product.location"/><span class="text-danger">*</span></label>
                            <div class="container">

                                <script type="text/javascript">

                                    function selectDistricts() {
                                        var id_region = $('select[name="region"]').val();
                                        $.ajax({
                                            type: "GET",
                                            url: "/controller",
                                            data: {command: 'district', id_region: id_region},
                                            success: function (responce) {
                                                $('select[name="district"]').html(responce);
                                            }
                                        });
                                    }
                                </script>

                                <select name="region" class="form-control" onchange="selectDistricts('get_ditricts', 2)"
                                        style="width: 30%; display: inline-block;">
                                    <option value="-1" selected="selected"><fmt:message key="region"/></option>

                                    <c:forEach items="${location}" var="region" varStatus="loop">
                                        <jsp:useBean id="region"
                                                     type="kz.epam.training.bulletin_board.model.location.Region"/>
                                        <option value=${loop.index}>${region.regionName}</option>
                                    </c:forEach>
                                </select>

                                <select name="district" id="district" class="form-control"
                                        style="width: 30%; display: inline-block;" required>
                                </select>

                            </div>
                        </div>

                        <div class="form-group">
                            <label><fmt:message key="address"/></label>
                            <input type="text" name="address" pattern="^[_A-z0-9А-я ]{1,}$" style="width:80%"
                                   maxlength="70" minlength="0"
                                   class="form-control" placeholder="<fmt:message key="enter.exact.address"/>"
                                   data-error="<fmt:message key="only.letters.numbers"/>" required>
                            <div class="help-block with-errors"></div>
                        </div>
                    </div>
                </div>
                    <%--Price swap contract --%>
                <hr>
                <div class="row">
                    <div class="col-lg-6 col-lg-offset-2">
                        <div class="form-group">
                            <label><fmt:message key="price"/></label>

                            <div class="container" style="width:30%; display: inline-block;">
                                <input type="text" name="priceMoney" pattern="^[0-9]{1,}$" maxlength="20" minlength="0"
                                       class="form-control"
                                       id="inputCost" data-error="<fmt:message key="only.integer.numbers"/>" required>
                                <div class="help-block with-errors"></div>
                            </div>

                            <div class="checkbox checkbox-info"
                                 style="width:30%; padding-left: 30px; display: inline-block;">
                                <input id="check1" name="swap" class="styled" value="on" type="checkbox">
                                <label for="check1"><fmt:message key="swap"/></label>
                            </div>

                            <div class="checkbox checkbox-info" style="width:30%; display: inline-block;">
                                <input id="check2" name="priceContract" class="styled" value="on" type="checkbox">
                                <label for="check2"><fmt:message key="price.contract"/></label>
                            </div>

                        </div>
                    </div>
                </div>
                    <%--Photos--%>

                <div class="row">
                    <div class="col-lg-6 col-lg-offset-2">
                        <div class="form-group">
                            <label><fmt:message key="photos"/></label>
                            <div class="container" style="width: 100%; border: 1px solid #e8e8e8; padding: 20px;">

                                <c:forEach var="index" begin="1" end="6">
                                    <div class="row" style="margin: 4px; display: inline-block;">
                                        <div class="col-sm-2">
                                            <div class="file-form-wrap">
                                                <div class="file-upload">
                                                    <div id="preview${index}"></div>
                                                    <label><br>
                                                        <input id="uploaded-file${index}" class="uf" type="file"
                                                               name="photo${index}"
                                                               onchange="getFileParam('uploaded-file${index}','preview${index}');"
                                                               accept="image/*">
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>

                            </div>
                        </div>
                    </div>
                </div>
                    <%--Phones--%>
                <label style="font-size: 16px; font-weight: 700"><fmt:message key="contacts"/></label>
                <hr>
                <div class="row">
                    <div class="col-lg-6 col-lg-offset-2">
                        <div class="form-group">
                            <div class="col-md-9">
                                <label><fmt:message key="phones"/><span class="text-danger">*</span></label>
                                <div class="row">
                                    <div class="col-sm-4">
                                        <input type="text" name="phone1" value=""
                                               class="form-control j-phoneAdvert-number fv-required" required>
                                    </div>
                                    <div class="col-sm-4">
                                        <input type="text" name="phone2" value=""
                                               class="form-control j-phoneAdvert-number">
                                    </div>
                                    <div class="col-sm-4">
                                        <input type="text" name="phone3" value=""
                                               class="form-control j-phoneAdvert-number">
                                    </div>
                                </div>
                                <div class="help-block"><fmt:message key="set.minimum.one.phone"/></div>
                            </div>
                        </div>
                    </div>
                </div>
                <hr>

                <div class="row">
                    <div class="col-sm-4 col-lg-offset-4">
                        <button type="submit" class="btn btn-success btn-lg btn-block" name="save_bid"
                                id="j-edit-item-submit">
                            <fmt:message key="safe"/>
                        </button>
                    </div>
                    <div class="col-sm-8">
                        <div id="j-edit-item-alert"></div>
                    </div>
                </div>
            </form>
        </div>

        <jsp:include page="/WEB-INF/jsp/fragment/footer.jsp"/>
    </div>
    </body>
    </html>
</fmt:bundle>