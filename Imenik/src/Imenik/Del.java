/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Imenik;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Fika9
 */
public class Del extends JFrame  implements ActionListener{
    JButton b1,b2;
 Imenik pb;
 JComboBox jcbPhones;

   public Del(Imenik pb)
   {
     
     this.pb = pb;
     JLabel l1 = new JLabel("Ime");
     jcbPhones = new JComboBox(pb.getNames());
     b1= new JButton("Obrisi");
     b1.addActionListener(this);
     b2= new JButton("Exit");
     b2.addActionListener(this);
     JPanel p1 = new JPanel();
     p1.setLayout( new FlowLayout());

     JPanel p2 = new JPanel();
     p2.setLayout( new FlowLayout());
     Container c =   getContentPane();
     c.setLayout( new BorderLayout());
     p1.add(l1);
     p1.add(jcbPhones);
 
     p2.add(b1); 
     p2.add(b2);

     c.add(p1,"North");
     c.add(p2,"South");
       
     
   setSize(300,200);
   setVisible(true);
   setLocationRelativeTo(null);
  }

    @Override
   public void actionPerformed(ActionEvent evt)
   {
     if ( evt.getSource() == b2 )
           setVisible(false);
     else  
     {
      String ime;

      ime = (String)jcbPhones.getSelectedItem();
      int res  = JOptionPane.showConfirmDialog(this,"Da li zelite da obrisete?",
          "Brisanje",
          JOptionPane.YES_NO_OPTION,
          JOptionPane.QUESTION_MESSAGE);
      if ( res == JOptionPane.YES_OPTION)
      {  pb.delete(ime);
         pb.writeToDisk();
         jcbPhones = new JComboBox(pb.getNames());
      }
    } 

  }

 
}

