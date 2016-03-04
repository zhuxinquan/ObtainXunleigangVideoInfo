import java.util.LinkedList;
import java.util.List;

/**
 * 抓取www.xunleigang.com电影详情
 * Created by zhuxinquan on 16-3-3.
 */
public class Main {
    public static void main(String[] args) {
        //海报从第一页开始改变链接最后的页数
        for(int i = 165; i <= 264; i++) {
            String url = "http://www.xunleigang.com/forum.php?mod=forumdisplay&fid=2&ortid=1&sortid=1&page=";
            url = url + String.valueOf(i);
            System.out.println(url);
            GetHomepagePosterUrl get = new GetHomepagePosterUrl(url);
        }
    }
}
