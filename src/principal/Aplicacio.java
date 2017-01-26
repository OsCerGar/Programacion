package principal;

import model.Restaurant;
import model.Menjador;
import model.elementsMobils.Cambrer;
import model.elementsMobils.Reserva;
import model.elementsMobils.Taula;
import java.util.InputMismatchException;
import java.util.Scanner;
import persistencia.GestorPersistencia;

/*
 * Classe Aplicacio per interactuar amb l'usuari, contenir els restaurants i cridar
 * a la resta de classes i mètodes mitjançant uns menús.
 */
/**
 *
 * @author fta
 */
public class Aplicacio {

    static private String FITXER = "restaurantPersitent.xml";
    static private GestorPersistencia gp = new GestorPersistencia();
    static private Restaurant[] restaurants = new Restaurant[1];//Restaurants del grup
    static private int comptaRestaurants = 0; //La propera posició buida del vector restaurants
    static private Restaurant restaurantActual = null; //Restaurant seleccionat
    static private Integer tipusElementTaula = 0; //Fa refrència a cambrers o reserves

    public static void main(String[] args) throws RestaurantExcepcio {
        try {
            menuPrincipal();
        } catch (RestaurantExcepcio e) {
            System.out.println(e.getMessage());
        }
    }

    private static void menuPrincipal() throws RestaurantExcepcio {
        int opcio = 0;
        Scanner dades = new Scanner(System.in);
        do {
            try {
                System.out.println("\nSelecciona una opció");
                System.out.println("\n0. Sortir");
                System.out.println("\n1. Gestió de restaurants");
                System.out.println("\n2. Gestió dels cambrers"); //1
                System.out.println("\n3. Gestió dels reserves"); //2
                System.out.println("\n4. Gestió dels taules"); //3
                System.out.println("\n5. Gestió dels menjadors"); //4
                opcio = dades.nextInt();
                switch (opcio) {
                    case 0:
                        break;
                    case 1:
                        menuRestaurant();
                        break;
                    case 2: //Cambrers
                        if (restaurantActual != null) {
                            tipusElementTaula = 1;
                            menuElementsTaula();
                        } else {
                            System.out.println("\nPrimer s'ha de seleccionar el restaurant en el menú de restaurants");
                        }
                        break;
                    case 3: //Reserves
                        if (restaurantActual != null) {
                            tipusElementTaula = 2;
                            menuElementsTaula();
                        } else {
                            System.out.println("\nPrimer s'ha de seleccionar el restaurant en el menú de restaurants");
                        }
                        break;
                    case 4:  //Taules
                        if (restaurantActual != null) {
                            menuTaules();
                        } else {
                            System.out.println("\nPrimer s'ha de seleccionar el restaurant en el menú de restaurants");
                        }
                        break;
                    case 5: //Menjadors
                        if (restaurantActual != null) {
                            menuMenjadors();
                        } else {
                            System.out.println("\nPrimer s'ha de seleccionar el restaurant en el menú de restaurants");
                        }
                        break;
                    default:
                        System.out.println("\nS'ha de seleccionar una opció correcta del menú.");
                        break;
                }
            } catch (InputMismatchException e) {
                RestaurantExcepcio ee = new RestaurantExcepcio("1");
                throw ee;
            } catch (ArrayIndexOutOfBoundsException e) {
                RestaurantExcepcio ee = new RestaurantExcepcio("2");
                throw ee;
            }
        } while (opcio != 0);
    }

    public static void menuRestaurant() throws RestaurantExcepcio {
        int opcio = 0;
        Scanner dades = new Scanner(System.in);
        do {
            int pos = -1;
            System.out.println("\nSelecciona una opció");
            System.out.println("\n0. Sortir");
            System.out.println("\n1. Alta");
            System.out.println("\n2. Seleccionar");
            System.out.println("\n3. Modificar");
            System.out.println("\n4. LListar restaurants");
            System.out.println("\n5. Carregar restaurant");
            System.out.println("\n6. Desar restaurant");
            opcio = dades.nextInt();
            switch (opcio) {
                case 0:
                    break;
                case 1:
                    restaurants[comptaRestaurants] = Restaurant.nouRestaurant();
                    comptaRestaurants++;
                    break;
                case 2:
                    pos = seleccionarRestaurant();
                    if (pos >= 0) {
                        restaurantActual = restaurants[pos];
                    } else {
                        System.out.println("\nNo existeix aquest restaurant");
                    }
                    break;
                case 3:
                    pos = seleccionarRestaurant();
                    if (pos >= 0) {
                        restaurants[pos].modificarRestaurant();
                    } else {
                        System.out.println("\nNo existeix aquest restaurant");
                    }
                    break;
                case 4:
                    for (int i = 0; i < comptaRestaurants; i++) {
                        restaurants[i].mostrarRestaurant();
                    }
                    break;
                case 5: //Carregar Restaurant
                    comptaRestaurants = 0;
                    restaurants = new Restaurant[1];
                    restaurants[comptaRestaurants] = gp.carregaRestaurant("XML", FITXER);
                    comptaRestaurants++;
                    break;
                case 6: //Desar Restaurant
                    pos = seleccionarRestaurant();
                    if (pos >= 0) {
                        gp.desarRestaurant("XML", FITXER, restaurants[pos]);
                    } else {
                        System.out.println("\nNo existeix aquest restaurant");
                    }
                    break;
                default:
                    System.out.println("\nS'ha de seleccionar una opció correcta del menú.");
                    break;
            }
        } while (opcio != 0);
    }

    public static void menuElementsTaula() throws RestaurantExcepcio {
        int opcio = 0;
        Scanner dades = new Scanner(System.in);
        do {
            System.out.println("\nSelecciona una opció");
            System.out.println("\n0. Sortir");
            System.out.println("\n1. Alta");
            System.out.println("\n2. Modificar");
            System.out.println("\n3. Llistar");
            opcio = dades.nextInt();
            switch (opcio) {
                case 0:
                    break;
                case 1:
                    switch (tipusElementTaula) {
                        case 1:
                            restaurantActual.nouCambrer(null);
                            break;
                        case 2:
                            restaurantActual.novaReserva(null);
                            break;
                    }
                    break;
                case 2:
                    int pos = restaurantActual.seleccionarElementRestaurant(tipusElementTaula, null);
                    if (pos >= 0) {
                        restaurantActual.getElementsRestaurant().get(pos).modificarElement();
                    } else {
                        System.out.println("\nNo existeix aquest element");
                    }
                    break;
                case 3:

                    for (int i = 0; i < Restaurant.getProperCodi(); i++) {
                        if (restaurantActual.getElementsRestaurant().get(i) instanceof Cambrer && tipusElementTaula == 1) {
                            restaurantActual.getElementsRestaurant().get(i).mostrarElement();
                        } else if (restaurantActual.getElementsRestaurant().get(i) instanceof Reserva && tipusElementTaula == 2) {
                            restaurantActual.getElementsRestaurant().get(i).mostrarElement();
                        }
                    }
                    break;
                default:
                    System.out.println("\nS'ha de seleccionar una opció correcta del menú.");
                    break;
            }
        } while (opcio != 0);
    }

    public static void menuTaules() {
        int opcio = 0;
        Scanner dades = new Scanner(System.in);
        do {
            System.out.println("\nSelecciona una opció");
            System.out.println("\n0. Sortir");
            System.out.println("\n1. Alta");
            System.out.println("\n2. Afegir cambrer");
            System.out.println("\n3. Afegir reserva");
            System.out.println("\n5. Llistar taules");
            opcio = dades.nextInt();
            switch (opcio) {
                case 0:
                    break;
                case 1:
                    restaurantActual.novaTaula(null);
                    break;
                case 2:
                    restaurantActual.afegirElementTaula(2);
                    break;
                case 3:
                    restaurantActual.afegirElementTaula(1);
                    break;
                case 5:
                    for (int i = 0; i < restaurantActual.getElementsRestaurant().size(); i++) {
                        if (restaurantActual.getElementsRestaurant().get(i) instanceof Taula) {
                            restaurantActual.getElementsRestaurant().get(i).mostrarElement();
                        }
                    }
                    break;
                default:
                    System.out.println("\nS'ha de seleccionar una opció correcta del menú.");
                    break;
            }
        } while (opcio != 0);
    }

    public static void menuMenjadors() {
        int opcio = 0;
        Scanner dades = new Scanner(System.in);
        do {
            System.out.println("\nSelecciona una opció");
            System.out.println("\n0. Sortir");
            System.out.println("\n1. Alta");
            System.out.println("\n2. Afegir taula");
            System.out.println("\n5. Llistar menjadors");
            opcio = dades.nextInt();
            switch (opcio) {
                case 0:
                    break;
                case 1:
                    restaurantActual.nouMenjador(null);
                    break;
                case 2:
                    restaurantActual.afegirElementMenjador();
                    break;
                case 5:
                    for (int i = 0; i < restaurantActual.getElementsRestaurant().size(); i++) {
                        if (restaurantActual.getElementsRestaurant().get(i) instanceof Menjador) {
                            restaurantActual.getElementsRestaurant().get(i).mostrarElement();
                        }
                    }
                    break;
                default:
                    System.out.println("\nS'ha de seleccionar una opció correcta del menú.");
                    break;
            }
        } while (opcio != 0);
    }

    public static Integer seleccionarRestaurant() {
        Scanner dades = new Scanner(System.in);
        System.out.println("\nCodi del restaurant?:");
        int codi = dades.nextInt();
        int pos = -1;
        for (int i = 0; i < comptaRestaurants; i++) {
            System.out.println("Codi restaurant : "+restaurants[i].getCodi());
            System.out.println("Codi passat per paràmetre : "+codi);
            if (restaurants[i].getCodi() == codi) {
                pos = i;
                break;
            }
        }
        return pos;
    }
}
