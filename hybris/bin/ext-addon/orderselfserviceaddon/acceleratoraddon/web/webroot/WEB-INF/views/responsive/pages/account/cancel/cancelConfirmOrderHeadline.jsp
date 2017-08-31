<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common" %>

<spring:htmlEscape defaultHtmlEscape="true" />

<spring:url value="/my-account/order/${orderData.code}/cancel" var="orderCancelUrl" htmlEscape="false"/>
<common:headline url="${orderCancelUrl}" labelKey="text.account.cancel.confirm.order.title" labelArguments="${orderData.code}" />
