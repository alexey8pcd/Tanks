package ru.ovcharov_alexey.tanks.v4.logic.campaign;

/**
 * @author Alexey
 */
public class LevelAndCampaign {

    private int levelNumber;
    private Campaign campaign;

    public LevelAndCampaign(int levelNumber, Campaign campaign) {
        if (levelNumber < 0 && levelNumber >= campaign.getLevels().size()) {
            levelNumber = 0;
        }
        this.levelNumber = levelNumber;
        this.campaign = campaign;
    }

    public LevelAndCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public Campaign getCampaign() {
        return campaign;
    }

}
