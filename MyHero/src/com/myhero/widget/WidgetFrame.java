package com.myhero.widget;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

import com.myhero.quiz.Eng_Exp;
import com.myhero.quiz.Verb;
import com.myhero.quiz.Voca;

/**
 * 위젯 기능 구현하는 클래스 - 위젯 이동 이벤트 - 위젯에 팝업 메뉴 달기 - 메뉴 아이템들에 대한 리스너
 */
public class WidgetFrame extends JFrame implements ActionListener {
	PopupMenu popup;
	MenuItem today, eat, sleep, bath, mini, exit;
	Panel progressBar, progressBar1;
	JProgressBar jpb1, jpb2, jpb3, jpb4;
	int a = 25, b = 60, c = 80, d = 70;
	JLabel charPane, iconMini, iconClose, charString;
	JLabel eaticon, sleepicon, bathicon;
	String charLevel = "1", charName = "으아";
	JLabel j1, j2, j3, j4;

	private SystemTray systemTray;
	private PopupMenu mPopup;
	private MenuItem mtoday, meat, msleep, mbath, mmini, mexit;

	int isclicked = 0;
	DragWindowListener dl;

	public WidgetFrame() throws AWTException {

		super("키워라 히어로");

		setSize(new Dimension(700, 700)); // JFrame의 크기 가로100 세로100
		setLocationRelativeTo(null); // 처음 창이 실행될때 중간에 위치하도록 하는 코드
		setUndecorated(true); // 윈도우 창의 테두리 제거
		setBackground(new Color(0, 0, 0, 0)); // JFrame의 배경색 지정(투명화 시키기 위하여)
		dl = new DragWindowListener(); // 마우스드래그 리스너를 생성

		JPanel panel = new JPanel() { // 투명한 효과를 줄 판넬을 만든다.
			protected void paintComponent(Graphics g) {
				if (g instanceof Graphics2D) { // 그래픽 g를 가져온다.
					final int R = 255; // RGB 코드중 R 을 255로 설정
					final int G = 255; // RGB 코드중 G 을 255로 설정
					final int B = 255; // RGB 코드중 B 을 255로 설정
					Paint p = new GradientPaint(0.0f, 0.0f, new Color(R, G, B, 0), 0.0f, getHeight(),
							new Color(R, G, B, 0), true); // 그라데이션 효과가 있는 페인트 p
															// 생성
					Graphics2D g2d = (Graphics2D) g; // g를 Graphics2D 타입으로 형변환
														// 시킨다
					g2d.setPaint(p); // 페인트p로 칠해준다
					g2d.fillRect(0, 0, getWidth(), getHeight()); // 페인트로 칠해진 상태의
																	// 사각형을 판넬에
																	// 꽉차게 그린다.
				}
			}
		}; // 투명한 판넬 생성 종료
		setContentPane(panel); // 컨텐트펜에 페인트로 칠한 판넬을 붙여준다
		setLayout(null); // 배치 관리자를 null로 설정

		try {
			picture(); // 시계 안 컴포넌트들을 붙이고 GUI를 초기화하는 함수
		} catch (IOException e) { // 입출력 예외가 발생하면
			e.printStackTrace(); // 그 예외를 프린트한다.
		}
		setVisible(true);
	}

	/** 캐릭터와 상태바 등을 붙이고 GUI를 초기화하는 함수 */
	private void picture() throws IOException, AWTException {
		// TODO Auto-generated method stub

		charString = new JLabel("Lv" + charLevel + ". " + charName); // 현재시간을
																		// 표시하는
																		// 라벨 생성
		charString.setFont(new Font(null, Font.BOLD, 22)); // 현재시간을 표시하는 라벨의 폰트
															// 변경
		// charString.setForeground(new Color(0, 0, 0)); // 현재시간을 표시하는 라벨의 글자색
		// 변경

		Image iimage = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("헐크.png"));
		ImageIcon charImage = new ImageIcon(iimage); // 배경 그림을 가져온다
		charPane = new JLabel(charImage); // 배경 그림이 포함된 라벨 생성
		init();
		charPane.addMouseListener(new RightClicked(charPane, popup));
		charPane.addMouseListener(dl); //
		charPane.addMouseMotionListener(dl);

		iimage = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("밥.png"));
		iimage = iimage.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		eaticon = new JLabel(new ImageIcon(iimage), JLabel.RIGHT);
		eaticon.setPreferredSize(new Dimension(100, 40));

		iimage = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("체력.png"));
		iimage = iimage.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		sleepicon = new JLabel(new ImageIcon(iimage), JLabel.RIGHT);

		iimage = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("샤워.png"));
		iimage = iimage.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		bathicon = new JLabel(new ImageIcon(iimage), JLabel.RIGHT);

		progressBar = new Panel();
		progressBar1 = new Panel();
		progressBar.setBackground(new Color(255, 255, 255));

		progressBar.setLayout(new GridLayout(3, 0, 8, 10));

		jpb1 = new JProgressBar(0, 100); // 최소0,최대100
		jpb2 = new JProgressBar(0, 100); // 최소0,최대100
		jpb3 = new JProgressBar(0, 100); // 최소0,최대100
		jpb4 = new JProgressBar(0, 200); // 최소0,최대100

		Color c1, c2, c3, c4;

		if (a <= 30)
			c1 = Color.red;
		else if (a <= 70)
			c1 = Color.orange;
		else
			c1 = Color.green;
		jpb1.setForeground(c1);

		if (b <= 30)
			c2 = Color.red;
		else if (b <= 70)
			c2 = Color.orange;
		else
			c2 = Color.green;
		jpb2.setForeground(c2);

		if (c <= 30)
			c3 = Color.red;
		else if (c <= 70)
			c3 = Color.orange;
		else
			c3 = Color.green;
		jpb3.setForeground(c3);

		if (d <= 30)
			c4 = Color.red;
		else if (d <= 70)
			c4 = Color.orange;
		else
			c4 = Color.green;
		jpb4.setForeground(c4);

		jpb1.setBackground(new Color(255, 255, 255));
		jpb2.setBackground(new Color(255, 255, 255));
		jpb3.setBackground(new Color(255, 255, 255));
		jpb4.setBackground(new Color(255, 255, 255));

		progressBar.add(eaticon);
		progressBar.add(jpb1);
		progressBar.add(sleepicon);
		progressBar.add(jpb2);
		progressBar.add(bathicon);
		progressBar.add(jpb3);
		progressBar1.add(jpb4);

		jpb1.setValue(a);
		jpb2.setValue(b);
		jpb3.setValue(c);
		jpb4.setValue(d);

		j1 = new JLabel(Integer.toString(a));
		j1.setBounds(475, 130, 100, 100);
		j2 = new JLabel(Integer.toString(b));
		j2.setBounds(475, 161, 100, 100);
		j3 = new JLabel(Integer.toString(c));
		j3.setBounds(475, 192, 100, 100);
		j4 = new JLabel(Integer.toString(d));
		j4.setBounds(298, 293, 100, 100);
		add(j1);
		add(j2);
		add(j3);
		add(j4);
		j1.setVisible(false);
		j2.setVisible(false);
		j3.setVisible(false);
		j4.setVisible(false);

		progressBar.setVisible(false); // 알람 설정 창을 감추기
		progressBar1.setVisible(false); // 알람 설정 창을 감추기

		charPane.addMouseListener(new MouseAdapter() { // 마우스 리스너 부착
			public void mouseEntered(MouseEvent e) { // 마우스 클릭 이벤트
				j1.setVisible(true);
				j2.setVisible(true);
				j3.setVisible(true);
				j4.setVisible(true);
				progressBar.setVisible(true); // 알람 설정 창이 보이도록 설정
				progressBar1.setVisible(true); // 알람 설정 창을 감추기
				repaint(); // 다시 그려준다
			}

			public void mouseExited(MouseEvent e) {
				progressBar.setVisible(false); // 알람 설정창이 보이지 않도록 설정
				progressBar1.setVisible(false); // 알람 설정 창을 감추기
				j1.setVisible(false);
				j2.setVisible(false);
				j3.setVisible(false);
				j4.setVisible(false);
				repaint(); // 다시 그려준다
			}

			public void mouseClicked(MouseEvent e) {
			}
		});
		charPane.setBounds(160, 50, 288, 288); // 캐릭터의 위치 지정
		charString.setBounds(260, 50, 100, 100); // 라벨의 위치 지정
		// progressBar.setBounds(0, 170, 200,80); // 왼쪽 배치
		progressBar.setBounds(330, 170, 200, 80); // 오른쪽 배치
		progressBar1.setBounds(215, 330, 180, 30);

		add(charString); // 최상위 컨테이너에 부착
		add(charPane); // 최상위 컨테이너에 부착
		add(progressBar);
		add(progressBar1);
	}

	/** 팝업 메뉴 배치 및 초기화 메소드 */
	public void init() throws AWTException {
		if (SystemTray.isSupported()) {
			mPopup = new PopupMenu();
			mtoday = new MenuItem("오늘의 영단어");
			mtoday.addActionListener(this);
			meat = new MenuItem("밥주기");
			meat.addActionListener(this);
			msleep = new MenuItem("잠자기");
			msleep.addActionListener(this);
			mbath = new MenuItem("씻기");
			mbath.addActionListener(this);
			mmini = new MenuItem("최소화");
			mmini.addActionListener(this);
			mexit = new MenuItem("종료");
			mexit.addActionListener(this);

			mPopup.add(mtoday);
			mPopup.addSeparator();
			mPopup.add(meat);
			mPopup.add(msleep);
			mPopup.add(mbath);
			mPopup.addSeparator();
			mPopup.add(mmini);
			mPopup.add(mexit);

			/* 숨겨진 아이콘에 표시하는 부분 : 수정 필요!!! */
			Image image = Toolkit.getDefaultToolkit().getImage("images/back.jpg");
			TrayIcon trayIcon = new TrayIcon(image, "Java 6.0 SystemTrayIcon Test", mPopup);
			
			trayIcon.setImageAutoSize(true);

			systemTray = SystemTray.getSystemTray();
			systemTray.add(trayIcon);
			trayIcon.addMouseListener(new MouseAdapter() { // 마우스 리스너 부착
				public void mousePressed(MouseEvent e) {
					System.out.println("트레이아이콘이 눌렸다 빠밤");
					
					//window.open(charPane);
				}
			});
		}

		popup = new PopupMenu();
		today = new MenuItem("오늘의 영단어");
		today.addActionListener(this);
		eat = new MenuItem("밥주기");
		eat.addActionListener(this);
		sleep = new MenuItem("잠자기");
		sleep.addActionListener(this);
		bath = new MenuItem("씻기");
		bath.addActionListener(this);
		mini = new MenuItem("최소화");
		mini.addActionListener(this);
		exit = new MenuItem("종료");
		exit.addActionListener(this);

		popup.add(today);
		popup.addSeparator();
		popup.add(eat);
		popup.add(sleep);
		popup.add(bath);
		popup.addSeparator();
		popup.add(mini);
		popup.add(exit);
		charPane.add(popup);

	}

	/** 팝업 메뉴 내 아이템 클릭 시 리스너 */
	public void actionPerformed(ActionEvent e) {
		MenuItem mItem = (MenuItem) e.getSource();
		if (mItem == today) {
		}

		if (mItem == eat) {
			new Voca();
		}

		if (mItem == sleep) {
			new Eng_Exp();
		}

		if (mItem == bath) {
			new Verb();
		}
		if (mItem == mini) {
			setState(JFrame.ICONIFIED); // 창의 상태를 최소화 상태로 변경
		}
		if (mItem == exit) {
			System.exit(0); // 시스템을 종료 시킨다
		}

		if (mItem == mtoday) {
			showMessage("Today Voca", "오늘의 단어를 공부합니다");
		}

		if (mItem == meat) {
			showMessage("밥 주기", "영어 단어 퀴즈");
			// new Voca();
		}

		if (mItem == msleep) {
			showMessage("잠 자기", "동사 3단 변화");
			// new Eng_Exp();
		}

		if (mItem == mbath) {
			showMessage("씻기", "영영 풀이");
			// new Verb();
		}
		if (mItem == mmini) {
			setState(JFrame.ICONIFIED); // 창의 상태를 최소화 상태로 변경
		}
		if (mItem == mexit) {
			System.exit(0); // 시스템을 종료 시킨다
			 dispose();
			  try {
				systemTray.wait();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} //프로그램 종료
		}
	}

	private void showMessage(String title, String message) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
	}

	/** 오른쪽 클릭 시 팝업 메뉴 띄워주는 메소드 */
	public class RightClicked extends MouseAdapter {
		JLabel charPane;
		PopupMenu popup;

		public RightClicked(JLabel charPane, PopupMenu pop) {
			this.charPane = charPane;
			popup = pop;
		}

		public void mouseClicked(MouseEvent e) {
			if (e.isMetaDown())
				popup.show(charPane, e.getX(), e.getY());
			// 우측에나타나는메소드 isMetaDown()와 xx.show메소드 를 쓴다.
		}
	}

	/** 드래그 마우스를 따라 창을 움직이는 리스너 */
	class DragWindowListener extends MouseAdapter {
		private final transient Point startPt = new Point(); // 마우스의 위치를 받는
																// Point
		private Window window; // 움직일 윈도우 창

		public void mousePressed(MouseEvent me) { // 마우스를 눌렀을때 이벤트
			if (window == null) { // window가 비었다면
				Object o = me.getSource(); // 눌러진 요소를 가져옴
				if (o instanceof Window) { // 눌러진 요소가 Window를 상속받는 요소라면
					window = (Window) o; // Window로 형태를 바꿈
				} else if (o instanceof JComponent) { // 눌러진 요소가 JComponent라면
					window = SwingUtilities.windowForComponent(me.getComponent()); // 컴포넌트가
																					// 붙어있는
																					// 윈도우를
																					// 불러온다
				}
			}
			startPt.setLocation(me.getPoint()); // 마우스의 위치를 받는 Point를 현재 이벤트가
												// 일어난 포인터로 재위치
		} // 마우스를 눌렀을때 이벤트의 끝

		public void mouseDragged(MouseEvent me) { // 마우스를 드래그 할때 이벤트
			if (window != null) { // window가 null이 아니면
				Point pt = new Point(); // 새로운 포인터를 생성
				pt = window.getLocation(pt); // window객체의 위치를 받아와 포인터에 넣는다
				int x = pt.x - startPt.x + me.getX(); // 움직인 x좌표 계산
				int y = pt.y - startPt.y + me.getY(); // 움직인 y좌표 계산
				window.setLocation(x, y); // 움직인 x, y 만큼 윈도우를 움직임
			}
		} // 마우스를 드래그 할때 이벤트의 끝
	} // 드래그 마우스를 따라 창을 움직이는 리스너의 끝

	/*public static void main(String[] args) throws AWTException { // 메인 함수
		new WidgetFrame();
	}*/
}