package model.elementsMobils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import model.Contenidor;

/*
 * Classe que defineix una taula. Una taula es defineix per un codi i total de places.
 * A més, contindrà un vector de reserves i un vector de cambrers.
 */
/**
 *
 * @author fta
 */
public class Taula extends Contenidor {

    private ArrayList<ElementTaula> elementsTaula;

    /*
     CONSTRUCTOR
     Paràmetres: valors pels atributs codi i places
     Accions:
     - Assignar als atributs corresponents els valors passats com a paràmetres
     */
    public Taula(String pCodi, int pPlaces) {
        super(pCodi, pPlaces);
        elementsTaula = new ArrayList();
    }

    /*
     Mètodes accessors    
     */
    public ArrayList<ElementTaula> getElementsTaula() {
        return elementsTaula;
    }

    public void setElementsTaula(ArrayList<ElementTaula> pElementsTaula) {
        elementsTaula = pElementsTaula;
    }

    /*
     Paràmetres: cap
     Accions:
     - Demanar a l'usuari les dades per consola per crear una nova Taula. Les dades
     a demanar són les que necessita el constructor.
     Retorn: La nova taula creada.
     */
    public static Taula novaTaula() {
        Scanner dades = new Scanner(System.in);
        String codiTaula;
        int placesTaula;

        System.out.println("\nCodi de la taula:");
        codiTaula = dades.next();
        System.out.println("\nTotal places de la taula:");
        placesTaula = dades.nextInt();

        return new Taula(codiTaula, placesTaula);
    }

    public void mostrarElement() {
        
        super.mostrarElement();

        System.out.println("\nAquesta taula té assignats:");
        Iterator<ElementTaula> punter = elementsTaula.iterator();
        while (punter.hasNext()) {
            ElementTaula elementActual = punter.next();
            if (elementActual instanceof Reserva) { //Mostrem si és reserva
                ((Reserva) elementActual).mostrarElement();
            } else { //Mostrem si és cambrer
                ((Cambrer) elementActual).mostrarElement();
            }
        }
    }


    /*
      Hem de tenir en compte si es tracta d'una reserva per controlar si la podem
      afegir o no
     */
    public void afegirElementTaula(ElementTaula pElement) {
        
        //Si és reserva i els comensals són més que les places de la taula
        if (pElement instanceof Reserva && ((Reserva) pElement).getComensals() > super.getPlaces() ) {
            System.out.println("\nLa reserva és massa gran per la taula");
        }else{
            elementsTaula.add(pElement);
        }

    }

}
