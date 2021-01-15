package ro.mta.se.lab.Controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.JSONObject;
import ro.mta.se.lab.Model.Locatie;
import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import ro.mta.se.lab.View.LoggerFile;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

public class MeteoMonitorController {
    /**
     * Datele clasei controller:
     *                          ->inFile numele/path-ul fisierului de intrare ce continere informatiile despre locatii
     *                          ->outFile numele/path-ul fisierului de iesire unde este scris istoricul cautarii
     *                          ->infoAfisare hash map ce contine lista cu orase pentru fiecare cod de tara
     *                          ->dateCautare lista cu toate orasele incarcate din fisierul de intrare
     */
    String inFile;
    String outFile;
    HashMap<String, ArrayList<String>> infoAfisare = new HashMap<>();
    List<Locatie>dateCautare;
    JSONObject jsonDate=null;

    @FXML
    private ComboBox<String> comboCountry;
    @FXML
    private  ComboBox<String> comboCity;
    @FXML
    private Button nextDay;
    @FXML
    private ImageView picto;
    @FXML
    private Label descriere;
    @FXML
    private Label id;
    @FXML
    private Label temperatura;
    @FXML
    private Label temperaturaMin;
    @FXML
    private Label temperaturaMax;
    @FXML
    private Label presiune;
    @FXML
    private Label vitezaVant;
    @FXML
    private Label umiditate;
    @FXML
    private Label nori;
    @FXML
    private Label oras;
    @FXML
    private Label data;

    private Boolean btn=false;

    /**
     * Constructorul clasei ce seteaza fisierele de intrare si de iesire
     * @param in
     * @param out
     */
    public MeteoMonitorController(String in, String out) {

        if ((in == null) || (out == null)) {
            System.out.println("Eroare la primirea fisierelor de intrare");
            //exceptie;
        }
        this.inFile = in;
        this.outFile = out;
    }
    @FXML
    public void selectCountryCod() {

    }

    /**
     * Metoda pentru setarea initiala a combobox-ului cu toate codurile de tara din fisierul de intrare.
     * Este prima metoda apelata la crearea scenei din clasa Controller
     * @throws IOException
     */
    @FXML
    public void initialize() throws IOException {
        creareDateInterfata(inFile);
        Set<String> coduriTara = new TreeSet<>(infoAfisare.keySet());
        comboCountry.setItems(FXCollections.observableArrayList(coduriTara));
    }

    /**
     * Metoda ce se apeleaza dupa selectarea unui cod de tara. Ea este responsabila de completarea combobox-ului de orase corespunzatoare codului de tara selectat
     * @param actionEvent
     */
    @FXML
    private void insertCountry(ActionEvent actionEvent){
        comboCity.setItems(FXCollections.observableArrayList(infoAfisare.get(comboCountry.getSelectionModel().getSelectedItem())));
        String s=comboCountry.getSelectionModel().getSelectedItem();
        nextDay.setVisible(false);
        //System.out.println(s);
    }

    /**
     * Metoda apelata dupa selectarea orasului pentru care se doreste obtinerea vremii.
     * Ea apeleaza metoda de request la server si metoda de completare a valorilor primit (parsate)
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    private void insertCity(ActionEvent actionEvent) throws IOException {
        String selectieCod=comboCountry.getSelectionModel().getSelectedItem();
        String selectieTara=comboCity.getSelectionModel().getSelectedItem();
        String latCautata;
        String longCautata;
        int varExist=0;
        for (Locatie step : dateCautare) {
            if(step.getNumeOras().equals(selectieTara))
            {
                varExist++;
            }
            if(step.getCodTara().equals(selectieCod))
            {
                varExist++;
            }
            if(varExist==2)
            {
                latCautata=String.valueOf(step.getLatitudine());
                longCautata=String.valueOf(step.getLongitudine());
                //System.out.println(latCautata);
                //System.out.println(longCautata);
                setareDetaliiVreme(latCautata,longCautata,1,0);
                break;
            }
            varExist=0;
        }

        //setareDetaliiVreme(selectieCod,selectieTara,0);
        nextDay.setVisible(true);
        nextDay.setText("Vremea maine?");
    }

    /**
     * Metoda ce se executa la apasarea butonului de obtinerii a vremii fie pentru ziua urmatoare, fie pentru ziua curenta(daca deja este afisata vremea pentru ziua urmatoare)
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    private void pressButton(ActionEvent actionEvent) throws IOException {

        String selectieCod=comboCountry.getSelectionModel().getSelectedItem();
        String selectieOras=comboCity.getSelectionModel().getSelectedItem();
        String latCautata=null;
        String longCautata=null;

        for (Locatie step : dateCautare) {
            if ((step.getNumeOras().equals(selectieOras)) && (step.getCodTara().equals(selectieCod))) {
                latCautata = String.valueOf(step.getLatitudine());
                longCautata = String.valueOf(step.getLongitudine());
                break;
            }
        }
        if(btn==false) {//vremea maine
            setareDetaliiVreme(latCautata,longCautata,1,1);
            nextDay.setText("Vremea astazi?");
        }else{//vremea curenta
            setareDetaliiVreme(latCautata,longCautata,1,0);
            nextDay.setText("Vremea maine?");
        }

        btn=!btn;
    }

    /**
     * Metoda ce apeleaza citirea fisierului de intrare, parsarea lui si se ocupa de initializarea listei de locatii
     * @param inFile path-ul fisierului de intrare de unde sunt citite datele
     * @throws IOException
     */
    public void creareDateInterfata(String inFile) throws IOException {
        List<Locatie> auxiliar=null;
        if(inFile==null)
        {
            System.out.println("Fisier intrare trimis null");
        }else{
            ParserFile parser=ParserFile.getInstance(inFile);
            auxiliar=parser.getListaLocatiiIntrare();
            setareTariCod(auxiliar);//hashmap-ul il folosesc sa-l afisez la ScrollBar/cautare
        }
    }

    /**
     * Metoda responsabila cu setarea valorilor din interfata grafica pe baza selectiei utilizatorului
     * @param codTara Codul de tara pentru care se doreste afisarea vremii
     * @param oras Orasul pentru care se doreste afisarea vremii
     * @param flag setarea functiei de apelare a serverului ( prin parametrii long+lat sau prin numeOras+codTara)
     * @param day setarea valorilor afisate pentru o anumita zi (14 zile request la server)
     * @throws IOException
     */
    private void setareDetaliiVreme(String codTara, String oras,int flag,int day) throws IOException {

        if((codTara!=null)&&(oras!=null))
        {
            String afisare=null;
            Long timpUTC= Long.valueOf(0);
            HashMap<String,String>informatiiZona;

            InterogareServer req=new InterogareServer();
            //JSONObject dateCautare=null;

            if(flag==0) {//pentru cod+oras
                this.jsonDate = req.obtinereDateSelectie(codTara, oras);
                informatiiZona = (new JsonService()).parsareJson(this.jsonDate);
            }else {//pentru long+lat
                if(day==0) {
                    this.jsonDate = req.obtinereDateZile(oras, codTara);
                }
                informatiiZona = (new JsonService()).parsareJsonDays(this.jsonDate,day);
            }

            Iterator iterator = informatiiZona.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry date = (Map.Entry) iterator.next();
                if(date.getKey().toString()=="id") {
                   // id.setText(date.getValue().toString());
                }else if(date.getKey().toString()=="temp"){
                    temperatura.setText(date.getValue().toString()+" \u2103");
                } else if(date.getKey().toString()=="tempMin"){
                    temperaturaMin.setText(date.getValue().toString()+" \u2103");
                } else if(date.getKey().toString()=="tempMax"){
                    temperaturaMax.setText(date.getValue().toString()+" \u2103");
                } else if(date.getKey().toString()=="pressure"){
                    presiune.setText(date.getValue().toString()+" hPa");
                } else if(date.getKey().toString()=="humidity"){
                    umiditate.setText(date.getValue().toString()+"%");
                } else if(date.getKey().toString()=="wind"){
                    vitezaVant.setText(date.getValue().toString()+"m/s");
                } else if(date.getKey().toString()=="clouds"){
                    nori.setText(date.getValue().toString()+"%");
                }else if(date.getKey().toString()=="description"){
                    descriere.setText(date.getValue().toString());
                }else if(date.getKey().toString()=="icon"){
                    String pathImagine = date.getValue().toString();
                    pathImagine = "./src/main/resources/img/pictograme/" + pathImagine + ".png";
                    File imgFile = new  File(pathImagine);

                    if(imgFile.exists()){
                        Image imagine = new Image(imgFile.toURI().toString());
                        picto.setImage((imagine));
                    }
                }else if(date.getKey().toString()=="dt"){
                    timpUTC += Long.parseLong(date.getValue().toString());
                }else if(date.getKey().toString()=="dtDiff"){
                    timpUTC += Long.parseLong(date.getValue().toString());
                }
            }
            DateTimeFormatter formatData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedDtm = Instant.ofEpochSecond(timpUTC).atZone(ZoneId.of("UTC")).format(formatData);
            data.setText(formattedDtm);
            this.oras.setText(comboCity.getSelectionModel().getSelectedItem()+","+comboCountry.getSelectionModel().getSelectedItem());

            if(day!=0)
            {
                afisare=comboCity.getSelectionModel().getSelectedItem()+" urmatoarea zi"+"-> "+"temperatura="+this.temperatura.getText()+" "+"presiune="+this.presiune.getText()+" "+"viteza vant="+this.vitezaVant.getText()+" "+"umiditate="+this.umiditate.getText()+" "+"prezenta nori="+this.nori.getText();
            }else{
                afisare=comboCity.getSelectionModel().getSelectedItem()+"-> "+"temperatura="+this.temperatura.getText()+" "+"presiune="+this.presiune.getText()+" "+"viteza vant="+this.vitezaVant.getText()+" "+"umiditate="+this.umiditate.getText()+" "+"prezenta nori="+this.nori.getText();
            }
            //scrierea in fisierul de iesire
            LoggerFile f=new LoggerFile();
            f.scriereFisier(this.outFile,afisare);
        }
    }

    /**
     * Metoda ce seteaza lista de locatii a controlerului si tabela hash ce contine codurile de tara si orasele pentru fiecare cod in parte.
     * @param primit
     */
    private void setareTariCod(List<Locatie>primit) {
        if(primit==null)
        {
            //exceptie
        }
        this.dateCautare=primit;
        for (Locatie step : primit) {
            if (infoAfisare.containsKey(step.getCodTara())) {
                infoAfisare.get(step.getCodTara()).add(step.getNumeOras());
                //System.out.println( step.getLatitudine());
                //System.out.println(step.getCodTara());

            } else {
                infoAfisare.putIfAbsent(step.getCodTara(),new ArrayList<>());
                infoAfisare.get(step.getCodTara()).add(step.getNumeOras());
            }
        }
    }
}
