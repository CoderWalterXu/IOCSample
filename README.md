# IOCSample
IOC注入思想，通过反射+注解，轻松实现布局、属性、（点击）事件注入，支持RecyclerView条目点击和长按

BaseActivity#onCreate()
```
InjectManager.inject(this);
```


```
// 注入布局
@ContentView(R.layout.activity_main)
public class MainActivity extends BaseIOCActivity {

    // 可以被private修饰
    @InjectView(R.id.tv)
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 注意，使用了IOC注入后，不能有setContentView()，否则IOC相关的都无效
    }
    
    @OnClick({R.id.tv, R.id.btn})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.tv:
                Toast.makeText(this, "点击了tv", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn:
                Toast.makeText(this, "点击了Button", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @OnLongClick({R.id.tv, R.id.btn})
    public boolean longClick(View btn) {
        switch (btn.getId()) {
            case R.id.tv:
                Toast.makeText(this, "长按了tv", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn:
                Toast.makeText(this, "长按了btn", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }

}
```


RecyclerView
```
    @OnItemClick(R.id.recyclerView)
    public void itemClick(View view, ItemBean itemBean, int position) {
        Toast.makeText(this, "点击了条目" + itemBean.getIndex(), Toast.LENGTH_SHORT).show();
    }

    @OnItemLongClick(R.id.recyclerView)
    public boolean itemLongClick(View view, ItemBean itemBean, int position) {
        Toast.makeText(this, "长按了条目" + itemBean.getIndex(), Toast.LENGTH_SHORT).show();
        return true;
    }
```

