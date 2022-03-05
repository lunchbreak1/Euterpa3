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

public class EuterpaPanel extends JPanel implements ActionListener
{
	//String MP3folder = "D:\\Music\\";
	String MP3folder = "C:\\Users\\iank1\\OneDrive\\Desktop\\Projects\\Java\\Euterpa3\\MP3s\\";
	JLabel titleLabel, pageLbl, timestampsLbl, seriesLbl, artistLbl, directorLbl, mediumLbl, companyLbl, yearLbl, artLbl, sortCodeLbl, removeCharsLbl, trimCharsLbl, removeNumsLbl;
	JTextField ostValue, seriesVal, artistVal, directorVal, mediumVal, companyVal, yearVal, artVal, sortCodeVal, removeChars, trimChars;
	JTextArea timestamps;
	JScrollPane sp;
	JButton selectOST, selectArt, submit;
	String OSTREGEXPATTERN = "(\\w|\\s)+(.mp3)";
	String ost, dir;
	
	String defaultRemoveCharacters = "/\\\"\'.:?";
	String defaultTrimCharacters = "/\\\"\':.-";
	
	JCheckBox removeNumbers;
	
	public EuterpaPanel()
	{
		super();
		setSize(800,650);
	    setVisible(true);
	    setLayout(null);
	    setBackground(Color.black);
	    
	    titleLabel = new JLabel("eUterpa");
	    titleLabel.setFont(new Font("Arial", Font.PLAIN, 24));
	    titleLabel.setBounds(350, 30, 100, 40);
	    titleLabel.setForeground(Color.white);
	    add(titleLabel);
	    
	    pageLbl = new JLabel("Create a Playlist");
	    pageLbl.setFont(new Font("Arial", Font.PLAIN, 24));
	    pageLbl.setBounds(330, 55, 180, 40);
	    pageLbl.setForeground(Color.white);
	    add(pageLbl);
	    
	    ostValue = new JTextField();
	    ostValue.setBounds(100, 90, 500, 35);
	    ostValue.setFont(new Font("Arial", Font.PLAIN, 24));
	    add(ostValue);
	  
	   // JFileChooser fileSelect = new JFileChooser("C:\\Users\\iank1\\OneDrive\\Desktop\\Projects\\Java\\Euterpa-2\\MP3s"); 
	    
	    selectOST = new JButton("Select OST"); 
	    selectOST.addActionListener(this); 
	    selectOST.setBounds(620, 90, 100, 35);
	    add(selectOST);
	    
	    timestampsLbl = new JLabel("Timestamps");
	    timestampsLbl.setBounds(100, 150, 500, 35);
	    timestampsLbl.setFont(new Font("Arial", Font.PLAIN, 24));
	    timestampsLbl.setForeground(Color.white);
	    add(timestampsLbl);
	    
	    timestamps = new JTextArea();
	    timestamps.setBounds(100, 185, 500, 200);
	    timestamps.setFont(new Font("Arial", Font.PLAIN, 24));
	    add(timestamps);
	    
	    sp = new JScrollPane(timestamps);
	    sp.setBounds(100, 185, 500, 200);
	    add(sp);
	   
	    
	    removeCharsLbl = new JLabel("Remove characters");
	    removeCharsLbl.setFont(new Font("Arial", Font.PLAIN, 24));
	    removeCharsLbl.setBounds(100, 400, 230, 40);
	    removeCharsLbl.setForeground(Color.white);
	    add(removeCharsLbl);
	    
	    removeChars = new JTextField(defaultRemoveCharacters);
	    removeChars.setBounds(350, 400, 270, 35);
	    removeChars.setFont(new Font("Arial", Font.PLAIN, 24));
	    add(removeChars);
	    
	    trimCharsLbl = new JLabel("Trim characters");
	    trimCharsLbl.setFont(new Font("Arial", Font.PLAIN, 24));
	    trimCharsLbl.setBounds(100, 450, 230, 40);
	    trimCharsLbl.setForeground(Color.white);
	    add(trimCharsLbl);
	    
	    trimChars = new JTextField(defaultTrimCharacters);
	    trimChars.setBounds(350, 450, 270, 35);
	    trimChars.setFont(new Font("Arial", Font.PLAIN, 24));
	    add(trimChars);
	    
	    removeNumsLbl = new JLabel("Remove numbers? (doesn't affect time)");
	    removeNumsLbl.setFont(new Font("Arial", Font.PLAIN, 24));
	    removeNumsLbl.setBounds(100, 500, 500, 40);
	    removeNumsLbl.setForeground(Color.white);
	    add(removeNumsLbl);
	    
	    removeNumbers = new JCheckBox();
	    removeNumbers.setBounds(590, 510, 25, 25);
	    add(removeNumbers);
	    
	    
	    artistLbl = new JLabel("Director");
	    artistLbl.setFont(new Font("Arial", Font.PLAIN, 24));
	    artistLbl.setBounds(100, 550, 150, 40);
	    artistLbl.setForeground(Color.white);
	    add(artistLbl);
	    
	    artistVal = new JTextField();
	    artistVal.setBounds(220, 550, 400, 35);
	    artistVal.setFont(new Font("Arial", Font.PLAIN, 24));
	    add(artistVal);

	    directorLbl = new JLabel("Composer");
	    directorLbl.setFont(new Font("Arial", Font.PLAIN, 24));
	    directorLbl.setBounds(100, 600, 120, 40);
	    directorLbl.setForeground(Color.white);
	    add(directorLbl);
	    
	    directorVal = new JTextField();
	    directorVal.setBounds(220, 600, 380, 35);
	    directorVal.setFont(new Font("Arial", Font.PLAIN, 24));
	    add(directorVal);
	    
//	    mediumLbl = new JLabel("Medium");
//	    mediumLbl.setFont(new Font("Arial", Font.PLAIN, 24));
//	    mediumLbl.setBounds(100, 600, 100, 40);
//	    add(mediumLbl);
//	    
//	    mediumVal = new JTextField();
//	    mediumVal.setBounds(200, 600, 400, 35);
//	    mediumVal.setFont(new Font("Arial", Font.PLAIN, 24));
//	    add(mediumVal);

	    companyLbl = new JLabel("Studio");
	    companyLbl.setFont(new Font("Arial", Font.PLAIN, 24));
	    companyLbl.setBounds(100, 650, 150, 40);
	    companyLbl.setForeground(Color.white);
	    add(companyLbl);
	    
	    companyVal = new JTextField();
	    companyVal.setBounds(200, 650, 400, 35);
	    companyVal.setFont(new Font("Arial", Font.PLAIN, 24));
	    add(companyVal);
	    
	    yearLbl = new JLabel("Year");
	    yearLbl.setFont(new Font("Arial", Font.PLAIN, 24));
	    yearLbl.setBounds(100, 700, 300, 40);
	    yearLbl.setForeground(Color.white);
	    add(yearLbl);
	    
	    yearVal = new JTextField();
	    yearVal.setBounds(200, 700, 400, 35);
	    yearVal.setFont(new Font("Arial", Font.PLAIN, 24));
	    add(yearVal);
	    
//	    sortCodeLbl = new JLabel("Sort Code");
//	    sortCodeLbl.setFont(new Font("Arial", Font.PLAIN, 24));
//	    sortCodeLbl.setBounds(80, 750, 120, 40);
//	    add(sortCodeLbl);
//	    
//	    sortCodeVal = new JTextField();
//	    sortCodeVal.setBounds(200, 750, 400, 35);
//	    sortCodeVal.setFont(new Font("Arial", Font.PLAIN, 24));
//	    add(sortCodeVal);

	    submit = new JButton("Create Playlist"); 
	    submit.addActionListener(this); 
	    submit.setBounds(300, 750, 175, 35);
	    add(submit);
	    
	    
	 // Open the save dialog 
	// j.showSaveDialog(null); 
	   // add(j);
	    
	    /*
	     * JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
  
            // set the selection mode to directories only 
            j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
  
            // invoke the showsOpenDialog function to show the save dialog 
            int r = j.showOpenDialog(null); 
  
            if (r == JFileChooser.APPROVE_OPTION) { 
                // set the label to the path of the selected directory 
                l.setText(j.getSelectedFile().getAbsolutePath()); 
            } 
            // if the user cancelled the operation 
            else
                l.setText("the user cancelled the operation"); 41
	     */
	} 
	
	 public void actionPerformed(ActionEvent evt) 
	 { 
	        // if the user presses the save button show the save dialog 
	        String com = evt.getActionCommand(); 
	  
	        if (com.equals("Select OST")) 
	        { 
	            // create an object of JFileChooser class 
	            JFileChooser fileChooser = new JFileChooser("C:\\Users\\iank1\\OneDrive\\Desktop\\Projects\\Java\\Euterpa3\\MP3s"); 
	  
	            // set the selection mode to directories only 
	            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	  
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
	        
	        if (com.equals("Create Playlist")) 
	        {
	        	GetOST(ostValue.getText());
	        	
	        	String s[] = timestamps.getText().split("\\r?\\n");
	            ArrayList<String>timestampList = new ArrayList<String>(Arrays.asList(s));
	        	
	        /*	EuterpaClient euterpa = new EuterpaClient(ost, dir, 
	        	timestampList, artVal.getText(), seriesVal.getText(), 
	        	artistVal.getText(),
	        	directorVal.getText(), mediumVal.getText(), 
	        	companyVal.getText(), yearVal.getText(), sortCodeVal.getText()); */
	            
	         /*   EuterpaClient.run(ost, dir, 
	    	        	timestampList, "", "", 
	    	        	directorVal.getText(), artistVal.getText(),
	    	        	"", 
	    	        	companyVal.getText(), yearVal.getText(), ""); */
	            
	            EuterpaClient.run(ost, dir, 
	    	        	timestampList, "", "", 
	    	        	directorVal.getText(), artistVal.getText(),
	    	        	"", 
	    	        	companyVal.getText(), yearVal.getText(), "",
	    	        	removeChars.getText(), trimChars.getText(),
	    	        	removeNumbers.isSelected());
	            
	            
	            
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
