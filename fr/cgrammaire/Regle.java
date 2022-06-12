package fr.cgrammaire;

import java.util.ArrayList;

class Regle{
  private String nom;
  private ArrayList<ArrayList<Symbole>> developpement;
  private ArrayList<Symbole> first;
  private ArrayList<Symbole> follow;

  /**
   * construireDeveloppement
   * @param dev : chaine de caractère définissant la règle
   * @return : void
   * 
   *
   * @fonction
   * Construit le développement à partir de la chaine de caractère passée
   * en paramètre. */
  public void construireDeveloppement(String dev){
    String production = dev.split(":==")[1];
    production = production.strip();

    ArrayList<Symbole> tempProd;

    for (String element : production.split("|")) {
     for (Char symbName : element.toCharArray()){
        tempProd.add(new Symbole(symbName, symbName.isUpperCase()));
      }

      this.developpement.add(tempProd);
      tempProd = new ArrayList<Symbole>();
    }

  }
}
