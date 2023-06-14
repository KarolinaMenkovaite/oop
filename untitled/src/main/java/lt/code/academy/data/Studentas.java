package lt.code.academy.data;

public class Studentas {
    String StudentoId;
    String StudentoVardas;
    String Slaptazodis;

    public Studentas() {
    }
//------------//


    public Studentas(String studentoId, String studentoVardas, String slaptazodis) {
        StudentoId = studentoId;
        StudentoVardas = studentoVardas;
        Slaptazodis = slaptazodis;
    }

    public Studentas(String studentoId, String studentoVardas) {
        StudentoId = studentoId;
        StudentoVardas = studentoVardas;
    }

    public String getStudentoId() {
        return StudentoId;
    }

    public void setStudentoId(String studentoId) {
        StudentoId = studentoId;
    }

    public String getStudentoVardas() {
        return StudentoVardas;
    }

    public void setStudentoVardas(String studentoVardas) {
        StudentoVardas = studentoVardas;
    }

    public String getSlaptazodis() {
        return Slaptazodis;
    }

    public void setSlaptazodis(String slaptazodis) {
        Slaptazodis = slaptazodis;
    }

    @Override
    public String toString() {
        return "Studentas{" +
                "StudentoId='" + StudentoId + '\'' +
                ", StudentoVardas='" + StudentoVardas + '\'' +
                ", Slaptazodis='" + Slaptazodis + '\'' +
                '}';
    }
}
