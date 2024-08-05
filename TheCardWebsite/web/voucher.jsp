<%-- 
    Document   : voucher
    Created on : Jul 30, 2024, 4:38:57 PM
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
        <title>The Card Shop - Săn mã giảm giá</title>
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
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        
        
        <![endif]-->
        <style>
            .buttom-left,
            .buttom-right {
                width: 30px;
                height: 30px;
                display: flex;
                align-items: center;
                justify-content: center;
                background-color: rgba(255, 87, 34, 0.5);
                border-radius: 40%;
                color: #fff;
                font-size: 24px;
                transition: background-color 0.3s ease;
                margin: 0px 10px;
            }

            .buttom-left:hover,
            .buttom-right:hover {
                background-color: rgba(255, 87, 34, 0.8);
            }
            /*Slide*/
            #testimonial_slider_1.carousel {
                width: 86%;
                margin: 35px 7% 35px;
            }

            #testimonial_slider_1 .carousel-inner {
                padding: 0;
                text-align: center;
            }

            #testimonial_slider_1.carousel .carousel-item {
                color: #999;
                font-size: 14px;
                text-align: center;
                overflow: hidden;
                min-height: auto;
            }

            #testimonial_slider_1.carousel .carousel-item a {
                color: #eb7245;
            }

            #testimonial_slider_1.carousel .img-box {
                width: 120px;
                height: 120px;
                margin: 0 auto;
                border-radius: 50%;
            }

            #testimonial_slider_1.carousel .img-box img {
                width: 100%;
                height: 100%;
                display: block;
                border-radius: 50%;
            }

            #testimonial_slider_1.carousel .testimonial {
                padding: 30px 0 10px;
                color: rgba(255, 255, 255, .7);
                font-size: 15px;
                line-height: 24px;
            }

            #testimonial_slider_1.carousel .overview {
                text-align: center;
                padding-bottom: 5px;
                font-size: 14px;
                color: #1ed085;
                font-weight: 500;
                line-height: 14px;
            }

            #testimonial_slider_1.carousel .overview b {
                color: #fff;
                font-size: 16px;
                text-transform: none;
                display: block;
                padding-bottom: 5px;
                font-weight: 500;
            }

            #testimonial_slider_1.carousel .star-rating i {
                font-size: 18px;
                color: #ffdc12;
            }

            #testimonial_slider_1.carousel .carousel-control {
                width: 30px;
                height: 30px;
                border-radius: 50%;
                background: #fff;
                text-shadow: none;
                top: 0;
                opacity: 1;
            }

            #testimonial_slider_1.carousel .carousel-control i {
                font-size: 20px;
                margin-right: 2px;
                color: #15283c;
                margin-top: -2px;
            }

            #testimonial_slider_1.carousel .carousel-control-prev {
                left: auto;
                right: 40px;
            }

            #testimonial_slider_1.carousel .carousel-control-next i {
                margin-right: -2px;
                margin-top: -2px;
            }

            #testimonial_slider_1.carousel .carousel-indicators {
                bottom: 15px;
            }

            #testimonial_slider_1.carousel .carousel-indicators li,
            #testimonial_slider_1.carousel .carousel-indicators li.active {
                width: 11px;
                height: 11px;
                margin: 1px 5px;
                border-radius: 50%;
            }

            #testimonial_slider_1.carousel .carousel-indicators li {
                background: #e2e2e2;
                border-color: transparent;
            }

            #testimonial_slider_1.carousel .carousel-indicators li.active {
                border: none;
                background: #888;
            }

            .voucher-section {
                background-color: white;
                padding: 20px;
                margin-bottom: 20px;
            }
            .voucher-header {
                background-color: #ff5722;
                color: white;
                padding: 10px;
                margin-bottom: 15px;
                border-radius: 5px;
            }
            .voucher-card {
                border: 1px solid #ddd;
                border-radius: 5px;
                padding: 15px;
                background-color: #f8f8f8;
            }
            .voucher-image {
                flex: 0 0 100px;
                margin-right: 15px;
            }
            .voucher-info {
                flex: 1;
            }
            .voucher-discount {
                font-weight: bold;
                color: #ff4500;
/*                font-size: 1.2em;*/
            }
            .voucher-condition {
                /*font-size: 0.9em;*/
                color: #666;
                margin-bottom: 10px;
            }
            .voucher-button {
                background-color: #ff4500;
                color: white;
                border: none;
                padding: 5px 15px;
                border-radius: 3px;
                cursor: pointer;
            }
            .voucher-link {
                color: #0066cc;
                text-decoration: none;
                font-size: 0.9em;
            }
            /*Pop up điều kiện*/
            body {
                margin: 0;
                padding: 0;
                background-color: rgba(0, 0, 0, 0.5);
            }
            .popup {
                background-color: #f8f8f8;
                width: 90%;
                max-width: 400px;
                margin: 20px auto;
                padding: 20px;
                border-radius: 8px;
            }
            .header {
                background-color: #ee4d2d;
                color: white;
                padding: 10px;
                margin: -20px -20px 20px -20px;
                border-radius: 8px 8px 0 0;
            }
            .logo {
                background-color: white;
                color: #ee4d2d;
                padding: 5px;
                border-radius: 4px;
                display: inline-block;
                margin-bottom: 20px;
            }
            h2 {
                margin: 0;
                font-size: 18px;
            }
            ul {
                padding-left: 20px;
            }
            .button {
                background-color: #ee4d2d;
                color: white;
                border: none;
                padding: 10px 20px;
                text-align: center;
                text-decoration: none;
                display: block;
                font-size: 16px;
                margin: 20px auto 0;
                cursor: pointer;
                border-radius: 4px;
            }
            /*THÊM MỚI*/
            /* Nút Xem thẻ của tôi */
            .view-cards-button {
                background-color: #007bff; /* Màu nền của nút */
                color: white; /* Màu chữ của nút */
                border: none;
                border-radius: 4px;
                padding: 10px 15px;
                font-size: 16px;
                cursor: pointer;
                text-align: center;
                display: inline-block;
                text-decoration: none;
                margin: 20px; /* Khoảng cách dưới nút */

                transition: background-color 0.3s ease;
            }

            .view-cards-button:hover {
                background-color: #0056b3; /* Màu nền khi hover */
            }

        </style>

        <%
            HttpSession sess = request.getSession();
            
            AccountLoginDAO ald = new AccountLoginDAO();
            UserDAO userDao = new UserDAO();
            GoogleLoginDAO gld = new GoogleLoginDAO();
            
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
            
            BrandDAO brandDao = new BrandDAO();
            List<Brand> dataBrand = brandDao.getListBrand();
            request.setAttribute("dataBrand", dataBrand);
            
            //get voucher;
            List<Voucher> dataVoucher = (List<Voucher>) request.getAttribute("dataVoucher");
            
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
                                    <!--<p><span class="online_animation"></span></p>-->
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
                        <h4>Chi tiết tin tức</h4>
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
                                    <li><a href="showfavoproduct"><i class="fa fa-arrow-circle-right"></i> <span>Sản phẩm yêu thích</span></a></li>
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
                    <!-- dashboard inner -->
                    <div class="midde_cont">
                        <div class="container-fluid">
                            <div class="row column_title">
                                <div class="col-md-12">
                                    <div class="page_title" style="padding: 10px">
                                        <marquee behavior="scroll" direction="left">
                                            <h6 style="color: #FF5722;">Website thực hành SWP391 của Nhóm 2 | SE1801 | FPT <a href="https://www.facebook.com/Anhphanhne" target="_blank" style="letter-spacing: 2px;"> | <i class="fa fa-facebook-square"></i></a>
                                                Bài tập đang trong quá trình hoàn thành và phát triển!</h6>
                                        </marquee>
                                    </div>
                                </div>
                            </div>

                            <!-- graph -->
                            <div class="row column3">
                                <!-- testimonial -->
                                <div class="col-md-12">
                                    <div  style="background-color:  white;" class="dark_bg full margin_bottom_30">
                                        <div class="container">
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <button class="view-cards-button" onclick="viewMyTichket()"><i class="fa fa-ticket yellow_color"></i> Xem mã giảm giá của tôi</button>
                                                </div>
                                            </div>
                                            <div class="container">
                                                <div class="row">
                                                    <%if(dataVoucher != null){%>
                                                    <%for (Voucher v : dataVoucher) {%>
                                                    <div class="col-md-6 voucher-section">
                                                        <div class="voucher-header">
                                                            <h4 class="mb-0" style="color: white;"><%=v.getTitle()%></h4>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-md-12">
                                                                <div class="voucher-card d-flex align-items-center">
                                                                    <div class="voucher-image mr-5">
                                                                        <img src="images/logo/<%=v.getImage()%>" alt="Voucher" style="width: 200px;">
                                                                    </div>
                                                                    <div class="voucher-info">
                                                                        <div class="voucher-discount">Giảm <%=v.getDiscount()%> %</div>
                                                                        <div class="voucher-condition">Giảm tối đa <%=v.getDiscountMax()%> VNĐ | Đơn Tối Thiểu <%=v.getPricefrom()%> | Số lượng: <%=v.getQuantity()%></div>
                                                                        <button onclick="saveVoucher(<%=v.getId()%>)" class="voucher-button mt-2">Lưu</button>
                                                                        <a href="#" class="voucher-link d-block mt-1" onclick="getRule(<%=v.getId()%>)">Điều kiện</a>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <%}%>
                                                    <%}else{%>
                                                    <div class="col-md-6 voucher-section">
                                                        <div class="voucher-header">
                                                            <h4 class="mb-0">${requestScope.notification}</h4>
                                                        </div>
                                                    </div>
                                                    <%}%>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
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



            <div class="modal fade" id="showRuleVoucher" tabindex="-1" role="dialog" aria-labelledby="orderDetailsModalLabel" aria-hidden="true" >
                <div class="modal-dialog modal-lg" role="document" > <!-- Added modal-lg class -->
                    <div class="modal-content" style="width:400px; margin-left: 20%">
                        <div class="popup" id="popupRule">
                            <!--                        <div class="header">
                                                        <div class="logo">The Card Shop</div>
                                                        <h2 style="color: white;">Giảm 8% Giảm tối đa đ20k</h2>
                                                        <p style="color: white;">Đơn Tối Thiểu đ150k</p>
                                                    </div>
                                                    <h4>Hạn sử dụng mã</h4>
                                                    <p>21 Th07 2024 00:00 - 31 Th07 2024 23:59</p>
                                                    <h4>Ưu đãi</h3>
                                                        <p>Lượt sử dụng có hạn. Nhanh tay kẻo lỡ bạn nhé! Giảm 8% Đơn Tối Thiểu đ150k Giảm tối đa đ20k</p>
                                                        <h4>Áp dụng cho sản phẩm</h4>
                                                        <p>Mã áp dụng trên App cho các sản phẩm tham gia chương trình và người dùng nhất định.</p>
                                                        <ul>
                                                            <li>- Những sản phẩm bị hạn chế chạy khuyến mãi theo quy định của Nhà nước sẽ không được hiển thị nếu nằm trong danh sách sản phẩm đã chọn. Tìm hiểu thêm.</li>
                                                        </ul>
                                                        <h4>Thanh Toán</h4>
                                                        <ul>
                                                            <li>- Thanh toán trực tiếp bằng số dư</li>
                                                            <li>- Tài khoản ngân hàng đã liên kết</li>
                                                            <li>- Thẻ Tín dụng/Ghi nợ</li>
                                                        </ul>
                                                        <button class="button btn-block" onclick="hideRule()" id="agreeButton">Đồng ý</button>-->
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal fade" id="viewMyTicket" tabindex="-1" role="dialog" aria-labelledby="orderDetailsModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg" role="document"> <!-- Modal lớn -->
                    <div class="modal-content">
                        <!-- Tiêu đề của modal -->
                        <div class="modal-header">
                            <h5 class="modal-title" id="orderDetailsModalLabel">Danh sách Mã Giảm Giá Của Bạn</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <!-- Nội dung của modal -->
                        <div class="modal-body">
                            <div class="container" id="viewTicket">
                               
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- jQuery -->
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
            <!-- jQuery -->
            <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10/dist/sweetalert2.all.min.js"></script>
            <!--<script src="js/jquery.min.js"></script>-->
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
                                                    function navigateToCategory() {
                                                        var select = document.getElementById('categorySelect');
                                                        var categoryId = select.value;
                                                        if (categoryId) {
                                                            window.location.href = 'categories?id=' + categoryId;
                                                        }
                                                    }

                                                    function viewMyTichket() {
                                                        //$('#viewMyTicket').modal('show');
                                                        $.ajax({
                                                            url: '/TheCardWebsite/showvoucher', // URL để gửi yêu cầu
                                                            type: 'GET', // Phương thức HTTP
                                                            data: {

                                                            },
                                                            success: function (data) {
                                                                $('#viewTicket').html(data); // Cập nhật nội dung modal với dữ liệu nhận được
                                                                $('#viewMyTicket').modal('show'); // Hiển thị modal
                                                            },
                                                            error: function (xhr, status, error) {
                                                                console.error('Lỗi khi lấy quy tắc voucher:', error); // Xử lý lỗi
                                                                // Hiển thị thông báo lỗi trong modal hoặc phần tử phù hợp
                                                                $('#popupRule').html('<p>Không thể tải quy tắc voucher lúc này. Vui lòng thử lại sau.</p>');
                                                                $('#showRuleVoucher').modal('show'); // Hiển thị modal với thông báo lỗi
                                                            }
                                                        });
                                                    }


                                                    function saveVoucher(voucherId) {
                                                        $.ajax({
                                                            url: '/TheCardWebsite/savevoucher', // URL để gửi yêu cầu
                                                            type: 'POST', // Phương thức HTTP
                                                            data: {
                                                                idVoucher: voucherId
                                                            },
                                                            success: function (data) {
                                                                Swal.fire({
                                                                    title: 'Kết quả xử lý!',
                                                                    html: data,
                                                                    icon: 'info',
                                                                    confirmButtonText: 'Đồng ý'
                                                                }).then((result) => {
                                                                    if (result.isConfirmed) {
                                                                        window.location.reload(); // Load lại trang khi người dùng ấn Đồng ý
                                                                    }
                                                                });
                                                            },
                                                            error: function (xhr, status, error) {
                                                                console.error('Lỗi khi lưu voucher:', error); // Xử lý lỗi
                                                                // Hiển thị thông báo lỗi trong modal hoặc phần tử phù hợp
                                                                $('#popupRule').html('<p>Không thể lưu voucher lúc này. Vui lòng thử lại sau.</p>');
                                                                $('#showRuleVoucher').modal('show'); // Hiển thị modal với thông báo lỗi
                                                            }
                                                        });
                                                    }

                                                    function getRule(voucherId) {
                                                        $.ajax({
                                                            url: '/TheCardWebsite/getrulevoucher', // URL để gửi yêu cầu
                                                            type: 'GET', // Phương thức HTTP
                                                            data: {
                                                                idVoucher: voucherId
                                                            },
                                                            success: function (data) {
                                                                $('#popupRule').html(data); // Cập nhật nội dung modal với dữ liệu nhận được
                                                                $('#viewMyTicket').modal('hide');
                                                                $('#showRuleVoucher').modal('show'); // Hiển thị modal
                                                            },
                                                            error: function (xhr, status, error) {
                                                                console.error('Lỗi khi lấy quy tắc voucher:', error); // Xử lý lỗi
                                                                // Hiển thị thông báo lỗi trong modal hoặc phần tử phù hợp
                                                                $('#popupRule').html('<p>Không thể tải quy tắc voucher lúc này. Vui lòng thử lại sau.</p>');
                                                                $('#showRuleVoucher').modal('show'); // Hiển thị modal với thông báo lỗi
                                                            }
                                                        });
                                                    }

                                                    function hideRule() {
                                                        $('#showRuleVoucher').modal('hide');
                                                    }

                                                    function sendComment() {
                                                        $.ajax({
                                                            url: '/TheCardWebsite/sendcomment',
                                                            type: 'POST',
                                                            data: {
                                                                name: name,
                                                                email: email,
                                                                message: message,
                                                                newId: newId
                                                            },
                                                            success: function (data) {
                                                                Swal.fire({
                                                                    title: 'Kết quả xử lý!',
                                                                    html: data,
                                                                    icon: 'info',
                                                                    confirmButtonText: 'Đồng ý'
                                                                }).then((result) => {
                                                                    if (result.isConfirmed) {
                                                                        window.location.reload(); // Load lại trang khi người dùng ấn Đồng ý
                                                                    }
                                                                });
                                                            },
                                                            error: function (xhr, status, error) {
                                                                console.error('Lỗi khi gửi thông báo đăng ký:', error);
                                                                Swal.fire({
                                                                    title: 'Lỗi',
                                                                    text: 'Không thể gửi thông báo đăng ký lúc này. Vui lòng thử lại sau.',
                                                                    icon: 'error',
                                                                    confirmButtonText: 'Đồng ý'
                                                                });
                                                            },
                                                            complete: function () {
                                                                $('#loading').hide();
                                                            }
                                                        });
                                                    }
            </script>
            <!-- custom js -->
            <!--<script src="js/chart_custom_style1.js"></script>-->
            <script src="js/custom.js"></script>
    </body>
</html>



