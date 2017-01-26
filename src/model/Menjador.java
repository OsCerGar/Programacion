
package model;


import model.Contenidor;
import model.elementsMobils.Taula;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/*
 * Classe que defineix un menjador. Un menjador es defineix per un codi, total de places
 * i les places que te ocupades per les taules. A més, contindrà un vector de taules.
 */
/**
 *
 * @author fta
 */
public class Menjador extends Contenidor {

    private int placesOcupades;
    private ArrayList<Taula> taules;

    /*
     CONSTRUCTOR
     Paràmetres: valors pels atributs codi i places
     Accions:
     - Assignar als atributs corresponents els valors passats com a paràmetres
     - Inicialitzar a 0 les places ocupades, ja que quan es crea un menjador aquest
     no té cap taula assignada.
     */
    public Menjador(String pCodi, int pPlaces) {
        super(pCodi,pPlaces);
        taules=new ArrayList();
    }

    /*
     Mètodes accessors    
     */
    public int getPlacesOcupades() {
        return placesOcupades;
    }

    public void setPlacesOcupades(int pPlacesOcupades) {
        placesOcupades = pPlacesOcupades;
    }

    public ArrayList<Taula> getTaules() {
        return taules;
    }

    public void setTaules(ArrayList<Taula> pTaules) {
        taules = pTaules;
    }

    /*
     Paràmetres: cap
     Accions:
     - Demanar a l'usuari les dades per consola per crear un nou menjador. Les dades
     a demanar són les que necessita el constructor.
     Retorn: El nou menjador creat.
     */
    public static Menjador nouMenjador() {
        Scanner dades = new Scanner(System.in);
        String codiMenjador;
        int placesMenjador;

        System.out.println("\nCodi del menjador:");
        codiMenjador = dades.next();
        System.out.println("\nTotal places del emnjador:");
        placesMenjador = dades.nextInt();

        return new Menjador(codiMenjador, placesMenjador);
    }

    public void mostrarElement() {
        super.mostrarElement();
        System.out.println("\nPlaces ocupades:" + placesOcupades);

        System.out.println("\nAquest menjador té assignades les taules:");
        
        Iterator<Taula> punter=taules.iterator();        
        while(punter.hasNext()){
            punter.next().mostrarElement();
        }
       
    }
    
    public void modificarElement(){
        super.modificarElement();
        
        Scanner dades = new Scanner(System.in);
        System.out.println("\nTotal places ocupades: " + placesOcupades);
        System.out.println("\nEntra el total de noves places ocupades :");
        placesOcupades = dades.nextInt();
    }


    /*
     Paràmetres: Reserva
     Accions:
     - afegeix la taula passada per paràmetre al vector de taules del menjador actual.
     Heu de tenir en compte que la taula a afegir no pot ser de més comensals que
     les places lliures que té el menjador actual. Si no és així, se li mostrarà a l'usuari 
     el missatge "Aquest menjador no té lloc per aquesta taula" i no s'afegirà 
     la taula.
     - actualitza la posició del vector de taules, si s'ha afegit la taula.
     - actualitzar les taules ocupades, si s'ha afegit la taula.
     Retorneu: cap
     */
    public void afegirTaulaMenjador(Taula taula) {
        if (taula.getPlaces() <= super.getPlaces()-placesOcupades) {
            taules.add(taula);
            placesOcupades-=taula.getPlaces();
        } else {
            System.out.println("\nAquest menjador no té lloc per aquesta taula");
        }
    }
}