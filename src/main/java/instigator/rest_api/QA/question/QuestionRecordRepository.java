package instigator.rest_api.QA.question;

import org.springframework.data.repository.CrudRepository;

// CRUD refers Create, Read, Update, Delete
public interface QuestionRecordRepository extends CrudRepository<QuestionRecord, String> {

}