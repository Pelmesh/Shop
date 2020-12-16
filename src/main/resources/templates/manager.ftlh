<#import "parts/common.ftlh" as c>
<#import "parts/header.ftlh" as h>
<#import "parts/pages.ftlh" as p>

<@c.page>
    <@h.page/>

    <div class="table-responsive container mt-4">
        <#if messageSuccess??>
            <div class="alert alert-success mt-3 text-center col-3" role="alert">
                ${messageSuccess}
            </div>
        </#if>
        <#if messageError??>
            <div class="alert alert-danger mt-3 text-center col-3" role="alert">
                ${messageError}
            </div>
        </#if>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>id</th>
                <th>Customer</th>
                <th>E-mail</th>
                <th>Phone number</th>
                <th>Address</th>
                <th>Username</th>
                <th>Status</th>
                <th>Save</th>
                <th>Info</th>
            </tr>
            </thead>
            <tbody>
            <#list page.content as order>
                <form action="/manager/${order.id}" method="post">
                    <tr>
                        <th scope="row">${order.id}</th>
                        <td>${order.customer!}</td>
                        <td>${order.mail!}</td>
                        <td>${order.phoneNumber!}</td>
                        <td>${order.address!}</td>
                        <td>${order.user.username!}</td>
                        <td>
                            <select class="form-control" name="status">
                                <#if order.status == "PROCESSED">
                                    <option selected>PROCESSED</option>
                                    <option>DELIVERED</option>
                                    <option>COMPLETED</option>
                                <#elseif order.status == "DELIVERED">
                                    <option>PROCESSED</option>
                                    <option selected>DELIVERED</option>
                                    <option>COMPLETED</option>
                                <#elseif order.status == "COMPLETED">
                                    <option>PROCESSED</option>
                                    <option>DELIVERED</option>
                                    <option selected>COMPLETED</option>
                                </#if>
                            </select>
                        </td>
                        <th><button type="submit" class="btn btn-wishlist">save</button></th>
                        <input type="hidden" name="_method" value="PUT">
                        <th><a href="/manager/${order.id}" class="btn">info</a></th>
                    </tr>
                </form>
            </#list>
            </tbody>
        </table>
    </div>


    <@p.pager url page/>

</@c.page>