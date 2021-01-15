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

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

public class MeteoMonitorController {
    /**
     * Acest parametru seteaza tabela cu orase si tari afisate utilizatorilor
     */
    String inFile;
    String outFile;
    HashMap<String, ArrayList<String>> infoAfisare = new HashMap<>();
    List<Locatie>dateCautare;

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

    public MeteoMonitorController(String in, String out) {

        if ((in == null) || (out == null)) {
            System.out.println("Fisiere de input nule");
            //exceptie;
        }
        this.inFile = in;
        this.outFile = out;
    }
    @FXML
    public void selectCountryCod()
    {
        for (Locatie step : dateCautare) {
            if (infoAfisare.containsKey(step.getCodTara())) {

                infoAfisare.get(step.getCodTara()).add(step.getNumeOras());

            } else {
                infoAfisare.putIfAbsent(step.getCodTara(),new ArrayList<>());
                infoAfisare.get(step.getCodTara()).add(step.getNumeOras());
            }
        }
    }

    @FXML
    public void initialize() throws IOException {
        creareDateInterfata(inFile);
        Set<String> coduriTara = new TreeSet<>(infoAfisare.keySet());
        comboCountry.setItems(FXCollections.observableArrayList(coduriTara));
    }

    @FXML
    private void insertCountry(ActionEvent actionEvent){
        comboCity.setItems(FXCollections.observableArrayList(infoAfisare.get(comboCountry.getSelectionModel().getSelectedItem())));
        String s=comboCountry.getSelectionModel().getSelectedItem();
    }
    @FXML
    private void insertCity(ActionEvent actionEvent) throws IOException {
        String selectieCod=comboCountry.getSelectionModel().getSelectedItem();
        String selectieTara=comboCity.getSelectionModel().getSelectedItem();
        setareDetaliiVreme(selectieCod,selectieTara,0);
        nextDay.setVisible(true);
        nextDay.setText("Vremea maine?");
    }
    @FXML
    private void pressButton(ActionEvent actionEvent) throws IOException {
        String selectieCod=comboCountry.getSelectionModel().getSelectedItem();
        String selectieOras=comboCity.getSelectionModel().getSelectedItem();
        int varExist=0;
        String latCautata;
        String longCautata;
        if(btn==false) {
            for (Locatie step : dateCautare) {
                if(step.getNumeOras().equals(selectieOras))
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
                    setareDetaliiVreme(latCautata,longCautata,1);
                    break;
                }
                varExist=0;
            }

            nextDay.setText("Vremea astazi?");
        }else{
            setareDetaliiVreme(selectieCod,selectieOras,0);
            nextDay.setText("Vremea maine?");
        }
        btn=!btn;
    }

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

    private void setareDetaliiVreme(String codTara, String oras,int flag) throws IOException {

        if((codTara!=null)&&(oras!=null))
        {
            String afisare=null;
            Long timpUTC= Long.valueOf(0);
            HashMap<String,String>informatiiZona;

            InterogareServer req=new InterogareServer();
            JSONObject dateCautare=null;
            if(flag==0) {
                dateCautare = req.obtinereDateSelectie(codTara, oras);
                informatiiZona = (new JsonService()).parsareJson(dateCautare);
            }else {

                dateCautare = req.obtinereDateZile(oras,codTara);
                informatiiZona = (new JsonService()).parsareJsonDays(dateCautare);

            }

            Iterator iterator = informatiiZona.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry date = (Map.Entry) iterator.next();
                if(date.getKey().toString()=="id") {
                   // System.out.println("cev123a");
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

                        //System.out.println("exista");
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
            this.oras.setText(oras+","+codTara);

            //afisare=this.id.toString()+this.temperatura.toString()+this.temperaturaMin.toString()+this.temperaturaMax.toString()+this.presiune.toString()+this.umiditate.toString()+this.vitezaVant.toString()+this.nori.toString();
            afisare=oras+"-> "+"temperatura="+this.temperatura.getText()+" "+"presiune="+this.presiune.getText()+" "+"viteza vant="+this.vitezaVant.getText()+" "+"umiditate="+this.umiditate.getText()+" "+"prezenta nori="+this.nori.getText();
            LoggerFile f=new LoggerFile();
            f.scriereFisier(this.outFile,afisare);
        }
    }

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
                //System.out.println(step.getNumeOras());
            }
        }
    }
}
