package pl.venixpll.app.render;

import lombok.Data;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;
import pl.venixpll.app.SnakeGameAPP;

import javax.vecmath.Vector2d;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class RenderSnake implements IRender {

    private LinkedList<Vector2d> snakePartList = new LinkedList<>();
    private Vector2d head;

    private int direction = 0;
    private SnakeGameAPP app;

    public RenderSnake(SnakeGameAPP app,int posX, int posY){
        this.app = app;
        head = new Vector2d(posX,posY);
        for(int i=0;i<30;i++){
            snakePartList.add(new Vector2d(posX + (5 * i),posY));
        }
    }

    @Override
    public void init(AppGameContainer container) {

    }

    public void move(double x,double y) {
        if (!snakePartList.isEmpty()){
            snakePartList.removeLast();
            head = new Vector2d(x, y);
            snakePartList.addFirst(head);

            if(head.getX() == 0 || head.getY() == 0 || head.getX() < 0 || head.getY() < 0){
                app.gameOver();
            }else if(head.getX() > app.getContainer().getWidth() || head.getY() == app.getContainer().getHeight() || head.getX() > app.getContainer().getWidth() || head.getY() > app.getContainer().getHeight()){
                app.gameOver();
            }

            final AtomicInteger index = new AtomicInteger(0);
            snakePartList.forEach(part -> {
                if(index.addAndGet(1) > 5) {
                    final Rectangle angle = new Rectangle((int) part.getX(), (int) part.getY(), 5, 5);
                    if (angle.intersects(new Rectangle((int) head.getX(), (int) head.getY(), 5, 5))) {
                        System.out.println("XD");
                        app.gameOver();
                    }
                }
            });
        }
    }

    @Override
    public void update() {
        switch(direction){
            case 0:
                move(head.getX(),head.getY() + 5);
                break;
            case 1:
                move(head.getX(),head.getY() - 5);
                break;
            case 2:
                move(head.getX() - 5,head.getY());
                break;
            case 3:
                move(head.getX() + 5,head.getY());
                break;
        }
    }

    @Override
    public void draw(Graphics graphics) {
        snakePartList.forEach(t -> {
            if(t.getX() == head.getX() && t.getY() == head.getY()){
                graphics.setColor(Color.red);
            }else{
                graphics.setColor(Color.white);
            }
            graphics.fillRect((int)t.getX(),(int)t.getY(),5,5);
        });
    }

    public void switchDir(int dir){
        if(this.direction == 0 && dir == 1){
            return;
        }else if(this.direction == 1 && dir == 0){
            return;
        }else if(this.direction == 2 && dir == 3){
            return;
        }else if(this.direction == 3 && dir == 2){
            return;
        }
        this.direction = dir;
    }
}
