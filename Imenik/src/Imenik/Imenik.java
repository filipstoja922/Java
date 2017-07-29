/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Imenik;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.Vector;

/**
 *
 * @author Fika9
 */
public class Imenik {
   private Vector  entries;
    public Imenik()
    {
       entries = new Vector();
    }

    public void add(Unos pe)
    {
        entries.add(pe);
    }

    public boolean delete(String name)
   {
      Iterator  itr;

      itr = entries.iterator();
      while (  itr.hasNext())
      {
         Unos pe = (Unos) itr.next();
         if ( pe.ime.equals(name) )
         {
             itr.remove();
             return  true;
         }
      }
      return false;
     } 

    public Unos  getPhoneEntry(String name)
    {
      Iterator  itr;

      itr = entries.iterator();
      while (  itr.hasNext())
      {
         Unos pe = (Unos) itr.next();
         if ( pe.ime.equals(name) )
         {
            return  pe;
         }

     }
     return null;


    }     
 
     public void writeToDisk()
     {
      try
      {
       FileWriter fw = new FileWriter("telefoni.dat");

      Iterator itr = entries.iterator();
     
      while ( itr.hasNext())
      {
       Unos pe =(Unos) itr.next();           
       fw.write( pe.toString() + "\n");
      }
      fw.close();
     }
     catch(Exception ex)
     {  System.out.println(ex); }
    } 



    public void readFromDisk()
    {
    String line;
    String ime,brojTelefona;
    char ptype;
    int p1,p2;
    try
    {
     FileReader fr = new FileReader("phones.dat");
     BufferedReader br  = new BufferedReader(fr);

     while (  (line = br.readLine() ) != null)
     {
     if ( line.trim().length() == 0 ) continue;

     p1 = line.indexOf(":");
     ime = line.substring(0,p1);
     p2 = line.indexOf(":",p1+1);
     brojTelefona =  line.substring(p1+1,p2);
     ptype = line.charAt(p2+1);
     Unos pe = new Unos(ime,brojTelefona,ptype);
     entries.add(pe);
    } 
    br.close();
   }
   catch(Exception ex)
   {  System.out.println(ex); }
  
  } 
        


  public String getAllEntries()
  {
      String st= "";
    Iterator itr = entries.iterator();
    while ( itr.hasNext())
       st = st + itr.next().toString() + "\n";
    return st;
  }



  public String getSelectedEntries(String pat,boolean ignorecase)
  {
   String st= "",name;
   Unos pe;

    if (ignorecase)
          pat= pat.toUpperCase();

    Iterator itr = entries.iterator();
    while (itr.hasNext())
    {
     
       pe = (Unos) itr.next();
       if ( ignorecase)
             name = pe.ime.toUpperCase();
       else
             name= pe.ime;
        
       if ( name.indexOf(pat) >= 0 )
            st = st + pe.toString() + "\n";
    }
    return st;
  }



  public Vector getNames()
  {
    Vector v = new Vector();

    Iterator itr = entries.iterator();
    Unos pe;
    while ( itr.hasNext())
    {
       pe = (Unos) itr.next(); 
       v.add( pe.ime);
    }

    return v;

  }
     


 }

