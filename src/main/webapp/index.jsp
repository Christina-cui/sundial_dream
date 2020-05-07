<%--
  Created by IntelliJ IDEA.
  User: 11957
  Date: 2020/4/25
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>

<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<html>
<head>
    <title>SUNDIAL DREAMS</title>
    <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700|Montserrat:300,400,700,900" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700|Montserrat:300,400,700,900" rel="stylesheet">

    <link type="text/css" rel="stylesheet" href="indexCss/style.min.css">
    <link type="text/css" rel="stylesheet" href="<%=basePath %>indexCss/styles-merged.css">

    <script src="<%=basePath%>indexJS/scripts.min.js"></script>
    <script src="<%=basePath%>indexJS/main.min.js"></script>
    <script src="<%=basePath%>indexJS/custom.js"></script>

</head>
<body>
<nav class="navbar navbar-default probootstrap-navbar">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">"暖阳公益"</span>
                <span class="icon-bar">一灯如豆</span>
                <span class="icon-bar">万盏燎原</span>
            </button>
            <a class="navbar-brand" href="index1.jsp"></a>
        </div>

        <jsp:include page="header.jsp"></jsp:include>

        <%--            <div id="navbar-collapse" class="navbar-collapse collapse">--%>
        <%--                <ul class="nav navbar-nav navbar-right">--%>
        <%--                    <li class="active"><a href="index1.jsp">主页</a></li>--%>
        <%--                    <li><a href="#">公益咨询</a></li>--%>
        <%--                    <li><a href="#">志愿招募</a></li>--%>
        <%--                    <li>--%>
        <%--                        <a href="#" data-toggle="dropdown" class="dropdown-toggle">志愿风采</a>--%>
        <%--                        <ul class="dropdown-menu">--%>
        <%--                            <li><a href="#">活动风采</a></li>--%>
        <%--                            <li><a href="#">特色活动</a></li>--%>
        <%--                            <li><a href="#">团队</a></li>--%>
        <%--                            <li><a href="#">个人</a></li>--%>
        <%--                        </ul>--%>
        <%--                    </li>--%>
        <%--                    <li><a href="#">公益论坛</a></li>--%>
        <%--                    <li><a href="#">公益求助</a> </li>--%>
        <%--                    <li class="dropdown">--%>
        <%--                        <a href="#" data-toggle="dropdown" class="dropdown-toggle">更多</a>--%>
        <%--                        <ul class="dropdown-menu">--%>
        <%--                            <li><a href="about.html">1</a></li>--%>
        <%--                            <li><a href="testimonial.html">2</a></li>--%>
        <%--                            <li><a href="cause-single.html">Cause Single</a></li>--%>
        <%--                            <li><a href="gallery.html">Gallery</a></li>--%>
        <%--                            <li class="dropdown-submenu dropdown">--%>
        <%--                                <a href="#" data-toggle="dropdown" class="dropdown-toggle"><span>Sub Menu</span></a>--%>
        <%--                                <ul class="dropdown-menu">--%>
        <%--                                    <li><a href="#">Second Level Menu</a></li>--%>
        <%--                                    <li><a href="#">Second Level Menu</a></li>--%>
        <%--                                    <li><a href="#">Second Level Menu</a></li>--%>
        <%--                                    <li><a href="#">Second Level Menu</a></li>--%>
        <%--                                </ul>--%>
        <%--                            </li>--%>
        <%--                            <li><a href="news.html">News</a></li>--%>
        <%--                            <li><a href="contact.html">Contact</a></li>--%>
        <%--                        </ul>--%>
        <%--                    </li>--%>

        <%--                    <li class="probootstra-cta-button last"><a href="" class="btn btn-primary">登录</a></li>--%>
        <%--                </ul>--%>
        <%--            </div>--%>
    </div>
</nav>

<section class="probootstrap-hero" style="background-image: url(<%=basePath %>img/hero_bg_bw_1.jpg)"
         data-stellar-background-ratio="0.5">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="probootstrap-slider-text probootstrap-animate" data-animate-effect="fadeIn">
                    <h1 class="probootstrap-heading probootstrap-animate">愿你被这个世界温柔以待 <span>一灯如豆，万盏燎原</span></h1>
                    <p class="probootstrap-animate"><a href="#" class="btn btn-primary btn-lg">点击捐赠</a></p>
                </div>
            </div>
        </div>
    </div>
    <div class="probootstrap-service-intro">
        <div class="container">
            <div class="probootstrap-service-intro-flex">
                <div class="item probootstrap-animate" data-animate-effect="fadeIn">
                    <div class="icon">
                        <i class="icon-wallet"></i>
                    </div>
                    <div class="text">
                        <h2>我有志愿活动！</h2>
                        <p>我们一直在路上</p>
                        <p><a href="#">活动申请</a></p>
                    </div>
                </div>
                <div class="item probootstrap-animate" data-animate-effect="fadeIn">
                    <div class="icon">
                        <i class="icon-heart"></i>
                    </div>
                    <div class="text">
                        <h2>我要当志愿者！</h2>
                        <p>虽然不曾谋面，但是还是很想谢谢你</p>
                        <p><a href="#">活动报名</a></p>
                    </div>
                </div>
                <div class="item probootstrap-animate" data-animate-effect="fadeIn">
                    <div class="icon">
                        <i class="icon-graduation-cap"></i>
                    </div>
                    <div class="text">
                        <h2>我需要帮助！</h2>
                        <p>世界的尽头依旧有爱</p>
                        <p><a href="#">帮帮我</a></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<footer class="probootstrap-footer probootstrap-bg">
    <div class="container">
        <div class="row">
            <div class="col-md-4 probootstrap-animate">
                <div class="probootstrap-footer-widget">
                    <h3>About Us</h3>
                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Porro provident suscipit natus a
                        cupiditate ab minus illum quaerat maxime inventore Ea consequatur consectetur hic provident
                        dolor ab aliquam eveniet alias</p>
                    <ul class="probootstrap-footer-social">
                        <li><a href="#"><i class="icon-twitter"></i></a></li>
                        <li><a href="#"><i class="icon-facebook"></i></a></li>
                        <li><a href="#"><i class="icon-github"></i></a></li>
                        <li><a href="#"><i class="icon-dribbble"></i></a></li>
                        <li><a href="#"><i class="icon-linkedin"></i></a></li>
                        <li><a href="#"><i class="icon-youtube"></i></a></li>
                    </ul>
                </div>
            </div>

            <div class="col-md-4 probootstrap-animate">
                <div class="probootstrap-footer-widget">
                    <h3>Contact Info</h3>
                    <ul class="probootstrap-contact-info">
                        <li><i class="icon-location2"></i>
                            <span>198 West 21th Street, Suite 721 New York NY 10016</span></li>
                        <li><i class="icon-mail"></i><span>info@domain.com</span></li>
                        <li><i class="icon-phone2"></i><span>+123 456 7890</span></li>
                    </ul>

                </div>
            </div>

            <div class="col-md-4 probootstrap-animate">
                <div class="probootstrap-footer-widget">
                    <h3>Donation</h3>
                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Fugit omnis nam obcaecati placeat.
                        Repellendus omnis in praesentium molestiae rem eligendi.</p>
                    <p><a href="#" class="btn btn-primary">Donate Now</a></p>
                </div>
            </div>

        </div>
        <!-- END row -->

    </div>

    <div class="probootstrap-copyright">
        <div class="container">
            <div class="row">
                <div class="col-md-8 text-left">
                    <p>Copyright &copy; 2018.proCompany name All rights reserved.More Templates <a
                            href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a> - Collect from <a
                            href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a></p>
                </div>
                <div class="col-md-4 probootstrap-back-to-top">
                    <p><a href="#" class="js-backtotop">Back to top <i class="icon-arrow-long-up"></i></a></p>
                </div>
            </div>
        </div>
    </div>
</footer>
</body>
</html>
