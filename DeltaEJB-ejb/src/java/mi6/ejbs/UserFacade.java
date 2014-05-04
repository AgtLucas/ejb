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
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import mi6.ejbs.exceptions.NoneexistentEntityException;
import mi6.entity.User;

/**
 *
 * @author AgtLucas
 */
@Stateless
public class UserFacade /*extends AbstractFacade<User>*/ implements UserFacadeLocal, Serializable {
    @PersistenceContext(unitName = "DeltaEJB-ejbPU")
    
    private EntityManagerFactory emf = null;
//    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

//    public UserFacade() {
//        super(User.class);
//    }

    @Override
    public void create(User user) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("DeltaEJB-ejbPU");
        EntityManager em = factory.createEntityManager();             
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();  
        em.close();
    }

    @Override
    public void edit(User user) throws NoneexistentEntityException.NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            user = em.merge(user);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = user.getId();                
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    @Override
    public void remove(long id) throws NoneexistentEntityException.NonexistentEntityException {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("DeltaEJB-ejbPU");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        User user = (User) em.createQuery("SELECT o FROM User o WHERE o.id = " + id).getSingleResult();
        em.remove(user);
        em.getTransaction().commit();
        em.close();
    }
    
    @Override
    public List<User> findUserEntities() {
        return findUserEntities(true, -1, -1);
    }

    @Override
    public List<User> findUserEntities(int maxResults, int firstResult) {
        return findUserEntities(false, maxResults, firstResult);
    }

    private List<User> findUserEntities(boolean x, int maxResults, int firstResult) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ObjetoDistribuido2-ejbPU");
        EntityManager em = factory.createEntityManager(); 
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(User.class));
            Query q = em.createQuery(cq);
            if (!x) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public User find(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> findRange(int[] range) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int count() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
