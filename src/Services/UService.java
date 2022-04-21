/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;

/**
 *
 * @author GhAlone
 */
public interface UService<U > {
       public boolean connecter(U u);
        public void changer_pass(U u);
        public void profil(U u);
        public void modifier(U u);
        public void inscrir(U u); 
        public List<U> afficher();
}


