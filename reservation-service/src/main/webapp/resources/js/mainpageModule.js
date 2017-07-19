$(function() {

  var common = (function() {
    var autoSlideInterval;
    var autoSlideTimeout;
    var productCount;
    return {
      autoSlideInterval: null,
      autoSlideTimeout: null,
      productCount: 0
    };
  })();

  var animateModule = (function() {
    var $list = $('.visual_img');
    var size = $list.find('li.item').outerWidth();
    var len = $list.find('li.item').length;
    var cnt = 1;

    $list.css('width', len * size);

    var autoSlide = function() {
      cnt = cnt % len;
      $list.animate({
        'left': -(cnt * size) + 'px'
      });
      cnt++;
      if (cnt === len - 1) {}
    }

    return {
      clearTimer: function(interval, timeout) {
        clearInterval(interval);
        clearTimeout(timeout);
      },

      startTimeout: function(time) {
        common.autoSlideTimeout = setTimeout(animateModule.startInterval, time);
      },

      startInterval: function() {
        common.autoSlideInterval = setInterval(autoSlide, 2000);
      },
      setCnt : function(inputCnt){
        cnt=inputCnt;
      },
      getCnt : function(){
        return cnt;
      },
      len:len,
      size:size,
      $list:$list
    };
  })();

  var getDataModule = (function() {
    //튜터링 받은것 수정
    var templateForProduct = Handlebars.compile( $('#productTemplate').html());
    var templateForCategory = Handlebars.compile($('#categoryTemplate').html());

    var addProduct = function(index, data) {
      var inputdata = {
        productList: data
      };
      add = templateForProduct(inputdata);
      $('div.wrap_event_box').find('.lst_event_box').eq(index).append(add);
    };

    return {
      getCategoryList : function() {
        $.ajax({
          url: "/categories/getCategoryList",
          type: "GET",
          dataType: "JSON",
          success: function appendCategoryList(data) {
            var inputdata;
            var add;
            // var source = $('#categoryTemplate').html();
            // var template = Handlebars.compile(source);
            for (var i = 0; i < data.length; i++) {
              inputdata = {
                categoryId: (i + 2),
                name: data[i].name
              };
              add = templateForCategory(inputdata);
              $(".event_tab_lst").append(add);
            }
          }
        });
      },

      getCategoryCount: function(categoryId) {
        $.ajax({
          url: "/products/" + categoryId,
          type: "GET", //
          dataType: "JSON",
          success: function getProductList(data) {
            $('.event_lst_txt .pink').text(data + '개');
          }
        });
      },

      getProduct: function(limit, offset, reset) {
        var inputData = {};
        inputData.limit = limit;
        inputData.offset = offset;
        inputData.categoryId = $('.anchor.active').closest('li.item').data("category") - 1;
        getDataModule.getCategoryCount(inputData.categoryId);
        $.ajax({
          url: "/products",
          type: "POST",
          dataType: "JSON",
          contentType: "application/JSON",
          data: JSON.stringify(inputData),
          success: function appendProductList(data) {
            var s = 0;
            if (reset) {
              $('.wrap_event_box ul').html('');
              common.productCount = data.length;
            } else {
              common.productCount += data.length;
            }
            console.log("getProduct : data 길이 : " + data.length);
            for (var i = 0; i < data.length; i++) {
              console.log("getProduct : data fileId : " + data[i].fileId);
              s = i % 2;
              switch (s) {
                case 0:
                  addProduct(0, data[i]);
                  break;
                case 1:
                  addProduct(1, data[i]);
                  break;
              }
            }
          }
        });
      }
    };

  })();

  //1.무한스크롤 이벤트
  $(document).scroll(function() {
    var maxHeight = $(document).height();
    var currentScroll = $(window).scrollTop() + $(window).height();
    if (maxHeight <= currentScroll) {
      getDataModule.getProduct(10, common.productCount, false);
    }
  });

  //2.더보기 버튼 이벤트
  $('.more .btn').on('click', function more() {
    getDataModule.getProduct(10, common.productCount, false);
  });

  //3. 프로모션 이전 버튼 이벤트
  $('.spr_event_pre').on('click', function movePrev() {
    animateModule.clearTimer(common.autoSlideInterval, common.autoSlideTimeout);
    var count=nextPrevEvent.movePre(animateModule.len,animateModule.size,animateModule.getCnt(),animateModule.$list);
    animateModule.setCnt(count);
    animateModule.startTimeout(4000);
  });

  //4. 프로모션 다음 버튼 이벤트
  $('.spr_event_nxt').on('click', function moveNext() {
    animateModule.clearTimer(common.autoSlideInterval, common.autoSlideTimeout);
    var count=nextPrevEvent.moveNxt(animateModule.len,animateModule.size,animateModule.getCnt(),animateModule.$list);
    animateModule.setCnt(count);
    animateModule.startTimeout(4000);
  });

  //5. 카테고리 선택 버튼 이벤트
  $('.event_tab_lst.tab_lst_min').on('click', '.item .anchor', function categoryClick(event) {
    $("a[class='anchor active']").removeClass('active');
    $(this).addClass('active');
    common.productCount = 0;
    getDataModule.getProduct(4, common.productCount, true);
  });

  //6. 페이지 로드
  var loadPage = (function() {
    common.productCount = 0;
    getDataModule.getCategoryList();
    getDataModule.getProduct(4, common.productCount, false);
    animateModule.startInterval();
  })();

});
