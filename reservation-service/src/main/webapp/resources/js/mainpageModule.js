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
    var size = $list.children().outerWidth();
    var len = $list.children().length;
    var cnt = 1;

    $list.css('width', len * size);

    var clearTimer = function(interval, timeout) {
      clearInterval(interval);
      clearTimeout(timeout);
    }

    var startTimeout = function(intervalFunction, time) {
      common.autoSlideTimeout = setTimeout(intervalFunction, time);
    }

    var autoSlide = function() {
      cnt = cnt % len;
      $list.animate({
        'left': -(cnt * size) + 'px'
      });
      cnt++;
      if (cnt == len - 1) {}
    }

    return {
      startInterval: function() {
        common.autoSlideInterval = setInterval(autoSlide, 2000);
      },

      movePre: function() {
        clearTimer(common.autoSlideInterval, common.autoSlideTimeout);
        startTimeout(autoSlide, 4000);
        if (cnt != 1) {
          $list.animate({
            'left': '+=' + size + 'px'
          });
          cnt--;
        }
      },

      moveNxt: function() {
        clearTimer(common.autoSlideInterval, common.autoSlideTimeout);
        startTimeout(autoSlide, 4000);
        cnt = cnt % len;
        $list.animate({
          'left': -(cnt * size) + 'px'
        });
        cnt++;
      }
    };
  })();

  var getDataModule = (function() {
    var source = $('#productTemplate').html();
    var template = Handlebars.compile(source);

    var addProduct = function(index, data) {
      var inputdata = {
        productList: data
      };
      add = template(inputdata);
      $('div.wrap_event_box').find('.lst_event_box').eq(index).append(add);
    };

    var getCategoryListPrivate = function() {
      $.ajax({
        url: "/categories/getCategoryList",
        type: "GET",
        dataType: "JSON",
        success: function appendCategoryList(data) {
          var inputdata;
          var add;
          var source = $('#categoryTemplate').html();
          var template = Handlebars.compile(source);
          for (var i = 0; i < data.length; i++) {
            inputdata = {
              categoryId: (i + 2),
              name: data[i].name
            };
            add = template(inputdata);
            $(".event_tab_lst").append(add);
          }
        }
      });
    }

    return {
      getCategoryList: getCategoryListPrivate,

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
        getCategoryListPrivate(inputData.categoryId);
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
              console.log("getProduct : data name : " + data[i].name);
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
  //3. 프로모션 다음 버튼 이벤트
  $('.spr_event_nxt').on('click', function moveNext() {
    animateModule.moveNxt();
  });
  //4. 프로모션 이전 버튼 이벤트
  $('.spr_event_pre').on('click', function movePrev() {
    animateModule.movePre();
  });
  //5. 카테고리 선택 버튼 이벤트
  $('.event_tab_lst.tab_lst_min').on('click', '.item .anchor', function categoryClick(event) {
    $("a[class='anchor active']").removeClass('active');
    $(this).addClass('active');
    common.productCount = 0;
    getDataModule.getProduct(4, common.productCount, true);
  });

  var loadPage = (function() {
    common.productCount = 0;

    getDataModule.getProduct(4, common.productCount, false);
    getDataModule.getCategoryCount(0);
    getDataModule.getCategoryList();

    animateModule.startInterval();
  })();

});
