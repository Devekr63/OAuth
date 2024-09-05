package com.appsdeveloperblog.ws.clients.controller;

import com.appsdeveloperblog.ws.clients.data.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Controller
@RequestMapping("/users")
public class AlbumsController {

//    @Autowired
//    RestTemplate restTemplate;

    @Autowired
    WebClient webClient;

    @Autowired
    OAuth2AuthorizedClientService oauth2AuthorizedClientService;
    @GetMapping("/albums")
    public String getAlbums(Model model, /*Id Token*/ @AuthenticationPrincipal OidcUser principal){

/*      //******** Code before adding WebClient *********
        //Accessing jwt token
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;

        OAuth2AuthorizedClient oauth2Client = oauth2AuthorizedClientService
                .loadAuthorizedClient(oauthToken.getAuthorizedClientRegistrationId(),oauthToken.getName());
        String jwtAccessToken = oauth2Client.getAccessToken().getTokenValue();
        System.out.println("Access Token: "+jwtAccessToken);

        System.out.println("Principal : "+principal);
        String idToken = principal.getIdToken().getTokenValue();
        System.out.println("Id Token: "+idToken);

 */

        String url = "http://localhost:9090/users/albums";
/*      //********* Code before adding Webclient *********
        HttpHeaders header = new HttpHeaders();
        header.add("Authorization", "Bearer "+jwtAccessToken);
        HttpEntity<List<Album>> entity = new HttpEntity<>(header);

        ResponseEntity<List<Album>> responseEntity = restTemplate.exchange(
                url, //url
                HttpMethod.GET, //Header
                entity, //Http entity
                new ParameterizedTypeReference<List<Album>>() {} //response type
        );
        List<Album> albums = responseEntity.getBody();
 */

        List<Album> albums = webClient.get()
            .uri(url)
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<List<Album>>() {})
            .block();

        model.addAttribute("albums", albums);

//        model.addAttribute(
//                "albums",Arrays.asList(
//                        new Album("100", "fire and thunder", "999", "Metal", "localhost://8082/album"),
//                        new Album("100", "Man are wicked", "911", "Metal", "localhost://8082/album")
//                )
//        );
        return "albums";
    }
}
