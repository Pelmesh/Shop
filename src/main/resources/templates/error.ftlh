<#import "parts/common.ftlh" as c>
<#import "parts/header.ftlh" as h>
<#import "parts/pages.ftlh" as p>

<@c.page>
    <@h.page/>
    <section class="page-error">
        <div class="container">
            <h1 class="name">Oops!</h1>
            <h3>Error message</h3>
            <p>Don't worry let's go Home</p>
            <a href="/" class="btn"><span>home</span></a>
        </div>
    </section>
</@c.page>