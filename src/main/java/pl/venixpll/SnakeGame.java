package pl.venixpll;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import pl.venixpll.app.SnakeGameAPP;

public class SnakeGame {

    public static void main(String[] args) throws SlickException {
        final AppGameContainer appgc = new AppGameContainer(new SnakeGameAPP("Snake"));
        appgc.setDisplayMode(800,600,false);
        appgc.setTargetFrameRate(60);
        appgc.setVSync(true);
        appgc.setShowFPS(false);
        appgc.start();
    }

}
