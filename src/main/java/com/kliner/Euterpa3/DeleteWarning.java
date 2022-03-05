package com.kliner.Euterpa3;

	import javax.swing.*;  
	import java.awt.event.*;  
	public class DeleteWarning extends WindowAdapter{  
	JFrame f;  
	String directory;
	DeleteWarning(String dir){  
	    f=new JFrame();   
	    directory=dir;
	    f.addWindowListener(this);  
	    f.setSize(300, 300);  
	    f.setLayout(null);  
	    f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);  
	    f.setVisible(true);  
	}  
	public void windowClosing(WindowEvent e) {  
	    int a=JOptionPane.showConfirmDialog(f,"Are you sure?");  
	if(a==JOptionPane.YES_OPTION){  
		try {
			EuterpaClient.delete(directory);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	}  
	}  

	}  