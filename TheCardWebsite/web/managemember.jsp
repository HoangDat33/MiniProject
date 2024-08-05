<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page import = "java.text.DecimalFormat" %>
<%@page import = "Model.*" %>
<%@page import = "DAL.*" %>
<%@page import = "java.util.*" %>   
<%@page session="true" %>
<%@ page import="Model.*" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- mobile metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="initial-scale=1, maximum-scale=1">
        <!-- site metas -->
        <title>The Card Shop - Danh sách đăng ký</title>
        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="images/logo/logo_icon.png" type="image/x-icon">
        <!-- site icon -->
        <link rel="icon" href="images/fevicon.png" type="image/png" />
        <!-- bootstrap css -->
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <!-- site css -->
        <link rel="stylesheet" href="style.css" />
        <!-- responsive css -->
        <link rel="stylesheet" href="css/responsive.css" />
        <!-- color css -->
        <link rel="stylesheet" href="css/colors.css" />

        <!-- select bootstrap -->
        <link rel="stylesheet" href="css/bootstrap-select.css" />
        <!-- scrollbar css -->
        <link rel="stylesheet" href="css/perfect-scrollbar.css" />
        <!-- custom css -->
        <link rel="stylesheet" href="css/custom.css" />
        <!--[if lt IE 9]>-->
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <!-- Bao gồm CSS của Bootstrap -->
        <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">-->

        <!-- Bao gồm JS của Bootstrap và jQuery -->
        <!--        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
                <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>-->

        <style>
            .modal-lg-custom {
                max-width: 90%;
            }
            .modal-content-custom {
                width: 100%;
            }
            .full.graph_head {
                background-color: #f8f9fa; /* Light background color */
                padding: 10px; /* Padding around the content */
                border-radius: 8px; /* Rounded corners */
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* Subtle shadow for depth */
                justify-content: space-between;
            }

            .header-container {
                display: flex; /* Flexbox for layout */
                align-items: center; /* Center items vertically */
                justify-content: space-between; /* Space between the h2 and form */
            }

            .heading1 {
                font-size: 24px; /* Larger font size for heading */
                font-weight: bold; /* Bold text */
                color: #343a40; /* Dark text color */
                margin: 0; /* Remove default margin */
            }

            .search-form {
                display: flex; /* Flexbox for layout */
                align-items: center; /* Center vertically */
            }

            .input-group {
                margin-left: 700px;
                display: flex; /* Flexbox for input and button */
                border: 1px solid #ced4da; /* Border around the input group */
                /*border-radius: 4px;  Rounded corners */
                overflow: hidden; /* Clip children if overflow */
                background-color: #ffffff; /* White background for input */
            }

            .sr-input {
                border: none;
                padding: 10px 10px;
                font-size: 14px;
                flex-grow: 1;
            }


            .sr-btn {
                background-color: orangered; /* Primary blue button */
                color: white; /* White text color */
                border-radius: 0px; /* Remove default button border */
                padding: 8px 10px; /* Padding inside the button */
                cursor: pointer; /* Pointer cursor on hover */
            }

            .sr-btn i {
                font-size: 18px; /* Larger icon size */
            }

            .sr-btn:hover {
                background-color: orange ; /* Darker blue on hover */
            }

            .sr-input:focus {
                outline: none; /* Remove default focus outline */
                box-shadow: none; /* Remove default box shadow */
            }

            .delete-toggle {
                appearance: none;
                -webkit-appearance: none;
                width: 50px;
                height: 25px;
                background-color: #4CAF50;
                border-radius: 25px;
                position: relative;
                cursor: pointer;
                outline: none;
                transition: background-color 0.2s;
            }

            .delete-toggle:checked {
                background-color: #f44336;
            }

            .delete-toggle:before {
                content: '';
                position: absolute;
                width: 21px;
                height: 21px;
                background-color: #ffffff;
                border-radius: 50%;
                top: 2px;
                left: 27px;
                transition: transform 0.2s, left 0.2s;
            }

            .delete-toggle:checked:before {
                left: 2px;
            }
            .form-container {
                max-width: 100%;
                margin: auto auto;
                padding: 20px;
                border: 1px solid #ccc;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            .password-wrapper {
                position: relative;
            }
            .password-wrapper input {
                padding-right: 40px;
            }
            .password-wrapper .toggle-password {
                position: absolute;
                margin-top: 15px;
                top: 50%;
                right: 10px;
                transform: translateY(-50%);
                cursor: pointer;
            }
        </style>

        <%
            HttpSession sess = request.getSession();
            AccountLoginDAO ald = new AccountLoginDAO();
            UserDAO userDao = new UserDAO();
            GoogleLoginDAO gld = new GoogleLoginDAO();
            OrderDAO o = new OrderDAO();
        
            User user = null;
            AccountLogin account = null;
            GoogleLogin gglogin = null;
            if(sess.getAttribute("account") != null){
                account = (AccountLogin) sess.getAttribute("account");
                user = (User) userDao.getUserById(account.getUser().getID());
            }else if(sess.getAttribute("gguser") != null){
                gglogin = (GoogleLogin) sess.getAttribute("gguser");
                user = (User) userDao.getUserById(gglogin.getUser().getID());
            }else{
                user = null;
                account = null;
            }
             
            String balance = null;
            if(user != null){
                DecimalFormat df = new DecimalFormat("#,###");
                df.setMaximumFractionDigits(0);
                balance = df.format(user.getBalance());
            }
            //List<Order> data = o.getListOrderByUserID(account.getUser().getID());
            List<Order> data = (List<Order>) request.getAttribute("data");
            List<OrderDetails> dataa = (List<OrderDetails>) request.getAttribute("list");
            List<AccountLogin> dataUser = (List<AccountLogin>) request.getAttribute("allAcc");
        %>
        <%
    String msg = (String) request.getAttribute("msg");
    if (msg != null && !msg.isEmpty()) { 
        %>
        <script type="text/javascript">
            alert("<%= msg %>");
        </script>
        <%
            } 
        %>
    </head>
    <body class="dashboard dashboard_1">
        <div class="full_container">
            <div class="inner_container">
                <!-- Sidebar  -->
                <nav id="sidebar">
                    <div class="sidebar_blog_1">
                        <div class="sidebar-header">
                            <div class="logo_section">
                                <a href="index.html"><img class="logo_icon img-responsive" src="images/logo/logo_icon.png" alt="#" /></a>
                            </div>
                        </div>
                        <div class="sidebar_user_info">
                            <div class="icon_setting"></div>
                            <div class="user_profle_side">
                                <%if(gglogin == null && account == null){%>
                                <div class="user_img"><img class="img-responsive" src="images/logo/logo_icon.png" alt="#" /></div>
                                <div class="user_info">
                                    <h6>The Card Shop</h6>
                                    <p><span class="online_animation"></span></p>
                                </div>
                                <%}else if(user.getFirstName() == null && user.getLastName() != null ){%>
                                <div class="user_img"><img class="img-responsive" src="images/logo/logo_icon.png" alt="#" /></div>
                                <div class="user_info">
                                    <h6><%=user.getLastName()%></h6>
                                    <p><span class="online_animation"></span></p>
                                </div>
                                <%}else if(user.getLastName() == null  && user.getFirstName() != null){%>
                                <div class="user_img"><img class="img-responsive" src="images/layout_img/userfeedback.jpg" alt="#" /></div>
                                <div class="user_info">
                                    <h6><%=user.getFirstName()%></h6>
                                    <p><span class="online_animation"></span></p>
                                </div>
                                <%}else if(user.getLastName() != null && user.getFirstName() != null){%>
                                <div class="user_img"><img class="img-responsive" src="images/layout_img/userfeedback.jpg" alt="#" /></div>
                                <div class="user_info">
                                    <h6><%=user.getLastName()%> <%=user.getFirstName()%></h6>
                                    <p><span class="online_animation"></span></p>
                                </div>
                                <%}else{%>
                                <div class="user_img"><img class="img-responsive" src="images/layout_img/userfeedback.jpg" alt="#" /></div>
                                <div class="user_info">
                                    <h6>Xin chào!</h6>
                                    <p><span class="online_animation"></span></p>
                                </div>
                                <%}%>

                            </div>
                        </div>
                    </div>
                    <div class="sidebar_blog_2">
                        <h4>Quản lý đơn đăng kí</h4>
                        <ul class="list-unstyled components">
                            <li><a href="showstatistic"><i class="fa fa-edit blue1_color"></i> <span>Quản lý thống kê</span></a></li>
                            <li><a href="displaybrand"><i class="fa fa-edit yellow_color"></i> <span>Quản lý sản phẩm</span></a></li>
                            <li><a href="manageruseracc"><i class="fa fa-edit orange_color"></i> <span>Quản lý người dùng</span></a></li>
                            <li><a href="manageallmember"><i class="fa fa-edit orange_color"></i> <span>Quản lý thành viên</span></a></li> 
                            <li><a href="displayorderlist"><i class="fa fa-edit purple_color"></i> <span>Quản lý đơn hàng</span></a></li>
                            <li><a href="managecategorynews"><i class="fa fa-edit green_color"></i> <span>Quản lý tin tức</span></a></li> 
                            <li><a href="managevoucher"><i class="fa fa-edit green_color"></i> <span>Quản lý voucher</span></a></li> 
                            <li><a href="usergetnotification"><i class="fa fa-edit orange_color"></i> <span>Đăng kí nhận thông báo</span></a></li>
                            <li><a href="commentnews"><i class="fa fa-edit orange_color"></i> <span>Bình luận tin tức</span></a></li>
                            <li><a href="loadreport"><i class="fa fa-edit red_color"></i> <span>Quản lý báo cáo</span></a></li>
                            <li><a href="displayfeedback"><i class="fa fa-edit green_color"></i> <span>Quản lý phản hồi</span></a></li> 
                            <li><a href="withdrawal-admin"><i class="fa fa-edit green_color"></i> <span>Quản lý đơn rút tiền</span></a></li> 
                            <li><a href="displayagreementpolicy"><i class="fa fa-edit green_color"></i> <span>Quản lý chính sách</span></a></li>
                        </ul>
                    </div>
                </nav>
                <!-- end sidebar -->
                <!-- right content -->
                <div id="content">
                    <!-- topbar -->
                    <div class="topbar">
                        <nav class="navbar navbar-expand-lg navbar-light">
                            <div class="full">
                                <button type="button" id="sidebarCollapse" class="sidebar_toggle"><i class="fa fa-bars"></i></button>
                                <div class="logo_section">

                                    <%if(account == null && gglogin == null){%>
                                    <%} else {%>
                                    <!--<a href="home.jsp"><img class="img-responsive" src="images/logo/logo.png" alt="Logo" /></a>-->
                                    <%}%>

                                </div>
                                <div class="right_topbar">
                                    <div class="icon_info">
                                        <ul>
                                            <%if(account == null && gglogin == null){%>
                                            <%}else{%>
                                            </br>
                                            <i class="fa fa-credit-card"></i>
                                            <strong>Số dư: </strong><%=balance%> VNĐ
                                            <span class="badge"></span>
                                            <%}%>

                                        </ul>
                                        <ul class="user_profile_dd">
                                            <li>
                                                <%if(account == null && gglogin == null){%>
                                                <a class="dropdown-toggle" data-toggle="dropdown"><img class="img-responsive rounded-circle" src="images/layout_img/userfeedback.jpg" alt="#" /><span class="name_user"> Tài khoản  </span></a>
                                                <div class="dropdown-menu">
                                                    <a class="dropdown-item" href="login.jsp">Đăng nhập</a>
                                                    <a class="dropdown-item" href="register.jsp">Đăng kí</a>
                                                </div>
                                                <% } else { %>
                                                <a class="dropdown-toggle" data-toggle="dropdown"><img class="img-responsive rounded-circle" src="images/layout_img/userfeedback.jpg" alt="#" /><span class="name_user">Tài khoản</span></a>
                                                <div class="dropdown-menu">
                                                    <a class="dropdown-item" href="userprofile.jsp">Thông tin cá nhân</a>
                                                    <% if(user.getRole().getID() == 1) { %>
                                                    <a class="dropdown-item" href="displaybrand">Tới trang admin</a>
                                                    <%}%>
                                                    <a class="dropdown-item" href="logoutservlet">Đăng xuất <i class="fa fa-sign-out"></i></a> 
                                                </div>
                                                <% } %>

                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </nav>
                    </div>
                    <!-- end topbar -->
                    <!-- dashboard inner -->
                    <div class="midde_cont">
                        <div class="container-fluid">
                            <div class="row column_title">
                                <div class="col-md-12">
                                    <div class="page_title" style="padding: 10px;">
                                        <marquee behavior="scroll" direction="left">
                                            <h6 style="color: #FF5722;">Website thực hành SWP391 của Nhóm 2 | SE1801 | FPT <a href="https://www.facebook.com/Anhphanhne" target="_blank" style="letter-spacing: 2px;"> | <i class="fa fa-facebook-square"></i></a>
                                                Bài tập đang trong quá trình hoàn thành và phát triển!</h6>
                                        </marquee>
                                    </div>
                                </div>
                            </div>
                            <!-- graph -->

                            <!--Show loại thẻ-->
                            <div class="row column1">
                                <div class="col-md-12">
                                    <div class="white_shd full margin_bottom_30">
                                        <div class="full graph_head">
                                            <div class="heading1 margin_0 header-container">
                                                <h2>Danh sách đăng ký thành viên</h2>
                                                <a href="accessmember" style="margin-left: 400px; background-color: #FF5722; border-color: #FF5722" class="btn btn-primary">Xem danh sách đăng ký</a>
                                            </div>
                                        </div>
                                        <div style="padding: 0px 20px 20px 20px" class="full price_table padding_infor_info">
                                            <table class="table table-striped" style="margin-bottom: 15px">
                                                <thead>
                                                    <tr>
                                                        <th style="padding: 10px">ID</th>
                                                        <th style="padding: 10px">Tên tài khoản</th>
                                                        <th style="padding: 10px">Ngày khỏi tạo</th>
                                                        <th style="padding: 10px">Xem đơn đăng ký</th>
                                                        <th style="padding: 10px">Thao tác khác</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:choose>
                                                        <c:when test="${not empty members}">
                                                            <c:forEach var="member" items="${members}">
                                                                <tr>
                                                                    <td style="padding: 10px">${member.getID()}</td>
                                                                    <td style="padding: 10px">${member.getLastName()} ${member.getFirstName()}</td>
                                                                    <td style="padding: 10px">${member.getCreatedAt()}</td>
                                                                    <td style="padding: 10px">
                                                                        <form style="max-width: 200px" action="accessmember" method="post">
                                                                            <input type="hidden" name="mId" value="${member.getID()}">
                                                                            <button class="btn btn-info" type="submit">Xem lại đơn</button>
                                                                        </form>
                                                                    </td>
                                                                    <td style="padding: 10px">
                                                                        <form style="max-width: 200px" action="manageallmember" method="post">
                                                                            <input type="hidden" name="mId" value="${member.getID()}">
                                                                            <button class="btn btn-info" type="submit">Xóa thành viên</button>
                                                                        </form>
                                                                    </td>
                                                                </tr>

                                                            </c:forEach>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <tr>
                                                                <td colspan="5" style="padding: 10px; text-align: center;">Không có thành viên nào</td>
                                                            </tr>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </tbody>
                                            </table>
                                        </div>

                                    </div>
                                </div>
                                <div class="container-fluid">
                                    <div class="footer">
                                        <p>Copyright © Bài tập thực hành nhóm của sinh viên đại học FPT Hà Nội<br><br>
                                            TEAM LEADER <a href=""></a> <i class="fa fa-envelope-o"></i> : DungPAHE173131@fpt.edu.vn
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <!-- end dashboard inner -->
                        </div>
                    </div>
                </div>

                <!-- Phần còn lại của nội dung -->
            </div> <!-- Kết thúc thẻ content -->

            <!-- Modal -->
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
            <!-- jQuery -->
            <!--<script src="js/jquery.min.js"></script>-->
            <script src="js/popper.min.js"></script>
            <!--<script src="js/bootstrap.min.js"></script>-->
            <!-- wow animation -->
            <script src="js/animate.js"></script>
            <!-- select country -->
            <script src="js/bootstrap-select.js"></script>
            <!-- owl carousel -->
            <script src="js/owl.carousel.js"></script> 
            <!-- chart js -->
            <script src="js/Chart.min.js"></script>
            <script src="js/Chart.bundle.min.js"></script>
            <script src="js/utils.js"></script>
            <script src="js/analyser.js"></script>
            <!-- nice scrollbar -->
            <script src="js/perfect-scrollbar.min.js"></script>
            <script>
            var ps = new PerfectScrollbar('#sidebar');
            </script>
            <!-- custom js -->
            <script src="js/chart_custom_style1.js"></script>
            <script src="js/custom.js"></script>
            <script>
            $(document).ready(function () {
                var msg = "<%= (request.getAttribute("msgCustom") != null) ? request.getAttribute("msgCustom") : "" %>";
                if (msg != "") {
                    alert("Thông báo: " + msg);
                }
            });

            </script>
    </body>
</html>

