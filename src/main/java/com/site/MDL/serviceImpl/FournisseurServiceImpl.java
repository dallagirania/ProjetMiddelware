package com.site.MDL.serviceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.site.MDL.model.Fourniseur;

@Service
public class FournisseurServiceImpl extends RouteBuilder {
    @Autowired
    DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void configure() throws Exception {

        //Insert Route
        from("direct:insert1").process(new Processor() {
            public void process(Exchange xchg) throws Exception {
                //Take the Fournisseur object from the exchange and create the insert query
                Fourniseur fourniseur = xchg.getIn().getBody(Fourniseur.class);
                String query = "INSERT INTO fourniseur(id,Nom,Matiere,Email,Tel)values('" + fourniseur.getId() + "','"
                        + fourniseur.getNom()+"','"+fourniseur.getMatiere() +"','"+fourniseur.getEmail() +"','" +fourniseur.getTel() + "')";
                // Set the insert query in body and call camel jdbc
                xchg.getIn().setBody(query);
            }
        }).to("jdbc:dataSource");

        // Select Route
        from("direct:select1").setBody(constant("select * from fourniseur")).to("jdbc:dataSource")
                .process(new Processor() {
                    public void process(Exchange xchg) throws Exception {
                        //the camel jdbc select query has been executed. We get the list of Fourniseurs.
                        ArrayList<Map<String, String>> dataList = (ArrayList<Map<String, String>>) xchg.getIn()
                                .getBody();
                        List<Fourniseur> fourniseur = new ArrayList<Fourniseur>();
                        System.out.println(dataList);
                        for (Map<String, String> data : dataList) {
                            Fourniseur fourniseur1 = new Fourniseur();
                            fourniseur1.setId(data.get("Id"));
                            fourniseur1.setNom(data.get("Nom"));
                            fourniseur1.setMatiere(data.get("Matiere"));
                            fourniseur1.setEmail(data.get("Email"));
                            fourniseur1.setTel(data.get("Tel"));
                            fourniseur.add(fourniseur1);
                        }
                        xchg.getIn().setBody(fourniseur);
                    }
                });


    }
}

