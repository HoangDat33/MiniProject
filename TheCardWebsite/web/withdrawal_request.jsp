<%-- 
    Document   : userprofile
    Created on : May 20, 2024, 4:36:16 PM
    Author     : Bravo 15
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import = "java.text.DecimalFormat" %>
<%@page import = "Model.*" %>
<%@page import = "DAL.*" %>
<%@page import = "java.util.*" %>
<%@page session="true" %>
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
        <title>The Card Shop - Rút tiền</title>
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
        <!-- calendar file css -->
        <link rel="stylesheet" href="js/semantic.min.css" />
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
        <link rel="stylesheet" href="https://cdn.datatables.net/1.11.5/css/dataTables.bootstrap4.min.css">
        <style>
            .container {
                display: flex;
                flex-direction: column;
            }
            .styled-inputimg {
                width: 100px;
                height: 35px;
                border-radius: 25px;
            }

            .styled-input {
                width: 100px;
                height: 35px;
                padding: 10px 15px;
                font-size: 16px;
                border: 2px solid #ccc;
                border-radius: 25px;
                outline: none;
                transition: all 0.3s ease;
                margin-left: 5px;
                margin-top: 5px;
            }

            .styled-input:focus {
                border-color: #007BFF;
                box-shadow: 0 0 10px rgba(0, 123, 255, 0.5);
            }

            .styled-input::placeholder {
                color: #aaa;
            }
        </style>
        <%
            HttpSession sess = request.getSession();
            AccountLoginDAO ald = new AccountLoginDAO();
            GoogleLoginDAO gld = new GoogleLoginDAO();
            UserDAO userDao = new UserDAO();
            
            User user = null;
            AccountLogin account = null;
            GoogleLogin gglogin = null;
            
            //Test - lúc merge thì xóa đi để chạy bên trên
            
            //account = (AccountLogin) sess.getAttribute("account");
            //user = (User) userDao.getUserById(account.getUser().getID());
              
            if(request.getAttribute("user") != null ){
                user = (User) request.getAttribute("user");
                account = (AccountLogin) request.getAttribute("account");
            }else if(sess.getAttribute("account") != null){
                account = (AccountLogin) sess.getAttribute("account");
                user = (User) userDao.getUserById(account.getUser().getID());
            }else if(sess.getAttribute("gguser") != null){
                gglogin = (GoogleLogin) sess.getAttribute("gguser");
                user = (User) userDao.getUserById(gglogin.getUser().getID());
            }else{
                user = null ; //(User) request.getAttribute("user");
                account = null; //(AccountLogin) request.getAttribute("account");
            } 
            
            
            
            //Format hiển thị số tiền
            //String balance = userDao.formatMoney(user.getBalance());
            String balance = null;
            if(user != null){
                DecimalFormat df = new DecimalFormat("#,###");
                df.setMaximumFractionDigits(0);
                balance = df.format(user.getBalance());
            }
        %>
    </head>
    <body class="inner_page profile_page">
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
                        <h4>Thông tin cá nhân</h4> 
                        <ul class="list-unstyled components">
                            <li><a href="home"><i class="fa fa-home yellow_color"></i> <span>Trang chủ</span></a></li>
                            <li class="active">
                                <a href="#dashboard" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-shopping-cart orange_color"></i> <span>Mua hàng</span></a>
                                <ul class="collapse list-unstyled" id="dashboard">
                                    <li>
                                        <a href="shop"><i class="fa fa-arrow-circle-right"></i> <span>Sản phẩm</span></a>
                                    </li>
                                    <%if(account != null || gglogin != null){%>
                                    <li>
                                        <a href="cart"><i class="fa fa-arrow-circle-right"></i> <span>Giỏ hàng</span></a>
                                    </li>
                                    <li>
                                        <a href="order"><i class="fa fa-arrow-circle-right"></i> <span>Đơn hàng</span></a>
                                    </li>
                                    <li>
                                        <a href="showfavoproduct"><i class="fa fa-arrow-circle-right"></i> <span>Sản phẩm yêu thích</span></a>
                                    </li>
                                    <%}%>
                                </ul>
                            </li>
                            <li class="active">
                                <a href="#additional_page" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-credit-card green_color"></i> <span>Quản lí thanh toán</span></a>
                                <ul class="collapse list-unstyled" id="additional_page">
                                    <%if(account != null || gglogin != null){%>
                                    <li>
                                        <a href="depositmoney"><i class="fa fa-arrow-circle-right"></i> <span>Nạp tiền</span></a>
                                    </li>
                                    <li>
                                        <a href="withdrawal-request?action=request"><i class="fa fa-arrow-circle-right"></i> <span>Rút tiền</span></a>
                                    </li>
                                    <li>
                                        <a href="withdrawal-request"> <i class="fa fa-arrow-circle-right"></i><span>Lịch sử rút tiền</span></a>
                                    </li>
                                    <li>
                                        <a href="displayDeposit"> <i class="fa fa-arrow-circle-right"></i><span>Lịch sử giao dịch</span></a>
                                    </li>
                                    <%} else{%>
                                    <li>
                                        <a href=""> <i class="fa fa-arrow-circle-right"></i><span>Vui lòng đăng nhập!</span></a>
                                    </li>
                                    <%}%>
                                </ul>
                            </li>
                            <li><a href="news"><i class="fa fa-newspaper-o red_color"></i><span>Theo dõi tin tức</span></a></li>
                            <li><a href="voucher"><i class="fa fa-ticket yellow_color"></i> <span>Săn mã giảm giá</span></a></li>
                            <li><a href="registermember"><i class="fa fa-newspaper-o red_color"></i> <span>Đăng kí thành viên</span></a> </li>

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
                                    <!--<a href="home.jsp"><img class="img-responsive" src="images/logo/logo.png" alt="Logo" /></a>-->
                                </div>
                                <div class="right_topbar">
                                    <div class="icon_info">
                                        <ul>
                                            </br>
                                            <i class="fa fa-credit-card"></i>
                                            <strong>Số dư: </strong> <%=balance%> VNĐ
                                            <span class="badge"></span>
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
                                                    <% if(user.getRole().getID() == 1) { %>
                                                    <a class="dropdown-item" href="showstatistic">Tới trang admin</a>
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

                    <!-- dashboard inner / Div bên trái-->
                    <div class="midde_cont">
                        <div class="container-fluid">

                            <div class="mt-4 container-fluid"> 
                                <div class="animated fadeIn">
                                    <div class="row">
                                        <div class="col-12 col-sm-12 col-md-12">
                                            <div class="card">
                                                <div class="card-body">
                                                    <div class="row">
                                                        <div class="col-md-12">
                                                            <div class="card-group">
                                                                <div class="card">
                                                                    <div class="card-header">
                                                                        <h3 class="mb-0">Lịch sử rút tiền</h3>
                                                                    </div>
                                                                    <c:if test="${errorMessage != null}">
                                                                        <div class="alert alert-danger mt-4">
                                                                            ${errorMessage}
                                                                        </div>
                                                                    </c:if>
                                                                    <c:if test="${successMessage != null}">
                                                                        <div class="alert alert-success mt-4">
                                                                            ${successMessage}
                                                                        </div>
                                                                    </c:if>
                                                                    <div class="card-body">
                                                                        <div class="table-responsive">
                                                                            <table class="table table-bordered table-striped" id="withdrawalRequestsTable">
                                                                                <thead>
                                                                                    <tr>
                                                                                        <th>STT</th>
                                                                                        <th>STK</th>
                                                                                        <th>Ngân hàng</th>
                                                                                        <th>Số tiền</th>
                                                                                        <th>Nội dung</th>
                                                                                        <th>Trạng thái</th>
                                                                                        <th>Tạo lúc</th>
                                                                                        <th>Cập nhật lúc</th>
                                                                                    </tr>
                                                                                </thead>
                                                                                <tbody>
                                                                                    <c:forEach var="request" items="${requests}" varStatus="index">
                                                                                        <tr>
                                                                                            <td><c:out value="${index.index + 1}" /></td>
                                                                                            <td><c:out value="${request.accountNumber}" /></td>
                                                                                            <td><c:out value="${request.bankName}" /></td>
                                                                                            <td><c:out value="${request.amount}" /></td>
                                                                                            <td><c:out value="${request.transactionDetails}" /></td>
                                                                                            <td>
                                                                                                <span class="badge badge-info">
                                                                                                    <c:out value="${request.status}" />
                                                                                                    <span>
                                                                                                        </td>
                                                                                                        <td><c:out value="${request.createdAt}" /></td>
                                                                                                        <td><c:out value="${request.updatedAt}" /></td>
                                                                                                        </tr>
                                                                                                    </c:forEach>
                                                                                                    </tbody>
                                                                                                    </table>
                                                                                                    </div>
                                                                                                    </div>
                                                                                                    </div>
                                                                                                    </div>
                                                                                                    </div>
                                                                                                    </div>
                                                                                                    </div>
                                                                                                    </div>
                                                                                                    </div>
                                                                                                    </div>
                                                                                                    </div>
                                                                                                    </div>                                    


                                                                                                    <div class="row column1"></div>
                                                                                                    <!-- end row -->
                                                                                                    <c:choose>
                                                                                                        <c:when test="${empty requestScope.thongbao && empty requestScope.error}">

                                                                                                        </c:when>
                                                                                                        <c:when test="${not empty requestScope.error}">
                                                                                                            <div class="row column_title" style="margin-top: 30px">
                                                                                                                <div class="col-md-12">
                                                                                                                    <div class="page_title" style="text-align: center;">
                                                                                                                        <H2 style="color: orangered; font-family:sans-serif;">${requestScope.error}<H2>
                                                                                                                                </div>
                                                                                                                                </div>
                                                                                                                                </div>
                                                                                                                            </c:when>
                                                                                                                            <c:otherwise>
                                                                                                                                <div class="row column_title" style="margin-top: 30px">
                                                                                                                                    <div class="col-md-12">
                                                                                                                                        <div class="page_title" style="text-align: center;">
                                                                                                                                            <H2 style="color: #28a745; font-family:sans-serif;">${requestScope.thongbao}<H2>
                                                                                                                                                    </div>
                                                                                                                                                    </div>
                                                                                                                                                    </div>
                                                                                                                                                </c:otherwise>
                                                                                                                                            </c:choose>

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
                                                                                                                                            </div>
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
                                                                                                                                            <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
                                                                                                                                            <!-- Bootstrap JS -->
                                                                                                                                            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
                                                                                                                                            <!-- DataTables JS -->
                                                                                                                                            <script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
                                                                                                                                            <script src="https://cdn.datatables.net/1.11.5/js/dataTables.bootstrap4.min.js"></script>
                                                                                                                                            <script src="js/datatable.js"></script>
                                                                                                                                            <script>
                                                                                                                                                                                                        $(document).ready(function () {
                                                                                                                                                                                                        $('#withdrawalRequestsTable').DataTable({
                                                                                                                                                                                                        language: datatable
                                                                                                                                                                                                        });
                                                                                                                                                                                                        });
                                                                                                                                            </script>
                                                                                                                                            <!-- custom js -->
                                                                                                                                            <script src="js/custom.js"></script>
                                                                                                                                            <!-- calendar file css -->    
                                                                                                                                            <script src="js/semantic.min.js"></script>
                                                                                                                                            </body>
                                                                                                                                            </html>
