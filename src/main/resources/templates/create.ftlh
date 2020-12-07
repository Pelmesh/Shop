<#import "parts/common.ftlh" as c>
<#import "parts/header.ftlh" as h>

<@c.page>
<@h.page/>
<form action="" method="post">
    <div class="container mt-5">
        <div class="row">
            <div class="col-12 col-md-5">
                <!--===== SLIDER-SINGLE-FOR =====-->
                <div class="slide-single-for">
                    <canvas id="canvas"   width="500px" height="500px"></canvas>
                </div>
                <!--=== SLIDER-SINGLE-FOR END ===-->
            </div>
            <div class="col-12 col-md-7 single-shop-left">
                <h2 class="title">T-shirt name<input class="inp-form" type="text" name="name" placeholder="T-shirt"></h2>
                <h2 class="title">T-shirt color -
                    <input type="color" class="" id="tcolor"/>
                </h2>
                <h2 class="title">Text color -
                    <input type="color" class="" id="favcolor">
                </h2>
                <label>Type</label>
                <select id="gender" onclick="sex()"  class="nice-select" name="gender">
                    <option value="MALE" class="btn">Man</option>
                    <option value="FEMALE" class="btn">Woman</option>
                </select>
                <label>Who can view</label>
                <select class="nice-select" name="allSee">
                    <option value="1" class="btn">All</option>
                    <option value="0" class="btn">Only me</option>
                </select>
                <div class="single-btn-cover">
                    <label class="custom-file-upload btn mr-2">
                        <input type="file" id="file" />
                        <i class="fa fa-cloud-upload"></i>Upload image
                    </label>
                    <button class="btn mr-2" onclick="text()"><span>Add text</span></button>
                    <button class="btn btn-wishlist mr-2" onclick="deleteObject()"><span>Delete selected object</span></button>
                    <button class="btn btn-green mr-2 mt-2" onclick="save()"><span>Save</span></button>
                </div>
                <h2 class="title mt-5">Description
                    <ul class="form-cover">
                        <li class="inp-text"><textarea name="description" placeholder="Description"></textarea></li>
                    </ul>
                </h2>
            </div>
            <input type="hidden" name="url" id="url" required></input>
            <input type="submit" id="post" style="display: none"></input>
        </div>
    </div>
</form>
</@c.page>




<style type="text/css">

</style>



<script type="text/javascript">
    var canvas = new fabric.Canvas('canvas');
    document.getElementById('file').addEventListener("change", function (e) {
        var file = e.target.files[0];
        var reader = new FileReader();
        reader.onload = function (f) {
            var data = f.target.result;
            fabric.Image.fromURL(data, function (img) {
                var oImg = img.set({left: 100, top: 100, angle: 0, opacity: 0.85}).scale(0.9);
                canvas.add(oImg).renderAll();
                var a = canvas.setActiveObject(oImg);
                var dataURL = canvas.toDataURL({format: 'png', quality: 0.8});
            });
        };
        reader.readAsDataURL(file);
    });

    canvas.setBackgroundImage('https://res.cloudinary.com/itr/image/upload/v1555868664/white_zkntjk.png', canvas.renderAll.bind(canvas));
    canvas.setOverlayImage('https://res.cloudinary.com/itr/image/upload/v1555868663/fon_qkezpa.png', canvas.renderAll.bind(canvas));
    var inp=document.querySelector("#file")
    inp.value="";

    function addText(){
        var textbox = new fabric.Textbox('This is a Textbox', {
            left: 250,
            top: 250,
            fill: '#000',
            strokeWidth: 2,
            opacity: 0.85,
            stroke: "#000"
        });
        canvas.add(textbox);
    }

    function deleteObject(){
        var activeObject = canvas.getActiveObject();
        canvas.remove(activeObject);
    }

    var red = new fabric.Rect({
        top: 0,
        left: 0,
        width: 500,
        height: 500,
        opacity: 0.70,
        fill: 'white' });
    canvas.add(red);
    canvas.item(0).selectable = false;


    var tscolor = document.getElementById("tcolor");
    var theInput = document.getElementById("favcolor");
    tscolor.addEventListener("input", function() {
        var theColor = tscolor.value;
        var activeObject = canvas.getActiveObject();
        var d=canvas.item(0);
        d.set({
            fill: theColor,
            stroke:theColor
        });
        canvas.renderAll();
    }, true);
    theInput.addEventListener("input", function() {
        var theColor = theInput.value;
        var activeObject = canvas.getActiveObject();
        activeObject.set({
            fill: theColor,
            stroke:theColor
        });
        canvas.renderAll();
    }, true);


    function save(){
        var svg = canvas.toSVG();
        console.log(canvas.toSVG())
        document.getElementById('url').value= svg;
        document.getElementById('post').click();
    }

    function sex(){
        if($('#gender').val()=='MALE'){
            canvas.setOverlayImage('https://res.cloudinary.com/itr/image/upload/v1555868663/fon_qkezpa.png', canvas.renderAll.bind(canvas));
            canvas.setBackgroundImage('https://res.cloudinary.com/itr/image/upload/v1555868664/white_zkntjk.png', canvas.renderAll.bind(canvas));
        }else{
            canvas.setOverlayImage('https://res.cloudinary.com/itr/image/upload/v1556117619/Wfon_rmnrqt.png', canvas.renderAll.bind(canvas));
            canvas.setBackgroundImage('https://res.cloudinary.com/itr/image/upload/v1556117619/Wwhite_uzpgsm.png', canvas.renderAll.bind(canvas));
        }
    }

</script>
