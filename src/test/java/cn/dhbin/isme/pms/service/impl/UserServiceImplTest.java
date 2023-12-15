package cn.dhbin.isme.pms.service.impl;

import cn.dhbin.isme.ServeApplicationTests;
import cn.dhbin.isme.pms.domain.dto.LoginTokenDto;
import cn.dhbin.isme.pms.domain.request.LoginRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {"pms.preview=true"})
class UserServiceImplTest extends ServeApplicationTests {

	@Autowired
	private UserServiceImpl userService;

	@Test
	void login() {
		LoginRequest request = new LoginRequest();
		request.setUsername("admin");
		request.setPassword("123456");
		request.setIsQuick(true);
		LoginTokenDto tokenDto = userService.login(request);
		assertNotNull(tokenDto);
		assertNotNull(tokenDto.getAccessToken());
	}

}