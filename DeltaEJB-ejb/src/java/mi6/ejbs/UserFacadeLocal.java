/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mi6.ejbs;

import java.util.List;
import javax.ejb.Local;
import javax.persistence.EntityManager;
import mi6.entity.User;
import mi6.ejbs.exceptions.NoneexistentEntityException.NonexistentEntityException;

/**
 *
 * @author AgtLucas
 */
@Local
public interface UserFacadeLocal {
    
    public EntityManager getEntityManager();
    
    public List<User> findUserEntities();
    
    public List<User> findUserEntities(int maxResults, int firstResult);

    public void create(User user);

    public void edit(User user) throws NonexistentEntityException;

    public void remove(long id) throws NonexistentEntityException;

    User find(Object id);
    

    List<User> findAll();

    List<User> findRange(int[] range);

    int count();
    
}
