<%@ attribute name="returnRequest" required="true"
              type="de.hybris.platform.ordermanagementfacade.returns.data.ReturnRequestData" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>

<div class="returnActionsSection" id="returnActions" data-theme="b" data-role="content">

    <!--Button begins-->
    <div class="row">
        <div class="return-details-actions">
            <div class="col-sm-6 col-md-5 col-lg-4 pull-right">
                <a href="${request.contextPath}/my-account/returns" data-role="button">
                    <button type="submit" class="btn btn-primary btn-block" id="backtoreturnsbutton">
                        <spring:theme code="text.account.return.details.back.to.returns"/>
                    </button>
                </a>
            </div>
        </div>

        <div class="col-sm-4 col-md-3" style="${style}">
            <div class="item-action">
                <c:set var="returnCode" value="${returnRequest.code}" scope="request"/>
                <action:actions element="div" parentComponent="${component}"/>
            </div>
        </div>
    </div>

</div>
