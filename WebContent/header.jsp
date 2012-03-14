<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.3/jquery.min.js"></script> 
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.2/jquery-ui.min.js"></script>
<script type="text/javascript" src="static/js/header.js"></script>

<link type="text/css" href="static/css/all.css" rel="stylesheet"/>
<link type="text/css" href="static/css/header.css" rel="stylesheet"/>


<div id="header">

	<div id="user-row">
		<div id="logo">
			<a href="index.jsp" class="logo">QuizWebsite</a>
		</div>
		<!-- Search -->
		<form method="get" id="searchform" action="SearchServlet">
			<div id="search">
				<span> <input type="text" name="q" placeholder="Search"
					size="40" />
					<button class="search-button" title="Search" /> </span>
			</div>
		</form>

		<ul id="user-nav">

			<!-- <li id="notifications-link">
							<a href="/notifications">Notifications</a>
							{% if unseen_notification_count > 0 %}
						     <strong class="count">
						          {{ unseen_notification_count }}
						      </strong> 
						    {% endif %}
						</li> -->
			<li id="nav-account">
				<div id="profile-container">
					<div id="session">
						<a id="session-name" href="/profile.jsp"> <%=(String) session.getAttribute("username")%>
						</a> <span class="arrow-down arrow-down-unselected"></span>
					</div>

					<img
						src="http://animal.discovery.com/mammals/cheetah/pictures/cheetah-picture.jpg"
						id="header-profile-pic" />
					<div class="clearfloat"></div>
				</div>
				<div class="dropdown">
					<ul id="user-dropdown">
						<li><a href="profile.jsp">Profile</a></li>
						<li><a href="settings.jsp">Settings</a></li>
						<li><a href="logout.jsp">Log Out</a></li>
					</ul>
				</div>
			</li>
			<li><span class="header-create-plus">+</span><a
				id="create-list-link" href="create-quiz.jsp">Create a Quiz</a></li>
		</ul>
		<div class="clearfloat"></div>

	</div>
</div>