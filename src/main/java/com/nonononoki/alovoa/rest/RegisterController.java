package com.nonononoki.alovoa.rest;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nonononoki.alovoa.model.AlovoaException;
import com.nonononoki.alovoa.model.RegisterDto;
import com.nonononoki.alovoa.service.RegisterService;

@RestController
@RequestMapping("/")
public class RegisterController {

	@Autowired
	private RegisterService registerService;

	@PostMapping(value = "/register", consumes = "application/json")
	public void register(@RequestBody RegisterDto dto)
			throws NoSuchAlgorithmException, AlovoaException, MessagingException, IOException {
		registerService.register(dto);
	}

	@PostMapping(value = "/register-oauth", consumes = "application/json")
	public String registerOauth(@RequestBody RegisterDto dto) throws MessagingException, IOException, AlovoaException,
			NumberFormatException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException,
			NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException {
		registerService.registerOauth(dto);
		return "profile";
	}
}
