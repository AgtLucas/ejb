/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mi6.ejbs;

import java.util.List;
import javax.ejb.Local;
import mi6.ejbs.exceptions.NoneexistentEntityException.NonexistentEntityException;
import mi6.entity.Role;

/**
 *
 * @author AgtLucas
 */
@Local
public interface RoleFacadeLocal {

    void create(Role role);

    void edit(Role role) throws NonexistentEntityException;

    void remove(Role role) throws NonexistentEntityException;

    Role find(Object id);

    List<Role> findAll();

    List<Role> findRange(int[] range);

    int count();
    
}
