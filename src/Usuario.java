import java.util.GregorianCalendar;


public class Usuario {

	private TimeLine timeLine; 
	
	public Usuario(String nome) {
		// TODO Auto-generated constructor stub
		timeLine = new TimeLine();
	}

	public Object getNumLinks() {
		return timeLine.getNumLinks();
	}

	public void postLink(String linkUrl) throws LinkInvalidoException {
		timeLine.postLink( linkUrl );
	}

	public GregorianCalendar getDataUltimaPostagem() {
		return timeLine.dataUltimaPostagem();
	}

	public long mediaEntrePostagem() {
		return timeLine.calculaMediaEntrePosts();
	}

	public String getSiteMaisPostado() {
		return timeLine.getSiteMaisPostado();
	}

}
