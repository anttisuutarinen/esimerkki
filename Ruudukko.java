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
	
	void piirr�(Pane pane)
	{
		System.out.println("piirr�: korkeus=" + korkeus + ", leveys=" + leveys);
		
		for(int y=0;y<korkeus;y++)
			for(int x=0;x<leveys;x++)
				ruudukko[y][x].piirr�Ruutu(pane,x,y);
	}
	
	void piirr�Ymp�rys(Pane pane)
	{
		Ruutu.piirr�Ymp�rys(pane,korkeus,leveys);
	}

	void asetaPalikka(Palikka p,int x,int y)
	{
		Ruutu.asetaPalikka(p,ruudukko,x,y,true);
	}
	
	void poistaPalikka(Palikka p,int x,int y)
	{
		Ruutu.asetaPalikka(p,ruudukko,x,y,false);
	}
	
	boolean onkoT�rm�ys(Palikka p,int x,int y)
	{
		if(Ruutu.onkoPalikkaRuudukolla(p,ruudukko,x,y))
			return true;
		
		return false;
	}
	
	void poistaT�ydetRivit()
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
				System.out.println("******************* L�ytyi t�ysi rivi!");
				Main.peli.lis��Scorea(10);
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
