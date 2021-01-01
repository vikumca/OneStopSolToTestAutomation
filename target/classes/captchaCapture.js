function addListener() {
        $(document).bind('DOMSubtreeModified',function(){
            if($('#captcha').length!=0){
                $(this).unbind('DOMSubtreeModified');
                var src = $('#captcha').attr("src");
                //$('#captcha').attr("src", 'caught.png');
               //alert(src);
               return src;
        }
        });
    }