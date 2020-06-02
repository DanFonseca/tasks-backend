package br.ce.wcaquino.taskbackend.utils;

import java.time.LocalDate;

import org.junit.Test;import net.bytebuddy.asm.Advice.Local;

import static org.junit.Assert.*;

public class DateUtilsTest {
	
	@Test
	public void retornaTrueDataFutura () {
		LocalDate date = LocalDate.of(2020, 06, 02);
		assertTrue(DateUtils.isEqualOrFutureDate(date));
	}
	
	@Test
	public void retornaFalseDataPassada () {
		LocalDate date = LocalDate.of(2020, 05, 02);
		assertFalse(DateUtils.isEqualOrFutureDate(date));
	}
	
	@Test
	public void retornaTrueDataAtual () {
		LocalDate date = LocalDate.now();
		assertTrue(DateUtils.isEqualOrFutureDate(date));
	}

}
