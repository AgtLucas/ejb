/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mi6.ejbs;

import static com.fasterxml.classmate.types.ResolvedPrimitiveType.all;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import mi6.ejbs.exceptions.NoneexistentEntityException.NonexistentEntityException;
import mi6.entity.UserRole;

/**
 *
 * @author AgtLucas
 */
@Stateless
public class UserRoleFacade implements UserRoleFacadeLocal, Serializable {
    @PersistenceContext(unitName = "DeltaEJB-ejbPU")
    
    private EntityManagerFactory emf = null;
//    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

//    public UserRoleFacade() {
//        super(UserRole.class);
//    }
     
    @Override
    public void create(UserRole userrole) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("DeltaEJB-ejbPU");
        EntityManager em = factory.createEntityManager();             
        em.getTransaction().begin();
        em.persist(userrole);
        em.getTransaction().commit();  
        em.close();
    }
    
    @Override
    public void edit(UserRole userrole) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            userrole = em.merge(userrole);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = userrole.getId();                
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    @Override
    public void remove(Long user, Long role) throws NonexistentEntityException {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("DeltaEJB-ejbPU");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        UserRole userrole = (UserRole) em.createQuery("SELECT o FROM UserRole o WHERE o.userId = " + user + " and o.roleId = " + role).getSingleResult();
        em.remove(userrole);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<UserRole> finUserRoleEntities() {
        return findUserRoleEntities(true, -1, -1);
    }

    @Override
    public List<UserRole> findUserRoleEntities(int maxResults, int firstResult) {
        return findUserRoleEntities(false, maxResults, firstResult);
    }
    
    private List<UserRole> findUserRoleEntities(boolean b, int maxResults, int firstResult) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("DeltaEJB-ejbPU");
        EntityManager em = factory.createEntityManager(); 
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UserRole.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public UserRole find(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UserRole> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UserRole> findRange(int[] range) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int count() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
