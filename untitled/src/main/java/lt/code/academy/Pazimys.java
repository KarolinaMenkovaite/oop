package lt.code.academy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lt.code.academy.data.FailuPavadinimai;
import lt.code.academy.data.Studentas;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Pazimys {

    private final ObjectMapper mapper;
    private final Scanner scanner;

    public Pazimys(ObjectMapper mapper, Scanner scanner) {
        this.mapper = mapper;
        this.scanner = scanner;
    }
    void rodytiIvertinimusDestytojui(){
        Map< Studentas,Integer> ivertinimai = gautiIvertinimus();

    }
    private Map <Studentas,Integer> gautiIvertinimus()
    {
        Map <Studentas,Integer> ivertinimai;
        System.out.println("Iveskite egzamino id");
        String egzaminoId = scanner.nextLine();
        String egzaminoIvertinimuFailas = egzaminoId + FailuPavadinimai.GRADES_FILE_EXTENSION;
        File file = new File(egzaminoIvertinimuFailas);
        if(!file.exists()){
            return null;
        }
        try{
            ivertinimai = mapper.readValue(file, new TypeReference<>(){});
            return ivertinimai;
        }catch (IOException e){
            System.out.println("Neimanoma perskaityti failo: "+ e.getMessage());
            return null;
        }
    }
    void rodytiIvertinimaStudentui (Studentas studentas)
    {
        Map <Studentas,Integer> ivertinimas = gautiIvertinimus();
        if(ivertinimas==null)
        {
            System.out.println("Nera jokiu egzaminu");
            return;
        }
        for(Map.Entry<Studentas,Integer> ivertinimai : ivertinimas.entrySet())
        {
            if(ivertinimai.getKey().getStudentoId().equals(studentas.getStudentoId()));
            return;
        }
        System.out.println("Jus nelaiket jokiu egzaminu");
    }
}
