<%-- 
    Document   : news
    Created on : Jul 29, 2024, 8:39:49 PM
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
        <title>The Card Shop - Tin tức</title>
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
            /*// mới*/
            .form-inline {
                display: flex;
                flex-flow: row nowrap;
                align-items: center;
            }

            /* Style for the select dropdown */
            .form-control {
                display: inline-block;
                width: 100%;
                height: calc(2.25rem + 2px);
                padding: 0.375rem 0.75rem;
                font-size: 1rem;
                line-height: 1.5;
                color: #495057;
                background-color: #fff;
                background-clip: padding-box;
                border: 1px solid #ced4da;
                border-radius: 0.25rem;
                transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
            }

            .form-control:focus {
                color: #495057;
                background-color: #fff;
                border-color: #80bdff;
                outline: 0;
                box-shadow: 0 0 0 0.2rem rgba(38, 143, 255, 0.25);
            }

            /* Style for the form select dropdown in navbar */
            #categorySelect {
                border: 1px solid #ccc;
                border-radius: 0.25rem;
                box-shadow: 0 0 0 0.2rem rgba(0, 0, 0, 0.1);
                padding: 0.5rem;
                font-size: 1rem;
                width: 200px; /* Adjust width as needed */
                margin-left: 15px; /* Add some spacing */
            }

            #categorySelect option {
                padding: 10px;
            }

            .divider {
                height: 1px;           /* Chiều cao của đường phân cách */
                background-color: #333; /* Màu của đường phân cách */
                margin: 20px 0;       /* Khoảng cách phía trên và dưới */
                width: 100%;          /* Chiều rộng của đường phân cách */
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
            
            CategoriesNewsDAO ctnd = new CategoriesNewsDAO();
            List<CategoriesNews> dataCate = ctnd.getListCategoriesNews();
            NewsDAO nd = new NewsDAO();
            List<News> dataNews = (List<News>) request.getAttribute("dataNews");//nd.getListNewByID(1);
            int idCategoriesNews = 0; 
            if(request.getAttribute("id") != null){
                idCategoriesNews = (int) request.getAttribute("id");
            }
            List<News> dataHotNews = nd.getListHotNews();
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
                        <h4>Tin tức</h4>
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
                            <li class="active">
                                <a href="#report_page" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-book white_color"></i> <span>Báo cáo & Chính sách</span></a>
                                <ul class="collapse list-unstyled" id="report_page">
                                    <%if(account != null || gglogin != null){%>
                                    <li>
                                        <a href="showreportuser"><i class="fa fa-arrow-circle-right"></i> <span>Danh sách báo cáo</span></a>
                                    </li>
                                    <li>
                                        <a href="showagreementpolicy"> <i class="fa fa-arrow-circle-right"></i><span>Chính sách & Thỏa thuận</span></a>
                                    </li>
                                    <%} else{%>
                                    <li>
                                        <a href=""> <i class="fa fa-arrow-circle-right"></i><span>Vui lòng đăng nhập!</span></a>
                                    </li>
                                    <%}%>
                                </ul>
                            </li>
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
                                        <header>
                                            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                                                <div class="container">
                                                    <a class="navbar-brand" href="news">Bảng tin và thông báo</a>
                                                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                                                        <span class="navbar-toggler-icon"></span>
                                                    </button>
                                                    <div class="collapse navbar-collapse" id="navbarNav">
                                                        <form class="form-inline ml-auto">
                                                            <select class="form-control" id="categorySelect" onchange="navigateToCategory()">
                                                                <option value="" selected disabled>Lựa chọn tin tức</option>
                                                                <% for (CategoriesNews e : dataCate) { %>
                                                                <option value="<%= e.getID() %>" <%= (e.getID() == idCategoriesNews) ? "selected" : "" %> >
                                                                    <%= e.getTitle() %>
                                                                </option>
                                                                <% } %>
                                                            </select>
                                                        </form>
                                                    </div>
                                                </div>
                                            </nav>
                                        </header>

                                        <main class="container mt-4">
                                            <h1 class="mb-4">Tin mới nhất</h1>
                                            <div class="row">
                                                <div class="col-md-8">
                                                    <%if(dataNews != null){%>
                                                    <%for(int i = 0; i <= 0; i++) {%>
                                                    <article class="mb-4">
                                                        <h2 style="color: black; padding-bottom: 20px;">Tin mới: <%=dataNews.get(i).getTitle()%></h2>
                                                        <p class="text-muted">Bởi Quản trị viên | <%=dataNews.get(i).getCreatedAt()%></p>
                                                        <img src="images/logo/viettel_logo.jpg" alt="Political Reform" class="img-fluid mb-3">
                                                        <p><%=dataNews.get(i).getDescription()%></p>
                                                        <a href="newsdetail?Id=<%=dataNews.get(i).getID()%>" class="btn btn-primary">Đọc thêm</a>
                                                    </article>
                                                    <%}%>
                                                    <%}else{%>
                                                    <article class="mb-4">
                                                        <h2 style="color: black; padding-bottom: 20px;">Bảng tin về khuyển mãi trống</h2>
                                                    </article>
                                                    <%}%>
                                                </div>

                                                <div class="col-md-4">
                                                    <h3>Tin nổi bật</h3>
                                                    <ul class="list-group">
                                                        <%if(dataHotNews != null){%>
                                                        <%for (News n : dataHotNews) {%>
                                                        <li class="list-group-item">
                                                            <a href="newsdetail?Id=<%=n.getID()%>" class="list-group-link"><%=n.getTitle()%></a>
                                                        </li>
                                                        <%}%>
                                                        <%}else{%>
                                                        <li class="list-group-item">Không có tin nào nổi bật!</li>
                                                            <%}%>
                                                    </ul>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="divider"></div>
                                                    <%if(dataNews != null){%>
                                                    <%for (int i = 1; i < dataNews.size(); i++) {%>
                                                    <article class="mb-4">
                                                        <h2 style="color: black; padding-bottom: 20px;">Tin mới: <%=dataNews.get(i).getTitle()%></h2>
                                                        <p class="text-muted">Bởi Quản trị viên | <%=dataNews.get(i).getCreatedAt()%></p>
                                                        <img src="images/logo/viettel_logo.jpg" alt="Political Reform" class="img-fluid mb-3">
                                                        <p><%=dataNews.get(i).getDescription()%></p>
                                                        <a href="newsdetail?Id=<%=dataNews.get(i).getID()%>" class="btn btn-primary">Đọc thêm</a>
                                                        <div class="divider"></div>
                                                    </article>
                                                    <%}%>
                                                    <%}else{%>
                                                    <article class="mb-4">
                                                        <h2 style="color: black; padding-bottom: 20px;">Bảng tin về khuyển mãi trống</h2>
                                                    </article>
                                                    <%}%>
                                                </div>
                                            </div> 

                                            <section class="mt-5" style="margin-bottom: 30px;">
                                                <h3>Đăng kí nhận thông báo mới</h3>
                                                <div class="card">
                                                    <div class="card-body">
                                                        <h5 class="card-title">Vui lòng để tại thông tin</h5>
                                                        <!--<form action="registergetnotification" method="POST">-->
                                                        <div class="form-group">
                                                            <label for="commentName">Họ tên</label>
                                                            <input type="text" class="form-control" name="name" id="commentName" required>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="commentEmail">Email</label>
                                                            <input type="email" class="form-control" name="email" id="commentEmail" required>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="commentContent">Lời nhắn (Không bắt buộc) </label>
                                                            <textarea class="form-control" name="message" id="commentContent" rows="3"></textarea>
                                                        </div>
                                                        <button type="submit" onclick="registerNotification()" class="btn btn-primary">Đăng kí</button>
                                                        <!--</form>-->
                                                    </div>
                                                </div>
                                            </section>
                                        </main>
                                    </div>
                                </div>
                            </div>
                            <!--Show loại thẻ-->

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
                                                            function registerNotification() {
                                                                var name = $('#commentName').val();
                                                                var email = $('#commentEmail').val();
                                                                var message = $('#commentContent').val();

                                                                $.ajax({
                                                                    url: '/TheCardWebsite/registergetnotification',
                                                                    type: 'POST',
                                                                    data: {
                                                                        name: name,
                                                                        email: email,
                                                                        message: message
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

