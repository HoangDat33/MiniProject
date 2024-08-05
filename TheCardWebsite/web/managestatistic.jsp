<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import = "java.text.DecimalFormat" %>
<%@page import = "Model.*" %>
<%@page import = "DAL.*" %>
<%@page import = "java.util.*" %>   
<%@page session="true" %>
<%@ page import="Model.Order" %>
<%@ page import="Model.OrderDetails" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- basic -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- mobile metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="initial-scale=1, maximum-scale=1">
        <!-- site metas -->
        <title>The Card Shop - Quản lý thống kê</title>
        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta name="author" content="">
        <!-- site icon -->
        <link rel="icon" href="images/fevicon.png" type="image/png" />
        <!-- bootstrap css -->
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <!-- site css -->
        <link rel="stylesheet" href="style.css" />
        <!-- responsive css -->
        <link rel="stylesheet" href="css/responsive.css" />
        <!-- color css -->
        <link rel="stylesheet" href="css/color.css" />
        <!-- select bootstrap -->
        <link rel="stylesheet" href="css/bootstrap-select.css" />
        <!-- scrollbar css -->
        <link rel="stylesheet" href="css/perfect-scrollbar.css" />
        <!-- custom css -->
        <link rel="stylesheet" href="css/custom.css" />
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    </head>
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
            List<Order> data = (List<Order>) request.getAttribute("data");
            List<OrderDetails> dataa = (List<OrderDetails>) request.getAttribute("list");
    %>
    <body class="dashboard dashboard_2">
        <div class="full_container">
            <div class="inner_container">
                <!-- Sidebar  -->
                <nav id="sidebar">
                    <div class="sidebar_blog_1">
                        <div class="sidebar-header">
                            <div class="logo_section">
                                <a href="home.jsp"><img class="logo_icon img-responsive" src="images/logo/logo_icon.png" alt="#" /></a>
                            </div>
                        </div>
                        <div class="sidebar_user_info">
                            <div class="icon_setting"></div>
                            <div class="user_profle_side">
                                <% if(gglogin == null && account == null) { %>
                                <div class="user_img"><img class="img-responsive" src="images/logo/logo_icon.png" alt="#" /></div>
                                <div class="user_info">
                                    <h6>The Card Shop</h6>
                                    <p><span class="online_animation"></span></p>
                                </div>
                                <% } else if(user.getFirstName() == null && user.getLastName() != null) { %>
                                <div class="user_img"><img class="img-responsive" src="images/logo/logo_icon.png" alt="#" /></div>
                                <div class="user_info">
                                    <h6><%=user.getLastName()%></h6>
                                    <p><span class="online_animation"></span></p>
                                </div>
                                <% } else if(user.getLastName() == null && user.getFirstName() != null) { %>
                                <div class="user_img"><img class="img-responsive" src="images/layout_img/userfeedback.jpg" alt="#" /></div>
                                <div class="user_info">
                                    <h6><%=user.getFirstName()%></h6>
                                    <p><span class="online_animation"></span></p>
                                </div>
                                <% } else if(user.getLastName() != null && user.getFirstName() != null) { %>
                                <div class="user_img"><img class="img-responsive" src="images/layout_img/userfeedback.jpg" alt="#" /></div>
                                <div class="user_info">
                                    <h6><%=user.getLastName()%> <%=user.getFirstName()%></h6>
                                    <p><span class="online_animation"></span></p>
                                </div>
                                <% } else { %>
                                <div class="user_img"><img class="img-responsive" src="images/layout_img/userfeedback.jpg" alt="#" /></div>
                                <div class="user_info">
                                    <h6>Xin chào!</h6>
                                    <p><span class="online_animation"></span></p>
                                </div>
                                <% } %>
                            </div>
                        </div>
                    </div>
                    <div class="sidebar_blog_2">
                        <h4>Quản lý thống kê</h4>
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
                                    <% if(account == null && gglogin == null) { %>
                                    <% } else { %>
                                    <!--<a href="home.jsp"><img class="img-responsive" src="images/logo/logo.png" alt="Logo" /></a>-->
                                    <% } %>
                                </div>
                                <div class="right_topbar">
                                    <div class="icon_info">
                                        <ul>
                                            <% if(account == null && gglogin == null) { %>
                                            <% } else { %>
                                            <br>
                                            <i class="fa fa-credit-card"></i>
                                            <strong>Số dư: </strong><%=balance%> VNĐ
                                            <span class="badge"></span>
                                            <% } %>
                                        </ul>
                                        <ul class="user_profile_dd">
                                            <li style="padding-left: 30px;">
                                                <% if(account == null && gglogin == null) { %>
                                                <a class="dropdown-toggle" data-toggle="dropdown"><img class="img-responsive rounded-circle" src="images/layout_img/userfeedback.jpg" alt="#" /><span class="name_user"> Tài khoản </span></a>
                                                <div class="dropdown-menu">
                                                    <a class="dropdown-item" href="login.jsp">Đăng nhập</a>
                                                    <a class="dropdown-item" href="register.jsp">Đăng kí</a>
                                                </div>
                                                <% } else { %>
                                                <a class="dropdown-toggle" data-toggle="dropdown"><img class="img-responsive rounded-circle" src="images/layout_img/userfeedback.jpg" alt="#" /><span class="name_user">Tài khoản</span></a>
                                                <div class="dropdown-menu">
                                                    <a class="dropdown-item" href="userprofile.jsp">Thông tin cá nhân</a>
                                                    <a class="dropdown-item" href="home">Tới trang bán hàng</a>
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
                                    <div class="page_title">
                                        <marquee behavior="scroll" direction="left">
                                            <h6 style="color: #FF5722;">Website thực hành SWP391 của Nhóm 2 | SE1801 | FPT <a href="https://www.facebook.com/Anhphanhne" target="_blank" style="letter-spacing: 2px;"> | <i class="fa fa-facebook-square"></i></a>
                                                Bài tập đang trong quá trình hoàn thành và phát triển!</h6>
                                        </marquee>
                                    </div>
                                </div>
                            </div>
                            <div class="row column1">
                                <div class="col-md-6 col-lg-3">
                                    <div class="full counter_section margin_bottom_30 yellow_bg">
                                        <div class="couter_icon">
                                            <div> 
                                                <i class="fa fa-user"></i>
                                            </div>
                                        </div>
                                        <div class="counter_no">
                                            <div>
                                                <p style="color: white" class="total_no">${countuser}</p>
                                                <p style="color: white" class="head_couter">Người dùng</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6 col-lg-3">
                                    <div class="full counter_section margin_bottom_30 blue1_bg">
                                        <div class="couter_icon">
                                            <div> 
                                                <i class="fa fa-briefcase"></i>
                                            </div>
                                        </div>
                                        <div class="counter_no">
                                            <div>
                                                <p style="color: white" class="total_no">${countbrand}</p>
                                                <p style="color: white" class="head_couter">Thương hiệu</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6 col-lg-3">
                                    <div class="full counter_section margin_bottom_30 green_bg">
                                        <div class="couter_icon">
                                            <div> 
                                                <i class="fa fa-newspaper-o"></i>
                                            </div>
                                        </div>
                                        <div class="counter_no">
                                            <div>
                                                <p style="color: white" class="total_no">${countcate}</p>
                                                <p style="color: white" class="head_couter">Loại thẻ</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6 col-lg-3">
                                    <div class="full counter_section margin_bottom_30 red_bg">
                                        <div class="couter_icon">
                                            <div> 
                                                <i class="fa fa-tasks"></i>
                                            </div>
                                        </div>
                                        <div class="counter_no">
                                            <div>
                                                <p style="color: white" class="total_no">${countcard}</p>
                                                <p style="color: white" class="head_couter">Tổng thẻ</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div style="display: flex; justify-content: center; align-items: center" class="row column1 social_media_section">
                                <div class="col-md-6 col-lg-5">
                                    <div class="full socile_icons tw margin_bottom_30">
                                        <div class="social_icon">
                                            <i class="fa fa-ticket"></i>
                                        </div>
                                        <div class="social_cont">
                                            <ul>
                                                <li>
                                                    <span><strong>${countexit}</strong></span>
                                                    <span>Thẻ chưa bán</span>
                                                </li>
                                                <li>
                                                    <span><strong>${countcdelete}</strong></span>
                                                    <span>Thẻ đã bán</span>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6 col-lg-6">
                                    <div class="full socile_icons linked margin_bottom_30">
                                        <div class="social_icon">
                                            <i class="fa fa-bullhorn"></i>
                                        </div>
                                        <div class="social_cont">
                                            <ul>
                                                <li>
                                                    <span><strong>${countfeedback}</strong></span>
                                                    <span>Đánh giá sản phẩm</span>
                                                </li>
                                                <li>
                                                    <span><strong>${countreport}</strong></span>
                                                    <span>Báo cáo sản phẩm</span>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!--revenue graph -->
                            <div class="row column2 graph margin_bottom_30">
                                <div class="col-md-l2 col-lg-12">
                                    <div class="white_shd full">
                                        <div class="full graph_head">
                                            <div class="heading1 margin_0" style="display: flex; align-items: center;">
                                                <%
                                                    int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
                                                %>
                                                <h2 style="margin-right: 7px;">Doanh thu năm</h2>
                                                <select id="yearSelect">
                                                    <c:forEach var="year" begin="2024" end="<%= currentYear %>">
                                                        <option value="${year}" <c:if test="${year == currentYear}">selected</c:if>>${year}</option>
                                                    </c:forEach>
                                                </select>
                                                <h2 style="margin-left: 2px;">: <fmt:formatNumber pattern="#,###" value="${revenue_annual}"/> VND</h2>
                                            </div>
                                        </div>
                                        <div class="full graph_revenue">
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="content">
                                                        <div class="area_chart">
                                                            <div class="row">
                                                                <div class="col-md-12">
                                                                    <canvas id="chart-1"></canvas>
                                                                </div>
                                                            </div>
                                                            <h6 style="text-align: center; margin-top: 30px; color: rgba(0, 119, 181);">Bảng thống kê doanh thu theo năm trên hệ thống</h6>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- end graph -->

                            <!-- number of user monthly graph -->
                            <div class="row column2 graph margin_bottom_30">
                                <div class="col-md-l2 col-lg-12">
                                    <div class="white_shd full">
                                        <div class="full graph_head">
                                            <div class="heading1 margin_0" style="display: flex; align-items: center;">
                                                <h2 style="margin-right: 7px;">Số người dùng mới năm</h2>
                                                <select id="selectedYear">
                                                    <c:forEach var="year" begin="2024" end="<%= currentYear %>">
                                                        <option value="${year}" <c:if test="${year == currentYear}">selected</c:if>>${year}</option>
                                                    </c:forEach>
                                                </select>
                                                <h2 style="margin-left: 2px;">: ${user_annual}</h2>
                                            </div>
                                        </div>
                                        <div class="full graph_revenue">
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="content">
                                                        <div class="area_chart">
                                                            <div class="row">
                                                                <div class="col-md-12">
                                                                    <canvas id="chart-2"></canvas>
                                                                </div>
                                                            </div>
                                                            <h6 style="text-align: center; margin-top: 30px; color: rgba(0, 119, 181);">Bảng thống kê người dùng trên hệ thống</h6>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                           
                                    </div>
                                </div>
                            </div>
                            <!-- end graph -->
                        </div>
                        <!-- footer -->
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
        <script>
            async function fetchRevenueData() {
                const selectedYear = document.getElementById('yearSelect').value;
                const response = await fetch('showrevenue?year=' + selectedYear);
                const data = await response.json();
                return data;
            }

            async function renderRevenueChart() {
                const revenueData = await fetchRevenueData();
                const months = ['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4', 'Tháng 5', 'Tháng 6', 'Tháng 7', 'Tháng 8', 'Tháng 9', 'Tháng 10', 'Tháng 11', 'Tháng 12'];
                const revenues = revenueData.map(item => item.revenue);

                // Get the context of the canvas element we want to select
                var ctx = document.getElementById('chart-1').getContext('2d');

                // Create the chart
                var myChart = new Chart(ctx, {
                    type: 'bar', // or 'bar', 'pie', etc.
                    data: {
                        labels: months,
                        datasets: [{
                                label: 'Doanh thu',
                                data: revenues,
                                backgroundColor: 'rgba(0, 119, 181, 0.2)',
                                borderColor: 'rgba(0, 119, 181, 1)',
                                borderWidth: 1
                            }]
                    },
                    options: {
                        responsive: true,
                        maintainAspectRatio: false,
                        scales: {
                            y: {
                                beginAtZero: true
                            }
                        }
                    }
                });
            }

            renderRevenueChart();

            // User chart
            async function fetchUserData() {
                const selectedyear = document.getElementById('selectedYear').value;
                const response = await fetch('showuser?year=' + selectedyear);
                const data = await response.json();
                return data;
            }

            async function renderUserChart() {
                const userData = await fetchUserData();
                const months = ['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4', 'Tháng 5', 'Tháng 6', 'Tháng 7', 'Tháng 8', 'Tháng 9', 'Tháng 10', 'Tháng 11', 'Tháng 12'];
                const users = userData.map(item => item.user);

                // Get the context of the canvas element we want to select
                var ctx = document.getElementById('chart-2').getContext('2d');

                // Create the chart
                var myChart = new Chart(ctx, {
                    type: 'line', // or 'bar', 'pie', etc.
                    data: {
                        labels: months,
                        datasets: [{
                                label: 'Số người dùng mới',
                                data: users,
                                backgroundColor: 'rgba(0, 119, 181, 0.2)',
                                borderColor: 'rgba(0, 119, 181, 1)',
                                borderWidth: 1
                            }]
                    },
                    options: {
                        responsive: true,
                        maintainAspectRatio: false,
                        scales: {
                            y: {
                                beginAtZero: true,
                                ticks: {
                                    callback: function (value) {
                                        if (Number.isInteger(value)) {
                                            return value;
                                        }
                                        return null;
                                    }
                                }
                            }
                        }
                    }
                });
            }

            renderUserChart();
        </script>
        <!-- jQuery -->
        <script src="js/jquery.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <!-- wow animation -->
        <script src="js/animate.js"></script>
        <!-- select country -->
        <script src="js/bootstrap-select.js"></script>
        <!-- owl carousel -->
        <script src="js/owl.carousel.js"></script> 
        <!--         chart js 
                <script src="js/Chart.min.js"></script>
                <script src="js/Chart.bundle.min.js"></script>
                <script src="js/utils.js"></script>
                <script src="js/analyser.js"></script>-->
        <!-- nice scrollbar -->
        <script src="js/perfect-scrollbar.min.js"></script>
        <script>
            var ps = new PerfectScrollbar('#sidebar');
        </script>
        <!-- custom js -->
        <script src="js/custom.js"></script>
        <script src="js/chart_custom_style2.js"></script>
    </body>
</html>