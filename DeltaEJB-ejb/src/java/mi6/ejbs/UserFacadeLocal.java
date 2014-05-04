/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mi6.ejbs;

import java.util.List;
import javax.ejb.Local;
import mi6.ejbs.exceptions.NoneexistentEntityException.NonexistentEntityException;
import mi6.entity.User;

/**
 *
 * @author AgtLucas
 */
@Local
public interface UserFacadeLocal {

    void create(User user);

    void edit(User user) throws NonexistentEntityException;

    void remove(User user) throws NonexistentEntityException;

    User find(Object id);

    List<User> findAll();

    List<User> findRange(int[] range);

    int count();
    
}
