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

//캐릭터 설정 화면
public class Character extends JFrame implements ActionListener {

	JLabel lName, lChoose; // 캐릭터 이름, 캐릭터 선택
	// 캐릭터 이미지 1,캐릭터 이미지 2,캐릭터 이미지 3,캐릭터 이미지 4, 이전버튼, 다음버튼
	JButton before, next;
	JToggleButton image1, image2, image3, image4;
	String id, characterName; // id, 캐릭터이름
	int type;// 캐릭터 설정

	Character(String id, String characterName) {
		this.id = id;
		this.characterName = characterName;
		Container c = this.getContentPane();
		setTitle("캐릭터 선택");
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

		lChoose = new JLabel("캐릭터 선택 : ");
		lChoose.setForeground(Color.white);
		lChoose.setLocation(30, 80);
		lChoose.setSize(150, 50);
		add(lChoose);
		// 하나가 on되면 나머지 다 off로 만들고 on일시는 체크가 표시된 그림으로 바뀜>>토글버튼
		// ImageIcon i1 = new ImageIcon("images/herk.png");
		image1 = new JToggleButton(new ImageIcon("images/herk.png"));
		image1.setLocation(20, 120);
		image1.setSize(100, 100);
		image1.setSelectedIcon(new ImageIcon("images/herk1.png"));
		image1.addActionListener((ActionListener) this);
		add(image1);

		image2 = new JToggleButton("이미지2");
		image2.setLocation(120, 120);
		image2.setSize(100, 20);
		add(image2);

		image3 = new JToggleButton("이미지3");
		image3.setLocation(220, 120);
		image3.setSize(100, 20);
		add(image3);

		image4 = new JToggleButton("이미지4");
		image4.setLocation(320, 120);
		image4.setSize(100, 20);
		add(image4);

		before = new JButton("이전");
		before.setBackground(Color.lightGray);
		before.setLocation(20, 250);
		before.setSize(100, 20);
		before.addActionListener((ActionListener) this);
		add(before);

		next = new JButton("확인");
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
		if (s == "확인") {
			LoginDAO loginDAO = new LoginDAO();
			// on 된 이미지에 숫자에 따라 type도 바뀜
			try {
				if (loginDAO.character(id, characterName, type)) {
					dispose();
					new Finish(characterName, type);
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (s == "이전") {
			dispose();
			new Register();
		}

	}

}
