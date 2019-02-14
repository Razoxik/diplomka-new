/**package cz.upce.diplomovaprace.repository;

import cz.upce.diplomovaprace.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class StudentRepositoryImpl implements StudentRepositoryCustom {
  public class StudentRepositoryImpl  {

    @PersistenceContext
    private EntityManager em;

     @Override
      public List findByName(String name) {
          return null;
     }

    @Override
    public List<Student> getNameLike(String firstName) {
        Query query = em.createNativeQuery("SELECT em.* FROM Student as em " +
                "WHERE em.name LIKE ?", Student.class);
        query.setParameter(1, firstName + "%");
        return query.getResultList();
    }

    // @Override
    //  public List findByName(Long id, String name, String address) {
    //      return  new ArrayList();
        //     return em.createQuery(
        //            "select c FROM cz.upce.diplomovaprace.entity.Student c WHERE c.name LIKE ?1")
        // //              .setParameter(1, name)
        //           .getResultList();
    // }
    //public List findWithName(String name) {
    //    return em.createQuery(
    //          “SELECT c FROM Customer c WHERE c.name LIKE ?1”)
    //          .setParameter(1, name)
    //          .getResultList();
    //}
}
*/