<#import "parts/common.ftlh" as c>
<#import "parts/header.ftlh" as h>
<#import "parts/pages.ftlh" as p>

<@c.page>
    <@h.page/>

    <div class="table-responsive container mt-4">
        <#if messageError??>
            <div class="alert alert-danger mt-3 text-center col-3" role="alert">
                ${messageError}
            </div>
        </#if>
        <#if messageSuccess??>
            <div class="alert alert-success mt-3 text-center col-3" role="alert">
                ${messageSuccess}
            </div>
        </#if>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Tshirt id</th>
                <th>Template id</th>
                <th>Template name</th>
                <th>Size</th>
                <th>Count</th>
                <th>Save</th>
            </tr>
            </thead>
            <tbody>
            <#list page.content as tShirt>
                <form action="/manager/tshirts/${tShirt.id}" method="post">
                    <tr>
                        <th scope="row">${tShirt.id}</th>
                        <td>${tShirt.template.id!}</td>
                        <td>${tShirt.template.name!}</td>
                        <td>${tShirt.size.size!}</td>
                        <td><input type="number" name="count" value="${tShirt.count!}" min="0" max="1000"></td>
                        <th><button type="submit" class="btn btn-wishlist">save</button></th>
                        <input type="hidden" name="_method" value="PUT">
                    </tr>
                </form>
            </#list>
            </tbody>
        </table>
    </div>


    <@p.pager url page/>

</@c.page>