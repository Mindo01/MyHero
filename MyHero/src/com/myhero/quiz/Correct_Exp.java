package com.myhero.quiz;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/** ��� ���� ���� ������ ����� â */
public class Correct_Exp extends JFrame implements ActionListener {
	private Container con;
	private JLabel Answer, Question;
	private JButton btExit;
	private int questionType;

	public Correct_Exp(int type) 
	{
		super("���� :-)");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		/* ���� ���� �� �޾��ֱ� */
		questionType = type;
		
		this.init();
		this.setSize(300, 300);
		this.setVisible(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}

	public void init() {
		con = this.getContentPane();
		con.setLayout(null);
		con.setBackground(new Color(0, 112, 192));
		
		Question = new JLabel("�����Դϴ�!!");
		Question.setSize(300, 40);
		Question.setLocation(50, 68);
		Question.setForeground(Color.WHITE);
		Question.setFont(new Font("a�ĵ��Ҹ�", Font.PLAIN, 35));

		String[] qType = {"������ ++", "ü�� ++", "û�ᵵ ++"};
		Answer = new JLabel(qType[questionType]);
		Answer.setSize(100, 30);
		Answer.setLocation(100, 140);
		Answer.setForeground(Color.WHITE);
		Answer.setFont(new Font("���� ���", Font.PLAIN, 20));
		
		btExit = new JButton("Ȯ��");
		btExit.setSize(65, 30);
		btExit.setLocation(110, 210);
		btExit.setForeground(Color.BLACK);
		btExit.setFont(new Font("���� ���", Font.PLAIN, 15));
		btExit.addActionListener(this);

		con.add(Question);
		con.add(Answer);
		con.add(btExit);
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == btExit)
		{
			dispose();
		} 
	}
}
