package DragcaveBot;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;

import java.util.*;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.util.Pair;
import javafx.stage.Stage;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Controller {
    public CheckBox sa;
    public CheckBox rd;
    public CheckBox bd;
    public CheckBox yd;
    public CheckBox gd;
    public CheckBox g;
    public CheckBox s;
    public CheckBox gw;
    public CheckBox cd;
    public CheckBox md;
    public CheckBox id;
    public CheckBox td;
    public CheckBox pd;
    public CheckBox x;
    public CheckBox bl;
    public CheckBox cheese;
    public CheckBox chicken;

    public Button login;
    public Button start;
    public Button stop;
    public Button clear;

    public TextField username;
    public TextField timeOutput;
    public TextField eggOutput;

    public PasswordField password;

    public TextArea output;
    public TextArea errorOutput;

    private long time = 500;
    private HashMap<String, Dragons.Dragon> wantedEggs = new HashMap<>();
    private LinkedHashMap<Dragons.Dragon, String> rareEggs = Dragons.getDragons();
    private LinkedHashMap<String, Dragons.Dragon> rareEggDescriptions = Dragons.getDragonDescriptions();
    private LinkedList<Dragons.Dragon> gottenEggs = new LinkedList<>();
    private LinkedList<Dragons.Dragon> eggs = new LinkedList<>();
    private Map<String, String > cookies;
    private ScheduledExecutorService clock;

    public void initialize() {
        for (Pair<CheckBox, Dragons.Dragon> pair:getAllCheckBoxes()) {
            CheckBox box = pair.getKey();
            Dragons.Dragon dragon = pair.getValue();
            box.setOnAction(e->changeWantedEggs(dragon, box));
        }
    }

    public void start() {
        if (cookies.isEmpty()) {
            output.setText("Please Login");
            return;
        }
        initialize();

        clock = Executors.newSingleThreadScheduledExecutor();
        clock.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                int minute = LocalDateTime.now().getMinute();
                int second = LocalDateTime.now().getSecond();
                if ((minute + 1) % 10 == 5 || (minute + 1) % 10 == 0 || minute % 10 == 0 || minute % 10 == 5) {
                    if (second % 2 == 0) {
                        if ((minute % 10 == 0 || minute % 10 == 5) && second <= 30) {
                            setEggLocations();
                        } else if (((minute + 1) % 10 == 5 || (minute + 1) % 10 == 0) && second >= 46) {
                            setEggLocations();
                        }
                    }
                }
                if (second % 10 == 0) {
                    System.out.println("");
                    System.out.println(wantedEggs.size());
                } else {
                    System.out.print(minute + ":" + second + "  ");
                    timeOutput.setText(minute + ":" + second + "  ");
                }
                if (minute % 15 == 0) {
                    gottenEggs.clear();
                    eggs.clear();
                }
            }
        }, 0, time, TimeUnit.MILLISECONDS);
    }

    public void login() {
        String user;
        String pass;
        user = username.getText();
        pass = password.getText();
        clearOutput();

        try {
            Document loginPage = Jsoup.connect("http://dragcave.net/login").get();
            Element loginElement = loginPage.getElementsByClass("_23_5").get(0);
            FormElement loginForm = (FormElement) loginElement;
            Connection con = loginForm.submit();
            Connection.Response loginResponse = con.data("username", user, "password", pass).execute();
            cookies = loginResponse.cookies();
            if (cookies.isEmpty()) {
                output.setText("Bad Login");
            } else {
                output.setText("Login Successful");
                login.setDisable(false);
            }
        } catch (Exception e) {
            errorOutput.setText(e.getLocalizedMessage() + "  " + e.getMessage());
        }
    }

    public void clearOutput() {
        output.setText("");
    }

    public void selectAll() {
        if (!sa.isSelected()) {
            sa.setSelected(false);
        } else {
            sa.setSelected(true);
            for (Pair<CheckBox, Dragons.Dragon> pair:getAllCheckBoxes()) {
                CheckBox box = pair.getKey();
                Dragons.Dragon dragon = pair.getValue();
                box.setSelected(true);
                wantedEggs.put(dragon.toString(), dragon);
            }
        }
    }

    public void stop() {
        Stage stage = (Stage) stop.getScene().getWindow();
        stage.close();
    }

    private void getEggs(LinkedList<Element> locations) {
        try {
            for (Element location : locations) {
                for (Element e : location.children()) {
                    String description = e.ownText();
                    String eggLink = e.child(0).attr("href");
                    checkEggLink(eggLink);
                    if (wantedEggs.containsKey(description)) {
                        Connection get = Jsoup.connect("http://dragcave.net" + eggLink).cookies(cookies);
                        Connection.Response resultResponse = get.cookies(cookies).execute();

                        Document resultDocument = resultResponse.parse();
                        Element resultElement = resultDocument.children().get(0).getElementsByClass("_3p_0")
                                .get(0).getElementsByClass("_3p_2").get(0).getElementById("middle");

                        String result = resultElement.children().get(0).text();
                        getEggResult(result, description, eggLink);
                        Thread.sleep(1000);
                    }
                    if (rareEggs.containsValue(description)) {
                        Dragons.Dragon rareEgg = rareEggDescriptions.get(description);
                        eggs.add(rareEgg);
                    }
                    System.out.println(description + " " + eggLink);
                }
            }
        } catch (Exception e) {
            errorOutput.setText(e.getLocalizedMessage() + "  " + e.getMessage());
            errorOutput.setText(e.getStackTrace().toString());
        }
    }

    private void setEggLocations(){
        try {
            Document Coast = Jsoup.connect("http://dragcave.net/locations/1").cookies(cookies).get();
            Document Desert = Jsoup.connect("http://dragcave.net/locations/2").cookies(cookies).get();
            Document Forest = Jsoup.connect("http://dragcave.net/locations/3").cookies(cookies).get();
            Document Jungle = Jsoup.connect("http://dragcave.net/locations/4").cookies(cookies).get();
            Document Alpine = Jsoup.connect("http://dragcave.net/locations/5").cookies(cookies).get();
            Document Volcano = Jsoup.connect("http://dragcave.net/locations/6").cookies(cookies).get();

            Element AlpineEggs = Alpine.getElementsByClass("eggs").get(0);
            Element CoastEggs = Coast.getElementsByClass("eggs").get(0);
            Element DesertEggs = Desert.getElementsByClass("eggs").get(0);
            Element ForestEggs = Forest.getElementsByClass("eggs").get(0);
            Element JungleEggs = Jungle.getElementsByClass("eggs").get(0);
            Element VolcanoEggs = Volcano.getElementsByClass("eggs").get(0);
            LinkedList<Element> eggs = new LinkedList<>();
            
            eggs.add(AlpineEggs);
            eggs.add(CoastEggs);
            eggs.add(DesertEggs);
            eggs.add(ForestEggs);
            eggs.add(JungleEggs);
            eggs.add(VolcanoEggs);
            getEggs(eggs);
        } catch (Exception e) {
            errorOutput.setText(e.getLocalizedMessage() + "  " + e.getMessage());
        }
    }

    private void getEggResult(String result, String description, String link) {
        if (result == "You are already overburdened and decide not to stress yourself by taking this egg.") {
            System.out.println("You are overburdened!");
        } else if (result == "Sorry, this egg has already been taken by somebody else.") {
            //gottenEggs.add(rareEggDescriptions.get(description) + " has been taken by somebody else." + " Link: " + link);
        } else{
            Dragons.Dragon dragon = rareEggDescriptions.get(description);
            gottenEggs.add(dragon);
            String currentOutput = output.getText();
            output.setText(currentOutput + "\n Got Egg!" );
        }
    }

    private void checkEggLink(String link) {
        if (link.equals("/register")) {
            login();
        }
    }

    private void changeWantedEggs(Dragons.Dragon dragon, CheckBox box) {
        if (box.isSelected()) {
            wantedEggs.put(dragon.toString(), dragon);
        } else {
            wantedEggs.remove(dragon.toString());
        }
        String eggSize = Integer.toString(wantedEggs.size());
        eggOutput.setText(eggSize);
    }

    private ArrayList<Pair<CheckBox, Dragons.Dragon>> getAllCheckBoxes() {
        int i = 0;
        ArrayList<Pair<CheckBox, Dragons.Dragon>> pairs = new ArrayList<>();
        CheckBox[] checkBoxes = {
                rd, bd, yd, gd, g, s, gw, cd,
                md, id, td, pd, x, bl, cheese, chicken
        };
        LinkedHashMap<Dragons.Dragon, String> dragons = Dragons.getDragons();

        for (Map.Entry<Dragons.Dragon, String> dragon:dragons.entrySet()) {
            CheckBox box = checkBoxes[i];
            pairs.add(new Pair<>(box, dragon.getKey()));
            i += 1;
        }
        return pairs;
    }
}
