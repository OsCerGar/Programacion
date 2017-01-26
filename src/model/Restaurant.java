package model;

import model.elementsMobils.Cambrer;
import model.elementsMobils.ElementTaula;
import model.elementsMobils.Reserva;
import model.elementsMobils.Taula;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import principal.Element;
import principal.RestaurantExcepcio;

/*
 * Classe que defineix un restaurant. Un restaurant es defineix per un codi, un 
 * nom i una adreça. A més, contindrà arrays amb cambrers, reserves, taules i menjadors. 
 */
/**
 *
 * @author fta
 */
public class Restaurant {

    private int codi;
    static private int properCodi = 1; //El proper codi a assignar
    private String nom;
    private String adreca;
    private ArrayList<Element> elementsRestaurant;

    /*
     CONSTRUCTOR
     Paràmetres: valors pels atributs nom i adreca
     Accions:
     - Assignar als atributs corresponents els valors passats com a paràmetres
     - Assignar a l'atribut codi el valor de l'atribut properCodi i actualitzar
     properCodi amb el següent codi a assignar.
     */
    public Restaurant(String pNom, String pAdreca) {
        codi = properCodi;
        properCodi++;
        nom = pNom;
        adreca = pAdreca;
        elementsRestaurant = new ArrayList();
    }
    
     public Restaurant(int pCodi, String pNom, String pAdreca) {
        codi = pCodi;
        nom = pNom;
        adreca = pAdreca;
        elementsRestaurant = new ArrayList();
    }

    /*
     Mètodes accessors    
     */
    public int getCodi() {
        return codi;
    }

    public void setCodi() {
        codi = properCodi;
    }

    public static int getProperCodi() {
        return properCodi;
    }

    public static void setProperCodi() {
        properCodi++;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdreca() {
        return adreca;
    }

    public void setAdreca(String adreca) {
        this.adreca = adreca;
    }

    public ArrayList<Element> getElementsRestaurant() {
        return elementsRestaurant;
    }

    public void setElementsRestaurant(ArrayList<Element> pElementsRestaurant) {
        elementsRestaurant = pElementsRestaurant;
    }


    /*
     Paràmetres: cap
     Accions:
     - Demanar a l'usuari les dades per consola per crear un nou Restaurant. Les dades
     a demanar són les que necessita el constructor.
     - Heu de tenir en compte que tant el nom com l'adreça, poden ser
     frases, per exemple, Bon menjar, o bé, C/ Gandia, 2.
     Retorn: El nou restaurant creat.
     */
    public static Restaurant nouRestaurant() {
        Scanner dades = new Scanner(System.in);
        String nomRestaurant;
        String adrecaRestaurant;

        System.out.println("Nom del restaurant:");
        nomRestaurant = dades.nextLine();
        System.out.println("Adreça del restaurant:");
        adrecaRestaurant = dades.nextLine();
        return new Restaurant(nomRestaurant, adrecaRestaurant);
    }

    /*
     Paràmetres: cap
     Accions:
     - Demanar a l'usuari que introdueixi les noves dades de l'objecte actual
     i modificar els atributs corresponents d'aquest objecte. En aquest cas
     no es pot modificar el contingut dels vectors, només els dels atributs 
     nom i adreça. Evidentment, tampoc podeu modificar el codi.
     - Li heu de mostrar a l'usuari el valor actual dels atributs de l'objecte
     actual, abans de modificar-los.
     Retorn: cap
     */
    public void modificarRestaurant() {
        Scanner dades = new Scanner(System.in);

        System.out.println("\nNom del restaurant: " + nom);
        System.out.println("\nEntra el nou nom:");
        nom = dades.nextLine();
        dades.nextLine(); //Neteja de buffer
        System.out.println("\nAdreça del restaurant: " + adreca);
        System.out.println("\nEntra la nova adreça:");
        adreca = dades.nextLine();
    }

    public void mostrarRestaurant() {
        System.out.println("\nLes dades del restaurant amb codi " + codi + " són:");
        System.out.println("\nNom:" + nom);
        System.out.println("\nAdreça:" + adreca);
    }

    /*
     CAMBRER
     */
    /*
     Paràmetres: cap
     Accions:
     - afegeix un nou cambrer al vector de cambrers d'aquest restaurant (l'objecte actual),
     fent servir el mètode de la classe Cambrer pertinent.
     Retorn: cap
     */
    public void nouCambrer(Cambrer pCambrer) {
        try {
            if (pCambrer == null) {
                pCambrer = Cambrer.nouCambrer();
            }
            seleccionarElementRestaurant(1, pCambrer.getCodi());
        } catch (RestaurantExcepcio e) {
            elementsRestaurant.add(pCambrer);
        }

    }

    /*
     RESERVA
     */
    /*
     Paràmetres: cap
     Accions:
     - afegeix una nova reserva al vector de reserves d'aquest restaurant (l'objecte actual),
     fent servir el mètode de la classe Reserva pertinent.
     Retorn: cap
     */
    public void novaReserva(Reserva pReserva) {
        try {
            if (pReserva == null) {
                pReserva = Reserva.novaReserva();
            }
            seleccionarElementRestaurant(2, pReserva.getCodi());
        } catch (RestaurantExcepcio e) {
            elementsRestaurant.add(pReserva);
        }

    }

    /*
     TAULA
     */
    /*
     Paràmetres: cap
     Accions:
     - afegeix una nova taula al vector de taules d'aquest restaurant (l'objecte actual),
     fent servir el mètode de la classe Taula pertinent.
     Retorn: cap
     */
    public void novaTaula(Taula pTaula) {
        try {
            if (pTaula == null) {
                pTaula = Taula.novaTaula();
            }
            seleccionarElementRestaurant(3, pTaula.getCodi());
        } catch (RestaurantExcepcio e) {
            elementsRestaurant.add(pTaula);
        }

    }

    /*
     MENJADOR
     */
    /*
     Paràmetres: cap
     Accions:
     - afegeix un nou menjador al vector de menjadors d'aquest restaurant (l'objecte actual),
     fent servir el mètode de la classe Menjadors pertinent.
     Retorn: cap
     */
    public void nouMenjador(Menjador pMenjador) {
        try {
            if (pMenjador == null) {
                pMenjador = Menjador.nouMenjador();
            }
            seleccionarElementRestaurant(4, pMenjador.getCodi());
        } catch (RestaurantExcepcio e) {
            elementsRestaurant.add(pMenjador);
        }

    }

    public int seleccionarElementRestaurant(int tipusElement, String pCodi) throws RestaurantExcepcio {
        Scanner dades = new Scanner(System.in);
        int opcio = tipusElement; //Opció a seleccionar per l'usuari del menú següent
        int comptadorElements = 0; //Comptador d'elements del resturant
        boolean trobat = false; //Element no trobat
        String codiElement; //Codi d'element a cercar

        if (pCodi == null) { //Si codi és null... 

            switch (opcio) {
                case 0: //Demanem quin tipus d'element vol seleccionar
                    System.out.println("\nQuè vols seleccionar?(introduiex un dels números):");
                    System.out.println("1. Reserva:");
                    System.out.println("2. Cambrer:");
                    System.out.println("3. Taula:");
                    System.out.println("4. Menjador:");
                    opcio = dades.nextInt();
                    break;

                //Demanem el codi del tipus d'element introduit
                case 1:
                    System.out.println("Codi de la reserva?:");
                    break;
                case 2:
                    System.out.println("Codi del cambrer?:");
                    break;

                case 3:
                    System.out.println("Codi de la taula?:");
                    break;
                case 4:
                    System.out.println("Codi del menjador?:");
                    break;
            }
            codiElement = dades.next();

        } else { //Si el codi no és null
            codiElement = pCodi;
        }

        int posElement = -1; //Posició que ocupa l'element seleccionat dins el vector d'elements del restaurant

        //Seleccionem la posició que ocupa dins el vector d'elements del restaurant 
        //l'element seleccionat en el menú anterior
        Iterator<Element> punter = elementsRestaurant.iterator();
        while (punter.hasNext() && !trobat) {
            Element elementActual = punter.next();
            if (elementActual instanceof Reserva && opcio == 1) {
                if (((Reserva) elementActual).getCodi().equals(codiElement)) {
                    posElement = comptadorElements;
                    trobat = true;
                }
            } else if (elementActual instanceof Cambrer && opcio == 2) {
                if (((Cambrer) elementActual).getCodi().equals(codiElement)) {
                    posElement = comptadorElements;
                    trobat = true;
                }
            } else if (elementActual instanceof Taula && opcio == 3) {
                if (((Taula) elementActual).getCodi().equals(codiElement)) {
                    posElement = comptadorElements;
                    trobat = true;
                }
            } else if (elementActual instanceof Menjador && opcio == 4) {
                if (((Menjador) elementActual).getCodi().equals(codiElement)) {
                    posElement = comptadorElements;
                    trobat = true;
                }
            }
            comptadorElements++;
        }

        if (posElement == -1) {
            throw new RestaurantExcepcio("2");
        } else {
            return posElement;
        }
    }

    public void afegirElementTaula(int tipusElementTaula) {
        Taula taulaSel = null;
        int pos;
        try {

            //Seleccionar Taula
            pos = seleccionarElementRestaurant(3, null);
            taulaSel = (Taula) elementsRestaurant.get(pos);

            pos = seleccionarElementRestaurant(tipusElementTaula, null); //Seleccionar element de Taula
            taulaSel.afegirElementTaula((ElementTaula) elementsRestaurant.get(pos)); //Afegim element

        } catch (RestaurantExcepcio e) {
            System.out.println(e.getMessage());
        }
    }

    public void afegirElementMenjador() {
        Menjador menjadorSel = null;
        int pos;
        try {
            
            //Seeleccionem el menjador
            pos = seleccionarElementRestaurant(4, null); 
            menjadorSel = (Menjador) elementsRestaurant.get(pos);

            pos = seleccionarElementRestaurant(3, null); //Seleccionem la taula
            menjadorSel.afegirTaulaMenjador((Taula) elementsRestaurant.get(pos)); //Afegim la taula
            
        } catch (RestaurantExcepcio e) {
            System.out.println(e.getMessage());
        }
    }
}
