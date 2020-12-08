<#import "parts/common.ftlh" as c>
<#import "parts/header.ftlh" as h>

<@c.page>
    <@h.page/>
    <form method="post" action="/cart/${tShirt.id}">
        <div class="container mt-5">
            <div class="row">
                <div class="col-8 col-md-5">
                    <!--===== SLIDER-SINGLE-FOR =====-->
                    <div class="slider-single-for">
                        <img src="${tShirt.template.url}">
                    </div>
                    <!--=== SLIDER-SINGLE-FOR END ===-->
                </div>
                    <div class="col-12 col-md-7 single-shop-left">
                        <h2 class="title">${tShirt.template.name}</h2>
                        <p>${tShirt.template.gender}</p>
                        <div class="single-price mt-3">
                            <#if tShirt.template.discount == true>
                                <div class="new-price">${tShirt.template.discountPrice}</div>
                                <div class="old-price">${tShirt.template.price}</div>
                            <#else>
                                <div class="new-price">${tShirt.template.price}</div>
                            </#if>
                        </div>
                        <p>${tShirt.template.description!}</p>
                        <div class="wheel-size mt-3">
                            <label>Size:</label>
                            <ul>
                                <#list sizes as size>
                                <li onclick="getSize('${size.size}')">${size.size}</li>
                                </#list>
                            </ul>
                            <input type="hidden" value="M" name="size" id="size"/>
                        </div>
                        <div class="single-btn-cover">
                            <button type="submit" class="btn btn-buy-now"><span>Add to Cart</span></button>
                        </div>
                    </div>
            </div>
        </div>
    </form>

    <section class="s-contacts page-contacts">
        <div class="container">
            <div class="blog-cover">
                <div class="post-item-cover" id="comm">
                    <#list comments as comment>
                    <blockquote>
                        <p>“${comment.message}”</p>
                        <cite>by <a href="#">Kerry Ashman</a></cite>
                    </blockquote>
                    </#list>
                </div>
            </div>
        </div>
        
        <form action="/comment/${tShirt.id}" method="post">
        <div class="reviews-form">
            <h2 class="title">Leave a Comment</h2>
            <form action="/">
                <ul class="form-cover">
                    <li class="inp-text"><textarea name="message" placeholder="Message"></textarea></li>
                </ul>
                <div class="btn-form-cover">
                    <button type="submit" class="btn"><span>submit comment</span></button>
                </div>
            </form>
        </div>
        </form>
    </section>


</@c.page>

<style>
blockquote{
    background-color: #fafafa;
}
</style>

<script>
    function getSize(size){
        document.getElementById('size').value= size;
    }

    window.onload = function () {
        comments();
    }
    function comments() {
        setInterval(loadComment, 2000);
    }
    function loadComment() {
        $.ajax({
            type: "GET",
            url:"/comment/${tShirt.id}",
            data: ${tShirt.id},
            success: function (rezult) {
                $('#comm').empty();
                $(rezult).appendTo('#comm');
            }
        })
    }
</script>
