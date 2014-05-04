/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mi6.ejbs;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import mi6.ejbs.exceptions.NoneexistentEntityException;
import mi6.entity.Role;

/**
 *
 * @author AgtLucas
 */
@Stateless
public class RoleFacade /*extends AbstractFacade<Role>*/ implements RoleFacadeLocal {
    @PersistenceContext(unitName = "DeltaEJB-ejbPU")
    private EntityManagerFactory emf = null;
//    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

//    public RoleFacade() {
//        super(Role.class);
//    }

    @Override
    public void create(Role role) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("DeltaEJB-ejbPU");
        EntityManager em = factory.createEntityManager();             
        em.getTransaction().begin();
        em.persist(role);
        em.getTransaction().commit();  
        em.close();
    }

    @Override
    public void edit(Role role) throws NoneexistentEntityException.NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            role = em.merge(role);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = role.getId();                
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
        Role role = (Role) em.createQuery("SELECT o FROM Role o WHERE o.id = " + id).getSingleResult();
        em.remove(role);
        em.getTransaction().commit();
        em.close();
    }
    
    @Override
    public List<Role> findRoleEntities() {
        return findRoleEntities(true, -1, -1);
    }

    @Override
    public List<Role> findRoleEntities(int maxResults, int firstResult) {
        return findRoleEntities(false, maxResults, firstResult);
    }

    private List<Role> findRoleEntities(boolean x, int maxResults, int firstResult) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ObjetoDistribuido2-ejbPU");
        EntityManager em = factory.createEntityManager(); 
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Role.class));
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
    public Role find(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Role> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Role> findRange(int[] range) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int count() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
