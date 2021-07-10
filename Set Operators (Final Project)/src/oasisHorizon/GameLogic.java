package oasisHorizon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;


public class GameLogic {
	private int questionNumber;
	private int points;
	private int wrongPoints;
	private int skipPoints;
	private int totalPoints;
	private Difficulty difficulty = Difficulty.Easy;
	private String setA, setB, question, userAnswer, correctAnswer;
	private static StringBuilder setAString;
	private static StringBuilder setBString;
	private Logic logic;
	private Boolean isCorrect;
	
	public enum Difficulty{
		Easy, Normal, Hard;
	}
	
	
	public void setDifficulty(Difficulty d) {
		this.difficulty = d;
	}
	
	public Difficulty getDifficulty() {
		return difficulty;
	}
	
	
	public GameLogic() {
		points = 0;
		wrongPoints = 0;
		skipPoints = 0;
		totalPoints = 5;
		questionNumber = 0;
		question = "";
		logic = new Logic();
		setAString = new StringBuilder();
		setBString = new StringBuilder();
	}
	
	
	public void Reset() {
		questionNumber = 0;
		points = 0;
		wrongPoints = 0;
		skipPoints = 0;
	}
	
	public void setTotalPoints(int num) {
		totalPoints = num;
	}
	
	public void setAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
	}
	
	public int getTotalPoints() {
		return totalPoints;
	}
	
	public void nextQuestion() {
		questionNumber++;
		question = randomQuestion();
		if(difficulty == difficulty.Easy) {
			getEasyQuestion();
			setA = setAString.toString();
			setB = setBString.toString();
		}
		else if(difficulty == difficulty.Normal) {
			getNormalQuestion();
			setA = setAString.toString();
			setB = setBString.toString();
		}
		else {
			getHardQuestion();
			setA = setAString.toString();
			setB = setBString.toString();
		}
	}
	
	public String getQuestion() {
		return question;
	}
	
	public String getCorrectSet() {
		return correctAnswer;
	}
	
	public String getSetA() {
		return setA;
	}
	
	public String getSetB() {
		return setB;
	}
	
	public int getQuestionNumber() {
		return questionNumber;
	}
	
	public int getPoints() {
		return points;
	}
	
	public int getWrong() {
		return wrongPoints;
	}
	
	public int getSkip() {
		return skipPoints;
	}
	
	public boolean isCorrect() {
		return isCorrect;
	}
	
	public void skipQuestion() {
		skipPoints++;
	}
	
	public void checkAnswer() {
		
		if(question == "AuB") {
			correctAnswer = logic.computeUnion(setA, setB);
		}
		else if(question == "AnB") {
			correctAnswer = logic.computeIntersection(setA, setB);
		}
		else if(question == "A-B") {
			correctAnswer = logic.computeDifference(setA, setB);
		}
		else if(question == "B-A") {
			correctAnswer = logic.computeDifference(setB, setA);
		}
		correctAnswer = correctAnswer.replaceAll(" ", "");
		userAnswer = userAnswer.replaceAll("[\\[\\], {}]", "");
		
		if(correctAnswer.length() != userAnswer.length()) {
			isCorrect = false;
			wrongPoints++;
			return;
		}
		

	    char[] correctAnswerChar = correctAnswer.toCharArray(); 
	    char[] userAnswerChar = userAnswer.toCharArray(); 
	    isCorrect = true;
	    List<String> answerListNoDupl = new ArrayList<>();
	    List<Boolean> checkingList = new ArrayList<>();
	        
	    // exclusive to 1 character occurrence only
	    for(int i=0; i<userAnswer.length(); i++){
	        Boolean isCorrectAnswerFound = false;
	        for(int j=0; j<correctAnswer.length(); j++){
	        	String tempCorrect = Character.valueOf(correctAnswerChar[j]).toString().toUpperCase();
	        	String tempUserAns = Character.valueOf(userAnswerChar[i]).toString().toUpperCase();
	        	if(tempCorrect.equals(tempUserAns)){
	        		if(!answerListNoDupl.contains(tempCorrect)){
	        			isCorrectAnswerFound = true;
	        			answerListNoDupl.add(tempUserAns);
	        			checkingList.add(true);
	                    } 
	                }
	        	if(j == correctAnswer.length()-1 && !isCorrectAnswerFound){
	        		checkingList.add(false);
	        		isCorrect = false;
	        		}
	        	}
	        }
	        if(isCorrect) {
	        	points++;
	        }
	        else {
	        	wrongPoints++;
	        }
	        return;
	}
	
	private static void getEasyQuestion() {
 		String[] alpha = { "A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"  };
 		String []random={"1","2"};      	
 		       	List<String> list = new ArrayList<String>();
 		       Random numberGenerator = new Random();
 		       int nexnex = numberGenerator.nextInt(26);
 		        int nexnex2 = numberGenerator.nextInt(2);
 		        int nextRandom = numberGenerator.nextInt(26);
 		        Set<Integer> validate = new HashSet<>();
 		        setAString.setLength(0);
 		        setBString.setLength(0);
 		        
 		        validate.add(nextRandom);
 		       setAString.append("{");
 		        for (int i = 0; i < 3; i++) {
 		           
 		            while(validate.contains(nextRandom)) {
 		                nextRandom = numberGenerator.nextInt(26);
 		                nexnex2=numberGenerator.nextInt(2);
 		            }
 		          
 		            validate.add(nextRandom);
 		           setAString.append(alpha[nextRandom]);
 		          if(i < 3-1) {
 		          setAString.append(",");
 		          }
 		            list.add(alpha[nextRandom]);
 		        
 				
 		        }
 		       setAString.append("}");
				
 		      setBString.append("{");
 		        for (int i = 0; i < nexnex2; i++) {
 		           
 		            while(validate.contains(nextRandom)) {
 		                nextRandom = numberGenerator.nextInt(26);
 		            }
 		          
 		            validate.add(nextRandom);
 		           setBString.append(alpha[nextRandom]);
 		           setBString.append(",");
 				
 		        }
 		         setBString.append(getRandomList(list));
 		         setBString.append(",");

 		int howmany=3-nexnex2;
 		 for (int i =1; i <howmany; i++){
 		 	while(validate.contains(nextRandom)) {
 		                nextRandom = numberGenerator.nextInt(26);
 		            }
 		          
 		            validate.add(nextRandom);
 		           setBString.append(alpha[nextRandom]);
 		           if(i < howmany-1) {
 		           setBString.append(",");
 		           }
 		 	
 		 }
 		  setBString.append("}");
 				
 			}

	
 		private static void getNormalQuestion() {
 		String[] alpha = { "A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"  };
 		String []random={"1","2","3"};      	
 		       	List<String> list = new ArrayList<String>();
 		       	List<String> list2 = new ArrayList<String>();
 		       Random numberGenerator = new Random();
 		       int nexnex = numberGenerator.nextInt(26);
 		        int nexnex2 = numberGenerator.nextInt(3);
 		        int nextRandom = numberGenerator.nextInt(26);
 		        Set<Integer> validate = new HashSet<>();
 		       setAString.setLength(0);
		        setBString.setLength(0);
 		        
 		        validate.add(nextRandom);
 		       setAString.append("{");
 		        for (int i = 0; i < 5; i++) {
 		           
 		            while(validate.contains(nextRandom)) {
 		                nextRandom = numberGenerator.nextInt(26);
 		                nexnex2=numberGenerator.nextInt(3);
 		            }
 		          
 		            validate.add(nextRandom);
 		            setAString.append(alpha[nextRandom]);
 		            if(i < 5-1) {
 		            setAString.append(",");
 		            }
 		            	list.add(alpha[nextRandom]);
 		            	list2.add(alpha[nextRandom]);

 		        }
 		       setAString.append("}");
			
 		      setBString.append("{");
 		        for (int i = 0; i < nexnex2; i++) {
 		           
 		            while(validate.contains(nextRandom)) {
 		                nextRandom = numberGenerator.nextInt(26);
 		            }
 		          
 		            validate.add(nextRandom);
 		            setBString.append(alpha[nextRandom]);
 		           setBString.append(",");
 		            
 		        
 				
 		        }
 		         for (int i =1; i <2; i++){
 		        	setBString.append(getRandomList(list));
 		        	setBString.append(",");
 		        	setBString.append(getRandomList(list2));
 		        	setBString.append(",");
 		         }
 		        

 		int howmany=5-nexnex2;
 		 for (int i =2; i <howmany; i++){
 		 	while(validate.contains(nextRandom)) {
 		                nextRandom = numberGenerator.nextInt(26);
 		            }
 		          
 		            validate.add(nextRandom);
 		           setBString.append(alpha[nextRandom]);
		           if(i < howmany-1) {
	 		         setBString.append(",");
	 		           }
 		 }
 		 setBString.append("}");
 		 
 				
 	}
 	
 	private static void getHardQuestion() {
 		String[] alpha = { "A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"  };
 		String []random={"1","2","3","4"};      	
 		       	List<String> list = new ArrayList<String>();
 		       	List<String> list2 = new ArrayList<String>();
 		       	List<String> list3 = new ArrayList<String>();
 		       Random numberGenerator = new Random();
 		       int nexnex = numberGenerator.nextInt(26);
 		        int nexnex2 = numberGenerator.nextInt(4);
 		        int nextRandom = numberGenerator.nextInt(26);
 		        Set<Integer> validate = new HashSet<>();
  		        setAString.setLength(0);
 		        setBString.setLength(0);
 		        
 		        
 		        validate.add(nextRandom);
 		       setAString.append("{");
 		        for (int i = 0; i < 7; i++) {
 		           
 		            while(validate.contains(nextRandom)) {
 		                nextRandom = numberGenerator.nextInt(26);
 		                nexnex2=numberGenerator.nextInt(4);
 		            }
 		          
 		            validate.add(nextRandom);
 		           setAString.append(alpha[nextRandom]);
 		           if(i < 7-1) {
 		          setAString.append(",");
 		           }
 		            	list.add(alpha[nextRandom]);
 		            	list2.add(alpha[nextRandom]);
 		            	list3.add(alpha[nextRandom]);
	
 		        }
 		       setAString.append("}");

 			 setBString.append("{");
 		        for (int i = 0; i < nexnex2; i++) {
 		           
 		            while(validate.contains(nextRandom)) {
 		                nextRandom = numberGenerator.nextInt(26);
 		            }
 		          
 		            validate.add(nextRandom);
 		           setBString.append(alpha[nextRandom]);
 		          setBString.append(",");
 		            
 		        
 				
 		        }
 		         for (int i =1; i <2; i++){
 		        	setBString.append(getRandomList(list));
 		        	setBString.append(",");
 		        	setBString.append(getRandomList(list2));
 		        	setBString.append(",");
 		        	setBString.append(getRandomList(list3));
 		        	setBString.append(",");
 		         }
 		        

 		int howmany=7-nexnex2;
 		 for (int i =3; i <howmany; i++){
 		 	while(validate.contains(nextRandom)) {
 		                nextRandom = numberGenerator.nextInt(26);
 		            }
 		          
 		            validate.add(nextRandom);
 		           setBString.append(alpha[nextRandom]);
 		           if(i < howmany-1) {
 		           setBString.append(",");
 		           }
 		 	
 		 }
 		setBString.append("}");
 				
 		}


	public static String getRandomList(List<String> list) {

	    //Math.random() = greater than or equal to 0.0 and less than 1
            //0-4
		Random random = new Random();
	 int index = random.nextInt(list.size());
	
	    return list.get(index);
	    
	}
	

	public String randomQuestion() {

	      List<String> list = new ArrayList<>(); 
	      	list.add("AuB"); 
	      	list.add("AnB");
	     	list.add("A-B");
	     	list.add("B-A");
	        Random rand = new Random(); 
	        return list.get(rand.nextInt(list.size())); 			
		}
	
}
