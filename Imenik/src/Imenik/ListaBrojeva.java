/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Imenik;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Fika9
 */
public class ListaBrojeva extends JFrame  implements ActionListener {
    
JButton b1;
  Imenik pb;
 JTextArea  ta;
   public ListaBrojeva(Imenik pb)
   {
    
     this.pb = pb;
     b1= new JButton("Exit");
     b1.addActionListener((ActionListener) this);
     ta = new JTextArea();     
     Container c  = getContentPane();
     c.setLayout(new BorderLayout());
     JScrollPane sp = new JScrollPane(ta,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
     c.add(sp,"Center");
     c.add(b1,"South");
     setSize(300,200);
     setVisible(true);
     setLocationRelativeTo(null);
     
     ta.setText(pb.getAllEntries());
      ta.setEditable(false);
      

   } 

@Override
   public void actionPerformed(ActionEvent evt)
   {
     if ( evt.getSource() == b1 )
           this.setVisible(false);
   }
}
