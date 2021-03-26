package m2i.formation.payload.request;

import java.util.Set;

import javax.validation.constraints.*;
 
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String pseudo;
    
    private Set<String> role;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String motDePasse;
  
    public String getPseudo() {
        return pseudo;
    }
 
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
 
    public String getMotDePasse() {
        return motDePasse;
    }
 
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    
    public Set<String> getRole() {
      return this.role;
    }
    
    public void setRole(Set<String> role) {
      this.role = role;
    }
}
