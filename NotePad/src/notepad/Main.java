
package notepad;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.metal.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import javax.swing.text.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class notepad extends JFrame implements ActionListener{
    JTextArea t;
    JFrame f;
    
    JScrollPane sp;
    
    
    notepad(){
        //initializing the frame and the textarea
        f = new JFrame("Notepad");
        
        t = new JTextArea(); 
                
        sp = new JScrollPane(t);
    
        JMenuBar menu = new JMenuBar();
    
        JMenu file = new JMenu("File");
    
        JMenuItem f1 = new JMenuItem("New");
        JMenuItem f2 = new JMenuItem("Open");
        JMenuItem f3 = new JMenuItem("Save");
        JMenuItem f4 = new JMenuItem("Print");

        //adding actionListener to individual menuitems
        f1.addActionListener(this);
        f2.addActionListener(this);
        f3.addActionListener(this);
        f4.addActionListener(this);

        //adding menuitems to the file menu
        file.add(f1);
        file.add(f2);
        file.add(f3);
        file.add(f4);
        
        JMenu edit = new JMenu("Edit");
    
        JMenuItem f5 = new JMenuItem("Cut");
        JMenuItem f6 = new JMenuItem("Copy");
        JMenuItem f7 = new JMenuItem("Paste");

        //adding actionListener to individual menuitems
        f5.addActionListener(this);
        f6.addActionListener(this);
        f7.addActionListener(this);

        //adding menuitems to the file menu
        edit.add(f5);
        edit.add(f6);
        edit.add(f7);
 
        JMenu close = new JMenu("Close Notepad");
        
        JMenuItem f8 = new JMenuItem("Close");
        
        f8.addActionListener(this);
        
        close.add(f8);
        
        menu.add(file);
        menu.add(edit);
        menu.add(close);
        
        f.getContentPane().add(sp);
        f.setJMenuBar(menu);
        //f.add(t);
        f.setSize(1000, 800);
        f.show();

    }

    //functionality implementation 
    public void actionPerformed(ActionEvent e){
        //getting the user click in form of a string
        String s = e.getActionCommand();
        
        switch(s){
            case "New": t.setText("");
                break;
            case "Open":
                // creating object of jfilechooser class ans initializing it to a location in the computer
                JFileChooser j = new JFileChooser("C:\\Users\\Nagendra\\Desktop\\");
                
                //initializing the opendialogbox
                int r = j.showOpenDialog(null);
                
                //if the user selects a file
                if(r == JFileChooser.APPROVE_OPTION){
                    
                    //extracting the absolute path of the selected file
                    File fi = new File(j.getSelectedFile().getAbsolutePath());
                    
                    String s1, s2;
                    try {
                        //place pointer at the staring of the file
                        FileReader fr = new FileReader(fi);
                        
                        //use bufferedreader to read line by line
                        BufferedReader br = new BufferedReader(fr);
                        
                       //storing the first line in s1
                       s1 = br.readLine();
                       
                       //appending sbusequent lines till end of the file is reached
                       while((s2 = br.readLine()) != null){
                           s1 = s1 + "\n" + s2;
                       }
                       
                       t.setText(s1);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(notepad.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                         Logger.getLogger(notepad.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                else{
                    JOptionPane.showMessageDialog(f, "You have cancelled file opening operation.");
                }
                
                break;
                
            case "Save":

                JFileChooser j1 = new JFileChooser("C:\\Users\\Nagendra\\Desktop\\");
                
                //initializing the opendialogbox
                int r1 = j1.showSaveDialog(null);
                
                //if the user selects a file
                if(r1 == JFileChooser.APPROVE_OPTION){
                    
                    //extracting the absolute path of the selected file
                    File fi = new File(j1.getSelectedFile().getAbsolutePath());
                    
                    try {
                        //place pointer at the staring of the file
                        FileWriter fr = new FileWriter(fi);
                        
                        //use bufferedreader to read line by line
                        BufferedWriter br = new BufferedWriter(fr);
                        
                        br.write(t.getText());
                        br.flush();
                        br.close();
                       
                       
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(notepad.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                         Logger.getLogger(notepad.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                else{
                    JOptionPane.showMessageDialog(f, "You have cancelled file saving operation.");
                }
                
                break;
            case "Print": {
            try {
                t.print();
            } catch (PrinterException ex) {
                Logger.getLogger(notepad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                break;
            case "Cut": t.cut();
                break;
            case "Copy": t.copy();
                break;
            case "Paste": t.paste();
                break;
            case "Close": f.setVisible(false);
                break;
                
        }
    }
}

public class Main {
    public static void main(String[] args) {
        notepad note = new notepad();
        
    }
    
}

















































