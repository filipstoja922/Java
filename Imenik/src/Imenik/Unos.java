/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Imenik;

/**
 *
 * @author Fika9
 */
class Unos {
  String ime, brojTelefona;
   char tip;
   
   public Unos(String name, String brojTelefona, char tip)
   {
      this.ime = name;
      this.brojTelefona  = brojTelefona;
      this.tip = tip;
   }

  @Override
   public String toString()
   {
      return  ime + ":" + brojTelefona + ":" + tip;
   }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public void setTip(char tip) {
        this.tip = tip;
    }

    public String getIme() {
        return ime;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public char getTip() {
        return tip;
    }

}
