package model.cards.definitionCards;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import model.abilities.*;
import model.abilities.commanderAbilities.*;
import model.cards.weatherCards.BitingFrost;
import model.cards.weatherCards.ImpenetrableFog;
import model.abilities.weatherAbilities.ClearWeatherAbility;
import model.cards.weatherCards.*;
import view.Launcher;

import java.util.*;

public enum Cards {
   MARDROEME {
       public Card getCard(){
            SpellCard card = new SpellCard("Skellige", 3, "Mardroeme", new MardoemeAbility(),
                                            "/cards/skellige/special_mardroeme.jpg");
           return card;
       }
   },
   BERSERKER{
       public Card getCard(){
           UnitCard card = new UnitCard("Skellige", 1, "Berserker", 4, false,
                   new ArrayList<>(Collections.singletonList(1)),
                   new BerserkerAbility(),
                   "/cards/skellige/skellige_berserker.jpg");
           return card;
       }
   },
    VILDKAARL{
       public Card getCard(){
           UnitCard card = new UnitCard("Skellige", 0, "Vildkaarl", 14, false,
                   new ArrayList<>(Collections.singletonList(1)),
                   new MoralBoostAbility(),
                   "/cards/skellige/skellige_vildkaarl.jpg");
           return card;
       }
    },
    SVANRIGE{
       public Card getCard(){
           UnitCard card = new UnitCard("Skellige", 1, "Svanrige", 4, false,
                   new ArrayList<>(Collections.singletonList(1)),
                   null,
                   "/cards/skellige/skellige_svanrige.jpg");
           return card;
       }
    },
    UDALRYK{
       public Card getCard(){
           UnitCard card = new UnitCard("Skellige", 1, "Udalryk", 4, false,
                   new ArrayList<>(Collections.singletonList(1)),
                   null,
                   "/cards/skellige/skellige_udalryk.jpg");
           return card;
       }
    },
    DONAR_AN_HINDAR{
       public Card getCard(){
           UnitCard card = new UnitCard("Skellige", 1, "Donar an Hindar", 4, false,
                   new ArrayList<>(Collections.singletonList(1)),
                   null,
                   "/cards/skellige/skellige_donar.jpg");
           return card;
       }
    },
    CLAN_AN_CRAITE{
       public Card getCard(){
           UnitCard card = new UnitCard("Skellige", 3, "Clan An Craite", 6, false,
                   new ArrayList<>(Collections.singletonList(1)),
                   new TightBondAbility(),
                   "/cards/skellige/skellige_craite_warrior.jpg");
           return card;
       }
    },
    MADMAN_LUGOS{
       public Card getCard(){
           UnitCard card = new UnitCard("Skellige", 1, "Madman Lugos", 6, false,
                   new ArrayList<>(Collections.singletonList(1)),
                   null,
                   "/cards/skellige/skellige_madmad_lugos.jpg");
           return card;
       }
    },
    CERYS{
       public Card getCard(){
           UnitCard card = new UnitCard("Skellige", 1, "Cerys", 10, true,
                   new ArrayList<>(Collections.singletonList(1)),
                   new MusterAbility(),
                   "/cards/skellige/skellige_cerys.jpg");
           return card;
       }
    },
    KAMBI{
       public Card getCard(){
           UnitCard card = new UnitCard("Skellige", 1, "Kambi", 11, true,
                   new ArrayList<>(Collections.singletonList(1)),
                   new TransformerAbility(),
                   "/cards/skellige/skellige_kambi.jpg");
           return card;
       }
    },
    BIRNA_BRAN{
       public Card getCard(){
           UnitCard card = new UnitCard("Skellige", 1, "Brian Bran", 2, false,
                   new ArrayList<>(Collections.singletonList(1)),
                   new MedicAbility(),
                   "/cards/skellige/skellige_birna.jpg");
           return card;
       }
    },
    IAN_DRUMMOND_SHIELDMAIDE{
       public Card getCard(){
           UnitCard card = new UnitCard("Skellige", 3, "Ian Drummond Shieldmaide", 4, false,
                   new ArrayList<>(Collections.singletonList(1)),
                   new TightBondAbility(),
                   "/cards/skellige/skellige_shield_maiden_2.jpg");
           return card;
       }
    },
    CLAN_DIMUN_PIRATE{
       public Card getCard(){
           UnitCard card = new UnitCard("Skellige", 1, "Clan Dimun Pirate", 6, false,
                   new ArrayList<>(Collections.singletonList(2)),
                   new ScorchAbility(),
                   "/cards/skellige/skellige_dimun_pirate.jpg");
           return card;
       }
    },
    CLAN_BROKVAR_ARCHER{
       public Card getCard(){
           UnitCard card = new UnitCard("Skellige", 3, "Clan Brokvar Archer", 6, false,
                   new ArrayList<>(Collections.singletonList(2)),
                   null,
                   "/cards/skellige/skellige_brokva_archer.jpg");
           return card;
       }
    },
    ERMION{
       public Card getCard(){
           UnitCard card = new UnitCard("Skellige", 1, "Ermion", 8, true,
                   new ArrayList<>(Collections.singletonList(2)),
                   new MardoemeAbility(),
                   "/cards/skellige/skellige_ermion.jpg");
           return card;
       }
    },
    HJALMAR {
        public Card getCard() {
            UnitCard card = new UnitCard("Skellige", 1, "Hjalmar", 10, true,
                    new ArrayList<>(Collections.singletonList(2)),
                    null,
                    "/cards/skellige/skellige_hjalmar.jpg");
            return card;
        }
    },
    YOUNG_BERSERKER{
       public Card getCard(){
           UnitCard card = new UnitCard("Skellige", 3, "Young Berserker", 2, false,
                   new ArrayList<>(Collections.singletonList(2)),
                   new BerserkerAbility(),
                   "/cards/skellige/skellige_young_berserker.jpg");
           return card;
       }
    },
    YOUNG_VILDKAARL{
       public Card getCard(){
           UnitCard card = new UnitCard("Skellige", 0, "Young Vildkaarl", 8, false,
                   new ArrayList<>(Collections.singletonList(2)),
                   new TightBondAbility(),
                   "/cards/skellige/skellige_young_vildkaarl.jpg");
           return card;
       }
    },
    LIGHT_LONGSHIP{
       public Card getCard(){
           UnitCard card = new UnitCard("Skellige", 3, "Light Longship", 4, false,
                   new ArrayList<>(Collections.singletonList(2)),
                   new MusterAbility(),
                   "/cards/skellige/skellige_light_longship.jpg");
           return card;
       }
    },
    WAR_LONGSHIP{
       public Card getCard(){
           UnitCard card = new UnitCard("Skellige", 3, "War Longship", 6, false,
                   new ArrayList<>(Collections.singletonList(3)),
                   new TightBondAbility(),
                   "/cards/skellige/skellige_war_longship.jpg");
           return card;
       }
    },
    DRAIG_BON_DHU{
       public Card getCard(){
           UnitCard card = new UnitCard("Skellige", 1, "Draig Bon Dhu", 2, false,
                   new ArrayList<>(Collections.singletonList(3)),
                   new HornAbility(),
                   "/cards/skellige/skellige_draig.jpg");
           return card;
       }
    },
    OLAF{
       public Card getCard(){
           UnitCard card = new UnitCard("Skellige", 1, "Olaf", 12, false,
                   new ArrayList<>(Arrays.asList(1, 2)),
                   new MoralBoostAbility(),
                   "/cards/skellige/skellige_olaf.jpg");
           return card;
       }
    },
    ELVEN_SKIRMISHER{
       public Card getCard(){
           UnitCard card = new UnitCard("Scoia'tael", 3, "Elven Skirmisher", 2, false,
                   new ArrayList<>(Collections.singletonList(2)),
                   new MusterAbility(),
                   "/cards/scoiatael/scoiatael_elf_skirmisher.jpg");
           return card;
       }
    },
    YAEVINN{
       public Card getCard(){
           UnitCard card = new UnitCard("Scoia'tael", 1, "Yaevinn", 6, false,
                   new ArrayList<>(Arrays.asList(1, 2)),
                   null,
                   "/cards/scoiatael/scoiatael_yaevinn.jpg");
           return card;
       }
    },
    CIARAN_AEP{
       public Card getCard(){
           UnitCard card = new UnitCard("Scoia'tael", 1, "Ciaran Aep", 3, false,
                   new ArrayList<>(Arrays.asList(1, 2)),
                   null,
                   "/cards/scoiatael/scoiatael_ciaran.jpg");
           return card;
       }
    },
    DENNIS_CRANMER{
       public Card getCard(){
           UnitCard card = new UnitCard("Scoia'tael", 1, "Dennis Cranmer", 6, false,
                   new ArrayList<>(Collections.singletonList(1)),
                   null,
                   "/cards/scoiatael/scoiatael_dennis.jpg");
           return card;
       }
    },
    DOL_BLATHANNA_SCOUT{
       public Card getCard(){
           UnitCard card = new UnitCard("Scoia'tael", 3, "Dol Blathanna Scout", 6, false,
                   new ArrayList<>(Arrays.asList(1, 2)),
                   null,
                   "/cards/scoiatael/scoiatael_dol_blathanna_scout.jpg");
           return card;
       }
    },
    DOL_BLATHANNA_ARCHER{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Scoia'tael", 1, " Dol Blathanna Archer", 4, false,
                    new ArrayList<>(Collections.singletonList(2)),
                    null,
                    "/cards/scoiatael/scoiatael_dol_archer.jpg");
            return card;
        }
    },
    DWARVEN_SKIRMISHER{
       public Card getCard(){
           UnitCard card = new UnitCard("Scoia'tael", 3, "Dwarven Skirmisher", 3, false,
                   new ArrayList<>(Collections.singletonList(1)),
                   new MusterAbility(),
                   "/cards/scoiatael/scoiatael_dwarf.jpg");
           return card;
       }
    },
    HAVEKAR_HEALER{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Scoia'tael", 3, "Havekar Healer", 0, false,
                    new ArrayList<>(Arrays.asList(1, 2)),
                    new MedicAbility(),
                    "/cards/scoiatael/scoiatael_havekar_healer.jpg");
            return card;
        }
    },
    HAVEKAR_SMUGGLER{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Scoia'tael", 3, "Havekar Smuggler", 5, false,
                    new ArrayList<>(Collections.singletonList(1)),
                    new MusterAbility(),
                    "/cards/scoiatael/scoiatael_havekar_smuggler.jpg");
            return card;
        }
    },
    IDEA_EMEAN_AEP{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Scoia'tael", 1, "Idea Emean aep", 6, false,
                    new ArrayList<>(Collections.singletonList(2)),
                    null,
                    "/cards/scoiatael/scoiatael_ida.jpg");
            return card;
        }
    },
    RIORDIAN{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Scoia'tael", 1, "Riordian", 1, false,
                    new ArrayList<>(Collections.singletonList(2)),
                    null,
                    "/cards/scoiatael/scoiatael_riordain.jpg");
            return card;
        }
    },
    TOROUVIEL{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Scoia'tael", 1, "Torouviel", 2, false,
                    new ArrayList<>(Collections.singletonList(2)),
                    null,
                    "/cards/scoiatael/scoiatael_toruviel.jpg");
            return card;
        }
    },
    VRIHEDD_BRIGADE_RECRUIT{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Scoia'tael", 1, "Vrihedd Brigade Recruit", 4, false,
                    new ArrayList<>(Collections.singletonList(2)),
                    null,
                    "/cards/scoiatael/scoiatael_vrihedd_recruit.jpg");
            return card;
        }
    },
    VRIHEDD_BRIGADE_VETERAN{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Scoia'tael", 2, "Vrihedd Brigade Veteran", 5, false,
                    new ArrayList<>(Arrays.asList(1, 2)),
                    null,
                    "/cards/scoiatael/scoiatael_vrihedd_brigade_1.jpg");
            return card;
        }
    },
    MILVA{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Scoia'tael", 1, "Milva", 10, false,
                    new ArrayList<>(Collections.singletonList(2)),
                    new MoralBoostAbility(),
                    "/cards/scoiatael/scoiatael_milva.jpg");
            return card;
        }
    },
    SAESENTHESSIS{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Scoia'tael", 1, "Saesenthessis", 10, true,
                    new ArrayList<>(Collections.singletonList(2)),
                    null,
                    "/cards/scoiatael/scoiatael_saskia.jpg");
            return card;
        }
    },
    Schirru{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Scoia'tael", 1, "Schirru", 8, false,
                    new ArrayList<>(Collections.singletonList(3)),
                    new ScorchAbility(),
                    "/cards/scoiatael/scoiatael_schirru.jpg");
            return card;
        }
    },
    EITHNE{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Scoia'tael", 1,"Eithne", 10, true,
                    new ArrayList<>(Collections.singletonList(2)),
                    null,
                    "/cards/scoiatael/scoiatael_eithne.jpg");
            return card;
        }
    },
    ISENGRIM_FAOILTIARNA{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Scoia'tael", 1, "Isengrim Faoiltiarna", 10, true,
                    new ArrayList<>(Collections.singletonList(1)),
                    new MoralBoostAbility(),
                    "/cards/scoiatael/scoiatael_isengrim.jpg");
            return card;
        }
    },
    BALLISTA{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Northern Realms", 2, "Ballista", 6, false,
                    new ArrayList<>(Collections.singletonList(3)),
                    null,
                    "/cards/realms/realms_ballista.jpg");
            return card;
        }
    },
    CATAPULT{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Northern Realms", 2, "Catapult", 8, false,
                    new ArrayList<>(Collections.singletonList(3)),
                    new TightBondAbility(),
                    "/cards/realms/realms_catapult_1.jpg");
            return card;
        }
    },
    DRAGON_HUNTER{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Northern Realms", 3,"Dragon Hunter", 5, false,
                    new ArrayList<>(Collections.singletonList(2)),
                    new TightBondAbility(),
                    "/cards/realms/realms_crinfrid.jpg");
            return card;
        }
    },
    DUN_BANNER_MEDIC{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Northern Realms", 1, "Dun Banner Medic", 5, false,
                    new ArrayList<>(Collections.singletonList(3)),
                    new MedicAbility(),
                    "/cards/realms/realms_banner_medic.jpg");
            return card;
        }
    },
    ESTERAD_THYSSEN{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Northern Realms", 1, "Esterad Thyssen", 10, true,
                    new ArrayList<>(Collections.singletonList(1)),
                    null,
                    "/cards/realms/realms_esterad.jpg");
            return card;
        }
    },
    JOHN_NATALIS{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Northern Realms", 1, "John Natalis", 10, true,
                    new ArrayList<>(Collections.singletonList(1)),
                    null,
                    "/cards/realms/realms_natalis.jpg");
            return card;
        }
    },
    KAEDWENI_SIEGE_EXPERT{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Northern Realms", 3, "Kaedweni Siege Expert", 1, false,
                    new ArrayList<>(Collections.singletonList(3)),
                    new MoralBoostAbility(),
                    "/cards/realms/realms_kaedwen_siege.jpg");
            return card;
        }
    },
    PHILIPPA_EILHART{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Northern Realms", 1, "Philippa Eilhart", 10, true,
                    new ArrayList<>(Collections.singletonList(2)),
                    null,
                    "/cards/realms/realms_philippa.jpg");
            return card;
        }
    },
    POOR_INFANTRY{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Northern Realms", 4, "Poor Infantry", 1, false,
                    new ArrayList<>(Collections.singletonList(1)),
                    new TightBondAbility(),
                    "/cards/realms/realms_poor_infantry.jpg");
            return card;
        }
    },
    PRINCE_STENNIS{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Northern Realms", 1, "Prince Stennis", 5, false,
                    new ArrayList<>(Collections.singletonList(1)),
                    new SpyAbility(),
                    "/cards/realms/realms_stennis.jpg");
            return card;
        }
    },
    REDANIAN_FOOT_SOLDIER{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Northern Realms", 2, "Redanian Foot Soldier", 1, false,
                    new ArrayList<>(Collections.singletonList(1)),
                    null,
                    "/cards/realms/realms_redania.jpg");
            return card;
        }
    },
    SABRINA_GLEVISSING{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Northern Realms", 1, "Sabrina Glevissing", 4, false,
                    new ArrayList<>(Collections.singletonList(2)),
                    null,
                    "/cards/realms/realms_sabrina.jpg");
            return card;
        }
    },
    SIEGE_TOWER{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Northern Realms", 1, "Siege Tower", 6, false,
                    new ArrayList<>(Collections.singletonList(3)),
                    null,
                    "/cards/realms/realms_siege_tower.jpg");
            return card;
        }
    },
    SIEGFRIED_OF_DENESLE{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Northern Realms", 1, "Siegfried of Denesle", 5, false,
                    new ArrayList<>(Collections.singletonList(1)),
                    null,
                    "/cards/realms/realms_siegfried.jpg");
            return card;
        }
    },
    SIGISMUND_DIJKSTRA{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Northern Realms", 1, "Sigismund Dijkstra", 4, false,
                    new ArrayList<>(Collections.singletonList(1)),
                    new SpyAbility(),
                    "/cards/realms/realms_dijkstra.jpg");
            return card;
        }
    },
    SILE_DE_TANSARVILLE{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Northern Realms",1,  "Sile de Tansarville", 5, false,
                    new ArrayList<>(Collections.singletonList(2)),
                    null,
                    "/cards/realms/realms_sheala.jpg");
            return card;
        }
    },
    THALER{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Northern Realms", 1, "Thaler", 1, false,
                    new ArrayList<>(Collections.singletonList(3)),
                    new SpyAbility(),
                    "/cards/realms/realms_thaler.jpg");
            return card;
        }
    },
    VERNON_ROCHE{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Northern Realms", 1, "Vernon Roche", 10, true,
                    new ArrayList<>(Collections.singletonList(1)),
                    null, "/cards/realms/realms_vernon.jpg");
            return card;
        }
    },
    VES{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Northern Realms", 1, "Ves", 5, false,
                    new ArrayList<>(Collections.singletonList(1)),
                    null,
                    "/cards/realms/realms_ves.jpg");
            return card;
        }
    },
    YARPEN_ZIRGRIN{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Northern Realms", 1, "Yarpen Zirgin", 2, false,
                    new ArrayList<>(Collections.singletonList(1)),
                    null,
                    "/cards/realms/realms_yarpen.jpg");
            return card;
        }
    },
    IMPERA_BRIGADE_GUARD{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Empire Nilfgaardian", 4, "Impera Brigade Guard", 3, false,
                    new ArrayList<>(Collections.singletonList(1)),
                    new TightBondAbility(),
                    "/cards/nilfgaard/nilfgaard_imperal_brigade.jpg");
            return card;
        }
    },
    STEFAN_SKELLEN{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Empire Nilfgaardian", 1, "Stefan Skellen", 9, false,
                    new ArrayList<>(Collections.singletonList(1)),
                    new SpyAbility(),
                    "/cards/nilfgaard/nilfgaard_stefan.jpg");
            return card;
        }
    },
    YOUNG_EMISSARY{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Empire Nilfgaardian", 2, "Young Emissary", 5, false,
                    new ArrayList<>(Collections.singletonList(1)),
                    new TightBondAbility(),
                    "/cards/nilfgaard/nilfgaard_young_emissary.jpg");
            return card;
        }
    },
    CAHIR_MAWR_DYFFRYN_AEP_CEALLACH{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Empire Nilfgaardian", 1, "Cahir Mawr Dyffryn aep Ceallach",
                    6, false,
                    new ArrayList<>(Collections.singletonList(1)),
                    null,
                    "/cards/nilfgaard/nilfgaard_cahir.jpg");
            return card;
        }
    },
    MENNO_COEHOORN{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Empire Nilfgaardian", 1, "Menno Coehoorn", 10, true,
                    new ArrayList<>(Collections.singletonList(1)),
                    new MedicAbility(),
                    "/cards/nilfgaard/nilfgaard_menno.jpg");
            return card;
        }
    },
    PUTTKAMMER{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Empire Nilfgaardian", 3, "Puttkammer", 3, false,
                    new ArrayList<>(Collections.singletonList(2)),
                    null,
                    "/cards/nilfgaard/nilfgaard_puttkammer.jpg");
            return card;
        }
    },
    BLACK_INFANTRY_ARCHER{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Empire Nilfgaardian", 2, "Black Infantry Archer", 10, false,
                    new ArrayList<>(Collections.singletonList(2)),
                    null,
                    "/cards/nilfgaard/nilfgaard_black_archer.jpg");
            return card;
        }
    },
    TIBOR_EGGEBRACHT{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Empire Nilfgaardian", 1, "Tibor Eggebracht", 10, true,
                    new ArrayList<>(Collections.singletonList(2)),
                    null,
                    "/cards/nilfgaard/nilfgaard_tibor.jpg");
            return card;
        }
    },
    RENUALD_AEP_MATSEN{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Empire Nilfgaardian", 1, "Renuald aep Matsen", 5, false,
                    new ArrayList<>(Collections.singletonList(2)),
                    null,
                    "/cards/nilfgaard/nilfgaard_renuald.jpg");
            return card;
        }
    },
    FRINGILLA_VIGO{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Empire Nilfgaardian", 1, "Fringilla Vigo", 6, false,
                    new ArrayList<>(Collections.singletonList(2)),
                    null,
                    "/cards/nilfgaard/nilfgaard_fringilla.jpg");
            return card;
        }
    },
    ROTTEN_MANGONEL{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Empire Nilfgaardian", 1, "Rotten Mangonel", 3, false,
                    new ArrayList<>(Collections.singletonList(3)),
                    null,
                    "/cards/nilfgaard/nilfgaard_rotten.jpg");
            return card;
        }
    },
    ZERRIKANIAN_FIRE_SCORPION{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Empire Nilfgaardian", 1, "Zerrikanian Fire Scorpion",
                    5, false,
                    new ArrayList<>(Collections.singletonList(3)),
                    null,
                    "/cards/nilfgaard/nilfgaard_zerri.jpg");
            return card;
        }
    },
    SIEGE_ENGINEER{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Empire Nilfgaardian", 1, "Siege Engineer", 6, false,
                    new ArrayList<>(Collections.singletonList(3)),
                    null,
                    "/cards/nilfgaard/nilfgaard_siege_engineer.jpg");
            return card;
        }
    },
    MORVRAN_VOORHIS{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Empire Nilfgaardian", 1, "Morvran Voorhis", 10, true,
                    new ArrayList<>(Collections.singletonList(3)),
                    null,
                    "/cards/nilfgaard/nilfgaard_morvran.jpg");
            return card;
        }
    },
    CYNTHIA{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Empire Nilfgaardian", 1, "Cynthia", 4, false,
                    new ArrayList<>(Collections.singletonList(2)),
                    null,
                    "/cards/nilfgaard/nilfgaard_cynthia.jpg");
            return card;
        }
    },
    ETOLIAN_AUXILIARY_ARCHERS{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Empire Nilfgaardian", 2, "Etolian Auxiliary Archers",
                    1, false,
                    new ArrayList<>(Collections.singletonList(2)),
                    new MedicAbility(),
                    "/cards/nilfgaard/nilfgaard_archer_support.jpg");
            return card;
        }
    },
    MORTEISEN{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Empire Nilfgaardian", 1, "Morteisen", 3, false,
                    new ArrayList<>(Collections.singletonList(1)),
                    null,
                    "/cards/nilfgaard/nilfgaard_morteisen.jpg");
            return card;
        }
    },
    NAUSICAA_CAVALRY_RIDER{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Empire Nilfgaardian", 3, "Nausicaa Cavalry Rider",
                    2, false,
                    new ArrayList<>(Collections.singletonList(1)),
                    new TightBondAbility(),
                    "/cards/nilfgaard/nilfgaard_nauzicaa_2.jpg");
            return card;
        }
    },
    SIEGE_TECHNICIAN{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Empire Nilfgaardian", 1, "Siege Technician", 0, false,
                    new ArrayList<>(Collections.singletonList(3)),
                    new MedicAbility(),
                    "/cards/nilfgaard/nilfgaard_siege_technician.jpg");
            return card;
        }
    },
    SWEERS{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Empire Nilfgaardian", 1, "Sweers", 2, false,
                    new ArrayList<>(Collections.singletonList(2)),
                    null,
                    "/cards/nilfgaard/nilfgaard_sweers.jpg");
            return card;
        }
    },
    VANHEMAR{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Empire Nilfgaardian", 1, "Vanhemar", 4, false,
                    new ArrayList<>(Collections.singletonList(2)),
                    null,
                    "/cards/nilfgaard/nilfgaard_vanhemar.jpg");
            return card;
        }
    },
    VATTIER_DE_RIDEAUX{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Empire Nilfgaardian", 1, "Vattier de Rideaux", 4, false,
                    new ArrayList<>(Collections.singletonList(1)),
                    new SpyAbility(),
                    "/cards/nilfgaard/nilfgaard_vattier.jpg");
            return card;
        }
    },
    VREEMD{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Empire Nilfgaardian", 1, "Vreemd", 2, false,
                    new ArrayList<>(Collections.singletonList(1)),
                    null,
                    "/cards/nilfgaard/nilfgaard_vreemde.jpg");
            return card;
        }
    },
   DRAUG{
       @Override
       public Card getCard() {
           UnitCard card = new UnitCard("Monsters", 1, "Draug", 10, true,
                   new ArrayList<>(Collections.singletonList(1)),
                   null,
                   "/cards/monsters/monsters_draug.jpg");
           return card;
       }
   },
    LESHEN{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Monsters", 1, "Leshen", 10, true,
                    new ArrayList<>(Collections.singletonList(1)),
                    null,
                    "/cards/monsters/monsters_leshen.jpg");
            return card;
        }
    },
    KAYRAN{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Monsters", 1, "Kayran", 8, true,
                    new ArrayList<>(Arrays.asList(1, 2)),
                    new MoralBoostAbility(),
                    "/cards/monsters/monsters_kayran.jpg");
            return card;
        }
    },
    TOAD{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Monsters", 1, "TOAD", 7, false,
                    new ArrayList<>(Collections.singletonList(2)),
                    new ScorchAbility(),
                    "/cards/monsters/monsters_toad.jpg");
            return card;
        }
    },
    ARACHAS_BEHEMOTH{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Monsters", 1, "Arachas Behemoth", 6, false,
                    new ArrayList<>(Collections.singletonList(3)),
                    new MusterAbility(),
                    "/cards/monsters/monsters_arachas_behemoth.jpg");
            return card;
        }
    },
    CRONE_WEAVESS{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Monsters", 1, "Crone:Weavess", 6, false,
                    new ArrayList<>(Collections.singletonList(1)),
                    new MusterAbility(),
                    "/cards/monsters/monsters_witch_velen_1.jpg");
            return card;
        }
    },
    CRONE_WHISPESS{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Monsters", 1, "Crone:Whispess", 6, false,
                    new ArrayList<>(Collections.singletonList(1)),
                    new MusterAbility(),
                    "/cards/monsters/monsters_witch_velen_2.jpg");
            return card;
        }
    },
    EARTH_ELEMENTAL{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Monsters", 1, "Earth Elemental", 6, false,
                    new ArrayList<>(Collections.singletonList(3)),
                    null,
                    "/cards/monsters/monsters_earth_elemental.jpg");
            return card;
        }
    },
    FIEND{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Monsters", 1, "Fiend", 6, false,
                    new ArrayList<>(Collections.singletonList(1)),
                    null,
                    "/cards/monsters/monsters_fiend.jpg");
            return card;
        }
    },
    FIRE_ELEMENTAL{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Monsters", 1, "Fire Elemental", 6, false,
                    new ArrayList<>(Collections.singletonList(3)),
                    null,
                    "/cards/monsters/monsters_fire_elemental.jpg");
            return card;
        }
    },
    FORKTAIL{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Monsters", 1, "Forktail", 5, false,
                    new ArrayList<>(Collections.singletonList(1)),
                    null,
                    "/cards/monsters/monsters_forktail.jpg");
            return card;
        }
    },
    GRAVE_HAG{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Monsters", 1, "Grave Hag", 5, false,
                    new ArrayList<>(Collections.singletonList(2)),
                    null,
                    "/cards/monsters/monsters_gravehag.jpg");
            return card;
        }
    },
    GRIFFIN{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Monsters", 1, "Griffin", 5, false,
                    new ArrayList<>(Collections.singletonList(1)),
                    null,
                    "/cards/monsters/monsters_griffin.jpg");
            return card;
        }
    },
    ICE_GIANT{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Monsters", 1, "Ice Giant", 5, false,
                    new ArrayList<>(Collections.singletonList(3)),
                    null,
                    "/cards/monsters/monsters_ice_giant.jpg");
            return card;
        }
    },
    PLAGUE_MAIDEN{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Monsters", 1, "Plague Maiden", 5, false,
                    new ArrayList<>(Collections.singletonList(1)),
                    null,
                    "/cards/monsters/monsters_mighty_maiden.jpg");
            return card;
        }
    },
    VAMPIRE_KATAKAN{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Monsters", 1, "Vampire:Katakan", 5, false,
                    new ArrayList<>(Collections.singletonList(1)),
                    new MusterAbility(),
                    "/cards/monsters/monsters_katakan.jpg");
            return card;
        }
    },
    WEREWOLF{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Monsters", 1, "Werwolf", 5, false,
                    new ArrayList<>(Collections.singletonList(1)),
                    null,
                    "/cards/monsters/monsters_werewolf.jpg");
            return card;
        }
    },
    ARACHAS{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Monsters", 3, "Arachas", 4, false,
                    new ArrayList<>(Collections.singletonList(1)),
                    new MusterAbility(),
                    "/cards/monsters/monsters_arachas.jpg");
            return card;
        }
    },
    VAMPIRE_BRUXA{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Monsters", 1, "Vampire:Bruxa", 4, false,
                    new ArrayList<>(Collections.singletonList(1)),
                    new MusterAbility(),
                    "/cards/monsters/monsters_bruxa.jpg");
            return card;
        }
    },
    VAMPIRE_EKIMMARA{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Monsters", 1, "Vampire:Ekimmara", 4, false,
                    new ArrayList<>(Collections.singletonList(1)),
                    new MusterAbility(),
                    "/cards/monsters/monsters_ekkima.jpg");
            return card;
        }
    },
    VAMPIRE_FLEDER{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Monsters", 1, "Vampire:Fleder", 4, false,
                    new ArrayList<>(Collections.singletonList(1)),
                    new MusterAbility(),
                    "/cards/monsters/monsters_fleder.jpg");
            return card;
        }
    },
    VAMPIRE_GARKAIN{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Monsters", 1, "Vampire:Garkain", 4, false,
                    new ArrayList<>(Collections.singletonList(1)),
                    new MusterAbility(),
                    "/cards/monsters/monsters_garkain.jpg");
            return card;
        }
    },
    NEKKER{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Monsters", 3, "Nekker", 2, false,
                    new ArrayList<>(Collections.singletonList(1)),
                    new MusterAbility(),
                    "/cards/monsters/monsters_nekker.jpg");
            return card;
        }
    },
    FOGLET{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Monsters", 1, "Foglet", 2, false,
                    new ArrayList<>(Collections.singletonList(1)),
                    null,
                    "/cards/monsters/monsters_foglet.jpg");
            return card;
        }
    },
    GHOUL{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Monsters", 3, "Ghoul", 1, false,
                    new ArrayList<>(Collections.singletonList(1)),
                    new MusterAbility(),
                    "/cards/monsters/monsters_ghoul.jpg");
            return card;
        }
    },
    COCKATRICE{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Monsters", 1, "Cockatrice", 2, false,
                    new ArrayList<>(Collections.singletonList(2)),
                    null,
                    "/cards/monsters/monsters_cockatrice.jpg");
            return card;
        }
    },
    ENDREGA{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Monsters", 1, "Endrega", 2, false,
                    new ArrayList<>(Collections.singletonList(2)),
                    null,
                    "/cards/monsters/monsters_endrega.jpg");
            return card;
        }
    },
    HARPY{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Monsters", 1, "Harpy", 2, false,
                    new ArrayList<>(Arrays.asList(1, 2)),
                    null,
                    "/cards/monsters/monsters_harpy.jpg");
            return card;
        }
    },
    WYVERN{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Monsters", 1, "Wyvern", 2, false,
                    new ArrayList<>(Collections.singletonList(2)),
                    null,
                    "/cards/monsters/monsters_wyvern.jpg");
            return card;
        }
    },
   BITING_FROST{
       public Card getCard(){
           return new BitingFrost();
       }
   },
    IMPENETRABLE_FOG{
        @Override
        public Card getCard() {
            return new ImpenetrableFog();
        }
    },
    TORRENTIAL_RAIN{
        @Override
        public Card getCard() {
            return new TorrentialRain();
        }
    },
    DECOY{
        @Override
        public Card getCard() {
            SpellCard card = new SpellCard("Neutral", 3, "Decoy", new NullAbility(), "/cards/special/special_decoy.jpg");
            return card;
        }
    },
    DANDELION{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Neutral", 1, "Dandelion", 2, false,
                    new ArrayList<>(Collections.singleton(1)),
                    new HornAbility(),
                    "/cards/neutral/neutral_dandelion.jpg");
            return card;
        }
    },
    EMIEL_REGIS{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Neutral", 1, "Emiel Regis", 5, false,
                    new ArrayList<>(Collections.singleton(1)),
                    null,
                    "/cards/neutral/neutral_emiel.jpg");
            return card;
        }
    },
    GAUNTER_O_DIMM{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Neutral", 1, "Gaunter O'Dimm", 2, false,
                    new ArrayList<>(Collections.singleton(3)),
                    new MusterAbility(),
                    "/cards/neutral/neutral_gaunter_odimm.jpg");
            return card;
        }
    },
    GAUNTER_O_DIMM_DARKNESS{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Neutral", 3, "Gounter O'Dimm Darkness",
                    4, false,
                    new ArrayList<>(Collections.singleton(2)),
                    new MusterAbility(),
                    "/cards/neutral/neutral_gaunter_odimm_darkness.jpg");
            return card;
        }
    },
    MYSTERIOUS_ELF{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Neutral", 1, "Mysterious Elf", 0, true,
                    new ArrayList<>(Collections.singleton(1)),
                    new SpyAbility(),
                    "/cards/neutral/neutral_mysterious_elf.jpg");
            return card;
        }
    },
    OLGIERD_VON_EVERC{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Neutral", 1, "Olgierd Von Everc", 6, false,
                    new ArrayList<>(Arrays.asList(1, 2)),
                    new MoralBoostAbility(),
                    "/cards/neutral/neutral_olgierd.jpg");
            return card;
        }
    },
    TRISS_MERIGOLD{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Neutral", 1, "Triss Merigold", 7, true,
                    new ArrayList<>(Collections.singleton(1)),
                    null,
                    "/cards/neutral/neutral_triss.jpg");
            return card;
        }
    },
    VILLENTRETENMERTH{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Neutral", 1, "Villentretenmerth", 7, false,
                    new ArrayList<>(Collections.singleton(1)),
                    new ScorchAbility(),
                    "/cards/neutral/neutral_villen.jpg");
            return card;
        }
    },
    YENNEFER_OF_VENGERBERG{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Neutral", 1, "Yennefer of Vengerberg",
                    7, true,
                    new ArrayList<>(Collections.singleton(2)),
                    new MedicAbility(),
                    "/cards/neutral/neutral_yennefer.jpg");
            return card;
        }
    },
    ZOLTAN_CHIVAY{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Neutral", 1, "Zoltan Chivay", 5, false,
                    new ArrayList<>(Collections.singleton(1)),
                    null,
                    "/cards/neutral/neutral_zoltan.jpg");
            return card;
        }
    },
    THE_SIEGEMASTER{
        @Override
        public Card getCard() {
            CommanderCard card = new CommanderCard("Northern Realms", 1, "The Siege Master",
                    new SiegeMasterAbility(),
                    "/cards/realms/commanders/realms_foltest_silver.jpg");
            return card;
        }
    },
    THE_STEEL_FORGED{
        public Card getCard() {
            CommanderCard card = new CommanderCard("Northern Realms", 1, "The Steel Forged",
                    new ClearWeatherAbility(),
                    "/cards/realms/commanders/realms_foltest_gold.jpg");
            return card;
        }
    },
    KING_OF_TEMERIA{
        public Card getCard() {
            CommanderCard card = new CommanderCard("Northern Realms", 1, "King of Temeria",
                    new KingOfTemeriaAbility(),
                    "/cards/realms/commanders/realms_foltest_copper.jpg");
            return card;
        }
    },
    LORD_COMMANDER_OF_THE_NORTH{
        public Card getCard() {
            CommanderCard card = new CommanderCard("Northern Realms", 1, "Lord Commander of the North",
                    new LordCommanderOfTheNorthAbility(),
                    "/cards/realms/commanders/realms_foltest_bronze.jpg");
            return card;
        }
    },
    SON_OF_MEDAL{
        public Card getCard() {
            CommanderCard card = new CommanderCard("Northern Realms", 1, "Son Of Medal",
                    new SonOfMedalAbility(),
                    "/cards/realms/commanders/realms_foltest_son_of_medell.jpg");
            return card;
        }
    },
    THE_WHITE_FLAME{
        public Card getCard() {
            CommanderCard card = new CommanderCard("Empire Nilfgaardian", 1, "The White Flame",
                    new TheWhiteFlameAbility(),
                    "/cards/nilfgaard/commanders/nilfgaard_emhyr_silver.jpg");
            return card;
        }
    },
    HIS_IMPERIAL_MAJESTY{
        @Override
        public Card getCard() {
            CommanderCard card = new CommanderCard("Empire Nilfgaardian", 1, "His Imperial Majesty",
                    new HisImperialMajestyAbility(),
                    "/cards/nilfgaard/commanders/nilfgaard_emhyr_copper.jpg");
            return card;
        }
    },
    EMPEROR_OF_NILFGAARD{
        @Override
        public Card getCard() {
            CommanderCard card = new CommanderCard("Empire Nilfgaardian", 1, "Emperor of Nilfgaard",
                    new EmperorOfNilfgaardAbility(),
                    "/cards/nilfgaard/commanders/nilfgaard_emhyr_bronze.jpg");
            return card;
        }
    },
    THE_RELENTLESS{
        @Override
        public Card getCard() {
            CommanderCard card = new CommanderCard("Empire Nilfgaardian", 1, "The Relentless",
                    new TheRelentlessAbility(),
                    "/cards/nilfgaard/commanders/nilfgaard_emhyr_gold.jpg");
            return card;
        }
    },
    INVADER_OF_THE_NORTH{
        @Override
        public Card getCard() {
            CommanderCard card = new CommanderCard("Empire Nilfgaardian", 1, "Invader of the North",
                    new InvaderOfTheNorthAbility(),
                    "/cards/nilfgaard/commanders/nilfgaard_emhyr_invader_of_the_north.jpg");
            return card;
        }
    },
    BRINGER_OF_DEATH{
        @Override
        public Card getCard() {
            CommanderCard card = new CommanderCard("Monsters", 1, "Bringer Of Death",
                    new BringerOfDeathAbility(),
                    "/cards/monsters/commanders/monsters_eredin_silver.jpg");
            return card;
        }
    },
    KING_OF_THE_WILD_HUNT{
        @Override
        public Card getCard() {
            CommanderCard card = new CommanderCard("Monsters", 1, "King Of the Wild Hunt",
                    new KingOfTheWildHuntAbility(),
                    "/cards/monsters/commanders/monsters_eredin_bronze.jpg");
            return card;
        }
    },
    DESTROYER_OF_WORLDS{
        @Override
        public Card getCard() {
            CommanderCard card = new CommanderCard("Monsters", 1, "Destroyer of Worlds",
                    new DestroyerOfWorldsAbility(),
                    "/cards/monsters/commanders/monsters_eredin_gold.jpg");
            return card;
        }
    },
    COMMANDER_OF_THE_RED_RIDERS{
        @Override
        public Card getCard() {
            CommanderCard card = new CommanderCard("Monsters", 1, "Commander Of Red Riders",
                    new CommanderOfRedRidersAbility(),
                    "/cards/monsters/commanders/monsters_eredin_copper.jpg");
            return card;
        }
    },
    THE_TREACHEROUS{
        @Override
        public Card getCard() {
            CommanderCard card = new CommanderCard("Monsters", 1, "The Treacherous",
                    new TreacherousAbility(),
                    "/cards/monsters/commanders/monsters_eredin_the_treacherous.jpg");
            return card;
        }
    },
    QUEEN_OF_DOL_BLATHANNA{
        @Override
        public Card getCard() {
            CommanderCard card = new CommanderCard("Scoia'tael", 1, "Queen of dol Blathanna",
                    new QueenOfDolBlathannaAbility(),
                    "/cards/scoiatael/commanders/scoiatael_francesca_silver.jpg");
            return card;
        }
    },
    THE_BEAUTIFUL{
        @Override
        public Card getCard() {
            CommanderCard card = new CommanderCard("Scoia'tael", 1, "The Beautiful",
                    new BeautifulAbility(),
                    "/cards/scoiatael/commanders/scoiatael_francesca_gold.jpg");
            return card;
        }
    },
    DAISY_OF_THE_VALLEY{
        @Override
        public Card getCard() {
            CommanderCard card = new CommanderCard("Scoia'tael", 1, "Daisy of the Valley",
                    new DaisyOfTheValleyAbility(),
                    "/cards/scoiatael/commanders/scoiatael_francesca_copper.jpg");
            return card;
        }
    },
    PUREBLOOD_ELF{
        @Override
        public Card getCard() {
            CommanderCard card = new CommanderCard("Scoia'tael", 1, "Pureblood Elf",
                    new PureBloodElfAbility(),
                    "/cards/scoiatael/commanders/scoiatael_francesca_bronze.jpg");
            return card;
        }
    },
    HOPE_OF_THE_AEN_SEIDHE{
        @Override
        public Card getCard() {
            CommanderCard card = new CommanderCard("Scoia'tael", 1, "Hope of the Aen Seidhe",
                    new HopeOfTheAenSeidheAbility(),
                    "/cards/scoiatael/commanders/scoiatael_francesca_hope_of_the_aen_seidhe.jpg");
            return card;
        }
    },
    CRACH_AN_CRAITE{
        @Override
        public Card getCard() {
            CommanderCard card = new CommanderCard("Skellige", 1, "Crach an Craite",
                    new CrachAnCraiteAbility(),
                    "/cards/skellige/commanders/skellige_crach_an_craite.jpg");
            return card;
        }
    },
    KING_BRAN{
        @Override
        public Card getCard() {
            CommanderCard card = new CommanderCard("Skellige", 1, "King Bran",
                    new KingBranAbility(),
                    "/cards/skellige/commanders/skellige_king_bran.jpg");
            return card; 
        }
    },
    BLUEBOY_LUGOS{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Skellige", 1,
                    "Blueboy Lugos", 6, false,
                    new ArrayList<>(Collections.singletonList(1)),
                    new NullAbility(),
                    "/cards/skellige/skellige_blueboy.jpg");
            return card;
        }
    },
    CLAN_TORDARROCH_ARMORSMOTH {
        public Card getCard() {
            UnitCard card = new UnitCard("Skellige", 1
                    , "Clan Tordarroch Armorsmith"
                    , 4, false,
                    new ArrayList<>(Collections.singletonList(1)),
                    new NullAbility(),
                    "/cards/skellige/skellige_tordarroch.jpg");
            return card;
        }
    },
    HOLGER_BLACKHAND{
        public Card getCard() {
            UnitCard card = new UnitCard("Skellige", 1,
                    "Holger Blackhand", 4, false,
                    new ArrayList<>(Collections.singletonList(3)),
                    new NullAbility(),
                    "/cards/skellige/skellige_holger.jpg");
            return card;
        }
    },
    IORVETH{
        public Card getCard() {
            UnitCard card = new UnitCard("Scoia'tael", 1,
                    "Iorveth", 10, true,
                    new ArrayList<>(Collections.singletonList(2)),
                    new NullAbility(),
                    "/cards/scoiatael/scoiatael_iorveth.jpg");
            return card;
        }
    },
    FILAVANDREL{
        public Card getCard() {
            UnitCard card = new UnitCard("Scoia'tael", 1,
                    "Filavandrel", 6, false,
                    new ArrayList<>(Arrays.asList(1, 2)),
                    new NullAbility(),
                    "/cards/scoiatael/scoiatael_filavandrel.jpg");
            return card;
        }
    },
    MAHAKAMAN_DEFENDER{
        public Card getCard() {
            UnitCard card = new UnitCard("Scoia'tael", 1,
                    "Mahakaman Defender", 5, false,
                    new ArrayList<>(Collections.singletonList(1)),
                    new NullAbility(),
                    "/cards/scoiatael/scoiatael_mahakam.jpg");
            return card;
        }
    },
    BARCLAY_ELS{
        public Card getCard() {
            UnitCard card = new UnitCard("Scoia'tael", 1,
                    "Barclay Els", 6, false,
                    new ArrayList<>(Arrays.asList(1, 2)),
                    new NullAbility(),
                    "/cards/scoiatael/scoiatael_barclay.jpg");
            return card;
        }
    },
    BLUE_STRIPES_COMMANDO{
        @Override
        public Card getCard() {
            UnitCard card = new UnitCard("Northern Realms",
                    3,
                    "Blue Stripes Commando", 4, false,
                    new ArrayList<>(Collections.singletonList(1)),
                    new TightBondAbility(),
                    "/cards/realms/realms_blue_stripes.jpg");
            return card;
        }
    },
    DETHMOLD{
        public Card getCard() {
            UnitCard card = new UnitCard("Northern Realms",
                    1,
                    "Dethmold", 6, false,
                    new ArrayList<>(Collections.singletonList(2)),
                    new NullAbility(),
                    "/cards/realms/realms_dethmold.jpg");
            return card;
        }
    },
    KEIRA_METZ{
        public UnitCard getCard() {
            UnitCard card = new UnitCard("Northern Realms",
                    1,
                    "Keira Metz", 5, false,
                    new ArrayList<>(Collections.singletonList(2)),
                    new NullAbility(),
                    "/cards/realms/realms_keira.jpg");
            return card;
        }
    },
    SHELDON_SKAGGS {
        public UnitCard getCard() {
            UnitCard card = new UnitCard("Northern Realms",
                    1,
                    "Sheldon Skaggs", 4, false,
                    new ArrayList<>(Collections.singletonList(2)),
                    new NullAbility(),
                    "/cards/realms/realms_sheldon.jpg");
            return card;
        }
    },
    TREBUCHET{
        public UnitCard getCard() {
            UnitCard card = new UnitCard("Northern Realms",
                    2,
                    "Trebuchet", 6, false,
                    new ArrayList<>(Collections.singletonList(3)),
                    new NullAbility(),
                    "/cards/realms/realms_trebuchet.jpg");
            return card;
        }
    },
    SHILARD_FITZ_OESTERLEN{
        public UnitCard getCard() {
            UnitCard card = new UnitCard("Nilfgaard",
                    1,
                    "Shilard Fitz-Oesterle", 7, false,
                    new ArrayList<>(Collections.singletonList(1)),
                    new SpyAbility(),
                    "/cards/nilfgaard/nilfgaard_shilard.jpg");
            return card;
        }
    },
    ASSIRE_VAR_ANAHID{
        public UnitCard getCard() {
            UnitCard card = new UnitCard("Nilfgaard",
                    1,
                    "Assire var Anahid", 6, false,
                    new ArrayList<>(Collections.singletonList(2)),
                    new NullAbility(),
                    "/cards/nilfgaard/nilfgaard_assire.jpg");
            return card;
        }
    },
    HEAVY_ZERRIKANIAN_FIRE_SCORPION{
        public UnitCard getCard() {
            UnitCard card = new UnitCard("Nilfgaard",
                    1,
                    "Heavy Zerrikanian Fire Scorpion", 10, false,
                    new ArrayList<>(Collections.singletonList(3)),
                    new NullAbility(),
                    "/cards/nilfgaard/nilfgaard_heavy_zerri.jpg");
            return card;
        }
    },
    ALBRICH{
        public UnitCard getCard() {
            UnitCard card = new UnitCard("Nilfgaard",
                    1,
                    "Albrich", 2, false,
                    new ArrayList<>(Collections.singletonList(2)),
                    new NullAbility(),
                    "/cards/nilfgaard/nilfgaard_albrich.jpg");
            return card;
        }
    },
    LETHO_OF_GULET{
        public UnitCard getCard() {
            UnitCard card = new UnitCard("Nilfgaard",
                    1,
                    "Letho of Gulet", 10, true,
                    new ArrayList<>(Collections.singletonList(1)),
                    new NullAbility(),
                    "/cards/nilfgaard/nilfgaard_letho.jpg");
            return card;
        }
    },
    RAINFARN{
        public UnitCard getCard() {
            UnitCard card = new UnitCard("Nilfgaard",
                    1,
                    "Rainfarn", 4, false,
                    new ArrayList<>(Collections.singletonList(1)),
                    new NullAbility(),
                    "/cards/nilfgaard/nilfgaard_rainfarn.jpg");
            return card;
        }
    },
    IMLERITH{
        public UnitCard getCard() {
            UnitCard card = new UnitCard("Monsters",
                    1,
                    "Imlerith", 10, true,
                    new ArrayList<>(Collections.singletonList(1)),
                    new NullAbility(),
                    "/cards/monsters/monsters_imlerith.jpg");
            return card;
        }
    },
    CRONE_BREWESS{
        public UnitCard getCard() {
            UnitCard card = new UnitCard("Monsters",
                    1,
                    "Crone: Brewess", 6, false,
                    new ArrayList<>(Collections.singletonList(1)),
                    new MusterAbility(),
                    "/cards/monsters/brewess.jpg");
            return card;
        }
    },
    FRIGHTENER{
        public UnitCard getCard() {
            UnitCard card = new UnitCard("Monsters",
                    1,
                    "Frightener", 5, false,
                    new ArrayList<>(Collections.singletonList(1)),
                    new NullAbility(),
                    "/cards/monsters/monsters_frightener.jpg");
            return card;
        }
    },
    BOTCHLING{
        public UnitCard getCard() {
            UnitCard card = new UnitCard("Monsters",
                    1,
                    "Botchling", 4, false,
                    new ArrayList<>(Collections.singletonList(1)),
                    new MusterAbility(),
                    "/cards/monsters/botchling.jpg");
            return card;
        }
    },
    CALAENO_HARPY{
        public UnitCard getCard() {
            UnitCard card = new UnitCard("Monsters",
                    1,
                    "Celaeno Harpy", 2, false,
                    new ArrayList<>(Arrays.asList(1, 2)),
                    new NullAbility(),
                    "/cards/monsters/monsters_celaeno_harpy.jpg");
            return card;
        }
    },
    GARGOYLE{
        public UnitCard getCard() {
            UnitCard card = new UnitCard("Monsters",
                    1,
                    "Gargoyle", 2, false,
                    new ArrayList<>(Collections.singletonList(2)),
                    new NullAbility(),
                    "/cards/monsters/monsters_gargoyle.jpg");
            return card;
        }
    },
    SKELLIGE_STORM{
        @Override
        public Card getCard() {
            return new SkelligeStorm();
        }
    },
    CLEAR_WEATHER{
        @Override
        public Card getCard() {
            return new ClearWeather();
        }
    },
    SCORCH{
        @Override
        public Card getCard() {
            SpellCard card = new SpellCard("Neutral",
                    3, "Scorch", new ScorchAbility(),
                    "/cards/neutral/special_scorch.jpg");
            return card;
        }
    },
    COMMANDER_HORN{
       //only one of its kind can be placed in a row.
        public Card getCard() {
            SpellCard card = new SpellCard("Neutral",
                    3, "Commander's horn", new HornAbility(),
                    "/cards/neutral/special_horn.jpg");
            return card;
        }
    },
    COW{
        public UnitCard getCard() {
            UnitCard card = new UnitCard("Neutral",
                    1,
                    "Cow", 0, false,
                    new ArrayList<>(Collections.singletonList(2)),
                    new TransformerAbility(),
                    "/cards/neutral/neutral_cow.jpg");
            return card;
        }
    },
    GERALT_OF_RIVIA{
        public UnitCard getCard() {
            UnitCard card = new UnitCard("Neutral",
                    1,
                    "Geralt of Rivia", 15, true,
                    new ArrayList<>(Collections.singletonList(1)),
                    null,
                    "/cards/neutral/neutral_geralt.jpg");
            return card;
        }
    },
    VESEMIR{
        public UnitCard getCard() {
            UnitCard card = new UnitCard("Neutral",
                    1,
                    "Vesemir", 6, false,
                    new ArrayList<>(Collections.singletonList(1)),
                    null,
                    "/cards/neutral/neutral_vesemir.jpg");
            return card;
        }
    };
    public abstract Card getCard();
}
