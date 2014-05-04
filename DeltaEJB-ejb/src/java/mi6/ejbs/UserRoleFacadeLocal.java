/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mi6.ejbs;

import java.util.List;
import javax.ejb.Local;
import javax.persistence.EntityManager;
import mi6.entity.UserRole;
import mi6.ejbs.exceptions.NoneexistentEntityException.NonexistentEntityException;

/**
 *
 * @author AgtLucas
 */
@Local
public interface UserRoleFacadeLocal {
    
    public EntityManager getEntityManager();
    
    public List<UserRole> finUserRoleEntities();
    
    public List<UserRole> findUserRoleEntities(int maxResults, int firstResult);

    public void create(UserRole userRole);

    public void edit(UserRole userRole) throws NonexistentEntityException, Exception;

    public void remove(Long user, Long role) throws NonexistentEntityException;

    UserRole find(Object id);

    List<UserRole> findAll();

    List<UserRole> findRange(int[] range);

    int count();
    
}
