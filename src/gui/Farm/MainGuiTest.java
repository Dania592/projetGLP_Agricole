package gui.Farm;


import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import data.configuration.GameConfiguration;
import data.stucture_base.Element;
import data.stucture_base.Farm;
import process.game.GameBuilder;

public class MainGuiTest  extends JFrame implements Runnable{

	private static final long serialVersionUID = 1L;

	private Farm farm ;

	private Board dashboard ;
	private Element selected ;
	private int x ;
	private int y ;


	public MainGuiTest(String title ) {
		super(title);
		init();
	}


	public void init() {

		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		farm=GameBuilder.buildinFarm();

		selected= farm.getManager().getMapManager().get("fermier");	

		dashboard = new Board(farm, selected);

		contentPane.add(dashboard);

		dashboard.addMouseListener(new MouseControls());


		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setSize(GameConfiguration.WINDOW_WIDTH , GameConfiguration.WINDOW_HEIGHT);
		setResizable(false);
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(GameConfiguration.GAME_SPEED);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//dashboard.setSelected(dashboard.getSelected());
			dashboard.getFarm().getEvolutionManager().UpdateEvolution();
			dashboard.repaint();
		}
	}

	private class MouseControls implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			Element element = farm.getManager().search(x, y);
			if(element!=null) {
				//System.out.println(element.getReference());
				selected = element ;
			}

		}

		@Override
		public void mousePressed(MouseEvent e) {
			x = e.getX() ;
			y = e.getY() ;
			//System.out.println(x+" "+y);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			int dx = e.getX() ;
			int dy = e.getY() ;
			farm.getManager().getMapManager().movingMap(dx - x, dy - y );

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			//System.out.println(x+" "+y);

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}




}
