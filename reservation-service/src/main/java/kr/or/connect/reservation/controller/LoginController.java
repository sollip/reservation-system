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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	String accessToken;
	String refreshToken;
	String code;
	String state;

	@GetMapping("/{url}")
	public String getLogin(HttpSession session, HttpServletRequest request){
		String requestURI=request.getServletPath();
		String nextRequest=requestURI.substring(6,requestURI.length());
		String queryString=request.getQueryString();
		String url=nextRequest+"?"+queryString;

		
		if(session.getAttribute("login")!=null && session.getAttribute("login").equals("loginOK")){
			return "redirect:"+url;
		}else{
			session.setAttribute("nextURL", url);
			
			//session.setAttribute("nextUrl", url);
			//session.setAttribute("productId", id);
			String callbackURL="http://127.0.0.1:8080/login/checkState";
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
			session.setAttribute("state", state);
			return "redirect:"+uri;
		}
	}

	@GetMapping("/checkState")
	public String checkState(HttpSession session,@RequestParam("code") String code, @RequestParam("state") String state){
		String beforState=(String) session.getAttribute("state");
		this.code=code;
		this.state=state;
		if(!beforState.equals(state)){
			return "error";
		}else{
			return "redirect:/login/getToken";
		}
	}

	@GetMapping("/getToken")
	public String getToken(HttpSession session) throws Exception{
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

			if(responseCode==200){
				Map<String,String> response = entity.getBody();
				accessToken=response.get("access_token");
				refreshToken=response.get("refresh_token");
				return "redirect:/login/getProfile";
			}
		} catch (Exception e) {
			System.out.println(e);
		}		
		return "error";
	}

	@GetMapping("/getProfile")
	public String getProfile(HttpSession session){

		URI uriForProfile=UriComponentsBuilder.newInstance()
				.scheme("https")
				.host("openapi.naver.com")
				.path("/v1/nid/me")
				.build().encode().toUri();

		HttpHeaders header=new HttpHeaders();
		header.set("Authorization"," Bearer "+accessToken);
		HttpEntity<String> httpEntity=new HttpEntity<>(header);
		ResponseEntity<Map<String,?>> profileEntity=restTemplate.exchange(uriForProfile, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<Map<String,?>>(){});
		System.out.println(profileEntity.toString());
		Map<String,?> body=profileEntity.getBody();
		Map<String,?> userInfo=(Map<String, ?>) body.get("response");
		User user=new User();

		user.setId(Integer.parseInt((String) userInfo.get("id")));
		user.setNickname((String)userInfo.get("nickname"));
		user.setSnsId((String)userInfo.get("id"));
		user.setSnsProfile((String)userInfo.get("profile_image"));
		user.setUsername((String)userInfo.get("name"));
		user.setEmail((String)userInfo.get("email"));
		
		User loginUser=userService.loginUser(user);
		if(loginUser!=null){
			session.setAttribute("login", "loginOK");
			session.setAttribute("user",loginUser);
			String nextURL=(String) session.getAttribute("nextURL");
			
			return "redirect:"+nextURL;
		}else {
			return "error";
		}
	}
}
