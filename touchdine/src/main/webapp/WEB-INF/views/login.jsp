<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
 <title>EzMenutouch</title>
  <spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	
 <!-- #CSS Links -->
		<!-- Basic Styles -->
		 <spring:url value="css/bootstrap.min.css" var="bootstrapCss" />
		 <link href="${bootstrapCss}" rel="stylesheet" />
		 
		 <spring:url value="css/font-awesome.min.css" var="fontCss" />
		 <link href="${fontCss}" rel="stylesheet" />
		 
		 <spring:url value="css/smartadmin-production-plugins.min.css" var="prodCss" />
		 <link href="${prodCss}" rel="stylesheet" />
		 
		  <spring:url value="css/smartadmin-production.min.css" var="prodMinCss" />
		 <link href="${prodMinCss}" rel="stylesheet" />
		 
		  <spring:url value="css/smartadmin-skins.min.css" var="smartAdminSkins" />
		 <link href="${smartAdmin}" rel="stylesheet" />
		
         <spring:url value="css/smartadmin-rtl.min.css" var="smartAdminrtl" />
		 <link href="${smartAdminrtl}" rel="stylesheet" />
		
          
         <spring:url value="img/favicon/favicon.ico" var="smartAdminrtl" />
		 <link href="${smartAdminrtl}" rel="shortcut icon"  type="image/x-icon" />

	     <spring:url value="img/favicon/favicon.ico" var="smartAdminrtl" />
		 <link href="${smartAdminrtl}" rel="icon"  type="image/x-icon" />

		<!-- #GOOGLE FONT -->
		<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,700italic,300,400,700">

		<!-- #APP SCREEN / ICONS -->
		<!-- Specifying a Webpage Icon for Web Clip 
			 Ref: https://developer.apple.com/library/ios/documentation/AppleApplications/Reference/SafariWebContent/ConfiguringWebApplications/ConfiguringWebApplications.html -->
		
		<spring:url value="img/splash/sptouch-icon-iphone.png" var="iphone" />
		<link rel="apple-touch-icon" href="${iphone}"/>
		<spring:url value="img/splash/touch-icon-ipad.png" var="ipad" />
		<link rel="apple-touch-icon" sizes="76x76" href="${ipad}"/>
		<spring:url value="img/splash/touch-icon-iphone-retina.png" var="retina" />
		<link rel="apple-touch-icon" sizes="120x120" href="${retina}">
		 <spring:url value="img/splash/touch-icon-ipad-retina.png" var="ipadRetina" />
		<link rel="apple-touch-icon" sizes="152x152" href="${ipadRetina}">
		
		<!-- iOS web-app metas : hides Safari UI Components and Changes Status Bar Appearance -->
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		
		<!-- Startup image for web apps -->
		 <spring:url value="img/splash/ipad-landscape.png" var="landScape" />
		<link rel="apple-touch-startup-image" href="${landScape }" media="screen and (min-device-width: 481px) and (max-device-width: 1024px) and (orientation:landscape)">
		 <spring:url value="img/splash/ipad-portrait.png" var="potrait" />
		<link rel="apple-touch-startup-image" href="${potrait }"  media="screen and (min-device-width: 481px) and (max-device-width: 1024px) and (orientation:portrait)">
		 <spring:url value="img/splash/iphone.png" var="iphonestartup" />
		<link rel="apple-touch-startup-image" href="${iphonestartup}"  media="screen and (max-device-width: 320px)">
 
	
</head>
<body class="animated fadeInDown">

		<header id="header">

			<div id="logo-group">
				<span id="logo"> <img src="img/logo.png" alt="EzMenuTouch"> </span>
			</div>

			
		</header>

		<div id="main" role="main">

			<!-- MAIN CONTENT -->
			<div id="content" class="container">

				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-7 col-lg-8 hidden-xs hidden-sm">
						<h1 class="txt-color-red login-header-big">EzTochMenu</h1>
						<div class="hero">

							<div class="pull-left login-desc-box-l">
								<h4 class="paragraph-header">A new Dining Experience </h4>
							</div>
							
						<!-- <img src="img/demo/iphoneview.png" class="pull-right display-image" alt="" style="width:210px"> -->

						</div>

					</div>
					<div class="col-xs-12 col-sm-12 col-md-5 col-lg-4">
						<div class="well no-padding">
							<form method="post"  action="login" id="login-form" class="smart-form client-form">
								<header>
									Sign In
								</header>

								<fieldset>
									
									<section>
										<label class="label">E-mail</label>
										<label class="input"> <i class="icon-append fa fa-user"></i>
											<input type="email" name="email">
											<b class="tooltip tooltip-top-right"><i class="fa fa-user txt-color-teal"></i> Please enter email address/username</b></label>
									</section>

									<section>
										<label class="label">Password</label>
										<label class="input"> <i class="icon-append fa fa-lock"></i>
											<input type="password" name="password">
											<b class="tooltip tooltip-top-right"><i class="fa fa-lock txt-color-teal"></i> Enter your password</b> </label>
										
									</section>

									<section>
										<label class="checkbox">
											<input type="checkbox" name="remember" checked="">
											<i></i>Stay signed in</label>
									</section>
								</fieldset>
								<footer>
									<button type="submit" class="btn btn-primary">
										Sign in
									</button>
								</footer>
							</form>

						</div>				
						
					</div>
				</div>
			</div>

		</div>

		<!--================================================== -->	

		<!-- PACE LOADER - turn this on if you want ajax loading to show (caution: uses lots of memory on iDevices)-->
		<script src="js/plugin/pace/pace.min.js"></script>

	    <!-- Link to Google CDN's jQuery + jQueryUI; fall back to local -->
	    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
		<script> if (!window.jQuery) { document.write('<script src="js/libs/jquery-2.1.1.min.js"><\/script>');} </script>

	    <script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
		<script> if (!window.jQuery.ui) { document.write('<script src="js/libs/jquery-ui-1.10.3.min.js"><\/script>');} </script>

		<!-- IMPORTANT: APP CONFIG -->
		<script src="js/app.config.js"></script>

		<!-- JS TOUCH : include this plugin for mobile drag / drop touch events 		
		<script src="js/plugin/jquery-touch/jquery.ui.touch-punch.min.js"></script> -->

		<!-- BOOTSTRAP JS -->		
		<script src="js/bootstrap/bootstrap.min.js"></script>

		<!-- JQUERY VALIDATE -->
		<script src="js/plugin/jquery-validate/jquery.validate.min.js"></script>
		
		<!-- JQUERY MASKED INPUT -->
		<script src="js/plugin/masked-input/jquery.maskedinput.min.js"></script>
		
		<!--[if IE 8]>
			
			<h1>Your browser is out of date, please update your browser by going to www.microsoft.com/download</h1>
			
		<![endif]-->

		<!-- MAIN APP JS FILE -->
		<script src="js/app.min.js"></script>

		<script type="text/javascript">
			runAllForms();

			$(function() {
				// Validation
				$("#login-form").validate({
					// Rules for form validation
					rules : {
						email : {
							required : true,
							email : true
						},
						password : {
							required : true,
							minlength : 3,
							maxlength : 20
						}
					},

					// Messages for form validation
					messages : {
						email : {
							required : 'Please enter your email address',
							email : 'Please enter a VALID email address'
						},
						password : {
							required : 'Please enter your password'
						}
					},

					// Do not change code below
					errorPlacement : function(error, element) {
						error.insertAfter(element.parent());
					}
				});
			});
		</script>

	</body>
