package util;

import static org.junit.Assert.*;

import org.junit.Test;

public class GmailTest {

	@Test
	public void testSendmail() {
		Gmail mailer = new Gmail(
				"yourGmailUsername@gmail.com", 
				"yourGmailPassword");
		try {
			mailer.sendmail(
					"yourGmailUser@gmail.com",
					"swu@nait.ca", 
					"DMIT2015 Assignment 3 from Sam Wu",
					"JavaMail API is simple!");
		} catch (Exception e) {
			fail("Error sending email");
		}
	}

}