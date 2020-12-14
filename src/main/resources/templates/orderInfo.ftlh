<#import "parts/common.ftlh" as c>
<#import "parts/header.ftlh" as h>
<#import "parts/pages.ftlh" as p>

<@c.page>
    <@h.page/>

    <div class="table-responsive container mt-4">
        <h1>Id order ${order.id}</h1>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>T-shirt id</th>
                <th>T-shirt name</th>
                <th>Size</th>
            </tr>
            </thead>
            <tbody>
            <#list order.items as item>
                <tr>
                    <th scope="row"><a href="/tshirt/${item.tshirt.template.id}">${item.tshirt.id}</a></th>
                    <td>${item.tshirt.template.name!}</td>
                    <td>${item.tshirt.size.size!}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>

</@c.page>