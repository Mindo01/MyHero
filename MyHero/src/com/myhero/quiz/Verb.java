package com.myhero.quiz;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** ¿µ¾î µ¿»ç º¯Çü ÄûÁî ÇÁ·¹ÀÓ */
public class Verb extends JFrame implements ActionListener {
	private Container con;
	private JLabel  Heart, Question;
	private JTextField past, pp;
	private JButton btExit;

	public Verb() 
	{
		super("Àá ÀÚ±â : µ¿»ç º¯È­");
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
		
		Heart = new JLabel("¢¾¢¾");
		Heart.setSize(80, 30);
		Heart.setLocation(10, 5);
		Heart.setForeground(Color.RED);
		Heart.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		
		Question = new JLabel("ÇöÀç ½ÃÁ¦");
		Question.setBackground(Color.WHITE);
		Question.setSize(120, 30);
		Question.setLocation(40, 100);
		Question.setForeground(Color.BLACK);
		Question.setOpaque(true);
		Question.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));

		past = new JTextField("°ú°Å", 80);
		past.setLocation(190, 100);
		past.setSize(100, 30);
		past.setOpaque(true);
		past.setForeground(Color.BLACK);
		past.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 18));
		
		pp = new JTextField("°ú°Å ºÐ»ç", 80);
		pp.setLocation(320, 100);
		pp.setSize(100, 30);
		pp.setOpaque(true);
		pp.setForeground(Color.BLACK);
		pp.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 18));
		
		btExit = new JButton("È®ÀÎ");
		btExit.setSize(65, 30);
		btExit.setLocation(210, 190);
		btExit.setForeground(Color.BLACK);
		btExit.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		btExit.addActionListener(this);

		con.add(Heart);
		con.add(Question);
		con.add(past);
		con.add(pp);
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
