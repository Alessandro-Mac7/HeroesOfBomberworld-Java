package manager;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import graphics.AnimatedBackground;
import graphics.Assets;
import loader.FileManager;
import system.GameConfig;

public class ScoreState extends AbstractGameState {

	private AnimatedBackground bm;
	private Font font;
	
	private String[] topTenList;
	private List <String> scoreList;
	private List <Integer> points;
	
	public ScoreState(GameStateManager gameStateManager){
		this.gameStateManager = gameStateManager;
		init();
	}
	
	@Override
	public void init() {
		bm = new AnimatedBackground();
		bm.setVelocity(0.3, 0);
		font = new Font("Arial", Font.BOLD, 32);

		topTenList = new String[10];
		scoreList = new ArrayList<String>();
		points = new ArrayList<Integer>();
		FileManager fm = new FileManager(scoreList);
		fm.readFile();
		generateSortedScore();
		generateTopTenList();
	}

	public void generateSortedScore(){
		
		ArrayList <String> tmpList = new ArrayList<String>();
		
		for(String line : scoreList){
			tmpList.add(line);
			StringTokenizer st=new StringTokenizer(line," ");
			st.nextToken();
			Integer numero=Integer.parseInt(st.nextToken());
			points.add(numero);
		
		}
		Collections.sort(points);
		Collections.reverse(points);
		scoreList.clear();
		
	
		for(Integer point : points){
		
			for(String tmp : tmpList){
				StringTokenizer st=new StringTokenizer(tmp," ");
				st.nextToken();
				Integer numero=Integer.parseInt(st.nextToken());
				
				if(numero.equals(point))
					scoreList.add(tmp);
				
			}
		}
	}
	
	public void generateTopTenList(){
		
		int index = 0;
		for(String line : scoreList){
			
			if(index < topTenList.length){
				topTenList[index] = line;
			}
			index++;
		}
	}
	
	@Override
	public void update() {
		bm.update();
	}

	@Override
	public void keyPressedEvent(int code) {
		if(code == KeyEvent.VK_ESCAPE)
			this.gameStateManager.setCurrentState(GameConfig.MENU_STATE);
	}

	@Override
	public void draw(Graphics g) {
		
		/* Pulisco lo schermo */
		g.clearRect(0, 0, GameConfig.DISPLAY_WIDTH, GameConfig.DISPLAY_HEIGHT);

		/* Disegno lo sfondo */
		g.drawImage(Assets.backgroundScore, 0, 0, null);

		/* Disegno lo sfondo animato */
		bm.draw(g);
		
		g.drawImage(Assets.titleScoreState, 0, 0, null);
		
		g.setFont(font);
		g.setColor(Color.ORANGE);
		FontMetrics fm = g.getFontMetrics(font);
				
		for(int i = 0; i < topTenList.length; i++ ){
			
			g.drawString(topTenList[i], (GameConfig.DISPLAY_WIDTH - fm.stringWidth(topTenList[i]))/2, 
					(GameConfig.DISPLAY_HEIGHT/5) + 30 * i);
		
		}
		
	}

	@Override
	public void keyReleasedEvent(int code) {}

}
