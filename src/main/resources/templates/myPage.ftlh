<#import "parts/common.ftlh" as c>
<#import "parts/header.ftlh" as h>
<#import "parts/pages.ftlh" as p>

<@c.page>
    <@h.page/>
    <!-- ================ HEADER-TITLE ================ -->
<section class="s-header-title" xmlns="http://www.w3.org/1999/html">
        <div class="container">
            <h1>My account</h1>
            <ul class="breadcrambs">
                <li><a href="/">Home</a></li>
                <li>My account</li>
            </ul>
        </div>
    </section>
    <!-- ============== HEADER-TITLE END ============== -->

    <!--===================== S-NEWS =====================-->
    <section class="s-news">
        <div class="container">
            <div class="row">
                <div class="col-12 col-lg-8 blog-cover" >
                    <#list page.content as order>
                        <div class="post-item-cover">
                            <h4 class="title title-line-left">Order №${order.id}, status ${order.status}</h4>
                            <div class="container">
                                <div class="our-advantages-wrap">
                                    <#list order.items as item>
                                    <div class="our-advantages-item">
                                        <img src="img/advantages-4.svg" alt="icon">
                                        <a href="/tshirt/${item.tshirt.template.id}">
                                            <h5>${item.tshirt.template.name}<br>${item.tshirt.size.size}</h5>
                                        </a>
                                    </div>
                                    </#list>
                                </div>
                            </div>
                        </div>
                    </#list>
                    <@p.pager url page/>
                </div>
                <div class="col-12 col-lg-4 sidebar">
                    <a href="#" class="btn btn-sidebar"><span>About me</span></a>
                    <ul class="widgets">
                        <h5 class="title">About me</h5>
                        <form method="post">
                            <input id="name" type="text" name="name" placeholder="Name" class="mt-3" value="${userAuth.name!}">
                            <input id="phone" type="tel" name="phoneNumber" placeholder="Phone" class="mt-3" value="${userAuth.phoneNumber!}">
                            <input  type="text" name="address" placeholder="Address" class="mt-3" value="${userAuth.address!}">
                            <button type="submit" class="btn btn-form mt-3"><span>send</span></button>
                        </form>
                        <h5 class="mt-3">E-mail  &nbsp;  ${userAuth.email!}</h5>
                    </ul>
                </div>
            </div>
        </div>
    </section>
</@c.page>