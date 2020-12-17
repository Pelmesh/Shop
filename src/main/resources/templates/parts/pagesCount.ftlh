<#macro pagerCount url page>
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
    <ul class="pagination">
        <#list [3, 6, 9, 12, 18, 24] as c>
            <#if c == page.getSize()>
                <li class="pagination-item no-active"><a disabled="disabled" href="#" tabindex="-1">${c}</a></li>
            <#else>
                <li class="pagination-item"><a href="${url}?page=${page.getNumber()}&size=${c}" tabindex="-1">${c}</a></li>
            </#if>
        </#list>
    </ul>
</#macro>
