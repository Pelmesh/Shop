<#import "parts/common.ftlh" as c>
<#import "parts/header.ftlh" as h>

<@c.page>
    <@h.page/>
    <form class= action="" method="post">
        <div class="container col-12 col-sm-8 col-xl-3 col-lg-5 mt-5">
            <h1>Sign up</h1>
            <#if RequestParameters.logout??>
                <div class="alert alert-success mt-3 text-center" role="alert">
                    You have been logged out
                </div>
            </#if>
            <input class="inp-form mt-3 form-control" type="text" name="username" placeholder="Name">
            <input class="inp-form mt-3" type="text" name="password" placeholder="Password">
            <#if RequestParameters.error??>
                <div class="alert alert-danger mt-3 text-center" role="alert">
                    Invalid username or password
                </div>
            </#if>
            <div class="btn-form-cover mt-3">
                <button type="submit" class="btn"><span>Sign in</span></button>
            </div>
        </div>
    </form>
</@c.page>