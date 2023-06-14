package lt.code.academy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.function.Function;

public class DarbasSuFailais {

    ObjectMapper mapper;

    public DarbasSuFailais(ObjectMapper mapper) {
        this.mapper = mapper;
    }
    void IrasytiIFaila (String FailoPavadinimas, Object objektas)
    {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        File file = new File(FailoPavadinimas);
        try {
            if (!file.exists()){
                file.createNewFile();
            }
            mapper.writeValue(file, objektas);
        } catch (IOException e) {
            System.out.printf("Neimanoma sukurti failo: %s: %s%n:", FailoPavadinimas, e.getMessage());
        }
    }
    <T> T SkaitytiFaila (String FailoPavadinimas, Function<File, T> function){
        try{
            File file = new File(FailoPavadinimas);
            T t = function.apply(file);
            return t;
        } catch(Exception e) {
            System.out.printf("Neimanoma perskaityti failo: %s%n ",FailoPavadinimas, e.getMessage());
        }
        return null;
    }
}
