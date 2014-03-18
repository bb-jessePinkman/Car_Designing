import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JButton;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;



public class FileChooserPage {

	   private JFrame mainFrame;
	   private JLabel headerLabel;
	   private JLabel statusLabel;
	   private JPanel controlPanel;
	   File ultimatixNameFile,tcToolNameFile;
	   int count=0;
	   JTabbedPane tabbedPane;
	   public FileChooserPage(){
		      prepareGUI();
		   }

	
	 public static void main(String[] args){
		 FileChooserPage swingControlDemo = new FileChooserPage();  
	     swingControlDemo.showFileChooser();       
	   }
	 
	 
	 
	 private void prepareGUI(){
		 tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		 
	      mainFrame = new JFrame("Billing System");
	      mainFrame.setSize(400,400);
	      mainFrame.setLayout(new GridLayout(3, 1));
	      
	      headerLabel = new JLabel("",JLabel.CENTER );
	      statusLabel = new JLabel("",JLabel.CENTER);        
	      
	      statusLabel.setSize(350,100);
	      
	      
	      mainFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
		        System.exit(0);
	         }        
	      });    
	      
	      
	      controlPanel = new JPanel();
	      controlPanel.setLayout(new FlowLayout());

	      mainFrame.add(headerLabel);
	      mainFrame.add(controlPanel);
	      
	      mainFrame.add(statusLabel);
	      mainFrame.setVisible(true);  
	   }
	 
	 
	 private void showFileChooser(){
	      headerLabel.setText("Control in action: JFileChooser"); 
	     
	      final JFileChooser  fileDialog = new JFileChooser();
	      JButton ultimatixFileButton = new JButton("Open Ultimatix Names File");
	      JButton tcToolFileButton = new JButton("Open TcTool Names File");
	      JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	      tabbedPane.addTab("Tab 1", new JLabel("This is tab 1"));
	      tabbedPane.addTab("Tab 2", new JLabel("This is tab 2"));
	      
	      ultimatixFileButton.addActionListener(new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent e) {
	            int returnVal = fileDialog.showOpenDialog(mainFrame);
	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	            	ultimatixNameFile = fileDialog.getSelectedFile();
	               System.out.println(ultimatixNameFile.toString());
	               statusLabel.setText("File Selected :" 
	               + ultimatixNameFile.getName());
	               count++;
	            }
	            else{
	               statusLabel.setText("Open command cancelled by user." );           
	            }      
	         }
	      });
	      tcToolFileButton.addActionListener(new ActionListener() {
		         @Override
		         public void actionPerformed(ActionEvent e) {
		            int returnVal = fileDialog.showOpenDialog(mainFrame);
		            if (returnVal == JFileChooser.APPROVE_OPTION) {
		            	tcToolNameFile = fileDialog.getSelectedFile();
		               System.out.println(tcToolNameFile.toString());
		               statusLabel.setText("File Selected :" 
		               + tcToolNameFile.getName());
		               count++;
		            }
		            else{
		               statusLabel.setText("Open command cancelled by user." );           
		            }      
		         }
		      });
	      
	      class ButtonClickListener implements ActionListener{
	          public void actionPerformed(ActionEvent e) {
	             String command = e.getActionCommand();  
	             if( command.equals( "OK" ))  {
	            	 if(count==2){
	      	    	   ReadExcel r=new ReadExcel();
	      	    	   r.fileProcessor(ultimatixNameFile, tcToolNameFile);
	      	    	   statusLabel.setText("File is been processing");
	      	    	   count=0;
	      	       }
	            	 else{
	            		 statusLabel.setText("Please select all the files");
	            	 }
	             }
	           /*  else if( command.equals( "Submit" ) )  {
	                statusLabel.setText("Submit Button clicked."); 
	             }
	             else  {
	                statusLabel.setText("Cancel Button clicked.");
	             }  */	
	          }		
	       }
	      controlPanel.add(ultimatixFileButton);
	      controlPanel.add(tcToolFileButton);
	      
	     
	       
	     
	      
	      JButton okButton = new JButton("OK");
	      okButton.setActionCommand("OK");
	      okButton.addActionListener(new ButtonClickListener());
	      controlPanel.add(okButton);
	      mainFrame.setVisible(true); 
	       if(count==2){
	    	   ReadExcel r=new ReadExcel();
	    	   r.fileProcessor(ultimatixNameFile, tcToolNameFile);
	    	   count=0;
	       }
	   }
}
