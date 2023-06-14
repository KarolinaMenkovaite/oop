package lt.code.academy.data;

public class Dalykas {
    String DalykoPavadinimas;

    public Dalykas() {
    }

    public Dalykas(String dalykoPavadinimas) {
        DalykoPavadinimas = dalykoPavadinimas;
    }

    public String getDalykoPavadinimas() {
        return DalykoPavadinimas;
    }

    public void setDalykoPavadinimas(String dalykoPavadinimas) {
        DalykoPavadinimas = dalykoPavadinimas;
    }

    @Override
    public String toString() {
        return "Dalykas{" +
                "DalykoPavadinimas='" + DalykoPavadinimas + '\'' +
                '}';
    }
}
