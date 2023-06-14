package lt.code.academy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.javafaker.Faker;
import lt.code.academy.data.Egzaminas;
import lt.code.academy.data.FailuPavadinimai;
import lt.code.academy.data.Studentas;
import lt.code.academy.data.StudentoAtsakymai;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class Ivertinimas {
    ObjectMapper mapper;
    Scanner scanner;
    Faker faker;
    DarbasSuFailais darbasSuFailais;
    Egzaminavimas egzaminavimas;

    public Ivertinimas(ObjectMapper mapper, Scanner scanner, Faker faker, DarbasSuFailais darbasSuFailais, Egzaminavimas egzaminavimas) {
        this.mapper = mapper;
        this.scanner = scanner;
        this.faker = faker;
        this.darbasSuFailais = darbasSuFailais;
        this.egzaminavimas = egzaminavimas;
    }

    void IvertinimoSistema(Scanner scanner)
    {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        System.out.println("Irasykite egzamino id, kad gautumete ivertinima");
        String egzaminoid = scanner.nextLine();
        String egzaminoFailoPav = egzaminoid + FailuPavadinimai.JSON_EXTENSION;
        try {
            File file = new File(egzaminoFailoPav);
            if(!file.exists())
            {
                System.out.println("Egzaminas neegzistuoja!");
                return;
            }
            Egzaminas egzaminas = mapper.readValue(file, new TypeReference<>(){});
            LocalDate dabar = LocalDate.now();
            if(dabar.isBefore(LocalDate.parse(egzaminas.getEgzaminoData()))){
                System.out.println("Dar per anksti atlikti egzamina. Egzamino data: " + egzaminas.getEgzaminoData());
                return;
            }
            String atsakymuSarasoFailuPav = egzaminas.getEgzaminoId() + FailuPavadinimai.ANSWERS_FILES_LIST_FILE_EXTENSION;
            StudentuIvertinimas(atsakymuSarasoFailuPav, egzaminas);
        } catch (IOException e) {
            System.out.println("Neimanoma perskaityti egzamino failo: " + e.getMessage());

        }
    }
    private void StudentuIvertinimas (String atsfailai, Egzaminas egzaminas)
    {
        try{
            List<String> failuPav = skaitytiAtsFaila(atsfailai);
            if(failuPav.size()==0){
                System.out.println("Nei vienas studentas nelaike sio egzamino");
                return;
            }
            Map < Studentas, Integer> ivertinimai = new HashMap<>();
            for(String failoPav : failuPav){
                File file = new File(failoPav);
                StudentoAtsakymai studentoAtsakymai = mapper.readValue(file, new TypeReference<>(){});
                Map <Integer,Integer> studentoatsakymai1 = studentoAtsakymai.getAtsakymai();
                Map <Integer,Integer> teisingiats = egzaminas.getTeisingiAtsakymai();
                int teisinguAtsRezultatai = skaiciuotiTeisingusAts(studentoatsakymai1, teisingiats);
                int UzdaviniuSkaicius = teisingiats.size();
                int ivertinimas = teisinguAtsRezultatai * 10 / UzdaviniuSkaicius;
                System.out.printf("Studento %s ivertinimas yra: %s%n", studentoAtsakymai.getStudentoVardas(), ivertinimas);
                Studentas studentas = new Studentas(studentoAtsakymai.getStudentoId(), studentoAtsakymai.getStudentoVardas(), studentoAtsakymai.getSlaptazodis());
                ivertinimai.put(studentas, ivertinimas);
            }

            String egazminoIvertinimuFailas = egzaminas.getEgzaminoId()+FailuPavadinimai.GRADES_FILE_EXTENSION;
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            darbasSuFailais.IrasytiIFaila(egazminoIvertinimuFailas, ivertinimai);

        }catch (IOException e){
            System.out.println("Neimanoma perskaityti failo: " + e.getMessage());
        }
    }
    public int skaiciuotiTeisingusAts(Map<Integer,Integer> studentoats, Map<Integer,Integer>teisingiAts)
    {
        int teisinguAts=0;
        for(Map.Entry<Integer,Integer> atsakymas : teisingiAts.entrySet()){
            int teisingasAts = atsakymas.getValue();
            int studentoDuotasAts = studentoats.get(atsakymas.getKey());
            if(teisingasAts==studentoDuotasAts){
                teisinguAts++;
            }
        }return teisinguAts;
    }
    List skaitytiAtsFaila(String atsfailai)
    {
        List <String> failuPav = new ArrayList<>();
        try {
            File file = new File(atsfailai);
            if(!file.exists()){
                System.out.println("Failo su tokiu pavadinimu nera: " + atsfailai);
                return failuPav;
            }
            failuPav = mapper.readValue(file, new TypeReference<>() {});
            return failuPav;
        }catch (IOException e){
            System.out.println("Neimanoma perksiatyti atsakymu sarasa: " + e.getMessage());
        }return failuPav;
    }
}