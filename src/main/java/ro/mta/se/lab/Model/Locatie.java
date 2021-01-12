package ro.mta.se.lab.Model;

import javafx.beans.property.*;

/**
 * @author Gunyx
 * Clasa ce contine parametrii pentru fiecare locatie din fisierul de intrare.
 *
 */

public class Locatie {
    IntegerProperty id;
    StringProperty numeOras;
    StringProperty codTara;
    FloatProperty longitudine;
    FloatProperty latitudine;

    public Locatie(int id, String numeOras, String codTara, float longitudine, float latitudine) {
        this.id = new SimpleIntegerProperty(id);
        this.numeOras = new SimpleStringProperty(numeOras);
        this.codTara = new SimpleStringProperty(codTara);
        this.longitudine = new SimpleFloatProperty(longitudine);
        this.latitudine = new SimpleFloatProperty(latitudine);
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public void setNumeOras(String numeOras) {
        this.numeOras.set(numeOras);
    }

    public void setCodTara(String codTara) {
        this.codTara.set(codTara);
    }

    public void setLongitudine(float longitudine) {
        this.longitudine.set(longitudine);
    }

    public void setLatitudine(float latitudine) {
        this.latitudine.set(latitudine);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getNumeOras() {
        return numeOras.get();
    }

    public StringProperty numeOrasProperty() {
        return numeOras;
    }

    public String getCodTara() {
        return codTara.get();
    }

    public StringProperty codTaraProperty() {
        return codTara;
    }

    public float getLongitudine() {
        return longitudine.get();
    }

    public FloatProperty longitudineProperty() {
        return longitudine;
    }

    public float getLatitudine() {
        return latitudine.get();
    }

    public FloatProperty latitudineProperty() {
        return latitudine;
    }
}

