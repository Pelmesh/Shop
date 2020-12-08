<#import "parts/common.ftlh" as c>
<#import "parts/header.ftlh" as h>

<@c.page>
    <@h.page/>
    <section class="s-header-title">
        <div class="container">
            <h1>Cart</h1>
            <ul class="breadcrambs">
                <li><a href="index.html">Home</a></li>
                <li>Cart</li>
            </ul>
        </div>
    </section>
<#assign price = 0 >
<#if cartList??>
    <div class="container">
        <div class="row pb-5 mb-4">
            <#list cartList as tShirt>
                <div class="col-lg-3 col-md-6 mb-4 mb-lg-0 mt-5">
                    <div class="card shadow-sm border-0 rounded">
                        <div class="card-body p-0">
                            <#if tShirt.tshirt.template.discount == true>
                                <#assign price = price + tShirt.tshirt.template.discountPrice>
                                <div class="sale">Sale</div>
                            </#if>
                            <a href="/tshirt/${tShirt.tshirt.id}">
                                <img src="${tShirt.tshirt.template.url}" alt="" class="w-100 card-img-top">
                            </a>
                            <div class="p-4">
                                <div class="row">
                                    <div class="col-8"><h5 class="mb-0">${tShirt.tshirt.template.name}</h5></div>
                                    <#if tShirt.tshirt.template.discount == false>
                                        <#assign price = price + tShirt.tshirt.template.price>
                                        <div class="col-4 text-right">
                                            <h5>
                                                ${tShirt.tshirt.template.price}
                                            </h5>
                                        </div>
                                    <#else>
                                        <div class="col-4 text-right">
                                            <h5>
                                                ${tShirt.tshirt.template.discountPrice}
                                            </h5>
                                        </div>
                                    </#if>
                                </div>
                                <div class="row">
                                    <div class="col-8">
                                        <p class="small text-muted">
                                            <#if tShirt.tshirt.user??>
                                                ${tShirt.tshirt.user.username!}
                                            <#else>
                                                NuN
                                            </#if>
                                        </p>
                                    </div>
                                    <#if tShirt.tshirt.template.discount == true>
                                        <div class="col-4 text-right">
                                            <p class="small text-muted">
                                                <strike>
                                                    ${tShirt.tshirt.template.price}
                                                </strike>
                                            </p>
                                        </div>
                                    </#if>
                                </div>
                                <div class="row">
                                    <div class="col-8"><a href="single-shop.html" class="btn"><span>buy now</span></a></div>
                                    <div class="col-4 text-right">size <h5>${tShirt.size.size}</h5></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>
    <form action="/order" method="post">
        <section class="s-find-bike">
            <div class="container">
                <div class="find-bike-form">
                    <h2 class="title">${price}</h2>
                    <ul class="form-cover">
                        <li class="inp-name"><input id="name" type="text" name="customer" placeholder="Name"></li>
                        <li class="inp-phone"><input id="phone" type="tel" name="phoneNumber" placeholder="Phone"></li>
                        <li class="inp-email"><input id="email" type="email" name="mail" placeholder="E-mail"></li>
                        <li class="inp-email"><input  type="text" name="address" placeholder="Address"></li>
                    </ul>
                    <div class="btn-form-cover">
                        <button id="#submit" type="submit" class="btn"><span>Buy</span></button>
                    </div>
                </div>
            </div>
        </section>
    </form>
</#if>
</@c.page>