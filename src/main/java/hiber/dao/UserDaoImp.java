package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

    @Override
    @SuppressWarnings("unchecked")
    public User getUserByCarModelAndSeries(String carModel, String carSeries) {
       TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car where model=:mod and series=:ser")
               .setParameter("mod", carModel)
               .setParameter("ser", carSeries);
       List<Car> cars = query.getResultList();

       if (!cars.isEmpty()) {
           Car findCar = cars.get(0);
           Long car_id = findCar.getId();

           List<User> users = listUsers();

           User findUser = users.stream().filter(user -> user.getId().equals(car_id))
                   .findAny()
                   .orElse(null);
           return findUser;
       }

       return null;
    }
}
