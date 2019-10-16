package world.object;

import java.awt.Rectangle;

//gli oggetti che si muovono devono utilizzare questi metodi
public interface IMoveable {

	public void move(int code);

	public boolean collide(Rectangle rect);
}
