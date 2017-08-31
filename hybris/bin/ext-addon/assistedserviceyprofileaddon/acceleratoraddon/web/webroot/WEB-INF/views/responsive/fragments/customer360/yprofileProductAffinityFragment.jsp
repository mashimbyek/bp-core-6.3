<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="assistedserviceutils" uri="http://hybris.com/tld/assistedserviceutils" %>

<div class="asm__customer360-overview-saved-section">
    <div class="row">
        <c:choose>
            <c:when test="${empty fragmentData}"> 
                <div class="col-xs-12">
                    <div class="asm__customer360-overview-divider">
                        <spring:theme
                            code="text.customer360.yprofile.product.recent.no.results"
                            text="There are currently no Recent Product items" />
                    </div>
                </div>
            </c:when>
            <c:otherwise>
            	<c:forEach items="${fragmentData}" var="productAffinityData">
                    <c:set var="viewedSince" value="${assistedserviceutils:timeSince(productAffinityData.updated)}" scope="page"/>
                    <c:url value="${productAffinityData.productData.url}" var="productUrl" />
                    <div class="col-sm-6 col-md-4">
                        <div class="asm__customer360-overview-product-view">
                            <div class="asm__customer360-overview-product-img">
                                <a href="${productUrl}" class="responsive-table-link text-nowrap">
                                    <product:productPrimaryImage product="${productAffinityData.productData}" format="thumbnail" />
                                </a>
                            </div>
                            <div class="asm__customer360-overview-product-name">
                                <a href="${productUrl}" class="responsive-table-link text-nowrap" title="${productAffinityData.productData.name}">
                                    <div class="hide_overflow">${productAffinityData.productData.name}</div>
                                </a>
                            </div>
                            <div class="asm__customer360-overview-product-sku">${productAffinityData.productData.code}</div>
                            <div class="asm__customer360-overview-product-price">${productAffinityData.productData.price.formattedValue}</div>
                            <div class="asm__customer360-overview-product-views">${productAffinityData.viewCount}&nbsp;
                                <c:choose>
                                    <c:when test="${productAffinityData.viewCount > 1}">
                                        <spring:theme code="text.customer360.yprofile.recent.views" text="views" />
                                    </c:when>
                                    <c:otherwise>
                                        <spring:theme code="text.customer360.yprofile.recent.view" text="view" />
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="asm__customer360-overview-product-time">
                                <spring:theme code="text.asm.util.${viewedSince}" arguments="${viewedSince.value}"/>&nbsp;
                                <spring:theme code="text.asm.util.ago" text="ago"/>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>
</div>