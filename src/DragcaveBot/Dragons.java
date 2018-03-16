package DragcaveBot;

import java.util.LinkedHashMap;

public class Dragons {
    public static enum Dragon {
        Xenowyrm {
            public String toString() {
                return "Mana courses throughout this glassy egg.";
            }
        },
        BlueDino {
            public String toString() {
                return "This egg looks like it doesn't belong; it is brightly colored with white spots. " +
                        "It's much lighter than the other eggs.";
            }
        },
        BlusangLindwurm {
            public String toString() {
                return "This egg smells faintly like brine.";
            }
        },
        CheeseDragon {
            public String toString() {
                return "This egg is soft and smells uncannily like cheese.";
            }
        },
        Chicken {
            public String toString() {
                return "This egg is much smaller than the others.";
            }
        },
        Copper {
            public String toString() {
                return "This egg gleams with a reddish shine.";
            }
        },
        GoldDragon {
            public String toString() {
                return "This egg is very reflective, almost metallic-looking.";
            }
        },
        GoldenWyvren {
            public String toString() {
                return "This egg shimmers like gold.";
            }
        },
        GreenDino {
            public String toString() {
                return "This egg looks like it doesn't belong; it is brightly colored with white spots.";
            }
        },
        IceDragon {
            public String toString() {
                return "This egg has icicles forming on it.";
            }
        },
        MagmaDragon {
            public String toString() {
                return "This egg is almost too hot to touch.";
            }
        },
        PaperDragon {
            public String toString() {
                return "This egg is tiny and made out of several pieces of paper folded together.";
            }
        },
        RedDino {
            public String toString() {
                return "This egg looks like it doesn't belong; it is brightly colored with white spots. " +
                        "It's much warmer than the rest of the eggs.";
            }
        },
        SilverDragon {
            public String toString() {
                return "This egg gives off a beautiful glow.";
            }
        },
        ThunderDragon {
            public String toString() {
                return "Whenever you go near this egg, your hair stands on end.";
            }
        },
        YellowDino {
            public String toString() {
                return "This egg looks like it doesn't belong; it is brightly colored with white spots. " +
                        "It's much heavier than the other eggs.";
            }
        }
    }

    public static LinkedHashMap<Dragon, String> getDragons() {
        LinkedHashMap<Dragon, String> dragons = new LinkedHashMap<>();

        dragons.put(Dragon.RedDino, Dragon.RedDino.toString());
        dragons.put(Dragon.BlueDino, Dragon.BlueDino.toString());
        dragons.put(Dragon.YellowDino, Dragon.YellowDino.toString());
        dragons.put(Dragon.GreenDino, Dragon.GreenDino.toString());
        dragons.put(Dragon.GoldDragon, Dragon.GoldDragon.toString());
        dragons.put(Dragon.SilverDragon, Dragon.SilverDragon.toString());
        dragons.put(Dragon.GoldenWyvren, Dragon.GoldenWyvren.toString());
        dragons.put(Dragon.Copper, Dragon.Copper.toString());
        dragons.put(Dragon.MagmaDragon, Dragon.MagmaDragon.toString());
        dragons.put(Dragon.IceDragon, Dragon.IceDragon.toString());
        dragons.put(Dragon.ThunderDragon, Dragon.ThunderDragon.toString());
        dragons.put(Dragon.PaperDragon, Dragon.PaperDragon.toString());
        dragons.put(Dragon.Xenowyrm, Dragon.Xenowyrm.toString());
        dragons.put(Dragon.BlusangLindwurm, Dragon.BlusangLindwurm.toString());
        dragons.put(Dragon.CheeseDragon, Dragon.CheeseDragon.toString());
        dragons.put(Dragon.Chicken, Dragon.Chicken.toString());
        return dragons;
    }

    public static LinkedHashMap<String, Dragon> getDragonDescriptions() {
        LinkedHashMap<String, Dragon> dragonDescriptions = new LinkedHashMap<>();

        dragonDescriptions.put(Dragon.Xenowyrm.toString(), Dragon.Xenowyrm);
        dragonDescriptions.put(Dragon.BlueDino.toString(), Dragon.BlueDino);
        dragonDescriptions.put(Dragon.BlusangLindwurm.toString(), Dragon.BlusangLindwurm);
        dragonDescriptions.put(Dragon.CheeseDragon.toString(), Dragon.CheeseDragon);
        dragonDescriptions.put(Dragon.Chicken.toString(), Dragon.Chicken);
        dragonDescriptions.put(Dragon.Copper.toString(), Dragon.Copper);
        dragonDescriptions.put(Dragon.GoldDragon.toString(), Dragon.GoldDragon);
        dragonDescriptions.put(Dragon.GoldenWyvren.toString(), Dragon.GoldenWyvren);
        dragonDescriptions.put(Dragon.GreenDino.toString(), Dragon.GreenDino);
        dragonDescriptions.put(Dragon.IceDragon.toString(), Dragon.IceDragon);
        dragonDescriptions.put(Dragon.MagmaDragon.toString(), Dragon.MagmaDragon);
        dragonDescriptions.put(Dragon.PaperDragon.toString(), Dragon.PaperDragon);
        dragonDescriptions.put(Dragon.RedDino.toString(), Dragon.RedDino);
        dragonDescriptions.put(Dragon.SilverDragon.toString(), Dragon.SilverDragon);
        dragonDescriptions.put(Dragon.ThunderDragon.toString(), Dragon.ThunderDragon);
        dragonDescriptions.put(Dragon.YellowDino.toString(), Dragon.YellowDino);
        return dragonDescriptions;
    }
}