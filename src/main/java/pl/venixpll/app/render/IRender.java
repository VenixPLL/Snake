package pl.venixpll.app.render;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Graphics;

public interface IRender {

    void init(final AppGameContainer container);
    void update();
    void draw(final Graphics graphics);

}
