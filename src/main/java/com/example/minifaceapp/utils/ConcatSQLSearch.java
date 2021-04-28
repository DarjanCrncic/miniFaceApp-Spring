package com.example.minifaceapp.utils;

import java.util.ArrayList;

import com.example.minifaceapp.dto.SearchDTO;

public class ConcatSQLSearch {

	public static String concatenate(String originalQuery, SearchDTO searchDTO, String[] caseAll) {
		
		String wordPosition = searchDTO.getWordPosition();
		ArrayList<String> words = searchDTO.getSearchWords();
		ArrayList<String> filters = searchDTO.getSearchParams();
		String logicalOperand = searchDTO.getLogicalOperand();

		StringBuilder str = new StringBuilder();

		str.append("SELECT * FROM( ");
		str.append(originalQuery);
		str.append(") WHERE ");

		for (int i = 0; i < filters.size(); i++) {
			if (filters.get(i).toString().equals("all")) {
				str.append("(");
				for (int j = 0; j < caseAll.length; j++) {
					if (wordPosition.equals("back")) {
						str.append("UPPER(" + caseAll[j] + ") LIKE '%" + words.get(i).toString().toUpperCase() + "' ");
					} else if (wordPosition.equals("front")) {
						str.append("UPPER(" + caseAll[j] + ") LIKE '" + words.get(i).toString().toUpperCase() + "%' ");
					} else {
						str.append("UPPER(" + caseAll[j] + ") LIKE '%" + words.get(i).toString().toUpperCase() + "%' ");
					}
					if (j < caseAll.length - 1) {
						str.append("OR ");
					}
				}
				str.append(")");
			} else {
				if (wordPosition.equals("back")) {
					str.append("UPPER(" + filters.get(i).toString() + ") LIKE '%" + words.get(i).toString().toUpperCase() + "' ");
				} else if (wordPosition.equals("front")) {
					str.append("UPPER(" + filters.get(i).toString() + ") LIKE '" + words.get(i).toString().toUpperCase() + "%' ");
				} else {
					str.append("UPPER(" + filters.get(i).toString() + ") LIKE '%" + words.get(i).toString().toUpperCase() + "%' ");
				}
			}

			if (i < filters.size() - 1) {
				str.append(logicalOperand.toUpperCase() + " ");
			}
		}

		return str.toString();
	}
	
	public static String createSQLQueryAddition(String filter, SearchDTO searchDTO, String[] caseAll) {
		
		String wordPosition = searchDTO.getWordPosition();
		ArrayList<String> words = searchDTO.getSearchWords();
		ArrayList<String> filters = searchDTO.getSearchParams();
		String logicalOperand = searchDTO.getLogicalOperand();
		
		StringBuilder str = new StringBuilder();
		str.append(filter + " (");
		for (int i = 0; i < filters.size(); i++) {
			if(filters.get(i).toString().contains("+")) {
				filters.add(i, "(" + filters.get(i).toString().replaceAll("\\+", " || ' ' || ") + ")");
			}
			if (filters.get(i).toString().equals("all")) {
				str.append("(");
				for (int j = 0; j < caseAll.length; j++) {
					if (wordPosition.equals("back")) {
						str.append("UPPER(" + caseAll[j] + ") LIKE '%" + words.get(i).toString().toUpperCase() + "' ");
					} else if (wordPosition.equals("front")) {
						str.append("UPPER(" + caseAll[j] + ") LIKE '" + words.get(i).toString().toUpperCase() + "%' ");
					} else {
						str.append("UPPER(" + caseAll[j] + ") LIKE '%" + words.get(i).toString().toUpperCase() + "%' ");
					}
					if (j < caseAll.length - 1) {
						str.append("OR ");
					}
				}
				str.append(")");
			} else {
				if (wordPosition.equals("back")) {
					str.append("UPPER(" + filters.get(i).toString() + ") LIKE '%" + words.get(i).toString().toUpperCase() + "' ");
				} else if (wordPosition.equals("front")) {
					str.append("UPPER(" + filters.get(i).toString() + ") LIKE '" + words.get(i).toString().toUpperCase() + "%' ");
				} else {
					str.append("UPPER(" + filters.get(i).toString() + ") LIKE '%" + words.get(i).toString().toUpperCase() + "%' ");
				}
			}

			if (i < filters.size() - 1) {
				str.append(logicalOperand.toUpperCase() + " ");
			}
		}
		str.append(")");
		return str.toString();
	}

}
