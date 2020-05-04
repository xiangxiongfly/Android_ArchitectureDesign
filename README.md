## MVP_Sample



### 项目整体结构

```
└── mvp_sample
    ├── MainActivity.java
    ├── base
    │   ├── BaseActivity.java
    │   ├── BaseApplication.java
    │   ├── BaseFragment.java
    │   ├── BaseMvpActivity.java
    │   ├── BaseMvpFragment.java
    │   ├── BasePresenter.java
    │   └── interfaces
    │       ├── IPresenter.java
    │       └── IView.java
    ├── mvc
    │   └── ArticlesActivity.java
    ├── mvp_activity
    │   ├── ArticlesContract.java
    │   ├── ArticlesModel.java
    │   ├── ArticlesMvpActivity.java
    │   └── ArticlesPresenter.java
    └── mvp_fragment
        ├── ArticlesContract.java
        ├── ArticlesFragment.java
        ├── ArticlesModel.java
        ├── ArticlesPresenter.java
        └── FragmentWithActivity.java
```





### 代码实现

#### 定义P和V接口

```java
public interface IPresenter<V extends IView> {

    /**
     * 绑定View
     */
    void attachView(V view);

    /**
     * 解绑View
     */
    void detachView();

    /**
     * 是否绑定View
     */
    boolean isViewAttached();

    /**
     * 获取View
     */
    V getView();
}

...

public interface IView {

    /**
     * 显示加载框
     */
    void showLoading();

    /**
     * 隐藏加载框
     */
    void hideLoading();
}
```



#### 定义Activity和Fragment基类

```java
public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
            initViews();
        }
        mContext = this;
        initPresenter();
        initDatas();
    }

    /**
     * 获取布局Id
     */
    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * 初始化空间
     */
    protected abstract void initViews();

    /**
     * 初始化Presenter
     */
    protected void initPresenter() {

    }

    /**
     * 初始化数据
     */
    protected abstract void initDatas();
}

...

public abstract class BaseFragment extends Fragment {

    protected Context mContext;
    protected BaseActivity mActivity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
        mActivity = (BaseActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutId(), container, false);
        initViews(rootView);
        initDatas();
        return rootView;
    }

    /**
     * 获取布局Id
     */
    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * 初始化控件
     */
    protected abstract void initViews(View rootView);

    /**
     * 初始化数据
     */
    protected abstract void initDatas();
}
```



#### 定义P和V基类

```java
public abstract class BaseMvpActivity<P extends IPresenter> extends BaseActivity implements IView {

    private P mPresenter;

    //初始化Presenter
    @Override
    protected void initPresenter() {
        super.initPresenter();
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    /**
     * 创建Presenter
     */
    protected abstract P createPresenter();

    /**
     * 获取Presenter
     */
    protected P getPresenter() {
        return mPresenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
    }
}

...

public abstract class BaseMvpFragment<P extends IPresenter> extends BaseFragment implements IView {

    private P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    protected abstract P createPresenter();

    protected P getPresenter() {
        return mPresenter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
    }
}

...

public class BasePresenter<V extends IView> implements IPresenter<V> {
    private WeakReference<V> mRef;

    @Override
    public void attachView(V view) {
        mRef = new WeakReference<>(view);
    }

    @Override
    public void detachView() {
        if (mRef != null) {
            mRef.clear();
            mRef = null;
        }
    }

    @Override
    public boolean isViewAttached() {
        return mRef != null && mRef.get() != null;
    }

    @Override
    public V getView() {
        return mRef.get();
    }
}
```



#### 定义契约类

```java
/**
 * 契约类，用于关联MVP三者关系
 */
public class ArticlesContract {
    /**
     * M层接口
     */
    interface IArticlesModel {
        void getArticles(ArticesCallback callback);
    }

    /**
     * V层接口
     */
    interface IArticlesView {
        void articlesSuccess(String success);

        void articlesError(String error);
    }

    /**
     * P层接口
     */
    interface IArticlesPresenter {
        void getArticles();
    }

    /**
     * 网络请求回调
     */
    interface ArticesCallback {
        void onSuccess(String success);

        void onError(String error);
    }
}
```



#### 实现M层

```java
public class ArticlesModel implements ArticlesContract.IArticlesModel {
    @Override
    public void getArticles(final ArticlesContract.ArticesCallback callback) {
        String url = "https://wanandroid.com/wxarticle/chapters/json";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                if (callback != null) {
                    callback.onError(e.getMessage());
                }
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                String result = response.body().string();
                if (callback != null) {
                    callback.onSuccess(result);
                }
            }
        });
    }
}
```



#### 实现V层

```java
public class ArticlesMvpActivity extends BaseMvpActivity<ArticlesPresenter> implements ArticlesContract.IArticlesView {


    private Button btn_get_articles;
    private TextView tv_articles;

    @Override
    protected ArticlesPresenter createPresenter() {
        return new ArticlesPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.articles_layout;
    }

    @Override
    protected void initViews() {
        btn_get_articles = findViewById(R.id.btn_get_articles);
        tv_articles = findViewById(R.id.tv_articles);
        btn_get_articles.setOnClickListener(v -> {
            showLoading();
            getPresenter().getArticles();
        });
    }

    @Override
    protected void initDatas() {

    }

    @Override
    public void articlesSuccess(final String success) {
        hideLoading();
        runOnUiThread(() -> tv_articles.setText(success));
    }

    @Override
    public void articlesError(final String error) {
        hideLoading();
        runOnUiThread(() -> tv_articles.setText(error));
    }

    private ProgressDialog progressDialog;

    @Override
    public void showLoading() {
        runOnUiThread(() -> {
            if (progressDialog == null) {
                progressDialog = new ProgressDialog(mContext);
                progressDialog.setMessage("加载中");
            }
            progressDialog.show();
        });
    }

    @Override
    public void hideLoading() {
        runOnUiThread(() -> {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.hide();
            }
        });
    }
}
```



#### 实现P层

```java
public class ArticlesPresenter extends BasePresenter<ArticlesMvpActivity> implements ArticlesContract.IArticlesPresenter {

    private final ArticlesModel mArticlesModel;

    public ArticlesPresenter() {
        mArticlesModel = new ArticlesModel();
    }

    @Override
    public void getArticles() {
        mArticlesModel.getArticles(new ArticlesContract.ArticesCallback() {
            @Override
            public void onSuccess(String success) {
                if (isViewAttached()) {
                    getView().articlesSuccess(success);
                }
            }

            @Override
            public void onError(String error) {
                if (isViewAttached()) {
                    getView().articlesError(error);
                }
            }
        });
    }
}
```











