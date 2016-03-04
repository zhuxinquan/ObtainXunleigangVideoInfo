import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.PrivateKey;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhuxinquan on 16-3-3.
 */
public class GetMovieInfo {
    private String url;
    private String imgUrl;
    VideoInfo videoInfo = new VideoInfo();

    public GetMovieInfo(String url, String imgUrl) {
        this.url = url;
        this.imgUrl = imgUrl;
    }

    public void getInfo(){
        Document doc = null;
        try {
            System.out.println(url);
            doc = (Document) Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_0) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.56 Safari/535.11")
                    .timeout(10000)
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String html = doc.toString();
        Pattern p = Pattern.compile("<table summary=\"影片信息\"([\\s\\S]*?)</table>");
        Matcher m = p.matcher(html);

        //设置链接url
        videoInfo.setUrl(url);
        //设置图片url
        videoInfo.setImageUrl(imgUrl);

        if(m.find()) {
            String videoinfo = m.group(0);
            //得到电影名
            videoInfo.setName(html.substring(html.indexOf("id=\"thread_subject\">") + 20, html.indexOf("</a>", html.indexOf("id=\"thread_subject\">"))));
            //得到画质
            String huazhi = videoinfo.substring(videoinfo.indexOf("<th>画 质:</th>"), videoinfo.indexOf("</a>", videoinfo.indexOf("<th>画 质:</th>")));
            Pattern reg_htm = Pattern.compile("<[^>]+>");  //匹配html标签
            Matcher m1 = reg_htm.matcher(huazhi);
            if(m1.find()) {
                huazhi = m1.replaceAll("");
            }
            Pattern reg_nbsp1 = Pattern.compile(" ");               //匹配实际空格
            m = reg_nbsp1.matcher(huazhi);
            if(m.find()) {
                huazhi = m.replaceAll("");
            }
            Pattern reg_br = Pattern.compile("画质:\n");                 //匹配换行符
            m = reg_br.matcher(huazhi);
            if(m.find()) {
                huazhi = m.replaceAll("");
            }
            videoInfo.setImageQuality(huazhi);
            //得到分类
            String fenlei = videoinfo.substring(videoinfo.indexOf("<th>分 类:"), videoinfo.indexOf("</td>", videoinfo.indexOf("<th>分 类:")));
            m1 = reg_htm.matcher(fenlei);
            if(m1.find()) {
                fenlei = m1.replaceAll("");
            }
            m = reg_nbsp1.matcher(fenlei);
            if(m.find()) {
                fenlei = m.replaceAll("");
            }
            Pattern reg_fenlei = Pattern.compile("分类:\n");
            m = reg_fenlei.matcher(fenlei);
            if(m.find()) {
                fenlei = m.replaceAll("");
            }
            videoInfo.setSort(fenlei);
            //得到地区
            String area = videoinfo.substring(videoinfo.indexOf("<th>地 区:"), videoinfo.indexOf("</td>", videoinfo.indexOf("<th>地 区:")));
            m1 = reg_htm.matcher(area);
            if(m1.find()) {
                area = m1.replaceAll("");
            }
            m = reg_nbsp1.matcher(area);
            if(m.find()) {
                area = m.replaceAll("");
            }
            Pattern reg_area = Pattern.compile("地区:\n");
            m = reg_area.matcher(area);
            if(m.find()) {
                area = m.replaceAll("");
            }
            videoInfo.setArea(area);
            //得到年份
            String year = videoinfo.substring(videoinfo.indexOf("<th>年 份:"), videoinfo.indexOf("</td>", videoinfo.indexOf("<th>年 份:")));
            m1 = reg_htm.matcher(year);
            if(m1.find()) {
                year = m1.replaceAll("");
            }
            m = reg_nbsp1.matcher(year);
            if(m.find()) {
                year = m.replaceAll("");
            }
            Pattern reg_year = Pattern.compile("年份:\n");
            m = reg_year.matcher(year);
            if(m.find()) {
                year = m.replaceAll("");
            }
            videoInfo.setYear(year);
            //得到导演编剧
            String director = videoinfo.substring(videoinfo.indexOf("<th>导演/编剧:"), videoinfo.indexOf("</td>", videoinfo.indexOf("<th>导演/编剧:")));
            m1 = reg_htm.matcher(director);
            if(m1.find()) {
                director = m1.replaceAll("");
            }
            m = reg_nbsp1.matcher(director);
            if(m.find()) {
                director = m.replaceAll("");
            }
            Pattern reg_director = Pattern.compile("导演/编剧:\n");
            m = reg_director.matcher(director);
            if(m.find()) {
                director = m.replaceAll("");
            }
            videoInfo.setDirector(director);
            //得到主演
            String starring = videoinfo.substring(videoinfo.indexOf("<th>主 演:"), videoinfo.indexOf("</td>", videoinfo.indexOf("<th>主 演:")));
            m1 = reg_htm.matcher(starring);
            if(m1.find()) {
                starring = m1.replaceAll("");
            }
            m = reg_nbsp1.matcher(starring);
            if(m.find()) {
                starring = m.replaceAll("");
            }
            Pattern reg_starring = Pattern.compile("主演:\n");
            m = reg_starring.matcher(starring);
            if(m.find()) {
                starring = m.replaceAll("");
            }
            videoInfo.setStarring(starring);
            //得到评分
            String score = videoinfo.substring(videoinfo.indexOf("<th>豆瓣评分:"), videoinfo.indexOf("</td>", videoinfo.indexOf("<th>豆瓣评分:")));
            m1 = reg_htm.matcher(score);
            if(m1.find()) {
                score = m1.replaceAll("");
            }
            m = reg_nbsp1.matcher(score);
            if(m.find()) {
                score = m.replaceAll("");
            }
            Pattern reg_score = Pattern.compile("豆瓣评分:\n");
            m = reg_score.matcher(score);
            if(m.find()) {
                score = m.replaceAll("");
            }
            videoInfo.setScore(score);
        }
    }

    //返回电影信息
    public VideoInfo getVideoInfo() {
        return videoInfo;
    }
}

//class test2{
//    public static void main(String[] args) {
//        VideoInfo v = new VideoInfo();
//        GetMovieInfo getMovieInfo = new GetMovieInfo("http://www.xunleigang.com/thread-279702-1-1.html", "hello");
//        getMovieInfo.getInfo();
//        System.out.println(getMovieInfo.getVideoInfo());
//    }
//}


