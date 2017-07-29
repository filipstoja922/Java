/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Imenik;




import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



/**
 *
 * @author Fika9
 */
public class GlavniProzor extends JFrame implements ActionListener {
   
   JPanel panel = new JPanel();
   Imenik pb = new Imenik();
   
   
  
   JButton  btnNoviUnos, btnLista,btnSearch,btnExit,btnDelite;

   public GlavniProzor()
   {
    
      pb.readFromDisk();
      Ikonice();
      setVisible(true);
      setSize(700,500);
      setLocationRelativeTo(null); 
      
       
    
   } 

   

  public void Ikonice()
  {
    
      
      setContentPane(panel);
      
      
      

   Icon  img1 = new ImageIcon("Add-Male-User.png");
   Icon  img2 = new ImageIcon("search.jpg");
   Icon  img3 = new ImageIcon("download.png");
   Icon  img4 = new ImageIcon("exit.jpg");
   Icon  img5 = new ImageIcon("Delete-icon.png");

 
   btnNoviUnos = new JButton(img1);
   btnNoviUnos.addActionListener(this);
   
   btnSearch = new JButton(img2);
   btnSearch.addActionListener(this);

   btnLista = new JButton(img3);
   btnLista.addActionListener(this);
   
   btnExit = new JButton(img4);
   btnExit.addActionListener(this);
   
   btnDelite = new JButton(img5);
   btnDelite.addActionListener(this);


   panel.add(btnNoviUnos);
   panel.add(btnLista);
   panel.add(btnSearch);
   panel.add(btnExit);
   panel.add(btnDelite);
   
  
 } 
  
   @Override
  public void actionPerformed(ActionEvent evt)
  {
    Object source = evt.getSource();
     if ( source == btnExit)
           exitProgram();
     else
        if (source == btnNoviUnos)
        {
           new NoviUnos(pb);
        }
       else
         if ( source == btnLista)
          {
            new ListaBrojeva(pb);
           }
         else
          if ( source == btnSearch)
          {   new Pretraga(pb); }
     else
         if ( source == btnDelite)
          {
            new Del(pb);
           }

  }
  
  public void exitProgram()
  {
     int r= JOptionPane.showConfirmDialog(this,  
            "Da li zelite da izadjete iz programa?",
            "Exit",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);

     if ( r == JOptionPane.YES_OPTION)
            System.exit(0);
  } 
                            
public static void main(String args[])
  {
    new GlavniProzor();
  }

} 

