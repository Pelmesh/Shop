<#import "parts/common.ftlh" as c>
<#import "parts/header.ftlh" as h>
<#import "parts/pages.ftlh" as p>
<#import "parts/pagesCount.ftlh" as pc>
<@c.page>
    <@h.page/>

<section class="s-shop">
    <div class="container">
        <div class="shop-sidebar-btn btn"><span>filter</span></div>
        <div class="row">
            <div class="col-12 col-lg-3 shop-sidebar">
                <form action="/filter" method="get">
                <ul class="widgets wigets-shop">
                    <li class="widget">
                        <h5 class="title">T-shirt on page</h5>
                        <@pc.pagerCount url page/>
                    </li>
                    <li class="widget">
                        <h5 class="title">T-shirt name</h5>
                        <ul>
                            <li><input type="text" placeholder="T-shirt name" name="name"
                                <#if filter?? && filter.name??>
                                    value="${filter.name}"
                                </#if>
                                </li>
                        </ul>
                    </li>
                    <li class="widget">
                        <h5 class="title">T-shirt author</h5>
                        <ul>
                            <li><input type="text" placeholder="T-shirt author" name="author"
                                <#if filter?? && filter.author??>
                                    value="${filter.author}"
                                </#if>
                                >
                            </li>
                        </ul>
                    </li>
                    <li class="widget wiget-gender">
                        <h5 class="title">gender</h5>
                        <ul>
                            <li>
                                <p>
                                    <input type="checkbox" name="genderMale"
                                            <#if filter?? && filter.genderMale == true>
                                                checked
                                            </#if>
                                    >
                                    <span>Men’s</span>
                                </p>
                            </li>
                            <li>
                                <p>
                                    <input type="checkbox" name="genderFemale"
                                        <#if filter?? && filter.genderFemale == true>
                                            checked
                                        </#if>
                                    ><span>Women’s</span>
                                </p>
                            </li>
                        </ul>
                    </li>
                    <li class="widget wiget-price">
                        <h5 class="title">price</h5>
                        <div id="slider-range"></div>
                        <div class="amount-cover">
                            <input type="number" class="btn" id="amount-min" min="0" max="1000" name="priceStart">
                            <span>&mdash;</span>
                            <input type="number" class="btn" id="amount-max" min="0" max="1000" name="priceEnd">
                        </div>
                    </li>
                    <li class="widget wiget-price">
                        <button type="submit" class="btn mt-2"><span>Search</span></button>
                    </li>
                    <li class="widget wiget-price">
                        <a href="/" class="">Reset Filters</a>
                    </li>
                </ul>
                </form>
            </div>

            <div class="col-12 col-lg-9 shop-cover">
                <div class="shop-product-cover">

                    <div class="row product-cover block">

                        <#list page.content as tShirt>
                            <div class="col-lg-4 col-md-6 mb-4 mb-lg-0 mt-5">
                                <div class="card shadow-sm border-0 rounded">
                                    <div class="card-body p-0">
                                        <#if tShirt.discount == true>
                                            <div class="sale">Sale</div>
                                        </#if>
                                        <a href="/tshirt/${tShirt.id}">
                                            <img src="${tShirt.url}" alt="" class="w-100 card-img-top">
                                        </a>
                                        <div class="p-4">
                                            <div class="row">
                                                <div class="col-8"><h5 class="mb-0">${tShirt.name}</h5></div>
                                                <#if tShirt.discount == false>
                                                    <div class="col-4 text-right">
                                                        <h5>
                                                            ${tShirt.price}
                                                        </h5>
                                                    </div>
                                                <#else>
                                                    <div class="col-4 text-right">
                                                        <h5>
                                                            ${tShirt.discountPrice}
                                                        </h5>
                                                    </div>
                                                </#if>
                                            </div>
                                            <div class="row">
                                                <div class="col-8">
                                                    <p class="small text-muted">
                                                        <#if tShirt.user??>
                                                            ${tShirt.user.username!}
                                                        <#else>
                                                            NuN
                                                        </#if>
                                                    </p>
                                                </div>
                                                <#if tShirt.discount == true>
                                                    <div class="col-4 text-right">
                                                        <p class="small text-muted">
                                                            <strike>
                                                                ${tShirt.price}
                                                            </strike>
                                                        </p>
                                                    </div>
                                                </#if>
                                            </div>
                                            <a href="/tshirt/${tShirt.id}" class="btn"><span>buy now</span></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </#list>

                    </div>

                    <#if filter??>
                        <#if filter.name??>
                            <#assign url=url+"?name="+filter.name!  >

                        <#else>
                            <#assign url=url+"?name=" >
                        </#if>
                        <#if filter.author??>
                            <#assign  url=url+"&author="+filter.author! >

                        <#else>
                            <#assign url=url+"&author="  >
                        </#if>
                        <#assign
                        url=url+"&priceStart="+filter.priceStart?c
                        url=url+"&priceEnd="+filter.priceEnd?c
                        >
                        <#if filter.genderFemale == true && filter.genderMale == false>
                            <#assign
                            url=url+"&genderFemale=on"
                            >
                        </#if>
                        <#if filter.genderFemale == false && filter.genderMale == true>
                            <#assign
                            url=url+"&genderMale=on"
                            >
                        </#if>
                    </#if>
                    <@p.pager url page/>
                </div>
            </div>
        </div>
    </div>
</section>

</@c.page>

