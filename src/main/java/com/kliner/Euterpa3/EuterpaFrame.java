package com.kliner.Euterpa3;

import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class EuterpaFrame extends JFrame {

	public EuterpaFrame() throws HeadlessException 
	{
		super();
		setBounds(0,0,800,1000);
		setTitle("SoundsFun");
	    
	    EuterpaPanel createPanel = new EuterpaPanel();
		EuterpaEditPanel editPanel = new EuterpaEditPanel();
		
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	    this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	    
	    JTabbedPane tp=new JTabbedPane();  
	    tp.setBounds(0,0, 800, 1000);  
	    tp.add("Create a Playlist",createPanel);  
	    tp.add("Edit a Playlist",editPanel);  

	    add(tp);  
	    setSize(800,960);  
	    setLayout(null);  
	    setVisible(true);  	    
	}
}
