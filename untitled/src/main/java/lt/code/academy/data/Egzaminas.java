package lt.code.academy.data;

import java.util.Map;

public class Egzaminas extends Destytojas{

    String EgzaminoId;
    String EgzaminoPavadinimas;
    String EgzaminoData;

    Map <Integer,String> Klausymai;
    Map <Integer, Integer> TeisingiAtsakymai;

    public Egzaminas() {
    }




    public Egzaminas(String destytojoId, String destytojoVardas, String slaptazodis, String egzaminoId, String egzaminoPavadinimas, String egzaminoData, Map<Integer, String> klausymai, Map<Integer, Integer> teisingiAtsakymai) {
        super(destytojoId, destytojoVardas, slaptazodis);
        EgzaminoId = egzaminoId;
        EgzaminoPavadinimas = egzaminoPavadinimas;
        EgzaminoData = egzaminoData;
        Klausymai = klausymai;
        TeisingiAtsakymai = teisingiAtsakymai;
    }

    public Egzaminas(String egzaminoId, String egzaminoPavadinimas, String data, String atsakymas, String teisingasatsakymas) {
    }


    public String getEgzaminoId() {
        return EgzaminoId;
    }

    public void setEgzaminoId(String egzaminoId) {
        EgzaminoId = egzaminoId;
    }

    public String getEgzaminoPavadinimas() {
        return EgzaminoPavadinimas;
    }

    public void setEgzaminoPavadinimas(String egzaminoPavadinimas) {
        EgzaminoPavadinimas = egzaminoPavadinimas;
    }

    public String getEgzaminoData() {
        return EgzaminoData;
    }

    public void setEgzaminoData(String egzaminoData) {
        EgzaminoData = egzaminoData;
    }

    public Map<Integer, String> getKlausymai() {
        return Klausymai;
    }

    public void setKlausymai(Map<Integer, String> klausymai) {
        Klausymai = klausymai;
    }

    public Map<Integer, Integer> getTeisingiAtsakymai() {
        return TeisingiAtsakymai;
    }

    public void setTeisingiAtsakymai(Map<Integer, Integer> teisingiAtsakymai) {
        TeisingiAtsakymai = teisingiAtsakymai;
    }
}
