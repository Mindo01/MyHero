package com.myhero.register;

import com.myhero.login.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import com.myhero.login.Finish;
import com.myhero.login.LoginDAO;

//ĳ���� ���� ȭ��
public class Character extends JFrame implements ActionListener {

	JLabel lName, lChoose; // ĳ���� �̸�, ĳ���� ����
	// ĳ���� �̹��� 1,ĳ���� �̹��� 2,ĳ���� �̹��� 3,ĳ���� �̹��� 4, ������ư, ������ư
	JButton before, next;
	JToggleButton image1, image2, image3, image4;
	String id, characterName; // id, ĳ�����̸�
	int type;// ĳ���� ����

	Character(String id, String characterName) {
		this.id = id;
		this.characterName = characterName;
		Container c = this.getContentPane();
		setTitle("ĳ���� ����");
		setSize(450, 350);
		setLayout(null);
		c.setBackground(new Color(0, 112, 192));
		createUI();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void createUI() {

		lName = new JLabel(characterName);
		lName.setForeground(Color.white);
		lName.setLocation(30, 30);
		lName.setSize(150, 50);
		add(lName);

		lChoose = new JLabel("ĳ���� ���� : ");
		lChoose.setForeground(Color.white);
		lChoose.setLocation(30, 80);
		lChoose.setSize(150, 50);
		add(lChoose);
		// �ϳ��� on�Ǹ� ������ �� off�� ����� on�Ͻô� üũ�� ǥ�õ� �׸����� �ٲ�>>��۹�ư
		// ImageIcon i1 = new ImageIcon("images/herk.png");
		image1 = new JToggleButton(new ImageIcon("images/herk.png"));
		image1.setLocation(20, 120);
		image1.setSize(100, 100);
		image1.setSelectedIcon(new ImageIcon("images/herk1.png"));
		image1.addActionListener((ActionListener) this);
		add(image1);

		image2 = new JToggleButton("�̹���2");
		image2.setLocation(120, 120);
		image2.setSize(100, 20);
		add(image2);

		image3 = new JToggleButton("�̹���3");
		image3.setLocation(220, 120);
		image3.setSize(100, 20);
		add(image3);

		image4 = new JToggleButton("�̹���4");
		image4.setLocation(320, 120);
		image4.setSize(100, 20);
		add(image4);

		before = new JButton("����");
		before.setBackground(Color.lightGray);
		before.setLocation(20, 250);
		before.setSize(100, 20);
		before.addActionListener((ActionListener) this);
		add(before);

		next = new JButton("Ȯ��");
		next.setBackground(Color.lightGray);
		next.setLocation(320, 250);
		next.setSize(100, 20);
		next.addActionListener((ActionListener) this);
		add(next);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s = e.getActionCommand();
		if (s == "Ȯ��") {
			LoginDAO loginDAO = new LoginDAO();
			// on �� �̹����� ���ڿ� ���� type�� �ٲ�
			try {
				if (loginDAO.character(id, characterName, type)) {
					dispose();
					new Finish(characterName, type);
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (s == "����") {
			dispose();
			new Register();
		}

	}

}
