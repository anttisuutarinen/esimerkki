package paketti;

import javafx.scene.paint.Color;

public class Palikat
{
	static final String[][] s1={
			{".x.",
			 "xxx"},
			{".x.",
			 "xx.",
			 ".x."},
			{".x.",
			 ".xx",
			 ".x."}};
	static final String[][] s2={
			{"xxx"},
			{".x.",
			 ".x.",
			 ".x."}};
	static final String[][] s3={
			{"x..",
			 "xxx"},
			{"..x",
			 "xxx"},
			{"xx.",
			 "x..",
			 "x.."}};
	static final String[][] s4={
			{"xx",
			 "xx"}};
	static final Color värit[]={Color.BLUE,Color.GREEN,Color.RED,Color.YELLOW};
	static final Palikka[] p1={new Palikka(s1[0],värit[0]),new Palikka(s1[1],värit[0]),new Palikka(s1[2],värit[0])};
	static final Palikka[] p2={new Palikka(s2[0],värit[1]),new Palikka(s2[1],värit[1])};
	static final Palikka[] p3={new Palikka(s3[0],värit[2]),new Palikka(s3[1],värit[2]),new Palikka(s3[2],värit[2])};
	static final Palikka[] p4={new Palikka(s4[0],värit[3])};
	static final int MÄÄRÄ=4;

}
