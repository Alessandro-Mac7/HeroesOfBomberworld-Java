package editor;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class MyButton extends JButton{
	
	private int height;
	private int width;
	private int x;
	private int y;
	
	public MyButton(Image img, Image img1, int x, int y)
	{
		this.x=x;
		this.y=y;
		this.setIcon(new ImageIcon(img));
		this.setBorder(null);
		this.setFocusable(false);
		this.setBounds(x, y, img1.getWidth(null), img1.getHeight(null));
		this.setContentAreaFilled(false);
		this.setRolloverIcon(new ImageIcon(img1));
	}
	
	public MyButton(Image img, Image img1, int width, int height, int x, int y)
	{
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.setIcon(new ImageIcon(img.getScaledInstance(this.width, this.height, 0)));
		this.setBorder(null);
		this.setFocusable(false);
		this.setBounds(this.x, this.y, width, height);
		this.setContentAreaFilled(false);
		this.setRolloverIcon(new ImageIcon(img1.getScaledInstance(this.width, this.height, 0)));
	}
	
	public void setImage(Image img, Image img1)
	{
		this.setIcon(new ImageIcon(img.getScaledInstance(this.width, this.height, 0)));
		this.setBorder(null);
		this.setFocusable(false);
		this.setContentAreaFilled(false);
		this.setRolloverIcon(new ImageIcon(img1.getScaledInstance(this.width, this.height, 0)));
	}
	
	public void soundClick()
	{
//		if (MenuSetting.getMusicOn())
//		{
//			SoundProvider.playClick();
//		}
	}
}
