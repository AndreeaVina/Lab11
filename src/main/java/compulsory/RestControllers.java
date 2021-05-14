package compulsory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import net.minidev.json.JSONObject;

@RestController
@RequestMapping(value = "/person")
public class RestControllers {

    private final PersonRepository repository;

    @Autowired
    public RestControllers(PersonRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "Hello from Spring Boot!";
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Iterable<Person> allPersons() {
        return repository.findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<String> add(@RequestBody JSONObject person) {
        if (repository.add(person.get("name").toString()) == 1) {
            return new ResponseEntity<String>("Added user with name : \"" + person.get("name").toString() + "\"", HttpStatus.CREATED);
        }
        return new ResponseEntity<String>("unable to register as \"" +
                person.get("name").toString() + "\"", HttpStatus.CONFLICT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody JSONObject object) {

        if (repository.findById(id).isPresent()) {
            Person person = repository.findPersonById(id);
            person.setName(object.get("name").toString());
            repository.save(person);
            return new ResponseEntity<String>("Updated user with name :  \"" + object.get("name").toString() + "\"", HttpStatus.OK);
        }
        return new ResponseEntity<String>("user with id " + id + "could not be found", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable int id) {
        if (repository.findById(id).isPresent()) {
            repository.delete(repository.findPersonById(id));
            return new ResponseEntity<String>("Deleted user with id :   " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>("User with this id :  " + id + " does not exist", HttpStatus.NOT_FOUND);
    }

}
