package application.recyclerviewdemo.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;
import com.myjoke.baselibray.util.LogUtil;
import com.myjoke.baselibray.util.ScreenUtil;
import com.myjoke.baselibray.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import application.dialogdemo.dialog.MyDialog;
import application.recyclerviewdemo.R;
import application.recyclerviewdemo.R2;
import application.recyclerviewdemo.adapter.LinearLayoutAdapter;
import application.recyclerviewdemo.divider.LinearLayoutItemDecoration;
import application.recyclerviewdemo.model.Student;
import application.recyclerviewdemo.util.DataUtil;
import application.recyclerviewdemo.util.RecyclerViewConstant;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@Route(path = RecyclerViewConstant.LinearLayoutRecyclerViewActivity)
public class LinearLayoutRecyclerViewActivity extends BaseActivity implements LinearLayoutAdapter.LinearLayoutClickListener, LinearLayoutAdapter.LinearLayoutLongClickListener {

    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;

    LinearLayoutAdapter adapter;

    Unbinder unbinder = null;
    private int position = -1;
    private List<Student> studentList = new ArrayList<>();
    private int id = 100;


    @Override
    public int getLayoutId() {
        return R.layout.activity_linear_layout_recycler_view;
    }

    @Override
    public void initView() {
        unbinder = ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
        registerForContextMenu(recyclerView); // 给你指定View，添加上下文菜单
    }

    @Override
    public void initData() {
        studentList = DataUtil.getDataList();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new LinearLayoutAdapter(this, studentList);
        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new LinearLayoutItemDecoration());

        adapter.setLinearLayoutClickListener(this);
        adapter.setLinearLayoutLongClickListener(this);

        RecyclerView.Adapter adapter2 = recyclerView.getAdapter();
        LogUtil.e("LinearLayoutRecyclerViewActivity adapter==adapter2:" + Boolean.toString(adapter == adapter2));

        LogUtil.e("LinearLayoutRecyclerViewActivity adapter.getItemCount()=" + adapter.getItemCount());
        LogUtil.e("LinearLayoutRecyclerViewActivity ScreenUtil.getScreenHeight(this)=" + ScreenUtil.getScreenHeight(this));


        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                LogUtil.e("LinearLayoutRecyclerViewActivity  postDelayed recyclerView.getChildCount()=" + recyclerView.getChildCount());
                LogUtil.e("LinearLayoutRecyclerViewActivity  recyclerView.getBottom()=" + recyclerView.getBottom());
            }
        }, 1000);

        recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                recyclerView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//                recyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                LogUtil.e("LinearLayoutRecyclerViewActivity  recyclerView.getHeight()=" + recyclerView.getHeight());
            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        unbinder = null;
    }

    @Override
    public void onClick(View view, int position) {
        int childCount = recyclerView.getChildCount();
        View childView = recyclerView.getChildAt(position);
        int childLayoutPosition = recyclerView.getChildLayoutPosition(childView);
        int childAdapterPosition = recyclerView.getChildAdapterPosition(childView);

        LogUtil.e("position==" + position + ",,,childCount==" + childCount + "   ,,,childLayoutPosition==" + childLayoutPosition + "     ,,childAdapterPosition==" + childAdapterPosition);

//        ToastUtil.getInstance().showToast(LinearLayoutRecyclerViewActivity.this, "点击=" + position);


        studentList.get(position).setName("修改之后的名称");
        adapter.notifyItemChanged(position);
    }

    @Override
    public void onLongClick(View view, int position) {
        ToastUtil.getInstance().showToast(LinearLayoutRecyclerViewActivity.this, "长按=" + position);
        this.position = position;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.item_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (R.id.view == item.getItemId()) {
            ToastUtil.getInstance().showToast(LinearLayoutRecyclerViewActivity.this, "上下文菜单 查看=" + position);
            new MyDialog.Builder(LinearLayoutRecyclerViewActivity.this, R.style.DialogTheme)
                    .setCancelable(true).setCanceledOnTouchOutside(true)
                    .setTitle("查看").setContent(studentList.get(position).toString()).show();
        } else if (R.id.delete == item.getItemId()) {
            ToastUtil.getInstance().showToast(LinearLayoutRecyclerViewActivity.this, "上下文菜单 删除=" + position);
            studentList.remove(position);
            adapter.notifyItemRemoved(position);
        } else if (R.id.add == item.getItemId()) {
            ToastUtil.getInstance().showToast(LinearLayoutRecyclerViewActivity.this, "上下文菜单 添加=" + position);
            Student student = new Student(id, "name");
            studentList.add(student);
            student = null;
            adapter.notifyItemInserted(position);
            id++;
        } else if (R.id.update == item.getItemId()) {
            ToastUtil.getInstance().showToast(LinearLayoutRecyclerViewActivity.this, "上下文菜单 修改=" + position);
            Student student = studentList.get(position);
            student.setName("修改名称=" + position);
            adapter.notifyItemChanged(position);
        }


        return super.onContextItemSelected(item);
    }
}
