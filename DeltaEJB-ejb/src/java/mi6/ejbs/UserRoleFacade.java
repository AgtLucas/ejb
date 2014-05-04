/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mi6.ejbs;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import mi6.entity.UserRole;
import mi6.ejbs.exceptions.NoneexistentEntityException.NonexistentEntityException;

/**
 *
 * @author AgtLucas
 */
@Stateless
public class UserRoleFacade extends AbstractFacade<UserRole> implements UserRoleFacadeLocal, Serializable {
    @PersistenceContext(unitName = "DeltaEJB-ejbPU")
    
    private EntityManagerFactory emf = null;
//    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public UserRoleFacade() {
        super(UserRole.class);
    }
     
    @Override
    public void create(UserRole userrole) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("DeltaEJB-ejbPU");
        EntityManager em = factory.createEntityManager();             
        em.getTransaction().begin();
        em.persist(userrole);
        em.getTransaction().commit();  
        em.close();
    }
    
//    @Override
//    public void edit(UserRole userrole) throws NonexistentEntityException, Exception {
//        EntityManager em = null;
//        try {
//            em = getEntityManager();
//            em.getTransaction().begin();
//            userrole = em.merge(userrole);
//            em.getTransaction().commit();
//        } catch (Exception ex) {
//            String msg = ex.getLocalizedMessage();
//            if (msg == null || msg.length() == 0) {
//                Long id = userrole.getId();                
//            }
//            throw ex;
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }

    @Override
    public List<UserRole> finUserRoleEntities() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UserRole> findUserRoleEntities(int maxResults, int firstResult) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
