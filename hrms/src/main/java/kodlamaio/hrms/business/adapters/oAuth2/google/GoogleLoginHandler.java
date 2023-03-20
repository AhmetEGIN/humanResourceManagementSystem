package kodlamaio.hrms.business.adapters.oAuth2.google;

import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import kodlamaio.hrms.business.adapters.oAuth2.Constants;

@Service
public class GoogleLoginHandler  {

//	public DataResult<UserGoogleRequest> getUser(OAuth2AuthenticationToken auth2AuthenticationToken) {
//		var result = auth2AuthenticationToken.getPrincipal().getAttributes();
//		UserGoogleRequest request = new UserGoogleRequest(result.get("email").toString(),
//				(boolean) result.get("verified_email"), result.get("name").toString(),
//				result.get("given_name").toString(), result.get("family_name").toString(),
//				result.get("picture").toString());
//
//		return new SuccessDataResult<UserGoogleRequest>(request);

//	}
//	private GoogleTokenResponse googleTokenResponse;
	
	public GoogleLoginHandler() throws java.io.IOException  {
//		GoogleTokenResponse googleTokenResponse = new GoogleAuthorizationCodeTokenRequest (
//				new NetHttpTransport(), 
//				JacksonFactory.getDefaultInstance(),  
//				"https://oauth2.googleapis.com/token", 
//				Constants.GOOGLE_CLIENT_ID, 
//				Constants.GOOGLE_CLIENT_SECRET,
//				Constants.GOOGLE_GRANT_TYPE, 
//				Constants.GOOGLE_REDIRECT_URI).execute(); 
//		this.googleTokenResponse = googleTokenResponse;
	}

//	public GoogleTokenResponse getGoogleTokenResponse() {
//		return googleTokenResponse;
//	}


}
