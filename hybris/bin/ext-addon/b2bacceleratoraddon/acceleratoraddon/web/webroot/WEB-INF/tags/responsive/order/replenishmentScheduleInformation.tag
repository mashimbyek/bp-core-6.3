<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="order" required="true" type="de.hybris.platform.commercefacades.order.data.OrderData" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<%--
	~ /*
	~  * [y] hybris Platform
	~  *
	~  * Copyright (c) 2000-2016 SAP SE or an SAP affiliate company.
	~  * All rights reserved.
	~  *
	~  * This software is the confidential and proprietary information of SAP
	~  * ("Confidential Information"). You shall not disclose such Confidential
	~  * Information and shall use it only in accordance with the terms of the
	~  * license agreement you entered into with SAP.
	~  *
	~  */
--%>

<div class="span-19 delivery_stages">
	<div class="headline">
		<spring:theme code="text.account.orderHistory.replenishment"/>
	</div>
	<div class="description">
		<spring:theme code="text.account.orderHistory.replenishment.startFrom" arguments="${order.triggerData.activationTime}"/>
	</div>
	<div class="description">
		<spring:theme code="text.account.orderHistory.replenishment.schedule" arguments="${order.triggerData.displayTimeTable}"/>
	</div>
	<div class="description">
		<c:url value="/my-account/my-replenishment/${order.jobCode}" var="scheduleUrl"/>
		<a href="${scheduleUrl}"><spring:theme code="text.account.orderHistory.replenishment.link"/></a>
	</div>
</div>
