package hr.java.vjezbe.entitet;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Student extends Osoba{
    private String ime;
    private String prezime;
    private String jmbag;
    private LocalDate datumRodjenja;

    public Student(String ime, String prezime, String jmbag, LocalDate datumRodjenja){
        super(ime, prezime);
        this.jmbag = jmbag;
        this.datumRodjenja = datumRodjenja;
    }

    public String getJmbag() {
        return jmbag;
    }

    public void setJmbag(String jmbag) {
        this.jmbag = jmbag;
    }

    public LocalDate getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(LocalDate datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }
}
