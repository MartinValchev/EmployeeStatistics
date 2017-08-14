package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.StatisticsService;

public class CharEntity implements Comparable<CharEntity> {
	private char character;
	private int occurreneCount;

	public char getCharacter() {
		return character;
	}

	public void setCharacter(char character) {
		this.character = character;
	}

	public int getOccurreneCount() {
		return occurreneCount;
	}

	public void setOccurreneCount(int occurreneCount) {
		this.occurreneCount = occurreneCount;
	}

	public int compareTo(CharEntity entity) {
		int currentOccurrenceCount = getOccurreneCount();
		int result = 0;
		if (currentOccurrenceCount > entity.getOccurreneCount()) {
			result = 1;
		} else if (currentOccurrenceCount < entity.getOccurreneCount()) {
			result = -1;
		}
		return result;
	}

	@Override
	public boolean equals(Object entityObject) {
		boolean isEqual = false;
		if (entityObject instanceof CharEntity) {
			CharEntity entity = (CharEntity) entityObject;
			if (entity.getCharacter() == this.getCharacter()) {
				isEqual = true;
			}
		}
		return isEqual;

	}
}
