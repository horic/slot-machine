package com.paypal.billsafe.dojos;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AppTest {

	@Test
	public void testApp() {

		RandomWordGenerator mock = mock(RandomWordGenerator.class);

		when(mock.generateWord()).thenReturn("ACCIDENT", "INCIDENT", "KUKIDENT");

		assertEquals("ACCIDENT", mock.generateWord());
		assertEquals("INCIDENT", mock.generateWord());
		assertEquals("KUKIDENT", mock.generateWord());
		
		mock = mock(RandomWordGenerator.class);
		mock.generateWord();
		
		verify(mock).generateWord();
		
	}

}
