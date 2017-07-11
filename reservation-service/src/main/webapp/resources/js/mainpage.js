$(document).ready(function(){
    loadMainPage();
    startInterval();
    $list=$('.visual_img');
    size=$list.children().outerWidth();
    len=$list.children().length;
    autoSlideInterval=null;
    autoSlideTimeout=null
    cnt=1;
    $list.css('width',len*size);
    $('.visual_img').append('<li class="item" style="background-image: url(http://naverbooking.phinf.naver.net/20170119_48/1484802596907hmVDm_JPEG/image.jpg); width: 338px;">'
        +'<a href="#"> <span class="img_btm_border"></span> <span class="img_right_border"></span> <span class="img_bg_gra"></span>'
            +'<div class="event_txt">'
                +'<h4 class="event_txt_tit">뮤지컬-김종욱찾기 네이버 예약</h4>'
                +'<p class="event_txt_adr">대학로 쁘띠첼씨어터</p>'
                +'<p class="event_txt_dsc">네이버 예매시, 손크림/발크림(중 래덤)을 드립니다</p>'
            +'</div>'
        +'</a>'
    +'</li>');
});

  function startInterval(){
      autoSlideInterval = setInterval(autoSlide,2000);
  }

  function autoSlide(){
    cnt=cnt%len;
    $list.animate({'left':-(cnt*size)+'px'});
    cnt++;
    if(cnt==len-1){

    }
  }

  $(document).on('click','.spr_event_pre',function movePre(){
    clearInterval(autoSlideInterval);
    clearTimeout(autoSlideTimeout);
    autoSlideTimeout = setTimeout(startInterval,4000);
    if(cnt!=1){
      $list.animate({'left': '+='+size+'px'});
      cnt--;
    }
  });

  $(document).on('click','.spr_event_nxt',function moveNxt(){
    clearInterval(autoSlideInterval);
    clearTimeout(autoSlideTimeout);
    autoSlideTimeout = setTimeout(startInterval,4000);
    cnt=cnt%len;
    $list.animate({'left':-(cnt*size)+'px'});
    cnt++;
  });

  $(document).on('click','.item .anchor',function categoryClick(event){
    $("a[class='anchor active']").removeClass('active');
    $(this).addClass('active');
  });

  function loadMainPage(){
    $.ajax({
      url : "http://localhost:8080/category/getCategoryList",
      type : "GET",
      dataType:"JSON",
      success : function getCategoryList(data){
        var num=1;
        for(var i=0;i<data.length;i++){
          $(".event_tab_lst").append('<li class="item" data-category="'+(i+2)+'">'
                     +'<a class="anchor"> <span>'+data[i].name+'</span> </a>'
                 +'</li>');
        }
      }
    });
  }
