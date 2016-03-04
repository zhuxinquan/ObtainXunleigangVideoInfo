/**
 * Created by zhuxinquan on 16-3-2.
 */
public class VideoInfo {
    private String name;            //电影名
    private String imageQuality;    //画质
    private String sort;            //分类
    private String area;            //地区
    private String year;            //年份
    private String director;        //导演
    private String starring;        //主演
    private String url;             //详情页url
    private String imageUrl;        //图片链接
    private String score;           //得到评分

    public String getImageQuality() {
        return imageQuality;
    }

    public void setImageQuality(String imageQuality) {
        this.imageQuality = imageQuality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getStarring() {
        return starring;
    }

    public void setStarring(String starring) {
        this.starring = starring;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "VideoInfo{" +
                "name='" + name + '\'' +
                ", imageQuality='" + imageQuality + '\'' +
                ", sort='" + sort + '\'' +
                ", area='" + area + '\'' +
                ", year='" + year + '\'' +
                ", director='" + director + '\'' +
                ", starring='" + starring + '\'' +
                ", url='" + url + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", score='" + score + '\'' +
                '}';
    }
}
