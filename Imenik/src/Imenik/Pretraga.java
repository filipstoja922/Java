/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Imenik;

import static com.sun.glass.ui.Cursor.setVisible;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Fika9
 */
public class Pretraga extends JFrame implements ActionListener  {
    
JTextField tfName;
 JButton b1;
 Imenik pb;
 JTextArea taResult;
 JCheckBox cbIgnoreCase;

   public Pretraga(Imenik pb)
   {
    
     this.pb = pb;
     JLabel l1 = new JLabel("Ime");
     tfName = new JTextField(20);  
     taResult = new JTextArea();

     b1= new JButton("Pretrazi");
     b1.addActionListener(this);

     cbIgnoreCase = new JCheckBox("Ignorisi");

     Container c = getContentPane();
     JPanel p = new JPanel();
     p.setLayout( new FlowLayout());

     p.add( l1);
     p.add(tfName);
     p.add(cbIgnoreCase);
     p.add(b1);

     c.add(p,"North");

     JScrollPane sp = new JScrollPane(taResult,
                             JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                             JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
     c.add(sp,"Center");

     setSize(500,200);
     setVisible(true);
     setLocationRelativeTo(null);
     

     this.addWindowListener( new WinCode());

   } 

   class WinCode extends WindowAdapter
   {
    @Override
    public void windowClosing(WindowEvent evt) {  setVisible(false); }
   }


@Override
   public void actionPerformed(ActionEvent evt)
   {
     if ( evt.getSource() == b1 )  
     {
       if ( cbIgnoreCase.isSelected() )
         taResult.setText(pb.getSelectedEntries(tfName.getText(),true));
       else
         taResult.setText(pb.getSelectedEntries(tfName.getText(), false));
     }

  }


}
