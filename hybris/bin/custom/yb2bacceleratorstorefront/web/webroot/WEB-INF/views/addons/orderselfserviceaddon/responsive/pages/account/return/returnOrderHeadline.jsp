<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common" %>

<spring:htmlEscape defaultHtmlEscape="true" />

<spring:url value="/my-account/order/${orderData.code}" var="orderDetailsUrl" htmlEscape="false"/>
<common:headline url="${orderDetailsUrl}" labelKey="text.account.return.order.title" labelArguments="${orderData.code}" />
