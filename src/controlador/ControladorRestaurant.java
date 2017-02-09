package controlador;

import java.awt.event.*;
import javax.swing.*;
import model.Restaurant;
import vista.MenuRestaurantVista;
import vista.RestaurantForm;
import vista.RestaurantLlista;
import vista.MenuPrincipalVista;



public class ControladorRestaurant implements ActionListener {

    private MenuRestaurantVista menuRestaurantVista;
    private RestaurantForm restaurantForm = null;
    private RestaurantLlista restaurantLlista = null;
    private int opcioSeleccionada = 0;

    
    /*  
        S'inicialitza l'atribut menuRestaurantVista (això mostrarà el menú principal)
        Es crida a afegirListenersMenu
    */
    public ControladorRestaurant() {
      MenuRestaurantVista menuRestaurantVista = new MenuRestaurantVista();
      afegirListenersMenu();
    }

    /*  Paràmetres: cap
    
        Acció: A cada botó de la vista del menú restaurant se li afegeix el listener 
        tenint en compte què el mètode actionPerformed, està implementat en aquesta classe.
        
        Retorn: cap
    */
    private void afegirListenersMenu() {
        JButton[] botones  = new JButton[7];
        botones = MenuRestaurantVista.getMenuButtons();
        MenuRestaurantVista.getMenuButtons().addActionListener(this); 

        // hay que hacer el add action listener a todos.

    }

    /*  Paràmetres: cap
    
        Acció: A cada botó de la vista del formulari restaurant se li afegeix el listener 
        tenint en compte què el mètode actionPerformed, està implementat en aquesta classe.
        
        Retorn: cap
    */
    private void afegirListenersForm() {
        RestaurantForm.getDesar().addActionListener(this);
        RestaurantForm.getSortir().addActionListener(this);

        
    }

    /*  Paràmetres: cap
    
        Acció: Al botó sortir de la vista de la llista de restaurants se li afegeix el listener 
        tenint en compte què el mètode actionPerformed, està implementat en aquesta classe.
        
        Retorn: cap
    */
    private void afegirListenersLlista() {
      
    }

    /*
        Paràmetres: ActionEvent
        
        Nota:
    
            Com ControladorRestaurant és listener del menú restaurant, del formulari i de la llista, llavors en aquest mètode
            actionPerformed heu de controlar si l'usuari ha premut algun botó de qualsevol dels esmentats frames.
            
            En el cas del formulari i de la llista, com provenen del menú restaurant (els llança el menú restaurant), heu de verificar
            primer que els objectes restaurantForm o restaurantLlista no són nulls, per tal de saber si podeu comparar-los amb
            algun botó d'aquests frames
    
        Accions per al menú:
    
            S'ha de cridar a bifurcaOpcio segons l'opció premuda. Penseu que l'opció es correspon amb
            la posició que el botó ocupa a l'array de botons de menuPrincipalVista
            També, heu d'actualitzar la propietat opcioSeleccionada (amb l'opció que ha premut l'usuari)
        
        Accions per al formulari:
            
        ---- DESAR ----
            Si el botó premut per l'usuari és el botó de desar, llavors:
                Si l'opció seleccionada en el menú restaurant és 1 (alta), llavors:
                    Es validen les dades mitjançant el mètode validarRestaurant():
                       Si no són correctes, validarRestaurant() mostrarà un missatge (no heu de fer res)
                       Si són correctes:
                         - Es crea un nou objecte Restaurant amb les dades del formulari
                         - S'afegeix el restaurant creat al vector restaurants del ControladorPrincipal
                         - Es desahabilita el codi del formulari mitjançant el mètode setEnabled(false) de la classe JTextField
                           que desahabilita l'input de text quan li passem el paràmetre false. D'aquesta manera, l'usuari no podrà
                           modificar el codi del restaurant.
                         - S'assigna aquest restaurant, com a restaurantActual (del ControladorPrincipal) i es canvia l'atribut
                           opcioSeleccionada a 2
                Si l'opció seleccionada en el menú biblioteca és 3 (modificació), llavors:
                    Nota: no es validen dades amb aquesta opció (per simplificar)
                    Es modifica l'objecte restaurant amb les dades introduides mitjançant el formulari (penseu que en aquests moments, el restaurant és el restaurant actual)
        
        ---- SORTIR ----
            Si el botó premut per l'usuari és el botó de sortir del formulari, llavors:
                Heu de tornar al menú restaurant i amagar el formulari.
        
        Accions per a la llista:
        
        ---- SORTIR ----
            Si el botó premut per l'usuari és el botó de sortir de la llista, llavors:
                Heu de tornar al menú restaurant i amagar la llista.
        
        Retorn: cap
    */ 
    public void actionPerformed(ActionEvent e) {
        

    }

    private void bifurcaOpcio(int opcio) {
        switch (opcio) {
            
            case 0: //sortir
                ControladorPrincipal.getMenuPrincipalVista().getFrame().setVisible(true);
                break;
                
            case 1: // alta
                if (ControladorPrincipal.getComptaRestaurants() < ControladorPrincipal.getMAXRESTAURANTS()) {
                    restaurantForm = new RestaurantForm();
                    afegirListenersForm();
                } else {
                    menuRestaurantVista.getFrame().setVisible(true);
                    JOptionPane.showMessageDialog(menuRestaurantVista.getFrame(), "Màxim nombre de restaurants assolit.");
                }
                break;
            case 2: //seleccionar
                menuRestaurantVista.getFrame().setVisible(true);
                if (ControladorPrincipal.getRestaurants()[0] != null) {
                    seleccionarRestaurant();
                } else {
                    JOptionPane.showMessageDialog(menuRestaurantVista.getFrame(), "Abans s'ha de crear al menys un restaurant");
                }
                break;
            case 3: //modificar
                if (ControladorPrincipal.getRestaurants()[0] != null) {
                    seleccionarRestaurant();
                    restaurantForm = new RestaurantForm(ControladorPrincipal.getRestaurantActual().getCodi(), ControladorPrincipal.getRestaurantActual().getNom(), ControladorPrincipal.getRestaurantActual().getAdreca());
                    restaurantForm.getCodi().setEnabled(false);
                    afegirListenersForm();
                } else {
                    menuRestaurantVista.getFrame().setVisible(true);
                    JOptionPane.showMessageDialog(menuRestaurantVista.getFrame(), "Abans s'ha de crear al menys un restaurant");
                }
                break;
            case 4: // llista
                if (ControladorPrincipal.getRestaurants()[0] != null) {
                    restaurantLlista = new RestaurantLlista();
                    afegirListenersLlista();
                } else {
                    menuRestaurantVista.getFrame().setVisible(true);
                    JOptionPane.showMessageDialog(menuRestaurantVista.getFrame(), "Abans s'ha de crear al menys un restaurant");
                }
                break;
            case 5: //carregar
            /*
             - Es mostra un diàleg (JOptionPane.showOptionDialog) amb un botó per carregar els documents XML
             
             - Un cop seleccionat el botó, amb un altre diàleg (JOptionPane.showInputDialog), es demana el codi del restaurant a carregar 
               (recordeu que el nom del fitxer és el codi de la biblioteca+l'extensió xml)
             
             - Un cop l'usuari ha introduit el codi i ha premut OK:
                 - Es crea un objecte restaurant (nouRestaurant) mitjançant el mètode carregarRestaurant del gestor de persistència.
                 - Es comprova mitjançant el mètode comprovarRestaurant si el codi del nou restaurant ja existeix en el vector de restaurants.
                 
                 Si existeix:
                    - Es mostra un diàleg notificant a l'usuari si vol substituir el restaurant del vector de restaurants del controlador principal pel que es carregarà des del fitxer (JOptionPane.showOptionDialog)
                       - Si l'usuari cancela, no es fa res
                       - Si l'usuari accepta, llavors s'afegeix el nou restaurant en el vector restaurants en la mateixa posició on s'havia trobat(recordeu que el mètode
                         comprovarRestaurants, retorna la posició on es troba el restaurant.
                 Si no existeix,
                    - S'afegeix el nou restaurant en la propera posició lliure (properRestaurant) del vector de restaurants del controlador principal.
                    - Es mostra un missatge confirmant que el restaurant s'ha afegit(JOptionPane.showMessageDialog)
            
             Si es produeix alguna excepció al carregar el fitxer, en la captura s'ha de mostrar el missatge corresponent de la classe RestaurantExcepcio
             mitjançant JOptionPane.showMessageDialog
            */
                break;
            case 6: //desar
            /*
             Es comprova si s'ha seleccionat el restaurant, és a dir, si el restaurant és l'actual. Si no ho és mostrarà un missatge indicat que abans s'ha de seleccionar (JOptionPane.showMessageDialog)
                
                Si el restaurant està seleccionat: 
                    - Es mostra un diàleg (JOptionPane.showOptionDialog) amb un botó, que representa al mètode de carrega de documents XML
                    (atribut  METODEPERSISTENCIA de ControladorPrincipal)
                    - Un cop escollit el mètode, es desa el restaurant cridant al mètode desarRestaurant del gestor de persistència
                
                Si es produeix alguna excepció al desar el fitxer, en la captura s'ha de mostrar el missatge corresponent de la classe RestaurantExcepcio
                mitjançant JOptionPane.showMessageDialog
            */
                break;
        }
    }

    private void seleccionarRestaurant() {
        String[] nomRestaurants = new String[ControladorPrincipal.getComptaRestaurants()];
        int i = 0;
        
        for (Restaurant restaurant : ControladorPrincipal.getRestaurants()) {
            if (restaurant != null) {
                nomRestaurants[i] = restaurant.getNom();
            }
            i++;
        }
        
        int missatge = JOptionPane.QUESTION_MESSAGE;
        
        int code = JOptionPane.showOptionDialog(null, "Selecciona un restaurant", "Selecció d'un restaurant",JOptionPane.YES_NO_OPTION, missatge, null, nomRestaurants,null);
        
        if (code != -1) { //No s'ha seleccionat cancelar i s'ha seleccionar un restaurant
            ControladorPrincipal.setRestaurantActual(ControladorPrincipal.getRestaurants()[code]);
        }
    }

    private int comprovarRestaurant(int codi) {
        int index = -1;
        int i = 0;
        for (Restaurant restaurant : ControladorPrincipal.getRestaurants()) {
            if (restaurant != null) {
                if (restaurant.getCodi()==codi) {
                    index = i;
                    break;
                }
            }
            i++;
        }
        return index;
    }

    private Boolean validarRestaurant() {
        Boolean retorna = true;
        try {
            int codi = Integer.parseInt(restaurantForm.getCodi().getText());
            int index = comprovarRestaurant(codi);
            if (index >= 0) {
                retorna = false;
                JOptionPane.showMessageDialog(menuRestaurantVista.getFrame(), "El codi ja existeix");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(menuRestaurantVista.getFrame(), "El codi ha de ser numèric");
            retorna = false;
        }
        if (restaurantForm.getNom().getText().equals("") || restaurantForm.getAdreca().getText().equals("")) {
            JOptionPane.showMessageDialog(menuRestaurantVista.getFrame(), "S'han d'introduir el nom i l'adreça");
            retorna = false;
        }

        return retorna;
    }

}
