/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Home
 */
public class Evaluation {
     private int  id;
    private double  note;
    private int idevent;
     private Eventl c ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public int getIdevent() {
        return idevent;
    }

    public void setIdevent(int idevent) {
        this.idevent = idevent;
    }

    public Eventl getC() {
        return c;
    }

    public void setC(Eventl c) {
        this.c = c;
    }

    public Evaluation() {
    }

    public Evaluation(int id, double note, Eventl c) {
        this.id = id;
        this.note = note;
        this.c = c;
    }

    public Evaluation(double note, Eventl c) {
        this.note = note;
        this.c = c;
    }

    @Override
    public String toString() {
        return "Evaluation{" + "id=" + id + ", note=" + note + ", idevent=" + idevent + ", c=" + c + '}';
    }
 


    
  

 
}
