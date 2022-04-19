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

    public Evaluation(int idevent) {
        this.idevent = idevent;
    }

    public Evaluation() {
    }

    public Evaluation(int id, double note, int idevent) {
        this.id = id;
        this.note = note;
        this.idevent = idevent;
    }

    public Evaluation(double note, int idevent) {
        this.note = note;
        this.idevent = idevent;
    }

    @Override
    public String toString() {
        return "Evaluation{" + "id=" + id + ", note=" + note + ", idevent=" + idevent + '}';
    }

    public int getIdevent() {
        return idevent;
    }

    public void setIdevent(int idevent) {
        this.idevent = idevent;
    }

  

 
}
