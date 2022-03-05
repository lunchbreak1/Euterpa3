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
		setBounds(0,0,800,900);
		setTitle("eUterpa");
	    
	    EuterpaPanel createPanel = new EuterpaPanel();
		EuterpaEditPanel editPanel = new EuterpaEditPanel();
		EuterpaDeletePanel deletePanel = new EuterpaDeletePanel();
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	    this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	    
	    JTabbedPane tp=new JTabbedPane();  
	    tp.setBounds(0,0, 800, 900);  
	    tp.add("Create a Playlist",createPanel);  
	    tp.add("Edit a Playlist",editPanel);  
	    tp.add("Delete a Playlist",deletePanel); 

	    add(tp);  
	    setSize(800,900);  
	    setLayout(null);  
	    setVisible(true);  	    
	}
}
