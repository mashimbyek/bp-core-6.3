<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="customer" value="${fragmentData}" scope="request"/>
<div role="tabpanel" class="tab-pane active" id="customerDetailsSection">
    <div class="row asm-customer360-tab">
        <div class="col-sm-6">
            <h3><spring:message code="text.customer360.profile" text="Profile"/></h3>
            <div class="row">
                <div class="col-sm-5">
                    <c:choose>
                        <c:when test="${not empty customer.profilePicture}">
                            <img src="${customer.profilePicture.url}" class="img-responsive img-circle profile-pic" title="<spring:theme code="text.asm.customerList.picture.alt" />"/>
                        </c:when>
                        <c:otherwise>
                            <span class="responsive-table-link default-pic"></span>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="col-sm-7">
                    <div class="form-group">
                        <label class="control-label"><spring:message code="text.customer360.profile.information" text="Information"/></label>
                        <p class="form-readonly-text form-readonly-text-lg"><c:out value="${customer.fullName}" /></p>
                        <a> <c:out value="${customer.email}" /> </a>
                    </div>
                </div>
            </div>
        </div>

        <c:set var="address" value="${customer.address}" scope="page"/>
        <div class="col-sm-6 customer360-contact-sub-tab">
            <h3><spring:message code="text.customer360.profile.contact" text="Contact"/></h3>
            <div class="row">
                <div class="col-sm-6">
                    <div class="form-group">
                        <label class="control-label"><spring:message code="text.customer360.profile.deliveryAddress" text="Delivery Address"/></label>
                        <c:if test="${not empty address}">
                            <p class="form-readonly-text form-readonly-text-lg"><c:out value="${address.line1 }"/></p>
                            <p class="form-readonly-text form-readonly-text-lg"><c:out value="${address.line2 }"/></p>
                            <p class="form-readonly-text form-readonly-text-lg"><c:out value="${address.town }"/></p>
                            <p class="form-readonly-text form-readonly-text-lg"><c:if test="${not empty address.region }"> <c:out value="${address.region.name}, "/> </c:if> <c:out value="${address.country.name}"/></p>
                        </c:if>
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="form-group">
                        <label class="control-label"><spring:message code="text.customer360.profile.phoneNumber" text="Phone Number"/></label>
                        <c:if test="${not empty address}">
                            <p class="form-readonly-text form-readonly-text-lg"><c:out value="${address.phone}" /></p>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
