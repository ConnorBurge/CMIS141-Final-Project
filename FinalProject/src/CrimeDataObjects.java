import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class CrimeDataObjects {

  private int year, murderNonManslaughter, rape, robbery, aggravatedAssault,
          propertyCrime, burglary, larceny, motorVehicleTheft;
  private float violentCrimeRate, murderNonManslaughterRate, rapeRate, robberyRate, aggravatedAssaultRate,
          propertyCrimeRate, burglaryRate, larcenyRate, motorVehcileTheftRate;
  private double population;
  private long violentCrime;
  private static BufferedReader fileIn;
  private static List<CrimeDataObjects> dataRecords;
  private static List<CrimeDataObjects> recordList = new ArrayList<CrimeDataObjects>();
  static {
    CrimeDataObjects.readData();
    recordList = CrimeDataObjects.getDataRecords();
  }

  public CrimeDataObjects() {
  }

  public CrimeDataObjects(int year, double population, long violentCrime, float violentCrimeRate,
                          int murderNonNegMan, float murderNonNegManRate, int rape,
                          float rapeRate, int robbery, float robberyRate, int aggAssault,
                          float aggAssaultRate, int propCrime, float propCrimeRate, int burglery,
                          float burglaryRate, int larceny, float larcenyRate, int mvTheft, float mvTheftRate) {
    this.year = year;
    this.population = population;
    this.violentCrime = violentCrime;
    this.violentCrimeRate = violentCrimeRate;
    this.murderNonManslaughter = murderNonNegMan;
    this.murderNonManslaughterRate = murderNonNegManRate;
    this.rape = rape;
    this.rapeRate = rapeRate;
    this.robbery = robbery;
    this.robberyRate = robberyRate;
    this.aggravatedAssault = aggAssault;
    this.aggravatedAssaultRate = aggAssaultRate;
    this.propertyCrime = propCrime;
    this.propertyCrimeRate = propCrimeRate;
    this.burglary = burglery;
    this.burglaryRate = burglaryRate;
    this.larceny = larceny;
    this.larcenyRate = larcenyRate;
    this.motorVehicleTheft = mvTheft;
    this.motorVehcileTheftRate = mvTheftRate;
  }
  public int getYear() {
    return  year;
  }
  public double getPopulation() {
    return population;
  }
  public float getMurderNonManslaughterRate() {
    return murderNonManslaughterRate;
  }
  public float getRobberyRate() {
    return robberyRate;
  }

  public static void readData() {
    String lineIn = null;
    Application userFile;
    try {
      userFile = new Application();
      fileIn = new BufferedReader(new FileReader(userFile.getFileToRead()));
    } catch (Exception e) {
      e.getMessage();
      System.out.println("File not found!");
    }
    dataRecords = new ArrayList<CrimeDataObjects>();
    try {
      fileIn.readLine();
      lineIn = fileIn.readLine();
      while(lineIn != null) {
        // System.out.println(lineIn);
        String[] parsedData = lineIn.split(",");
        buildCrimeDataObjects(parsedData);
        lineIn = fileIn.readLine();
      }
    } catch (Exception e) {
      e.getMessage();
    }
  }

  public static void buildCrimeDataObjects(String[] parsedData) {
    String[] data = parsedData;
    CrimeDataObjects csvRecord;
    int year = Integer.parseInt(data[0]);
    long population = Long.parseLong(data[1]);
    long violentCrime = Long.parseLong(data[2]);
    float violentCrimeRate = Float.parseFloat(data[3]);
    int murderNonNegManslaughter = Integer.parseInt(data[4]);
    float murderNonNegManslaughterRate = Float.parseFloat(data[5]);
    int rape = Integer.parseInt(data[6]);
    float rapeRate = Float.parseFloat(data[7]);
    int robbery = Integer.parseInt(data[8]);
    float robberyRate = Float.parseFloat(data[9]);
    int aggravatedAssault = Integer.parseInt(data[10]);
    float aggravatedAssaultRate = Float.parseFloat(data[11]);
    int propertyCrime = Integer.parseInt(data[12]);
    float propertyCrimeRate = Float.parseFloat(data[13]);
    int burglary = Integer.parseInt(data[14]);
    float burglaryRate = Float.parseFloat(data[15]);
    int larceny = Integer.parseInt(data[16]);
    float larcenyRate = Float.parseFloat(data[17]);
    int motorVehicleTheft = Integer.parseInt(data[18]);
    float motorVehicleTheftRate = Float.parseFloat(data[19]);

    csvRecord = new CrimeDataObjects(year,population,violentCrime,violentCrimeRate,murderNonNegManslaughter,
                                     murderNonNegManslaughterRate,rape,rapeRate,robbery,robberyRate,
                                     aggravatedAssault,aggravatedAssaultRate,propertyCrime,propertyCrimeRate,
                                     burglary,burglaryRate,larceny,larcenyRate,motorVehicleTheft,motorVehicleTheftRate);
    dataRecords.add(csvRecord);
  }

  public static List<CrimeDataObjects> getDataRecords() {
    return dataRecords;
  }

  public ArrayList<String> populationGrowthRate() {
    String tempYears;
    String yearToYearGrowth = "";
    double tempGrowth;
    ArrayList<String> growthRateChart = new ArrayList<String>();
    growthRateChart.add("**** Year to Year ****");
    growthRateChart.add("**** Growth Rates ****");
    growthRateChart.add("----------------------");
     for (int i = 0; i < recordList.size()-1; i++){
       tempYears = Integer.toString(recordList.get(i).getYear());
       tempYears += " - ";
       tempYears += Integer.toString(recordList.get(i+1).getYear());
       tempGrowth = (((recordList.get(i+1).getPopulation() -
               recordList.get(i).getPopulation()) /
               (recordList).get(i).getPopulation()))*100;
      growthRateChart.add(yearToYearGrowth);
    }
    return growthRateChart;
  }

  public String getMurderRateHigh() {
     int year = 0;
     float highestRate = 0;
     String murderRateHigh = "\n****Highest Murder Rate****" +
             "\n---------------------------\n" +
             "Year: ";
     year = recordList.get(0).getYear();
     highestRate = recordList.get(0).getMurderNonManslaughterRate();
     for (int i = 1; i < recordList.size(); i++) {
       if (highestRate < recordList.get(i).getMurderNonManslaughterRate()) {
         year = recordList.get(i).getYear();
         highestRate = recordList.get(i).getMurderNonManslaughterRate();
       }
     }
     murderRateHigh += Integer.toString(year);
     murderRateHigh += "  Murder Rate:";
     murderRateHigh += Float.toString(highestRate);
     return murderRateHigh;
  }

  public String getMurderRateLow() {
    int year = 0;
    float lowestRate = 0;
     String murderRateLow = "\n****Lowest Murder Rate****" +
                            "\n--------------------------\n" +
                            "Year: ";
     year = recordList.get(0).getYear();
     lowestRate = recordList.get(0).getMurderNonManslaughterRate();
     for( int i = 1; i < recordList.size(); i++ ){
       if(lowestRate < recordList.get(i).getMurderNonManslaughterRate() ){
         year = recordList.get(i).getYear();
         lowestRate = recordList.get(i).getMurderNonManslaughterRate();
       }
     }
     murderRateLow += Integer.toString(year);
     murderRateLow += "  Murder Rate:";
     murderRateLow += Float.toString(lowestRate);
     return murderRateLow;
  }

  public String getRobberyRateHigh () {
    int year = 0;
    float highestRate = 0;
    String robberyRateHigh = "\n**** Highest Robbery Rate ****" +
                             "\n------------------------------\n";
    year = recordList.get(0).getYear();
    highestRate = recordList.get(0).getRobberyRate();
    for( int i = 1; i < recordList.size(); i++ ){
      if(highestRate < recordList.get(i).getRobberyRate() ){
        year = recordList.get(i).getYear();
        highestRate = recordList.get(i).getRobberyRate();
      }
    }
     robberyRateHigh += Integer.toString(year);
     robberyRateHigh += "  Robbery Rate:";
     robberyRateHigh += Float.toString(highestRate);
     return robberyRateHigh;
  }

  public String getRobberyRateLow () {
    int year = 0;
    float highestRate = 0;
    String robberyRateLow = "\n**** Lowest Robbery Rate ****" +
                            "\n-----------------------------\n";
    year = recordList.get(0).getYear();
    highestRate = recordList.get(0).getRobberyRate();
    for( int i = 1; i < recordList.size(); i++ ){
      if(highestRate > recordList.get(i).getRobberyRate() ){
        year = recordList.get(i).getYear();
        highestRate = recordList.get(i).getRobberyRate();
      }
    }
     robberyRateLow += Integer.toString(year);
     robberyRateLow += "  Robbery Rate:";
     robberyRateLow += Float.toString(highestRate);
     return robberyRateLow;
  }
} // End of class