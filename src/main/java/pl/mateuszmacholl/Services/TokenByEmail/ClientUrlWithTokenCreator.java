package pl.mateuszmacholl.Services.TokenByEmail;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by ggere on 19.02.2018.
 */
@Getter
@Setter
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
}
