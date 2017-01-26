package principal;

/**
 *
 * @author fta
 */
public class RestaurantExcepcio extends Exception {

    private String codiCausa = "desconegut";
    private String missatge;

    public RestaurantExcepcio(String pCodiCausa) {
        codiCausa = pCodiCausa;
        switch (codiCausa) {
            case "1":
                missatge = "L'opció ha de ser correcta";
                break;
            case "2":
                missatge = "L'element no existeix";
                break;
            case "GestorXML.model":
                missatge = "No s'ha pogut crear el model XML per desar el restaurant";
                break;
            case "GestorXML.desar":
                missatge = "No s'ha pogut desar el restaurant a causa d'error d'entrada/sortida";
                break;
            case "GestorXML.carrega":
                missatge = "No s'ha pogut carregar el restaurant a causa d'error d'entrada/sortida";
                break;
            default:
                missatge = "Error desconegut";
                break;
        }
    }

    @Override
    public String getMessage() {
        return "Amb el codi de causa:  " + codiCausa + ", s'ha generat el següent missatge: " + missatge;
    }

}