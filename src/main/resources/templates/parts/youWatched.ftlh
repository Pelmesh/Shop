<#macro page>
<#if listPreview??>
<section class="s-header-title mt-5">
    <div class="container">
        <h1>You watched</h1>
    </div>
</section>
<div class="container">
    <div class="row pb-5 mb-4">
        <#list listPreview as tShirt>
            <div class="col-lg-3 col-md-6 mb-4 mb-lg-0 mt-5">
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
</div>
</#if>
</#macro>