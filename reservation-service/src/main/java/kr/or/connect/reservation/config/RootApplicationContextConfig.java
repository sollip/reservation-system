package kr.or.connect.reservation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages={"kr.or.connect.reservation.dao","kr.or.connect.reservation.service"})
@Import({DbConfig.class}) //DbConfig를 설정한다.
public class RootApplicationContextConfig { //서비스단에 대한 정보들

}
