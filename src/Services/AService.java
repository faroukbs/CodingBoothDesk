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
public interface AService <A> {
        public boolean connecter(A a);
        public void modifier(A a);
        public List<A> afficher();
        public void delete(A a);
}
