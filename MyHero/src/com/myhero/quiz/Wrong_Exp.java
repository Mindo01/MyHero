package com.myhero.quiz;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** 퀴즈에 대한 답이 오답일 경우의 창 */
public class Wrong_Exp extends JFrame implements ActionListener {
	private Container con;
	private JLabel Answer, Question;
	private JButton btExit;
	private int questionType;
	
	public Wrong_Exp(int type) 
	{
		super("오답 :-(");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
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
		
		Question = new JLabel("오답입니다ㅠㅠ");
		Question.setSize(300, 40);
		Question.setLocation(30, 68);
		Question.setForeground(Color.WHITE);
		Question.setFont(new Font("a파도소리", Font.PLAIN, 35));

		String[] qType = {"포만감이", "체력이", "청결도가"};
		Answer = new JLabel(qType[questionType]+" 줄어듭니다");
		Answer.setSize(300, 30);
		Answer.setLocation(58, 140);
		Answer.setForeground(Color.WHITE);
		Answer.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		
		btExit = new JButton("확인");
		btExit.setSize(65, 30);
		btExit.setLocation(110, 210);
		btExit.setForeground(Color.BLACK);
		btExit.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
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
