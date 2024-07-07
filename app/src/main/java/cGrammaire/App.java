package cGrammaire;

public class App {
    public static void main(String[] args) {
      EnsembleSymbole l = new EnsembleSymbole();

      System.out.println("----Affichage des symboles récupérés dans le fichier----");
      l.getListesSymbole();
      System.out.println("-----------------");

      Regle rg = new Regle();
      Regle rgB = new Regle();
      Regle rgC = new Regle();

      rgB.construireDeveloppement("B::=x|y");
      rgC.construireDeveloppement("C::=zy");
      rg.construireDeveloppement("A::=aB|kxB|yC");

      Symbole A;
      Symbole C;
      Symbole B;
    
      A = l.listesSymbole.get(l.getSymbole('A'));
      C = l.listesSymbole.get(l.getSymbole('C'));
      B = l.listesSymbole.get(l.getSymbole('B'));

      A.setRegle(rg);
      C.setRegle(rgC);
      B.setRegle(rgB);


      l.construireFirst(A);
      l.construireFirst(B);
      l.construireFirst(C); 
    

      l.construireFollow(A);
      l.construireFollow(B);
      l.construireFollow(C);

      System.out.println("Affichage des first de A");
      A.afficherFirst();
      System.out.println("-------------------");

      System.out.println("Affichage des first de B");
      B.afficherFirst();
      System.out.println("-------------------");

      System.out.println("Affichage des first de C");
      C.afficherFirst();
      System.out.println("-------------------");

      System.out.println("Affichage des follow de A");
      A.afficherFollow();
      System.out.println("-------------------");

      System.out.println("Affichage des follow de B");
      B.afficherFollow(); 
      System.out.println("-------------------");

      System.out.println("Affichage des follow de C");
      C.afficherFollow();
      System.out.println("-------------------");

      l.estUnecGrammaire();

    }
}
