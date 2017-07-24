$(function() {
  class Ticket extends eg.Component {
    constructor(count, price, discountRate, age,type, $qty) {
      super();
      this.discountRate = discountRate;
      this.count = count;
      this.price = parseInt(price);
      this.age = age;
      this.type=type;
      this.$qty = $qty;
      this.$count = $qty.find('.count_control_input');
      this.$plusBtn = $qty.find('.ico_plus3');
      this.$minusBtn = $qty.find('.ico_minus3');
      this.$individualPrice = $qty.find('.individual_price');
      this.$totalMoney = $qty.find('.total_price');
      this.totalMoney = 0;
      this.$totalCount=$('.inline_txt.selected span').eq(1);
      this.$input;
    }

    init() {
      this.on("minus", this.minus);
      this.on("plus", this.plus);
      this.on("changeTicket", this.changeTicket);
      this.price = this.price * (1 - this.discountRate);
      this.$input=$('input[data-type='+this.type+']');
    }

    plus() {
      if (this.count === 0) {
        this.$minusBtn.removeClass('disabled');
        this.$count.removeClass('disabled');
        this.$individualPrice.addClass('on_color');
      }
      this.totalMoney = parseInt(this.$totalMoney.text());
      this.$count.val(++this.count);
      var total = this.totalMoney + this.price;
      this.$totalMoney.text(parseInt(total));

      //총 개수 증가
      this.$totalCount.text(++Ticket.prototype.totalCount);

      //
    this.$input.val(this.count);
    }

    minus() {
      if (this.count > 0) {
        if (this.count === 1) {
          this.$minusBtn.addClass('disabled');
          this.$count.addClass('disabled');
          this.$individualPrice.removeClass('on_color');
        }
        this.totalMoney = parseInt(this.$totalMoney.text());
        this.$count.val(--this.count);

        var total = this.totalMoney - this.price;
        this.$totalMoney.text(parseInt(total));
        //총 개수 감소
        this.$totalCount.text(--Ticket.prototype.totalCount);
        this.$input.val(this.count);

      }
    }
  }
  class CheckAll extends eg.Component{
    constructor(){
      super();
      this.username;
      this.tel;
      this.email;
    }
    init(){
      this.on("checkAll",this.checkAll);
      this.on("checkUserInfo",this.checkUserInfo);
    }
    checkAll(){
      console.log("checkAll");
      if(this.checkUserInfo() && this.checkAgreement() && this.checkTicket()  ){
        $('.bk_btn_wrap ').removeClass('disable');
      }else{
        $('.bk_btn_wrap ').addClass('disable');
      }
    }
    checkAgreement(){
      //console.log($('.chk_agree'));
      if($('.chk_agree').is(':checked')){
        console.log("동의완료");
        return true;
      }else{
        //alert("약관에 동의해 주세요.");
        return false;
      }
    }
    checkUserInfo(){
      console.log("checkUserInfo");
      this.username=$('input[name="reservationName"]').val();
      this.tel=$('input[name="reservationTel"]').val();
      this.email=$('input[name="reservationEmail"]').val();
      if(this.username===""){
        //alert("예매자 이름을 입력하세요.");
        return false;
      }
      if(this.tel===""){
        //alert("핸드폰 번호를 입력하세요.");
        return false;
      }
      if(this.username!=="" && this.tel!==""){
        if(this.tel.length ===11 || this.tel.length===10){
          var re = /^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})([0-9]{3,4})([0-9]{4})$/;
          if(!re.test(this.tel)){
            //alert("핸드폰 번호를 확인해 주세요.");
            return false;
          }
        }
        else{
          return false;
        }
      }else{
        //alert("입력된 정보를 확인해 주세요.");
        return false;
      }
      if(this.email!==""){
        var reEmail = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
        if(!reEmail.test(this.email)){
          //alert("이메일을 확인해 주세요.");
          return false;
        }
      }
      return true;
    }
    checkTicket(){;
      if($('.individual_price.on_color').length>0){
        return true;
      }else{
        //alert("티켓을 선택해 주세요.");
        return false;
      }
    }
  }
  Ticket.prototype.totalCount=0;


  var length = $('.ticket_body .qty').length;
  var ticket = new Array(length);
  var $qty = $('.ticket_body .qty');
  var $age = $('.product_amount span');
  var $price = $('.price');

  for (var i = 0; i < length; i++) {
    ticket[i] = new Ticket(0, $price.eq(i).text().replace(',', ''), $price.data("discount"), $age.eq(i).text(),$age.eq(i).data("type"), $qty.eq(i));
    ticket[i].init();
    console.dir(ticket[i]);
  }

  var check=new CheckAll();
  check.init();

  $('.btn_plus_minus.ico_plus3').on('click', function(event) {
    event.preventDefault();
    ticket[$(this).closest('.qty').data("index")].trigger("plus");
    check.trigger("checkAll");
  });

  $('.btn_plus_minus.ico_minus3').on('click', function(event) {
    event.preventDefault();
    ticket[$(this).closest('.qty').data("index")].trigger("minus");
    check.trigger("checkAll");
  });

  $('.btn_agreement').on('click', function(event) {
    event.preventDefault();
    $(this).closest('.agreement').addClass('open');
  });

  $('.chk_agree').on('click',function(event){
    if($(this).is(':checked')){
      check.trigger("checkAll");
    }
  });

  $('.inline_control').on('keyup','input',function(event){
    check.trigger("checkAll");
  });

  $('.bk_btn_wrap').on('click',function(event){

    var queryString = $("form.form_horizontal").serializeArray();
    var data={};
    for(var i=0;i<queryString.length;i++){
      var name=queryString[i].name;
      data[name]=queryString[i].value;
    }
    if($(this).hasClass('disable')){
    }else{
      $.ajax({
        url: "/reservations",
        type:"post",
        data: JSON.stringify(data),
        contentType: " application/json",
        success:function(){
          alert("예약완료!");
        }
      });
    }
  });

});
