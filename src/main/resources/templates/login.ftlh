<#import "parts/common.ftlh" as c>
<#import "parts/header.ftlh" as h>

<@c.page>
    <@h.page/>
    <form class= action="" method="post">
        <div class="container col-9 col-sm-3 mt-5">
            <h1>Sign up</h1>
            <input class="inp-form mt-3 form-control" type="text" name="username" placeholder="Name">
            <input class="inp-form mt-3" type="text" name="password" placeholder="Password">
            <div class="btn-form-cover mt-3">
                <button type="submit" class="btn"><span>Sign in</span></button>
            </div>
        </div>
    </form>
</@c.page>