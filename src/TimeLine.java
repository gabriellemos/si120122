import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.LinkedList;


public class TimeLine {

	private LinkedList<Link> linksList;
	private GregorianCalendar dataDoPrimeiroPost;
	private SiteCompartilhado siteMaisCompartilhado;
	private Hashtable<Integer, SiteCompartilhado> sitesCompartilhados;
	
	public TimeLine() {
		linksList = new LinkedList<Link>();
		sitesCompartilhados = new Hashtable<Integer, SiteCompartilhado>();
	}
	
	public Object getNumLinks() {
		return linksList.size();
	}

	public void postLink(String linkUrl) throws LinkInvalidoException {
		Link link = new Link(linkUrl);
		linksList.add(0, link);
		if ( dataDoPrimeiroPost == null )
			dataDoPrimeiroPost = link.getData();
		
		
		SiteCompartilhado buscado = new SiteCompartilhado(linkUrl);
		int hashCode = buscado.hashCode();
		if ( sitesCompartilhados.containsKey( hashCode ) ) {
			sitesCompartilhados.get(hashCode).incrementFrequencia();
		}else{
			sitesCompartilhados.put(hashCode, buscado);
		}
		buscado = sitesCompartilhados.get(hashCode);
		
		if ( siteMaisCompartilhado == null || siteMaisCompartilhado.getFrequencia() < buscado.getFrequencia() ){
			siteMaisCompartilhado = buscado;
		}
	}

	public GregorianCalendar dataUltimaPostagem() {
		GregorianCalendar retorno;
		try{
			retorno = linksList.get(0).getData();
		}catch (Exception e) {
			retorno = null;
		}
		return retorno;
	}

	public long calculaMediaEntrePosts() {
		long retorno = -1;
		if ( linksList.size() >= 2 ) {
			retorno = (dataUltimaPostagem().get(Calendar.SECOND) - dataDoPrimeiroPost.get(Calendar.SECOND))/linksList.size();
		}
		return retorno;
	}

	public String getSiteMaisPostado() {
		String resposta = null;
		if ( siteMaisCompartilhado != null ){
			resposta = siteMaisCompartilhado.toString();
		}
		return resposta;
	}

}
