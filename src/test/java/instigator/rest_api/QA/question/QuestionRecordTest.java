package instigator.rest_api.QA.question;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class QuestionRecordTest {

	private String UUID = "uuid001";
	private QuestionRecord questionRecord1;
	private QuestionRecord questionRecord2;
	
	@Before
	public void setUp() throws Exception {
		questionRecord1 = new QuestionRecord();
		questionRecord2 = new QuestionRecord(UUID);
	}

	@Test
	public void testConstructor() {
		assertNotNull(questionRecord1);
		assertNotNull(questionRecord2);
	}
	@Test
	public void testGetUuid() {
		String actual1 = questionRecord1.getUuid();
		String expected1 = "aaa";
		assertEquals("Testing getUuid for questionRecord1", expected1, actual1);
		
		String actual2 = questionRecord2.getUuid();
		String expected2 = "uuid001";
		assertEquals("Testing getUuid for questionRecord2", expected2, actual2);
	}
}
