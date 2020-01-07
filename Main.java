package paketti;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application
{
	static Tetris peli=new Tetris(20,10);
	static Pane pane;
	static GameLoop gl;
	
	public static void main(String[] args)
	{
        launch(args);
        System.out.println("Done");
	}
	
    @Override public void start(Stage primaryStage)
    {
		System.out.println("Tetris");
		
		//Tetris peli=new Tetris(20,10);
		
		/*Pane*/ pane = new Pane();
		Scene scene = new Scene(pane, 500, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		peli.piirr‰Ymp‰rys(pane);
		peli.piirr‰Ruudukko(pane);
		
		scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) ->
		{
			if(key.getCode()==KeyCode.ENTER)
			{
				peli.liikutaAlas();
				peli.piirr‰Ruudukko(pane);
				peli.p‰ivit‰Score(Main.pane);
		    }
			
			if(key.getCode()==KeyCode.LEFT)
			{
				peli.liikutaVasemmalle();
				peli.piirr‰Ruudukko(pane);
		    }
			
			if(key.getCode()==KeyCode.RIGHT)
			{
				peli.liikutaOikealle();
				peli.piirr‰Ruudukko(pane);
		    }
			
			if(key.getCode()==KeyCode.DOWN)
			{
				peli.vaihdaAsentoa();
				peli.piirr‰Ruudukko(pane);
		    }
			
			if(key.getCode()==KeyCode.Q)
			{
				peli.uusiPalikka();
				peli.piirr‰Ruudukko(pane);
		    }
			
			if(key.getCode()==KeyCode.SPACE)
			{
				peli.tiputaPalikka();
				peli.piirr‰Ruudukko(pane);
				peli.p‰ivit‰Score(Main.pane);
		    }
		});

		gl=new GameLoop();
		gl.start();
    }
}

//https://community.oracle.com/thread/2238235?tstart=0
//java.lang.IllegalStateException: Not on FX application thread
//http://stackoverflow.com/questions/17850191/why-am-i-getting-java-lang-illegalstateexception-on-javafx
//http://stackoverflow.com/questions/25579693/javafx-snake-thread-sleep-does-not-load-fxml
class GameLoop extends Thread implements Runnable
{
	static boolean aja=true;
	
	@Override public void run()
	{
		while(aja)		//on olemassa myˆs isInterrupted(): http://stackoverflow.com/questions/6410721/how-to-stop-a-thread-that-is-running-forever-without-any-use
		{
			try {
 				Thread.sleep(1000);
			}
			catch(Exception e) {}
			
			Platform.runLater(new Runnable()
			{
				@Override public void run()
    			{
					System.out.println("Platform t‰ss‰");
					
					if(!aja) return;
						
					Main.peli.liikutaAlas();
		  			Main.peli.piirr‰Ruudukko(Main.pane);
		  			Main.peli.p‰ivit‰Score(Main.pane);
    			}
			});
		}
	}
}
