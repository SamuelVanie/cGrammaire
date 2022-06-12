package fr.cgrammaire;

import java.util.ArrayList;

class Symbole {
  private String symbole;
  private bool estTerminal;

  /**
   * Symbole 
   * @param symb : nom du symbole
   * @param estTerminal : symbole terminal ou non ?*/
  public Symbole(String symb, bool estTerminal) {
    this.symbole = symb;
    this.estTerminal = estTerminal;

  }

  public String getSymbole(){
    return this.symbole;
  }

  public bool getEstTerminal(){
    return this.estTerminal;
  }

  public void setSymbole(String symb){
    this.symbole = symb;
  }

  public void setEstTerminal(bool estTerminal){
    this.estTerminal = estTerminal;
  }

}
