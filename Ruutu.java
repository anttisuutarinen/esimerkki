package paketti;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Ruutu
{
	private boolean aktiivinen;
	private Color v‰ri;
	private static final int ALKUX=20;
	private static final int ALKUY=40;
	private static final int LEVEYS=20;
	private static final int KORKEUS=25;

	public Ruutu()
	{
		aktiivinen=false;
		v‰ri=Color.BLACK;
	}
	
	public Ruutu(Color v‰ri)
	{
		this.v‰ri=v‰ri;
		aktiivinen=true;
	}
	
	boolean aktiivinen()
	{
		return aktiivinen;
	}
	
	void deaktivoi()
	{
		aktiivinen=false;
	}
	
	void kopioi(Ruutu l‰hde)
	{
		aktiivinen=l‰hde.aktiivinen;
		v‰ri=l‰hde.v‰ri;
	}
	
	void asetaRuutu(boolean aktiivinen,Color v‰ri)
	{
		this.v‰ri=v‰ri;
		this.aktiivinen=aktiivinen;
	}
	
	void piirr‰Ruutu(Pane pane,int x,int y)
	{		
		Color v;
		if(!aktiivinen) v=Color.BLACK;
			else v=v‰ri;
		
		Rectangle rect = new Rectangle(LEVEYS-2,KORKEUS-2);
		rect.setFill(v);
		rect.setLayoutX(ALKUX+x*LEVEYS+1);
	    rect.setLayoutY(ALKUY+y*KORKEUS+1);
		pane.getChildren().add(rect);
		
		//System.out.println("piirr‰Ruutu()");
	}
	
	static void piirr‰Ymp‰rys(Pane pane, int korkeus, int leveys)		//leveys ja korkeus ruudukon leveys ja korkeus ruudukossa
	{
		Rectangle rect = new Rectangle(LEVEYS*leveys,KORKEUS*korkeus);
		rect.setLayoutX(ALKUX);
	    rect.setLayoutY(ALKUY);
	    rect.setFill(Color.TRANSPARENT);
	    rect.setStroke(Color.BLUE);
		pane.getChildren().add(rect);		
	}

	static void asetaPalikka(Palikka p,Ruutu[][] ruudukko,int x,int y,boolean n‰kyv‰)
	{
		Palikka.piirr‰Ruudukolle(p,ruudukko,x,y,n‰kyv‰);
	}
	
	static boolean onkoPalikkaRuudukolla(Palikka p,Ruutu[][] ruudukko,int x,int y)
	{
		return Palikka.onkoPalikkaRuudukolla(p,ruudukko,x,y);
	}
}
