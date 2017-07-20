var naver_id_login=new naver_id_login("cYcPk9usu3TaN83mu0va", "http://localhost:8080/myReservation");
var state = naver_id_login.getUniqState();
  	naver_id_login.setButton("white", 2,40);
  	naver_id_login.setDomain("http://localhost:8080/");
  	naver_id_login.setState(state);
  	naver_id_login.setPopup();
  	naver_id_login.init_naver_id_login();
