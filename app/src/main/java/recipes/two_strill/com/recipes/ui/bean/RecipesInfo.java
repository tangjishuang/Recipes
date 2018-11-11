package recipes.two_strill.com.recipes.ui.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by SD on 2018/8/24.
 */

public class RecipesInfo  implements Serializable  {


    /**
     * resultcode : 200
     * reason : Success
     * result : {"data":[{"id":"2185","title":"西瓜皮炒毛豆","tags":"炒;夏季;快手菜;夏季菜谱;创意菜","imtro":"平时吃西瓜都把西瓜皮给扔了，一次看电视偶然发现西瓜是个好东西，西瓜皮含有丰富的糖类、矿物质、维生素，具有清热解毒、利尿等功效。用于消暑解渴、清热解毒，西瓜皮优于西瓜瓤，西瓜皮不再扔了，用西瓜皮炒毛豆可当下饭菜挺不错的.","ingredients":"西瓜皮,半只;毛豆,100g","burden":"油,适量;盐,少许;味精,少许;剁椒,少许","albums":["http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/3/2185_459569.jpg"],"steps":[{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/22/2185_740a660f63c2019c.jpg","step":"1.所有食材"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/22/2185_289f1cf3db095598.jpg","step":"2.毛豆去壳洗净，西瓜皮去皮洗净切条"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/22/2185_a9ab42b087c38da4.jpg","step":"3.起油锅加热"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/22/2185_5b494f17bb254c1b.jpg","step":"4.放入毛豆煸炒5分钟"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/22/2185_1ab3e8c57b2b6eb7.jpg","step":"5.放入西瓜皮煸炒"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/22/2185_c38d0bb54cf01515.jpg","step":"6.再放少许盐煸炒"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/22/2185_b52b35d45bc9925e.jpg","step":"7.然后放少许剁椒煸炒"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/22/2185_b3e302d14a1ea4fd.jpg","step":"8.最后放少许味精炒匀出锅"}]}],"totalNum":"183","pn":"0","rn":"1"}
     * error_code : 0
     */

    private String resultcode;
    private String reason;
    private ResultBean result;
    private int error_code;

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    @Override
    public String toString() {
        return "RecipesInfo{" +
                       "resultcode='" + resultcode + '\'' +
                       ", reason='" + reason + '\'' +
                       ", result=" + result +
                       ", error_code=" + error_code +
                       '}';
    }

    public static class ResultBean implements Serializable{


        /**
         * data : [{"id":"2185","title":"西瓜皮炒毛豆","tags":"炒;夏季;快手菜;夏季菜谱;创意菜","imtro":"平时吃西瓜都把西瓜皮给扔了，一次看电视偶然发现西瓜是个好东西，西瓜皮含有丰富的糖类、矿物质、维生素，具有清热解毒、利尿等功效。用于消暑解渴、清热解毒，西瓜皮优于西瓜瓤，西瓜皮不再扔了，用西瓜皮炒毛豆可当下饭菜挺不错的.","ingredients":"西瓜皮,半只;毛豆,100g","burden":"油,适量;盐,少许;味精,少许;剁椒,少许","albums":["http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/3/2185_459569.jpg"],"steps":[{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/22/2185_740a660f63c2019c.jpg","step":"1.所有食材"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/22/2185_289f1cf3db095598.jpg","step":"2.毛豆去壳洗净，西瓜皮去皮洗净切条"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/22/2185_a9ab42b087c38da4.jpg","step":"3.起油锅加热"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/22/2185_5b494f17bb254c1b.jpg","step":"4.放入毛豆煸炒5分钟"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/22/2185_1ab3e8c57b2b6eb7.jpg","step":"5.放入西瓜皮煸炒"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/22/2185_c38d0bb54cf01515.jpg","step":"6.再放少许盐煸炒"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/22/2185_b52b35d45bc9925e.jpg","step":"7.然后放少许剁椒煸炒"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/22/2185_b3e302d14a1ea4fd.jpg","step":"8.最后放少许味精炒匀出锅"}]}]
         * totalNum : 183
         * pn : 0
         * rn : 1
         */
        @Override
        public String toString() {
            return "ResultBean{" +
                           "data=" + data +
                           '}';
        }

        private String totalNum;
        private String pn;
        private String rn;
        private List<DataBean> data;

        public String getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(String totalNum) {
            this.totalNum = totalNum;
        }

        public String getPn() {
            return pn;
        }

        public void setPn(String pn) {
            this.pn = pn;
        }

        public String getRn() {
            return rn;
        }

        public void setRn(String rn) {
            this.rn = rn;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean  implements Serializable{
            @Override
            public String toString() {
                return "DataBean{" +
                               "id='" + id + '\'' +
                               ", title='" + title + '\'' +
                               ", tags='" + tags + '\'' +
                               ", imtro='" + imtro + '\'' +
                               ", ingredients='" + ingredients + '\'' +
                               ", burden='" + burden + '\'' +
                               ", albums=" + albums +
                               ", steps=" + steps +
                               '}';
            }

            /**
             * id : 2185
             * title : 西瓜皮炒毛豆
             * tags : 炒;夏季;快手菜;夏季菜谱;创意菜
             * imtro : 平时吃西瓜都把西瓜皮给扔了，一次看电视偶然发现西瓜是个好东西，西瓜皮含有丰富的糖类、矿物质、维生素，具有清热解毒、利尿等功效。用于消暑解渴、清热解毒，西瓜皮优于西瓜瓤，西瓜皮不再扔了，用西瓜皮炒毛豆可当下饭菜挺不错的.
             * ingredients : 西瓜皮,半只;毛豆,100g
             * burden : 油,适量;盐,少许;味精,少许;剁椒,少许
             * albums : ["http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/3/2185_459569.jpg"]
             * steps : [{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/22/2185_740a660f63c2019c.jpg","step":"1.所有食材"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/22/2185_289f1cf3db095598.jpg","step":"2.毛豆去壳洗净，西瓜皮去皮洗净切条"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/22/2185_a9ab42b087c38da4.jpg","step":"3.起油锅加热"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/22/2185_5b494f17bb254c1b.jpg","step":"4.放入毛豆煸炒5分钟"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/22/2185_1ab3e8c57b2b6eb7.jpg","step":"5.放入西瓜皮煸炒"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/22/2185_c38d0bb54cf01515.jpg","step":"6.再放少许盐煸炒"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/22/2185_b52b35d45bc9925e.jpg","step":"7.然后放少许剁椒煸炒"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/22/2185_b3e302d14a1ea4fd.jpg","step":"8.最后放少许味精炒匀出锅"}]
             */

            private String id;
            private String title;
            private String tags;
            private String imtro;
            private String ingredients;
            private String burden;
            private List<String> albums;
            private List<StepsBean> steps;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTags() {
                return tags;
            }

            public void setTags(String tags) {
                this.tags = tags;
            }

            public String getImtro() {
                return imtro;
            }

            public void setImtro(String imtro) {
                this.imtro = imtro;
            }

            public String getIngredients() {
                return ingredients;
            }

            public void setIngredients(String ingredients) {
                this.ingredients = ingredients;
            }

            public String getBurden() {
                return burden;
            }

            public void setBurden(String burden) {
                this.burden = burden;
            }

            public List<String> getAlbums() {
                return albums;
            }

            public void setAlbums(List<String> albums) {
                this.albums = albums;
            }

            public List<StepsBean> getSteps() {
                return steps;
            }

            public void setSteps(List<StepsBean> steps) {
                this.steps = steps;
            }

            public static class StepsBean implements Serializable{
                /**
                 * img : http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/22/2185_740a660f63c2019c.jpg
                 * step : 1.所有食材
                 */

                private String img;
                private String step;

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }

                public String getStep() {
                    return step;
                }

                public void setStep(String step) {
                    this.step = step;
                }
            }
        }
    }
}
