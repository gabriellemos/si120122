import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Link {

	private String linkURL;
	private GregorianCalendar dataDePostagem;
	
	public Link(String linkUrl) throws LinkInvalidoException {
		// TODO Auto-generated constructor stub
		if ( linkEhValido( linkUrl ) ){
			this.linkURL = linkUrl;
			dataDePostagem = new GregorianCalendar();
		}else
			throw new LinkInvalidoException();
	}

	private boolean linkEhValido(String linkURL) {
		Pattern a = Pattern.compile("http(s)?://(.+)/.+");
		Matcher s = a.matcher(linkURL);
		return s.find();
	}

	public GregorianCalendar getData() {
		return dataDePostagem;
	}

}
