package pl.mateuszmacholl.Services.TokenByEmail;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by ggere on 19.02.2018.
 */
@Service
@Scope(value = "prototype")
public class ClientUrlWithTokenCreator {
	private String token;
	private String clientUrl;

	public String createClientUrlWithToken(){
		return clientUrl + "?token=" + token;
	}

	protected ClientUrlWithTokenCreator(){
		this.token = generateToken();
	}

	private String generateToken() {
		return UUID.randomUUID().toString();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getClientUrl() {
		return clientUrl;
	}

	public void setClientUrl(String clientUrl) {
		this.clientUrl = clientUrl;
	}
}
