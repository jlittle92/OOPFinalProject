package edu.neumont.csc150.finalproject.littlej;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class ImageComponent extends JComponent {

	private Image img;
	
	public ImageComponent(){
	}
	
	public void changeImage(String imagePath, int x, int y) {
		img = new ImageIcon(this.getClass().getResource(imagePath)).getImage();
		this.setBounds(x, y, 2000, 2000);
	//////why won't it let me set the image sizes as follows?
	//////this.setBounds(x, y, img.getWidth(null), img.getHeight(null));
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(img, this.getLocation().x, this.getLocation().y, null);
	}
}
