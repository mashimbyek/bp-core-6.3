<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="b2b-order" tagdir="/WEB-INF/tags/addons/b2bacceleratoraddon/responsive/order"%>
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

<spring:url value="/my-account/order/approvalDecision" var="orderApprovalDecisionURL" htmlEscape="false" />

<b2b-order:orderApprovalDecisionPopup orderApprovalDecisionForm="${orderApprovalDecisionForm}" orderApprovalData="${orderApprovalData}"
    orderApprovalDecisionURL="${orderApprovalDecisionURL}" 
    decision="REJECT" 
    actionButtonLabel="text.account.orderApproval.rejectButton.displayName"
    actionButtonClass="approverDecisionRejectButton"
    modalPopupClass="orderApprovalRejectCommentModal"
    commentLabel="text.account.orderApproval.reject.reason" />
