package com.kliner.Euterpa3;


	import java.awt.Color;
import java.awt.Font;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.util.ArrayList;
	import java.util.Arrays;
	import java.util.regex.Matcher;
	import java.util.regex.Pattern;

	import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
	import javax.swing.JLabel;
	import javax.swing.JPanel;
	import javax.swing.JScrollPane;
	import javax.swing.JTextArea;
	import javax.swing.JTextField;
	import javax.swing.filechooser.FileSystemView;
	import javax.swing.colorchooser.*;
	
	public class EuterpaEditPanel extends JPanel implements ActionListener {

		//String MP3folder = "D:\\Music\\";
		String MP3folder = Globals.MUSIC_LIBRARY_PATH;
		JLabel titleLabel, pageLbl, omitStringLbl, timestampsLbl, seriesLbl, artistLbl, directorLbl, 
		mediumLbl, companyLbl, yearLbl, artLbl, sortCodeLbl, removeNumsLbl, addTrackNumsLbl;
		JTextField ostValue, omitStringVal, seriesVal, artistVal, directorVal, 
		mediumVal, companyVal, yearVal, artVal, sortCodeVal;
		JTextArea timestamps;
		JScrollPane sp;
		JButton selectOST, selectArt, submit;
		String OSTREGEXPATTERN = "(\\w|\\s)+(.mp3)";
		String ost, dir;
		
		JCheckBox removeNumbers, addTrackNumbers;
		
		public EuterpaEditPanel()
		{
			super();
			setSize(800,1000);
		    setVisible(true);
		    
		    setLayout(null);
		   
		    
		    titleLabel = new JLabel("SoundsFun");
		    titleLabel.setFont(new Font("Arial", Font.PLAIN, 24));
		    titleLabel.setBounds(350, 30, 150, 40);
		    titleLabel.setForeground(Color.white);
		    setBackground(Color.black);
		    
		    add(titleLabel);
		    
		    pageLbl = new JLabel("Edit a Playlist");
		    pageLbl.setFont(new Font("Arial", Font.PLAIN, 24));
		    pageLbl.setBounds(330, 55, 150, 40);
		    pageLbl.setForeground(Color.white);
		    add(pageLbl);
		    
		    ostValue = new JTextField(Globals.MUSIC_LIBRARY_PATH);
		    ostValue.setBounds(100, 90, 500, 35);
		    ostValue.setFont(new Font("Arial", Font.PLAIN, 24));
		    add(ostValue);
		    
		    selectOST = new JButton("Select Playlist"); 
		    selectOST.addActionListener(this); 
		    selectOST.setBounds(620, 90, 120, 35);
		    add(selectOST);
		    
		    omitStringLbl = new JLabel("String to Omit");
		    omitStringLbl.setFont(new Font("Arial", Font.PLAIN, 24));
		    omitStringLbl.setBounds(100, 150, 150, 40);
		    omitStringLbl.setForeground(Color.white);
		    add(omitStringLbl);
		    
		    omitStringVal = new JTextField();
		    omitStringVal.setBounds(250, 150, 345, 35);
		    omitStringVal.setFont(new Font("Arial", Font.PLAIN, 24));
		    add(omitStringVal);

		    artistLbl = new JLabel("Director");
		    artistLbl.setFont(new Font("Arial", Font.PLAIN, 24));
		    artistLbl.setBounds(100, 300, 120, 40);
		    artistLbl.setForeground(Color.white);
		    add(artistLbl);
		    
		    artistVal = new JTextField();
		    artistVal.setBounds(200, 300, 400, 35);
		    artistVal.setFont(new Font("Arial", Font.PLAIN, 24));
		    add(artistVal);

		    directorLbl = new JLabel("Composer");
		    directorLbl.setFont(new Font("Arial", Font.PLAIN, 24));
		    directorLbl.setBounds(100, 350, 120, 40);
		    directorLbl.setForeground(Color.white);
		    add(directorLbl);
		    
		    directorVal = new JTextField();
		    directorVal.setBounds(230, 350, 370, 35);
		    directorVal.setFont(new Font("Arial", Font.PLAIN, 24));
		    add(directorVal);
		    
		    removeNumsLbl = new JLabel("Remove leading numbers?");
		    removeNumsLbl.setFont(new Font("Arial", Font.PLAIN, 24));
		    removeNumsLbl.setBounds(100, 550, 500, 40);
		    removeNumsLbl.setForeground(Color.white);
		    add(removeNumsLbl);
		    
		    removeNumbers = new JCheckBox();
		    removeNumbers.setBounds(590, 560, 25, 25);
		    add(removeNumbers);
		    
		    addTrackNumsLbl = new JLabel("Add track numbers and change all tracks?");
		    addTrackNumsLbl.setFont(new Font("Arial", Font.PLAIN, 24));
		    addTrackNumsLbl.setBounds(100, 600, 500, 40);
		    addTrackNumsLbl.setForeground(Color.white);
		    add(addTrackNumsLbl);
		    
		    addTrackNumbers = new JCheckBox();
		    addTrackNumbers.setBounds(590, 610, 25, 25);
		    add(addTrackNumbers);

		    companyLbl = new JLabel("Studio");
		    companyLbl.setFont(new Font("Arial", Font.PLAIN, 24));
		    companyLbl.setBounds(100, 400, 150, 40);
		    companyLbl.setForeground(Color.white);
		    add(companyLbl);
		    
		    companyVal = new JTextField();
		    companyVal.setBounds(250, 400, 350, 35);
		    companyVal.setFont(new Font("Arial", Font.PLAIN, 24));
		    add(companyVal);
		    
		    yearLbl = new JLabel("Year");
		    yearLbl.setFont(new Font("Arial", Font.PLAIN, 24));
		    yearLbl.setBounds(100, 450, 100, 40);
		    yearLbl.setForeground(Color.white);
		    add(yearLbl);
		    
		    mediumLbl = new JLabel("Medium");
		    mediumLbl.setFont(new Font("Arial", Font.PLAIN, 24));
		    mediumLbl.setBounds(80, 500, 120, 40);
		    mediumLbl.setForeground(Color.white);
		    add(mediumLbl);
		    
		    mediumVal = new JTextField();
		    mediumVal.setBounds(200, 500, 400, 35);
		    mediumVal.setFont(new Font("Arial", Font.PLAIN, 24));
		    add(mediumVal);
		    
		    yearVal = new JTextField();
		    yearVal.setBounds(200, 450, 400, 35);
		    yearVal.setFont(new Font("Arial", Font.PLAIN, 24));
		    add(yearVal);
		    
		    sortCodeLbl = new JLabel("Sort Code");
		    sortCodeLbl.setFont(new Font("Arial", Font.PLAIN, 24));
		    sortCodeLbl.setBounds(80, 700, 120, 40);
		    sortCodeLbl.setForeground(Color.white); 
		    add(sortCodeLbl);
		    
		    sortCodeVal = new JTextField();
		    sortCodeVal.setBounds(200, 700, 400, 35);
		    sortCodeVal.setFont(new Font("Arial", Font.PLAIN, 24));
		    add(sortCodeVal);


		    submit = new JButton("Edit Playlist"); 
		    submit.addActionListener(this); 
		    submit.setBounds(300, 750, 175, 35);
		    add(submit);
		} 
		
		 public void actionPerformed(ActionEvent evt) 
		 { 
		        // if the user presses the save button show the save dialog 
		        String com = evt.getActionCommand(); 
		  
		        if (com.equals("Select Playlist")) 
		        { 
		            // create an object of JFileChooser class 
		            JFileChooser fileChooser = new JFileChooser(MP3folder); 
		  
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
		        
		        if (com.equals("Edit Playlist")) 
		        {
		        	EuterpaClient.edit(ostValue.getText(), 
		        	omitStringVal.getText(),
		        	directorVal.getText(),
		        	artistVal.getText(),
		        	companyVal.getText(), yearVal.getText(),
		        	removeNumbers.isSelected(),
		        	addTrackNumbers.isSelected(),
		        	sortCodeVal.getText());
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


