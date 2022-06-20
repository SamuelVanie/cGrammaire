package cGrammaire;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

class EnsembleSymbole{
  public ArrayList<Symbole> listesSymbole;

  public EnsembleSymbole(){
    this.listesSymbole = new ArrayList<>();
    try{
      FileInputStream fis = new FileInputStream("./src/main/resources/symbole");
      Scanner sc = new Scanner(fis);
      while(sc.hasNextLine()){
        char symb = sc.nextLine().strip().toCharArray()[0];
        this.listesSymbole.add(new Symbole(symb, !Character.isUpperCase(symb)));
      }
      sc.close();
    }catch(FileNotFoundException e){
      System.out.println("Fichier introuvable !");
    }
  }

  public void getListesSymbole(){
    for (Symbole symb : this.listesSymbole) {
      System.out.println(symb.getSymbole());
    }
  }

  public int getSymbole(char symb){
    for (int i=0; i<this.listesSymbole.size(); i++){
      if (this.listesSymbole.get(i).getSymbole() == symb)
        return i;
    }
    return -1;
  }

  public void construireFirst(Symbole symb){
    ArrayList<Symbole> first = new ArrayList<>();
    if (symb.getFirst().size() == 0){
      for (ArrayList<Symbole> groupe : symb.regle.getDevelopement()) {
        if (groupe.get(0).getEstTerminal()) {
          first.add(groupe.get(0));
        }else{
          if (getSymbole(groupe.get(0).getSymbole()) != -1){
            construireFirst(this.listesSymbole.get(getSymbole(groupe.get(0).getSymbole())));
            first.addAll(this.listesSymbole.get(getSymbole(groupe.get(0).getSymbole())).getFirst());
          }
        }
      }

      ArrayList<Symbole> firstDefinitif = new ArrayList<>();
      boolean comp = false;

      for (Symbole symbl : first){
        for(Symbole symbl2 : firstDefinitif){
          if (symbl.getSymbole() == symbl2.getSymbole()){
            comp = true;
          }
        }
        if(!comp)
          firstDefinitif.add(symbl); 
      }

      symb.setFirst(firstDefinitif);

    }
  }

  public void construireFollow(Symbole symb){
    char nomSymbole = symb.getSymbole();

    ArrayList<Symbole> follow = new ArrayList<>();
    for (Symbole symbole : this.listesSymbole){
      if (!symbole.getEstTerminal()){
        ArrayList<Character> sy = symbole.regle.getSuivant(nomSymbole);
        if (sy.size() > 0){
          for (Character suiv : sy) {
            if(Character.isUpperCase(suiv)){
              Symbole current = this.listesSymbole.get(this.getSymbole(suiv));
              this.construireFirst(current);
              follow.addAll(current.getFirst());
            }else{
              follow.add(this.listesSymbole.get(this.getSymbole(suiv)));
            }
          }
        }
}
    }

    ArrayList<Symbole> followDefinitif = new ArrayList<>();
    boolean comp = false;

    for (Symbole symbl : follow){
      for(Symbole symbl2 : followDefinitif){
        if (symbl.getSymbole() == symbl2.getSymbole()){
          comp = true;
        }
      }
      if(!comp)
      followDefinitif.add(symbl); 
    }

    symb.setFollow(followDefinitif);
  }

  public boolean estContenuDans(ArrayList<Symbole> liste1, ArrayList<Symbole> liste2){
    for (Symbole symbole : liste2) {
     if (liste1.contains(symbole))
      return true;
    }
    return false;
  }

  public void estUnecGrammaire(){
    // Premier critère intersection des ensembles de first
    for (int i=0; i<this.listesSymbole.size(); i++){
      for (int j=i+1; j<this.listesSymbole.size(); j++){
        if (estContenuDans(this.listesSymbole.get(i).getFirst(), this.listesSymbole.get(j).getFirst())){
          System.out.println("Critère des firsts : Il ne s'agit pas d'une C-Grammaire car l'intersection des firsts de " + this.listesSymbole.get(i).getSymbole() + " et ceux de " + this.listesSymbole.get(j).getSymbole() + " n'est pas vide");
        }
      }
    }

    // Second critère intersection entre first et follow
    for (Symbole symb : this.listesSymbole){
        if (estContenuDans(symb.getFirst(), symb.getFollow())){
          System.out.println("Il ne s'agit pas d'une C-Grammaire car l'intersection de first de" + symb.getSymbole() + " et follow de " + symb.getSymbole());
          return;
        }
    }

    System.out.println("Il s'agit bien d'une C Grammaire, les deux critères sont vérifiés");
  }


}
