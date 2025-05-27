package data.scripts;
import java.awt.Color;
import java.util.List;

//import com.fs.starfarer.api.impl.campaign.ids.Factions;
//import data.strings.descriptions;
import data.scripts.relations.LIB_Relations;
//import data.scripts.campaign.econ.DR_industries;
import com.fs.starfarer.api.campaign.*;
import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.Industry;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.EconomyAPI;
import com.fs.starfarer.api.campaign.econ.Industry;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.impl.campaign.econ.impl.InstallableItemEffect;
import com.fs.starfarer.api.impl.campaign.econ.impl.ItemEffectsRepo;
import com.fs.starfarer.api.impl.campaign.ids.*;

public class LIB_ModPlugin extends BaseModPlugin {

    @Override
    public void onNewGame() {
        libertyGen(); // star
        oportunidadGen();//  ming world desert/arid world
        isencaoGen();// heavy industy world jungle 
        Rapture(); // farning world aquatic
        factionRelations(); // sets faction relations 
        tlhabGen(); // fuel world cryvoulcanic
	    frigjoringGen(); // refinery world tundra
	//kulug();
	//Mirfak();
	//wangMangGen();
	//Diocletian();
	//wangMangTwo();
		
    }
    private void libertyGen() {
        StarSystemAPI system = Global.getSector().createStarSystem("liberty");
        PlanetAPI star = system.initStar("Liberty", "star_yellow", 800,-3200, -1000, 250);
         //SectorEntityToken relay = system.addCustomEntity("Liberty_relay",null, "comm_relay_makeshift","FFA");
        //relay.setCircularOrbitPointingDown(star, 230, 6500, 265f);
        system.autogenerateHyperspaceJumpPoints(true, true);
        system.updateAllOrbits();

    system.addAsteroidBelt(star, 100, 5000f, 250f, 1000, 250, Terrain.ASTEROID_BELT, "Astroid Belt");
	system.addRingBand(star, "misc", "rings_asteroids0", 256f, 0, Color.white, 256f, 5000f, 226f, null, null);

        system.addAsteroidBelt(star, 100, 10000f, 250f, 1000, 250, Terrain.ASTEROID_BELT, "Astroid Belt");
	system.addRingBand(star, "misc", "rings_asteroids0", 256f, 0, Color.white, 256f, 10000f, 226f, null, null);
        SectorEntityToken relay = system.addCustomEntity("Liberty_relay",null, "comm_relay_makeshift","FFA");
        relay.setCircularOrbitPointingDown(star, 230, 6500, 265f);
     }
    private void oportunidadGen() {
        StarSystemAPI system = Global.getSector().getStarSystem("liberty");
        SectorEntityToken star = system.getStar();
        PlanetAPI planet = system.addPlanet("Oportunidad", 
		star, 
		"Oportunidad", 
		"desert", 
		90, 
		150, // planet size
		2000, // distance from star
		50); // days to rotate around sun
        MarketAPI market = Global.getFactory().createMarket(
                "Oportunidad_market", //market id
                planet.getName(), //market display name, usually the planet's name
                5 //market size
        );


        planet.setMarket(market);
        //Market global property settings
        market.setPrimaryEntity(planet);

        market.setSurveyLevel(MarketAPI.SurveyLevel.FULL);

        planet.setFaction("FFA");
        //planet.setCustomDescriptionId("DR_planet_new_terra");
        market.setPlanetConditionMarketOnly(false); //We are going to turn this into a proper colony and not just a "surface only".
        market.setFactionId("FFA");
        market.addIndustry(Industries.POPULATION);
        market.addCondition(Conditions.POPULATION_5);
        market.setSize(5);
        market.addSubmarket(Submarkets.SUBMARKET_STORAGE);
        market.addSubmarket(Submarkets.SUBMARKET_BLACK);
        market.addSubmarket(Submarkets.SUBMARKET_OPEN);
      
        market.addCondition(Conditions.VERY_HOT);
		market.addCondition(Conditions.DISSIDENT);
       market.addCondition(Conditions.RUINS_SCATTERED);
        market.addCondition(Conditions.ORE_SPARSE);
        market.addCondition(Conditions.VICE_DEMAND);
		//market.addCondition(Conditions.POLLUTION);
        market.addCondition(Conditions.RARE_ORE_ULTRARICH);
        //market.addCondition(Conditions.INIMICAL_BIOSPHERE);
        market.addCondition(Conditions.FRONTIER);
        market.addCondition(Conditions.RARE_ORE_ULTRARICH);
        market.addIndustry(Industries.WAYSTATION);

        

        // industries
        market.addIndustry(Industries.MEGAPORT);
        market.addIndustry(Industries.STARFORTRESS_MID);
	    //market.addIndustry(DR_industries.DR_STATION3);
        market.addIndustry(Industries.PATROLHQ);
        market.addIndustry(Industries.GROUNDDEFENSES); //GroundDefenses
        market.addIndustry(Industries.MINING);
       // market.addIndustry(Industries.HEAVYINDUSTRY);
       market.addIndustry(Industries.LIGHTINDUSTRY);
       market.addIndustry(Industries.COMMERCE);

        market.getIndustry(Industries.MINING).setAICoreId(Commodities.ALPHA_CORE);
        
       market.getIndustry(Industries.GROUNDDEFENSES).setAICoreId(Commodities.GAMMA_CORE);
		market.getIndustry(Industries.POPULATION).setAICoreId(Commodities.GAMMA_CORE);
		market.getIndustry(Industries.STARFORTRESS_MID).setAICoreId(Commodities.GAMMA_CORE);
       market.getIndustry(Industries.PATROLHQ).setAICoreId(Commodities.GAMMA_CORE);
		market.getIndustry(Industries.COMMERCE).setAICoreId(Commodities.GAMMA_CORE);
        market.getIndustry(Industries.LIGHTINDUSTRY).setAICoreId(Commodities.BETA_CORE);
		market.getIndustry(Industries.WAYSTATION).setAICoreId(Commodities.GAMMA_CORE);
       market.getIndustry(Industries.MEGAPORT).setAICoreId(Commodities.GAMMA_CORE);

        //Industry MaistrePATROLHQ = market.getIndustry(Industries.PATROLHQ);// grabs the orbital 
       // MaistrePATROLHQ.setSpecialItem(new SpecialItemData(Items.CRYOARITHMETIC_ENGINE , null));// adds a cryo engine
       // Industry MaistreHEAVYBATTERIES = market.getIndustry(Industries.HEAVYBATTERIES);// grabs the heavy batteries
       // MaistreHEAVYBATTERIES.setSpecialItem(new SpecialItemData(Items.DRONE_REPLICATOR , null));// adds a drone replicator

        //Industry MaistreWorks = market.getIndustry(Industries.HEAVYINDUSTRY);// grabs the orbital 
        //MaistreWorks.setSpecialItem(new SpecialItemData(Items.CORRUPTED_NANOFORGE, null));// adds a corrupted nano forge

        //Those rascally Tritachyon have set their tariffs to 15%!
        market.getTariff().modifyFlat("generator", 0.15f);
        //planet.setMarket(market);
        EconomyAPI globalEconomy = Global.getSector().getEconomy();
        globalEconomy.addMarket(
                market, //The market to add obviously!
                true //The "withJunkAndChatter" flag. It will add space debris in orbit and radio chatter sound effects.*
        );
       // planet.setCustomDescriptionId("DR_planet_Maistre"); // adds planet description
		//planet.setInteractionImage("illustrations", "Maistre_planet");//adds illustration
        system.updateAllOrbits();
    }

    private void isencaoGen() {
        StarSystemAPI system = Global.getSector().getStarSystem("liberty");
        SectorEntityToken star = system.getStar();
        PlanetAPI planet = system.addPlanet("Isencao", star, "isencao", "jungle", 180, 180, 4000, 100);
        MarketAPI market = Global.getFactory().createMarket(
                "isencao_market", //market id
                planet.getName(), //market display name, usually the planet's name
                6 //market size
        );


        planet.setMarket(market);
        //Market global property settings
        market.setPrimaryEntity(planet);

        market.setSurveyLevel(MarketAPI.SurveyLevel.FULL);

        planet.setFaction("FFA");

        market.setPlanetConditionMarketOnly(false); //We are going to turn this into a proper colony and not just a "surface only".
        market.setFactionId("FFA");
        market.addIndustry(Industries.POPULATION);
        market.addCondition(Conditions.POPULATION_4);
        market.setSize(6);
        market.addSubmarket(Submarkets.SUBMARKET_STORAGE);
        market.addSubmarket(Submarkets.SUBMARKET_BLACK);
        market.addSubmarket(Submarkets.SUBMARKET_OPEN);
        market.addCondition(Conditions.HABITABLE);
        market.addCondition(Conditions.FRONTIER);
        market.addCondition(Conditions.HOT);
        market.addCondition(Conditions.DISSIDENT);
        market.addCondition(Conditions.ORE_SPARSE);
        market.addCondition(Conditions.ORGANICS_ABUNDANT);
        market.addCondition(Conditions.INDUSTRIAL_POLITY);
        market.addCondition(Conditions.FARMLAND_POOR);
        market.addCondition(Conditions.POLLUTION);
        market.addCondition(Conditions.INIMICAL_BIOSPHERE);
        market.addCondition(Conditions.VICE_DEMAND);

        market.addIndustry(Industries.MEGAPORT);
        market.addIndustry(Industries.STARFORTRESS_MID);
        market.addIndustry(Industries.PATROLHQ);
        //market.addIndustry(Industries.MILITARYBASE);
        market.addIndustry(Industries.HEAVYBATTERIES);
        market.addIndustry(Industries.ORBITALWORKS);
        market.addIndustry(Industries.WAYSTATION);
        market.addIndustry(Industries.LIGHTINDUSTRY);
        market.addIndustry(Industries.COMMERCE);
        market.addIndustry(Industries.MINING);

        
         market.getIndustry(Industries.ORBITALWORKS).setAICoreId(Commodities.ALPHA_CORE);
         market.getIndustry(Industries.LIGHTINDUSTRY).setAICoreId(Commodities.ALPHA_CORE);
        
       market.getIndustry(Industries.HEAVYBATTERIES).setAICoreId(Commodities.GAMMA_CORE);
		market.getIndustry(Industries.POPULATION).setAICoreId(Commodities.GAMMA_CORE);
		market.getIndustry(Industries.STARFORTRESS_MID).setAICoreId(Commodities.GAMMA_CORE);
       market.getIndustry(Industries.PATROLHQ).setAICoreId(Commodities.GAMMA_CORE);
		market.getIndustry(Industries.COMMERCE).setAICoreId(Commodities.GAMMA_CORE);
        //market.getIndustry(Industries.LIGHTINDUSTRY).setAICoreId(Commodities.BETA_CORE);
		market.getIndustry(Industries.WAYSTATION).setAICoreId(Commodities.GAMMA_CORE);
       market.getIndustry(Industries.MEGAPORT).setAICoreId(Commodities.GAMMA_CORE);


        //add special items to industries WIP

         Industry ilynOrbitalWorks = market.getIndustry(Industries.ORBITALWORKS);// grabs the orbital 
         ilynOrbitalWorks.setSpecialItem(new SpecialItemData(Items.CORRUPTED_NANOFORGE, null));// adds a corrupted nano forge

        //Those rascally Tritachyon have set their tariffs to 15%!
        market.getTariff().modifyFlat("generator", 0.15f);
        //planet.setMarket(market);
        EconomyAPI globalEconomy = Global.getSector().getEconomy();
        globalEconomy.addMarket(
                market, //The market to add obviously!
                true //The "withJunkAndChatter" flag. It will add space debris in orbit and radio chatter sound effects.*
        );
       // planet.setCustomDescriptionId("DR_planet_Ilyin"); // adds planet description
		//planet.setInteractionImage("illustrations", "illyn_planet");//adds illustration
        system.updateAllOrbits();

    } 
    private void Rapture(){
        StarSystemAPI system = Global.getSector().getStarSystem("liberty");
        SectorEntityToken star = system.getStar();
        PlanetAPI planet = system.addPlanet("Rapture", star, "Rapture","water", 270, 180, 6004, 140);
        MarketAPI market = Global.getFactory().createMarket(
                "Rapture_market", //market id
                planet.getName(), //market display name, usually the planet's name
                6 //market size
        );


        planet.setMarket(market);
        //Market global property settings
        market.setPrimaryEntity(planet);

        market.setSurveyLevel(MarketAPI.SurveyLevel.FULL);

        planet.setFaction("FFA");

        market.setPlanetConditionMarketOnly(false); //We are going to turn this into a proper colony and not just a "surface only".
        market.setFactionId("FFA");
        market.addIndustry(Industries.POPULATION);
        market.addCondition(Conditions.POPULATION_6);
        market.setSize(6);
        market.addSubmarket(Submarkets.SUBMARKET_STORAGE);
        market.addSubmarket(Submarkets.SUBMARKET_BLACK);
        market.addSubmarket(Submarkets.SUBMARKET_OPEN);

        market.addCondition(Conditions.HABITABLE);
        market.addCondition(Conditions.WATER_SURFACE);
        market.addCondition(Conditions.REGIONAL_CAPITAL);
        market.addCondition(Conditions.FRONTIER);
        market.addCondition(Conditions.DISSIDENT);
        //market.addCondition(Conditions.RUINS_WIDESPREAD);
        market.addCondition(Conditions.ORE_SPARSE);
        market.addCondition(Conditions.VICE_DEMAND);

        // industries
        market.addIndustry(Industries.MEGAPORT);
        market.addIndustry(Industries.STARFORTRESS_MID);
        market.addIndustry(Industries.HIGHCOMMAND);//highcommand
        //market.addIndustry(Industries.HEAVYINDUSTRY);
        
        market.addIndustry(Industries.WAYSTATION);
        market.addIndustry(Industries.AQUACULTURE); //aquaculture
        market.addIndustry(Industries.LIGHTINDUSTRY);
        market.addIndustry(Industries.COMMERCE);
        market.addIndustry(Industries.GROUNDDEFENSES); //GroundDefenses


         market.getIndustry(Industries.AQUACULTURE).setAICoreId(Commodities.ALPHA_CORE);
        
        market.getIndustry(Industries.GROUNDDEFENSES).setAICoreId(Commodities.GAMMA_CORE);
	 	market.getIndustry(Industries.POPULATION).setAICoreId(Commodities.GAMMA_CORE);
	 	market.getIndustry(Industries.STARFORTRESS_MID).setAICoreId(Commodities.GAMMA_CORE);
        market.getIndustry(Industries.HIGHCOMMAND).setAICoreId(Commodities.ALPHA_CORE);
		market.getIndustry(Industries.COMMERCE).setAICoreId(Commodities.GAMMA_CORE);
        market.getIndustry(Industries.LIGHTINDUSTRY).setAICoreId(Commodities.BETA_CORE);
	 	market.getIndustry(Industries.WAYSTATION).setAICoreId(Commodities.GAMMA_CORE);
        market.getIndustry(Industries.MEGAPORT).setAICoreId(Commodities.GAMMA_CORE);

        market.getTariff().modifyFlat("generator", 0.15f);
        //planet.setMarket(market);
        EconomyAPI globalEconomy = Global.getSector().getEconomy();
		//planet.setInteractionImage("illustrations", "bombard_tactical_result");//adds illustration
        globalEconomy.addMarket(
                market, //The market to add obviously!
                true //The "withJunkAndChatter" flag. It will add space debris in orbit and radio chatter sound effects.*
        );
        //planet.setCustomDescriptionId("DR_planet_Burke"); // adds planet description
        system.updateAllOrbits();

    }
    public void factionRelations() {
        LIB_Relations.generate(Global.getSector());

    }

private void tlhabGen(){
    StarSystemAPI system = Global.getSector().getStarSystem("liberty");
    SectorEntityToken star = system.getStar();
    PlanetAPI planet = system.addPlanet("Tlhab", star, "tlhab","cryovolcanic", 270, 180, 12000, 180);
    MarketAPI market = Global.getFactory().createMarket(
            "tlhab_market", //market id
            planet.getName(), //market display name, usually the planet's name
            5 //market size
    );


    planet.setMarket(market);
    //Market global property settings
    market.setPrimaryEntity(planet);

    market.setSurveyLevel(MarketAPI.SurveyLevel.FULL);

    planet.setFaction("FFA");

    market.setPlanetConditionMarketOnly(false); //We are going to turn this into a proper colony and not just a "surface only".
    market.setFactionId("FFA");
    market.addIndustry(Industries.POPULATION);
    market.addCondition(Conditions.POPULATION_5);
    market.setSize(5);
    market.addSubmarket(Submarkets.SUBMARKET_STORAGE);
    market.addSubmarket(Submarkets.SUBMARKET_BLACK);
    market.addSubmarket(Submarkets.SUBMARKET_OPEN);
    market.addCondition(Conditions.VERY_COLD);
    //market.addCondition(Conditions.HABITABLE);
     market.addCondition(Conditions.VOLATILES_DIFFUSE);
    market.addCondition(Conditions.FRONTIER);
    market.addCondition(Conditions.DISSIDENT);
    market.addCondition(Conditions.RARE_ORE_SPARSE);
    market.addCondition(Conditions.ORE_SPARSE);
    market.addCondition(Conditions.EXTREME_WEATHER);
    market.addCondition(Conditions.VICE_DEMAND);

    // industries
    market.addIndustry(Industries.MEGAPORT);
    market.addIndustry(Industries.STARFORTRESS_MID);
    market.addIndustry(Industries.PATROLHQ);
    //market.addIndustry(Industries.HEAVYINDUSTRY);
    
    market.addIndustry(Industries.WAYSTATION);
   //market.addIndustry(Industries.AQUACULTURE); //aquaculture
    market.addIndustry(Industries.LIGHTINDUSTRY);
    market.addIndustry(Industries.COMMERCE);
    market.addIndustry(Industries.MINING);
    market.addIndustry(Industries.GROUNDDEFENSES); //GroundDefenses

         market.getIndustry(Industries.MINING).setAICoreId(Commodities.ALPHA_CORE);
        
        market.getIndustry(Industries.GROUNDDEFENSES).setAICoreId(Commodities.GAMMA_CORE);
	 	market.getIndustry(Industries.POPULATION).setAICoreId(Commodities.GAMMA_CORE);
	 	market.getIndustry(Industries.STARFORTRESS_MID).setAICoreId(Commodities.GAMMA_CORE);
        market.getIndustry(Industries.PATROLHQ).setAICoreId(Commodities.BETA_CORE);
		market.getIndustry(Industries.COMMERCE).setAICoreId(Commodities.GAMMA_CORE);
        market.getIndustry(Industries.LIGHTINDUSTRY).setAICoreId(Commodities.BETA_CORE);
	 	market.getIndustry(Industries.WAYSTATION).setAICoreId(Commodities.GAMMA_CORE);
        market.getIndustry(Industries.MEGAPORT).setAICoreId(Commodities.GAMMA_CORE);


    market.getTariff().modifyFlat("generator", 0.15f);
    //planet.setMarket(market);
    EconomyAPI globalEconomy = Global.getSector().getEconomy();
    //planet.setInteractionImage("illustrations", "bombard_tactical_result");//adds illustration
    globalEconomy.addMarket(
            market, //The market to add obviously!
            true //The "withJunkAndChatter" flag. It will add space debris in orbit and radio chatter sound effects.*
    );
    //planet.setCustomDescriptionId("DR_planet_Burke"); // adds planet description
    system.updateAllOrbits();

}
private void frigjoringGen(){
    StarSystemAPI system = Global.getSector().getStarSystem("liberty");
    SectorEntityToken star = system.getStar();
    PlanetAPI planet = system.addPlanet("Frigjoring", star, "frigjoring","tundra", 270, 180, 9000, 180);
    MarketAPI market = Global.getFactory().createMarket(
            "frigjoring_market", //market id
            planet.getName(), //market display name, usually the planet's name
            6 //market size
    );


    planet.setMarket(market);
    //Market global property settings
    market.setPrimaryEntity(planet);

    market.setSurveyLevel(MarketAPI.SurveyLevel.FULL);

    planet.setFaction("FFA");

    market.setPlanetConditionMarketOnly(false); //We are going to turn this into a proper colony and not just a "surface only".
    market.setFactionId("FFA");
    market.addIndustry(Industries.POPULATION);
    market.addCondition(Conditions.POPULATION_6);
    market.setSize(6);
    market.addSubmarket(Submarkets.SUBMARKET_STORAGE);
    market.addSubmarket(Submarkets.SUBMARKET_BLACK);
    market.addSubmarket(Submarkets.SUBMARKET_OPEN);
    market.addCondition(Conditions.COLD);
    market.addCondition(Conditions.HABITABLE);
    market.addCondition(Conditions.FRONTIER);
    market.addCondition(Conditions.DISSIDENT);
    market.addCondition(Conditions.RARE_ORE_SPARSE);
    market.addCondition(Conditions.ORGANICS_TRACE);
    market.addCondition(Conditions.FARMLAND_POOR);
    market.addCondition(Conditions.VICE_DEMAND);

    // industries
    market.addIndustry(Industries.MEGAPORT);
    market.addIndustry(Industries.STARFORTRESS_MID);
    market.addIndustry(Industries.PATROLHQ);
    market.addIndustry(Industries.FUELPROD);
    market.addIndustry(Industries.WAYSTATION);
    market.addIndustry(Industries.LIGHTINDUSTRY);
    market.addIndustry(Industries.COMMERCE);
    market.addIndustry(Industries.REFINING);
    market.addIndustry(Industries.GROUNDDEFENSES); //GroundDefenses


         market.getIndustry(Industries.REFINING).setAICoreId(Commodities.ALPHA_CORE);
         market.getIndustry(Industries.FUELPROD).setAICoreId(Commodities.ALPHA_CORE);

        market.getIndustry(Industries.GROUNDDEFENSES).setAICoreId(Commodities.GAMMA_CORE);
	 	market.getIndustry(Industries.POPULATION).setAICoreId(Commodities.GAMMA_CORE);
	 	market.getIndustry(Industries.STARFORTRESS_MID).setAICoreId(Commodities.GAMMA_CORE);
        market.getIndustry(Industries.PATROLHQ).setAICoreId(Commodities.BETA_CORE);
		market.getIndustry(Industries.COMMERCE).setAICoreId(Commodities.GAMMA_CORE);
        market.getIndustry(Industries.LIGHTINDUSTRY).setAICoreId(Commodities.BETA_CORE);
	 	market.getIndustry(Industries.WAYSTATION).setAICoreId(Commodities.GAMMA_CORE);
        market.getIndustry(Industries.MEGAPORT).setAICoreId(Commodities.GAMMA_CORE);

    market.getTariff().modifyFlat("generator", 0.15f);
    //planet.setMarket(market);
    EconomyAPI globalEconomy = Global.getSector().getEconomy();
    //planet.setInteractionImage("illustrations", "bombard_tactical_result");//adds illustration
    globalEconomy.addMarket(
            market, //The market to add obviously!
            true //The "withJunkAndChatter" flag. It will add space debris in orbit and radio chatter sound effects.*
    );
    //planet.setCustomDescriptionId("DR_planet_Burke"); // adds planet description
    system.updateAllOrbits();

}
}