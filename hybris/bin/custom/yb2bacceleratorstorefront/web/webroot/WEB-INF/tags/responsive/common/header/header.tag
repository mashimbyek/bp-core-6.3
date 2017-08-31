<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="hideHeaderLinks" required="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav"%>
<%@ taglib prefix="formElement"
	tagdir="/WEB-INF/tags/responsive/formElement"%>

<!-- TEST -->
<!--  For twitter button -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>


<style>
/* The Modal (background) */
.modal1 {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 5; /* Sit on top */
	padding-top: 100px; /* Location of the box */
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content1 {
	position: relative;
	background-color: #fefefe;
	margin: auto;
	padding: 0;
	border: 1px solid #888;
	width: 40%;
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.19);
	-webkit-animation-name: animatetop;
	-webkit-animation-duration: 0.4s;
	animation-name: animatetop;
	animation-duration: 0.4s
}

/* Add Animation */
@
-webkit-keyframes animatetop {
	from {top: -300px;
	opacity: 0
}

to {
	top: 0;
	opacity: 1
}

}
@
keyframes animatetop {
	from {top: -300px;
	opacity: 0
}

to {
	top: 0;
	opacity: 1
}

}

/* The Close Button */
.close3 {
	color: white;
	float: right;
	font-size: 28px;
	font-weight: bold;
}

.close3:hover, .close3:focus {
	color: #000;
	text-decoration: none;
	cursor: pointer;
}

.modal-header1 {
	padding: 2px 16px;
	background-color: #142939;
	color: white;
}

.modal-body1 {
	padding: 2px 16px;
}

.modal-footer1 {
	padding: 2px 16px;
	background-color: #142939;
	color: white;
}

/* The Modal (background) */
.modal2 {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 5; /* Sit on top */
	padding-top: 100px; /* Location of the box */
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content2 {
	position: relative;
	background-color: #fefefe;
	margin: auto;
	padding: 0;
	border: 1px solid #888;
	width: 60%;
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.19);
	-webkit-animation-name: animatetop1;
	-webkit-animation-duration: 0.4s;
	animation-name: animatetop1;
	animation-duration: 0.4s
}

/* Add Animation */
@
-webkit-keyframes animatetop1 {
	from {top: -300px;
	opacity: 0
}

to {
	top: 0;
	opacity: 1
}

}
@
keyframes animatetop1 {
	from {top: -300px;
	opacity: 0
}

to {
	top: 0;
	opacity: 1
}

}

/* The Close Button */
.close4 {
	color: white;
	float: right;
	font-size: 28px;
	font-weight: bold;
}

.close4:hover, .close4:focus {
	color: #000;
	text-decoration: none;
	cursor: pointer;
}

.modal-header2 {
	padding: 2px 16px;
	background-color: #142939;
	color: white;
}

.modal-body2 {
	padding: 2px 16px;
}

.modal-footer2 {
	padding: 2px 16px;
	background-color: #142939;
	color: white;
}

gh {
	display: none;
}

ghs {
	display: none;
}

a {
	cursor: pointer;
}

.fa {
	font-size: 30px;
	width: 160px;
	text-decoration: none;
}

.fa:hover {
	opacity: 0.7;
}

.fa-twitter {
	background: #55ACEE;
	color: white;
}

.danQ {
	content: '?';
	display: inline-block;
	font-family: sans-serif;
	font-weight: bold;
	text-align: center;
	width: 1.8ex;
	height: 1.8ex;
	font-size: 9px;
	line-height: 1.8ex;
	border-radius: 1.2ex;
	margin-right: 4px;
	padding: 1px;
	color: white;
	background: white;
	border-color: white;
	text-decoration: none;
	border-color: white;
}

.danQ1 {
	content: '?';
	display: inline-block;
	font-family: sans-serif;
	font-weight: bold;
	text-align: center;
	width: 1.8ex;
	height: 1.8ex;
	font-size: 1.4ex;
	line-height: 1.8ex;
	border-radius: 1.2ex;
	margin-right: 4px;
	padding: 1px;
	color: blue;
	background: white;
	border: 1px solid blue;
	text-decoration: none;
	line-height: 1.8ex;
	border-radius: 1.2ex;
	margin-right: 4px;
	padding: 1px;
	color: blue;
	background: white;
	border: 1px solid blue;
}
</style>


<!-- TEST -->






<cms:pageSlot position="TopHeaderSlot" var="component" element="div"
	class="container">
	<cms:component component="${component}" />
</cms:pageSlot>

<header class="js-mainHeader">
	<nav class="navigation navigation--top hidden-xs hidden-sm">
		<div class="row">
			<div class="col-sm-12 col-md-4">
				<div class="nav__left js-site-logo">
					<cms:pageSlot position="SiteLogo" var="logo" limit="1">
						<cms:component component="${logo}" element="div"
							class="yComponentWrapper" />
					</cms:pageSlot>
				</div>
			</div>
			<div class="col-sm-12 col-md-8">
				<div class="nav__right">
					<ul class="nav__links nav__links--account">
						<c:if test="${empty hideHeaderLinks}">
							<c:if test="${uiExperienceOverride}">
								<li class="backToMobileLink"><c:url
										value="/_s/ui-experience?level=" var="backToMobileStoreUrl" />
									<a href="${backToMobileStoreUrl}"> <spring:theme
											code="text.backToMobileStore" />
								</a></li>
							</c:if>

							<sec:authorize access="!hasAnyRole('ROLE_ANONYMOUS')">
								<c:set var="maxNumberChars" value="25" />
								<c:if test="${fn:length(user.firstName) gt maxNumberChars}">
									<c:set target="${user}" property="firstName"
										value="${fn:substring(user.firstName, 0, maxNumberChars)}..." />
								</c:if>

								<li class="logged_in js-logged_in"><ycommerce:testId
										code="header_LoggedUser">
										<spring:theme code="header.welcome"
											arguments="${user.firstName},${user.lastName}"
											htmlEscape="true" />
									</ycommerce:testId></li>
							</sec:authorize>

							<cms:pageSlot position="HeaderLinks" var="link">
								<cms:component component="${link}" element="li" />
							</cms:pageSlot>

							<sec:authorize access="hasAnyRole('ROLE_ANONYMOUS')">
								<li class="liOffcanvas"><ycommerce:testId
										code="header_Login_link">
										<a href="<c:url value='/login'/>"> <spring:theme
												code="header.link.login" />
										</a>
									</ycommerce:testId></li>
							</sec:authorize>

							<sec:authorize access="!hasAnyRole('ROLE_ANONYMOUS')">
								<li class="liOffcanvas"><ycommerce:testId
										code="header_signOut">
										<a href="<c:url value='/logout'/>"> <spring:theme
												code="header.link.logout" />
										</a>
									</ycommerce:testId></li>
							</sec:authorize>

						</c:if>
					</ul>
				</div>
			</div>
		</div>
	</nav>
	<%-- a hook for the my account links in desktop/wide desktop--%>
	<div class="hidden-xs hidden-sm js-secondaryNavAccount collapse"
		id="accNavComponentDesktopOne">
		<ul class="nav__links">

		</ul>
	</div>
	<div class="hidden-xs hidden-sm js-secondaryNavCompany collapse"
		id="accNavComponentDesktopTwo">
		<ul class="nav__links js-nav__links">

		</ul>
	</div>
	<nav class="navigation navigation--middle js-navigation--middle">
		<div class="container-fluid">
			<div class="row">
				<div class="mobile__nav__row mobile__nav__row--table">
					<div class="mobile__nav__row--table-group">
						<div class="mobile__nav__row--table-row">
							<div class="mobile__nav__row--table-cell visible-xs hidden-sm">
								<button
									class="mobile__nav__row--btn btn mobile__nav__row--btn-menu js-toggle-sm-navigation"
									type="button">
									<span class="glyphicon glyphicon-align-justify"></span>
								</button>

							</div>







							<!----------------------------------------------------------------------------- TEST START Shows the new get help button in mobile-->


							<ycommerce:testId code="get_Help_link_small">
								<div
									class="mobile__nav__row--table-cell hidden-sm hidden-md hidden-lg mobile__nav__row--seperator">
									<a id="getHelpBtn1"
										class="mobile__nav__row--btn mobile__nav__row--btn-location btn">
										<span class="glyphicon glyphicon-question-sign"
										title="Get Help"></span>
									</a>


									<!-- The Modal -->
									<div id="getHelpModal1" class="modal2">

										<!-- Modal content -->
										<div class="modal-content2">
											<div class="modal-header2">
												<span class="close4">&times;</span>
												<h2>
													Get Help <span align="right" class="danQ1">?</span>
												</h2>
											</div>
											<div class="modal-body2">

												<p>
													<a
														href="https://powertools.local:9002/yb2bacceleratorstorefront/powertools/en/USD/faq"
														target="_blank">FAQ PAGE</a>
												</p>
												<p>
													<a
														href="https://powertools.local:9002/yb2bacceleratorstorefront/powertools/en/USD/my-account/support-tickets"
														target="_blank">LOG A HELP TICKET</a>
												</p>
												<p>
													<a onclick="myFunction1()" class="linkButton">PLEASE
														CONTACT ME</a>
												</p>
												<form>
													<ghs id="getHelp1"> EMAIL <input type="radio"
														name="type" value="Email1" checked='checked'>
													PHONE <input type="radio" name="type" value="Phone1">
													TWITTER <input type="radio" name="type" value="Twitter1">
													<script>
														$(function() {
															$('#send_to_phone1')
																	.children()
																	.prop(
																			'disabled',
																			true);
															$(
																	'input[name="type"]')
																	.on(
																			'click',
																			function() {
																				if ($(
																						this)
																						.val() == 'Email1') {
																					$(
																							'#send_to_email1')
																							.show();

																					$(
																							'#send_to_email1')
																							.children()
																							.prop(
																									'disabled',
																									false);

																					$(
																							'#send_to_name1')
																							.show();

																					$(
																							'#send_to_name1')
																							.children()
																							.prop(
																									'disabled',
																									false);

																					$(
																							'#send_to_phone1')
																							.hide();

																					$(
																							'#send_to_phone1')
																							.children()
																							.prop(
																									'disabled',
																									true);

																					$(
																							'#send_to_twitter1')
																							.hide();
																					$(
																							'#send_us1')
																							.show();

																				}
																				if ($(
																						this)
																						.val() == 'Phone1') {
																					$(
																							'#send_to_email1')
																							.hide();

																					$(
																							'#send_to_email1')
																							.children()
																							.prop(
																									'disabled',
																									true);

																					$(
																							'#send_to_name1')
																							.show();

																					$(
																							'#send_to_name1')
																							.children()
																							.prop(
																									'disabled',
																									false);

																					$(
																							'#send_to_phone1')
																							.show();

																					$(
																							'#send_to_phone1')
																							.children()
																							.prop(
																									'disabled',
																									false);

																					$(
																							'#send_to_twitter1')
																							.hide();
																					$(
																							'#send_us1')
																							.show();

																				}
																				if ($(
																						this)
																						.val() == 'Twitter1') {
																					$(
																							'#send_to_email1')
																							.hide();

																					$(
																							'#send_to_name1')
																							.hide();

																					$(
																							'#send_to_name1')
																							.children()
																							.prop(
																									'disabled',
																									true);

																					$(
																							'#send_to_phone1')
																							.hide();
																					$(
																							'#send_to_twitter1')
																							.show();
																					$(
																							'#send_us1')
																							.hide();
																				}

																			});
														});
													</script>
													<div id="send_to_name1">
														NAME*:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br />
														<input type="text" name=name required id="name"> <br />
													</div>
													<br />

													I NEED HELP WITH*:
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea
														rows="1" cols="20" name=description required
														id="description45" maxlength="100"></textarea> <br />
													<br />
													<div id="send_to_email1">
														EMAIL*:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br />
														<input type="email" name="Email" required id="email">
														<br /> <br />
													</div>
													<div id="send_to_phone1" style="display: none">
														PHONE NUMBER*:
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br />
														<input type="number" name="Phone number" id="phone">
														<br /> <br />
													</div>
													<div id="send_to_twitter1" style="display: none">

														<script>
															function twitterFunction1() {

																var x = document
																		.getElementById('description45').value;
																var hrf = "https://twitter.com/messages/compose?recipient_id=801018578946392064&text="
																		+ x;
																document
																		.getElementById("a_link1").href = hrf;

															}
														</script>
														<a href="#" id="a_link1" onclick="twitterFunction1();"
															type="submit" class="fa fa-twitter" target="_blank">Tweet
															Us</a>


													</div>

													<div id="send_us1">
														<input code=1 type="submit" value="CONTACT ME"
															class="btn-primary btn-place-order btn-block btn-lg " />
													</div>
													</ghs>

												</form>
												<!-- JavaScript to show or hide the content -->
												<script>
													function myFunction1() {
														var xx = document
																.getElementById('getHelp1');
														if (xx.style.display === 'block') {
															xx.style.display = 'none';
														} else {
															xx.style.display = 'block';
														}
													}
												</script>


											</div>
											<div class="modal-footer2">
												<h3></h3>
											</div>

										</div>

									</div>

									<script>
										// Get the modal
										var modal1 = document
												.getElementById('getHelpModal1');

										// Get the button that opens the modal
										var btn1 = document
												.getElementById("getHelpBtn1");

										// Get the <span> element that closes the modal
										var span1 = document
												.getElementsByClassName("close4")[0];

										// When the user clicks the button, open the modal 
										btn1.onclick = function() {
											modal1.style.display = "block";
										}

										// When the user clicks on <span> (x), close the modal
										span1.onclick = function() {
											modal1.style.display = "none";
										}

										// When the user clicks anywhere outside of the modal, close it
										window.onclick = function(event) {
											if (event.target == modal) {
												modal1.style.display = "none";
											}
										}
									</script>

								</div>

							</ycommerce:testId>

							<!----------------------------------------------------------------------------- TEST END Shows the new get help button in mobile-->



							<div
								class="mobile__nav__row--table-cell visible-xs mobile__nav__row--seperator">
								<ycommerce:testId code="header_search_activation_button">
									<button
										class="mobile__nav__row--btn btn mobile__nav__row--btn-search js-toggle-xs-search hidden-sm hidden-md hidden-lg"
										type="button">
										<span class="glyphicon glyphicon-search"></span>
									</button>
								</ycommerce:testId>
							</div>

							<c:if test="${empty hideHeaderLinks}">
								<ycommerce:testId code="header_StoreFinder_link">
									<div
										class="mobile__nav__row--table-cell hidden-sm hidden-md hidden-lg mobile__nav__row--seperator">
										<a href="<c:url value="/store-finder"/>"
											class="mobile__nav__row--btn mobile__nav__row--btn-location btn">
											<span class="glyphicon glyphicon-map-marker"></span>
										</a>

									</div>
								</ycommerce:testId>
							</c:if>

							<cms:pageSlot position="MiniCart" var="cart" element="div"
								class="miniCartSlot componentContainer mobile__nav__row--table hidden-sm hidden-md hidden-lg">
								<cms:component component="${cart}" element="div"
									class="mobile__nav__row--table-cell" />
							</cms:pageSlot>

						</div>
					</div>
				</div>
			</div>
			<div class="row desktop__nav">
				<div class="nav__left col-xs-12 col-sm-6">
					<div class="row">
						<div class="col-sm-2 hidden-xs visible-sm mobile-menu">
							<button class="btn js-toggle-sm-navigation" type="button">
								<span class="glyphicon glyphicon-align-justify"></span>
							</button>
						</div>
						<div class="col-sm-10">
							<div class="site-search">
								<cms:pageSlot position="SearchBox" var="component">
									<cms:component component="${component}" element="div" />
								</cms:pageSlot>
							</div>
						</div>
					</div>
				</div>
				<div class="nav__right col-xs-6 col-xs-6 hidden-xs">
					<ul class="nav__links nav__links--shop_info">
						<li><c:if test="${empty hideHeaderLinks}">
								<ycommerce:testId code="header_StoreFinder_link">
									<div class="nav-location hidden-xs">
										<a href="<c:url value="/store-finder"/>" class="btn"> <span
											class="glyphicon glyphicon-map-marker"></span>
										</a>
									</div>
								</ycommerce:testId>





								<!----------------------------------------------------------------------------- TEST Start Shows the new get help button for desktop-->


								<ycommerce:testId code="get_Help_link">
									<div class="nav-location hidden-xs">

										<a class="btn" id="getHelpBtn"> <span
											class="glyphicon glyphicon-question-sign" title="Get Help"></span>
										</a>

										<!-- The Modal-->
										<div id="getHelpModal" class="modal1">

											<!-- Modal content -->
											<div class="modal-content1">
												<div class="modal-header1">
													<span class="close3">&times;</span>
													<h2>
														Get Help <span align="right" class="danQ">?</span>
													</h2>
												</div>
												<div class="modal-body1">

													<p>
														<a
															href="https://powertools.local:9002/yb2bacceleratorstorefront/powertools/en/USD/faq"
															target="_blank">FAQ PAGE</a>
													</p>
													<p>
														<a
															href="https://powertools.local:9002/yb2bacceleratorstorefront/powertools/en/USD/my-account/support-tickets"
															target="_blank">LOG A HELP TICKET</a>
													</p>
													<p>
														<a onclick="myFunction()" class="linkButton">PLEASE
															CONTACT ME</a>
													</p>
													<form>
														<gh id="getHelp"> EMAIL <input type="radio"
															name="type" value="Email" checked='checked'>

														PHONE <input type="radio" name="type" value="Phone">

														TWITTER <input type="radio" name="type" value="Twitter">


														<script>
															$(function() {
																$(
																		'#send_to_phone')
																		.children()
																		.prop(
																				'disabled',
																				true);
																$(
																		'input[name="type"]')
																		.on(
																				'click',
																				function() {
																					if ($(
																							this)
																							.val() == 'Email') {
																						$(
																								'#send_to_email')
																								.show();

																						$(
																								'#send_to_name')
																								.show();

																						$(
																								'#send_to_name')
																								.children()
																								.prop(
																										'disabled',
																										false);

																						$(
																								'#send_to_email')
																								.children()
																								.prop(
																										'disabled',
																										false);

																						$(
																								'#send_to_phone')
																								.hide();

																						$(
																								'#send_to_phone')
																								.children()
																								.prop(
																										'disabled',
																										true);

																						$(
																								'#send_to_twitter')
																								.hide();
																						$(
																								'#send_us')
																								.show();

																					}
																					if ($(
																							this)
																							.val() == 'Phone') {
																						$(
																								'#send_to_email')
																								.hide();

																						$(
																								'#send_to_email')
																								.children()
																								.prop(
																										'disabled',
																										true);

																						$(
																								'#send_to_name')
																								.show();

																						$(
																								'#send_to_name')
																								.children()
																								.prop(
																										'disabled',
																										false);

																						$(
																								'#send_to_phone')
																								.show();

																						$(
																								'#send_to_phone')
																								.children()
																								.prop(
																										'disabled',
																										false);

																						$(
																								'#send_to_twitter')
																								.hide();
																						$(
																								'#send_us')
																								.show();

																					}
																					if ($(
																							this)
																							.val() == 'Twitter') {
																						$(
																								'#send_to_email')
																								.hide();
																						$(
																								'#send_to_phone')
																								.hide();
																						$(
																								'#send_to_twitter')
																								.show();
																						$(
																								'#send_us')
																								.hide();

																						$(
																								'#send_to_name')
																								.hide();

																						$(
																								'#send_to_name')
																								.children()
																								.prop(
																										'disabled',
																										true);
																					}

																				});
															});
														</script> <br />
														<br />
											

															<div id="send_to_name">
																NAME*:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
																	type="text" name=name required id="name"> <br />
																<br />
															</div>

															I NEED HELP WITH*:<br />
															&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
															<textarea rows="1" cols="20" name=description required
																id="description44" maxlength="100"></textarea>
															<br />

															<div id="send_to_email">
																EMAIL*:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
																	type="email" name="Email" required id="email">
																<br /> <br />
															</div>

															<div id="send_to_phone" style="display: none">
																PHONE NUMBER*:<input type="number" name="Phone number"
																	id="phone" required> <br /> <br />
															</div>

															<div id="send_to_twitter" style="display: none">

																<script>
																	function twitterFunction() {

																		var x = document
																				.getElementById('description44').value;
																		var hrf = "https://twitter.com/messages/compose?recipient_id=801018578946392064&text="
																				+ x;
																		document
																				.getElementById("a_link").href = hrf;

																	}
																</script>
																<a href="#" id="a_link" onclick="twitterFunction();"
																	type="submit" class="fa fa-twitter" target="_blank">Tweet
																	Us</a>

															</div>

															<div id="send_us">
																<input type="submit" value="Contact Me"
																	class="btn-primary btn-place-order btn-block btn-lg " />
															</div>

														</form>
														</gh>

													</form>
													<!-- JavaScript to show or hide the content -->
													<script>
														function myFunction() {
															var x = document
																	.getElementById('getHelp');
															if (x.style.display === 'block') {
																x.style.display = 'none';
															} else {
																x.style.display = 'block';
															}
														}
													</script>

												</div>
												<div class="modal-footer1">
													<h3></h3>



												</div>

											</div>

										</div>

										<script>
											// Get the modal
											var modal = document
													.getElementById('getHelpModal');

											// Get the button that opens the modal
											var btn = document
													.getElementById("getHelpBtn");

											// Get the <span> element that closes the modal
											var span = document
													.getElementsByClassName("close3")[0];

											// When the user clicks the button, open the modal 
											btn.onclick = function() {
												modal.style.display = "block";
											}

											// When the user clicks on <span> (x), close the modal
											span.onclick = function() {
												modal.style.display = "none";
											}

											// When the user clicks anywhere outside of the modal, close it
											window.onclick = function(event) {
												if (event.target == modal) {
													modal.style.display = "none";
												}
											}
										</script>
									</div>
								</ycommerce:testId>


								<!----------------------------------------------------------------------------- TEST End Shows the new get help button for desktop-->



							</c:if></li>

						<li><cms:pageSlot position="MiniCart" var="cart"
								element="div" class="componentContainer">

								<cms:component component="${cart}" element="div" />

							</cms:pageSlot></li>
					</ul>
				</div>
			</div>
		</div>
	</nav>
	<a id="skiptonavigation"></a>
	<nav:topNavigation />
</header>


<cms:pageSlot position="BottomHeaderSlot" var="component" element="div"
	class="container-fluid">
	<cms:component component="${component}" />
</cms:pageSlot>
