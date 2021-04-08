package m2i.formation.payload.response;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String pseudo;

	public JwtResponse(String accessToken, Long id, String pseudo) {
		this.token = accessToken;
		this.id = id;
		this.pseudo = pseudo;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

}
