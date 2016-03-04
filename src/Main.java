import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhuxinquan on 16-3-3.
 */
public class Main {
    public static void main(String[] args) {
        for(int i = 165; i <= 264; i++) {
            String url = "http://www.xunleigang.com/forum.php?mod=forumdisplay&fid=2&ortid=1&sortid=1&page=";
            url = url + String.valueOf(i);
            System.out.println(url);
            GetHomepagePosterUrl get = new GetHomepagePosterUrl(url);
        }
    }
}
