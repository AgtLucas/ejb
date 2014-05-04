/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mi6.ejbs;

import java.util.List;
import javax.ejb.Local;
import javax.persistence.EntityManager;
import mi6.entity.Role;
import mi6.ejbs.exceptions.NoneexistentEntityException.NonexistentEntityException;

/**
 *
 * @author AgtLucas
 */
@Local
public interface RoleFacadeLocal {
    
    public EntityManager getEntityManager();
    
    public List<Role> findRoleEntities();
    
    public List<Role> findRoleEntities(int maxResults, int firstResult);

    public void create(Role role);

    public void edit(Role role) throws NonexistentEntityException;

    public void remove(long id) throws NonexistentEntityException;

    Role find(Object id);

    List<Role> findAll();

    List<Role> findRange(int[] range);

    int count();
    
}
