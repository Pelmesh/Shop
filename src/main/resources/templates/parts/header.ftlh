<#macro page>
    <header class="header">
        <a href="#" class="nav-btn">
            <span></span>
            <span></span>
            <span></span>
        </a>
        <div class="top-panel">
            <div class="container">
                <div class="top-panel-cover">
                    <ul class="header-cont">
                        <li><a href="tel:+375441234567"><i class="fa fa-phone"></i>+375 44 7180454</a></li>
                        <li><a href="mailto:creto@gmail.com"><i class="fa fa-envelope" aria-hidden="true"></i>creto@gmail.com</a></li>
                    </ul>
                    <ul class="icon-right-list">
                        <li><a class="header-like" href="#"><i class="fa fa-heart" aria-hidden="true"></i></a></li>
                        <li><a class="header-user" href="#"><i class="fa fa-user" aria-hidden="true"></i></a></li>
                        <li><a class="header-cart header-like" href="/cart"><i class="fa fa-shopping-cart" aria-hidden="true"></i><span>${countCart!}</span></a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="header-menu">
            <div class="container">
                <a href="/" class="logo"><img src="/img/logo3.svg" alt="logo"></a>
                <nav class="nav-menu">
                    <ul class="nav-list">
                        <li class="dropdown">
                            <a href="#">Pages <i class="fa fa-angle-down" aria-hidden="true"></i></a>
                            <ul>
                                <li><a href="about.html">About Us</a></li>
                                <li><a href="news.html">News</a></li>
                                <li><a href="404.html">Page error 404</a></li>
                            </ul>
                        </li>
                        <li><a href="/create">Create</a></li>
                        <li><a href="services.html">Services</a></li>
                        <li><a href="shop.html">Shop</a></li>
                        <li class="active"><a href="gallery.html">Gallery</a></li>
                        <li><a href="shop.html">Shop</a></li>
                        <li><a href="contacts.html">Contacts</a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </header>
</#macro>