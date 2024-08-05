<%-- 
    Document   : managecommentnew
    Created on : Aug 1, 2024, 9:51:45 PM
    Author     : Bravo 15
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- mobile metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="initial-scale=1, maximum-scale=1">
        <!-- site metas -->
        <title>The Card Shop - Quản lý bình luận tin</title>
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
        <link rel="stylesheet" href="path/to/your/css/styles.css">
        <!--[if lt IE 9]>-->
        <!--        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
                <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>-->
        <style>
            .modal-lg-custom {
                max-width: 90%;
            }
            .modal-content-custom {
                width: 100%;
            }

            .switch {
                position: relative;
                display: inline-block;
                width: 40px; /* Smaller width */
                height: 20px; /* Smaller height */
            }
            .switch input {
                opacity: 0;
                width: 0;
                height: 0;
            }
            .slider {
                position: absolute;
                cursor: pointer;
                top: 0;
                left: 0;
                right: 0;
                bottom: 0;
                background-color: #ccc;
                transition: .4s;
            }
            .slider:before {
                position: absolute;
                content: "";
                height: 14px; /* Smaller knob height */
                width: 14px; /* Smaller knob width */
                left: 3px; /* Adjusted for smaller switch */
                bottom: 3px; /* Adjusted for smaller switch */
                background-color: white;
                transition: .4s;
            }
            input:checked + .slider {
                background-color: #2196F3;
            }
            input:checked + .slider:before {
                transform: translateX(20px); /* Adjusted for smaller switch */
            }
            .slider.round {
                border-radius: 20px; /* Smaller border radius */
            }
            .slider.round:before {
                border-radius: 50%;
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
            BrandDAO brandDao = new BrandDAO();
            List<Brand> dataBrand = brandDao.getListBrand();
            ProductCategoriesDAO pcdDao = new ProductCategoriesDAO();
            List<ProductCategories> dataPCate = pcdDao.getListProductAdmin();
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
                        <h4>Quản lý bình luận tin tức</h4>
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
                                    <div class="page_title" style="padding: 10px;">
                                        <marquee behavior="scroll" direction="left">
                                            <h6 style="color: #FF5722;">Website thực hành SWP391 của Nhóm 2 | SE1801 | FPT <a href="https://www.facebook.com/Anhphanhne" target="_blank" style="letter-spacing: 2px;"> | <i class="fa fa-facebook-square"></i></a>
                                                Bài tập đang trong quá trình hoàn thành và phát triển!</h6>
                                        </marquee>
                                    </div>
                                </div>
                            </div>
                            <div class="row column1">
                                <div class="col-md-12">
                                    <div class="card">
                                        <div class="card-header bg-light">
                                            <div class="d-flex justify-content-between align-items-center">
                                                <h5 class="card-title mb-0">Quản lý bình luận tin tức</h5>
                                                <form class="form-inline" action="searchcommentnews" method="GET">
                                                    <%if(request.getAttribute("key")!=null){
                                                    String key = (String) request.getAttribute("key");%>
                                                    <input class="form-control mr-sm-2" type="search" placeholder="Nhập email người dùng" aria-label="Search" name="keyword" value="<%= key%>"/>
                                                    <%} else{%>
                                                    <input class="form-control mr-sm-2" type="search" placeholder="Nhập email người dùng" aria-label="Search" name="keyword"/>
                                                    <%}%>
                                                    <button class="btn btn-outline-primary my-2 my-sm-0" type="submit"><i class="fa fa-search"></i>Tìm kiếm</button>
                                                </form>
                                            </div>
                                        </div>
                                        <div class="card-body">
                                            <div class="table-responsive">
                                                <table class="table table-striped">
                                                    <thead>
                                                        <tr>
                                                            <th style="text-align: center; font-weight: bold; font-family: sans-serif; font-size: 15px; width: 30px">ID</th>
                                                            <th style="text-align: center; font-weight: bold; font-family: sans-serif; font-size: 15px;">Tên</th>
                                                            <th style="text-align: center; font-weight: bold; font-family: sans-serif; font-size: 15px;">Email</th>
                                                            <th style="text-align: center; font-weight: bold; font-family: sans-serif; font-size: 15px;">Bình luận</th>
                                                            <th style="text-align: center; font-weight: bold; font-family: sans-serif; font-size: 15px;">Tin tức</th>
                                                            <th style="text-align: center; font-weight: bold; font-family: sans-serif; font-size: 15px;">Trạng thái</th>
                                                            <th style="text-align: center; font-weight: bold; font-family: sans-serif; font-size: 15px;">Xóa bởi</th>
                                                            <th style="text-align: center; font-weight: bold; font-family: sans-serif; font-size: 15px;">Điều chỉnh</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%List<CommentNews> dataCMNews = (List<CommentNews>) request.getAttribute("dataCMNews");
                                                            if (dataCMNews != null) {
                                                            for (CommentNews n : dataCMNews) {%>
                                                        <tr><td style="cursor: pointer;"><%= n.getId() %></td>
                                                    <input type="hidden" id="idnews" value="<%= n.getId() %>">
                                                    <td style="text-align: center"  style="cursor: pointer;"><%= n.getName() %></td>
                                                    <td style="text-align: center"  style="cursor: pointer;"><%= n.getEmail() %></td>
                                                    <td style="text-align: center"  style="cursor: pointer;"><%= n.getMessage() %></td>
                                                    <td style="text-align: center"  style="cursor: pointer;"><%= n.getNews().getTitle() %></td>

                                                    <%if(n.getIsDelete() == false){%>
                                                    <td style="text-align: center"> Đang hoạt động</td>
                                                    <%}else {%>
                                                    <td style="text-align: center" > Ngừng hoạt động</td>
                                                    <%}%>

                                                    <%if(n.getDeletedBy() == 0){%>
                                                    <td style="text-align: center">Trống</td>
                                                    <%}else {%>
                                                    <td style="text-align: center" ><%=n.getDeletedBy() %></td>
                                                    <%}%>

                                                    <td class="d-flex justify-content-start align-items-center">
                                                        <a href="#" class="btn btn-info btn-sm text-light mr-2" 
                                                           data-id="<%=n.getId()%>" 
                                                           data-name="<%=n.getName()%>" 
                                                           data-email="<%=n.getEmail()%>"
                                                           data-message="<%=n.getMessage()%>"
                                                           data-news="<%=n.getNews().getTitle()%>"
                                                           data-createdat="<%=n.getCreatedAt()%>"
                                                           data-updatedat="<%=n.getUpdatedAt()%>"
                                                           data-deletedat="<%=n.getDeletedAt()%>"
                                                           data-deletedby="<%=n.getDeletedBy()%>"
                                                           data-isdelete="<%=n.getIsDelete()%>"
                                                           data-toggle="modal" 
                                                           data-target="#viewDetailModal">Xem chi tiết</a>
                                                        <%if(n.getIsDelete() == false){%>
                                                        <a href="#" class="btn btn-danger btn-sm" onclick="rejectComment(<%= n.getId()%>)">Xóa</a> 
                                                        <%}else{%>

                                                        <%}%>
                                                    </td>
                                                    </tr>
                                                    <%}}%> 
                                                    </tbody>
                                                </table>

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
                            <!-- end dashboard inner -->
                        </div>
                        <!-- end dashboard inner -->
                    </div>
                </div>
            </div>

            <!-- Phần còn lại của nội dung -->
        </div>
        <!-- Kết thúc thẻ content -->
        <div class="modal fade" id="viewDetailModal" tabindex="-1" role="dialog" aria-labelledby="viewDetailModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addBrandModalLabel">Chi tiết bình luận</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="name">Tên người dùng:</label>
                            <input type="text" class="form-control" id="name" name="name" required readonly>
                        </div>
                        <div class="form-group">
                            <label for="name">Email:</label>
                            <input type="text" class="form-control" id="email" name="email" readonly required>
                        </div>
                        <div class="form-group">
                            <label for="name">Nội dung bình luận:</label>
                            <input type="text" class="form-control" id="contentComment" name="contentComment" readonly required>
                        </div>
                        <div class="form-group">
                            <label for="name">Tin tức:</label>
                            <input type="text" class="form-control" id="news" name="news" readonly required>
                        </div>
                        <div class="form-group">
                            <label for="name">Ngày tạo:</label>
                            <input type="text" class="form-control" id="createdat" name="createdat" readonly required>
                        </div>
                        <div class="form-group">
                            <label for="name">Ngày cập nhật:</label>
                            <input type="text" class="form-control" id="updatedat" name="updatedat" readonly required>
                        </div>
                        <div class="form-group">
                            <label for="hotNewsToggle">Ngày xóa:</label>
                            <input type="text" class="form-control" id="deletedat" name="deletedat" readonly required>
                        </div>
                        <div class="form-group">
                            <label for="name">Trạng thái:</label>
                            <input type="text" class="form-control" id="status" name="status" readonly required>
                        </div>
                        <div class="form-group">
                            <label for="name">Xóa bởi:</label>
                            <input type="text" class="form-control" id="deletedby" name="deletedby" readonly required>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        $('#viewDetailModal').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget); // Button that triggered the modal
            // Lấy giá trị từ thuộc tính data-*
            var id = button.data('id');
            var name = button.data('name');
            var email = button.data('email');
            var message = button.data('message');

            var news = button.data('news');
            var createdat = button.data('createdat');
            var updatedat = button.data('updatedat');

            var deletedat = button.data('deletedat');
            var deletedby = button.data('deletedby');
            var isdelete = button.data('isdelete');

            // Kiểm tra giá trị
            console.log('ID:', id);
            console.log('Title:', name);
            console.log('Email:', email);
            console.log('Desc:', message);
            console.log('Content First:', news);
            console.log('Content Body:', createdat);
            console.log('Content End:', updatedat);
            console.log('Categories:', deletedat);
            console.log('Hot News:', deletedby);
            console.log('Desc Image:', isdelete);

            // Gán giá trị cho các trường trong modal
            var modal = $(this);
            modal.find('#idUpdate').val(id || '');
            modal.find('#name').val(name || '');
            modal.find('#email').val(email || '');
            modal.find('#contentComment').val(message || '');
            modal.find('#news').val(news || '');
            modal.find('#createdat').val(createdat || '');
            modal.find('#updatedat').val(updatedat || 'Trống');
            modal.find('#deletedat').val(deletedat || 'Trống');
            modal.find('#status').val(deletedby || 'Trống');
            modal.find('#deletedby').val(isdelete || 'Trống');

        });
    });
</script>
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


    function rejectComment(id) {
        $.ajax({
            url: '/TheCardWebsite/rejectcommentnews',
            type: 'POST',
            data: {
                id: id
            },
            success: function (data) {
                if (data.valid) {
                    Swal.fire({
                        title: 'Xóa bình luận thành công',
                        text: data.message,
                        icon: 'success',
                        confirmButtonText: 'Đồng ý'
                    }).then((result) => {
                        if (result.isConfirmed) {
                            location.reload();
                        }
                    });
                } else {
                    Swal.fire({
                        title: 'Lỗi xóa bình luận',
                        text: data.message,
                        icon: 'warning',
                        confirmButtonText: 'Đồng ý'
                    }).then((result) => {
                        if (result.isConfirmed) {
                            location.reload();
                        }
                    });
                }
            },
            error: function (xhr, status, error) {
                console.error('Lỗi khi xóa bình luận tin tức:', error); // Xử lý lỗi
            }
        });
    }
</script>
<!-- custom js -->
<!--<script src="js/chart_custom_style1.js"></script>-->
<script src="js/custom.js"></script>
</body>
</html>




