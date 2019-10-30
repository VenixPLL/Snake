package pl.venixpll.app.render;

import lombok.RequiredArgsConstructor;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import pl.venixpll.app.SnakeGameAPP;

import javax.vecmath.Vector2d;
import java.util.Random;

@RequiredArgsConstructor
public class RenderApple implements IRender {

    private final SnakeGameAPP app;
    private final int posX,posY;

    @Override
    public void init(AppGameContainer container) {

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics graphics) {
        final Random r = new Random();
        final Rectangle snakeHead = new Rectangle((int)app.getSnake().getHead().getX(),(int)app.getSnake().getHead().getY(),5,5);
        if(snakeHead.intersects(new Rectangle(posX,posY,10,10))){
            final Vector2d last = app.getSnake().getSnakePartList().getLast();
            for(int i=0;i<2;i++) {
                app.getSnake().getSnakePartList().add(new Vector2d(last.getX() + (5 * i), last.getY()));
            }
            app.getAppleList().remove(this);
            app.getAppleList().add(new RenderApple(app,r.nextInt(app.getContainer().getWidth() - 20) + 10,r.nextInt(app.getContainer().getHeight() - 20) + 10));
            app.setPoints(app.getPoints() + 1);
        }
        graphics.setColor(Color.red);
        graphics.fillRoundRect(posX,posY,10,10,5,20);
    }
}