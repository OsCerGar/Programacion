package persistencia;

import model.Restaurant;
import principal.RestaurantExcepcio;

/**
 *
 * @author Francesca
 */
public interface ProveedorPersistencia {
    public void desarRestaurant(String nomFitxer, Restaurant restaurant) throws RestaurantExcepcio;
    public Restaurant carregaRestaurant(String nomFitxer) throws RestaurantExcepcio;
}
