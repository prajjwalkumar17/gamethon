package in.gamernation.app.APIResponses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ArcadeResponse {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("Leagues")
    @Expose
    private List<League> leagues = null;

    public ArcadeResponse(Integer count, List<League> leagues) {
        this.count = count;
        this.leagues = leagues;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<League> getLeagues() {
        return leagues;
    }

    public void setLeagues(List<League> leagues) {
        this.leagues = leagues;
    }

    public class League {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("thumb")
        @Expose
        private String thumb;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("entry")
        @Expose
        private Integer entry;
        @SerializedName("prizes")
        @Expose
        private Integer prizes;
        @SerializedName("kill_coins")
        @Expose
        private Integer killCoins;
        @SerializedName("start_date")
        @Expose
        private String startDate;
        @SerializedName("total_participant")
        @Expose
        private Integer totalParticipant;
        @SerializedName("map")
        @Expose
        private String map;
        @SerializedName("prize_pool")
        @Expose
        private List<PrizePool> prizePool = null;

        public League(String id, String thumb, String name, Integer entry, Integer prizes, Integer killCoins, String startDate, Integer totalParticipant, String map, List<PrizePool> prizePool) {
            this.id = id;
            this.thumb = thumb;
            this.name = name;
            this.entry = entry;
            this.prizes = prizes;
            this.killCoins = killCoins;
            this.startDate = startDate;
            this.totalParticipant = totalParticipant;
            this.map = map;
            this.prizePool = prizePool;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getEntry() {
            return entry;
        }

        public void setEntry(Integer entry) {
            this.entry = entry;
        }

        public Integer getPrizes() {
            return prizes;
        }

        public void setPrizes(Integer prizes) {
            this.prizes = prizes;
        }

        public Integer getKillCoins() {
            return killCoins;
        }

        public void setKillCoins(Integer killCoins) {
            this.killCoins = killCoins;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public Integer getTotalParticipant() {
            return totalParticipant;
        }

        public void setTotalParticipant(Integer totalParticipant) {
            this.totalParticipant = totalParticipant;
        }

        public String getMap() {
            return map;
        }

        public void setMap(String map) {
            this.map = map;
        }

        public List<PrizePool> getPrizePool() {
            return prizePool;
        }

        public void setPrizePool(List<PrizePool> prizePool) {
            this.prizePool = prizePool;
        }
    }


    public class PrizePool {

        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("standing")
        @Expose
        private String standing;
        @SerializedName("prize")
        @Expose
        private Integer prize;

        public PrizePool(String id, String standing, Integer prize) {
            this.id = id;
            this.standing = standing;
            this.prize = prize;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStanding() {
            return standing;
        }

        public void setStanding(String standing) {
            this.standing = standing;
        }

        public Integer getPrize() {
            return prize;
        }

        public void setPrize(Integer prize) {
            this.prize = prize;
        }
    }
}
