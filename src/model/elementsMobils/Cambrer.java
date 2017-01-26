package model.elementsMobils;


import java.util.Scanner;

/*
 * Classe que defineix un cambrer. Un cambrer es defineix pel seu codi, nom, telefon, torn 
 * en què treballa (matí, tarda, matí i tarda,etc.) i si està actiu o no.
 */
/**
 *
 * @author fta
 */
public class Cambrer extends ElementTaula {

    private String torn;
    private boolean actiu;

    /*
     CONSTRUCTOR
     Paràmetres: valors per tots els atributs de la classe, menys l'atribut actiu,
     ja que un cambrer sempre estarà en actiu en el moment que es crea.
     Accions:
     - Assignar als atributs els valors passats com a paràmetres
     - Assignar el valor que sigui escaient a l'atribut actiu
     */
    public Cambrer(String pCodi, String pNom, String pTelefon, String pTorn) {
        super(pCodi,pNom,pTelefon);
        torn = pTorn;
        actiu = true;
    }

    /*
     Mètodes accessors    
     */
    public String getTorn() {
        return torn;
    }

    public void setTorn(String pTorn) {
        torn = pTorn;
    }

    public boolean getActiu() {
        return actiu;
    }

    public void setActiu(boolean pActiu) {
        actiu = pActiu;
    }

    /*
     Paràmetres: cap
     Accions:
     - Demanar a l'usuari les dades per consola per crear un nou cambrer. Les dades
     a demanar són les que necessita el constructor.
     - També heu de tenir en compte que tant el nom com el torn, poden ser frases,
     per exemple, Francesc Xavier, o bé, matí i tarda.
     Retorn: El nou cambrer creat.
     */
    public static Cambrer nouCambrer() {
        Scanner dades = new Scanner(System.in);
        String codiCambrer;
        String nomCambrer;
        String telefonCambrer;
        String tornCambrer;

        System.out.println("Codi del cambrer:");
        codiCambrer = dades.next();
        dades.nextLine(); //Neteja buffer
        System.out.println("Nom del cambrer:");
        nomCambrer = dades.nextLine();
        System.out.println("Telefon del cambrer:");
        telefonCambrer = dades.next();
        dades.nextLine(); //Neteja buffer
        System.out.println("Torn del cambrer:");
        tornCambrer = dades.nextLine();

        return new Cambrer(codiCambrer, nomCambrer, telefonCambrer, tornCambrer);
    }

    /*
     Paràmetres: cap
     Accions:
     - Demanar a l'usuari que introdueixi les noves dades de l'objecte actual
     i modificar els atributs corresponents d'aquest objecte.
     -Quan mostreu a l'usuari si està actiu o no, ho heu de fer mostrant-li els missatges
     "actiu" o "no actiu".
     -Quan es demani que s'introdueixi el nou estat (si està actiu o no) del cambrer, 
     se li ha d'indicar a l'usuari que introdueixi "sí" o "no". Si introdueix sí, 
     el cambrer estarà en actiu i si introdueix no, doncs no ho estarà. Si l'usuari 
     no introdueix un valor correcte, l'heu d'avisar i tornar a demanar que introdueixi
     l'estat del cambrer, així fins que introdueixi el valor correcte.
     - Li heu de mostrar a l'usuari el valor actual dels atributs de l'objecte
     actual, abans de modificar-los
     Retorn: cap
     */
    public void modificarElement() {
        Scanner dades = new Scanner(System.in);
        boolean valorCorrecte = false;
        String estatCambrer;
        
        super.modificarElement();
        System.out.println("\nTorn del cambrer: " + torn);
        System.out.println("\nEntra el nou torn:");
        torn = dades.nextLine();
        System.out.print("\nEstat del cambrer: ");
        if (actiu) {
            System.out.println("actiu");
        } else {
            System.out.println("no actiu");
        }
        while (!valorCorrecte) {
            System.out.println("\nEntra el nou estat(Escriu sí o no):");
            estatCambrer = dades.next();
            if (estatCambrer.equals("sí")) {
                actiu = true;
                valorCorrecte = true;
            } else if (estatCambrer.equals("no")) {
                actiu = false;
                valorCorrecte = true;
            }
        }
    }

    public void mostrarElement() {
        super.mostrarElement();
        System.out.println("\nTorn:" + torn);
        System.out.print("\nEstat: ");
        if (actiu) {
            System.out.println("actiu");
        } else {
            System.out.println("no actiu");
        }
    }
}
