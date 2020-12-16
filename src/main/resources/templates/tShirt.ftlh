<#import "parts/common.ftlh" as c>
<#import "parts/header.ftlh" as h>
<#import "parts/pages.ftlh" as p>

<@c.page>
    <@h.page/>
    <form method="post" action="/cart/${tShirt.id}">
        <div class="container mt-5">
            <div class="row">
                <div class="col-12 col-md-5">
                    <div class="slider-single-for">
                        <img src="${tShirt.url}">
                    </div>

                </div>
                    <div class="col-12 col-md-7 single-shop-left">
                        <h2 class="title">${tShirt.name}</h2>
                        <p>${tShirt.gender}</p>
                        <div class="single-price mt-3">
                            <#if tShirt.discount == true>
                                <div class="new-price">${tShirt.discountPrice}</div>
                                <div class="old-price">${tShirt.price}</div>
                            <#else>
                                <div class="new-price">${tShirt.price}</div>
                            </#if>
                        </div>
                        <p>${tShirt.description!}</p>
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
                    <#list page.content as comment>
                    <form action="/admin/${comment.id}" method="post">
                        <blockquote>
                            <p>“${comment.message}”</p>
                            <cite>by <a href="#">${comment.author.username!}</a></cite>
                            <#if userAuth?? && userAuth.role == 'ADMIN'>
                                <button type="submit" class="btn btn-delete">Delete</button>
                                <input type="hidden" name="_method" value="DELETE">
                            </#if>
                        </blockquote>
                    </form>
                    </#list>
                </div>
            </div>
        </div>

        <@p.pager url page/>

        <#if userAuth??>
            <div class="reviews-form">
                <h2 class="title">Leave a Comment</h2>
                <ul class="form-cover">
                    <li class="inp-text"><textarea name="message" id="comment" placeholder="Message"></textarea></li>
                </ul>
                <div class="btn-form-cover">
                    <button onclick="sendMessage()" class="btn"><span>submit comment</span></button>
                </div>
            </div>
        </#if>
    </section>
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/sockjs-client/sockjs.min.js"></script>
    <script src="/webjars/stomp-websocket/stomp.min.js"></script>
</@c.page>

<script>
    connect()
    function connect() {
        var socket = new SockJS('/chat-messaging');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function(frame) {
            console.log("connected: " + frame);
            stompClient.subscribe('/chat/messages/${tShirt.id}', function(response) {
                var data = JSON.parse(response.body);
                console.log(data)
                draw(data);
            });
        });
    }
    function disconnect(){
        stompClient.disconnect();
    }
    function draw(data) {
        var div = document.getElementById('comm')
        var blockquote = document.createElement('blockquote');
        var p = document.createElement('p');
        var cite = document.createElement('cite');
        var a = document.createElement('a');
        p.innerHTML = data.message
        a.innerHTML = data.author.username
        cite.innerHTML = "by "
        cite.append(a)
        blockquote.append(p)
        blockquote.append(cite)
        div.append(blockquote);
    }
    function sendMessage(){
        var id= <#if userAuth??>${userAuth.id}</#if>
        var text = $("#comment").val()
        if (!text || /^\s*$/.test(text) || text.length === 0 || !text.trim() || text.length>2048) {
            return false;
        }
        text = text.trim();
        stompClient.send("/app/message/${tShirt.id}",  {}, JSON.stringify({'message': text,
        'id': id}));
    }
</script>
