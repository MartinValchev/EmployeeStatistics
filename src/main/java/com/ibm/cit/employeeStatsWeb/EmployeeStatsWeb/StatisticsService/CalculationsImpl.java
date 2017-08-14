package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.StatisticsService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.model.Employee;





public class CalculationsImpl implements Calculations {

	List<Employee> employees;
	StringBuilder builder;

	public CalculationsImpl(List<Employee> employees) {
		this.employees = employees;
		builder = new StringBuilder();
	}

	public double calculateAvgEmpAge() {
		double avgAge = 0;
		double ageSum = 0;
		if (isEmployeeListValid()) {
			for (Employee employee : employees) {
				ageSum += employee.getAge();
			}
			avgAge = ageSum / employees.size();
		}

		return avgAge;
	}

	private boolean isEmployeeListValid() {
		return (employees != null && employees.size() > 0);
	}

	public String getThreeMostCommonChars() {
		StringBuilder builderNew = new StringBuilder();
		String nameOcurrences = getNamesString();
		char[] nameChars = nameOcurrences.toCharArray();
		Arrays.sort(nameChars);
		List<CharEntity> entityList = getCharEntityList(nameChars);
		Collections.sort(entityList);
		char firstCommon = entityList.get(entityList.size() - 1).getCharacter();
		char secondCommon = entityList.get(entityList.size() - 2).getCharacter();
		char thirdCommon = entityList.get(entityList.size() - 3).getCharacter();
		builderNew.append("First Three most common Characters in Empoyee Names: '");
		builderNew.append(firstCommon).append("'-(");
		builderNew.append(entityList.get(entityList.size() - 1).getOccurreneCount());
		builderNew.append(") times , '");
		builderNew.append(secondCommon).append("' -(");
		builderNew.append(entityList.get(entityList.size() - 2).getOccurreneCount());
		builderNew.append(") times").append("', '").append(thirdCommon).append("'-(");
		builderNew.append(entityList.get(entityList.size() - 3).getOccurreneCount());
		builderNew.append(") times");
		return builderNew.toString();
	}

	private List<CharEntity> getCharEntityList(char[] nameChars) {
		List<CharEntity> entityList = new ArrayList<CharEntity>();
		char compareChar = nameChars[0];
		int occurreneCount = 0;
		int indexCounter = 0;
		char currentChar = '~';
		while (indexCounter < nameChars.length) {
			currentChar = nameChars[indexCounter];
			if (currentChar == compareChar) {
				occurreneCount++;
			} else {
				CharEntity charEntity = new CharEntity();
				charEntity.setCharacter(compareChar);
				charEntity.setOccurreneCount(occurreneCount);
				occurreneCount = 1;
				compareChar = currentChar;
				entityList.add(charEntity);
			}
			indexCounter++;
		}
		if (occurreneCount != 1) {
			CharEntity charEntity = new CharEntity();
			charEntity.setCharacter(compareChar);
			charEntity.setOccurreneCount(occurreneCount);
			compareChar = currentChar;
			entityList.add(charEntity);
		}
		return entityList;
	}

	private String getNamesString() {
		if (isEmployeeListValid()) {
			for (Employee employee : employees) {
				builder.append(employee.getEmployeeFirstName()).append(employee.getEmployeeLastName());
			}
		}
		String nameOcurrences = builder.toString().toLowerCase();
		return nameOcurrences;
	}

	public double calculateAvgLenghtOfService() {
		double avgSurviceLenght = 0;
		double surviceLengthSum = 0;
		if (isEmployeeListValid()) {
			for (Employee employee : employees) {
				surviceLengthSum += employee.getLengthOfService();
			}
			avgSurviceLenght = surviceLengthSum / employees.size();
		}
		return avgSurviceLenght;

	}

	public double findMaxLengthOfService() {
		double maxServiceLenght = 0;
		double currentServiceLenght = 0;
		if (isEmployeeListValid()) {
			maxServiceLenght = employees.get(0).getLengthOfService();
			for (Employee employee : employees) {
				currentServiceLenght = employee.getLengthOfService();
				if (currentServiceLenght > maxServiceLenght) {
					maxServiceLenght = currentServiceLenght;
				}
			}
		}
		return maxServiceLenght;
	}

}
