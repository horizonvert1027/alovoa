package com.nonononoki.alovoa;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.nonononoki.alovoa.entity.User;
import com.nonononoki.alovoa.entity.user.UserWebPush;
import com.nonononoki.alovoa.repo.ConversationRepository;
import com.nonononoki.alovoa.repo.UserRepository;
import com.nonononoki.alovoa.repo.UserWebPushRepository;
import com.nonononoki.alovoa.service.AuthService;
import com.nonononoki.alovoa.service.CaptchaService;
import com.nonononoki.alovoa.service.NotificationService;
import com.nonononoki.alovoa.service.RegisterService;
import com.nonononoki.alovoa.service.UserService;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class NotificationTest {

	@Autowired
	private RegisterService registerService;

	@Autowired
	private CaptchaService captchaService;

	@Value("${app.age.min}")
	private int minAge;

	@Value("${app.message.size}")
	private int maxMessageSize;

	@MockBean
	private AuthService authService;

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ConversationRepository conversationRepo;

	@Autowired
	private UserWebPushRepository userWebPushRepository;

	@Test
	public void test() throws Exception {

		List<User> testUsers = UserTest.getTestUsers(captchaService, registerService);
		User user1 = testUsers.get(1);

		Mockito.when(authService.getCurrentUser()).thenReturn(user1);

		UserWebPush wp = new UserWebPush();
		notificationService.subscribe(wp);

		Assert.assertEquals(userWebPushRepository.count(), 1);

		UserTest.deleteAllUsers(userService, authService, captchaService, conversationRepo, userRepo);

	}

}