package compulsory;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
interface PersonRepository extends CrudRepository<Person, Integer> {

    Person findPersonById(int id);
    @Modifying
    @Transactional
    @Query(value = "insert into users(name) VALUES (?1)", nativeQuery = true)
    int add(String name);

}
