import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.text.html.HTMLDocument.Iterator;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;


public class testUsuatio {
	
	private Usuario usuario;
	
	@Before
	public void setUp(){
		usuario = new Usuario("Test");
	}
	
	@Test
	public void testPostLink() throws LinkInvalidoException {
		Assert.assertEquals(0, usuario.getNumLinks());
		
		usuario.postLink("http://www.youtube.com/watch?v=lZfnsvIqsfg&list=FL15gLH83RKzABs5WY1WgloQ");
		
		Assert.assertEquals(1, usuario.getNumLinks());
	}
	
	@Test
	public void testPostInvalidLink(){
		String[] invalidLinkList = { "http", "https", "linkInvalidohttps://", "linkInvhttps://alido", 
				"linkInvalidohttp://", "linkInvhttp://alido", "http:/", "https:/"};
		
		for (String string : invalidLinkList) {
			postLinkInvalido(string);
		}
	}

	private void postLinkInvalido( String linkInvalido ){
		Assert.assertEquals(0, usuario.getNumLinks());
		
		try {
			usuario.postLink(linkInvalido);
		} catch (LinkInvalidoException excption) {
			Assert.assertTrue( true );
		} catch (Exception excption) {
			Assert.fail( "Deve retornar uma LinkInvalidoException ! ao invez de " + excption.getClass() );
		}
		
		String mensagem = "O link " + linkInvalido + "deveria ser invalido por nao inicializar por http:// ou https://";
		Assert.assertEquals(mensagem, 0, usuario.getNumLinks());
	}
	
	@Test
	public void testGetDataDaUltimaPostagem() throws LinkInvalidoException{
	
		GregorianCalendar calendar = new GregorianCalendar();
//		System.out.println( calendar.get(Calendar.DAY_OF_MONTH) + " " + calendar.get(Calendar.MONTH) + " " + calendar.get(Calendar.YEAR) );
//		System.out.println( calendar.get(Calendar.SECOND) );
		
		Assert.assertNull("Nao deveria retornar nada", usuario.getDataUltimaPostagem());
		
		usuario.postLink("http://www.youtube.com/watch?v=lZfnsvIqsfg&list=FL15gLH83RKzABs5WY1WgloQ");
		
		Assert.assertEquals( calendar.get(Calendar.SECOND), usuario.getDataUltimaPostagem().get(Calendar.SECOND), 0.1);
	}
	
	@Test
	public void testTempoMedioEntrePostagens() throws LinkInvalidoException, InterruptedException{
		String msg = "Não deve haver media quando tiver menos de 2 posts";
		
		Assert.assertEquals(msg, -1, usuario.mediaEntrePostagem());
		usuario.postLink("http://link1/s");
		
		Assert.assertEquals(msg, -1,  usuario.mediaEntrePostagem());
		usuario.postLink("http://link2/s");
				
		Assert.assertEquals(0, usuario.mediaEntrePostagem(), 0.01);
		
		Thread.sleep(10000);
		usuario.postLink("http://link3/s");
		Assert.assertEquals(3, usuario.mediaEntrePostagem(), 0.01);
		
		Thread.sleep(10000);
		usuario.postLink("http://link4/s");
		Assert.assertEquals(5, usuario.mediaEntrePostagem(), 0.01);
	}
	
	@Test
	public void testSiteMaisComum() throws LinkInvalidoException{
		String[] validLinkList = { "http://youtu.be/6qj9wcbMSMU", "http://vimeo.com/50792317",
				"http://vimeo.com/55769583", "http://youtu.be/UCrnatxo678", "http://youtu.be/0gELMlZuvdc"};
		
		Assert.assertEquals( null, usuario.getSiteMaisPostado() );
		
		usuario.postLink(validLinkList[0]);
		Assert.assertEquals( "youtu.be", usuario.getSiteMaisPostado() );
		
		usuario.postLink(validLinkList[1]);
		Assert.assertEquals( "youtu.be", usuario.getSiteMaisPostado() );
		
		usuario.postLink(validLinkList[2]);
		Assert.assertEquals( "vimeo.com", usuario.getSiteMaisPostado() );
		
		usuario.postLink(validLinkList[3]);
		Assert.assertEquals( "vimeo.com", usuario.getSiteMaisPostado() );
		
		usuario.postLink(validLinkList[4]);
		Assert.assertEquals( "youtu.be", usuario.getSiteMaisPostado() );
	}
	
}
