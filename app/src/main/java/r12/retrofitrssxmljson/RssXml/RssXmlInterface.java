package r12.retrofitrssxmljson.RssXml;

import r12.retrofitrssxmljson.RssXml.ModelRss.Rss;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RssXmlInterface {

    @GET("rss.xml")
    Call<Rss> getRssItems();
}
