<%--
  Created by IntelliJ IDEA.
  User: Abay
  Date: 7/25/2017
  Time: 5:44 PM
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

        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="Cache-Control" content="no-cache,no-store,must-revalidate">
        <title><fmt:message key="site.logo"/></title>
        <link rel="icon" href="../../img/favicon.ico" type="image/x-icon">

        <links:commonLink/>

        <script src="../../js/image-script.js"></script>

        <script type="text/javascript">



            /** Takes districts for current region*/
            function selectDistricts() {
                var id_region = $('select[name="region"]').val();

                $.ajax({

                    type: "POST",
                    url: "/controller",
                    data: {command: 'district', id_region: id_region},
                    success: function (responce) {
                        $('select[name="district"]').html(responce);
                    }
                });
            }

            /** Get result by parametrs */
            function searchByParametrs(selected_page) {

// Take current values from page
                var id_heading = $('select#heading').val();
                var id_district = $('select#id_district').val();
                var search_word = $('input#search_word').val();
                var price_from = $('input#price_from').val();
                var price_to = $('input#price_to').val();
                var in_description = $('input#in_description').prop('checked');
                var only_with_photo = $('input#only_with_photo').prop('checked');

                var order_by_new = $('input#order_by_new').prop('checked');
                var order_by_cheap = $('input#order_by_cheap').prop('checked');
                var order_by_expensive = $('input#order_by_expensive').prop('checked');

// Create array
                var data = {};

// Set only chosen parameters
                data['command'] = "search"; //set command name

                if (order_by_new) data['order_by_new'] = order_by_new;
                if (order_by_cheap) data['order_by_cheap'] = order_by_cheap;
                if (order_by_expensive) data['order_by_expensive'] = order_by_expensive;

                if (id_heading !== '') data['id_heading'] = id_heading;
                if (id_district !== '') data['id_district'] = id_district;
                if (search_word !== '') {
                    data['search_word'] = search_word;
                    if (in_description) data['in_description'] = in_description;
                }
                if (price_from !== '') data['price_from'] = price_from;
                if (price_to !== '') data['price_to'] = price_to;
                if (only_with_photo) data['only_with_photo'] = only_with_photo;
                if (null !== selected_page) data['selectedPage'] = selected_page;
// Send request
                $.ajax({
                    type: "GET",
                    url: "/controller",
                    data: data,
                    cache: false,
                    complete: function () {
                        window.history.replaceState("Details", "Title", this.url.replace("command=search", "command=search_advert"));
                    },
                    success: function (responce) {
                        $('div[name="search-body-result"]').html(responce);
                    }
                });
            }
            function removeSearchRow() {
                document.getElementById("search_word").value = "";//Clear search row
            }

        </script>
    </head>

    <body class="mystyle-body">

    <div class="wraper">
        <jsp:include page="/WEB-INF/jsp/fragment/header.jsp"/>

        <iframe id="history_frame" name="history_frame" src="about: blank;" style="display: none;"></iframe>


        <div class="content container main-form">
            <!-- Settings search and sort -->
            <div class="row" style="background: #f7f7f7; border-radius: 5px;">
                <h3><fmt:message key="search.caption"/></h3>

                <!-- Search row -->
                <div class="col-md-4">
                    <div id="custom-search-input">
                        <div class="input-group col-md-12">
                    <span class="input-group-addon">
                     <i class="glyphicon glyphicon-search"></i>
                      </span>
                            <input type="text" id="search_word" value="" class="form-control input-md"
                                   placeholder="<fmt:message key="search.caption"/>">
                            <span class="input-group-btn">
                            <button class="btn btn-info btn-md" onclick="removeSearchRow()" style="height: 34px;"
                                    type="button">
                                <i class="glyphicon glyphicon-remove"></i>
                            </button>
                        </span>
                        </div>
                    </div>
                </div>

                <!-- Select region and district -->
                <div class="col-md-4">
                    <select name="region" class="form-control" onchange="selectDistricts()"
                            style="width: 49%; display: inline-block;">
                        <option value="-1" selected="selected"><fmt:message key="region"/></option>
                        <c:forEach items="${location}" var="region" varStatus="loop">
                            <option value=${loop.index}>${region.regionName}</option>
                        </c:forEach>
                    </select>
                    <select name="district" id="id_district" class="form-control"
                            style="width: 49%; display: inline-block;">
                        <option value="" selected="selected"></option>
                    </select>
                </div>


                <!-- Select heading -->
                <div class="col-md-4">
                    <select name="heading" id="heading" class="form-control" data-type="section" data-next=""
                            style="width: 90%;display:inline-block;outline:orange solid 4px;"
                            onchange="searchByParametrs()">
                        <option value="0"><fmt:message key="heading"/></option>

                        <c:forEach items="${headings}" var="heading">
                            <option
                                    <c:if test="${id_heading == heading.headingId}">selected=selected</c:if>
                                    value="${heading.headingId}">${heading.headingName}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <!-- Select search parameters -->
                <div class="col-md-12">
                    <div class="col-md-6">
                        <div class="checkbox checkbox-info" style="width:49%;  display: inline-block;">
                            <input class="styled" id="in_description" value="" type="checkbox">
                            <label for="in_description">
                                <fmt:message key="search.title.description"/>
                            </label>
                        </div>
                        <div class="checkbox checkbox-info" style="width:49%; display: inline-block;">
                            <input class="styled" id="only_with_photo" value="" type="checkbox">
                            <label for="only_with_photo">
                                <fmt:message key="only.photo"/>
                            </label>
                        </div>
                    </div>
                </div>

                <!-- Settings range of price -->
                <div class="col-md-12">
                    <div class="col-md-3">
                        <input type="text" name="price" pattern="^[0-9]{1,}$" style="width:80%" maxlength="20" minlength="0"
                               class="form-control input-sm" id="price_from"
                               placeholder="<fmt:message key="price.from"/>"
                               data-error="<fmt:message key="only.numbers"/>">
                        <br>
                        <input type="text" name="price" pattern="^[0-9]{1,}$" style="width:80%" maxlength="20" minlength="0"
                               class="form-control input-sm" id="price_to"
                               placeholder="<fmt:message key="price.to"/>"
                               data-error="<fmt:message key="only.numbers"/>">
                    </div>
                </div>

<script>

    /**Allow only numbers for price input*/
    $(document.getElementsByName("price")).bind("change keyup input click", function() {
        if (this.value.match(/[^0-9]/g)) {
            this.value = this.value.replace(/[^0-9]/g, '');
        }
    });
    
</script>

                <!-- Search button -->
                <div class="col-md-12">
                    <div class="col-md-2 pull-right">
                        <button type="button" class="btn btn-info btn-md btn-block" name="search"
                                onclick="searchByParametrs(1)">
                            <i class="glyphicon glyphicon-search"></i> &nbsp <b><fmt:message key="search.big"/></b>
                        </button>
                    </div>
                </div>

                <!-- Sorting ,buttoms -->
                <div class="col-md-12" style="padding: 20px 0px; ">
                    <div class="col-md-5 pull-right">

                        <div class="btn-group" data-toggle="buttons">
                            <label class="btn btn-primary btn-sm">
                                <input name="radiosort" id="order_by_new" type="radio" onchange="searchByParametrs()">
                                <fmt:message key="newest"/>
                            </label>
                            <label class="btn btn-primary btn-sm">
                                <input name="radiosort" id="order_by_cheap" type="radio" onchange="searchByParametrs()">
                                <fmt:message key="cheapest"/>
                            </label>
                            <label class="btn btn-primary btn-sm">
                                <input name="radiosort" id="order_by_expensive" type="radio"
                                       onchange="searchByParametrs()">
                                <fmt:message key="most.expensive"/>
                            </label>
                        </div>
                    </div>
                </div>
            </div>
            <hr>

            <!-- Blocks of adverts -->
            <div class="row " name="search-body-result">
                <jsp:include page="/WEB-INF/jsp/fragment/search_body_result.jsp"/>
            </div>
        </div>
        <jsp:include page="/WEB-INF/jsp/fragment/footer.jsp"/>
    </div>
    </body>
    </html>

</fmt:bundle>