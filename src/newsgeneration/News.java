package newsgeneration;

// Class news possédant un titre de news et url
public class News {
	
	private String titre;
	private String url;
	

	
	public News(String titre, String url) {
		super();
		this.titre = titre;
		this.url = url;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String toString(){
		return ""+getTitre();
	}
}
