
package net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.twitch;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TwitchFollowsDto {

    @SerializedName("_total")
    @Expose
    private long total;
    @SerializedName("streams")
    @Expose
    private List<Stream> streams = null;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<Stream> getStreams() {
        return streams;
    }

    public void setStreams(List<Stream> streams) {
        this.streams = streams;
    }

}
