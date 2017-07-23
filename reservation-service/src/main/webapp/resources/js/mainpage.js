$(function() {
  $list = $('.visual_img');
  size = $list.children().outerWidth();
  len = $list.children().length;

  $list.css('width', len * size);

  loadMainPage();
  loadProductList();
  loadCategoryCount(0);
  startInterval();

  $('.event_tab_lst.tab_lst_min').on('click', '.item .anchor', function categoryClick(event) {
    $("a[class='anchor active']").removeClass('active');
    $(this).addClass('active');
    setProduct(4,0,true);
  });

  $('.spr_event_pre').on('click', function movePre() {
    clearInterval(autoSlideInterval);
    clearTimeout(autoSlideTimeout);
    autoSlideTimeout = setTimeout(startInterval, 4000);
    if (cnt != 1) {
      $list.animate({
        'left': '+=' + size + 'px'
      });
      cnt--;
    }
  });

  $('.spr_event_nxt').on('click', function moveNxt() {
    clearInterval(autoSlideInterval);
    clearTimeout(autoSlideTimeout);
    autoSlideTimeout = setTimeout(startInterval, 4000);
    cnt = cnt % len;
    $list.animate({
      'left': -(cnt * size) + 'px'
    });
    cnt++;
  });

  $('.more .btn').on('click', function more(){
     setProduct(10,productCount,false);
  });

});


var autoSlideInterval = null;
var autoSlideTimeout = null;

var cnt = 1;
var offset = 0;
var limit = 0;
var productCount=0;
///////////////
$(document).scroll(function (){
  var maxHeight = $(document).height();
  var currentScroll=$(window).scrollTop()+$(window).height();
  if(maxHeight<=currentScroll){
    setProduct(10,productCount,false);
  }
});

////////////
function loadMainPage() {
  $.ajax({
    url: "/categories/getCategoryList",
    type: "GET",
    dataType: "JSON",
    success: function getCategoryList(data) {
      var inputdata;
      var add;
      var source=$('#categoryTemplate').html();
      var template=Handlebars.compile(source);
      for (var i = 0; i < data.length; i++) {
        inputdata ={
          categoryId:(i+2),
          name:data[i].name
        };
        add=template(inputdata);
        $(".event_tab_lst").append(add);
      }
    }
  });
}
/////////
function loadProductList() {
  setProduct(4,0,false);
}
/////////
function loadCategoryCount(categoryId){
  $.ajax({
    url: "/products/productCount?categoryId="+categoryId,
    type: "GET",
		dataType: "JSON",
    success: function getProductList(data) {
      $('.event_lst_txt .pink').text(data+'개');
    }
  });
}

//////////////////////////////////////
function addProduct(index,data){
  var inputdata;
  var add;
  var source=$('#productTemplate').html();
  var template=Handlebars.compile(source);
  inputdata ={
    fileName:data.fileName,
    saveFileName:data.saveFileName,
    title:data.name,
    place:data.placeName,
    description:data.description
  };
  add=template(inputdata);
   $('div.wrap_event_box').find('.lst_event_box').eq(index).append(add);
}

/////////////////////////
function startInterval() {
  autoSlideInterval = setInterval(autoSlide, 2000);
}

///////////////////////////////////////
function autoSlide() {
  cnt = cnt % len;
  $list.animate({
    'left': -(cnt * size) + 'px'
  });
  cnt++;
  if (cnt == len - 1) {

  }
}
/////////////////////////////////////////////
function setProduct(limit,offset,reset){
  var data = {};
  data.limit = limit;
  data.offset = offset;
  data.categoryId = $('.anchor.active').closest('li.item').data("category") - 1;
  loadCategoryCount(data.categoryId);
  $.ajax({
    url: "/products?limit="+limit+"&offset="+offset+"$categoryId="+categoryId,
    type: "GET",
    dataType: "JSON",
    success: function getProductList(data) {
      var s=0;
      if(reset){
        $('.wrap_event_box ul').html('');
        productCount=data.length;
      }else{
        productCount+=data.length;
      }
      for (var i = 0; i < data.length; i++) {
        s=i%2;
        switch (s) {
          case 0:
              addProduct(0,data[i]);
              break;
          case 1:
              addProduct(1,data[i]);
              break;
        }
      }
    }
  });
}
