import java.util.*;
import java.io.*;

public class Application {
  private static String fileToRead = "";
  private double startTime = 0.0;
  private double endTime = 0.0;
  Scanner input = new Scanner(System.in);

  public Application() {
  }

  public Application(String filePath) {
    setFileToRead(filePath);
  }

  public void setFileToRead(String filePath) {
    String file = filePath;
    char seporator = File.separatorChar;
    if(seporator != '/') {
      String windowsFilePath = file.replace('/','\\');
      filePath = windowsFilePath;
    }
    fileToRead = file;
  }
  public String getFileToRead() {
    return fileToRead;
  }

  public void setStartTime() {
    this.startTime = System.nanoTime();
  }
  public double getStartTime() {
    return startTime;
  }

  public void setEndTime() {
    this.endTime = System.nanoTime();
  }
  public double getEndTime() {
    return endTime;
  }

  public void initialize() {
    welcomeScreen();
    setFileToRead(input.nextLine());
    File file = new File(getFileToRead());
    if(file.exists()){
      setStartTime();
      menu();
    }else{
      System.out.println("\n**File does not exist**");
      System.out.println("1). Enter 'Q' to Quit");
      System.out.println("2). Enter any charachter to re enter file path");
      System.out.print("Enter your selection: ");
      if(input.next().toUpperCase().compareTo("Q") != 0){
        initialize();
      }else{
        System.exit(0);
      }
    }
    input.close();
  }

  public void welcomeScreen(){
    System.out.println("\n*** Welcome to The US Crime Statistical Application***\n");
    System.out.println(" - Please enter the file path to start.");
    System.out.println(" - Please use / as path seperator: Example C:/user/file");
    System.out.print("File: ");
  }

  public void menu() {
    System.out.println("\n");
    System.out.println("******************** Welcome to The US Crime Statistical Application ********************");
    System.out.println("\n\nEnter the number of the question you want answered. Enter 'Q' to quit the program:\n");
    System.out.println("1. What were growth percentages in population growth for each consecutive year from 1994 - 2013?");
    System.out.println("2. What year was the Murder rate the highest?");
    System.out.println("3. What year was the Murder rate the lowest?");
    System.out.println("4. What year was the Robbery rate the highest?");
    System.out.println("5. What year was the Robbery rate the lowest?");
    System.out.println("Q. Quit the program");
    System.out.print("\nEnter your selection: ");
    userSelection();
  }

  public void userSelection() {
    String userInput;
    input = new Scanner(System.in);
    userInput = input.next().toUpperCase();
    statisticSelection(userInput);
    input.close();
  }

  public void optionToQuit() {
    String userInput;
    input = new Scanner(System.in);
    System.out.println("\nEnter 'C' for menu or 'Q' to quit");
    userInput = input.next().toUpperCase();
    if(userInput.compareTo("C") == 0){
      menu();
    }else if (userInput.compareTo("Q") == 0){
      statisticSelection(userInput);
    }else{
      System.out.println("**Invalid selection - Returning to menu**.\n\n\n\n");
      menu();
    }
    input.close();
  }

  public void statisticSelection(String selection){
    boolean askContinue = false;
    CrimeDataObjects statistic = new CrimeDataObjects();
    System.out.println("\n");
    switch(selection){
      case "1":  ArrayList<String> growthRateChart = statistic.populationGrowthRate();
        for(String year : growthRateChart)
          System.out.println(year);
        askContinue = true;
        break;
      case "2": System.out.println(statistic.getMurderRateHigh());
        askContinue = true;
        break;
      case "3": System.out.println(statistic.getMurderRateLow());
        askContinue = true;
        break;
      case "4": System.out.println(statistic.getRobberyRateHigh());
        askContinue = true;
        break;
      case "5": System.out.println(statistic.getMurderRateLow());
        askContinue = true;
        break;
      case "Q": 	setEndTime();
        System.out.println(elsapsedTime());
        System.exit(0);
        break;
      case "C": menu();
        break;
      default: System.out.println("\n**Invalid Entry.\n");
        menu();
        break;
    }
    if(askContinue){
      optionToQuit();
      askContinue = false;
    }
  }

  public String elsapsedTime(){
    double time = (getEndTime() - getStartTime())/1000000000.0;
    String elapsedTime = ("\n***Elapsed Time: " + Math.round(time) + " seconds***\n");
    elapsedTime += "***Thank you for using the US Crime Statistics Program***\n\n";
    return elapsedTime;
  }
}