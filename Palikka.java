package paketti;

import javafx.scene.paint.Color;

public class Palikka
{
	private Color v�ri;
	private int[][] muoto;		//j�rjestyksess� korkeus,leveys

	public Palikka(String[] muoto, Color v�ri)
	{
		this.v�ri=v�ri;
		
		this.muoto=new int[muoto.length][muoto[0].length()];
		              
		for(int y=0;y<muoto.length;y++)
		{
			this.muoto[y]=new int[muoto[0].length()];

			for(int x=0;x<muoto[0].length();x++)
				this.muoto[y][x]=muoto[y].charAt(x)=='.'? 0:1;
		}
	}
	
	static void piirr�Ruudukolle(Palikka p, Ruutu[][] ruudukko, int px, int py,boolean n�kyv�)
	{
		for(int y=0;y<p.muoto.length;y++)
			for(int x=0;x<p.muoto[0].length;x++)
				if(p.muoto[y][x]!=0)
				{
					if(!n�kyv�) ruudukko[py+y][px+x].asetaRuutu(false,Color.BLACK);
						else ruudukko[py+y][px+x].asetaRuutu(true,p.v�ri);
				}
	}
	
	//palauttaa true jos t�rm�ys
	static boolean onkoPalikkaRuudukolla(Palikka p, Ruutu[][] ruudukko, int px, int py)
	{
		for(int y=0;y<p.muoto.length;y++)
			for(int x=0;x<p.muoto[0].length;x++)
				if(p.muoto[y][x]!=0)
				{
					//huom. yl�reuna?
					if(px+x<0 || px+x>=ruudukko[0].length || py+y>=ruudukko.length)
						return true;
					
					if(ruudukko[py+y][px+x].aktiivinen())
						return true;
				}

		return false;
	}
}
