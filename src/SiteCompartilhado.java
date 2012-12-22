import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SiteCompartilhado {

	private String linkUrl;
	private int frequencia;
	public SiteCompartilhado(String linkUrl) throws LinkInvalidoException {
		if ( isValidLink( linkUrl ) ) {
			this.linkUrl = extractLink(linkUrl);
			frequencia = 1;
		}else
			throw new LinkInvalidoException();
	}

	private String extractLink(String linkUrl2) {
		Pattern q = Pattern.compile("(?!http:)//.+");
		Matcher w = q.matcher(linkUrl2);
		boolean e = w.find();
		String finalString = w.group().substring(2);
		return finalString.substring(0, finalString.indexOf("/"));
	}

	private boolean isValidLink(String linkUrl2) {
		Pattern a = Pattern.compile("http(s)?://(.+)/.+");
		Matcher s = a.matcher(linkUrl2);
		return s.find();
	}

	public void incrementFrequencia() {
		frequencia++;
	}

	public int getFrequencia() {
		return frequencia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((linkUrl == null) ? 0 : linkUrl.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return linkUrl;
	}
	
	
}
