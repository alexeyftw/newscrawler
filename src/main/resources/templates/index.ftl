<#import "macros/common.ftl" as c>
<#import "macros/pager.ftl" as p>

<@c.page>
<#list page.content as item>
    <div class="container">
        <h1>
                ${item.title}
        </h1>
        <p>${item.pubDate}</p>
        <#if item.preview?hasContent>
            <div class="preview-image">${item.preview}</div>
        </#if>
        <blockquote class="blockquote-reverse">
            <div>${item.description}</div>
            <footer><a href="${item.link}">Ссылка на источник (${item.source.sourceName})</a></footer>
        </blockquote>
    </div>
    <#sep><hr></#sep>
</#list>
<@p.pager "/news" page/>
</@c.page>
