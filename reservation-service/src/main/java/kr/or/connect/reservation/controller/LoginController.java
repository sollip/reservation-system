package kr.or.connect.reservation.controller;


import java.math.BigInteger;
import java.net.URI;
import java.security.SecureRandom;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import kr.or.connect.reservation.domain.User;
import kr.or.connect.reservation.service.UserService;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	UserService userService;

	String clientId="cYcPk9usu3TaN83mu0va";
	String clientSecret="h1dML3hm4M";
	HttpSession session;

	@GetMapping
	public String getLogin(HttpServletRequest request){
		String responseType="code";
		String callbackURL="http://127.0.0.1:8080/login/loginOK";
		String state=new BigInteger(130,new SecureRandom()).toString(32);
		URI uri=null;
		try{
			uri = UriComponentsBuilder.newInstance()
					.scheme("https")
					.host("nid.naver.com")
					.path("/oauth2.0/authorize")
					.queryParam("response_type", "code")
					.queryParam("client_id", clientId)
					.queryParam("redirect_uri"  , callbackURL)
					.queryParam("state", state)
					.build()
					.encode()
					.toUri();

		}catch(Exception e){
			System.out.println("ERROR 01");
		}
		session=request.getSession();
		session.setAttribute("state", state);
		System.out.println("state1:"+state);
		return "redirect:"+uri;
	}

	@GetMapping("/loginOK")
	public String getToken(HttpServletRequest request) throws Exception{
		String code=request.getParameter("code");
		String state=request.getParameter("state");
		String accessToken=null;
		String refreshToken=null;

		session=request.getSession();
		String stateInSession=(String) session.getAttribute("state");
		if(!stateInSession.equals(state)){
			return "error";
		}
		try {
			URI uri = UriComponentsBuilder.newInstance()
					.scheme("https")
					.host("nid.naver.com")
					.path("/oauth2.0/token")
					.queryParam("grant_type", "authorization_code")
					.queryParam("client_id", clientId)
					.queryParam("client_secret", clientSecret)
					.queryParam("code"  , code)
					.queryParam("state", state)
					.build()
					.encode()
					.toUri();

			//Map<String,String> response = restTemplate.getForObject(uri, Map.class);
			ResponseEntity<Map<String,String>> entity=restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<Map<String,String>>(){});
			int responseCode=entity.getStatusCodeValue();
			if(responseCode==200){ //인증 성공 

				Map<String,String> response = entity.getBody();
				accessToken=response.get("access_token");
				refreshToken=response.get("refresh_token");
				System.out.println("accesstoken:"+accessToken);
				//	https://openapi.naver.com/v1/nid/me
				try{

					URI uriForProfile=UriComponentsBuilder.newInstance()
							.scheme("https")
							.host("openapi.naver.com")
							.path("/v1/nid/me")
							.build().encode().toUri();

					HttpHeaders header=new HttpHeaders();
					header.set("Authorization"," Bearer "+accessToken);
					HttpEntity<String> httpEntity=new HttpEntity<>(header);
					ResponseEntity<Map<String,?>> profileEntity=restTemplate.exchange(uriForProfile, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<Map<String,?>>(){});
					
					Map<String,?> userInfo1=profileEntity.getBody();
					Map<String,?> userInfo=(Map<String, ?>) userInfo1.get("response");
					User user=new User();
					user.setNickname((String)userInfo.get("nickname"));
					user.setSnsId((String)userInfo.get("id"));
					user.setSnsProfile((String)userInfo.get("profile_image"));
					user.setUsername((String)userInfo.get("name"));
					user.setEmail((String)userInfo.get("email"));
					user.setId((String)userInfo.get("id"));
					System.out.println(user.toString());
					if(userService.loginUser(user)!=null){
						//성공~!
						System.out.println("로긴 성공");
						session=request.getSession();
						session.setAttribute("login", "loginOK");
						return "redirect:/myReservation";
						
					}else {
						System.out.println("실패!");
						return "error";
					}
					
					

				}catch(HttpClientErrorException e){
					e.printStackTrace();
					return "error";
				}

			}else{ //인증 실패 
				return "error";
			}				
		} catch (Exception e) {
			System.out.println(e);
			return "error";
		}		
	}
}
