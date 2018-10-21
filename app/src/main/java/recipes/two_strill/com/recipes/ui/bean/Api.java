package recipes.two_strill.com.recipes.ui.bean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by SD on 2018/8/25.
 */

public interface Api {


    //搜索：http://apis.juhe.cn/cook/query.php
    /*@POST("query.php")
    Observable<RecipesInfo> getSearch(@Body SearchParamsBean searchParamsBean);*/
    @GET("query.php")
    Observable<RecipesInfo> getSearch(@Query("key") String key,@Query("menu") String menu);

    @GET("query.php")
    Observable<RecipesInfo> getBinner(@Query("key") String key, @Query("menu") String menu, @Query("rn") int rn);

    //分类标签列表：http://apis.juhe.cn/cook/category
    @GET("category")
    Observable<CategoryInfo> getCategory(@Query("key") String key, @Query("parentid")String parentid);

    //按标签检索菜谱：http://apis.juhe.cn/cook/index
    @GET("index")
    Observable<RecipesInfo> getIndex(@Query("key") String key, @Query("cid")String cid);



    //按照菜谱ID查看详情：http://apis.juhe.cn/cook/queryid




}
