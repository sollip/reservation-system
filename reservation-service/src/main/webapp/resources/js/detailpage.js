$(function() {

  console.log($('img'));
  $('._detail').lazyload();
  var $listMain = $('.visual_img');

  var size = $listMain.find('li.item').outerWidth();
  var len = $listMain.find('li.item').length;
  var count = 1;
  var $imageCount = $('.figure_pagination .num').eq(0);

  //1. 오시는길 클릭
  $('.info_tab_lst').on('click', '._path', function() {
    event.preventDefault(); // a태그 이벤트 막기..
    $("a[class='anchor active']").removeClass('active');
    $(this).find('.anchor').addClass('active');
    $('.detail_area_wrap').addClass('hide');
    $('.detail_location').removeClass('hide');
  });

  //2. 상세 정보 클릭
  $('.info_tab_lst').on('click', '._detail', function() {
    event.preventDefault();
    $("a[class='anchor active']").removeClass('active');
    $(this).find('.anchor').addClass('active');
    $('.detail_area_wrap').removeClass('hide');
    $('.detail_location').addClass('hide');
  });

  //네이버 지도
  var myaddress = $('.store_addr_bold').text();
  naver.maps.Service.geocode({
    address: myaddress
  }, function(status, response) {
    if (status !== naver.maps.Service.Status.OK) {
      return alert(myaddress + '의 검색 결과가 없거나 기타 네트워크 에러');
    }
    var result = response.result;
    var myaddr = result.items[0].point.x + "," + result.items[0].point.y;
    var placeName = $('.store_addr_bold').data('id');
    console.log("placeName:" + placeName);
    $('.store_map.img_thumb').attr('src', "https://openapi.naver.com/v1/map/staticmap.bin?clientId=cYcPk9usu3TaN83mu0va&url=http://127.0.0.1:8080/&crs=EPSG:4326&center=" + myaddr + "&level=10&w=340&h=150&baselayer=default&markers=" + myaddr);
    //네이버 지도로 이동설정...
    $('.store_location').attr('href', 'http://map.naver.com/?lng=' + result.items[0].point.x + '&pinTitle=' + placeName + '&level=2&pinType=SITE&lat=' + result.items[0].point.y + '&enc=utf8;');
  });

  //3. 이미지 다음 버튼 클릭
  $('.nxt_inn').on('click', function() {
    count = nextPrevEvent.moveNxt(len, size, count, $listMain);
    if (count === 1) {
      $('.btn_prev i').addClass('off');
    } else if (count === 2) {
      $('.btn_prev i').removeClass('off');
    }
    $imageCount.text(count);
  });

  //4. 이미지 이전 버튼 클릭
  $('.prev_inn').on('click', function() {
    count = nextPrevEvent.movePre(len, size, count, $listMain);
    if (count === 1) {
      $('.btn_prev i').addClass('off');
    } else if (count === 2) {
      $('.btn_prev i').removeClass('off');
    }
    $imageCount.text(count);
  });

  //5.펼쳐보기 버튼 클릭
  $('.bk_more._open').on('click', function() {
    console.log("펼처보기 클릭");
    $('.store_details').removeClass('close3');
    $('.bk_more._open').hide();
    $('.bk_more._close').show();
  });

  //6. 접기 버튼 클릭
  $('.bk_more._close').on('click', function() {
    console.log("접기 클릭");
    $('.store_details').addClass('close3');
    $('.bk_more._close').hide();
    $('.bk_more._open').show();

  });
  ////////////////////////////////////////////////////////////////////////////////////////
  //7.터치 이벤트
  var obj;
  var gapX;
  var startX = {};
  var endX = {};
  var touchStart = function($list) {
    console.log("event startX: " + startX);
    obj = $list.offset();
  };
  var touchMove = function(cnt, $list) {
    gapX = endX - startX;
    console.log("gapX:" + gapX);
    if (gapX > 0) { //왼쪽->오른쪽 이전 이미지
      if (cnt !== 1) {
        $list.attr('style', 'left:' + (obj.left + (gapX)) + 'px');
      }
    }
    if (gapX <= 0) { //다음 이미지 왜 90을 안빼면 넘길떄 길이 90만큼 이전 이미지가 보이는지 찾아보기
      $list.attr('style', 'left:' + (obj.left + (gapX) - 90) + 'px');
    }
  };
  var touchEnd = function(size, cnt, len, $list) {
    if (Math.abs(gapX) > (size / 2)) { //슬라이드 넘어가기
      if (cnt === 1 && gapX > 0) {} else {
        if (gapX > 0) {
          console.log("-->이전으로 넘어가기 cnt:" + cnt + "len:" + len + "size:" + size);
          cnt = nextPrevEvent.movePre(len, size, cnt, $list);
          //$imageCount.text(cnt);
        } else {

          cnt = nextPrevEvent.moveNxt(len, size, cnt, $list);
          console.log("-->다음으로 넘어가기 cnt:" + cnt + "len:" + len);

          //$imageCount.text(cnt);
        }
      }
    } else { //돌아오기
      $list.attr('style', 'left:' + (-1) * (size * (cnt - 1)) + 'px');
    }
    gapX = 0;
    return cnt;
  };

  //  onEvent();

  var offEvent = function() {
    $('.container_visual').off('touchstart', '.visual_img');
    $('.container_visual').off('touchmove', '.visual_img');
    $('.container_visual').off('touchend', '.visual_img');
  }

  var onEvent = function() {
    $('.container_visual').on('touchstart', '.visual_img', function(event) {
      event.preventDefault();
      startX = event.originalEvent.changedTouches[0].pageX;
      touchStart($listMain);
    });
    $('.container_visual').on('touchmove', '.visual_img', function(event) {
      event.preventDefault();
      endX = event.originalEvent.changedTouches[0].pageX;
      touchMove(count, $listMain);
    });
    $('.container_visual').on('touchend', '.visual_img', function(event) {
      event.preventDefault();
      count = touchEnd(size, count, len, $listMain);
      $imageCount.text(count);
    });
  }

  onEvent();

  // popup//////////////////////
  var $listPopup = $('.visual_img.popup');
  var sizePopup;
  var lenPopup;
  var cntPopup;
  var $imageCountInPopup = $('#photoviwer .figure_pagination .num').eq(0);

  var popupEventInit = function() {
    //var obj
    $('#photoviwer .container_visual_popup').on('touchstart', '.visual_img.popup', function(event) {
      event.preventDefault();
      startX = event.originalEvent.changedTouches[0].pageX;
      touchStart($listPopup);
    });
    //var gapX;
    $('#photoviwer .container_visual_popup').on('touchmove', '.visual_img.popup', function(event) {
      endX = event.originalEvent.changedTouches[0].pageX;
      touchMove(cntPopup, $listPopup);
    });
    $('#photoviwer .container_visual_popup').on('touchend', '.visual_img.popup', function(event) {
      cntPopup = touchEnd(sizePopup, cntPopup, lenPopup, $listPopup);
      $imageCountInPopup.text(cntPopup);
    });
  }

  var template = Handlebars.compile($('#imageTemplate').html());

  $('.list_item').on('click', '.thumb', function() {
    var commentId = $(this).closest('.list_item').data("id");
    $.ajax({
      url: "/products/commentImages?commentId=" + commentId,
      type: "GET",
      dataType: "JSON",
      success: function(data) {
        var add;
        $('.num.off.popup span').text(data.length);
        for (var i = 0; i < data.length; i++) {
          var inputdata = {
            imageList: data[i]
          };
          add += template(inputdata);
        }
        $('#photoviwer .visual_img').append(add);
        sizePopup = $listPopup.find('li.item.popup').outerWidth();
        lenPopup = $listPopup.find('li.item.popup').length;
        popupWindowByImagePopup();
        popupEventInit();
      }
    });
  });

  function popupWindowByImagePopup() {
    cntPopup = 1;
    $listPopup.attr('style', 'left: 0px');
    var height = $(document).height();
    $('#photoviwer').css({
      'width': 414 + 'px',
      'height': height
    });
    offEvent();
    $('#photoviwer').show();
    $imageCountInPopup.text(cntPopup);
  }

  $('#exit').on('click', function() {
    onEvent();
    $('#photoviwer .item.popup').remove();
    $('#photoviwer').hide();

  });

  //예약하기 버튼 클릭
  $('.bk_btn').on('click',function(){
    console.log("클릭됨");
    if($(this).find("span").data("sales")){
      var id=$('.main').data("id");
      console.log("dkdlel:"+id);
      location.href="/products/reservation?id="+id;
    }else{
      console.log("아님");
    }
  });
});
