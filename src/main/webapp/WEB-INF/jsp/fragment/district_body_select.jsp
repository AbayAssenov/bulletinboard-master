<%--
  Created by IntelliJ IDEA.
  User: Abay
  Date: 8/22/2017
  Time: 11:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${district==null}">
    <option value="" selected="selected"></option>
</c:if>

<c:forEach items="${district}" var="district" varStatus="loop">
    <option value="${district.districtId}">${district.districtName}</option>
</c:forEach>
