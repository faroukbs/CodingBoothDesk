/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import static com.itextpdf.text.pdf.PdfName.C;
import java.util.List;

/**
 *
 * @author aicha
 */
public interface ICService<T> {
    void ajouterCommande(T t);
    void modifierCommande(T t);
    public List<T> AfficherCommande(T t);

    void supprimerCommande(int idcommande);
    List<T> recupererCommande();
}