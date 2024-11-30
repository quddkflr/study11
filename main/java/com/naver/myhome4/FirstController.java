package com.naver.myhome4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
//@RequestMapping("/")
@RequestMapping("/first-service")
public class FirstController {
	private static final Logger logger = LoggerFactory.getLogger(FirstController.class);

	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to FirstService";
	}

	@GetMapping("/message")
	public String message(@RequestHeader("first-request") String header) {
		logger.info(header);
		return "Hello World in First Service";
	}

	@GetMapping("/check")
	public String check(HttpServletRequest request) {
		logger.info("Server port={}", request.getServerPort());
		return String.format("first service 입니다. 랜덤 포트 번호는 %s 입니다.", request.getServerPort());
	}

	@Value("${token.name}")
	private String configstr;

	@GetMapping("/health_check")
	public String status() {
		logger.info(configstr);
		return String.format("깃에서 읽어온 값:token.name=%s", configstr);
	}
}