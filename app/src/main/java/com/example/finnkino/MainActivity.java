package com.example.finnkino;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


public class MainActivity extends AppCompatActivity {

    Spinner spinner1,spinner2;

    ArrayList<String> arrayList_teatteri;
    ArrayAdapter<String> arrayAdapter_teatteri;

    ArrayList<String> arrayList_pvm,arrayList_elokuva,arrayList_klo;
    ArrayAdapter<String> arrayAdapter_pvm,arrayAdapter_elokuva,arrayAdapter_klo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        readXML();
        Spinner pvmspinner = (Spinner) findViewById(R.id.spinner2);
        Spinner filmspinner = (Spinner) findViewById(R.id.spinner3);
        Spinner klospinner = (Spinner) findViewById(R.id.spinner4);



        //pvm Spinner
        arrayList_pvm=new ArrayList<>();
        arrayList_pvm.add("21.3.2021");
        arrayList_pvm.add("22.3.2021");
        arrayList_pvm.add("23.3.2021");
        arrayList_pvm.add("24.3.2021");
        arrayList_pvm.add("25.3.2021");

        arrayAdapter_pvm=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_pvm);

        pvmspinner.setAdapter(arrayAdapter_pvm);

        //klo spinner
        arrayList_klo=new ArrayList<>();
        arrayList_klo.add("12:00");
        arrayList_klo.add("14:00");
        arrayList_klo.add("15:30");
        arrayList_klo.add("16:47");
        arrayList_klo.add("20:38");

        arrayAdapter_klo=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList_klo);

        klospinner.setAdapter(arrayAdapter_klo);

        //Elokuva spinner
        arrayList_elokuva= new ArrayList<>();
        arrayList_elokuva.add("Parasite");
        arrayList_elokuva.add("Star wars episode V - The Empire Strikes Back");
        arrayList_elokuva.add("Mother");
        arrayList_elokuva.add("Palm Springs");
        arrayList_elokuva.add("The Greatest Showman");

        arrayAdapter_elokuva=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_elokuva);

        filmspinner.setAdapter(arrayAdapter_elokuva);


    }

    public void readXML(){
        try {
            Spinner tspinner = (Spinner) findViewById(R.id.spinner1);
            ArrayList<Theatre> theatre_list =new ArrayList<>();
            ArrayAdapter<Theatre> theatre_adapter = new ArrayAdapter<Theatre>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, theatre_list);
            theatre_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            String urlString = "https://www.finnkino.fi/xml/TheatreAreas/";
            Document doc = builder.parse(urlString);
            doc.getDocumentElement().normalize();
            System.out.println("Root element: "+ doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getDocumentElement().getElementsByTagName("TheatreArea");

            for (int i = 0; i < nList.getLength() ; i++){
                Node node =nList.item(i);


                if (node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;
                    System.out.println(element.getElementsByTagName("Name").item(0).getTextContent() + "\n");
                    theatre_list.add(new Theatre(element.getElementsByTagName("ID").item(0).getTextContent(), element.getElementsByTagName("Name").item(0).getTextContent()));
                }

            }
            tspinner.setAdapter(theatre_adapter);




        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e){
            e.printStackTrace();
        }   finally{
            System.out.println("#########DONE############");
        }


    }
}