<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <title>The card shop - Đăng ký thành viên</title>
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
        <%
            HttpSession sess = request.getSession();
            
            AccountLoginDAO ald = new AccountLoginDAO();
            UserDAO userDao = new UserDAO();
            GoogleLoginDAO gld = new GoogleLoginDAO();
            
            User user = null;
            AccountLogin account = null;
            GoogleLogin gglogin = null;
            //account = (AccountLogin) sess.getAttribute("account");
            //gglogin = (GoogleLogin) sess.getAttribute("gguser");
            //user = (User) userDao.getUserById(account.getUser().getID());
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
            List<Comment> dataComment = (List<Comment>) request.getAttribute("comments");
        %>
        <style>
            .rmember-form {
                display: flex;
                flex-direction: column;
                width: 100%;
            }

            .rmember-form input,
            .rmember-form button {
                width: 100%;
                padding: 10px;
                margin-bottom: 10px;
                box-sizing: border-box;
            }
        </style>
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
                        <h4>Chi tiết sản phẩm</h4>
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
                                            <div class="heading1 margin_0">
                                                <h2>Đăng ký thành viên</h2>
                                            </div>
                                        </div>
                                        <div class="full price_table padding_infor_info">
                                            <h5 style="color: green; text-align: center; margin-bottom: 20px" >Điều kiện để trở thành thành viên là  giới thiệu 2 người bạn mới sử dụng hệ thống</h5>
                                            <p>Khi bạn giới thiệu 2 người bạn sử dụng trang web của chúng tôi, bạn sẽ nhận được ưu đãi giảm 2% mỗi đơn hàng!</p>
                                            <form class="rmember-form">
                                                <input type="text" id="email1" name="email1" placeholder="Nhập email thứ nhất" required>
                                                <input type="text" id="email2" name="email2" placeholder="Nhập email thứ hai" required>
                                                <input type="text" id="msg" name="msg" placeholder="Nhập lời nhắn" required>
                                                <button class="btn btn-info" type="button" onclick="regisMember()">Gửi đăng ký</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- end graph -->

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
                                                    function regisMember() {
                                                        var email1 = $('#email1').val();
                                                        var email2 = $('#email2').val();
                                                        var msg = $('#msg').val();

                                                        // Kiểm tra định dạng email
                                                        var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

                                                        if (!emailPattern.test(email1)) {
                                                            alert('Email thứ nhất không hợp lệ.');
                                                            return;
                                                        }

                                                        if (!emailPattern.test(email2)) {
                                                            alert('Email thứ hai không hợp lệ.');
                                                            return;
                                                        }

                                                        // Thực hiện yêu cầu AJAX nếu tất cả email đều hợp lệ
                                                        $.ajax({
                                                            url: '/TheCardWebsite/registermember',
                                                            type: 'POST',
                                                            data: {
                                                                email1: email1,
                                                                email2: email2,
                                                                msg: msg
                                                            },
                                                            dataType: 'json', // Đảm bảo jQuery xử lý phản hồi dưới dạng JSON
                                                            success: function (data) {
                                                                alert(data.message);
                                                                if (data.message === "Đã gửi thành công, hãy chờ hệ thống xét duyệt!") {
                                                                    window.location.href = '/TheCardWebsite/home';
                                                                }
                                                            },
                                                            error: function (xhr) {
                                                                console.error('Error fetching user details:', xhr);
                                                                alert('Đã xảy ra lỗi, vui lòng thử lại sau.');
                                                            }
                                                        });
                                                    }
            </script>
        </script>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <!-- custom js -->
        <script src="js/chart_custom_style1.js"></script>
        <script src="js/custom.js"></script>
</body>
</html>

