# retrofitDemo
retrofitDemo


### 参数注解

@GET()： 注解在方法上，表示get请求,括号内为请求url
@POST()： 注解在方法上，表示Post请求,括号内为请求url
@PUT()： Put请求
@DELETE()： Delete请求
@Query()： get请求时，注解在方法中，注解后括号中是键，跟在后面的是值，将会以key=value的方式拼接在url后面
@QueryMap：get请求时，如果参数很多，可以将参数集成到map集合，用此注解在方法中传递
@Field()： Post请求时，注解在方法中，注解后括号中是键，跟在后面的是值，将每一个请求参数都存放至请求体中
@FieldMap：Post请求时，如果参数很多，可以将参数集成到map集合，用此注解在方法中传递
@Body：若Post请求参数有多个，可统一封装到一个类中，用此注解在方法中传递
@Path()： 注解在方法中，注解后括号中的是url上的占位符，跟在后面的是填充到url占位符上的字符串
@FormUrlEncoded()：用于Post请求，将会自动将请求参数的类型调整为application/x-www-form-urlencoded
@Multipart()： 用于Post请求，上传
@Part()： 用于Post请求，注解在上传的方法中，上传的参数
@Headers()： 添加许多请求头，括号内为请求头内容
@Header()： 添加一个请求头，有重名的将会覆盖


我们先来看看官网上的案例：

1. 先定义你的网络接口

```
public interface GitHubService {
    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);
}
复制代码
```

1. 创建Retrofit对象

```
Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .build();
复制代码
```

2. 获得网络请求API的实例

```
GitHubService service = retrofit.create(GitHubService.class);
复制代码
```

3. 调用API方法

```
Call<List<Repo>> call = service.listRepos("octocat");
复制代码
```

4. 执行网络请求

```
// 同步
try {
    List<Repo> repos = call.execute().body();
} catch (IOException e) {
    e.printStackTrace();
}

// 异步
call.enqueue(new Callback<List<Repo>>() {
    @Override
    public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
        // 数据返回成功
    }

    @Override
    public void onFailure(Call<List<Repo>> call, Throwable t) {
       // 数据返回失败
    }
});
复制代码
```

至此，`Retrofit`的一次网络请求就结束了.

