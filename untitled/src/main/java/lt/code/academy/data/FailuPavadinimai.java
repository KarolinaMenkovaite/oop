package lt.code.academy.data;

public enum FailuPavadinimai
{
    STUDENTS_FILE ("studentu.json"),
    TEACHERS_FILE ("Destytojai.json"),
    JSON_EXTENSION (".json"),
    ANSWERS_FILES_LIST_FILE_EXTENSION ("AtsakymuSarasas.json"),
    GRADES_FILE_EXTENSION ("Ivertinimai.json");

    private final String string;

    FailuPavadinimai(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }
}
