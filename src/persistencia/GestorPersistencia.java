package persistencia;

import model.Restaurant;
import principal.RestaurantExcepcio;

/**
 *
 * @author Francesca
 */
public class GestorPersistencia {
    private GestorXML gestor;

    public GestorXML getGestor() {
        return gestor;
    }

    public void setGestor(GestorXML pGestor) {
        gestor = pGestor;
    }

    public void desarRestaurant(String tipusPersistencia, String nomFitxer, Restaurant restaurant) throws RestaurantExcepcio {
        if (tipusPersistencia.equals("XML")) {
            gestor = new GestorXML();
            gestor.desarRestaurant(nomFitxer, restaurant);
        }
    }

    public Restaurant carregaRestaurant(String tipusPersistencia, String nomFitxer) throws RestaurantExcepcio {
        ProveedorPersistencia gestor = null;
        Restaurant restaurant = null;
        
        if (tipusPersistencia.equals("XML")) {
            gestor = new GestorXML();
            restaurant=gestor.carregaRestaurant(nomFitxer);
        }
        
        return restaurant;
    }
}
