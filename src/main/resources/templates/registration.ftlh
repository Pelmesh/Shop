<#import "parts/common.ftlh" as c>
<#import "parts/header.ftlh" as h>

<@c.page>
    <@h.page/>
    <form class= action="" method="post">
        <div class="container col-12 col-sm-8 col-xl-3 col-lg-5 mt-5">
            <h1>Sign up</h1>
            <input class="inp-form mt-3 form-control" type="text" name="username" placeholder="Name"
                   pattern="^[А-ЯА-яA-Za-z0-9]{4,15}$"
                    <#if user??>
                        value="${user.username!}"
                    </#if>
            >
            <span class=" invalid-feedback" id="form__error">Example name: user1245, UserOne</span>
            <div class="invalid-feedback ml-1">
                Please choose a username.
            </div>
            <input class="inp-form mt-3 " type="email" name="email" placeholder="E-mail"
                    <#if user??>
                        value="${user.email!}"
                    </#if>
            >
            <span class="invalid-feedback" id="form__error">Example name: mail@mail.ru</span>
            <input class="inp-form mt-3" type="password" name="password" placeholder="Password"
            pattern="^[A-Za-z0-9]{4,30}$">
            <span class="invalid-feedback" id="form__error">Bad password</span>
            <input class="inp-form mt-3" type="password" name="passwordConfirm" placeholder="Password confirm">
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