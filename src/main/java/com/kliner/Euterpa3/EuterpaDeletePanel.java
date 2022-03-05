package com.kliner.Euterpa3;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EuterpaDeletePanel extends JPanel implements ActionListener  {
	
	String MP3folder = "C:\\Users\\iank1\\OneDrive\\Desktop\\Projects\\Java\\Euterpa-2\\MP3s\\";
	JLabel titleLabel, pageLbl, omitStringLbl, timestampsLbl, seriesLbl, artistLbl, directorLbl, mediumLbl, companyLbl, yearLbl, artLbl, sortCodeLbl;
	JTextField ostValue, omitStringVal, seriesVal, artistVal, directorVal, mediumVal, companyVal, yearVal, artVal, sortCodeVal;
	JTextArea timestamps;
	JScrollPane sp;
	JButton selectOST, selectArt, submit;
	String OSTREGEXPATTERN = "(\\w|\\s)+(.mp3)";
	String ost, dir; 
	
	public EuterpaDeletePanel()
	{
		super();
		setSize(800,750);
	    setVisible(true);
	    
	    setLayout(null);
	   
	    
	    titleLabel = new JLabel("eUterpa");
	    titleLabel.setFont(new Font("Arial", Font.PLAIN, 24));
	    titleLabel.setBounds(350, 30, 100, 40);
	    titleLabel.setForeground(Color.white);
	    setBackground(Color.black);
	    
	    add(titleLabel);
	    
	    pageLbl = new JLabel("Delete a Playlist");
	    pageLbl.setFont(new Font("Arial", Font.PLAIN, 24));
	    pageLbl.setBounds(330, 55, 150, 40);
	    pageLbl.setForeground(Color.white);
	    add(pageLbl);
	    
	    ostValue = new JTextField();
	    ostValue.setBounds(100, 90, 500, 35);
	    ostValue.setFont(new Font("Arial", Font.PLAIN, 24));
	    add(ostValue);
	    
	    selectOST = new JButton("Select Playlist"); 
	    selectOST.addActionListener(this); 
	    selectOST.setBounds(620, 90, 120, 35);
	    add(selectOST);
	    
//	    omitStringLbl = new JLabel("String to Omit");
//	    omitStringLbl.setFont(new Font("Arial", Font.PLAIN, 24));
//	    omitStringLbl.setBounds(100, 150, 150, 40);
//	    omitStringLbl.setForeground(Color.white);
//	    add(omitStringLbl);
//	    
//	    omitStringVal = new JTextField();
//	    omitStringVal.setBounds(250, 150, 345, 35);
//	    omitStringVal.setFont(new Font("Arial", Font.PLAIN, 24));
//	    add(omitStringVal);
	  		    
//	    artVal = new JTextField();
//	    artVal.setBounds(100, 200, 500, 35);
//	    artVal.setFont(new Font("Arial", Font.PLAIN, 24));
//	    add(artVal);
//	  
//	   // JFileChooser fileSelect = new JFileChooser("C:\\Users\\iank1\\OneDrive\\Desktop\\Projects\\Java\\Euterpa-2\\MP3s"); 
//	    
//	    selectArt = new JButton("Select Art"); 
//	    selectArt.addActionListener(this); 
//	    selectArt.setBounds(620, 200, 100, 35);
//	    add(selectArt);
//	    
//	    seriesLbl = new JLabel("Series");
//	    seriesLbl.setFont(new Font("Arial", Font.PLAIN, 24));
//	    seriesLbl.setBounds(100, 250, 100, 40);
//	    seriesLbl.setForeground(Color.white);
//	    add(seriesLbl);
//	    
//	    seriesVal = new JTextField();
//	    seriesVal.setBounds(200, 250, 400, 35);
//	    seriesVal.setFont(new Font("Arial", Font.PLAIN, 24));
//	    add(seriesVal);
	    
//	    artistLbl = new JLabel("Director");
//	    artistLbl.setFont(new Font("Arial", Font.PLAIN, 24));
//	    artistLbl.setBounds(100, 300, 120, 40);
//	    artistLbl.setForeground(Color.white);
//	    add(artistLbl);
//	    
//	    artistVal = new JTextField();
//	    artistVal.setBounds(200, 300, 400, 35);
//	    artistVal.setFont(new Font("Arial", Font.PLAIN, 24));
//	    add(artistVal);
//
//	    directorLbl = new JLabel("Composer");
//	    directorLbl.setFont(new Font("Arial", Font.PLAIN, 24));
//	    directorLbl.setBounds(100, 350, 120, 40);
//	    directorLbl.setForeground(Color.white);
//	    add(directorLbl);
//	    
//	    directorVal = new JTextField();
//	    directorVal.setBounds(230, 350, 370, 35);
//	    directorVal.setFont(new Font("Arial", Font.PLAIN, 24));
//	    add(directorVal);
//	    
//	    mediumLbl = new JLabel("Medium");
//	    mediumLbl.setFont(new Font("Arial", Font.PLAIN, 24));
//	    mediumLbl.setBounds(100, 400, 100, 40);
//	    mediumLbl.setForeground(Color.white);
//	    add(mediumLbl);
//	    
//	    mediumVal = new JTextField();
//	    mediumVal.setBounds(200, 400, 400, 35);
//	    mediumVal.setFont(new Font("Arial", Font.PLAIN, 24));
//	    add(mediumVal);

//	    companyLbl = new JLabel("Company");
//	    companyLbl.setFont(new Font("Arial", Font.PLAIN, 24));
//	    companyLbl.setBounds(100, 450, 150, 40);
//	    companyLbl.setForeground(Color.white);
//	    add(companyLbl);
//	    
//	    companyVal = new JTextField();
//	    companyVal.setBounds(250, 450, 350, 35);
//	    companyVal.setFont(new Font("Arial", Font.PLAIN, 24));
//	    add(companyVal);
//	    
//	    yearLbl = new JLabel("Year");
//	    yearLbl.setFont(new Font("Arial", Font.PLAIN, 24));
//	    yearLbl.setBounds(100, 500, 100, 40);
//	    yearLbl.setForeground(Color.white);
//	    add(yearLbl);
//	    
//	    yearVal = new JTextField();
//	    yearVal.setBounds(200, 500, 400, 35);
//	    yearVal.setFont(new Font("Arial", Font.PLAIN, 24));
//	    add(yearVal);
//	    
//	    sortCodeLbl = new JLabel("Sort Code");
//	    sortCodeLbl.setFont(new Font("Arial", Font.PLAIN, 24));
//	    sortCodeLbl.setBounds(80, 550, 120, 40);
//	    sortCodeLbl.setForeground(Color.white);
//	    add(sortCodeLbl);
//	    
//	    sortCodeVal = new JTextField();
//	    sortCodeVal.setBounds(200, 550, 400, 35);
//	    sortCodeVal.setFont(new Font("Arial", Font.PLAIN, 24));
//	    add(sortCodeVal);
//
	    submit = new JButton("Delete Playlist"); 
	    submit.addActionListener(this); 
	    submit.setBounds(300, 600, 175, 35);
	    add(submit);
	} 
	
	 public void actionPerformed(ActionEvent evt) 
	 { 
	        // if the user presses the save button show the save dialog 
	        String com = evt.getActionCommand(); 
	  
	        if (com.equals("Select Playlist")) 
	        { 
	            // create an object of JFileChooser class 
	            JFileChooser fileChooser = new JFileChooser("C:\\Users\\iank1\\OneDrive\\Desktop\\Projects\\Java\\Euterpa3\\MP3s"); 
	  
	            // set the selection mode to directories only 
	            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	  
	            // invoke the showsSaveDialog function to show the save dialog 
	            int r = fileChooser.showSaveDialog(null); 
	  
	            if (r == JFileChooser.APPROVE_OPTION) 
	            { 
	                // set the label to the path of the selected directory 
	                ostValue.setText(fileChooser.getSelectedFile().getAbsolutePath()); 
	            } 
	            // if the user cancelled the operation 
	            else
	            {
	              //  l.setText("the user cancelled the operation"); 
	            } 
	        }
	        
	        if (com.equals("Select Art")) 
	        { 
	            // create an object of JFileChooser class 
	            JFileChooser fileChooser = new JFileChooser("C:\\Users\\iank1\\OneDrive\\Desktop\\Projects\\Java\\Euterpa3\\Art"); 
	  
	            // set the selection mode to directories only 
	            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	  
	            // invoke the showsSaveDialog function to show the save dialog 
	            int r = fileChooser.showSaveDialog(null); 
	  
	            if (r == JFileChooser.APPROVE_OPTION) 
	            { 
	                // set the label to the path of the selected directory 
	                artVal.setText(fileChooser.getSelectedFile().getAbsolutePath()); 
	            } 
	            // if the user cancelled the operation 
	            else
	            {
	              //  l.setText("the user cancelled the operation"); 
	            } 
	        }
	        
	        if (com.equals("Delete Playlist")) 
	        {
	        	try {
					//EuterpaClient.delete(ostValue.getText());
	        		JOptionPane.showMessageDialog(null, "This will really, really delete" + ostValue.getText() + " Are you sure?");

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	          
	  } 
	 
	 public void GetOST(String input)
	 {
		 try
		 {
			 Pattern pattern = Pattern.compile(OSTREGEXPATTERN);
				Matcher matcher = pattern.matcher(input);

				while (matcher.find())
				{
					ost = matcher.group(0);
				}
				dir = input.replace(ost, "");
				System.out.println("OST: "+ost);
				System.out.println("Dir: "+dir);
		 }
		 catch (Exception e)
		 {
			 e.printStackTrace();
		 }
		
	 }
}
