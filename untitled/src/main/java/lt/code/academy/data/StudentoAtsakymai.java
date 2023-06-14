package lt.code.academy.data;

import java.util.Map;

public class StudentoAtsakymai extends Studentas
{
    String EgzaminoId;

    Map <Integer, Integer> Atsakymai;

    public StudentoAtsakymai() {
    }

    public StudentoAtsakymai(String studentoId, String studentoVardas) {
        super(studentoId, studentoVardas);
    }

    public StudentoAtsakymai(String studentoId, String studentoVardas, String egzaminoId, Map<Integer, Integer> atsakymai) {
        super(studentoId, studentoVardas);
        this.EgzaminoId = egzaminoId;
        this.Atsakymai = atsakymai;
    }

    public String getEgzaminoId() {
        return EgzaminoId;
    }

    public void setEgzaminoId(String egzaminoId) {
        EgzaminoId = egzaminoId;
    }

    public Map<Integer, Integer> getAtsakymai() {
        return Atsakymai;
    }

    public void setAtsakymai(Map<Integer, Integer> atsakymai) {
        Atsakymai = atsakymai;
    }
}
