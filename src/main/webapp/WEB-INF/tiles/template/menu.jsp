<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<!-- Title and other stuffs -->
<title></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="author" content="">

<!-- bootstrap & fontawesome -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resource/assets/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resource/css/AdminLTE.min.css" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resource/assets/font-awesome/4.5.0/css/font-awesome.min.css" />

<!-- text fonts -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resource/assets/css/fonts.googleapis.com.css" />
<!-- ace styles -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resource/assets/css/ace.min.css"
	class="ace-main-stylesheet" id="main-ace-style" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resource/assets/css/ace-skins.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resource/assets/css/ace-rtl.min.css" />
<!-- ace settings handler -->
<script
	src="${pageContext.request.contextPath}/resource/assets/js/ace-extra.min.js"></script>

<script
	src="${pageContext.request.contextPath}/resource/bufferGrid/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resource/bufferGrid/w2ui.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resource/bufferGrid/w2ui.min.css" />

<script src="${pageContext.request.contextPath}/resource/js/common.js"></script>

<link
	href="${pageContext.request.contextPath}/resource/dataTable/jquery.dataTables.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resource/dataTable/buttons.dataTables.min.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/resource/dataTable/jquery-1.12.3.js"></script>
<script
	src="${pageContext.request.contextPath}/resource/dataTable/jquery.dataTables.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resource/dataTable/dataTables.buttons.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resource/dataTable/buttons.flash.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resource/dataTable/jszip.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resource/dataTable/pdfmake.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resource/dataTable/vfs_fonts.js"></script>
<script
	src="${pageContext.request.contextPath}/resource/dataTable/buttons.html5.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resource/dataTable/buttons.print.min.js"></script>
	
	
	<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/js/bootstrap-datepicker.min.js"></script>
	<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/css/bootstrap-datepicker.css"
	rel="stylesheet" type="text/css">

<link
	href="${pageContext.request.contextPath}/resource/select2/select2.min.css"
	rel="stylesheet" type="text/css">
<script
	src="${pageContext.request.contextPath}/resource/select2/select2.min.js"></script>

<link
	href="${pageContext.request.contextPath}/resource/w2ui/w2ui-1.5.rc1.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resource/w2ui/w2ui-1.5.rc1.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/resource/w2ui/w2ui-1.5.rc1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resource/w2ui/w2ui-1.5.rc1.js"></script>
<script src="https://cdn.ckeditor.com/4.10.0/standard-all/ckeditor.js"></script>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resource/css/style.css" />
<body>

	<div id="sidebar" class="sidebar responsive ace-save-state">
		<script type="text/javascript">
			try {
				ace.settings.loadState('sidebar')
			} catch (e) {
			}
		</script>

		<div class="sidebar-shortcuts" id="sidebar-shortcuts">
			<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
				<button class="btn btn-success">
					<i class="ace-icon fa fa-signal"></i>
				</button>

				<button class="btn btn-info">
					<i class="ace-icon fa fa-pencil"></i>
				</button>

				<button class="btn btn-warning">
					<i class="ace-icon fa fa-users"></i>
				</button>

				<button class="btn btn-danger">
					<i class="ace-icon fa fa-cogs"></i>
				</button>
			</div>

			<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
				<span class="btn btn-success"></span> <span class="btn btn-info"></span>

				<span class="btn btn-warning"></span> <span class="btn btn-danger"></span>
			</div>
		</div>
		<!-- /.sidebar-shortcuts -->

		<ul class="nav nav-list">
			<li class=""><a href="${pageContext.request.contextPath}/">
					<i class="menu-icon fa fa-tachometer"></i> <span class="menu-text">
						Dashboard </span>
			</a> <b class="arrow"></b></li>

			<sec:authorize access="hasRole('ROLE_ADMIN')">
			<li class=""><a href="javascript:void(0)"
				class="dropdown-toggle"> <i class="menu-icon fa fa-gear fa-spin"></i>
					<span class="menu-text"> Settings </span> <b
					class="arrow fa fa-angle-down"></b>
			</a> <b class="arrow"></b>

				<ul class="submenu">
					

					
						
					<li class=""><a href="javascript:void(0)"
						class="dropdown-toggle"><i class="menu-icon fa fa-calendar purple"></i> Bank <b
							class="arrow fa fa-angle-down"></b>
					</a> <b class="arrow"></b>

						<ul class="submenu">
							
							<li class=""><a
								href="${pageContext.request.contextPath}/addBank">
									<i class="menu-icon fa fa-plus purple"></i> Add Bank
							</a> <b class="arrow"></b>
							</li>

							<li class=""><a
								href="${pageContext.request.contextPath}/bankList"> <i
									class="menu-icon fa fa-list purple"></i> Bank List
							</a> <b class="arrow"></b>
							</li>
							
							<li class=""><a
								href="${pageContext.request.contextPath}/addBankBranch"> <i
									class="menu-icon fa fa-plus"></i> Add Branch
							</a> <b class="arrow"></b>
							</li>
							
							<li class=""><a
								href="${pageContext.request.contextPath}/branchBankList"> <i
									class="menu-icon fa fa-list"></i> Branch List
							</a> <b class="arrow"></b>
							</li>
						</ul>
						
						</li>
						
						<li class=""><a href="javascript:void(0)"
						class="dropdown-toggle"><i class="menu-icon fa fa-calendar purple"></i> Nbfi <b
							class="arrow fa fa-angle-down"></b>
					</a> <b class="arrow"></b>

						<ul class="submenu">
						
							<li class=""><a
								href="${pageContext.request.contextPath}/addNbfi">
									<i class="menu-icon fa fa-plus purple"></i> Add Nbfi
							</a> <b class="arrow"></b></li>

							<li class=""><a
								href="${pageContext.request.contextPath}/nbfiList"> <i
									class="menu-icon fa fa-list"></i> Nbfi List
							</a> <b class="arrow"></b>
							</li>
							
							<li class=""><a
								href="${pageContext.request.contextPath}/addNbfiBranch"> <i
									class="menu-icon fa fa-plus"></i> Add Branch
							</a> <b class="arrow"></b>
							</li>
							
							<li class="">
							<a href="${pageContext.request.contextPath}/branchNbfiList"> <i
									class="menu-icon fa fa-list"></i> Branch List
							</a> <b class="arrow"></b>
							</li>
							
						</ul>
						
						</li>
						
						<li class=""><a href="javascript:void(0)"
						class="dropdown-toggle"><i class="menu-icon fa fa-calendar purple"></i> District <b
							class="arrow fa fa-angle-down"></b>
					</a> <b class="arrow"></b>

						<ul class="submenu">
						
							<li class=""><a
								href="${pageContext.request.contextPath}/addDistrict">
									<i class="menu-icon fa fa-plus purple"></i> Add District
							</a> <b class="arrow"></b></li>

							<li class=""><a
								href="${pageContext.request.contextPath}/districtList"> <i
									class="menu-icon fa fa-list"></i> District List
							</a> <b class="arrow"></b>
							</li>
							
						</ul>
						
						</li>
						
				</ul> </li>
			</sec:authorize>

			
		</ul>
		<!-- /.nav-list -->

		<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
			<i id="sidebar-toggle-icon"
				class="ace-icon fa fa-angle-double-left ace-save-state"
				data-icon1="ace-icon fa fa-angle-double-left"
				data-icon2="ace-icon fa fa-angle-double-right"></i>
		</div>
	</div>




</body>
</html>

