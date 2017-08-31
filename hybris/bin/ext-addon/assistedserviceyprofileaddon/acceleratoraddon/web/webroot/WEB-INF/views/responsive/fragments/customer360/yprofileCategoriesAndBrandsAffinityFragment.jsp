<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="assistedserviceutils" uri="http://hybris.com/tld/assistedserviceutils" %>


<div class="asm__customer360-overview-recent-section">
    <div class="row">
        <c:choose>
            <c:when test="${empty fragmentData}">
               <div class="col-xs-12">
                   <div class="asm__customer360-overview-divider">
                       <spring:theme code="text.customer360.yprofile.categoryAndBrands.empty" text="There are currently no Recent Category or Brand items" />
                   </div>
               </div>
            </c:when>
           <c:otherwise>
            <c:forEach items="${fragmentData}" var="categoryAndBrandData">
                <c:set var="viewedSince" value="${assistedserviceutils:timeSince(categoryAndBrandData.updated)}" scope="page"/>
                <c:url value="${categoryAndBrandData.categoryData.url}" var="categoryUrl" />
                <div class="col-xs-6 col-sm-3 col-md-2">
                    <div class="asm__customer360-overview-recent">
                        <div class="asm__customer360-overview-recent-image">
                            <a href="${categoryUrl}">
                                <c:choose>
                                    <c:when test="${not empty categoryAndBrandData.image}">
                                       <div class="hide_overflow"> <img src="${categoryAndBrandData.image.url}" alt="${fn:escapeXml(categoryAndBrandData.categoryData.name)}" title="${fn:escapeXml(categoryAndBrandData.categoryData.name)}"/></div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="hide_overflow"><theme:image code="img.missingProductImage" alt="${fn:escapeXml(categoryAndBrandData.categoryData.name)}" title="${fn:escapeXml(categoryAndBrandData.categoryData.name)}"/></div>
                                    </c:otherwise>
                                </c:choose>
                            </a>
                        </div>
                        <div class="asm__customer360-overview-recent-name">
                            <a href="${categoryUrl}">
                                ${categoryAndBrandData.categoryData.name}
                            </a>
                        </div>
                        <div class="asm__customer360-overview-recent-views">${categoryAndBrandData.viewCount}&nbsp;
                            <c:choose>
                                <c:when test="${categoryAndBrandData.viewCount > 1}">
                                    <spring:theme code="text.customer360.yprofile.recent.views" text="views" />
                                </c:when>
                                <c:otherwise>
                                    <spring:theme code="text.customer360.yprofile.recent.view" text="view" />
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="asm__customer360-overview-recent-time">
                            <spring:theme code="text.asm.util.${viewedSince}" arguments="${viewedSince.value}" text="while"/>&nbsp;
                            <spring:theme code="text.asm.util.ago" text="ago"/>
                        </div>
                    </div>
                </div>
            </c:forEach>
           </c:otherwise>
        </c:choose>
    </div>
</div>