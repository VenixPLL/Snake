package pl.venixpll.app;

import lombok.Data;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import pl.venixpll.app.render.RenderApple;
import pl.venixpll.app.render.RenderSnake;

import java.util.concurrent.CopyOnWriteArrayList;

@Data
public class SnakeGameAPP extends BasicGame {

    private RenderSnake snake;
    private CopyOnWriteArrayList<RenderApple> appleList = new CopyOnWriteArrayList<>();
    private GameContainer container;
    private int points;

    private boolean paused = true;
    private boolean gameOver = true;

    public SnakeGameAPP(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        this.container = gameContainer;
        appleList.add(new RenderApple(this,gameContainer.getWidth() / 2,gameContainer.getHeight() / 2));
        snake = new RenderSnake(this,gameContainer.getWidth() / 2,gameContainer.getHeight() / 2);
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        if(!paused) {
            snake.update();
        }
    }

    public void gameOver(){
        this.gameOver = true;
        this.paused = true;
        this.appleList.clear();
        try {
            init(this.container);
        }catch(Exception exc){}
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        snake.draw(graphics);
        appleList.forEach(a -> {
            a.draw(graphics);
        });
        graphics.setColor(Color.white);
        graphics.drawString("Points: " + points,20,20);
        if(paused){
            graphics.drawString("CLICK ESC TO PLAY!",(gameContainer.getWidth() / 2) - graphics.getFont().getWidth("CLICK ESC TO PLAY!") / 2,gameContainer.getHeight() / 2);
        }
    }

    @Override
    public void keyPressed(int key, char c) {
        if(key == Keyboard.KEY_ESCAPE){
            if(gameOver) {
                this.setPoints(0);
                this.gameOver = false;
            }
            paused = !paused;
        }
        if(key == 200){
            snake.switchDir(1);
        }else if(key == 208){
            snake.switchDir(0);
        }else if(key == 203){
            snake.switchDir(2);
        }else if(key == 205){
            snake.switchDir(3);
        }
    }

    public int hashCode(){
        return -1;
    }
}
