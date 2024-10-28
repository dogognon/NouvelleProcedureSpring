package com.dogognon.sohliou.kone.security.oauth2login;

import static com.dogognon.sohliou.kone.security.oauth2login.HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME;

import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.dogognon.sohliou.kone.security.exception.BadRequestException;
import com.dogognon.sohliou.kone.security.oauth2login.util.AppProperties;
import com.dogognon.sohliou.kone.security.oauth2login.util.CookieUtils;
import com.dogognon.sohliou.kone.security.oauth2ressourceserver.TokenProvider;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	
 
	private TokenProvider tokenProvider;

    private AppProperties appProperties;

    private HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

    OAuth2AuthenticationSuccessHandler(AppProperties appProperties,//TokenProvider tokenProvider,
                                       HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository) {
        //this.tokenProvider = tokenProvider;
        this.appProperties = appProperties;
        this.httpCookieOAuth2AuthorizationRequestRepository = httpCookieOAuth2AuthorizationRequestRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String targetUrl = determineTargetUrl(request, response, authentication);

        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }
        
        clearAuthenticationAttributes(request, response);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    @Override
	protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        Optional<String> redirectUri = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
                .map(Cookie::getValue);

        if(redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
            throw new BadRequestException("Sorry! We've got an Unauthorized Redirect URI and can't proceed with the authentication"+redirectUri.get());
        }

        String targetUrl = redirectUri.orElse(getDefaultTargetUrl());
        System.out.println("targetUrl "+targetUrl);
        Map<String, String> token = tokenProvider.createJwtToken(authentication);

        return UriComponentsBuilder.fromUriString(targetUrl)
                .queryParam("token", token)
                .build().toUriString();
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
    }

    private boolean isAuthorizedRedirectUri(String uri) {
        URI clientRedirectUri = URI.create(uri);

        return appProperties.getOauth2().getAuthorizedRedirectUris()
                .stream()
                .anyMatch(authorizedRedirectUri -> {
                    // Only validate host and port. Let the clients use different paths if they want to
                    URI authorizedURI = URI.create(authorizedRedirectUri);
                    if(authorizedURI.getHost().equalsIgnoreCase(clientRedirectUri.getHost())
                            && authorizedURI.getPort() == clientRedirectUri.getPort()) {
                        return true;
                    }
                    return false;
                });
    }
}
