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
                <th>Template id</th>
                <th>Name</th>
                <th>User</th>
                <th>Gender</th>
                <th>All see</th>
                <th>price</th>
                <th>discount</th>
                <th>discountPrice</th>
                <th>Save</th>
            </tr>
            </thead>
            <tbody>
            <#list page.content as template>
                <form action="/manager/templates/${template.id}" method="post">
                    <tr>
                        <th scope="row"><a href="/tshirt/${template.id}">${template.id}</a></th>
                        <td>${template.name!}</td>
                        <td><#if template.user??>${template.user.username!}</#if></td>
                        <td>${template.gender!}</td>
                        <td>
                            <select class="form-control" name="allSee">
                                <#if template.allSee == true>
                                    <option selected value="1">for all</option>
                                    <option value="0">for me</option>
                                <#elseif template.allSee == false>
                                    <option value="1">for all</option>
                                    <option selected value="0">for me</option>
                                </#if>
                            </select>
                        </td>
                        <td><input type="number" name="price" value="${template.price}" min="5" max="1000"></td>
                        <td>
                            <select class="form-control" name="discount">
                                <#if template.discount == true>
                                    <option selected value="1">discount</option>
                                    <option value="0">no discount</option>
                                <#elseif template.discount == false>
                                    <option value="1">discount</option>
                                    <option selected value="0">no discount</option>
                                </#if>
                            </select>
                        </td>
                        <td><input type="number" name="discountPrice" value="${template.discountPrice!}" min="5" max="1000"></td>
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