package org.sp.app0719.admin.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.sp.app0719.admin.controller.Member2TableModel;
import org.sp.app0719.client.view.MainFrame;

public class AdminMain extends JFrame{
	JPanel p_north;
	JComboBox box;//html select box
	JTextField t_keyword;
	JButton bt_search;
	
	Member2TableModel model;
	JTable table;
	JScrollPane scroll;
	
	public AdminMain() {
		p_north = new JPanel();
		box = new JComboBox();
		t_keyword = new JTextField();
		bt_search = new JButton("검색");
		
		
		table = new JTable(model=new Member2TableModel());
		scroll = new JScrollPane(table);
		
		//스타일
		box.setPreferredSize(new Dimension(150,30));
		t_keyword.setPreferredSize(new Dimension(550,30));
		
		//조립
		p_north.add(box);
		p_north.add(t_keyword);
		p_north.add(bt_search);
		
		add(p_north, BorderLayout.NORTH);
		add(scroll);
		
		setSize(800,500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		
		
	}
	
	public static void main(String[] args) {
		new AdminMain();
	}
}








