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
 *                                                   ->prezenta nori
 *
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
    FloatProperty nori;

    /**
     * Constructorul clasei vreme ce seteaza parametrii pentru fiecare locatie in parte
     * @param id id oras
     * @param temperatura temperatura locala
     * @param temperaturaMin val tem min
     * @param temeperaturaMax val tem max
     * @param presiune presiune actuala
     * @param vitezaVant viteza actuala
     * @param valoareUV valoare UV
     * @param umiditate umiditate
     * @param nori prezenta nori
     */
    public Vreme(int id, float temperatura, float temperaturaMin, float temeperaturaMax, float presiune, float vitezaVant, float valoareUV, float umiditate, float nori) {
        this.id = new SimpleIntegerProperty(id);
        this.temperatura = new SimpleFloatProperty(temperatura);
        this.temperaturaMin = new SimpleFloatProperty(temperaturaMin);
        this.temeperaturaMax = new SimpleFloatProperty(temeperaturaMax);
        this.presiune = new SimpleFloatProperty(presiune);
        this.vitezaVant = new SimpleFloatProperty(vitezaVant);
        this.valoareUV =new SimpleFloatProperty(valoareUV);
        this.umiditate = new SimpleFloatProperty(umiditate);
        this.nori = new SimpleFloatProperty(nori);
    }


    public float getNori() {
        return nori.get();
    }

    public FloatProperty noriProperty() {
        return nori;
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
