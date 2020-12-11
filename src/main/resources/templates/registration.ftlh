<#import "parts/common.ftlh" as c>
<#import "parts/header.ftlh" as h>

<@c.page>
    <@h.page/>
    <form class= action="" method="post">
        <div class="container col-12 col-sm-8 col-xl-3 col-lg-5 mt-5">
            <h1>Sign up</h1>
            <input class="inp-form mt-3 form-control is-invalid" type="text" name="username" placeholder="Name"
                    <#if user??>
                        value="${user.username!}"
                    </#if>
            >
            <div class="invalid-feedback ml-1">
                Please choose a username.
            </div>
            <input class="inp-form mt-3 " type="email" name="email" placeholder="E-mail"
                    <#if user??>
                        value="${user.email!}"
                    </#if>
            >
            <input class="inp-form mt-3" type="text" name="password" placeholder="Password">
            <input class="inp-form mt-3" type="text" name="passwordConfirm" placeholder="Password confirm">
            <#if message??>
                <div class="alert alert-danger mt-3 text-center" role="alert">
                    ${message}
                </div>
            </#if>
            <div class="btn-form-cover mt-3">
                <button type="submit" class="btn"><span>Sign up</span></button>
            </div>
        </div>
    </form>
</@c.page>