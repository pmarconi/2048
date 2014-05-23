package main.java;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MainWindow extends JFrame{
	
	private static final long serialVersionUID = 1L;

	private Game2048State model;
	
	private JPanel contentPanel;
	
	private Controller controller;
	
	private boolean keyLock;
	
	int sizeRect = 125;
	
	public void setController(Controller c){
		controller = c;
	}
	
	public void setModel(Game2048State s){
		model = s;
	}
	
	public class ContentPanel extends JPanel{
		
		private static final long serialVersionUID = 1L;
		private BufferedImage n0;
	    private BufferedImage n2;
	    private BufferedImage n4;
	    private BufferedImage n8;
	    private BufferedImage n16;
	    private BufferedImage n32;
	    private BufferedImage n64;
	    private BufferedImage n128;
	    private BufferedImage n256;
	    private BufferedImage n512;
	    private BufferedImage n1024;
	    private BufferedImage n2048;
	    
	    public ContentPanel(){
	    	loadImages();
	    	this.setBackground(Color.darkGray);
	    }
		
		public void paint(Graphics g){
			super.paint(g);
	        drawBoard(g);
	    }
		
		public void drawBoard(Graphics g){
	        for(int i= 0; i < 4; i++){
	            for(int j= 0; j < 4; j++){
	            	if(model.get(i, j) == 0){
	            		drawPiece2(g,n0, i, j);
	            	}else if (model.get(i, j) == 2){
	                    drawPiece2(g,n2, i, j);
	                }else if (model.get(i, j) == 4){
	                    drawPiece2(g,n4, i, j);
	                }else if (model.get(i, j) == 8){
	                    drawPiece2(g,n8, i, j);
	                }else if (model.get(i, j) == 16){
	                    drawPiece2(g,n16, i, j);
	                }else if (model.get(i, j) == 32){
	                    drawPiece2(g,n32, i, j);
	                }else if (model.get(i, j) == 64){
	                    drawPiece2(g,n64, i, j);
	                }else if (model.get(i, j) == 128){
	                    drawPiece2(g,n128, i, j);
	                }else if (model.get(i, j) == 256){
	                    drawPiece2(g,n256, i, j);
	                }else if (model.get(i, j) == 512){
	                    drawPiece2(g,n512, i, j);
	                }else if (model.get(i, j) == 1024){
	                    drawPiece2(g,n1024, i, j);
	                }else if (model.get(i, j) == 2048){
	                    drawPiece2(g,n2048, i, j);
	                }
	            }
	        }
	    }
		
		private void loadImages() {
	        try {																
	            n0 = ImageIO.read(new File("images/n0.png"));
	            n2 = ImageIO.read(new File("images/n2.png"));
	            n4 = ImageIO.read(new File("images/n4.png"));
	            n8 = ImageIO.read(new File("images/n8.png"));
	            n16 = ImageIO.read(new File("images/n16.png"));
	            n32 = ImageIO.read(new File("images/n32.png"));
	            n64 = ImageIO.read(new File("images/n64.png"));
	            n128 = ImageIO.read(new File("images/n128.png"));
	            n256 = ImageIO.read(new File("images/n256.png"));
	            n512 = ImageIO.read(new File("images/n512.png"));
	            n1024 = ImageIO.read(new File("images/n1024.png"));
	            n2048 = ImageIO.read(new File("images/n2048.png"));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
		
		private void drawPiece2(Graphics g, BufferedImage img, int row, int col) {
			int begini = 10, beginj = 10;
	        g.drawImage(img,(begini+(col)*(sizeRect+10)),(beginj+(row)*(sizeRect+10)),null);
	    }
		
	}
	
	public MainWindow(){
		this.setTitle("2048");
		setResizable(false);
		addMenuBar();
		contentPanel = new ContentPanel();
		this.add(contentPanel); 
		this.setVisible(true);
		this.setSize(555,610);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addKeyListener(new java.awt.event.KeyAdapter() {  
            public void keyReleased(java.awt.event.KeyEvent e) {  
            	Integer key = e.getKeyCode();
                if (!keyLock){
                	if (key == KeyEvent.VK_LEFT && model.isMovePossibleLeft()) {
                        model.moveLeft();
                        contentPanel.repaint();
                    	keyLock = true;
                        executeCpuMove();
                    }else if (key == KeyEvent.VK_RIGHT && model.isMovePossibleRight()) {
                        model.moveRight();
                        contentPanel.repaint();
                    	keyLock = true;
                        executeCpuMove();
                    }else if (key == KeyEvent.VK_UP && model.isMovePossibleUp()) {
                        model.moveUp();
                        contentPanel.repaint();
                    	keyLock = true;
                        executeCpuMove();
                    }else if (key == KeyEvent.VK_DOWN && model.isMovePossibleDown()) {
                        model.moveDown();
                        contentPanel.repaint();
                    	keyLock = true;
                        executeCpuMove();
                    }
                }
            }  
        });
	}
	
	public void addMenuBar(){
		JMenuBar menuBar = new javax.swing.JMenuBar();
		this.setJMenuBar(menuBar);
		
		JMenu gameJMenu = new javax.swing.JMenu();
		gameJMenu.setText("Game");
		menuBar.add(gameJMenu);
		
		JMenuItem newGameItem = new javax.swing.JMenuItem();
		newGameItem.setText("New game");
		newGameItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				controller.newGame();
				keyLock = false;
				contentPanel.repaint();
			}
		});
		gameJMenu.add(newGameItem);
		
		JMenuItem playAIItem = new javax.swing.JMenuItem();
		playAIItem.setText("Play AI");
		playAIItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				keyLock = true;
				controller.playAI();
			}
		});
		gameJMenu.add(playAIItem);
		
		JMenuItem exitItem = new javax.swing.JMenuItem();
		exitItem.setText("Exit");
		exitItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				System.exit(0);
			}
		});
		gameJMenu.add(exitItem);
		
		JMenu helpJMenu = new javax.swing.JMenu();
		helpJMenu.setText("Help");
		menuBar.add(helpJMenu);
		
		JMenuItem aboutItem = new javax.swing.JMenuItem();
		aboutItem.setText("About");
		newGameItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				
			}
		});
		helpJMenu.add(aboutItem);     
	}
	
	public void executeCpuMove(){
		controller.cpuMove();		
		contentPanel.repaint();
		keyLock = false;
	}
	
	public void paintContentPanel(){
		contentPanel.repaint();
		//System.out.println("repaint: \n"+model.toString());
	}

}
