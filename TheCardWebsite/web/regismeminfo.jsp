
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
        <title>The Card Shop - Đơn đăng ký</title>
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
        <style>
            .full.price_table.padding_infor_info {
                padding: 20px;
                margin: 20px 50px;
                max-width: 90%;
                width: 100%;
                background-color: #f9f9f9;
                border: 1px solid #ddd;
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                font-family: 'Arial', sans-serif;
                color: #333;
                text-align: center; /* Center text horizontally */
            }
            .full.price_table.padding_infor_info h5 {
                color: #007bff;
                font-size: 1.2em;
                margin-top: 1.5em;
            }
            .full.price_table.padding_infor_info p {
                font-size: 1em;
                line-height: 1.5;
                margin-top: 0.5em;
            }
            #moreDetailContent {
                padding: 20px;
                font-family: 'Arial', sans-serif;
                color: #333;
            }

            #moreDetailContent h5 {
                font-size: 1.2em;
                color: #007bff;
                margin-bottom: 10px;
                text-align: center;
            }

            #moreDetailContent p {
                font-size: 1em;
                margin: 10px 0;
                line-height: 1.6;
                text-align: justify;
            }

            #moreDetailContent .highlight {
                font-weight: bold;
                color: #dc3545;
            }

            #moreDetailContent .email-info {
                background-color: #f8f9fa;
                padding: 10px;
                border-radius: 5px;
                margin-bottom: 10px;
            }

            #moreDetailContent .email-info span {
                color: #28a745;
                font-weight: bold;
            }

            #moreDetailContent .created-at {
                color: #17a2b8;
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
                        <h4>Quản lí đơn đăng ký</h4>
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
                                                <h2>Đơn đăng ký thành viên</h2>
                                            </div>
                                        </div>
                                        <div style="padding: 0px 20px 20px 20px" class="full price_table padding_infor_info">
                                            <h5>Văn bản yêu cầu trở thành thành viên thân thiết</h2>
                                                <p>Tôi ${memberRegis.getUser().getFirstName()} ${memberRegis.getUser().getLastName()} đã hoàn thành đủ yêu cầu để trở thành thành viên thân thiết.</p>

                                                <h5>1. Email bạn bè 1</h3>
                                                    <p>${memberRegis.getEmail1()}</p>

                                                    <h5>2. Email bạn bè 2</h3>
                                                        <p>${memberRegis.getEmail2()}</p>

                                                        <h5>3. Thông điệp</h3>
                                                            <p>${memberRegis.getMsgString()}</p>

                                                            <h5>4. Ngày gửi đơn</h3>
                                                                <p>${memberRegis.getCreatedAt()}</p>
                                                                <div style="display: flex; justify-content: center">
                                                                    <button style="margin-right: 20px" type="button" class="btn btn-info" onclick="validEmail('${memberRegis.getUser().getEmail()}', '${memberRegis.getEmail1()}', '${memberRegis.getEmail2()}')">Kiểm tra email</button>
                                                                    <form action="resultaccess" method="get">
                                                                        <input type="hidden" name="choosed" value="reject">
                                                                        <input type="hidden" name="idform" value="${memberRegis.getId()}">
                                                                        <button style="background-color: #FF5722; border-color: #FF5722; margin-right: 20px; margin-left: 20px" class="btn btn-info" type="submit">Từ chối</button> 
                                                                    </form>
                                                                    <form action="resultaccess" method="get">
                                                                        <input type="hidden" name="choosed" value="accept">
                                                                        <input type="hidden" name="idform" value="${memberRegis.getId()}">
                                                                        <button style="background-color: #009c95; margin-left: 20px;" class="btn btn-info" type="submit">Chấp nhận</button>
                                                                    </form>
                                                                </div>
                                                                </div>
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

                                                                <div class="modal fade" id="moreDetail" tabindex="-1" role="dialog" aria-labelledby="moreDetailLabel" aria-hidden="true">
                                                                    <div style="max-width: 40%; width: 60%;" class="modal-dialog" role="document">
                                                                        <div class="modal-content">
                                                                            <div class="modal-header">
                                                                                <h5 class="modal-title" id="moreDetailLabel">Thông tin email</h5>
                                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                                    <span aria-hidden="true">&times;</span>
                                                                                </button>
                                                                            </div>
                                                                            <div class="modal-body" id="moreDetailBody">
                                                                                <div id="moreDetailContent"></div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>

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

                                                                        function validEmail(e0, e1, e2) {
                                                                            $.ajax({
                                                                                url: '/TheCardWebsite/validemail',
                                                                                type: 'GET',
                                                                                data: {
                                                                                    e0: e0,
                                                                                    e1: e1,
                                                                                    e2: e2
                                                                                },
                                                                                success: function (data) {
                                                                                    $('#moreDetailContent').html(data);
                                                                                    $('#moreDetail').modal('show');
                                                                                },
                                                                                error: function (xhr) {
                                                                                    console.error('Error fetching order data:', xhr);
                                                                                }
                                                                            });
                                                                        }
                                                                </script>
                                                                <!-- custom js -->
                                                                <script src="js/chart_custom_style1.js"></script>
                                                                <script src="js/custom.js"></script>
                                                                </body>
                                                                </html>

