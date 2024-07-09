package elna.torla.tft.service;

import aj.org.objectweb.asm.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import elna.torla.tft.entities.Champion;
import elna.torla.tft.entities.Item;
import elna.torla.tft.entities.Trait;
import elna.torla.tft.entities.TraitLevel;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Service
public class InitDbService {

    private ObjectMapper traitsFr;
    private ObjectMapper traitsEn;
    private ObjectMapper itemsFr;
    private ObjectMapper itemsEn;
    private ObjectMapper championsFr;
    private ObjectMapper championsEn;
    private TraitService traitService;
    private ItemService itemService;
    private ChampionService championService;
    private TraitLevelService traitLevelService;

    public InitDbService(TraitService traitService, ItemService itemService, ChampionService championService, TraitLevelService traitLevelService) {
        this.traitsFr = new ObjectMapper();;
        this.traitsEn = new ObjectMapper();;
        this.itemsFr = new ObjectMapper();;
        this.itemsEn = new ObjectMapper();;
        this.championsFr = new ObjectMapper();;
        this.championsEn = new ObjectMapper();;
        this.traitService = traitService;
        this.itemService = itemService;
        this.championService = championService;
        this.traitLevelService = traitLevelService;
    }

    public void initDatabase() {
        // Traits En Français
        JsonNode jsonTraitsFr;
        try(InputStream streamTraitsFr = TypeReference.class.getResourceAsStream("/riotdatas/fr/tft-trait.json")){
            jsonTraitsFr=traitsFr.readValue(streamTraitsFr, JsonNode.class);

        } catch (IOException e)
        {
            throw new RuntimeException("Failed to Read tft-trait.json",e);
        }
        JsonNode dataTraitsFr =  getData(jsonTraitsFr);
        for (JsonNode traitFr : dataTraitsFr){
            createOrUpdateTraitFromNode(traitFr,"fr");
        }

        // Traits En Anglais
        JsonNode jsonTraitsEn;
        try(InputStream streamTraitsEn = TypeReference.class.getResourceAsStream("/riotdatas/en/tft-trait.json")){
            jsonTraitsEn=traitsEn.readValue(streamTraitsEn, JsonNode.class);

        } catch (IOException e)
        {
            throw new RuntimeException("Failed to Read tft-trait.json",e);
        }
        JsonNode dataTraitsEn =  getData(jsonTraitsEn);
        for (JsonNode traitEn : dataTraitsEn){
            createOrUpdateTraitFromNode(traitEn,"en");
        }

        // Items En Français
        JsonNode jsonItemsFr;
        try(InputStream streamItemsFr = TypeReference.class.getResourceAsStream("/riotdatas/fr/tft-item.json")){
            jsonItemsFr=itemsFr.readValue(streamItemsFr, JsonNode.class);

        } catch (IOException e)
        {
            throw new RuntimeException("Failed to Read tft-item.json",e);
        }
        JsonNode dataItemsFr =  getData(jsonItemsFr);
        for (JsonNode itemFr : dataItemsFr){
            createOrUpdateItemFromNode(itemFr,"fr");
        }

        // Items En Anglais
        JsonNode jsonItemsEn;
        try(InputStream streamItemsEn = TypeReference.class.getResourceAsStream("/riotdatas/en/tft-item.json")){
            jsonItemsEn=itemsEn.readValue(streamItemsEn, JsonNode.class);

        } catch (IOException e)
        {
            throw new RuntimeException("Failed to Read tft-item.json",e);
        }
        JsonNode dataItemsEn =  getData(jsonItemsEn);
        for (JsonNode itemEn : dataItemsEn){
            createOrUpdateItemFromNode(itemEn,"en");
        }

        // Champions en Français
        JsonNode jsonChampionsFr;
        try(InputStream streamChampionsFr = TypeReference.class.getResourceAsStream("/riotdatas/fr/tft-champion.json")){
            jsonChampionsFr=championsFr.readValue(streamChampionsFr, JsonNode.class);

        } catch (IOException e)
        {
            throw new RuntimeException("Failed to Read tft-champion.json",e);
        }
        JsonNode dataChampionsFr =  getData(jsonChampionsFr);
        for (JsonNode championFr : dataChampionsFr){
            createOrUpdateChampionFromNode(championFr,"fr");
        }

        // Champions en Anglais
        JsonNode jsonChampionsEn;
        try(InputStream streamChampionsEn = TypeReference.class.getResourceAsStream("/riotdatas/en/tft-champion.json")){
            jsonChampionsEn=championsEn.readValue(streamChampionsEn, JsonNode.class);

        } catch (IOException e)
        {
            throw new RuntimeException("Failed to Read tft-champion.json",e);
        }
        JsonNode dataChampionsEn =  getData(jsonChampionsEn);
        for (JsonNode championEn : dataChampionsEn){
            createOrUpdateChampionFromNode(championEn,"en");
        }
    }

    private void createOrUpdateTraitFromNode (JsonNode trait, String language)
    {
        String riotId = trait.get("id").asText();
        if(!riotId.contains("TFTTutorial")&&!riotId.contains("TFTEvent")) {
            String name = trait.get("name").asText();
            String urlImage = "https://rerollcdn.com/icons/" + name.toLowerCase() + ".png";
            urlImage=urlImage.replaceAll("\\s", "");

            Trait traitInDb = this.traitService.getTraitByRiotId(riotId);
            if (traitInDb != null) {
                if (language.equals("fr"))
                    traitInDb.setNom(name);
                else if (language.equals("en")) {
                    traitInDb.setNomAnglais(name);
                }
                traitInDb.setUrlImage(urlImage);
                this.traitService.updateTrait(traitInDb.getId(),traitInDb);

                JsonNode palliers = trait.get("palliers");
                for (JsonNode pallier:palliers){
                    createOrUpdateTraitPallierFromNode(pallier,traitInDb);
                }
            } else {
                Trait traitACreer = new Trait();
                traitACreer.setNom(name);
                traitACreer.setNomAnglais(name);
                traitACreer.setRiotId(riotId);
                traitACreer.setUrlImage(urlImage);
                this.traitService.createTrait(traitACreer);

                JsonNode palliers = trait.get("palliers");
                for (JsonNode pallier:palliers){
                    createOrUpdateTraitPallierFromNode(pallier,traitACreer);
                }
            }
        }
    }

    private void createOrUpdateTraitPallierFromNode (JsonNode pallier, Trait trait)
    {
        int value = pallier.get("value").asInt();
        String color = pallier.get("color").asText();
        TraitLevel traitLevel= new TraitLevel(0,trait,value,color);
        this.traitLevelService.createTraitLevel(traitLevel);
    }

    private void createOrUpdateItemFromNode (JsonNode item, String language)
    {
        String riotId = item.get("id").asText();
        if(!riotId.contains("TFT_Consumable") &&
                !riotId.contains("TFT_Assist")&&
                !riotId.contains("TFT_Item_Debug")&&
                !riotId.contains("TFTTutorial")&&
                !riotId.contains("TFTEvent")) {
            String name = item.get("name").asText();
            String urlImage = name.replaceAll("\\.", "");
            urlImage=urlImage.replaceAll("'", "");
            urlImage=urlImage.replaceAll("\\s", "");
            urlImage = "https://rerollcdn.com/items/"+ urlImage +".png";
            Item itemInDb = this.itemService.getItemByRiotId(riotId);
            if (itemInDb != null) {
                if (language.equals("fr"))
                    itemInDb.setNom(name);
                else if (language.equals("en")) {
                    itemInDb.setNomAnglais(name);
                }

                Trait traitBonus = new Trait();
                if (riotId.contains("EmblemItem"))
                {
                    int posDebut = riotId.indexOf("_Item_");
                    int posFin = riotId.indexOf("EmblemItem");
                    String nomTrait = riotId.substring(posDebut + 6, posFin);

                    Trait traitBonusInDb = this.traitService.getTraitByNomAnglais(nomTrait);
                    if (traitBonusInDb != null) {
                        itemInDb.setTraitBonus(traitBonusInDb);
                    } else {
                        itemInDb.setTraitBonus(traitBonus);
                    }
                }
                else {
                    itemInDb.setTraitBonus(traitBonus);
                }
                itemInDb.setUrlImage(urlImage);
                this.itemService.updateItem(itemInDb.getId(),itemInDb);
            } else {
                Item itemACreer = new Item();
                Trait traitBonus = new Trait();
                if (riotId.contains("EmblemItem"))
                {
                    int posDebut = riotId.indexOf("_Item_");
                    int posFin = riotId.indexOf("EmblemItem");
                    String nomTrait = riotId.substring(posDebut + 6, posFin);

                    Trait traitBonusInDb = this.traitService.getTraitByNomAnglais(nomTrait);
                    if (traitBonusInDb != null)
                    {
                        itemACreer.setTraitBonus(traitBonusInDb);
                    }else {
                        itemACreer.setTraitBonus(traitBonus);
                    }
                }
                else {
                    itemACreer.setTraitBonus(traitBonus);
                }
                itemACreer.setNom(name);
                itemACreer.setNomAnglais(name);
                itemACreer.setRiotId(riotId);
                itemACreer.setUrlImage(urlImage);
                this.itemService.createItem(itemACreer);
            }
        }
    }

    private void createOrUpdateChampionFromNode (JsonNode champion, String language)
    {
        String riotId = champion.get("id").asText();
        if(!riotId.contains("TFTTutorial")&&
                !riotId.contains("TFTEvent")) {
            String name = champion.get("name").asText();
            int cout = champion.get("tier").asInt();
            JsonNode image = champion.get("image");
            String nameImage = image.get("full").asText();
            String idTrait1 = champion.get("trait1").asText();
            String idTrait2 = champion.get("trait2").asText();
            String idTrait3 = champion.get("trait3").asText();

            String urlIcone = "https://rerollcdn.com/characters/Skin/11/"+ name;
            urlIcone=urlIcone.replaceAll("'", "");
            urlIcone=urlIcone.replaceAll("\\s", "");
            urlIcone+= ".png";
            Champion championInDb = this.championService.getChampionByRiotId(riotId);
            if (championInDb != null) {
                if (language.equals("fr"))
                    championInDb.setNom(name);
                else if (language.equals("en")) {
                    championInDb.setNomAnglais(name);
                }
                Trait trait1 = this.traitService.getTraitByRiotId(idTrait1);
                Trait trait2 = this.traitService.getTraitByRiotId(idTrait2);
                championInDb.setTrait1(trait1);
                championInDb.setTrait2(trait2);

                if (idTrait3.equals("None"))
                {
                    Trait trait3 = new Trait();
                    championInDb.setTrait3(trait3);
                }
                else
                {
                    Trait trait3 = this.traitService.getTraitByRiotId(idTrait3);
                    championInDb.setTrait3(trait3);
                }
                championInDb.setCout(cout);
                championInDb.setUrlIcone(urlIcone);

                this.championService.updateChampion(championInDb.getId(),championInDb);
            } else {
                Champion championACreer = new Champion();

                Trait trait1 = this.traitService.getTraitByRiotId(idTrait1);
                Trait trait2 = this.traitService.getTraitByRiotId(idTrait2);
                Trait trait3 = this.traitService.getTraitByRiotId(idTrait3);
                championACreer.setTrait1(trait1);
                championACreer.setTrait2(trait2);
                championACreer.setTrait3(trait3);
                championACreer.setCout(cout);

                championACreer.setNom(name);
                championACreer.setNomAnglais(name);
                championACreer.setRiotId(riotId);
                championACreer.setUrlImage(nameImage);
                championACreer.setUrlIcone(urlIcone);
                this.championService.createChampion(championACreer);
            }
        }
    }

    private JsonNode getData(JsonNode json){
        return Optional.ofNullable(json)
                .map(j -> j.get("data"))
                .orElseThrow(() -> new IllegalArgumentException("Invalid JSON"));
    }


}
