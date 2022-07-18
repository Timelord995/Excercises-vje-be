package hr.java.vjezbe.entitet;

import java.util.ArrayList;

public abstract class ObrazovnaUstanova {
    private String nazivUstanove;
    private ArrayList<Predmet> predmeti;
    private ArrayList<Profesor> profesori;
    private ArrayList<Student> studenti;
    private ArrayList<Ispit> ispiti;

    public ObrazovnaUstanova(String nazivUstanove, ArrayList<Predmet> predmeti, ArrayList<Profesor> profesori, ArrayList<Student> studenti, ArrayList<Ispit> ispiti){
        this.nazivUstanove = nazivUstanove;
        this.predmeti = predmeti;
        this.profesori = profesori;
        this.studenti = studenti;
        this.ispiti = ispiti;
    }

    public String getNazivUstanove(){
        return nazivUstanove;
    }

    public void setNazivUstanove(String nazivUstanove){
        this.nazivUstanove = nazivUstanove;
    }

    public ArrayList<Predmet> getPredmeti(){
        return predmeti;
    }

    public void setPredmeti(ArrayList<Predmet> predmeti){
        this.predmeti = predmeti;
    }

    public ArrayList<Profesor> getProfesori(){
        return profesori;
    }

    public void setProfesori(ArrayList<Profesor> profesori){
        this.profesori = profesori;
    }

    public ArrayList<Student> getStudenti(){
        return studenti;
    }

    public void setStudenti(ArrayList<Student> studenti){
        this.studenti = studenti;
    }

    public ArrayList<Ispit> getIspiti(){
        return ispiti;
    }

    public void setIspiti(ArrayList<Ispit> ispiti){
        this.ispiti = ispiti;
    }

    public abstract Student odrediNajuspjesnijegStudentaNaGodini(Integer godina);
}
