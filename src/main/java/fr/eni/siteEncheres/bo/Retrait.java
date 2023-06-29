package fr.eni.siteEncheres.bo;

public class Retrait {
	
	private String rue;
	private String code_Postal; // variable code_Postal car déjà existante dans la bo Utilisateur (codePostal)
	private String ville;
	
	
	public Retrait() {
	}

	public Retrait(String rue, String code_Postal, String ville) {
		super();
		this.rue = rue;
		this.code_Postal = code_Postal;
		this.ville = ville;
	}

	public String getRue() {
		return rue;
	}


	public void setRue(String rue) {
		this.rue = rue;
	}


	public String getCode_Postal() {
		return code_Postal;
	}


	public void setCode_Postal(String code_Postal) {
		this.code_Postal = code_Postal;
	}


	public String getVille() {
		return ville;
	}


	public void setVille(String ville) {
		this.ville = ville;
	}

	@Override
	public String toString() {
		return "Retrait [rue=" + rue + ", code_Postal=" + code_Postal + ", ville=" + ville + "]";
	}

	
}
