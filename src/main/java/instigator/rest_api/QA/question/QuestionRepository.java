package instigator.rest_api.QA.question;

import org.springframework.data.repository.CrudRepository;

////CRUD refers Create, Read, Update, Delete
public interface QuestionRepository extends CrudRepository<Question, Integer> {

}
