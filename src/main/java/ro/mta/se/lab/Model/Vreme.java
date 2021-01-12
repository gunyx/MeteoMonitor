package ro.mta.se.lab.Model;

import javafx.beans.property.*;

/**
 * @author Gunyx
 * Clasa ce contine parametrii intorsi de la server pentru fiecare oras(zona) din fisierul de intrare.
 * Aici sunt regasite proprietatile ce tin de vreme:
 *                                                   ->temepratura
 *                                                   ->presiune
 *                                                   ->viteza vant
 *                                                   ->valoare radiatii UV
 *                                                   ->umiditate
 */
public class Vreme {

    IntegerProperty id;
    FloatProperty temperatura;
    FloatProperty temperaturaMin;
    FloatProperty temeperaturaMax;
    FloatProperty presiune;
    FloatProperty vitezaVant;
    FloatProperty valoareUV;
    FloatProperty umiditate;

    public Vreme(int id, Float temperatura, Float temperaturaMin, Float temeperaturaMax, Float presiune, Float vitezaVant, Float valoareUV, Float umiditate) {
        this.id = new SimpleIntegerProperty(id);
        this.temperatura = new SimpleFloatProperty(temperatura);
        this.temperaturaMin = new SimpleFloatProperty(temperaturaMin);
        this.temeperaturaMax = new SimpleFloatProperty(temeperaturaMax);
        this.presiune = new SimpleFloatProperty(presiune);
        this.vitezaVant = new SimpleFloatProperty(vitezaVant);
        this.valoareUV =new SimpleFloatProperty(valoareUV);
        this.umiditate = new SimpleFloatProperty(umiditate);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public float getTemperatura() {
        return temperatura.get();
    }

    public FloatProperty temperaturaProperty() {
        return temperatura;
    }

    public float getTemperaturaMin() {
        return temperaturaMin.get();
    }

    public FloatProperty temperaturaMinProperty() {
        return temperaturaMin;
    }

    public float getTemeperaturaMax() {
        return temeperaturaMax.get();
    }

    public FloatProperty temeperaturaMaxProperty() {
        return temeperaturaMax;
    }

    public float getPresiune() {
        return presiune.get();
    }

    public FloatProperty presiuneProperty() {
        return presiune;
    }

    public float getVitezaVant() {
        return vitezaVant.get();
    }

    public FloatProperty vitezaVantProperty() {
        return vitezaVant;
    }

    public float getValoareUV() {
        return valoareUV.get();
    }

    public FloatProperty valoareUVProperty() {
        return valoareUV;
    }

    public float getUmiditate() {
        return umiditate.get();
    }

    public FloatProperty umiditateProperty() {
        return umiditate;
    }
}
