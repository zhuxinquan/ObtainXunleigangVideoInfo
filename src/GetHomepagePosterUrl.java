import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Created by zhuxinquan on 16-3-3.
 */
public class GetHomepagePosterUrl {
    public final static String URL = "jdbc:mysql://localhost:3306/moviedb?characterEncoding=utf-8";
    public final static String USERNAME = "root";
    public final static String PASSWD = "2737353904";
    public final static String Driver = "com.mysql.jdbc.Driver";

    //保存海报Url
    private List<VideoInfo> videoInfos = new LinkedList<>();

    GetHomepagePosterUrl(String url){
        Document doc = null;
        try {
            doc = (Document) Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_0) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.56 Safari/535.11")
//                    .proxy("112.195.87.183", 9000)
                    .timeout(10000).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //得到整个网页源码
        String html = doc.toString();

        //所有海报块
        Pattern reg_form = Pattern.compile("<form method=\"post\" autocomplete=\"off\" name=\"moderate\" id=\"moderate\" action=\"forum([\\s\\S]*)</form>");
        Matcher m = reg_form.matcher(html);
        String formPost = null;
        if(m.find()){
            formPost = m.group(0);
//            System.out.println(formPost);
        }
        //取得链接地址
        Pattern reg_link = Pattern.compile("<li style=\"width:150px;\">([\\s\\S]*?)</li>");
        Matcher m1 = reg_link.matcher(formPost);
        String infourl = null;
        String infoImg = null;
        while(m1.find()) {
            infourl = m1.group(0);
            infoImg = m1.group(0);
            Pattern reg_link2 = Pattern.compile("<span class=\"mnch\"><a href=(.*?)</a>");
            Matcher m2 = reg_link2.matcher(infourl);
            if(m2.find()){
                infourl = m2.group(0).substring(m2.group(0).indexOf("href=\"") + 6, m2.group(0).indexOf(".html\"") + 5);
            }
            Pattern reg_imglink = Pattern.compile("<img src=\"data/attachment/forum/.*?(jpg)");
            m2 = reg_imglink.matcher(infoImg);
            if(m2.find()) {
                String imgUrl = m2.group(0);
                Pattern p3 = Pattern.compile("data/attachment/.*jpg");
                Matcher m3 = p3.matcher(imgUrl);
                if(m3.find()) {
                    imgUrl = m3.group(0);
                }
                infoImg = imgUrl;
                infoImg = "http://www.xunleigang.com/" + infoImg;
            }
            infourl = "http://www.xunleigang.com/" + infourl;
            GetMovieInfo getInfo = new GetMovieInfo(infourl, infoImg);
            String s = infoImg;
            Pattern p3 = Pattern.compile("data/attachment/.*jpg");
            Matcher m3 = p3.matcher(s);
            if(m3.find()){
                videoInfos.add(getInfo.getVideoInfo());
            }
            getInfo.getInfo();
        }
        try {
            Class.forName(Driver);
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWD);
            String sql = "insert into movie_data(name, picture, classfication, area, year, director, star, score, download, jpg) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            for (VideoInfo v:videoInfos) {
                writeIntoDB(v, ps);
//            System.out.println(v);
            }
            ps.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void writeIntoDB(VideoInfo v, PreparedStatement ps){
        try {
            ps.setString(1, v.getName());
            ps.setString(2, v.getImageQuality());
            ps.setString(3, v.getSort());
            ps.setString(4, v.getArea());
            ps.setString(5, v.getYear());
            ps.setString(6, v.getDirector());
            ps.setString(7, v.getStarring());
            ps.setString(8, v.getScore());
            ps.setString(9, v.getUrl());
            ps.setString(10, v.getImageUrl());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
