package persistencia;

import model.elementsMobils.Cambrer;
import model.elementsMobils.ElementTaula;
import model.elementsMobils.Reserva;
import model.elementsMobils.Taula;
import java.io.FileWriter;
import nu.xom.*;
import model.Menjador;
import model.Restaurant;
import principal.RestaurantExcepcio;

/**
 *
 * @author fta
 */
public class GestorXML implements ProveedorPersistencia {

    private Document doc;
    private Restaurant restaurant;

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant pRestaurant) {
        restaurant = pRestaurant;
    }

    @Override
    public void desarRestaurant(String nomFitxer, Restaurant pRestaurant) throws RestaurantExcepcio {
        construeixModel(pRestaurant);
        desarModel(nomFitxer);
    }

    @Override
    public Restaurant carregaRestaurant(String nomFitxer) throws RestaurantExcepcio {
        carregarFitxer(nomFitxer);
        fitxerRestaurant();
        return restaurant;
    }

    /*Paràmetres: Restaurant a partir del qual volem construir el model
     *
     *Acció: 
     *Llegir els atributs de l'objecte Restaurant passat per paràmetre per construir
     *un model (document XML) sobre el Document doc (atribut de GestorXML).
     *L'arrel del document XML és "restaurant" i heu d'afegir-ne els valors de codi,
     *nom i adreça com atributs. Aquesta arrel, l'heu d'afegir a doc.
     *Un cop fet això, heu de recórrer l'ArrayList elements de Restaurnat i per 
     *a cada element, afegir un fill a doc. Cada fill tindrà com atributs els 
     *atributs de l'objecte (nif, nom, …)
     *Si es tracta d'una taula, a més, heu d'afegir fills addicionals amb els 
     *valors dels elements d'aquesta taula (els seus cambrers i reserves)
     *Si es tracta d'un menjador, a més, heu d'afegir fills addicionals amb els 
     *valors dels elements d'aquest menjadors (les seves taules)
     *
     *Retorn: cap
     */
    private void construeixModel(Restaurant pRestaurant) throws RestaurantExcepcio {
        //Creem l'element arrel restaurant
        Element arrel = new Element("restaurant");
        //Inserim els atributs de restaurant
        arrel.addAttribute(new Attribute("codi", String.valueOf(pRestaurant.getCodi())));
        arrel.addAttribute(new Attribute("adreça", pRestaurant.getAdreca()));
        arrel.addAttribute(new Attribute("nom", pRestaurant.getNom()));

        for (int i = 0; i < pRestaurant.getElementsRestaurant().size(); i++) {
            if (pRestaurant.getElementsRestaurant().get(i) instanceof Cambrer) {
                //creem cambrer
                Element cambrer = new Element("cambrer");

                //Afegim atributs cambrer
                cambrer.addAttribute(new Attribute("codi", ((Cambrer) pRestaurant.getElementsRestaurant().get(i)).getCodi()));
                cambrer.addAttribute(new Attribute("nom", ((Cambrer) pRestaurant.getElementsRestaurant().get(i)).getNom()));
                cambrer.addAttribute(new Attribute("telefon", ((Cambrer) pRestaurant.getElementsRestaurant().get(i)).getTelefon()));
                cambrer.addAttribute(new Attribute("torn", ((Cambrer) pRestaurant.getElementsRestaurant().get(i)).getTorn()));
                if (((Cambrer) pRestaurant.getElementsRestaurant().get(i)).getActiu()) {
                    cambrer.addAttribute(new Attribute("actiu", "si"));
                } else {
                    cambrer.addAttribute(new Attribute("actiu", "no"));
                }

                //Afegim cambrer a restaurant
                arrel.appendChild(cambrer);

            } else if (pRestaurant.getElementsRestaurant().get(i) instanceof Reserva) {
                //creem reserva
                Element reserva = new Element("reserva");

                //Afegim atributs reserva
                reserva.addAttribute(new Attribute("codi", ((Reserva) pRestaurant.getElementsRestaurant().get(i)).getCodi()));
                reserva.addAttribute(new Attribute("nom", ((Reserva) pRestaurant.getElementsRestaurant().get(i)).getNom()));
                reserva.addAttribute(new Attribute("telefon", ((Reserva) pRestaurant.getElementsRestaurant().get(i)).getTelefon()));
                reserva.addAttribute(new Attribute("data", ((Reserva) pRestaurant.getElementsRestaurant().get(i)).getData()));
                reserva.addAttribute(new Attribute("hora", ((Reserva) pRestaurant.getElementsRestaurant().get(i)).getHora()));
                reserva.addAttribute(new Attribute("comensals", String.valueOf(((Reserva) pRestaurant.getElementsRestaurant().get(i)).getComensals())));

                //Afegim reserva a restaurant
                arrel.appendChild(reserva);

            } else if (pRestaurant.getElementsRestaurant().get(i) instanceof Taula) {
                //creem taula
                Element taula = new Element("taula");

                //Afegim atributs taula
                taula.addAttribute(new Attribute("codi", ((Taula) pRestaurant.getElementsRestaurant().get(i)).getCodi()));
                taula.addAttribute(new Attribute("places", String.valueOf(((Taula) pRestaurant.getElementsRestaurant().get(i)).getPlaces())));

                //Afegim elements taula
                for (int j = 0; j < ((Taula) pRestaurant.getElementsRestaurant().get(i)).getElementsTaula().size(); j++) {
                    if (((Taula) pRestaurant.getElementsRestaurant().get(i)).getElementsTaula().get(j) instanceof Cambrer) {
                        //creem cambrer
                        Element cambrer = new Element("cambrer");

                        //Afegim atributs cambrer
                        cambrer.addAttribute(new Attribute("codi", ((Cambrer) ((Taula) pRestaurant.getElementsRestaurant().get(i)).getElementsTaula().get(j)).getCodi()));
                        cambrer.addAttribute(new Attribute("nom", ((Cambrer) ((Taula) pRestaurant.getElementsRestaurant().get(i)).getElementsTaula().get(j)).getNom()));
                        cambrer.addAttribute(new Attribute("telefon", ((Cambrer) ((Taula) pRestaurant.getElementsRestaurant().get(i)).getElementsTaula().get(j)).getTelefon()));
                        cambrer.addAttribute(new Attribute("torn", ((Cambrer) ((Taula) pRestaurant.getElementsRestaurant().get(i)).getElementsTaula().get(j)).getTorn()));
                        if (((Cambrer) ((Taula) pRestaurant.getElementsRestaurant().get(i)).getElementsTaula().get(j)).getActiu()) {
                            cambrer.addAttribute(new Attribute("actiu", "si"));
                        } else {
                            cambrer.addAttribute(new Attribute("actiu", "no"));
                        }

                        //Afegim cambrer a taula
                        taula.appendChild(cambrer);

                    } else { //És reserva
                        //creem reserva
                        Element reserva = new Element("reserva");

                        //Afegim atributs reserva
                        reserva.addAttribute(new Attribute("codi", ((Reserva) ((Taula) pRestaurant.getElementsRestaurant().get(i)).getElementsTaula().get(j)).getCodi()));
                        reserva.addAttribute(new Attribute("nom", ((Reserva) ((Taula) pRestaurant.getElementsRestaurant().get(i)).getElementsTaula().get(j)).getNom()));
                        reserva.addAttribute(new Attribute("telefon", ((Reserva) ((Taula) pRestaurant.getElementsRestaurant().get(i)).getElementsTaula().get(j)).getTelefon()));
                        reserva.addAttribute(new Attribute("data", ((Reserva) ((Taula) pRestaurant.getElementsRestaurant().get(i)).getElementsTaula().get(j)).getData()));
                        reserva.addAttribute(new Attribute("hora", ((Reserva) ((Taula) pRestaurant.getElementsRestaurant().get(i)).getElementsTaula().get(j)).getHora()));
                        reserva.addAttribute(new Attribute("comensals", String.valueOf(((Reserva) ((Taula) pRestaurant.getElementsRestaurant().get(i)).getElementsTaula().get(j)).getComensals())));

                        //Afegim reserva a taula
                        taula.appendChild(reserva);
                    }

                }

                //Afegim taula a restaurant
                arrel.appendChild(taula);

            } else { //És menjador
                //creem menjador
                Element menjador = new Element("menjador");

                //Afegim atributs menjador
                menjador.addAttribute(new Attribute("codi", ((Menjador) pRestaurant.getElementsRestaurant().get(i)).getCodi()));
                menjador.addAttribute(new Attribute("places", String.valueOf(((Menjador) pRestaurant.getElementsRestaurant().get(i)).getPlaces())));
                menjador.addAttribute(new Attribute("placesOcupades", String.valueOf(((Menjador) pRestaurant.getElementsRestaurant().get(i)).getPlacesOcupades())));

                //Afegim taules al restaurant
                for (int j = 0; j < ((Menjador) pRestaurant.getElementsRestaurant().get(i)).getTaules().size(); j++) {
                    //creem taula
                    Element taula = new Element("taula");

                    //Afegim atributs taula
                    taula.addAttribute(new Attribute("codi", (((Taula) ((Menjador) pRestaurant.getElementsRestaurant().get(i)).getTaules().get(j))).getCodi()));
                    taula.addAttribute(new Attribute("places", String.valueOf((((Taula) ((Menjador) pRestaurant.getElementsRestaurant().get(i)).getTaules().get(j))).getPlaces())));

                    //Afegim elements taula
                    for (int k = 0; k < (((Taula) ((Menjador) pRestaurant.getElementsRestaurant().get(i)).getTaules().get(j))).getElementsTaula().size(); k++) {
                        if ((((Taula) ((Menjador) pRestaurant.getElementsRestaurant().get(i)).getTaules().get(j))).getElementsTaula().get(k) instanceof Cambrer) {
                            //creem cambrer
                            Element cambrer = new Element("cambrer");

                            //Afegim atributs cambrer
                            cambrer.addAttribute(new Attribute("codi", (((Taula) ((Menjador) pRestaurant.getElementsRestaurant().get(i)).getTaules().get(j))).getElementsTaula().get(k).getCodi()));
                            cambrer.addAttribute(new Attribute("nom", (((Taula) ((Menjador) pRestaurant.getElementsRestaurant().get(i)).getTaules().get(j))).getElementsTaula().get(k).getNom()));
                            cambrer.addAttribute(new Attribute("telefon", (((Taula) ((Menjador) pRestaurant.getElementsRestaurant().get(i)).getTaules().get(j))).getElementsTaula().get(k).getTelefon()));
                            cambrer.addAttribute(new Attribute("torn", ((Cambrer) ((Taula) ((Menjador) pRestaurant.getElementsRestaurant().get(i)).getTaules().get(j)).getElementsTaula().get(k)).getTorn()));
                            if (((Cambrer) ((Taula) ((Menjador) pRestaurant.getElementsRestaurant().get(i)).getTaules().get(j)).getElementsTaula().get(k)).getActiu()) {
                                cambrer.addAttribute(new Attribute("actiu", "si"));
                            } else {
                                cambrer.addAttribute(new Attribute("actiu", "no"));
                            }

                            //Afegim cambrer a taula
                            taula.appendChild(cambrer);

                        } else { //És reserva
                            //creem reserva
                            Element reserva = new Element("reserva");

                            //Afegim atributs reserva
                            reserva.addAttribute(new Attribute("codi", (((Taula) ((Menjador) pRestaurant.getElementsRestaurant().get(i)).getTaules().get(j))).getElementsTaula().get(k).getCodi()));
                            reserva.addAttribute(new Attribute("nom", (((Taula) ((Menjador) pRestaurant.getElementsRestaurant().get(i)).getTaules().get(j))).getElementsTaula().get(k).getNom()));
                            reserva.addAttribute(new Attribute("telefon", (((Taula) ((Menjador) pRestaurant.getElementsRestaurant().get(i)).getTaules().get(j))).getElementsTaula().get(k).getTelefon()));
                            reserva.addAttribute(new Attribute("data", ((Reserva) ((Taula) ((Menjador) pRestaurant.getElementsRestaurant().get(i)).getTaules().get(j)).getElementsTaula().get(k)).getData()));
                            reserva.addAttribute(new Attribute("hora", ((Reserva) ((Taula) ((Menjador) pRestaurant.getElementsRestaurant().get(i)).getTaules().get(j)).getElementsTaula().get(k)).getHora()));
                            reserva.addAttribute(new Attribute("comensals", String.valueOf(((Reserva) ((Taula) ((Menjador) pRestaurant.getElementsRestaurant().get(i)).getTaules().get(j)).getElementsTaula().get(k)).getComensals())));

                            //Afegim reserva a taula
                            taula.appendChild(reserva);
                        }

                    }

                    //Afegim taula a menjador
                    menjador.appendChild(taula);
                }

                //Afegim menjador restaurant
                arrel.appendChild(menjador);

            }
        }

        //Creem nou document XML amb tot l'arbre XML creat
        doc = new Document(arrel);
    }

    private void desarModel(String rutaFitxer) throws RestaurantExcepcio {
        try {
            FileWriter fitxer = new FileWriter(rutaFitxer, false); //Obrim fitxer per sobreescriure
            fitxer.write(doc.toXML());
            fitxer.close();
        } catch (Exception e) {
            throw new RestaurantExcepcio("GestorXML.desar");
        }
    }

    private void carregarFitxer(String rutaFitxer) throws RestaurantExcepcio { //throws RestaurantExcepcio, ParsingException {
        Builder builder = new Builder();
        try {
            doc = builder.build(rutaFitxer);
            System.out.println(doc.toXML());
        } catch (Exception e) {
            throw new RestaurantExcepcio("GestorXML.carregar");
        }
    }

    /*Paràmetres: cap
     *
     *Acció: 
     *Llegir el fitxer del disc i carregar-lo sobre l'atribut doc.
     *Llegir el doc per assignar valors als atributs de Restaurant (i la resta 
     *d'objectes)
     *Per fer això, com l'arrel conté els atributs del restaurant, al fer 
     *Element arrel = doc.getDocument(); ja podeu crear l'objecte Restaurant.
     *Un cop fet això, heu de recòrrer el doc i per cada fill, haureu d'afegir un
     *element a l'ArrayList elements de Restaurant (nouXXX(.....))
     *Si el fill (del doc) que s'ha llegit és una taula o un menjador, recordeu 
     *que a més de fer novaTaula o nouMenjador per afegir-los a Restaurnat, també
     *haureu d'afegir en la taula els seus cambrers i reserves, i en menjador les 
     *seves taules.
     *
     *Retorn: cap
     */
    private void fitxerRestaurant() throws RestaurantExcepcio {
        Element arrel = doc.getRootElement();

        //Es crea l'objecte restaurant
        Restaurant nouRestaurant = new Restaurant(Integer.parseInt(arrel.getAttributeValue("codi")), arrel.getAttributeValue("nom"), arrel.getAttributeValue("adreca"));

        Elements elements; //Elements del restaurant.
        Elements elementsTaula; //Elements d'una taula
        Elements elementsMenjador; //Elements d'un menjador
        int index; //Index dels elements d'un restaurant

        //Recorregut dels cambrers      
        elements = arrel.getChildElements("cambrer");
        for (int i = 0; i < elements.size(); i++) {
            //Es crea l'objecte Cambrer
            Cambrer cambrer = new Cambrer(elements.get(i).getAttributeValue("codi"), elements.get(i).getAttributeValue("nom"), elements.get(i).getAttributeValue("telefon"), elements.get(i).getAttributeValue("torn"));

            //Mirem si el cambrer està actiu o no
            if (elements.get(i).getAttributeValue("actiu").equals("si")) {
                cambrer.setActiu(true);
            } else {
                cambrer.setActiu(false);
            }

            //Afegim el nou cambrer als elements del restaurant.
            nouRestaurant.nouCambrer(cambrer);
        }

        //Recorregut de les reserves    
        elements = arrel.getChildElements("reserva");
        for (int i = 0; i < elements.size(); i++) {
            
            //Es crea l'objecte reserva
            Reserva reserva = new Reserva(elements.get(i).getAttributeValue("codi"), elements.get(i).getAttributeValue("nom"), elements.get(i).getAttributeValue("telefon"), elements.get(i).getAttributeValue("data"), elements.get(i).getAttributeValue("hora"), Integer.parseInt(elements.get(i).getAttributeValue("comensals")));

            //Afegim la nova reserva als elements del restaurant.
            nouRestaurant.novaReserva(reserva);
        }

        //Recorregut de les taules 
        elements = arrel.getChildElements("taula");
        for (int i = 0; i < elements.size(); i++) {

            //Es crea l'objecte Taula
            Taula taula = new Taula(elements.get(i).getAttributeValue("codi"), Integer.parseInt(elements.get(i).getAttributeValue("places")));

            //Recorregut dels cambrers
            elementsTaula = elements.get(i).getChildElements("cambrer");
            for (int j = 0; j < elementsTaula.size(); j++) {

                //Seleccionem el cambrer de la taula, del vector d'elements del restaurant
                index = nouRestaurant.seleccionarElementRestaurant(2, elementsTaula.get(i).getAttributeValue("codi"));

                //Afegim el cambrer seleccionat a la taula.
                taula.afegirElementTaula((ElementTaula) nouRestaurant.getElementsRestaurant().get(index));
            }

            //Recorregut de les reserves
            elementsTaula = elements.get(i).getChildElements("reserva");
            for (int j = 0; j < elementsTaula.size(); j++) {

                //Seleccionem la reserva de la taula, del vector d'elements del restaurant
                index = nouRestaurant.seleccionarElementRestaurant(1, elementsTaula.get(i).getAttributeValue("codi"));

                //Afegim la reserva seleccionada a la taula.
                taula.afegirElementTaula((ElementTaula) nouRestaurant.getElementsRestaurant().get(index));
            }

            //Afegim la nova taula als elements del restaurant.
            nouRestaurant.novaTaula(taula);
        }

        //Recorregut dels menjadors    
        elements = arrel.getChildElements("menjador");
        for (int i = 0; i < elements.size(); i++) {

            //Es crea l'objecte Menjador
            Menjador menjador = new Menjador(elements.get(i).getAttributeValue("codi"), Integer.parseInt(elements.get(i).getAttributeValue("places")));

            //Afegim al menjador les places ocupades
            menjador.setPlacesOcupades(Integer.parseInt(elements.get(i).getAttributeValue("placesOcupades")));

            //Recorregut de les taules
            elementsMenjador = elements.get(i).getChildElements("taula");
            for (int j = 0; j < elementsMenjador.size(); j++) {

                //Seleccionem la taula del menjador, del vector d'elements del restaurant
                index = nouRestaurant.seleccionarElementRestaurant(3, elementsMenjador.get(i).getAttributeValue("codi"));

                //Afegim la taula seleccionat al menjador.
                menjador.afegirTaulaMenjador((Taula) nouRestaurant.getElementsRestaurant().get(index));
            }

            //Afegim el nou menjador als elements del restaurant.
            nouRestaurant.nouMenjador(menjador);
        }
    }
}
