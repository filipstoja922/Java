/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Imenik;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author Fika9
 */
public final class NoviUnos extends JFrame implements ActionListener {
    
 GridBagConstraints gbc;
 GridBagLayout gbl;
 JTextField tf1,tf2;
 JRadioButton rindv, rcomp;
 JButton b1,b2;
Imenik pb;
   public NoviUnos(Imenik pb)
   {
    
     this.pb = pb;
     JLabel l1 = new JLabel("Ime");
     tf1 = new JTextField(20);
     JLabel l2 = new JLabel("Broj ");
     tf2 = new JTextField(20);
    
     JLabel l3 = new JLabel("Tip");

     rindv = new JRadioButton("Prijatelj");
     rcomp = new JRadioButton("Kolega");

     ButtonGroup bg = new ButtonGroup();
     bg.add(rindv);
     bg.add(rcomp);
     
     b1= new JButton("Dodaj");
     b1.addActionListener(this);
     b2= new JButton("Izadji");
     b2.addActionListener(this);
     
     gbl = new GridBagLayout();
     gbc  = new GridBagConstraints();
     getContentPane().setLayout(gbl);
     displayComponent(l1,0,0,1,1);
     displayComponent(tf1,1,0,2,1);
     displayComponent(l2,0,1,1,1);
     displayComponent(tf2,1,1,2,1);
     displayComponent(l3,0,2,1,1);
     displayComponent(rindv,1,2,1,1);
     displayComponent(rcomp,2,2,1,1);
     displayComponent(b1,1,3,1,1);     
     displayComponent(b2,2,3,1,1);     

     setSize(300,200);
     setVisible(true);
     setLocationRelativeTo(null);
     

   } 

   @Override
   public void actionPerformed(ActionEvent evt)
   {
     if ( evt.getSource() == b2 )
           this.setVisible(false);
        else
     {
      String ime,brojTelefona;
      char tip;

      ime = tf1.getText();
      brojTelefona = tf2.getText();
      if ( rindv.isSelected())
          tip  = 'P';
      else
         tip = 'K';
 
     Unos pe = new Unos(ime,brojTelefona,tip);
     pb.add(pe);
     pb.writeToDisk();
     } 

   }

 public void displayComponent(Component c, int x, int y, int w, int h)
 {
    gbc.gridx = x;
    gbc.gridy = y;
    gbc.gridwidth = w;
    gbc.gridheight = h;
    gbl.setConstraints(c,gbc);
    getContentPane().add(c);
 } 
 
 
}
