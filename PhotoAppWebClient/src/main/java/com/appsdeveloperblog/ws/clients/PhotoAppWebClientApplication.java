package com.appsdeveloperblog.ws.clients;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class PhotoAppWebClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoAppWebClientApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate(){return new RestTemplate();}

	@Bean
	public WebClient webClient(
			ClientRegistrationRepository clientRegistrationRepository, /*Repository for registered oauth client*/
			OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository //Stores info about clients that have already been authorized
	){
		//This filter will include an access token to every http client request
		ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2 =
				new ServletOAuth2AuthorizedClientExchangeFilterFunction(
					clientRegistrationRepository, oAuth2AuthorizedClientRepository
				);
		oauth2.setDefaultOAuth2AuthorizedClient(true); //auto-detect the client configuration that its need to use based on the client auth object
		return WebClient.builder().apply(oauth2.oauth2Configuration()).build();
	}

}
