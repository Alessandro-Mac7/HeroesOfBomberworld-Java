package editor;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import system.AssetsEditor;
import system.ControlEditor;


@SuppressWarnings("serial")
public class EditorPanel extends JPanel implements MouseListener, MouseMotionListener{
	
	/*Vettore di Tile, per memorizzare i punti toccati dal mouse*/
	private Vector<TilePoint> points;
	
	/*Mappa necessaria a salvare o caricare il gioco*/
	private char [][] map;

	public EditorPanel(){
		super();
		init();
		
	}
	
	public void init(){
		/*Setto il pannello*/
		this.setPreferredSize(new Dimension(ControlEditor.DISPLAY_WIDTH, ControlEditor.DISPLAY_HEIGHT));
		this.setFocusable(false);
		
		/*Inizializzo il vettore e la matrice*/
		points = new Vector<TilePoint>();
		map = new char [ControlEditor.SIZE_MAP][ControlEditor.SIZE_MAP];
		resetMap();
		
		/*Aggiungo gli eventi del mouse*/
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public void paintComponent(Graphics g){
		/*Pulisco lo schermo*/
		g.clearRect(0, 0, ControlEditor.DISPLAY_WIDTH, ControlEditor.DISPLAY_HEIGHT);
		
		/*Disegno lo sfondo*/
		drawBackground(g);
		
		/*Disegno il contenuto dell'editor*/
		
		for(int i = 0; i < points.size();i++){
			TilePoint tmp = points.get(i);
			g.drawImage(tmp.getImage(), tmp.getPoint().x*32, tmp.getPoint().y*32, 32, 32, null);
		}
	
		g.dispose();
	}
	
	private void drawBackground(Graphics g) {
		
		g.drawImage(ControlEditor.getBackgroundEditor(), 0, 0, ControlEditor.DISPLAY_WIDTH, ControlEditor.DISPLAY_HEIGHT, null);
		
	}
	
	public void drawMatrixLoaded(){
		
		for (int row = 0; row < ControlEditor.SIZE_MAP; row++) {
			for (int col = 0; col < ControlEditor.SIZE_MAP; col++) {
				switch (map[col][row]) {
					case 'w':
						TilePoint tp = new TilePoint(row, col, AssetsEditor.wallImage, ControlEditor.WALL);
		    			points.add(tp);
						break;
					case 'b':
						TilePoint tp1 = new TilePoint(row, col, AssetsEditor.breakableWallImage, ControlEditor.BLOCK);
		    			points.add(tp1);
						break;
					case 'p':
						TilePoint tp2 = new TilePoint(row, col, AssetsEditor.ninjaPlayer, ControlEditor.PLAYER);
		    			points.add(tp2);
						break;
					case 'm':
						TilePoint tp3 = new TilePoint(row, col, AssetsEditor.manhole[0], ControlEditor.MANHOLE);
		    			points.add(tp3);
						break;
					case 'k':
						TilePoint tp4 = new TilePoint(row, col, AssetsEditor.key[0], ControlEditor.LEVER);
		    			points.add(tp4);
						break;
					case 't':
						TilePoint tp5 = new TilePoint(row, col, AssetsEditor.trap[0], ControlEditor.TRAP);
		    			points.add(tp5);
						break;
					case 'l':
						TilePoint tp6 = new TilePoint(row, col, AssetsEditor.powerUpOff[0], ControlEditor.pLIFE);
		    			points.add(tp6);
						break;
					case 'u':
						TilePoint tp7 = new TilePoint(row, col, AssetsEditor.powerUpOff[1], ControlEditor.pBOMB);
		    			points.add(tp7);
						break;
					case 's':
						TilePoint tp8 = new TilePoint(row, col, AssetsEditor.powerUpOff[2], ControlEditor.pSTAR);
		    			points.add(tp8);
						break;
					case 'v':
						TilePoint tp9 = new TilePoint(row, col, AssetsEditor.powerUpOff[3], ControlEditor.pBOOST);
		    			points.add(tp9);
						break;
					case 'n':
						TilePoint tp10 = new TilePoint(row, col, AssetsEditor.enemyOff, ControlEditor.ENEMY);
		    			points.add(tp10);
						break;
					case 'h':
						TilePoint tp11 = new TilePoint(row, col, AssetsEditor.bossOff, ControlEditor.BOSS);
		    			points.add(tp11);
						break;
					
					default:
						break;
				}
			}
		}
		repaint();
		
	}


	public void setTheme(){
		
		for(int i=0; i < points.size(); i++){
			switch(ControlEditor.getCurrentTheme()){
			case ControlEditor.NINJA:
				if(points.get(i).getType() == ControlEditor.WALL){
					points.get(i).setImage(AssetsEditor.wallImage);
				}
				else if(points.get(i).getType() == ControlEditor.BLOCK)
					points.get(i).setImage(AssetsEditor.breakableWallImage);
				break;
				
			case ControlEditor.TANK:
				if(points.get(i).getType() == ControlEditor.WALL){
					points.get(i).setImage(AssetsEditor.wallTank);
				}
				else  if(points.get(i).getType() == ControlEditor.BLOCK)
					points.get(i).setImage(AssetsEditor.breakableTank);
				break;
				
			case ControlEditor.MAGE:
				if(points.get(i).getType() == ControlEditor.WALL){
					points.get(i).setImage(AssetsEditor.wallMage);
				}
				else  if(points.get(i).getType() == ControlEditor.BLOCK)
					points.get(i).setImage(AssetsEditor.breakableMage);
				break;
				
			}
		}
	}
	
	public void mouseDragged(MouseEvent e) {
		/*Controllo che non vada fuori dalle dimensioni dello schermo*/
		if(e.getX()<ControlEditor.DISPLAY_WIDTH &&
				e.getY()<ControlEditor.DISPLAY_HEIGHT &&
					e.getX()>0 &&
						e.getY()>0)
			mousePressed(e);	
		repaint();
	}
	
	public void mousePressed(MouseEvent e) {
		
		int modifiers = e.getModifiers();
		 
		/*Controllo che lo scaturirsi degli eventi sia tramite il tasto sinistro*/
        if ((modifiers & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK) {
        	/*Controllo che il non sia stato cliccato il bottone Cancella*/
        	if(!ControlEditor.deleteIsActive){
        		int x = e.getX();
    			int y = e.getY();
    			Point p = mapPoint(x,y);
    			TilePoint cp = new TilePoint((int) p.getX(), (int) p.getY(), ControlEditor.getImageEditor(), ControlEditor.type);
    			if(cp.getType()==ControlEditor.PLAYER){
    				if(!limitPlayer()){
        				removeDuplicate(p);
                		points.add(cp);
        			}
        		}
    			else if(cp.getType()==ControlEditor.MANHOLE){
    				if(!limitManhole()){
        				removeDuplicate(p);
                		points.add(cp);
        			}
        		}
    			else if(cp.getType()==ControlEditor.LEVER){
    				if(!limitKey()){
    					removeDuplicate(p);
                		points.add(cp);
        
    				}
    			}
    			else
    			{
    				removeDuplicate(p);
            		points.add(cp);
    			}
    		}
        	else{
        		int x = e.getX();
    			int y = e.getY();
    			Point p = mapPoint(x,y);
    			TilePoint cp = new TilePoint((int) p.getX(), (int) p.getY(), ControlEditor.getImageEditor(), ControlEditor.type);
    			removeDuplicate(p);
    			map[(int)p.getX()][(int)p.getY()]=ControlEditor.EMPTY;
    			points.remove(cp);
        	}
        		
		}
        
       
        //System.out.println(points.size());
		repaint();
	}
	
	public boolean limitPlayer(){
		
		for(int i = 0; i < points.size(); i++){
			if(points.get(i).getType() == 'p' ){
				return true;
			}
		}
		return false;
	}
	
	public boolean limitManhole(){
			
			for(int i = 0; i < points.size(); i++){
				if( points.get(i).getType() == 'm' ){
					return true;
				}
			}
			return false;
		}
		
	public boolean limitKey(){
		
		for(int i = 0; i < points.size(); i++){
			if(points.get(i).getType() == 'k'){
				return true;
			}
		}
		return false;
	}

	
	/*Funzione che trasforma i pixel in punti per Matrice*/
	public Point mapPoint(int x, int y){
		int new_x = x ;
		int new_y = y ;
		new_x = new_x / ControlEditor.GAMEOBJECT_SIZE;
		new_y = new_y / ControlEditor.GAMEOBJECT_SIZE;
		return new Point(new_x, new_y);
		
	}
	
	/*Funzione che rimuove i duplicati, ovvero rimuove i punti in quella posizione per liberare il posto al nuovo punto*/
	private void removeDuplicate(Point p){
		for(int i = 0; i < points.size();i++){
			TilePoint tmp = points.get(i);
			if (tmp.getPoint().equals(p)){
				points.remove(i);
				return;
			}
		}
	}
	
	/*Setta la mappa a EMPTY*/
	public void resetMap(){
		for(int i=0; i < ControlEditor.SIZE_MAP; i++)
			for (int j = 0; j < ControlEditor.SIZE_MAP; j++) 
				map[i][j] = ControlEditor.EMPTY;
		points.clear();
		repaint();
			
	}
	
	/*Riempe la mappa in base ai volori presenti nel vettore*/
	public void fillMap(){
	
	for(int k = 0; k < points.size(); k++){
		for(int i=0; i<ControlEditor.SIZE_MAP; i++){
			for(int j=0; j<ControlEditor.SIZE_MAP; j++){
				if(i==points.get(k).getPoint().getX() && j==points.get(k).getPoint().getY())
					map[j][i]=points.get(k).getType();
			}
		
		}
	}
	
	for(int i=0; i<ControlEditor.SIZE_MAP; i++){
		for(int j=0; j<ControlEditor.SIZE_MAP; j++){
			System.out.print(map[i][j]);
		}
		System.out.println();
	}
	
	}
	
	public void screenShoot(String name){
		
		BufferedImage img = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
		this.paint(img.getGraphics());
		File outputfile = new File(name);
		try {
			ImageIO.write(img, "png", outputfile);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	
	}
	
	public char getMap(int row, int col) {
		return map[row][col];
	}

	public void setMap(int row, int col, char b) {
		map[row][col] = b;
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
