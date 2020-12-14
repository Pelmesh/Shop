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
                        <li><a href="mailto:t-shirt.creto@mail.ru"><i class="fa fa-envelope" aria-hidden="true"></i>t-shirt.creto@mail.ru</a></li>
                    </ul>
                    <ul class="icon-right-list">
                        <#if userAuth??>
                            <li><a href="/logout">Log Out</a></li>
                            <li><a class="header-user" href="/me"><i class="fa fa-user" aria-hidden="true"></i></a></li>
                        <#else>
                            <li><a href="/registration">Sign Up</a></li>
                            <li><a href="/login">Sign In</a></li>
                        </#if>
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
                        <li class="active"><a href="/create">Create</a></li>
                        <#if userAuth??>
                            <li class="dropdown">
                                <a href="/me">My Account<i class="fa fa-angle-down" aria-hidden="true"></i></a>
                                <ul>
                                    <li><a href="/me/myStyle">My style</a></li>
                                </ul>
                            </li>
                        </#if>
                        <#if userAuth?? && userAuth.role == 'ADMIN'>
                            <li><a href="/admin">Admin</a></li>
                        </#if>
                        <li><a href="/cart">Cart</a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </header>
</#macro>