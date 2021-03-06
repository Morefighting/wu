package com.whp.fallback;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.whp.service.UserClientService;
import com.whp.vo.User;

import feign.hystrix.FallbackFactory;

@Component
public class UserFallbcakFactory implements FallbackFactory<UserClientService> {

	@Override
	public UserClientService create(Throwable throwable) {
		return new UserClientService() {
			
			@Override
			public List<User> list() {
				List<User> list = new ArrayList<>();
				User user = new User();
				user.setUid(8888);
				user.setUsername("[error] user-consume");
				user.setAddress("user list fallback...");
				list.add(user);
				return list;
			}
			
			@Override
			public User get(long uid) {
				User user = new User();
				user.setUid(8888);
				user.setUsername("[error] user-consume");
				user.setAddress("user get fallback...");
				return user;
			}
		};
	}

}
