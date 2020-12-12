<#macro pager url page>
    <#if page.getTotalPages() gt 7>
        <#assign
        totalPages = page.getTotalPages()
        pageNumber = page.getNumber() + 1

        head = (pageNumber > 4)?then([1, -1], [1, 2, 3])
        tail = (pageNumber < totalPages - 3)?then([-1, totalPages], [totalPages - 2, totalPages - 1, totalPages])
        bodyBefore = (pageNumber > 4 && pageNumber < totalPages - 1)?then([pageNumber - 2, pageNumber - 1], [])
        bodyAfter = (pageNumber > 2 && pageNumber < totalPages - 3)?then([pageNumber + 1, pageNumber + 2], [])

        body = head + bodyBefore + (pageNumber > 3 && pageNumber < totalPages - 2)?then([pageNumber], []) + bodyAfter + tail
        >
    <#else>
        <#assign body = 1..page.getTotalPages()>
    </#if>
    <div class="container shop-product-cover mb-5">
        <div class="pagination-cover">
            <ul class="pagination">
                <#list body as p>
                    <#if (p - 1) == page.getNumber()>
                        <li class="pagination-item active"><a href="#" tabindex="-1">${p}</a></li>
                    <#elseif p == -1>
                        <li class="pagination-item no-active"><a disabled="disabled"href="#" tabindex="-1">...</a></li>
                    <#else>
                        <li class="pagination-item"><a href="${url}?page=${p - 1}&size=${page.getSize()}" tabindex="-1">${p}</a></li>
                    </#if>
                </#list>
            </ul>
        </div>
    </div>
</#macro>
