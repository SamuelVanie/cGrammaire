package cGrammaire;

import java.util.ArrayList;

class Symbole {
  private char symbole;
  private boolean estTerminal;
  private ArrayList<Symbole> first = new ArrayList<>();
  private ArrayList<Symbole> follow = new ArrayList<>();
  public Regle regle;
  

  /**
   * Symbole 
   * @param symb : nom du symbole
   * @param estTerminal : symbole terminal ou non ?*/
  public Symbole(char symb, boolean estTerminal) {
    this.symbole = symb;
    this.estTerminal = estTerminal;
  }

  public char getSymbole(){
    return this.symbole;
  }

  public boolean getEstTerminal(){
    return this.estTerminal;
  }

  public void setSymbole(char symb){
    this.symbole = symb;
  }

  public void setEstTerminal(boolean estTerminal){
    this.estTerminal = estTerminal;
  }

  public void construireRegle(String developpement){
    this.regle.construireDeveloppement(developpement);
  }

  public boolean equals(Symbole symb){
    boolean comp = this.getSymbole() == symb.getSymbole();
    return comp;
  }

  public ArrayList<Symbole> getFirst(){
    return this.first;
  }

  public ArrayList<Symbole> getFollow(){
    return this.follow;
  }

  public void afficherFirst(){
    for (Symbole symb : this.getFirst()) {
     System.out.println(symb.getSymbole()); 
    }
  }

  public void afficherFollow(){
    for (Symbole symb : this.getFollow()) {
     System.out.println(symb.getSymbole()); 
    }
  }


  public void setFirst(ArrayList<Symbole> symb){
    this.first = symb;
  }

  public void setFollow(ArrayList<Symbole> symb){
    this.follow = symb;
  }

  public void construireFirst(){}

  public void setRegle(Regle regle){
    this.regle = regle;
  }

  public Regle getRegle(){
    return this.regle;
  }

}
