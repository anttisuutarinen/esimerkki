package paketti;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Ruudukko
{
	private int korkeus;
	private int leveys;
	private Ruutu[][] ruudukko;
	
	public Ruudukko(int korkeus, int leveys)
	{
		this.korkeus=korkeus;
		this.leveys=leveys;
		
		ruudukko=new Ruutu[korkeus][leveys];
		for(int i=0;i<korkeus;i++)
			ruudukko[i]=new Ruutu[leveys];
		
		for(int y=0;y<korkeus;y++)
			for(int x=0;x<leveys;x++)
				ruudukko[y][x]=new Ruutu();
	}
	
	void piirrä(Pane pane)
	{
		System.out.println("piirrä: korkeus=" + korkeus + ", leveys=" + leveys);
		
		for(int y=0;y<korkeus;y++)
			for(int x=0;x<leveys;x++)
				ruudukko[y][x].piirräRuutu(pane,x,y);
	}
	
	void piirräYmpärys(Pane pane)
	{
		Ruutu.piirräYmpärys(pane,korkeus,leveys);
	}

	void asetaPalikka(Palikka p,int x,int y)
	{
		Ruutu.asetaPalikka(p,ruudukko,x,y,true);
	}
	
	void poistaPalikka(Palikka p,int x,int y)
	{
		Ruutu.asetaPalikka(p,ruudukko,x,y,false);
	}
	
	boolean onkoTörmäys(Palikka p,int x,int y)
	{
		if(Ruutu.onkoPalikkaRuudukolla(p,ruudukko,x,y))
			return true;
		
		return false;
	}
	
	void poistaTäydetRivit()
	{
		for(int y=korkeus-1;y>=0;y--)
		{
			int a=0;
			for(int x=0;x<leveys;x++)
				if(ruudukko[y][x].aktiivinen())
					a++;
			
			if(a==leveys)
			{
				poistaRivi(y);
				y++;
				System.out.println("******************* Löytyi täysi rivi!");
				Main.peli.lisääScorea(10);
			}
		}
	}
	
	private void poistaRivi(int rivi)
	{
		for(int y=rivi;y>0;y--)
			for(int x=0;x<leveys;x++)
				ruudukko[y][x].kopioi(ruudukko[y-1][x]);
		
		for(int i=0;i<leveys;i++)
			ruudukko[0][i].deaktivoi();
	}
}
