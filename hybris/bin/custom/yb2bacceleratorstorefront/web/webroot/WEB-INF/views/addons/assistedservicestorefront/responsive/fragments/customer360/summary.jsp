<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="assistedserviceutils" uri="http://hybris.com/tld/assistedserviceutils" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="customer" value="${fragmentData}" scope="page"/>
<c:set var="address" value="${customer.address}" scope="page"/>
<c:set var="timeSince" value="${assistedserviceutils:timeSince(customer.signedUp)}" scope="page"/>
<c:set var="name" value="${fn:split(customer.fullName, ' ')[0]}" scope="page"/>

<div role="tabpanel" class="tab-pane active" id="customerDetailsSection">

<div class="asm__customer360-overview">
    <div class="asm__customer360-overview-info">
       <div class="row">
            <div class="col-sm-4">
               <div class="asm__customer360-overview-info-points">
                    <div class="asm__customer360-overview-info-headline">Loyalty Points</div>
                    <div class="asm__customer360-overview-info-points-pts">2455 PTS</div>
                    <div class="asm__customer360-overview-info-points-ptsinfo">Frequent Buyer</div>
                    <div class="asm__customer360-overview-info-points-next">140 pts to next level</div>
                    <div class="asm__customer360-overview-info-points-medals">
                        <div class="asm__customer360-overview-info-points-medals-item asm__customer360-overview-info-points-medals-item1">

                        </div>
                        <div class="asm__customer360-overview-info-points-medals-item asm__customer360-overview-info-points-medals-item1">

                        </div>
                        <div class="asm__customer360-overview-info-points-medals-item asm__customer360-overview-info-points-medals-item2">

                        </div>
                    </div>

                    <div class="asm__customer360-overview-info-points-medals-info">Shops 3 times per month, spends $57 above average</div>

                    <a href="" class="asm__customer360-overview-info-points-btn" id="asmVoucherCode" value="LoyalCoupon5">Eligible for 5% off next purchase</a>
               </div>
           </div>
           <div class="col-sm-4">
                <div class="asm__customer360-overview-info-is">
                    <div class="asm__customer360-overview-info-headline"><spring:theme code="${name}" htmlEscape="true"/> is</div>
                    <div class="asm__customer360-overview-info-is-tags">

                        <div class="asm__customer360-overview-info-is-tags-item">busy</div>
                        <div class="asm__customer360-overview-info-is-tags-item">on-the-go</div>
                        <div class="asm__customer360-overview-info-is-tags-item">early-adopting</div>
                        <div class="asm__customer360-overview-info-is-tags-item">trendy</div>
                        <div class="asm__customer360-overview-info-is-tags-item">frequent buyer</div>
                        <div class="asm__customer360-overview-info-is-tags-item">tech savvy</div>
                        <div class="asm__customer360-overview-info-is-tags-item">photographer</div>
                    </div>
                </div>
           </div>
           <div class="col-sm-4">
                <div class="asm__customer360-overview-info-uses">
                    <div class="asm__customer360-overview-info-headline"><spring:theme code="${name}" htmlEscape="true"/> uses</div>
                    <table class="asm__customer360-overview-info-table">
                        <tr>
                            <td>
                                <div class="asm__customer360-overview-info-uses-icon asm__customer360-overview-info-uses-icon-desktop"></div>
                            </td>
                            <td>
                                <div class="asm__customer360-overview-info-uses-icon asm__customer360-overview-info-uses-icon-tablet"></div>
                            </td>
                            <td>
                                <div class="asm__customer360-overview-info-uses-icon asm__customer360-overview-info-uses-icon-mobile"></div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="asm__customer360-overview-info-uses-value">50%</div>
                                <div class="asm__customer360-overview-info-uses-name">Desktop</div>
                            </td>
                            <td>
                                <div class="asm__customer360-overview-info-uses-value">25%</div>
                                <div class="asm__customer360-overview-info-uses-name">Tablet</div>
                            </td>
                            <td>
                               <div class="asm__customer360-overview-info-uses-value">25%</div>
                                <div class="asm__customer360-overview-info-uses-name">Mobile Phone</div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="asm__customer360-overview-info-uses-device">Windows 10<br>Chrome 54.0</div>
                            </td>
                            <td>
                                <div class="asm__customer360-overview-info-uses-device">IOS 8.2<br>Safari 5.1</div>
                            </td>
                            <td>
                                <div class="asm__customer360-overview-info-uses-device">Marshmallow<br>Chrome 54.0</div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="asm__customer360-overview-info-uses-browser asm__customer360-overview-info-uses-browser-chrome"></div>
                            </td>
                            <td>
                                <div class="asm__customer360-overview-info-uses-browser asm__customer360-overview-info-uses-browser-safari"></div>
                            </td>
                            <td>
                                <div class="asm__customer360-overview-info-uses-browser asm__customer360-overview-info-uses-browser-firefox"></div>
                            </td>
                        </tr>
                    </table>
                </div>
           </div>
       </div>
    </div>
</div>
<TEXTAREA ID="asmCopyHoldtext" STYLE="display:none;"></TEXTAREA>

<script>
	$("#asmVoucherCode").click(function(e) {
		copyToClipBoard($(this).attr('value'));
		e.preventDefault();
	});
</script>