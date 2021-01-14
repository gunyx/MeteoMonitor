package ro.mta.se.lab.Controller;


import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import org.json.JSONObject;
import ro.mta.se.lab.Model.Locatie;
import java.io.IOException;
import java.util.*;

public class MeteoMonitorController {
    /**
     * Acest parametru seteaza tabela cu orase si tari afisate utilizatorilor
     */
    String inFile;
    String outFile;

    HashMap<String,List<String>>infoAfisare = new HashMap<>();
    @FXML
    private ChoiceBox choise_box_country;
    @FXML
    private Comparable choice_box_city;

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


    public MeteoMonitorController(String in, String out) {

        if((in==null)||(out==null))
        {
            System.out.println("Fisiere de input nule");
            //exceptie;
        }
        this.inFile=in;
        this.outFile=out;
    }

    public void initialize() throws IOException {
        creareDateInterfata(inFile);
    }



    public void creareDateInterfata(String inFile) throws IOException {
        List<Locatie> auxiliar;
        auxiliar=ParserFile.getInstance(inFile).getListaLocatiiIntrare();
        setareTariCod(auxiliar);//hashmap-ul il folosesc sa-l afisez la ScrollBar/cautare
    }

    private void setareDetaliiVreme(String codTara, String oras) throws IOException {

        if((codTara!=null)&&(oras!=null))
        {
            String afisare=null;
            HashMap<String,String>informatiiZona;
            String valoareString=null;

            InterogareServer req=new InterogareServer();
            JSONObject dateCautare=req.obtinereDateSelectie(codTara,oras);
            informatiiZona=(new JsonService()).parsareJson(dateCautare);

            Iterator iterator = informatiiZona.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry date = (Map.Entry) iterator.next();
                if(date.getKey().toString()=="id") {
                    id.setText(date.getValue().toString());
                }else if(date.getKey().toString()=="temp"){
                    temperatura.setText(date.getValue().toString()+" \u2103"+" ( ");
                } else if(date.getKey().toString()=="tempMin"){
                    temperaturaMin.setText(date.getValue().toString()+" - ");
                } else if(date.getKey().toString()=="tempMax"){
                    temperaturaMax.setText(date.getValue().toString()+" )");
                } else if(date.getKey().toString()=="pressure"){
                    presiune.setText(date.getValue().toString()+" hPa");
                } else if(date.getKey().toString()=="humidity"){
                    umiditate.setText(date.getValue().toString()+"%");
                } else if(date.getKey().toString()=="wind"){
                    vitezaVant.setText(date.getValue().toString()+"m/s");
                } else if(date.getKey().toString()=="clouds"){
                    nori.setText(date.getValue().toString()+"%");
                }
            }
            afisare=this.id.toString()+this.temperatura.toString()+this.temperaturaMin.toString()+this.temperaturaMax.toString()+this.presiune.toString()+this.umiditate.toString()+this.vitezaVant.toString()+this.nori.toString();
            LoggerFile f=new LoggerFile();
            f.scriereFisier(this.outFile,afisare);
        }
    }

    private void setareTariCod(List<Locatie>primit) {
        if(primit==null)
        {
            //exceptie
        }
        for (Locatie step : primit) {
            if (infoAfisare.containsKey(step.getCodTara())) {

                infoAfisare.get(step.getCodTara()).add(step.getNumeOras());

            } else {
                infoAfisare.putIfAbsent(step.getCodTara(),new ArrayList<>());
                infoAfisare.get(step.getCodTara()).add(step.getNumeOras());
            }
        }
    }
}
