package com.dz.prism.utlis;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.dz.core.common.utils.Reporter;

public class DateUtils {
	
	/**
	 * @author shamilya
	 * @param dateFormat - The format of date which you pass (ex : MM/dd/yyyy)
	 * @param date - The date you want to change the format (ex : 05/25/2021)
	 * @return will return a LocalDate (ex : 2021/25/05)
	 */
	public static LocalDate convertStringToDate(String dateFormat,String date) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
		LocalDate date2 = null;
		
		try {
			date2 = LocalDate.parse(date, formatter);
		} catch (DateTimeParseException e) {
			Reporter.reportStep("Can't parse date : " + date + " with format " + dateFormat, "fail");
			e.printStackTrace();
		}
		
		return date2;
	}
	
	public static List<LocalDate> getDatesBetween(LocalDate startDate, LocalDate endDate) {

		if (startDate != null && endDate != null) {
			long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);
			return IntStream.iterate(0, i -> i + 1).limit(numOfDaysBetween + 1).mapToObj(i -> startDate.plusDays(i))
					.collect(Collectors.toList());
		} else {
			Reporter.reportStep("Check Start Date :" + startDate + " or End Dtae :" + endDate + " is not valid date", "fail");
			return null;
		}
		
	}
	
	public static LocalDate getCurrentDateAsLocalDate(String dateFormat) {
			
			String curDate = PlayWrightUtlis.getCurrentDateWithFormat(dateFormat);
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
			LocalDate date2 = null;
			
			try {
				date2 = LocalDate.parse(curDate, formatter);
			} catch (DateTimeParseException e) {
				e.printStackTrace();
			}
			
			return date2;
	}
	
	public static LocalDate getLastDateOfMonth(String date, String dateFormat) {
		LocalDate localDate = convertStringToDate(dateFormat, date);
		
		int month = localDate.getMonthValue();
		
		int year = localDate.getYear();
		
		int lastDateOfMonth = localDate.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
		
		LocalDate lastDate = LocalDate.of(year, month, lastDateOfMonth);
		
		return lastDate;
	}
	
	public static int getMonth(LocalDate date) {
		return date.getMonthValue();
	}
	
	public static int getYear(LocalDate date) {
		return date.getYear();
	}
	
	public static LocalDate getPreviousDate(String date, String dateFormat) {
		LocalDate localDate = convertStringToDate(dateFormat, date);
		LocalDate previousDate = localDate.minusDays(1);
		
		return previousDate;
	}
	public static LocalDate getNextDate(String date, String dateFormat) {
		LocalDate localDate = convertStringToDate(dateFormat, date);
		LocalDate nextDate = localDate.plusDays(1);
		
		return nextDate;
	}
	
	public static LocalDate getYearStartDate(String date, String dateFormat) {
		LocalDate localDate = convertStringToDate(dateFormat, date);
		int year = localDate.getYear();
		LocalDate startDate = LocalDate.of(year, 1, 1);
		
		return startDate;
	}
	
	public static LocalDate getMonthStartDate(String date, String dateFormat) {
		LocalDate localDate = convertStringToDate(dateFormat, date);
		int year = localDate.getYear();
		int month = localDate.getMonthValue();
		LocalDate startDate = LocalDate.of(year, month, 1);
		
		return startDate;
	}
	
	public static String convertLocalDateToString(LocalDate date, String format) {
		try {
			return date.format(DateTimeFormatter.ofPattern(format)).toString();
		} catch (DateTimeParseException e) {
			Reporter.reportStep(e.getMessage(), "fail");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static String changeDateFormat(String date, String currentFormat, String reqFormat) {
		LocalDate localDate = convertStringToDate(currentFormat, date);
		try {
			return localDate.format(DateTimeFormatter.ofPattern(reqFormat)).toString();
		} catch (DateTimeParseException e) {
			Reporter.reportStep(e.getMessage(), "fail");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static LocalDate getFutureDate(String date, String dateFormat,int daysToAdd) {
		LocalDate localDate = convertStringToDate(dateFormat, date);
		LocalDate futureDate = localDate.plusDays(daysToAdd);
		
		return futureDate;
	}
	
	public static String getMaxDateInList(List<String> dateStrings,String dateFormat) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
		
		// Find the maximum date using Java streams
		String maxDate = dateStrings.stream()
				.max(Comparator.comparing(dateString -> {
					try {
						return LocalDateTime.parse(dateString, formatter);
					} catch (DateTimeParseException e) {
						Reporter.reportStep("DateTimeParseException occured while parsing Date : " + dateString + " with format : " + dateFormat, "fail");
						return null;
					}
				}))
				.orElse(null);

		// Display the maximum date
		System.out.println(maxDate);
		return maxDate;
	}
	
	public static LocalDate getYearLastDate(String date, String dateFormat) {
		LocalDate localDate = convertStringToDate(dateFormat, date);
		int year = localDate.getYear();
		LocalDate startDate = LocalDate.of(year, 12, 31);
		
		return startDate;
	}
	
	public static String getCurrentYear() {
		LocalDate date = LocalDate.now();
		int year = date.getYear();
		String yearStr = Integer.toString(year);
		return yearStr;
	}
	
	public static String getPreviousYear(String currentYear) {
		String curDate = "01/01/" + currentYear;
		LocalDate date = convertStringToDate("dd/MM/yyyy", curDate);
		LocalDate year = date.minusYears(1);
		String yearStr = Integer.toString(year.getYear());
		return yearStr;
	}
	
	public static LocalDate getYearStartDate(LocalDate localDate) {
		int year = localDate.getYear();
		LocalDate startDate = LocalDate.of(year, 1, 1);
		return startDate;
	}
	
	public static LocalDate getYearLastDate(LocalDate localDate) {
		int year = localDate.getYear();
		LocalDate startDate = LocalDate.of(year, 12, 31);
		
		return startDate;
	}
	
	public static LocalDate getPreviousDate(LocalDate localDate) {
		LocalDate previousDate = localDate.minusDays(1);
		
		return previousDate;
	}
	
	public static LocalDate addDaysToLocalDate(LocalDate localDate, int days) {
		LocalDate startDate = localDate.plusDays(days);
		return startDate;
	}
	
	public static boolean isBetween(LocalDate startDate, LocalDate endDate, LocalDate dateToCheck) {
		
		//boolean status = startDate.compareTo(dateToCheck) * dateToCheck.compareTo(endDate) >= 0;
		boolean status = dateToCheck.compareTo(startDate) >= 0 && dateToCheck.compareTo(endDate) <=0;
		return status; 
	}
	
	public static LocalDate minusDaysToLocalDate(LocalDate localDate, int days) {
		LocalDate startDate = localDate.minusDays(days);
		return startDate;
	}
	
	public static LocalDate minusMonthToLocalDate(LocalDate localDate, int month) {
		LocalDate startDate = localDate.minusMonths(month);
		return startDate;
	}
	
	public static LocalDate getMonthStartDate(LocalDate localDate) {
		int year = localDate.getYear();
		int month = localDate.getMonthValue();
		LocalDate startDate = LocalDate.of(year, month, 1);
		
		return startDate;
	}
	
	public static LocalDate getMonthEndDate(LocalDate localDate) {
		
		YearMonth yearMonth = YearMonth.from(localDate);
		LocalDate startDate = yearMonth.atEndOfMonth();
		
		return startDate;
	}
	
	public static LocalDate addWeeksToLocalDate(LocalDate localDate, int weeks) {
		LocalDate startDate = localDate.plusWeeks(weeks);
		return startDate;
	}
	
	public static LocalDate minusWeeksToLocalDate(LocalDate localDate, int weeks) {
		LocalDate startDate = localDate.minusWeeks(weeks);
		return startDate;
	}
	
	public static LocalDate convertStringToDateRevenue(String dateFormat,String date) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
		LocalDate date2 = LocalDate.parse(date, formatter);
		return date2;
	}
	
	public static LocalDate getWeekStartDate(String dateFormat,String date) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
			LocalDate actDate = LocalDate.parse(date, formatter);
			return actDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
		} catch (Exception e) {
			Reporter.reportStep("Exception occured due to : " + e.getMessage(), "fail");
			return null;
		}
	}
	
	public static LocalDate getWeekEndDate(String dateFormat,String date) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
			LocalDate actDate = LocalDate.parse(date, formatter);
			return actDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY));
		} catch (Exception e) {
			Reporter.reportStep("Exception occured due to : " + e.getMessage(), "fail");
			return null;
		}
	}
	
	public static LocalDate getNextWeekStartDate(String dateFormat,String date) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
			LocalDate actDate = LocalDate.parse(date, formatter);
			LocalDate startDate = actDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
			return addWeeksToLocalDate(startDate, 1);
		} catch (Exception e) {
			Reporter.reportStep("Exception occured due to : " + e.getMessage(), "fail");
			return null;
		}
	}
	
	public static LocalDate getNextWeekEndDate(String dateFormat,String date) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
			LocalDate actDate = LocalDate.parse(date, formatter);
			LocalDate endDate = actDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY));
			return addWeeksToLocalDate(endDate, 1);
		} catch (Exception e) {
			Reporter.reportStep("Exception occured due to : " + e.getMessage(), "fail");
			return null;
		}
	}
	
	public static Map<String, LocalDate> getQuarterDates(int quarter, int year) {
		Map<String, LocalDate> result = new HashMap<String, LocalDate>();

		switch (quarter) {
		case 1:
			result.put("start", LocalDate.of(year, 1, 1));
			result.put("end", LocalDate.of(year, 3, 31));
			break;
		case 2:
			result.put("start", LocalDate.of(year, 4, 1));
			result.put("end", LocalDate.of(year, 6, 30));
			break;
		case 3:
			result.put("start", LocalDate.of(year, 7, 1));
			result.put("end", LocalDate.of(year, 9, 30));
			break;
		case 4:
			result.put("start", LocalDate.of(year, 10, 1));
			result.put("end", LocalDate.of(year, 12, 31));
			break;
		default:
			throw new IllegalArgumentException("Quarter must be between 1 and 4.");
		}

		return result;
	}
	 
	/**
	 * Apl to June is consisdered as 1st quarter
	 * @param quarter
	 * @param fiscalYearStart
	 * @return
	 */
	public static Map<String, LocalDate> getFiscalQuarterDates(int quarter, int fiscalYearStart) {
		Map<String, LocalDate> result = new HashMap<>();

		switch (quarter) {
		case 1: 
			result.put("start", LocalDate.of(fiscalYearStart, 4, 1));
			result.put("end", LocalDate.of(fiscalYearStart, 6, 30));
			break;
		case 2:
			result.put("start", LocalDate.of(fiscalYearStart, 7, 1));
			result.put("end", LocalDate.of(fiscalYearStart, 9, 30));
			break;
		case 3:
			result.put("start", LocalDate.of(fiscalYearStart, 10, 1));
			result.put("end", LocalDate.of(fiscalYearStart, 12, 31));
			break;
		case 4:
			result.put("start", LocalDate.of(fiscalYearStart + 1, 1, 1));
			result.put("end", LocalDate.of(fiscalYearStart + 1, 3, 31));
			break;
		default:
			throw new IllegalArgumentException("Quarter must be between 1 and 4.");
		}

		return result;
	}
	
	public static LocalDate addMonthsToLocalDate(LocalDate localDate, int month) {
		LocalDate startDate = localDate.plusMonths(month);
		return startDate;
	}
	
	public static LocalDate getWeekStartDate(LocalDate actDate) {
		try {
			return actDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
		} catch (Exception e) {
			Reporter.reportStep("Exception occured due to : " + e.getMessage(), "fail");
			return null;
		}
	}
	
	public static LocalDate getWeekEndDate(LocalDate actDate) {
		try {
			return actDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY));
		} catch (Exception e) {
			Reporter.reportStep("Exception occured due to : " + e.getMessage(), "fail");
			return null;
		}
	}
	
	public static String minusYearToDate(String date, int years, String dateFormat) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
		LocalDate actDate = LocalDate.parse(date, formatter);
		LocalDate startDate = actDate.minusYears(years);
		
		String reqDate = convertLocalDateToString(startDate, dateFormat);
		
		return reqDate;
	}
}
