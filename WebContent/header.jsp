<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.3/jquery.min.js"></script> 
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.2/jquery-ui.min.js"></script>
<script type="text/javascript" src="static/js/header.js"></script>
<script type="text/javascript" src="static/js/tooltips.js"></script>
<script type="text/javascript" src="static/js/jquery.tipTip.js"></script>

<link type="text/css" href="static/css/all.css" rel="stylesheet"/>
<link type="text/css" href="static/css/header.css" rel="stylesheet"/>
<link type="text/css" href="static/css/buttons.css" rel="stylesheet"/>
<link type="text/css" href="static/css/tooltips.css" rel="stylesheet"/>


<div id="header">
	<% String loggedInUser = (String) session.getAttribute("username"); %>
	<div id="user-row">
		<div id="logo">
			<a href="index.jsp" class="logo">QuizWebsite</a>
		</div>
		<!-- Search -->
			<div id="search">
				<form action="search.jsp" method="get" class="search-form">"
					<span>
						<input type="text" name="query" placeholder="Search" size="40" />
						<button type="submit" class="search-button" title="Search" /> 
					</span>
				</form>
			</div>

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
						<a id="session-name" href="user.jsp?username=<%=(String) session.getAttribute("username")%>">
						<%
						out.println(model.User.getUser((String) session.getAttribute("username")).firstName); 
						%>
						</a> <span class="arrow-down arrow-down-unselected"></span>
					</div>
					
					<%
					out.println("<img src=\"");
					out.println(model.User.getUser((String) session.getAttribute("username")).imageUrl); 
					out.println("\" id=\"header-profile-pic\" />");
					%>

					<div class="clearfloat"></div>
				</div>
				<div class="dropdown">
					<ul id="user-dropdown">
						<li><a href="user.jsp?username=<%=(String) session.getAttribute("username")%>">Profile</a></li>
						<li><a href="messages.jsp">Inbox</a></li>
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