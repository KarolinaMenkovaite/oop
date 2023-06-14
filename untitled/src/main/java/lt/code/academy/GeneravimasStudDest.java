package lt.code.academy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import lt.code.academy.data.Destytojas;
import lt.code.academy.data.FailuPavadinimai;
import lt.code.academy.data.Studentas;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GeneravimasStudDest {
    ObjectMapper mapper;
    Faker faker;
    DarbasSuFailais DarbasSuFailais;

    public GeneravimasStudDest(ObjectMapper mapper, Faker faker, DarbasSuFailais failuIrasimas) {
        this.mapper = mapper;
        this.faker = faker;
        DarbasSuFailais = failuIrasimas;
    }

    void GeneruotiStudentus()
    {
        Map<String, Studentas> studentai = new HashMap<>();
        int Numeris = 1;
        for(int i=0; i<=19; i++){
            String id = String.valueOf(Numeris);
            String vardas = faker.name().fullName();
            String slaptazodis = "1235";
            studentai.put(id, new Studentas(id,vardas,slaptazodis));
            //System.out.println(id + " " + vardas + " " + slaptazodis );
            Numeris++;
        }
        DarbasSuFailais.IrasytiIFaila(FailuPavadinimai.STUDENTS_FILE.toString(), studentai);//?
    }
    void GeneruotiDestytojus()
    {
        Map <String, Destytojas> destytojai = new HashMap<>();
        int Numeris = 1;
        for (int i = 0; i<=2; i++){
            String dalykas = faker.artist().name();
            String vardas = faker.name().fullName();
            String slaptazodis = "1235";
            String id = String.valueOf(Numeris);
            destytojai.put(id, new Destytojas(id,vardas, slaptazodis));
            System.out.println(id + " " + vardas + " " + slaptazodis );
            Numeris++;
        }
        DarbasSuFailais.IrasytiIFaila(FailuPavadinimai.TEACHERS_FILE.toString(), destytojai);
    }
    Map <String,Studentas> NuskaitytiStudentus() {
        try {
            java.io.File file = new File(FailuPavadinimai.STUDENTS_FILE.toString());
            Map <String, Studentas> studentai = mapper.readValue(file, new TypeReference<>() {
            });
            return studentai;
        }catch (IOException e)
        {
            System.out.printf("Neimanoma perskaityti %s failo: %s%n", FailuPavadinimai.STUDENTS_FILE.toString(), e.getMessage());
        }
        return null;
    }
    Map <String,Destytojas> NuskaitytiDestytojus() {
        try {
            java.io.File file = new File(FailuPavadinimai.TEACHERS_FILE.toString());
            Map <String, Destytojas> destytojai = mapper.readValue(file, new TypeReference<>() {
            });
            return destytojai;
        }catch (IOException e)
        {
            System.out.printf("Neimanoma perskaityti %s failo: %s%n", FailuPavadinimai.TEACHERS_FILE.toString(), e.getMessage());
        }
        return null;
    }
}
