package com.myhero.widget;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

import com.myhero.quiz.Eng_Exp;
import com.myhero.quiz.Verb;
import com.myhero.quiz.Voca;

/**
 * ���� ��� �����ϴ� Ŭ���� - ���� �̵� �̺�Ʈ - ������ �˾� �޴� �ޱ� - �޴� �����۵鿡 ���� ������
 */
public class WidgetFrame extends JFrame implements ActionListener {
	PopupMenu popup;
	MenuItem today, eat, sleep, bath, mini, exit;
	Panel progressBar, progressBar1;
	JProgressBar jpb1, jpb2, jpb3, jpb4;
	int a = 25, b = 60, c = 80, d = 70;
	JLabel charPane, iconMini, iconClose, charString;
	JLabel eaticon, sleepicon, bathicon;
	String charLevel = "1", charName = "����";
	JLabel j1, j2, j3, j4;

	private SystemTray systemTray;
	private PopupMenu mPopup;
	private MenuItem mtoday, meat, msleep, mbath, mmini, mexit;

	int isclicked = 0;
	DragWindowListener dl;

	public WidgetFrame() throws AWTException {

		super("Ű���� �����");

		setSize(new Dimension(700, 700)); // JFrame�� ũ�� ����100 ����100
		setLocationRelativeTo(null); // ó�� â�� ����ɶ� �߰��� ��ġ�ϵ��� �ϴ� �ڵ�
		setUndecorated(true); // ������ â�� �׵θ� ����
		setBackground(new Color(0, 0, 0, 0)); // JFrame�� ���� ����(����ȭ ��Ű�� ���Ͽ�)
		dl = new DragWindowListener(); // ���콺�巡�� �����ʸ� ����

		JPanel panel = new JPanel() { // ������ ȿ���� �� �ǳ��� �����.
			protected void paintComponent(Graphics g) {
				if (g instanceof Graphics2D) { // �׷��� g�� �����´�.
					final int R = 255; // RGB �ڵ��� R �� 255�� ����
					final int G = 255; // RGB �ڵ��� G �� 255�� ����
					final int B = 255; // RGB �ڵ��� B �� 255�� ����
					Paint p = new GradientPaint(0.0f, 0.0f, new Color(R, G, B, 0), 0.0f, getHeight(),
							new Color(R, G, B, 0), true); // �׶��̼� ȿ���� �ִ� ����Ʈ p
															// ����
					Graphics2D g2d = (Graphics2D) g; // g�� Graphics2D Ÿ������ ����ȯ
														// ��Ų��
					g2d.setPaint(p); // ����Ʈp�� ĥ���ش�
					g2d.fillRect(0, 0, getWidth(), getHeight()); // ����Ʈ�� ĥ���� ������
																	// �簢���� �ǳڿ�
																	// ������ �׸���.
				}
			}
		}; // ������ �ǳ� ���� ����
		setContentPane(panel); // ����Ʈ�濡 ����Ʈ�� ĥ�� �ǳ��� �ٿ��ش�
		setLayout(null); // ��ġ �����ڸ� null�� ����

		try {
			picture(); // �ð� �� ������Ʈ���� ���̰� GUI�� �ʱ�ȭ�ϴ� �Լ�
		} catch (IOException e) { // ����� ���ܰ� �߻��ϸ�
			e.printStackTrace(); // �� ���ܸ� ����Ʈ�Ѵ�.
		}
		setVisible(true);
	}

	/** ĳ���Ϳ� ���¹� ���� ���̰� GUI�� �ʱ�ȭ�ϴ� �Լ� */
	private void picture() throws IOException, AWTException {
		// TODO Auto-generated method stub

		charString = new JLabel("Lv" + charLevel + ". " + charName); // ����ð���
																		// ǥ���ϴ�
																		// �� ����
		charString.setFont(new Font(null, Font.BOLD, 22)); // ����ð��� ǥ���ϴ� ���� ��Ʈ
															// ����
		// charString.setForeground(new Color(0, 0, 0)); // ����ð��� ǥ���ϴ� ���� ���ڻ�
		// ����

		Image iimage = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("��ũ.png"));
		ImageIcon charImage = new ImageIcon(iimage); // ��� �׸��� �����´�
		charPane = new JLabel(charImage); // ��� �׸��� ���Ե� �� ����
		init();
		charPane.addMouseListener(new RightClicked(charPane, popup));
		charPane.addMouseListener(dl); //
		charPane.addMouseMotionListener(dl);

		iimage = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("��.png"));
		iimage = iimage.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		eaticon = new JLabel(new ImageIcon(iimage), JLabel.RIGHT);
		eaticon.setPreferredSize(new Dimension(100, 40));

		iimage = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("ü��.png"));
		iimage = iimage.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		sleepicon = new JLabel(new ImageIcon(iimage), JLabel.RIGHT);

		iimage = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("����.png"));
		iimage = iimage.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		bathicon = new JLabel(new ImageIcon(iimage), JLabel.RIGHT);

		progressBar = new Panel();
		progressBar1 = new Panel();
		progressBar.setBackground(new Color(255, 255, 255));

		progressBar.setLayout(new GridLayout(3, 0, 8, 10));

		jpb1 = new JProgressBar(0, 100); // �ּ�0,�ִ�100
		jpb2 = new JProgressBar(0, 100); // �ּ�0,�ִ�100
		jpb3 = new JProgressBar(0, 100); // �ּ�0,�ִ�100
		jpb4 = new JProgressBar(0, 200); // �ּ�0,�ִ�100

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

		progressBar.setVisible(false); // �˶� ���� â�� ���߱�
		progressBar1.setVisible(false); // �˶� ���� â�� ���߱�

		charPane.addMouseListener(new MouseAdapter() { // ���콺 ������ ����
			public void mouseEntered(MouseEvent e) { // ���콺 Ŭ�� �̺�Ʈ
				j1.setVisible(true);
				j2.setVisible(true);
				j3.setVisible(true);
				j4.setVisible(true);
				progressBar.setVisible(true); // �˶� ���� â�� ���̵��� ����
				progressBar1.setVisible(true); // �˶� ���� â�� ���߱�
				repaint(); // �ٽ� �׷��ش�
			}

			public void mouseExited(MouseEvent e) {
				progressBar.setVisible(false); // �˶� ����â�� ������ �ʵ��� ����
				progressBar1.setVisible(false); // �˶� ���� â�� ���߱�
				j1.setVisible(false);
				j2.setVisible(false);
				j3.setVisible(false);
				j4.setVisible(false);
				repaint(); // �ٽ� �׷��ش�
			}

			public void mouseClicked(MouseEvent e) {
			}
		});
		charPane.setBounds(160, 50, 288, 288); // ĳ������ ��ġ ����
		charString.setBounds(260, 50, 100, 100); // ���� ��ġ ����
		// progressBar.setBounds(0, 170, 200,80); // ���� ��ġ
		progressBar.setBounds(330, 170, 200, 80); // ������ ��ġ
		progressBar1.setBounds(215, 330, 180, 30);

		add(charString); // �ֻ��� �����̳ʿ� ����
		add(charPane); // �ֻ��� �����̳ʿ� ����
		add(progressBar);
		add(progressBar1);
	}

	/** �˾� �޴� ��ġ �� �ʱ�ȭ �޼ҵ� */
	public void init() throws AWTException {
		if (SystemTray.isSupported()) {
			mPopup = new PopupMenu();
			mtoday = new MenuItem("������ ���ܾ�");
			mtoday.addActionListener(this);
			meat = new MenuItem("���ֱ�");
			meat.addActionListener(this);
			msleep = new MenuItem("���ڱ�");
			msleep.addActionListener(this);
			mbath = new MenuItem("�ı�");
			mbath.addActionListener(this);
			mmini = new MenuItem("�ּ�ȭ");
			mmini.addActionListener(this);
			mexit = new MenuItem("����");
			mexit.addActionListener(this);

			mPopup.add(mtoday);
			mPopup.addSeparator();
			mPopup.add(meat);
			mPopup.add(msleep);
			mPopup.add(mbath);
			mPopup.addSeparator();
			mPopup.add(mmini);
			mPopup.add(mexit);

			/* ������ �����ܿ� ǥ���ϴ� �κ� : ���� �ʿ�!!! */
			Image image = Toolkit.getDefaultToolkit().getImage("images/back.jpg");
			TrayIcon trayIcon = new TrayIcon(image, "Java 6.0 SystemTrayIcon Test", mPopup);
			
			trayIcon.setImageAutoSize(true);

			systemTray = SystemTray.getSystemTray();
			systemTray.add(trayIcon);
			trayIcon.addMouseListener(new MouseAdapter() { // ���콺 ������ ����
				public void mousePressed(MouseEvent e) {
					System.out.println("Ʈ���̾������� ���ȴ� ����");
					
					//window.open(charPane);
				}
			});
		}

		popup = new PopupMenu();
		today = new MenuItem("������ ���ܾ�");
		today.addActionListener(this);
		eat = new MenuItem("���ֱ�");
		eat.addActionListener(this);
		sleep = new MenuItem("���ڱ�");
		sleep.addActionListener(this);
		bath = new MenuItem("�ı�");
		bath.addActionListener(this);
		mini = new MenuItem("�ּ�ȭ");
		mini.addActionListener(this);
		exit = new MenuItem("����");
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

	/** �˾� �޴� �� ������ Ŭ�� �� ������ */
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
			setState(JFrame.ICONIFIED); // â�� ���¸� �ּ�ȭ ���·� ����
		}
		if (mItem == exit) {
			System.exit(0); // �ý����� ���� ��Ų��
		}

		if (mItem == mtoday) {
			showMessage("Today Voca", "������ �ܾ �����մϴ�");
		}

		if (mItem == meat) {
			showMessage("�� �ֱ�", "���� �ܾ� ����");
			// new Voca();
		}

		if (mItem == msleep) {
			showMessage("�� �ڱ�", "���� 3�� ��ȭ");
			// new Eng_Exp();
		}

		if (mItem == mbath) {
			showMessage("�ı�", "���� Ǯ��");
			// new Verb();
		}
		if (mItem == mmini) {
			setState(JFrame.ICONIFIED); // â�� ���¸� �ּ�ȭ ���·� ����
		}
		if (mItem == mexit) {
			System.exit(0); // �ý����� ���� ��Ų��
			 dispose();
			  try {
				systemTray.wait();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} //���α׷� ����
		}
	}

	private void showMessage(String title, String message) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
	}

	/** ������ Ŭ�� �� �˾� �޴� ����ִ� �޼ҵ� */
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
			// ��������Ÿ���¸޼ҵ� isMetaDown()�� xx.show�޼ҵ� �� ����.
		}
	}

	/** �巡�� ���콺�� ���� â�� �����̴� ������ */
	class DragWindowListener extends MouseAdapter {
		private final transient Point startPt = new Point(); // ���콺�� ��ġ�� �޴�
																// Point
		private Window window; // ������ ������ â

		public void mousePressed(MouseEvent me) { // ���콺�� �������� �̺�Ʈ
			if (window == null) { // window�� ����ٸ�
				Object o = me.getSource(); // ������ ��Ҹ� ������
				if (o instanceof Window) { // ������ ��Ұ� Window�� ��ӹ޴� ��Ҷ��
					window = (Window) o; // Window�� ���¸� �ٲ�
				} else if (o instanceof JComponent) { // ������ ��Ұ� JComponent���
					window = SwingUtilities.windowForComponent(me.getComponent()); // ������Ʈ��
																					// �پ��ִ�
																					// �����츦
																					// �ҷ��´�
				}
			}
			startPt.setLocation(me.getPoint()); // ���콺�� ��ġ�� �޴� Point�� ���� �̺�Ʈ��
												// �Ͼ �����ͷ� ����ġ
		} // ���콺�� �������� �̺�Ʈ�� ��

		public void mouseDragged(MouseEvent me) { // ���콺�� �巡�� �Ҷ� �̺�Ʈ
			if (window != null) { // window�� null�� �ƴϸ�
				Point pt = new Point(); // ���ο� �����͸� ����
				pt = window.getLocation(pt); // window��ü�� ��ġ�� �޾ƿ� �����Ϳ� �ִ´�
				int x = pt.x - startPt.x + me.getX(); // ������ x��ǥ ���
				int y = pt.y - startPt.y + me.getY(); // ������ y��ǥ ���
				window.setLocation(x, y); // ������ x, y ��ŭ �����츦 ������
			}
		} // ���콺�� �巡�� �Ҷ� �̺�Ʈ�� ��
	} // �巡�� ���콺�� ���� â�� �����̴� �������� ��

	/*public static void main(String[] args) throws AWTException { // ���� �Լ�
		new WidgetFrame();
	}*/
}