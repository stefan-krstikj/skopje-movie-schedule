package mk.ukim.finki.skopjemovieschedule.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class JscoupParser {
    private String mURL;
    private String mCinemaName;
    private Document mDocument;


    public JscoupParser(String mURL, String mCinemaName) throws IOException {
        this.mURL = mURL;
        this.mCinemaName = mCinemaName;
        mDocument = Jsoup.connect(mURL).get();
    }


}
