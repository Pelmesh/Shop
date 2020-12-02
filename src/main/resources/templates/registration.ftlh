<#import "parts/common.ftlh" as c>
<#import "parts/header.ftlh" as h>

<@c.page>
    <@h.page/>
    <form class= action="" method="post">
        <div class="container col-9 col-sm-3 mt-5">
            <h1>Sign up</h1>
            <input class="inp-form mt-3 form-control is-invalid" type="text" name="username" placeholder="Name">
            <div class="invalid-feedback ml-1">
                Please choose a username.
            </div>
            <input class="inp-form mt-3 " type="email" name="mail" placeholder="E-mail">
            <input class="inp-form mt-3" type="text" name="password" placeholder="Password">
            <input class="inp-form mt-3" type="text" name="subscribe" placeholder="Password confirm">
            <div class="alert alert-danger mt-3 text-center" role="alert">
                User exist!
            </div>
            <div class="btn-form-cover mt-3">
                <button type="submit" class="btn"><span>Sign up</span></button>
            </div>
        </div>
    </form>
</@c.page>