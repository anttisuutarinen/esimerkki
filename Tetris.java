package paketti;

import java.util.Random;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Tetris
{
	private Ruudukko ruudukko;
	private int palikkax;
	private int palikkay;
	private int asento;
	private final static int YLƒX=3;
	private Palikka palikka[]=Palikat.p1;
	private int score=0;
	
	public Tetris(int korkeus, int leveys)
	{
		palikkax=YLƒX;
		palikkay=0;
		asento=0;
		ruudukko=new Ruudukko(korkeus,leveys);
	}
	
	void piirr‰Ruudukko(Pane pane)
	{
		ruudukko.piirr‰(pane);
	}
	
	void piirr‰Ymp‰rys(Pane pane)
	{
		ruudukko.piirr‰Ymp‰rys(pane);
	}
	
	void liikutaAlas()
	{
		ruudukko.poistaPalikka(palikka[asento],palikkax,palikkay);
		
		if(ruudukko.onkoTˆrm‰ys(palikka[asento],palikkax,palikkay+1))
		{
			ruudukko.asetaPalikka(palikka[asento],palikkax,palikkay);
			nollaaPalikka();
			ruudukko.asetaPalikka(palikka[asento],palikkax,palikkay);
			return;
		}
		palikkay++;
		ruudukko.asetaPalikka(palikka[asento],palikkax,palikkay);
	}
	
	void nollaaPalikka()
	{
		palikkax=YLƒX;
		palikkay=0;
		asento=0;
		
		palikka=arvoPalikka();
		ruudukko.poistaT‰ydetRivit();	//paikka ok?
		
		if(ruudukko.onkoTˆrm‰ys(palikka[asento],palikkax,palikkay))
		{
			System.out.println("GAME OVER!");
			
			//Main.gl.stop();	//deprecated
			GameLoop.aja=false;
			sanoIkkuna();
		}
	}
	
	@FXML
    void sanoIkkuna()		//luodaan ikkuna ilman Scene Builderia
    {		
    	BorderPane root=new BorderPane();
    	Scene scene=new Scene(root,100,200);
    	Stage stage = new Stage();
    	stage.setScene(scene);
    	Text text = new Text(30,50,"Game Over");
        root.getChildren().add(text);
        stage.show();
    }
	
	Palikka[] arvoPalikka()
	{
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(Palikat.MƒƒRƒ)+1;
		
		switch(randomInt)
		{
			case 1: return Palikat.p1;
			case 2: return Palikat.p2;
			case 3: return Palikat.p3;
			case 4: return Palikat.p4;
		}
		
		System.out.println("virhe! kts. Palikat");
		return null;
	}

	void liikutaVasemmalle()
	{
		ruudukko.poistaPalikka(palikka[asento],palikkax,palikkay);
		
		if(!ruudukko.onkoTˆrm‰ys(palikka[asento],palikkax-1,palikkay))
			palikkax--;

		ruudukko.asetaPalikka(palikka[asento],palikkax,palikkay);
	}
	
	void liikutaOikealle()
	{
		ruudukko.poistaPalikka(palikka[asento],palikkax,palikkay);
		
		if(!ruudukko.onkoTˆrm‰ys(palikka[asento],palikkax+1,palikkay))
			palikkax++;

		ruudukko.asetaPalikka(palikka[asento],palikkax,palikkay);
	}

	void vaihdaAsentoa()
	{
		ruudukko.poistaPalikka(palikka[asento],palikkax,palikkay);
		
		int toinenAsento=asento+1;
		if(toinenAsento>=palikka.length)
			toinenAsento=0;
		
		if(!ruudukko.onkoTˆrm‰ys(palikka[toinenAsento],palikkax,palikkay))
			asento=toinenAsento;

		ruudukko.asetaPalikka(palikka[asento],palikkax,palikkay);
	}

	void uusiPalikka()
	{		
		nollaaPalikka();
		ruudukko.asetaPalikka(palikka[asento],palikkax,palikkay);
	}
	
	void tiputaPalikka()
	{
		ruudukko.poistaPalikka(palikka[asento],palikkax,palikkay);
		
		while(!ruudukko.onkoTˆrm‰ys(palikka[asento],palikkax,palikkay+1))
			palikkay++;

		ruudukko.asetaPalikka(palikka[asento],palikkax,palikkay);
	}
	
	void lis‰‰Scorea(int m‰‰r‰)
	{
		score+=m‰‰r‰;
	}
	
	/*int kerroScore()
	{
		return score;
	}*/

	int oldScore=-1;
	void p‰ivit‰Score(Pane pane)
	{
		if(score!=oldScore)
		{
			oldScore=score;
			Rectangle rect=new Rectangle();
			rect.setX(290);
			rect.setY(50);
			rect.setWidth(100);
			rect.setHeight(30);
			rect.setFill(Color.GRAY);
			pane.getChildren().add(rect);
			Text text = new Text(300,75,"Score: " + score);
			pane.getChildren().add(text);
		}
	}
}
