<#import "parts/common.ftlh" as c>
<#import "parts/header.ftlh" as h>
<#import "parts/pages.ftlh" as p>

<@c.page>
    <@h.page/>

    <div class="table-responsive container mt-4">
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>id</th>
                    <th>Username</th>
                    <th>E-mail</th>
                    <th>Phone number</th>
                    <th>Blocked</th>
                    <th>Role</th>
                    <th>Save</th>
                </tr>
            </thead>
            <tbody>
                <#list page.content as user>
                    <form action="/admin/${user.id}" method="post">
                        <tr>
                            <th scope="row">${user.id}</th>
                            <td>${user.username!}</td>
                            <td>${user.email!}</td>
                            <td>${user.phoneNumber!}</td>
                            <td>
                                <select class="form-control" name="isActive">
                                    <#if user.active == false>
                                        <option value="1">active</option>
                                        <option selected value="0">blocked</option>
                                    <#elseif user.active == true>
                                        <option selected value="1">active</option>
                                        <option value="0">blocked</option>
                                    </#if>
                                </select>
                            </td>
                            <td>
                                <select class="form-control" name="role">
                                    <#if user.role == "USER">
                                        <option selected>USER</option>
                                        <option>ADMIN</option>
                                        <option>MANAGER</option>
                                    <#elseif user.role == "ADMIN">
                                        <option>USER</option>
                                        <option selected>ADMIN</option>
                                        <option>MANAGER</option>
                                    <#elseif user.role == "MANAGER">
                                        <option>USER</option>
                                        <option>ADMIN</option>
                                        <option selected>MANAGER</option>
                                    </#if>
                                </select>
                            </td>
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