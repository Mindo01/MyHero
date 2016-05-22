package com.myhero.quiz;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** øµøµ «Æ¿Ã ƒ˚¡Ó «¡∑π¿” */
public class Eng_Exp extends JFrame implements ActionListener {
	private Container con;
	private JLabel Answer, Heart, Question;
	private JTextField f_answer;
	private JButton btExit;

	public Eng_Exp() 
	{
		super("æƒ±‚ : øµøµ «Æ¿Ã");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.init();
		this.setSize(500, 300);
		this.setVisible(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}

	public void init() {
		con = this.getContentPane();
		con.setLayout(null);
		con.setBackground(new Color(0, 112, 192));
		
		Heart = new JLabel("¢æ¢æ¢æ");
		Heart.setSize(80, 30);
		Heart.setLocation(10, 5);
		Heart.setForeground(Color.RED);
		Heart.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 15));
		
		Question = new JLabel("øµ øµ «Æ ¿Ã");
		Question.setBackground(Color.WHITE);
		Question.setSize(400, 50);
		Question.setLocation(50, 68);
		Question.setForeground(Color.BLACK);
		Question.setOpaque(true);
		Question.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 22));

		Answer = new JLabel("¡§¥‰ :");
		Answer.setSize(80, 30);
		Answer.setLocation(55, 150);
		Answer.setForeground(Color.WHITE);
		Answer.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 22));

		f_answer = new JTextField(80);
		f_answer.setLocation(150, 150);
		f_answer.setSize(300, 30);
		f_answer.setForeground(Color.BLACK);
		f_answer.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 18));
		
		btExit = new JButton("»Æ¿Œ");
		btExit.setSize(65, 30);
		btExit.setLocation(205, 210);
		btExit.setForeground(Color.BLACK);
		btExit.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 15));
		btExit.addActionListener(this);

		con.add(Heart);
		con.add(Question);
		con.add(Answer);
		con.add(f_answer);
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
