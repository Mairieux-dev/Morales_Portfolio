package oasisHorizon;

import java.util.*;

public class Logic {
	
	private String alphabetString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	private ArrayList<Character> stack, stackCompare, alphabet;
	private StringBuilder stringBuilder;
	
	// Union of two sets
	public String computeUnion(String setA, String setB){
		
		stack = new ArrayList<Character>();
		stringBuilder = new StringBuilder();
		initAlphabet();
		
		for(char element :setA.toCharArray()) {
			if(alphabet.contains(element)) {
				if(stack.contains(element) == false) {
					stack.add(element);
				}
			}
		}
		
		for(char element :setB.toCharArray()) {
			if(alphabet.contains(element)) {
				if(stack.contains(element) == false) {
					stack.add(element);
				}
			}
		}
		
		for(char element : stack) {
			stringBuilder.append(element + " ");
		}
		
		return stringBuilder.toString();
	}
	
	// Union of three sets
	public String computeUnion(String setA, String setB, String setC) {
		
		computeUnion(setA, setB);
		stringBuilder.setLength(0);
		
		for(char element : setC.toCharArray()) {
			if(alphabet.contains(element)) {
				if(stack.contains(element) == false) {
					stack.add(element);
				}
			}
		}
		for(char element : stack) {
			stringBuilder.append(element + " ");
		}
		
		return stringBuilder.toString();
	}
	
	// Intersection of two sets
	public String computeIntersection(String setA, String setB) {
		
		stack = new ArrayList<Character>();
		stackCompare = new ArrayList<Character>();
		stringBuilder = new StringBuilder();
		initAlphabet();
		
		for(char element :setA.toCharArray()) {
			if(alphabet.contains(element)) {
				if(stack.contains(element) == false) {
					stack.add(element);
				}
			}
		}
		
		for(char element : setB.toCharArray()){
			if(alphabet.contains(element)) {
				if(stack.contains(element)) {
					if(stackCompare.contains(element) == false) {
						stackCompare.add(element);
					}
				}
			}
		}
		
		for(char element : stackCompare) {
			stringBuilder.append(element + " ");
		}
		
		return stringBuilder.toString();
	}
	
	// Intersection of three sets
	public String computeIntersection(String setA, String setB, String setC) {
		
		computeIntersection(setA, setB);
		stringBuilder.setLength(0);
		stack.clear();
		
		for(char element : setC.toCharArray()){
			if(alphabet.contains(element)) {
				if(stackCompare.contains(element)) {
					if(stack.contains(element) == false) {	
					stack.add(element);
					}
				}
			}
		}
		
		for(char element : stack) {
			stringBuilder.append(element + " ");
		}
		
		return stringBuilder.toString();
	}
	
	public String computeDifference(String setA, String setB) {
		
		stack = new ArrayList<Character>();
		stackCompare = new ArrayList<Character>();
		stringBuilder = new StringBuilder();
		initAlphabet();
		
		for(char element :setA.toCharArray()) {
			if(alphabet.contains(element)) {
				if(stack.contains(element) == false) {
					stack.add(element);
				}
			}
		}
		
		for(char element : setB.toCharArray()){
			if(alphabet.contains(element)) {
				if(stackCompare.contains(element) == false) {
					stackCompare.add(element);
				}
			}
		}
		
		stack.removeAll(stackCompare);
		for(char element : stack) {
			stringBuilder.append(element + " ");
		}
		
		return stringBuilder.toString();
	}
	
	public String computeCartesian(String setA, String setB) {
		
		stack = new ArrayList<Character>();
		stackCompare = new ArrayList<Character>();
		stringBuilder = new StringBuilder();
		initAlphabet();
		
		for(char element :setA.toCharArray()) {
			if(alphabet.contains(element)) {
				if(stack.contains(element) == false) {
					stack.add(element);
				}
			}
		}
		
		for(char element :setB.toCharArray()) {
			if(alphabet.contains(element)) {
				if(stackCompare.contains(element) == false) {
					stackCompare.add(element);
				}
			}
		}
		
		for(char firstElement :stack) {
			for(char secondElement :stackCompare) {
				stringBuilder.append("("+firstElement+","+secondElement+") ");
			}
		}
		
		return stringBuilder.toString();
	}
	
	// Pushes all the letters of the alphabet into a stack.
	public void initAlphabet() {
		alphabet = new ArrayList<Character>();
		for(char element : alphabetString.toCharArray()) {
			alphabet.add(element);
		}
	}	
}
