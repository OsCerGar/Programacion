package controlador;

import java.awt.GridLayout;
import persistencia.GestorPersistencia;
import java.awt.event.*;
import javax.swing.*;
import model.Restaurant;
import vista.MenuPrincipalVista;

public class ControladorPrincipal implements ActionListener {

    static private MenuPrincipalVista menuPrincipalVista;
    static private final Integer MAXRESTAURANTS = 10;
    static private Restaurant[] restaurants = new Restaurant[MAXRESTAURANTS];
    static private int properRestaurant = 0;
    static private Restaurant restaurantActual = null;
    static private int tipusElement = 0;
    static private GestorPersistencia gp = new GestorPersistencia();
    static private final String[] METODEPERSISTENCIA = {"XML"};

    /*  
        S'inicialitza l'atribut menuPrincipalVista (això mostrarà el menú principal)
        A cada botó del menú principal se li afegeix el listener tenint en compte què el
        mètode actionPerformed, està implementat en aquesta classe.
    */
    public ControladorPrincipal() {

        MenuPrincipalVista menuPrincipalVista = new MenuPrincipalVista();
        
        frame = new JFrame("Llistatdebiblioteques");
        frame.setLayout(new GridLayout(0,1));

        
        
    }

    /*  Paràmetres: ActionEvent
    
        Acció: S'ha de cridar a bifurcaOpcio segons l'opció premuda. Penseu que l'opció es correspon amb
        la posició que el botó ocupa a l'array de botons de menuPrincipalVista.
        
        Retorn: cap
    */
    public void actionPerformed(ActionEvent e) {

    }

    private void bifurcaOpcio(int opcio) {
        switch (opcio) {
            case 0: //Sortir
                System.exit(0);
                break;
            
            case 1: //Gestió restaurant
                menuPrincipalVista.getFrame().setVisible(false);
                ControladorRestaurant controladorRestaurant = new ControladorRestaurant();
                break;
            
            case 2: //Gestió reserves
                JOptionPane.showMessageDialog(menuPrincipalVista.getFrame(), "No heu d'implementar aquest menú");              
                break;
        }
    }



    public static MenuPrincipalVista getMenuPrincipalVista() {
        return menuPrincipalVista;
    }

    public static Integer getMAXRESTAURANTS() {
        return MAXRESTAURANTS;
    }

    
    public static Restaurant[] getRestaurants() {
        return restaurants;
    }

    public static void setRestaurants(Restaurant[] pRestaurants) {
        ControladorPrincipal.restaurants = pRestaurants;
    }

    public static int getComptaRestaurants() {
        return properRestaurant;
    }

    public static void setComptaRestaurants() {
        ControladorPrincipal.properRestaurant++;
    }

    public static Restaurant getRestaurantActual() {
        return restaurantActual;
    }

    public static void setRestaurantActual(Restaurant pRestaurantActual) {
        ControladorPrincipal.restaurantActual = pRestaurantActual;
    }

    public static Integer getTipusElement() {
        return tipusElement;
    }

    public static void setTipusElement(int pTipusElement) {
        ControladorPrincipal.tipusElement = pTipusElement;
    }

    public static GestorPersistencia getGp() {
        return gp;
    }

    public static void setGp(GestorPersistencia gp) {
        ControladorPrincipal.gp = gp;
    }

    public static String[] getMETODEPERSISTENCIA() {
        return METODEPERSISTENCIA;
    }
    
}
