import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

class Players{
    private String player_name,wicketType,status;
    private int total_runs,wickets,fours,sixes;
    public Players(String player_name){
        this.player_name = player_name;
        this.status="NotOut";
        total_runs=0;
        wickets=0;fours=0;
        sixes=0;
    }
    //setter methods
    public void setFours(){
        this.fours = this.fours+1;
    }
    public void setSixes(){
        this.sixes = this.sixes+1;
    }
    public void setWickets(){
        this.wickets = this.wickets+1;
    }
    public void setTotalRuns(int run){
        this.total_runs = run;
    }
    public void setWicketType(String wicketType){
        this.wicketType = wicketType;
        this.status="Out";
    }

    //getter methods
    public int getFours(){
        return this.fours;
    }
    public int getSixes(){
        return this.sixes;
    }
    public int getWickets(){
        return this.wickets;
    }
    public int getTotalRuns(){
        return this.total_runs;
    }
    public String getWicketType(){
        return this.wicketType;
    }
    public String getStatus(){
        return this.status;
    }

}

class Squad{
    String country_name;
    String captain_name;
    ArrayList<String> players_list;
    ArrayList<String> batsman,bowlers,allRounders;
    String wicket_keeper_name;
    public Squad(String country_name,String captain_name,ArrayList players_list,ArrayList batsman,ArrayList bowlers,ArrayList allRounders,String wicket_keeper_name){
        this.country_name=country_name;
        this.captain_name = captain_name;
        this.players_list = players_list;
        this.batsman = batsman;
        this.bowlers = bowlers;
        this.allRounders = allRounders;
        this.wicket_keeper_name =   wicket_keeper_name;
    }
}
class Match{
    Squad batting,bowling;
    int overs;
    int target;
    public Match(Squad batting,Squad bowling,int overs){
        this.batting = batting;
        this.bowling = bowling;
        this.overs = overs;
    }
    public Match(Squad batting,Squad bowling,int overs,int target){
        this.batting = batting;
        this.bowling = bowling;
        this.overs = overs;
        this.target = target;
    }

}

public class International_Cricket {
    static void rulesAndRegulations(){
        System.out.println("Rules And Regulations:");
        System.out.println("    1)No. of players in a Squad should be 11.\n    2)Captain name should start with a special character '*'\n    3)Batsman's name should ends with  'BA'\n    4)Bowler's name should ends with 'BO'\n    5)Wicket Keeper's name should ends with 'WC'\n    6)All Rounder name should ends with 'AL'");
    }
    static ArrayList createArrayList(){
        return new ArrayList<String>();
    }
    static Squad squadFormation(){
        Scanner scanner  = new Scanner(System.in);
        rulesAndRegulations();
        //country
        System.out.println("Enter country's name");
        String country = scanner.next();
        //playersList
        System.out.println("Enter player's names:");
        String captain_name="",wicket_Keeper="";
        ArrayList<String> players_list = createArrayList();
        ArrayList<String> batsman = createArrayList();
        ArrayList<String> bowlers = createArrayList();
        ArrayList<String>allrounders = createArrayList();
        for(int i=1;i<=5;i++) {
            String name = scanner.next();
            String identity = name.substring(name.length()-2,name.length());
			name = name.substring(0,name.length()-2);
            //check captaincy
            if(name.startsWith("*")){
                name = name.substring(1,name.length());
                if(captain_name.equals("")){
                    captain_name = name+"*";
                    players_list.add(captain_name);
                }else {
                    System.out.println("*********OOPS..! More than one captain is not allowed...Please try again..*********");
                    i--;
                }
            }
            switch (identity){
                case "BA":
                    batsman.add(name);
                    players_list.add(name);
                    break;
                case "BO":
                    bowlers.add(name);
                    players_list.add(name);
                    break;
                case "AL":
                    allrounders.add(name);
                    players_list.add(name);
                    break;
                case "WC":
                    if(!wicket_Keeper.equals("")) {
                        System.out.println("********* OOPS..! you've already entered wicketKeeprs name...Please try again..*********");
                        i--;
                    }
                    else {
                        wicket_Keeper = name;
                        players_list.add(name);
                    }
                    break;
                default:
                    System.out.println("********* OOPS..! Please enter names with identity...*********");
                    i--;
            }
        }
        Squad squad = new Squad(country,captain_name,players_list,batsman,bowlers,allrounders,wicket_Keeper);
        System.out.println("~~~~~~~~~~ Congratulations..! Your Squad is successfully created..~~~~~~~~~~");
        return squad;
    }
static void squadInfo(Squad squad){
        System.out.println("Country Name:- "+squad.country_name);
        System.out.println("Captain Name:- "+squad.captain_name);
        System.out.println("List of Batsman:-");
        System.out.println("    "+squad.batsman);
        System.out.println("List of Bowlers:-");
        System.out.println("    "+squad.bowlers);
        System.out.println("List of AllRounders:-");
        System.out.println("    "+squad.allRounders);
        System.out.println("Wicket Keeper Name:- "+squad.wicket_keeper_name);
	System.out.println();
                                   }
	void waitFor(int seconds){
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
     int generateRandom(int num){
        Random random = new Random();
        int n = random.nextInt(num);
        return n;
    }
    Match matchInfo(int overs,Squad squad1,Squad squad2){
        System.out.println("Its Time for tossing\n");

        Squad[] squads = new Squad[2];
        squads[0]=squad1;
        squads[1]= squad2;

        //toss selector
        int toss_selector = icc.generateRandom(2);
        System.out.println("Now toss_selector is "+squads[toss_selector].country_name+"'s captain "+squads[toss_selector].captain_name);

        //toss selector asking move
        int move = icc.generateRandom(2);
        String[] toss = {"Heads","Tails"};
        System.out.println(squads[toss_selector].country_name+"'s captain ask for "+ toss[move]);
        System.out.println("***---Lets spin the coin---***");

        icc.waitFor(2);

        //spin the coin
        int resultant_move = icc.generateRandom(2);
        int winner=move^resultant_move;
        winner=(winner+1)%2;
        winner = winner^toss_selector;
        winner=(winner+1)%2;

        icc.waitFor(1);

        //toss selector decision
        int decision = icc.generateRandom(2);
        if(decision==0)
            System.out.println(squads[winner].country_name+"'s captain won the toss and choose batting");
        else {
            System.out.println(squads[winner].country_name + "'s captain won the toss and choose chasing");
            winner=(winner+1)%2;
        }

        //match formation
        Match match = new Match(squads[winner],squads[(winner+1)%2],overs);
        return match;
    }
    ArrayList prioritizePlayers(ArrayList one,ArrayList two,ArrayList three,String wc){
        ArrayList<String> arr = new ArrayList<String>();
        arr.addAll(one);
        arr.addAll(two);
        arr.add(wc);
        arr.addAll(three);
        return arr;
    }
    String pollPlayers(ArrayList<String> bSquad){
        String name = bSquad.get(0);
        bSquad.remove(0);
        return name;
    }
    int firstHalfMatch(Match match){
        ArrayList<String> battingSquad ;
        battingSquad = icc.prioritizePlayers(match.batting.batsman,match.batting.allRounders,match.batting.bowlers,match.batting.wicket_keeper_name);
        ArrayList<String> bowlingSquad = new ArrayList<String>();
        bowlingSquad.addAll(match.bowling.bowlers);
        bowlingSquad.addAll(match.bowling.allRounders);

        //match attributes
            int overs = match.overs;
            String batsman1 = icc.pollPlayers(battingSquad);
            String batsman2 = icc.pollPlayers(battingSquad);
            String bowler = icc.pollPlayers(bowlingSquad);
            bowlingSquad.add(bowler);
            int totalRuns=0,batsman1_runs=0,batsman2_runs=0;
            int totalWickets=0;

        //matchs= Scenario
        int[] status = {1,1,1,4,2,2,2,7,2,9,3,4,1,3,6,15,16,1,18,2,6,6,4,1,1,2};

        for(int i=0;i<overs&&totalWickets<10;i++){

            for(int ball=1;ball<=6&&totalWickets<10;ball++){
                int random = icc.generateRandom(26);
                switch (status[random]){
                    case 4:
                        Players players = match.batting.players_info.get(batsman1);
                        players.setFours();
                        batsman1_runs+=status[random];
                        totalRuns = totalRuns+status[random];
                        break;
                    case 6:
                        Players players1 = match.batting.players_info.get(batsman1);
                        players1.setSixes();
                        batsman1_runs+=status[random];
                        totalRuns = totalRuns+status[random];
                        break;
                    case 1 :
                    case 3:
                        batsman1_runs+=status[random];
                        totalRuns = totalRuns+status[random];
                        int temp = batsman1_runs;
                        batsman1_runs = batsman2_runs;
                        batsman2_runs = temp;

                        String temp1 = batsman1;
                        batsman1 = batsman2;
                        batsman2 = temp1;
                        break;
                    case 2:
                        batsman1_runs+=status[random];
                        totalRuns = totalRuns+status[random];
                        break;
                    case 7:
                    case 9:
                    case 15:
                    case 18:
                        Players players2=match.batting.players_info.get(batsman1);
                        players2.setTotalRuns(batsman1_runs);
                        if(status[random]==7)
                            players2.setWicketType("BoldOut by"+bowler);
                        if(status[random]==9)
                            players2.setWicketType("LBW by"+bowler);
                        if(status[random]==15)
                            players2.setWicketType("RunOut");
                        if(status[random]==18)
                            players2.setWicketType("CatchOut"+bowler);
                        batsman1=icc.pollPlayers(battingSquad);
                        batsman1_runs=0;
                        totalWickets++;
                        players2 = match.bowling.players_info.get(bowler);
                        players2.setWickets();
                        break;
                }
                System.out.println(match.batting.country_name+"-->"+totalRuns+"-"+totalWickets+" Overs:-"+i+"."+ball+" "+batsman1+"-"+batsman1_runs+"  "+batsman2+"-"+batsman2_runs);
            }
            bowler = bowlingSquad.get(i%bowlingSquad.size());
        }
        match.target=totalRuns+1;
        return totalRuns;
    }


 public static void main(String[] args){
        icc = new International_Cricket();
        Scanner scanner = new Scanner(System.in);
        //players input
        System.out.println("Enter no.of players in a squad");
        int players = scanner.nextInt();
        Squad squad1=null,squad2 = null;
        squad1 = icc.squadFormation(players);
        squad2 = icc.squadFormation(players);
	 
	System.out.println("~~~~~~~~~~ Squad 1 ~~~~~~~~~~");
        icc.squadInfo(squad1);
        System.out.println("~~~~~~~~~~ Squad 2 ~~~~~~~~~~");
        icc.squadInfo(squad2);

        //overs input
        System.out.println("Enter no.of overs");
        int overs  = scanner.nextInt();
        Match match = icc.matchInfo(overs,squad1,squad2);

        //start the match
        int target = icc.firstHalfMatch(match);
        System.out.print(target);
    }
}



