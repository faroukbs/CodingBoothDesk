/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author GhAlone
 */
public class Utilisateur {
    private int id ; 
    private String nom ;
    private String prenom ;
    private int num_tel ;
    private String password ;
    private String date_naissance ;
    private String email ;
    private String roles="[\"ROLE_USER\"]";
    private String image ; 
    private int is_verified=1;

    public Utilisateur(int id, String password, String email) {
        this.id = id;
        this.password = password;
        this.email = email;
    }


    public Utilisateur(int id) {
        this.id = id;
    }
/*
    public Utilisateur(int id, String nom, String prenom, int num_tel, String password, String date_naissance, String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.num_tel = num_tel;
        this.password = password;
        this.date_naissance = date_naissance;
        this.email = email;
       // this.roles = roles;
    }
*/


    public Utilisateur(String password, String email, String roles) {
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    public Utilisateur(String nom, String prenom, int num_tel, String password, String email, String image) {
        this.nom = nom;
        this.prenom = prenom;
        this.num_tel = num_tel;
       this.password = password;
        this.email = email;
        this.image = image;
    }

    public Utilisateur(int id, String nom, String prenom, int num_tel, String password, String email, String image) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.num_tel = num_tel;
        this.password = password;
        this.email = email;
        this.image = image;
    }
/*
    /**
     *
     * @param nom
     * @param prenom
     * @param num_tel
     * @param password
     * @param email
     * @param image
     *//*
    public Utilisateur(String nom, String prenom, int num_tel, String password, String email, String image) {
        this.nom = nom;
        this.prenom = prenom;
        this.num_tel = num_tel;
        this.password = password;
       // this.date_naissance = date_naissance;
        this.email = email;
        //this.roles = roles;
        this.image = image;
    }*/

    public Utilisateur(int id, String email, String roles, String password, String nom, String prenom, String date_naissance, int num_tel, String image, int is_verified) {
        this.id = id;
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.num_tel = num_tel;
        this.image = image;
        this.is_verified = is_verified;
       
        
       
      
    }

    public Utilisateur(String text, String text0, String text1, String text2, String hashPassword, String toString) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Utilisateur() {
        
    }

    public Utilisateur(int parseInt, String text, String text0, String text1, String text2, String toString) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Utilisateur(int parseInt, String text, String text0, int parseInt0, String text1, String text2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setName(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(String date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
      public int getisVerified() {
        return is_verified;
    }

    public void setIsVerified(int isVerified) {
        this.is_verified = is_verified;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ",is_verified=" + is_verified + ", num_tel=" + num_tel + ", password=" + password + ", date_naissance=" + date_naissance + ", email=" + email + ", roles=" + roles + ", image=" + image + '}';
    }
    
    
    
}
