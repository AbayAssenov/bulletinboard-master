<%--
  Created by IntelliJ IDEA.
  User: Abay
  Date: 8/6/2017
  Time: 6:53 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<fmt:bundle basename="locale">


    <c:forEach items="${listAdverts}" var="advert" varStatus="loop">

        <div class="col-sm-12 " style="margin: 3px; border: 2px solid #eee;">
            <div class="col-sm-2 ">
                    <%--picture cover advert--%>
                <a href="/controller?command=advert&id_advert=${advert.id}" title="${advert.title}">
                    <img onerror="this.src = '../../img/no_img.jpeg'"
                         src="data:image/jpeg;base64,${advert_images[advert.id].image}"
                         alt="Image of advert" style="max-width: 170px; max-height: 130px;"/>
                </a>
            </div>

            <div class="col-sm-10">
                <div class="col-sm-10">
                    <div class="col-sm-12">
                        <a href="/controller?command=advert&id_advert=${advert.id}" title="${advert.title}">
                            <strong style="color: #0098d0;font-size: 16px;">${advert.title}</strong>
                        </a>
                    </div>
                    <div class="col-sm-12">
                        <p class="color-9 lheight16 marginbott5">
                            <small>
                                <span>${advert.address}</span>
                            </small>
                        </p>
                        <p>${advert.date}</p>
                    </div>
                </div>

                <div class="col-sm-2">
                    <p class="price">
                        <strong style="font-size: 22px;">${advert.priceMoney}&nbsp <fmt:message key="tg"/></strong>
                    </p>
                </div>
            </div>
        </div>

    </c:forEach>
    <hr>
    <div class="col-sm-12 text-center">
        <nav aria-label="Page navigation">
            <ul class="pagination" id="pagination" name="selectPage">
                <c:forEach begin="1" end="${countPage}" varStatus="loop">
                    <li>
                        <a href="javascript: searchByParametrs(${loop.index})">${loop.index}</a>
                    </li>
                </c:forEach>
            </ul>
        </nav>
    </div>
</fmt:bundle>