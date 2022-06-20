package cGrammaire;

import java.util.ArrayList;

class Regle {
  private ArrayList<ArrayList<Symbole>> developpement;

  /**
   * construireDeveloppement
   * 
   * @param dev : chaine de caractère définissant la règle
   * @return : void
   * 
   *
   * @fonction
   *           Construit le développement à partir de la chaine de caractère
   *           passée
   *           en paramètre.
   */
  public void construireDeveloppement(String dev) {
    String production = dev.split("::=")[1];
    production = production.strip();

    this.developpement = new ArrayList<ArrayList<Symbole>>();
    ArrayList<Symbole> tempProd = new ArrayList<Symbole>();

    for (String element : production.split("\\|")) {
      for (Character symbName : element.toCharArray()) {
        if(symbName != '|')
          tempProd.add(new Symbole(symbName, !Character.isUpperCase(symbName)));
      }

      this.developpement.add(tempProd);
      tempProd = new ArrayList<Symbole>();
    }
  }

  public void afficherDeveloppement(){
    for (ArrayList<Symbole> bloc : this.developpement) {
      for (Symbole symb : bloc) {
       System.out.print(symb.getSymbole()); 
      }
      System.out.println();
    } 
  }

  public ArrayList<ArrayList<Symbole>> getDevelopement(){
    return this.developpement;
  }

  public ArrayList<Character> getSuivant(char symbole){
    ArrayList<Character> suivant = new ArrayList<>();
    for (ArrayList<Symbole> groupe : this.developpement){
      for (int i=0; i<groupe.size(); i++){
        if(groupe.get(i).getSymbole() == symbole){
          if (i < groupe.size()-1)
            suivant.add(groupe.get(i+1).getSymbole());
        }
      }
    }
    return suivant;
  }

}
