package lt.code.academy.data;

public class Destytojas extends Dalykas{
    String DestytojoId;
    String DestytojoVardas;
    String Slaptazodis;

    public Destytojas() {
    }

    public Destytojas(String destytojoId, String destytojoVardas, String slaptazodis) {
        DestytojoId = destytojoId;
        DestytojoVardas = destytojoVardas;
        Slaptazodis = slaptazodis;
    }

    public String getDestytojoId() {
        return DestytojoId;
    }

    public void setDestytojoId(String destytojoId) {
        DestytojoId = destytojoId;
    }

    public String getDestytojoVardas() {
        return DestytojoVardas;
    }

    public void setDestytojoVardas(String destytojoVardas) {
        DestytojoVardas = destytojoVardas;
    }

    public String getSlaptazodis() {
        return Slaptazodis;
    }

    public void setSlaptazodis(String slaptazodis) {
        Slaptazodis = slaptazodis;
    }

    @Override
    public String toString() {
        return "Destytojas{" +
                "DestytojoId=" + DestytojoId +
                ", DestytojoVardas='" + DestytojoVardas + '\'' +
                ", Slaptazodis='" + Slaptazodis + '\'' +
                '}';
    }
}
