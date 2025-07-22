package data.scripts.relations;

import com.fs.starfarer.api.impl.campaign.ids.Factions;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorGeneratorPlugin;
import com.fs.starfarer.api.campaign.FactionAPI;
import  data.scripts.LIB_ModPlugin;
import com.fs.starfarer.api.impl.campaign.shared.SharedData;

public class LIB_Relations implements SectorGeneratorPlugin {
    @Override
    public static void generate(SectorAPI sector) {
        FactionAPI FFA = sector.getFaction("FFA"); // assigns a faction variable
        SharedData.getData().getPersonBountyEventData().addParticipatingFaction("FFA");
        FFA.setRelationship(Factions.LUDDIC_CHURCH, -0.35f);
	FFA.setRelationship(Factions.INDEPENDENT, 0.5f);
        FFA.setRelationship(Factions.LUDDIC_PATH, -0.99f);
        FFA.setRelationship(Factions.PERSEAN, -.35f);
        FFA.setRelationship(Factions.PIRATES, -0.5f);
        FFA.setRelationship("sindrian_diktat", -0.9f);
        FFA.setRelationship("hegemony", -0.9f);
        FFA.setRelationship("tritachyon", 0.8f); 
        // mods
	//FFA.setRelationship("DR", -0.7f); 
        FFA.setRelationship("hcok", -0.7f);    
        FFA.setRelationship("ironshell", -0.9f);       
        FFA.setRelationship("battlefleets_imperium", -0.54f); 
        FFA.setRelationship("sevencorp", -0.5f);	 
        FFA.setRelationship("xlu", 0.5f);	 
        FFA.setRelationship("GKSec", -.95f);	 
        FFA.setRelationship("MVS", 0.4f);
        FFA.setRelationship("syndicate_asp", -0.24f);	   
        FFA.setRelationship("cmc", -0.2f);        
        FFA.setRelationship("SCY", -0.54f);     
        FFA.setRelationship("neutrinocorp", 0.25f);
        FFA.setRelationship("battlefleets_forcesofchaos", -0.70f);           
        FFA.setRelationship("xhanempire", -0.85f);	
        FFA.setRelationship("dassault_mikoyan", 0.4f);
        FFA.setRelationship("scalartech", 0.5f);     
        FFA.setRelationship("blackrock_driveyards", 0.5f);	   
        FFA.setRelationship("uaf", -0.01f);
        FFA.setRelationship("pn_colony", 0.69f);    
        FFA.setRelationship("junk_pirates", -0.55f);  
        FFA.setRelationship("ORA", -0.30f);          
        FFA.setRelationship("shadow_industry", 0.95f);	
        FFA.setRelationship("vic", 0.55f);     
        FFA.setRelationship("interstellarimperium", -0.65f);     
        FFA.setRelationship("prv", 0.42f);   
        FFA.setRelationship("new_galactic_order", -0.6f);	
        FFA.setRelationship("battlefleets_ork_pirates", -0.5f);
        FFA.setRelationship("diableavionics", -0.4f);      
        FFA.setRelationship("unitedpamed", -0.15f);     
        FFA.setRelationship("rb", -0.5f);    
        FFA.setRelationship("JYD", 0.51f);
	FFA.setRelationship("legionarry", -0.5f);      
        // FFA.setRelationship("Lte", 0.0f);   
        FFA.setRelationship("gmda", -0.20f);   
        // FFA.setRelationship("oculus", -0.25f);     
        // FFA.setRelationship("nomads", -0.25f); 
        // FFA.setRelationship("thulelegacy", -0.25f); 
        // FFA.setRelationship("infected", -0.99f); 
	FFA.setRelationship("fantasy_manufacturing", 0.35f);
	FFA.setRelationship("Goat_Aviation_Bureau", 0.40f);
	FFA.setRelationship("fob", -0.10f); 
	FFA.setRelationship("batavia", -0.50f); 

	
    }

}