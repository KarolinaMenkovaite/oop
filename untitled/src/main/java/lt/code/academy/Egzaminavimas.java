package lt.code.academy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.javafaker.Faker;
import lt.code.academy.data.*;

import java.io.File;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Egzaminavimas {

    Scanner scanner;
    Faker faker;
    ObjectMapper mapper;
    DarbasSuFailais darbasSuFailais;
    Random random = new Random();
    public Egzaminavimas(Scanner scanner, Faker faker, ObjectMapper mapper, DarbasSuFailais darbasSuFailais) {
        this.scanner = scanner;
        this.faker = faker;
        this.mapper = mapper;
        this.darbasSuFailais = darbasSuFailais;
    }

    void SukurtiEgzamina(Destytojas destytojas)
    {
        Egzaminas egzaminas = GeneruotiEgzaminoKlausymus(destytojas);
        String failoPavadinimas = egzaminas.getEgzaminoId() + FailuPavadinimai.JSON_EXTENSION;
        darbasSuFailais.IrasytiIFaila(failoPavadinimas, egzaminas);
    }

    Egzaminas GeneruotiEgzaminoKlausymus(Destytojas destytojas)
    {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String egzaminoId = faker.code().ean8();
        String egzaminoPavadinimas = faker.commerce().material();
        String data = gautiData().toString();
        Map <Integer,String> klausymai = new HashMap<>();
        Map <Integer,Integer> atsakymai = new HashMap<>();
        int numeris=1;
        for  (int i = 0; i<=9; i++)
        {
            String klausimas = faker.chuckNorris().fact();//??
            klausymai.put(numeris,klausimas);
            int atsakymas = random.nextInt(1,3);
            atsakymai.put(numeris,atsakymas);
            numeris++;
        }
        System.out.println("Egzaminas sukurtas");
        System.out.println("Egzamino pavadinimas: " + egzaminoPavadinimas);
        System.out.println("Egzamino id: " + egzaminoId);
        return new Egzaminas(egzaminoId, egzaminoPavadinimas, data, klausymai.toString(), atsakymai.toString());
    }

    LocalDate gautiData()
    {
        while (true){
            try {
                System.out.println("Ivveskite egzamino data (Forma yyyy.MM.dd) :");
                String data = scanner.nextLine();
                LocalDate dabartineData = LocalDate.parse(data, DateTimeFormatter.ofPattern("yyyy.MM.dd"));
                return  dabartineData;
            }catch (DateTimeException e){
                System.out.println("Data irasyta blogu formatu, bandykite isnaujo!");
            }
        }
    }

    void LaikytiEgzamina(Studentas studentas)
    {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        System.out.println("Iveskite egzamino id");
        String id = scanner.nextLine();
        String egzaminoFailoPavadinimas = id + FailuPavadinimai.JSON_EXTENSION;
        File egzaminoFailas = new File(egzaminoFailoPavadinimas);
        if(!egzaminoFailas.exists()){
            System.out.println("Tokio egzamino id neegzistuoja");
            return;
        }
        try {
            Egzaminas egzaminas = mapper.readValue(egzaminoFailas, new TypeReference<>(){});
            LocalDate dataDabar = LocalDate.now();
            if(!dataDabar.equals(LocalDate.parse(egzaminas.getEgzaminoData()))){
                System.out.printf("Jus negalite laikyti sio egzamino siandien! Egzamino data: %s%n", egzaminas.getEgzaminoData());
                return;
            }
            String failoPavadinimas = id + studentas.getStudentoId() + FailuPavadinimai.JSON_EXTENSION;
            String atsakymuSarasoFailas = egzaminas.getEgzaminoId() + FailuPavadinimai.ANSWERS_FILES_LIST_FILE_EXTENSION;
            boolean pirmasLaikymas = PatikrintiLaikKarta(failoPavadinimas, atsakymuSarasoFailas);
            if(!pirmasLaikymas){
                return;
            }
            Map <Integer, Integer> studentoAtsakymai = paleistiKlausymus(egzaminas);
            StudentoAtsakymai atsakymai = new StudentoAtsakymai(studentas.getStudentoId(), studentas.getStudentoVardas(), egzaminas.getEgzaminoId(), studentoAtsakymai);
            darbasSuFailais.IrasytiIFaila(failoPavadinimas, atsakymai);
            sukurtiSarasaSuStudentuAts(egzaminas, failoPavadinimas);

        }catch (IOException e) {
            System.out.println("Neimanoma perskaityti egzamino failo: " + e.getMessage());
        }


    }
    void sukurtiSarasaSuStudentuAts(Egzaminas egzaminas, String pavadinimas)
    {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        List <String> failuPavadinimai = new ArrayList<>();
        String fauloPavadinimas = egzaminas.getEgzaminoId() + FailuPavadinimai.ANSWERS_FILES_LIST_FILE_EXTENSION;
        File file = new File(fauloPavadinimas);
        try{
            if(!file.exists()){
                file.createNewFile();
                failuPavadinimai.add(pavadinimas);
                mapper.writeValue(file, failuPavadinimai);
                return;
            }
            failuPavadinimai = mapper.readValue(file, new TypeReference<>(){});
            failuPavadinimai.add(pavadinimas);
        } catch (IOException e) {
            System.out.println("Neimanoma suskurti failo: " + e.getMessage());
        }

    }

    boolean PatikrintiLaikKarta(String failoPavadinimas, String sarasoFailas)
    {
        try {
            File file = new File(sarasoFailas);
            if(file.exists()){
                List<String> atsakymai =  mapper.readValue(file, new TypeReference<>(){});
                for(String atsakymas : atsakymai){
                    if(atsakymai.equals(failoPavadinimas)){
                        System.out.println("Jus negalite laikyti egzamino antra karta!!");
                        return false;
                    }
                }
            }
            {

            }
        }catch (IOException e) {
            System.out.printf("Neimanoma perskaityti %s failo : %s%n", failoPavadinimas, e.getMessage());
        }
        return true;
    }

    Map <Integer,Integer> paleistiKlausymus(Egzaminas egzaminas)
    {
        Map <Integer,String> klausimai = egzaminas.getKlausymai();
        Map <Integer, Integer> atsakymai = new HashMap<>();
        int atsakymas;
        for(Map.Entry<Integer, String> klausimas : klausimai.entrySet()){
            System.out.printf("Klausimo nr. %s%n", klausimas.getKey());
            System.out.println(klausimas.getValue());
            atsakymas = random.nextInt(1,3);
            System.out.println("Atsakymas: " + atsakymas);
            atsakymai.put(klausimas.getKey(),atsakymas);
        }
        System.out.println("Jus vaigete savo egzamina!");
        return atsakymai;
    }
}