<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common" %>

<spring:htmlEscape defaultHtmlEscape="true" />

<spring:url value="/my-account/order/${orderData.code}/returns" var="orderReturnUrl" htmlEscape="false"/>
<common:headline url="${orderReturnUrl}" labelKey="text.account.return.confirm.order.title" labelArguments="${orderData.code}" />
