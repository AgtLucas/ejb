/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mi6.ejbs;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mi6.entity.Role;

/**
 *
 * @author AgtLucas
 */
@Stateless
public class RoleFacade /*extends AbstractFacade<Role>*/ implements RoleFacadeLocal {
    @PersistenceContext(unitName = "DeltaEJB-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RoleFacade() {
        super(Role.class);
    }
    
}
